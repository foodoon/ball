ball
====
工程是基于spring,velocity,mybatis搭建的。maven管理
  工程目录结构如下：（java工程，eclipse for java 或者idea开发工具导入maven工程即可），本地测试的时候启动JettyServerStart.java即可。默认监听9990端口。http://localhost:9990/api/list.htm 即可访问。
  deploy
     build.sh  服务器上部署脚本
     create-db.sql创建数据库脚本
     ball-pro.sql.数据库表结构，数据初始化脚本
     ...（其他备份，更新文件可以忽略）
  docs
     系统相关文档
  src java代码目录
        main
        test
            java
                 JettyServerStart.java 项目本地测试启动main入口
  webapps  html模版,web.xml等描述文件存放目录
      assets  静态资源存放目录
      template velocity模版目录
      WEB-INF