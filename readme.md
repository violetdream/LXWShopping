# 商城项目(LXWShopping)

## 前端模块(lxwshopping-front)

* 新建vue项目，vue init webpack lxwshopping-front

``` vue
  Project name vue-start //项目名称
  Project description A Vue.js project // 项目描述
  Author  // 作者名称
  Vue build standalone // 推荐选前者

  Install vue-router   
// 是否安装vue-router路由组件，也可不安装使用第三方或简单的项目自己写

  Use ESLint to lint your code  
// 是否使用eslint管理代码，个人项目不推荐

  Set up unti tests 
// 是否使用karma来做单元测试

  Setup e2e tests with Nightwatch 
// 是否安装e2e测试

  Should we run 'npm install' for you after the project has been created 
// 选择使用npm或yarn进行安装模块
```

出现错误

``` javascript
npm ERR! Unexpected end of JSON input while parsing near '...@vue/component-compil'
```

这里因为package.json中声明的npm版本不符，需修改后运行 npm cache clean --force

再 npm install 解决

##  用户中心模块(user-service)

1 验证码服务KaptchaServiceImpl

每次访问生成的验证码通过Redis缓存60s，以UUID为Key，验证码Code为Value，每次验证请求时再从Redis根据UUID作为Key去取，获取到表示验证成功，否则为超过60s，返回验证失败。（用户量大时有Bug）





# 学习Spring Boot

``` java
@Configuration
@ConditionalOnClass({DataSource.class, EmbeddedDatabaseType.class})
@EnableConfigurationProperties({DataSourceProperties.class})
@Import({DataSourcePoolMetadataProvidersConfiguration.class, DataSourceInitializationConfiguration.class})
public class DataSourceAutoConfiguration
```

注解EnableConfigurationProperties通过DataSourceProperties读取到配置文件属性得到Bean，判断到依赖里有DataSource，EmbeddedDatabaseType的Class就会自动加载相应的数据源；因为在spring.factories中配置了

```java
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
```

自启动加载，只要依赖了mysql,durid,mybatis等相关包，就会自动配置装载。

# 本地安装MySQL

- 创建my.ini

  ```tex
  [mysqld]
  # set basedir to your installation path
  basedir=D:\\MySql\\mysql-8.0.17-winx64
  # set datadir to the location of your data directory
  datadir=D:\\MySql\\mysql-8.0.17-winx64\\data
  ```

- 创建data目录，mysqld --initialize-insecure

- 安装，mysqld --install-manual

- 启动服务 net start mysql

- 登录，mysql -u root

- 创建数据库,CREATE DATABASE gpmall CHARACTER SET utf8 COLLATE utf8_general_ci;切换数据库use gpmall;

- 修改密码mysqladmin -u root -p password  

  ![1567654130690](C:\Users\刘仙伟\AppData\Roaming\Typora\typora-user-images\1567654130690.png)

- 用新密码登录MySql，mysql -uroot -p123456

- 执行SQL文件，

  方法1: mysql -uroot -p123456 -Dgpmall<E:\\咕泡学院\\gpmail\\gpmall\\db_scrpit\\gpmall.sql

  方法2:连接上数据库内，执行source E:\\咕泡学院\\gpmail\\gpmall\\db_scrpit\\gpmall.sql;

- 修改MySQL字符集为UTF-8，修改my.ini文件

  ```ini
  [mysqld]
  # set basedir to your installation path
  basedir=D:\\MySql\\mysql-8.0.17-winx64
  # set datadir to the location of your data directory
  datadir=D:\\MySql\\mysql-8.0.17-winx64\\data
  character_set_server = utf8
  port = 3306
  [client]
  default_character_set = utf8
  
  [mysql]
  default_character_set = utf8
  ```

  修改完后，重启mysql, net stop mysql; net start mysql;

  

# 本地安装Redis

- 官网下载Redis，https://redis.io/download

- 解压文件，tar -zxvf redis-5.0.5.tar.gz

- 先查看是否安装c++的编译器； 执行 yum -y install gcc-c++

- 进入redis-5.0.5,执行make

- 修改redis.conf

  加上注释#bind 127.0.0.1

  redis默认不是以守护进程的方式运行，可以通过该配置项修改，使用yes启用守护进程，设置为no，daemonize no

  保护模式,protected-mode no

  添加认证，设置密码\# requirepass foobared   requirepass redis123456

