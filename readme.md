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

## 准备环境

* 一台2G单核服务器，1M宽带，装有CentOS7.6操作系统
* 对操作系统更新并安装依赖

``` shell
yum -y update
yum install -y conntrack ipvsadm ipset jq sysstat curl iptables libseccomp
#对系统相关参数设置
01 `关闭防火墙`
	systemctl stop firewalld && systemctl disable firewalld
02 `关闭selinux`
	setenforce 0
	sed -i 's/^SELINUX=enforcing$/SELINUX=permissive/' /etc/selinux/config
03 `关闭swap`
	swapoff -a
	sed -i '/swap/s/^\(.*\)$/#\1/g' /etc/fstab
04 `配置iptables的ACCEPT规则`
	iptables -F && iptables -X && iptables \
    -F -t nat && iptables -X -t nat && iptables -P FORWARD ACCEPT
05 `设置系统参数`
# ====================================================================================
cat <<EOF >  /etc/sysctl.d/k8s.conf
net.bridge.bridge-nf-call-ip6tables = 1
net.bridge.bridge-nf-call-iptables = 1
EOF
sysctl --system
# ======================================================================================
```



### 安装docker18.09.0

``` shell
01 `进入虚拟机`
    vagrant ssh [nodeName]
02 `卸载之前安装的docker`
    sudo yum remove docker docker latest docker-latest-logrotate \
    docker-logrotate docker-engine docker-client docker-client-latest docker-common
03 `安装必要依赖`
    sudo yum install -y yum-utils device-mapper-persistent-data lvm2
04 `添加软件源信息`
    sudo yum-config-manager \
    --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
    yum list | grep docker-ce
05 `更新yum缓存`
    sudo yum makecache fast
06 `安装docker`
    sudo yum install -y docker-ce-18.09.0 docker-ce-cli-18.09.0 containerd.io 18.09.0
07 `启动docker并设置开机启动`
    sudo systemctl start docker && sudo systemctl enable docker
08 `测试docker安装是否成功`
    sudo docker run hello-world
`默认安装在目录/var/lib/docker`
#限制每个容器可以占用的磁盘空间
sudo dockerd --storage-driver overlay2 --storage-opt overlay2.size=1G
```

### 安装openjdk或Oracle JDK

``` shell
yum list | grep openjdk
yum install java-11-openjdk.x86_64安装Git
`默认安装在目录/usr/lib/jvm/java-11-openjdk-11.0.5.10-0.el7_7.x86_64`
#发现还是需要用JDK
下载并上传jdk-11.0.5_linux-x64_bin.tar.gz至目录/lxw/java/
配置/etc/profile
export MAVEN_HOME=/lxw/maven/apache-maven-3.6.3
export JAVA_HOME=/lxw/java/jdk-11.0.5
export PATH=$JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH
```

### 安装Git

``` shell
yum list | grep git
yum install git
`默认安装在目录/usr/libexec/git-core`
#温馨提示：在Windows下设置下
git config --global core.autocrlf false
```

### 安装Maven

``` shell
cd /lxw/maven
wget http://mirror.bit.edu.cn/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz
tar -zxvf apache-maven-3.6.3-bin.tar.gz
mkdir repo
#配置环境变量,修改maven本地仓库存放路径及mirror境像地址
vi /lxw/maven/apache-maven-3.6.3/conf/settings.xml
通过修改profile文件:
vim /etc/profile
/export PATH //找到设置PATH的行，添加
export MAVEN_HOME=/lxw/maven/apache-maven-3.6.3
export PATH=$JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH
生效方法：系统重启
有效期限：永久有效
用户局限：对所有用户
shutdown -r now 或  source /etc/profile 生效
mvn -v
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: /lxw/maven/apache-maven-3.6.3
Java version: 11.0.5, vendor: Oracle Corporation, runtime: /usr/lib/jvm/java-11-openjdk-11.0.5.10-0.el7_7.x86_64
Default locale: en_US, platform encoding: UTF-8
OS name: "linux", version: "3.10.0-1062.9.1.el7.x86_64", arch: "amd64", family: "unix"

#使和软链接，把mvn命令建立到/usr/bin目录下
`Usage: ln [OPTION]... [-T] TARGET LINK_NAME   (1st form)\
  or:  ln [OPTION]... TARGET                  (2nd form)\
  or:  ln [OPTION]... TARGET... DIRECTORY     (3rd form)\
  or:  ln [OPTION]... -t DIRECTORY TARGET...  (4th form)\
