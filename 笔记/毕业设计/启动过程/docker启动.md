## docker发布项目

首先我们需要安装redis、数据库、consul、java镜像。

#### docker安装

```shell
yum -y install docker-io
yum list installed | grep docker
```

镜像加速

```shell
{
  "registry-mirrors": ["https://wghlmi3i.mirror.aliyuncs.com"]
}
或者
{
  "registry-mirrors": ["https://registry.docker-cn.com"]
}
```

启动服务

```shel
systemctl start docker.service
```

已经安装完成，下来案例实验。

- 启动docker

  ```shell
  [root@VM_0_15_centos ~]# systemctl start docker
  ```

  

- 停止

  ```shell
  systemctl stop docker
  ```

  

（1）启动一个hello-world

```shell
 docker run hello-world 运行容器，如果没有，就会自己去下载
```

（2）安装mysql

- 速索mysql

```shell
docker search mysql
```

- pull镜像

```shell
 docker pull  docker.io/mysql
```

- 启动它

```shell
  sudo docker run --name kw-mysql -e MYSQL_ROOT_PASSWORD=123456 -p 3307:3306 -d mysql
```

- –name：给新创建的容器命名，此处命名为`pwc-mysql`
- -e：配置信息，此处配置`mysql`的`root用户`的登陆密码
- -p：端口映射，此处映射`主机3306端口`到`容器pwc-mysql的3306端口`
- -d：成功启动容器后输出容器的完整ID，例如上图 `73f8811f669ee...`
- 最后一个`mysql`指的是`mysql镜像名字`

远程连接

```shell
mysql> CREATE USER 'kangwang'@'%' IDENTIFIED BY '123456';
Query OK, 0 rows affected (0.00 sec)
mysql> GRANT ALL ON *.* TO 'kangwang'@'%';
Query OK, 0 rows affected (0.01 sec)
```

cmd中输入

```shell
mysql -h118.24.235.237 -p3307 -ukangwang -p123456
```

（3）安装redis

查询Redis

```shell
docker search redis
```

下载redis

```shell
 docker pull docker.io/redis
```

启动redis

```shell
docker run --name kw-redis -p 6379:6379 -d redis:latest redis-server
```

使用工具连接数据库。



补充：

​	使用makefile构建redis

```shell
FROM 
```



（3）docker安装consul

查找镜像

```shell
docker search consul
```

下载镜像

```shell
docker pull consul
```

启动

```
docker run --name consuls -d -p 8500:8500 -p 8300:8300 -p 8301:8301 -p 8302:8302 -p 8600:8600 consul:1.4.4 agent -server -bootstrap-expect 2 -ui -bind=0.0.0.0 -client=0.0.0.0
```

注意端口占用。