- 启动 src/redis-server，可使用内置客户端进行互动，执行src/redis-cli



#  运维及部署

## 安装ZooKeeper

```shell
#拉取境像
docker pull zookeeper
```

```shell
#运行zookeeper，得到container
docker run --name docker-zookeeper -p 2181:2181 -p 2888:2888 -p 3888:3888 --restart always -d zookeeper

docker exec -it 2781fd823b82 /bin/bash
```

## 安装Redis

``` shell
#拉取境像
docker pull redis
#启动镜像
docker run -p 6379:6379 -v $PWD/data:/data -d redis:latest redis-server --appendonly yes --name redis-service

命令说明：
-p 6379:6379 : 将容器的6379端口映射到主机的6379端口
-v $PWD/data:/data : 将主机中当前目录下的data挂载到容器的/data
redis-server --appendonly yes : 在容器执行redis-server启动命令，并打开redis持久化配置
#连接redis的几种方式
docker exec -it 567806e4121e redis-cli
docker exec -it 567806e4121e redis-cli -h localhost -p 6379 
docker exec -it 567806e4121e redis-cli -h 127.0.0.1 -p 6379 
docker exec -it 567806e4121e redis-cli -h 172.17.0.3 -p 6379 

```

## 安装MySQL

``` shell
#拉取境像
docker pull mysql
#创建my.cnf文件置于/lxw/mysql/data目录下
[client]
default-character-set=utf8
 
[mysql]
default-character-set=utf8
 
[mysqld]
init_connect='SET collation_connection = utf8_unicode_ci'
init_connect='SET NAMES utf8'
character-set-server=utf8
collation-server=utf8_unicode_ci
skip-character-set-client-handshake
pid-file        = /var/run/mysqld/mysqld.pid
socket          = /var/run/mysqld/mysqld.sock
datadir         = /var/lib/mysql
secure-file-priv= NULL
# Disabling symbolic-links is recommended to prevent assorted security risks
symbolic-links=0

# Custom config should go here
!includedir /etc/mysql/conf.d/
#创建MySQL容器
docker run -di --name shopping_mysql -v /lxw/mysql/data/my.cnf:/etc/mysql/my.cnf -v /lxw/mysql/data:/data  -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 mysql

-p 代表端口映射，格式为  宿主机映射端口:容器运行端口
-e 代表添加环境变量  MYSQL_ROOT_PASSWORD是root用户的登陆密码
-v /lxw/mysql/data:/data : 将主机中当前目录下的data挂载到容器的/data
#进入MySQL容器,登陆MySQL
docker exec -it shopping_mysql /bin/bash
#登录MySQL
mysql -u root -p
```

``` tiki wiki
Navicat 远程连接docker容器中的mysql 报错1251 - Client does not support authentication protocol 解决办法
1、进行授权远程连接(注意mysql 8.0跟之前的授权方式不同)
GRANT ALL ON *.* TO 'root'@'%';
刷新权限
flush privileges;
2、更改加密规则
ALTER USER 'root'@'localhost' IDENTIFIED BY 'password' PASSWORD EXPIRE NEVER;
3、更新root用户密码
ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY '123456';
##这步很重要，不执行，退出后本地无法登录了
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '123456';
4、刷新权限
flush privileges;
```

``` shell
1、将文件shopping_mysql.sql文件上传到宿主机/data目录下
2、创建数据库
CREATE DATABASE shopping_mysql CHARACTER SET utf8 COLLATE utf8_general_ci;
切换数据库use shopping_mysql;
#查看MySQL字符集
show variables like'character%'; 
mysql> show variables like'character%';
+--------------------------+--------------------------------+
| Variable_name            | Value                          |
+--------------------------+--------------------------------+
| character_set_client     | utf8                           |
| character_set_connection | utf8                           |
| character_set_database   | utf8                           |
| character_set_filesystem | binary                         |
| character_set_results    | utf8                           |
| character_set_server     | utf8                           |
| character_set_system     | utf8                           |
| character_sets_dir       | /usr/share/mysql-8.0/charsets/ |
+--------------------------+--------------------------------+
8 rows in set (0.01 sec)
3、执行SQL文件，
方法1: mysql -uroot -p123456 -Dshopping_mysql</data/shopping_mysql.sql;
方法2:连接上数据库内，执行source /data/shopping_mysql.sql;

```