In the 1st form, create a link to TARGET with the name LINK_NAME.\
In the 2nd form, create a link to TARGET in the current directory.\
In the 3rd and 4th forms, create links to each TARGET in DIRECTORY.

cd /usr/bin
ln –s /lxw/maven/apache-maven-3.6.3/bin/mvn
```

## 安装NPM

``` shell
#yum 安装
yum install npm
#验证安装
npm -version
3.10.10

```



## 安装Jenkins实现自动化布署

``` shell
# 为了能够使用jenkins库，首先需要导入jenkins库的 key 
`To use this repository, run the following command:
 If you've previously imported the key from Jenkins, the "rpm --import" will fail because you already have a key. Please ignore that and move on.
`
sudo wget -O /etc/yum.repos.d/jenkins.repo https://pkg.jenkins.io/redhat/jenkins.repo
sudo rpm --import https://pkg.jenkins.io/redhat/jenkins.io.key
# 安装
yum install jenkins
#删除软件：yum remove jenkins-x.x.x.rpm或者yum erase jenkins-x.x.x.rpm
#升级软件：yum upgrade jenkins或者yum update jenkins
#查询信息：yum info jenkins
#Start/Stop
sudo service jenkins start/stop/restart
sudo chkconfig jenkins on

```

### What does this package do?

- Jenkins will be launched as a daemon on startup. See `/etc/init.d/jenkins` for more details.
- The '`jenkins`' user is created to run this service. If you change this to a different user via the config file, you must change the owner of /var/log/jenkins, /var/lib/jenkins, and /var/cache/jenkins.
- Log file will be placed in `/var/log/jenkins/jenkins.log`. Check this file if you are troubleshooting Jenkins.
- `/etc/sysconfig/jenkins` will capture configuration parameters for the launch.
- By default, Jenkins listen on port 8080. Access this port with your browser to start configuration.  Note that the built-in firewall may have to be opened to access this port from other computers.  (See http://www.cyberciti.biz/faq/disable-linux-firewall-under-centos-rhel-fedora/ for instructions how to disable the firewall permanently)
- A Jenkins RPM repository is added in `/etc/yum.repos.d/jenkins.repo`

### Configure the firewall

```shell
firewall-cmd --permanent --new-service=jenkins
firewall-cmd --permanent --service=jenkins --set-short="Jenkins Service Ports"
firewall-cmd --permanent --service=jenkins --set-description="Jenkins service firewalld port exceptions"
firewall-cmd --permanent --service=jenkins --add-port=8080/tcp
firewall-cmd --permanent --add-service=jenkins
firewall-cmd --zone=public --add-service=http --permanent
firewall-cmd --reload

firewall-cmd --list-all
```

### 环境配置

``` shell
`01 登录安装`
http://120.79.28.199:8080
`02 安装依赖后，输入用户名信息`
用户名:jenkins
密码：123456
全名：violetdream
`03 插件管理中，选择Maven Integration插件进行直接安装`

`把jenkins用户加入到dockerroot用户组`
vim /etc/group
#group_name:passwd:GID:user_list
dockerroot:x:666:jenkins
source /etc/group
`在/etc/group 中的每条记录分四个字段：\
第一字段：用户组名称；\
第二字段：用户组密码；\
第三字段：GID\
第四字段：用户列表，每个用户之间用,号分割；本字段可以为空；如果字段为空表示用户组为GID的用户名；

gpasswd -a jenkins dockerroot #将登陆用户加入到docker用户组中
gpasswd -a jenkins root #将登陆用户加入到docker用户组中
newgrp dockerroot #更新用户组

#在脚本中使用sudo命令出现错误时sudo: no tty present and no askpass program specified
vi /etc/sudoers
jenkins ALL=(ALL) NOPASSWD: ALL


```

![image-20200107112415248](C:\Users\刘仙伟\AppData\Roaming\Typora\typora-user-images\image-20200107112415248.png)



## 提交代码-Git版本管理

