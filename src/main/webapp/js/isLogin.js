
//检测是否登录
function isLogin(passgeName)
{
$.ajax({
    type:'get',
    url:'login',
    success:function(body) {
    
       
        if(body.userId==0)
        {
            alert('当前未登录');
            //跳转页面
            location.assign('blog_login.html');
        }
        if(passgeName!='blog_detail.html')
        {
        
            //说明当前登录成功
            //更改用户信息
            let userName=document.querySelector('.card>h3');
            userName.innerHTML=body.userName;


        }
    else 
    {
            getAuthorInfo(body.userId);
    }
    },
    error:function(){
        location.assign('blog_login.html');
        alert('当前未登录')
    }
})
}
//查找作者id
function getAuthorInfo(userId)
{
    
    let AuthorId;
    $.ajax({
        type:'get',
        url:'author'+location.search,
        success:function(body)
        {
            let userName=document.querySelector('.card>h3');
            userName.innerHTML=body.userName;
            AuthorId=body.userId;
            if(AuthorId==userId)
            {
                let nav=document.querySelector('.nav');
                let a=document.createElement("a");
                a.href="deleteBlog"+location.search;
                a.innerHTML='删除博客';
                nav.appendChild(a);
            }
            
        }
    }) 

   
}

function logOut()
{
    let button=document.querySelector('#logout');
    button.onclick=function(){
        $.ajax({
            type:'get',
            url:'logout',
            success:function(body)
            {
                location.assign('blog_login.html')
            }
        })
        
    }
      


    

    
}