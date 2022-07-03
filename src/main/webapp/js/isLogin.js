
isLogin();
//检测是否登录
function isLogin()
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
        else 
        {
            alert('登录成功!');
        }
    },
    error:function(){
        location.assign('blog_login.html');
        alert('当前未登录')
    }
})
}