## 安装Tomcat

``` shell
/** 方式一 **/
#拉取境像
docker pull tomcat

#以挂载的方式启动
docker run -d --name lxwshopping-front -v /lxw/lxwshopping-front:/usr/local/tomcat/webapps/ROOT -p 8080:8080 -p 80:80 -p 443:443 tomcat

# 进去Tomcat查看文件
docker exec -it lxwshopping-front /bin/bash

/** 方式二 **/
#下载
cd /lxw/tomcat
wget https://mirrors.cnnic.cn/apache/tomcat/tomcat-9/v9.0.29/bin/apache-tomcat-9.0.29.tar.gz
#解压
tar -zxvf apache-tomcat-9.0.29.tar.gz
#修改相关配置

```

## 安装Nginx

``` shell
cd /lxw/nginx
tar -zxvf nginx-1.17.6.tar.gz
mkdir data
cd nginx-1.17.6
#安装第三方依赖库
yum install pcre-devel
yum install zlib-devel
#安装
./configure --prefix=/lxw/nginx/data
make&&make install
#官方解释
--prefix=path
defines a directory that will keep server files. This same directory will also be used for all relative paths set by configure (except for paths to libraries sources) and in the nginx.conf configuration file. It is set to the /usr/local/nginx directory by default.
#启动和停止
cd ../data
启动  sbin/nginx
停止  ./nginx -s stop
重新加载 ./nginx -s reload
#在/data/conf/nginx.conf上添加 
include /lxw/lxwshopping-front/*.conf;
#添加配置文件/lxw/lxwshopping-front/nginx.conf
server{
	listen 80;
	server_name localhost;
	location / {
		root   /lxw/lxwshopping-front;
		index  index.html index.htm;
	}
	location /user{
		proxy_pass http://172.17.0.6:8081;
	}
	location /shopping{
		proxy_pass http://172.17.0.8:8082;
	}
	location /cashier{
		proxy_pass http://120.79.28.199:8083;
	}
}

```





## 部署lxwshopping-front

``` shell
#在router/index.js加上
export default new Router({
  base: "/shopping/",//项目名称 访问路由页面都需要加上这个，访问根路径为http://ip:port/shopping/
  mode: "history",//消去#
#在config/index.js中找到build对象修改
assetsPublicPath: '/shopping/',
productionSourceMap: false,
#在lxwshopping-front目录生成安装包
npm run build
#在目录将得到安装包D:\Project_WorkSpace\LXWShopping\lxwshopping-front\dist
#上传Dist目录下的文件到/lxw/lxwshopping-front目录下
#发现静态资源访问路径不对，配置tomcat/server.xml
<Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs"
               prefix="localhost_access_log" suffix=".txt"
               pattern="%h %l %u %t &quot;%r&quot; %s %b" />
               <Context path="" docBase="shopping" reloadable="false" />
#shopping目录下增加WEB-INF/web.xml文件，消除#
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://java.sun.com/xml/ns/javaee"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
                      http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
  version="3.0"
  metadata-complete="true">

  <display-name>shopping</display-name>
  <description>
     shopping
  </description>
  <error-page>  
   <error-code>404</error-code>  
   <location>/index.html</location>  
</error-page>  
</web-app>
#访问测试
http://120.79.28.199:8080/shopping/index.html
```



## 部署user-service

``` shell
#maven打包得到user-provider-0.0.1-SNAPSHOT.jar，并上传至/lxw/user-service目录 
#在目录/lxw/user-service目录下创建Dockerfile文件
FROM openjdk:8
MAINTAINER violetdream
LABEL name="user-service-image" version="1.0" author="violetdream"
COPY user-provider-0.0.1-SNAPSHOT.jar user-service-image.jar
CMD ["java","-jar","user-service-image.jar"]

#根据dockerfile创建境像
docker build -t user-service-image ./
#查看image是否构建成功
docker images
[root@m user-service]# docker images
REPOSITORY                           TAG                 IMAGE ID            CREATED             SIZE
user-service-image                   latest              759abc3aa082        40 seconds ago      488MB
openjdk                              8                   09df0563bdfc        4 days ago          488MB
redis                                latest              dcf9ec9265e0        4 days ago          98.2MB
mysql                                latest              d435eee2caa5        5 days ago          456MB
zookeeper                            latest              c91a7d13d4d9        5 days ago          224MB

#基于image创建container
docker run -d --name user-service user-service-image

#查看启动日志
docker logs user-service
#如报错如下，表示打包没有打相关依赖的包到路径下
no main manifest attribute, in user-service-image.jar
#需要设置goal-repackage属性为true，否则打包后文件依赖文件没有一起打包，然后镜像内没有可以运行的程序文件 

#将境像推送到我的阿里云Hub上进行Registry
docker login --username=violetdream@aliyun.com registry.cn-hangzhou.aliyuncs.com
docker tag user-service-image registry.cn-hangzhou.aliyuncs.com/violetdream/lxwshopping:2019
docker push registry.cn-hangzhou.aliyuncs.com/violetdream/lxwshopping:2019
#可顺便进入zookeeper进行查看，是否将服务注册上去了
docker exec -it docker-zookeeper /bin/bash
zkCli.sh

```

