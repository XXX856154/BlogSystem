package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//封装user表的增删改查
public class UserDao
{
    public static void insert(User user)
    {
        Connection connection=null;
        String sql="insert into user values(?,?,?)";
        PreparedStatement preparedStatement=null;
        try
        {
            connection=DBUtils.getConnect();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,user.getUserId());
            preparedStatement.setString(2,user.getUserName());
            preparedStatement.setString(3,user.getPassword());
            preparedStatement.executeUpdate();

        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            DBUtils.close(connection,preparedStatement,null);
        }

    }
    public static void delete(User user)
    {
    Connection connection=null;
    String sql="delete from user where userId=?";
    PreparedStatement preparedStatement=null;
    try
    {
        connection=DBUtils.getConnect();
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,user.getUserId());
        preparedStatement.executeUpdate();

    }
    catch (SQLException sqlException)
    {
        sqlException.printStackTrace();
    }
    finally
    {
        DBUtils.close(connection,preparedStatement,null);
    }

}
    public static List<User> getUser()
    {
        Connection connection=null;
        String sql="select * from user";
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        List<User> list=new ArrayList<>();
        try
        {
            connection=DBUtils.getConnect();
            preparedStatement=connection.prepareStatement(sql);
           resultSet= preparedStatement.executeQuery();
           while (resultSet.next())
           {
               User user=new User();
               user.setUserId(resultSet.getInt("userId"));
               user.setUserName(resultSet.getString("userName"));
               user.setPassword(resultSet.getString("password"));
               list.add(user);
           }

        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            DBUtils.close(connection,preparedStatement,resultSet);
        }
        return list;

    }
    public static User selectOne(int userId)
{
    Connection connection=null;
    String sql="select * from user where userId=?";
    PreparedStatement preparedStatement=null;
    ResultSet resultSet=null;
    List<User> list=new ArrayList<>();
    try
    {
        connection=DBUtils.getConnect();
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,userId);
        resultSet= preparedStatement.executeQuery();
        if(resultSet.next())
        {
            User user=new User();
            user.setUserId(resultSet.getInt("userId"));
            user.setUserName(resultSet.getString("userName"));
            user.setPassword(resultSet.getString("password"));
           return user;
        }

    }
    catch (SQLException sqlException)
    {
        sqlException.printStackTrace();
    }
    finally
    {
        DBUtils.close(connection,preparedStatement,resultSet);
    }
  return null;

}

}
