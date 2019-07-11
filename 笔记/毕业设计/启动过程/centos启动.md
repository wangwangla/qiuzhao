## 云服务器配置（Centos 7.3）

#### jdk的安装

(1)查看软件包列表

```shell
yum search java | grep -i --color jdk
```

（2）选择版本进行安装

```shell
yum install -y java-1.8.0-openjdk java-1.8.0-openjdk-devel
```

或者如下命令，安装jdk1.8.0的所有文件

```shell
yum install -y java-1.8.0-openjdk*
```

（3）查看是否安装成功

```shell
java -version
```

（4）环境变量配置

```shell
set java environment  
JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.181-3.b13.el7_5.x86_64
PATH=$PATH:$JAVA_HOME/bin  
CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar  
export JAVA_HOME  CLASSPATH  PATH 
```

（5）立即生效

```shell
source  /etc/profile
```

（6）查看变量

```shell
echo $JAVA_HOME
echo $PATH
echo $CLASSPATH
```

​	

#### 米特网注册账号

https://www.mite5.com

#### nginx的安装

```
更新软件
yum update
安装必须工具
yum -y install make zlib zlib-devel gcc-c++ libtool openssl openssl-devel
安装pcre  
wget http://downloads.sourceforge.net/project/pcre/pcre/8.35/pcre-8.35.tar.gz	
解压安装包：tar zxvf pcre-8.35.tar.gz
进入安装包目录：cd pcre-8.35
编译：./configure
安装：make && make install

下载nginx
cd /usr/local/
wget http://nginx.org/download/nginx-1.8.0.tar.gz
tar -zxvf nginx-1.8.0.tar.gz
cd nginx-1.8.0  
安装nginx指定目录
./configure --prefix=/usr/local/nginx 
make
make install

--with-pcre=/usr/local/pcre-8.36 指的是pcre-8.36 的源码路径。
--with-zlib=/usr/local/zlib-1.2.8 指的是zlib-1.2.8 的源码路径。
```

启动

```shell
systemctl restart docker.service
```

 检查是否启动成功：

​	打开浏览器访问此机器的 IP，如果浏览器出现 Welcome to nginx! 则表示 Nginx 已经安装并运行成功。

部分命令如下：
	重启：

```shelll
/usr/local/nginx/sbin/nginx –s reload
```

​	停止：

```shell
/usr/local/nginx/sbin/nginx –s stop
```

```
测试配置文件是否正常：
$ /usr/local/nginx/sbin/nginx –t

强制关闭：
$ pkill nginx
```

### Redis安装

```
yum install redis
```

### 数据库安装

```
- 查看本身是否已经安装
rpm -qa | grep mariadb
- 卸载
rpm -e --nodeps  上面查出来的名字
- 设置yum repository

设置rmp 
rpm -Uvh https://dl.fedoraproject.org/pub/epel/epel-release-latest-6.noarch.rpm

下载webtatic-release RPM
rpm -Uvh https://mirror.webtatic.com/yum/el6/latest.rpm

安装
yum install mysql55w mysql55w-server

service mysqld start 开启服务

mysqladmin -u root password 'root'设置密码

mysql -u root -p  登录
```



#### consul安装

```
# wget https://releases.hashicorp.com/consul/1.3.0/consul_1.3.0_linux_amd64.zip
# unzip  consul_1.3.0_linux_amd64.zip
./consul
```



#### jboot项目构建

构建前需要注意：

​	base模块中的upload中写了一个死代码，那是nginx的html目录，需要修改。图片的服务器地址是http://服务器IP:80/图片名称.格式

项目根目录执行

```
mvn clean install	 mvn clean
```

达到客户端和服务端分别执行

```
mvn clean package appassembler:generate-daemons
```

上传服务器

```
jboot-admin\jboot-admin\target目录下的generated-resources复制到服务器
在服务器上给执行权限，然后启动服务器
```

​	在服务器上将他们授予执行权限，然后就可以进行执行了，在执行的过程中，可以查看日志，在日志目录下，查看根目录下的启动日志。

```
到达bin目录下执行
chmod 777 *
```

启动

```
客户端启动
./jboot-admin start
服务端启动
./jboot-system-provider
```



犯得错误：在根目录下执行名，这个要在服务端程序和客户端程序中都执行，然后分别启动即可。

#### 服务器配置

```shell
数据库
consul
redis	 	 
```

### 启动前检查

- nginx已启动
  ./nginx
- consul启动
  ./consul agent -dev 或者consul agent -dev
- Redis启动
  开机找服务开启
  ./redis-server

jboot-admin下Application
jboot-admin-provider下Application

运行run方法。