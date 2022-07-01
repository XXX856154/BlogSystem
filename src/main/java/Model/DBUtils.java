package Model;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils
{
    private static final String url="jdbc:mysql://127.0.0.1:3306/java_blog?characterEncoding=utf8&useSSL=false";
    private static final String userName="root";
    private  static final String password="526077798";
    private static volatile  DataSource dataSource=null;
   public static void createDataSource()
   {
       if (dataSource == null)
       {
           synchronized (DBUtils.class)
           {
               if (dataSource == null)
               {
                   dataSource = new MysqlDataSource();
                   ((MysqlDataSource) dataSource).setURL(url);
                   ((MysqlDataSource) dataSource).setUser(userName);
                   ((MysqlDataSource) dataSource).setPassword(password);
               }
           }
       }
   }

    public static Connection getConnect()
    {
        if(dataSource==null)
            createDataSource();
        try
        {
            return dataSource.getConnection();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public static void  save()
    {

    }
    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet)
    {
        if(connection!=null)
        {
            try
            {
                connection.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        if(preparedStatement!=null)
        {
            try
            {
                preparedStatement.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        if(resultSet!=null)
        {
            try
            {
                resultSet.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

    }
}

