package Controller;

import Model.Blog;
import Model.BlogDao;
import Model.User;
import Model.UserDao;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/author")
public class AuthorServlet extends HttpServlet
{
    private ObjectMapper objectMapper=new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("application/json;charset=utf8");
        String param=req.getParameter("blogId");
        if(param==null)
        {

            resp.getWriter().write("\"ok\":false,\"reason\":当前参数缺失");
            return;

        }
        BlogDao blogDao=new BlogDao();
        Blog blog=blogDao.selectOne(Integer.parseInt(param));
        if(blog==null)
        {
            resp.getWriter().write("\"ok\":false,\"reason\":当前博客不存在");
            return;
        }
        int userId=blog.getUserId();
        UserDao userDao=new UserDao();
        User user=userDao.selectOne(userId);
        if(user==null)
        {
            resp.getWriter().write("\"ok\":false,\"reason\":当前用户不存在");
            return;
        }
        user.setPassword("");
        resp.getWriter().write(objectMapper.writeValueAsString(user));


    }
}
