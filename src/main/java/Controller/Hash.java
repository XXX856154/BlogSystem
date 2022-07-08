package Controller;

import Model.User;
import Model.UserDao;

import java.util.Arrays;
import java.util.List;

public class Hash
{
    private static UserNode hash[];
    private int num = 100;
    private int expectCount;

    public Hash()
    {
        hash=new UserNode[num];
        List<User> list= UserDao.getUser();
        for(int i=0;i<list.size();i++)
        {
            isContain(list.get(i));
        }
    }
    public boolean isContain(User user)
    {
        int key=getKey(user);//获取关键码
        if(key<hash.length)
        {
            if(hash[key]==null)
            {
                UserNode userNode=new UserNode();
                userNode.setUser(user);
                hash[key]=userNode;
                return false;
            }
            else if(key>hash.length)
            {
                hash=expandCapacity(hash);//扩容
            }
            else//发生哈希冲突
            {
                UserNode next=hash[key].getNext();
                while(next!=null)
                {
                    if(next.getUser().getUserName().equals(user.getUserName()))
                    {
                        next=next.getNext();
                    }
                    else
                    {
                        UserNode newNode=new UserNode();
                        newNode.setUser(user);
                        next.setNext(newNode);
                        return false;
                    }
                }
                return true;
            }
        }
        else
        {
            hash=expandCapacity(hash);//扩容
            if(isContain(user))
            {
                return true;
            }
        }
        return true;
    }
    public int getKey(User user)
    {
        int key=0;
        String userName=user.getUserName();
        for(int i=0;i<userName.length();i++)
        {
            key+=userName.charAt(i);
        }
        return key%(num*10);
    }
    public UserNode[] expandCapacity(UserNode[] oldHash)
    {
        num=num*2;
        hash=Arrays.copyOf(oldHash,num);
        return hash;

    }
}


class  UserNode
{
    private User user;
    private UserNode next;

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public UserNode getNext()
    {
        return next;
    }

    public void setNext(UserNode next)
    {
        this.next = next;
    }
}

