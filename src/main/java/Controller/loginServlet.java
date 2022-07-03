package Controller;

import Model.BlogDao;
import Model.User;
import Model.UserDao;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class loginServlet extends HttpServlet
{
    private ObjectMapper objectMapper=new ObjectMapper();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //servlet默认的不是utf8
        req.setCharacterEncoding("utf8");

        String userName=req.getParameter("userName");
        String password=req.getParameter("password");
        System.out.println(userName+" "+password);
        //空和空串
        if(userName==null||"".equals(userName)||password==null||"".equals(password))
        {
            resp.setStatus(404);
            return;
        }

        User  user=UserDao.selectByName(userName);
        if(user==null||!user.getPassword().equals(password))
        {
            resp.setContentType("text/html;charset=utf8");
            resp.getWriter().write("用户或者密码错误");
            return ;
        }
        HttpSession session=req.getSession(true);
        session.setAttribute("user",user);
        resp.sendRedirect("blog_list.html");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //检测是否登录
        HttpSession session=req.getSession(false);
        User user ;
        //如果没有登录
        if(session==null)
        {
            user =new User();
            user.setUserId(0);
            user.setUserName("");
            user.setPassword("");
            String  s=objectMapper.writeValueAsString(user);
            resp.setContentType("application/json;charset=utf8");
            resp.getWriter().write(s);

        }
        else
        {
            user=(User)session.getAttribute("user");
            String  s=objectMapper.writeValueAsString(user);
            resp.setContentType("application/json;charset=utf8");
            resp.getWriter().write(s);
        }
    }
}
