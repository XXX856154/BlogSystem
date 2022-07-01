package Model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public class Blog
{

    private int blogId;
    private String title;
    private String content;
    private int userId;
    private java.sql.Timestamp postTime;

    public String getPostTime()
    {
        //设定日期格式
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(postTime);
    }

    public void setPostTime(Timestamp postTime)
    {
        this.postTime = postTime;
    }

    public int getBlogId()
    {
        return blogId;
    }

    public void setBlogId(int blogId)
    {
        this.blogId = blogId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public int getUserId()
    {
        return userId;
    }
    public void setUserId(int userId)
    {
        this.userId = userId;
    }


}

