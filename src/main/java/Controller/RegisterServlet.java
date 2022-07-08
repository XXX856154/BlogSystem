package Controller;


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


@WebServlet("/register")
public class RegisterServlet extends HttpServlet
{
    private ObjectMapper objectMapper=new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String userName=req.getParameter("userName");
        String password=req.getParameter("password");
        if(userName==null||password==null||"".equals(userName)||"".equals(password))
        {
            return ;
        }


        User user =new User();
        user.setUserName(userName);
        user.setPassword(password);
        Hash hash=new Hash();
        if(hash.isContain(user))
        {
            resp.setContentType("application/json;charset=utf8");
            resp.getWriter().write("\"ok\":false");
            return ;
        }
        else
        {
            UserDao userDao=new UserDao();
            userDao.insert(user);
            HttpSession session = req.getSession(true);
            session.setAttribute("user",user);
            resp.setContentType("application/json;charset=utf8");
            resp.getWriter().write("\"ok\":true");

        }


    }
}
