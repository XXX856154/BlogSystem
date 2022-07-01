-- 建库操作
create database if not exists java_blog;

use java_blog;

drop table if exists blog;
--  创建博客表
create table blog(
    blogId int primary key auto_increment, -- 文章id
    title varchar(1024), -- 文章标题
    content mediumtext,
    userId int ,-- 文章作者
    postTime datetime -- 创建时间
 );
 -- 插入数据
 insert into blog values(null,'这是第一篇博客','从今天开始，我要认真写博客',1,now());
 insert into blog values(null,'这是第二篇博客','从昨天开始，我要认真写博客',1,now());
 insert into blog values(null,'这是第三篇博客','从前天开始，我要认真写博客',1,now());
drop table if exists user;
-- 创建用户表
create table user(
    userId int primary key auto_increment,
    userName varchar(128) unique,-- 用户号码一般不重复
    password varchar(128)
);
insert into user values(null,'zhangsan','1234');
insert into user values(null,'lisi','1234');