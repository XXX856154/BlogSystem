package Controller;

import Model.Blog;
import Model.BlogDao;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/blog")
public class BlogServlet extends HttpServlet
{
    private ObjectMapper objectMapper=new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String parameter=req.getParameter("blogId");
        BlogDao blogDao=new BlogDao();
        if (parameter == null)
        {

            List<Blog>  list =blogDao.getBlog();
            String s=objectMapper.writeValueAsString(list);
            resp.setContentType("application/json;charset=utf8");
            resp.getWriter().write(s);
        }else
        {
            Blog blog=blogDao.selectOne(Integer.parseInt(parameter));
            String json=objectMapper.writeValueAsString(blog);
            resp.setContentType("application/json;charset=utf8");
            resp.getWriter().write(json);
        }

    }
}