## 部署lxwshopping-user

``` shell
#maven打包得到lxwshopping-user-0.0.1-SNAPSHOT.jar，并上传至/lxw/lxwshopping-user目录 
#在目录/lxw/lxwshopping-user目录下创建Dockerfile文件
FROM openjdk:8
MAINTAINER violetdream
LABEL name="lxwshopping-user-image" version="1.0" author="violetdream"
COPY lxwshopping-user-0.0.1-SNAPSHOT.jar lxwshopping-user-image.jar
CMD ["java","-jar","lxwshopping-user-image.jar"]

#根据dockerfile创建境像
docker build -t lxwshopping-user-image ./

#基于image创建container
docker run -d --name lxwshopping-user -p 8081:8081 lxwshopping-user-image

#将境像推送到我的阿里云Hub上进行Registry
docker login --username=violetdream@aliyun.com registry.cn-hangzhou.aliyuncs.com
docker tag lxwshopping-user-image registry.cn-hangzhou.aliyuncs.com/violetdream/lxwshopping-user:2019
docker push registry.cn-hangzhou.aliyuncs.com/violetdream/lxwshopping-user:2019
```

## 部署shopping-service

``` shell
#maven打包得到shopping-provider-0.0.1-SNAPSHOT.jar，并上传至/lxw/shopping-service目录 
#在目录/lxw/shopping-service目录下创建Dockerfile文件
FROM openjdk:8
MAINTAINER violetdream
LABEL name="shopping-service-image" version="1.0" author="violetdream"
COPY shopping-provider-0.0.1-SNAPSHOT.jar shopping-service-image.jar
CMD ["java","-jar","shopping-service-image.jar"]

#根据dockerfile创建境像
docker build -t shopping-service-image ./

#基于image创建container
docker run -d --name shopping-service shopping-service-image

#将境像推送到我的阿里云Hub上进行Registry
docker login --username=violetdream@aliyun.com registry.cn-hangzhou.aliyuncs.com
docker tag shopping-service-image registry.cn-hangzhou.aliyuncs.com/violetdream/shopping-service:2019
docker push registry.cn-hangzhou.aliyuncs.com/violetdream/shopping-service:2019
```

## 部署lxwshopping-shopping

``` shell
#maven打包得到lxwshopping-shopping-0.0.1-SNAPSHOT.jar，并上传至/lxw/lxwshopping-shopping目录 
#在目录/lxw/lxwshopping-shopping目录下创建Dockerfile文件
FROM openjdk:8
MAINTAINER violetdream
LABEL name="lxwshopping-shopping-image" version="1.0" author="violetdream"
COPY lxwshopping-shopping-0.0.1-SNAPSHOT.jar lxwshopping-shopping-image.jar
CMD ["java","-jar","lxwshopping-shopping-image.jar"]

#根据dockerfile创建境像
docker build -t lxwshopping-shopping-image ./

#基于image创建container
docker run -d --name lxwshopping-shopping -p 8082:8082 lxwshopping-shopping-image

#将境像推送到我的阿里云Hub上进行Registry
docker login --username=violetdream@aliyun.com registry.cn-hangzhou.aliyuncs.com
docker tag lxwshopping-shopping-image registry.cn-hangzhou.aliyuncs.com/violetdream/lxwshopping-shopping:2019
docker push registry.cn-hangzhou.aliyuncs.com/violetdream/lxwshopping-shopping:2019
```

