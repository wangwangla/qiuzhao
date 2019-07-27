## windows 运行

运行前需要注意：

​	base模块中的upload中写了一个死代码，那是nginx的html目录，需要修改。图片的服务器地址是http://服务器IP:80/图片名称.格式

### 启动前检查

- nginx已启动
  ./nginx
- consul启动
  ./consul agent -dev 或者consul agent -dev
- Redis启动
  开机找服务开启
  ./redis-server
  redis没有设置密码
- mysql已经启动


#### 项目启动

jboot-admin下Application
jboot-admin-provider下Application

运行run方法。



端口为8888.