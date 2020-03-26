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