``` shell
在当前工程目录创建git版本控制，D:\Project_WorkSpace\LXWShopping目录下，跑命令(在liuxianwei_dev分支上进行开发并提交commit，在master分支上进行合并merge，并push到远程github仓库)

git init

git add .

git commit -m "shopping"

git remote add origin [github地址](https://github.com/violetdream/LXWShopping.git)

git push -u origin master

#重点功能 git sparseCheckout 下载github上的子目录
git config core.sparseCheckout true
#加入你要导出的子目录
echo resources/templates/trydone>> .git/info/sparse-checkout
#开始pull下来，与正常使用git一样
git pull origin master
```

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
docker run -d --name redis -p 6379:6379 -v /lxw/redis/data:/data -v /lxw/redis/redis.conf:/etc/redis/redis.conf  redis:latest

命令说明：
-p 6379:6379 : 将容器的6379端口映射到主机的6379端口
-v /lxw/redis/data : 将主机中当前目录下的data挂载到容器的/data
redis-server --appendonly yes : 在容器执行redis-server启动命令，并打开redis持久化配置
#连接redis的几种方式
docker exec -it f20faa4b722f redis-cli
docker exec -it f20faa4b722f redis-cli -h localhost -p 6379 
docker exec -it f20faa4b722f redis-cli -h 127.0.0.1 -p 6379 
docker exec -it f20faa4b722f redis-cli -h 172.17.0.4 -p 6379 

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

## 安装RabbitMQ

``` shell
#拉取境像文件 [rabbitmq image](https://registry.hub.docker.com/_/rabbitmq/)
docker pull rabbitmq:3.8.2-management
#启动rabbitmq
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 -v /lxw/rabbitmq/data:/var/lib/rabbitmq --hostname myRabbit -e RABBITMQ_DEFAULT_USER=rabbitmq -e RABBITMQ_DEFAULT_PASS=123456 rabbitmq:3.8.2-management
#说明：
-d 后台运行容器；
--name 指定容器名；
-p 指定服务运行的端口（5672：应用访问端口；15672：控制台Web端口号）；
-v 映射目录或文件；
--hostname  主机名（RabbitMQ的一个重要注意事项是它根据所谓的 “节点名称” 存储数据，默认为主机名）；
-e 指定环境变量；（RABBITMQ_DEFAULT_VHOST：默认虚拟机名；RABBITMQ_DEFAULT_USER：默认的用户名；RABBITMQ_DEFAULT_PASS：默认用户名的密码）
#打开web管理端查看
可以使用浏览器打开web管理端：http://120.79.28.199:15672

#通过命令对rabbitmq进行读写以及管理队列的权限，完成对rabbitmq的授权,然后启动项目就正常了
docker exec -it rabbitmq /bin/bash
rabbitmqctl set_permissions -p / rabbitmq .* .* .*

#添加queue及Exchange如下图所示
```

``` java
public static final String DELAY_EXCHANGE="delay_exchange";
public static  final String DELAY_QUEUE="delay_queue";
//报错
[ERROR] 2019-12-26 17:14:48,578 --AMQP Connection 120.79.28.199:5672-- [org.springframework.amqp.rabbit.connection.CachingConnectionFactory] Channel shutdown: connection error; protocol method: #method<connection.close>(reply-code=503, reply-text=COMMAND_INVALID - unknown exchange type 'x-delayed-message', class-id=40, method-id=10) 
需要安装rabbitmq_delayed_message_exchange插件
rabbitmq-plugins list
rabbitmq-plugins enable rabbitmq_delayed_message_exchange
```

```bash
1. 进入docker容器内 docker exec  -t rabbit  bash
2. rabbitmq-plugins list 命令查看已安装插件
3. 在插件网址找到延迟插件的下载地址 http://www.rabbitmq.com/community-plugins.html 
4. exit 退出容器到宿主机中,下载插件: wget https://github.com/rabbitmq/rabbitmq-delayed-message-exchange/releases/download/v3.8.0/rabbitmq_delayed_message_exchange-3.8.0.ez
5. 解压 unzip v3.8.0.zip -d . 
6. 拷贝至docker容器内: docker cp rabbitmq_delayed_message_exchange-3.8.0.ez rabbitmq:/plugins
7. 再次进入docker容器内: 进入docker容器内 docker exec  -t rabbitmq  bash
8. 执行命令让插件生效: 启动延时插件：rabbitmq-plugins enable rabbitmq_delayed_message_exchange
```

