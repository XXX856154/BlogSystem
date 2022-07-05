package Controller;

import Model.Blog;
import Model.BlogDao;
import Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/submitBlog")
public class SubmitServlet extends HttpServlet
 {
     @Override
     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
     {
         req.setCharacterEncoding("utf8");
         HttpSession session=req.getSession(false);
         User user= (User) session.getAttribute("user");
         String title=req.getParameter("title");
         String content=req.getParameter("content");
         if(session==null||user==null||"".equals(title)||"".equals(content))
         {
             resp.setContentType("text/html;charset=utf8");
             resp.getWriter().write("参数错误");
             return;

         }

         Blog blog=new Blog();
         blog.setUserId(user.getUserId());
         blog.setTitle(title);
         blog.setContent(content);
         BlogDao blogDao=new BlogDao();
         blogDao.insert(blog);
         resp.sendRedirect("blog_list.html");

     }
 }
