package Model;

//用于封装博客的增删改查

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BlogDao
{
    //可以插入
    //获取所有博客内容
    //可以根据id删除
    //可以根据id查找博客
    public void insert(Blog blog)
    {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try
        {
            connection=DBUtils.getConnect();
            String sql="insert into blog values(null,?,?,?,now())";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,blog.getTitle());
            preparedStatement.setString(2,blog.getContent());
            preparedStatement.setInt(3,blog.getBlogId());
            preparedStatement.executeUpdate();


        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            DBUtils.close(connection,preparedStatement,null);
        }

    }
    public List<Blog> getBlog()
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Blog> list=new ArrayList<>();
        ResultSet resultSet=null;

        try
        {
            connection=DBUtils.getConnect();
            String sql="select * from blog order by postTime desc ";
            preparedStatement=connection.prepareStatement(sql);
           resultSet=preparedStatement.executeQuery();
            while(resultSet.next())
            {
                Blog blog=new Blog();
                blog.setBlogId(resultSet.getInt("blogId"));
                String s=resultSet.getString("content");
                if(s.length()>50)
                {
                    s=s.substring(0,50);
                }
                blog.setContent(s);
                blog.setTitle(resultSet.getString("title"));
                blog.setUserId(resultSet.getInt("userId"));
                blog.setPostTime(resultSet.getTimestamp("postTime"));
                list.add(blog);


            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            DBUtils.close(connection,preparedStatement,resultSet);
        }
    return list;
    }
    public Blog selectOne(int blogId)
    {
        Connection connection =null;
        PreparedStatement preparedStatement=null;
        String sql="select * from blog where blogId= ?";
        ResultSet resultSet=null;
        try
        {
            connection= DBUtils.getConnect();
            preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setInt(1,blogId);
             resultSet=preparedStatement.executeQuery();
            if(resultSet.next())
            {
                Blog blog=new Blog();
                blog.setBlogId(resultSet.getInt("blogId"));
                blog.setContent(resultSet.getString("content"));
                blog.setTitle(resultSet.getString("title"));
                blog.setUserId(resultSet.getInt("userId"));
                blog.setPostTime(resultSet.getTimestamp("postTime"));
                return blog;
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            DBUtils.close(connection,preparedStatement,resultSet);
        }
        return null;
    }
    public void delete(int blogId)
    {
        Connection connection =null;
        PreparedStatement preparedStatement=null;
        String sql="delete * from blog where blogId= ?";
        ResultSet resultSet=null;
        try
        {
            connection= DBUtils.getConnect();
            preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setInt(1,blogId);
            preparedStatement.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            DBUtils.close(connection,preparedStatement,null);
        }
    }
}