![image-20191226165049910](C:\Users\刘仙伟\AppData\Roaming\Typora\typora-user-images\image-20191226165049910.png)

![image-20191226165241329](C:\Users\刘仙伟\AppData\Roaming\Typora\typora-user-images\image-20191226165241329.png)



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



## 利用Jenkins搭建自动化布署

Jenkins上配置` 全局工具配置 `

![image-20200103151035598](C:\Users\刘仙伟\AppData\Roaming\Typora\typora-user-images\image-20200103151035598.png)

![image-20200103151046976](C:\Users\刘仙伟\AppData\Roaming\Typora\typora-user-images\image-20200103151046976.png)

![image-20200103151101465](C:\Users\刘仙伟\AppData\Roaming\Typora\typora-user-images\image-20200103151101465.png)

 78e0fc7568408daf7ad7864d358f586dc1c7ab92`

进入`GitHub上指定的项目 --> setting --> Webhooks --> add webhook `

![image-20200108160429896](C:\Users\刘仙伟\AppData\Roaming\Typora\typora-user-images\image-20200108160429896.png)

在Jenkins上配置GitHub服务器

![image-20200103154303789](C:\Users\刘仙伟\AppData\Roaming\Typora\typora-user-images\image-20200103154303789.png)

![image-20200108160219734](C:\Users\刘仙伟\AppData\Roaming\Typora\typora-user-images\image-20200108160219734.png)



![image-20200103154041901](C:\Users\刘仙伟\AppData\Roaming\Typora\typora-user-images\image-20200103154041901.png)

创建freestyle任务

![image-20200103155445844](C:\Users\刘仙伟\AppData\Roaming\Typora\typora-user-images\image-20200103155445844.png)



![image-20200103162452893](C:\Users\刘仙伟\AppData\Roaming\Typora\typora-user-images\image-20200103162452893.png)



![image-20200103160338394](C:\Users\刘仙伟\AppData\Roaming\Typora\typora-user-images\image-20200103160338394.png)



![image-20200103160548179](C:\Users\刘仙伟\AppData\Roaming\Typora\typora-user-images\image-20200103160548179.png)



![image-20200103160645502](C:\Users\刘仙伟\AppData\Roaming\Typora\typora-user-images\image-20200103160645502.png)

至关重要的一步

![image-20200107100245086](C:\Users\刘仙伟\AppData\Roaming\Typora\typora-user-images\image-20200107100245086.png)



## User-service构建脚本

``` shell
#!/bin/sh
cd /var/lib/jenkins/workspace/user-service/lxwshopping-parent
mvn clean
mvn install
echo 'parent install OK '
cd /var/lib/jenkins/workspace/user-service/lxwshopping-commons
mvn clean
mvn install
echo 'commons install OK '
cd /var/lib/jenkins/workspace/user-service/user-service
mvn clean
mvn install
echo 'user services OK '
echo 'ALL INSTALL OK'
cat <<EOF >  ./user-provider/target/Dockerfile
FROM openjdk:8
MAINTAINER violetdream
LABEL name="user-service-image" version="1.0" author="violetdream"
COPY user-provider-*.jar user-service-image.jar
CMD ["java","-jar","user-service-image.jar"]
EOF
sudo docker stop user-service
sudo docker rm user-service
sudo docker rmi user-service-image
sudo docker build -t user-service-image ./user-provider/target/
echo 'user-service Image Create OK'
sudo docker run -d --name user-service user-service-image
echo 'user-service Container Create OK'
#将境像推送到我的阿里云Hub上进行Registry
sudo docker tag user-service-image registry.cn-hangzhou.aliyuncs.com/lxwshopping/user-service:2019
sudo docker push registry.cn-hangzhou.aliyuncs.com/lxwshopping/user-service:2019
sudo docker rmi registry.cn-hangzhou.aliyuncs.com/lxwshopping/user-service:2019
```

## lxwshopping-front构建脚本

``` shell
#!/bin/sh
cd /var/lib/jenkins/workspace/lxwshopping-front/lxwshopping-front
#注意至少要保证free内存1G可用才能成功，在这里我提前安装好
#sudo npm install
echo 'lxwshopping-front install OK '
rm -rf dist
sudo npm run build
echo 'lxwshopping-front build OK '
ls -ltr ./

```

