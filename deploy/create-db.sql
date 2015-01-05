DROP DATABASE IF EXISTS `ball`;
CREATE DATABASE `ball` default charset=utf8 ;
use `ball`;


delete from mysql.user where User = 'ball_user';
grant select,update,delete,insert on `ball`.* to 'ball_user'@'%' identified by 'ball_pwd';
grant select,update,delete,insert on `ball`.* to 'ball_user'@'localhost' identified by 'ball_pwd';
flush privileges;