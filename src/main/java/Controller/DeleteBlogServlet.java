package Controller;

import Model.BlogDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteBlog")
public class DeleteBlogServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String blogId=req.getParameter("blogId");
        BlogDao blogDao=new BlogDao();
        blogDao.delete(Integer.parseInt(blogId));
        resp.sendRedirect("blog_list.html");
    }
}
