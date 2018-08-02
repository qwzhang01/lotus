# LOTUS音频播放系统 介绍

### 前言
lotus项目是针对音乐播放APP的后台管理系统及API系统。基于Spring+SpringMVC+Mybatis框架吗，使用dubbo搭建的分布式敏捷开发系统架构。功能包含后台管理系统：文章管理、专辑管理、曲目管理、文件管理；API系统；H5分享页面。项目是自己工作之余学习搭建的，希望各路大神多多指教。
    
### 介绍
我是在阅读《Spring4实战》，学习开源项目Jpress源码过程中开发的本项目。自己比较喜欢Jpress，所以项目前端使用了Jpress全套样式和Js插件机，在此对 [Jpress](https://gitee.com/fuhai/jpress "Jpress Git地址") 表示感谢。我自己工作中模板引擎一直使用beetl，项目中也集成的是这个模板。Dubbo正在学习过程中，代码中可能会有不合理的地方，以后对Dubbo的认识加深了的话，再重构这些地方。
    
### 组织结构

``` 
lotus
├── lotus-common -- SSM框架公共模块
├── lotus-web -- 后台管理模板
├── lotus-api -- API接口系统
├── lotus-dao -- Mybatis
├── lotus-service -- 业务层
├── lotus_rpc_api -- RPC对外模块
├── lotus_rpc_server -- RPC服务模块
```

### 后端技术:
技术 | 名称 | 官网
----|------|----
Spring Framework | 容器  | [http://projects.spring.io/spring-framework/](http://projects.spring.io/spring-framework/)
SpringMVC | MVC框架  | [http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc](http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc)
Apache Shiro | 安全框架  | [http://shiro.apache.org/](http://shiro.apache.org/)
MyBatis | ORM框架  | [http://www.mybatis.org/mybatis-3/zh/index.html](http://www.mybatis.org/mybatis-3/zh/index.html)
MyBatis Generator | 代码生成  | [http://www.mybatis.org/generator/index.html](http://www.mybatis.org/generator/index.html)
PageHelper | MyBatis物理分页插件  | [http://git.oschina.net/free/Mybatis_PageHelper](http://git.oschina.net/free/Mybatis_PageHelper)
Druid | 数据库连接池  | [https://github.com/alibaba/druid](https://github.com/alibaba/druid)
Beetl | 模板引擎  | [http://ibeetl.com/](http://ibeetl.com/)
Quartz | 作业调度框架  | [http://www.quartz-scheduler.org/](http://www.quartz-scheduler.org/)
Ehcache | 进程内缓存框架  | [http://www.ehcache.org/](http://www.ehcache.org/)
Log4J | 日志组件  | [http://logging.apache.org/log4j/1.2/](http://logging.apache.org/log4j/1.2/)
Swagger2 | 接口测试框架  | [http://swagger.io/](http://swagger.io/)
Qiniu | 云存储  |  [http://www.qiniu.com/](http://www.qiniu.com/)
Maven | 项目构建管理  | [http://maven.apache.org/](http://maven.apache.org/)
ZooKeeper | 分布式协调服务  | [http://zookeeper.apache.org/](http://zookeeper.apache.org/)
Dubbo | 分布式服务框架  | [http://dubbo.io/](http://dubbo.io/)
Redis | 分布式缓存数据库  | [https://redis.io/](https://redis.io/)

### 前端技术:
技术 | 名称 | 官网
----|------|----
jQuery | 函式库  | [http://jquery.com/](http://jquery.com/)
Bootstrap | 前端框架  | [http://getbootstrap.com/](http://getbootstrap.com/)
ALT AdminLTE| 前端框架  | [https://adminlte.io/](https://adminlte.io/)
Bootstrap-table | Bootstrap数据表格  | [http://bootstrap-table.wenzhixin.net.cn/](http://bootstrap-table.wenzhixin.net.cn/)
Fine-uploader | 上传插件  | [https://fineuploader.com/](https://fineuploader.com/)
Alertifyjs | 提示插件  | [http://alertifyjs.com/](http://alertifyjs.com/)
Font-awesome | 字体图标  | [http://fontawesome.io/](http://fontawesome.io/)

### 开发工具:
- MySql: 数据库
- Tomcat: 应用服务器
- Git: 版本管理
- Nginx: 反向代理服务器
- IntelliJ IDEA: 开发IDE
- PowerDesigner: 建模工具
- Navicat for MySQL: 数据库客户端

### 开发环境
- Jdk8+
- Mysql5.5+
- Redis
- Zookeeper

### 开发指南
1. 本机安装Jdk8、Mysql、Redis、Zookeeper并启动相关服务，使用默认配置默认端口即可
2. 克隆源代码到本地并打开，推荐使用IntelliJ IDEA，本地编译并安装到本地maven仓库
3. 将根目录下的lotus_cms.sql导入数据库
4. 启动lotus_rpc_service项目中的RpcServiveApiStarter，注册服务至Zookeeper并启动相关服务
5. 启动lotus_api，即启动API项目
6. 启动lotus_web，即启动后台管理项目
7. 注册七牛账号，在数据库中配置七牛token

### 注意
1. Dubbo还在学习中，Dubbo打包貌似有点问题
2. 除了数据库连接 ，配置信息都在数据库里面