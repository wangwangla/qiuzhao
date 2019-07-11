1.http://mirrors.aliyun.com/docker-toolbox/windows/docker-toolbox/
2.安装
  傻瓜式安装
  初始化的时候会获取不到镜像，或者下载失败，可以使用翻墙软件，我用手机下载，在github上找小明vpn，下载就可以使用。
  下载结束，复制到cache目录下
  我的启动是失败的，查了一下日志
  00:12:51.00:10:04.129200 GUI: UIMediumEnumerator: Medium-enumeration finished!
    00:11:35.703328 ERROR [COM]: aRC=E_UNEXPECTED (0x8000ffff) aIID={7844aa05-b02e-4cdd-a04f-ade4a762e6b7} aComponent={SessionWrap} aText={The session is not locked (session state: Unlocked)}, preserve=false aResultDetail=0
    00:13:07.414974 GUI: UISelectorWindow: Geometry saved as: Origin=709x371, Size=933x306
 网上的解决方法：
    原因：
        原因因为大多人使用的ghost系统都会破解uxtheme.dll文件
        导致virtualbox启动失败验证uxtheme.dll
        下载microsoft的sigcheck工具http://technet.microsoft.com/en-us/sysinternals/bb897441.aspx 
        运行命令sigcheck -i -a -h c:\windows\system32\uxtheme.dll显示结果第一行是 Verified:Unigned 说明是破解的 
        显示结果第一行是 Verified:Signed 说明是原版的 
        使用原版的uxtheme.dll替换c:\windows\system32\uxtheme.dll即可正常使用virtualbox
    替换掉windows/system/uxtheme.dll文件
        安装如果有统一问题，私信我。
    我的替换掉就已经好了。

3.docker安装Jenkins
    docker search jenkins 有一个官方的，但是我下载失败了
    docker pull jenkins/jenkins


    下载镜像太慢，修改仓库地址，在window下修改config中仓库的位置
4.docker安装maven
    


https://hub.docker.com
注册账号
    账号：kangwang
    密码：********
在本地登录：
    docker login
推送到仓库中
    docker push

举例：
    下载hello-world
    docker pull hello-world
    
    将它推送到我的仓库中
    修改个名字
    docker tag 23hdfdkf4 kwlalala
    docker push kwlalala

使用自动构建，根据github中的代码进行自己构建
    （1）在本地写好Dockerfile文件上传到github上，然后登陆自己的docker账号
    （2）使用docker 账号关联github
    （3）选中github中的文件
    （4）然后点击Click here to customize
    （5）点击Create
    这个时候就可以看到构建的镜像

搭建本地仓库
    获取镜像：
        docker pull docker-register
    运行镜像
        sudo docker run -d   -p 5000:5000 -v /opt/data/registry:/var/lib/registry --name private_registry registry 
                       后台  端口           挂载  ，前者是宿主机的目录，后者是容器的目录     名字


spring boot和docker的集成
    需要打成jar，就需要相应的插件支持，导入maven中

在resource目录下：
    创建Dockerfile文件
        FROM java
        MAINTAINER "name"<292992@qq.com>
        Add xx.jar app.jar
        expose 80
        CMD Java -jar app.jar

问题来了，你放个文件谁来搭理你呢
    这个时候需要docker插件
     <plugin>
        <groupId>com.spotify</groupId>
        <artifactId>docker-maven-plugin</artifactId>
        <version>0.4.13</version>
        <configuration>
          <imageName>${project}</imageName>
          <dockerDirectory>${project.basedir}/src/main/docker</dockerDirectory>
          <resources>
            <resource>
              <targetPath>/</targetPath>
              <directory>${project.build.directory}</directory>
              <include>${project.build.finalName}.jar</include>
            </resource>
          </resources>
        </configuration>
      </plugin>
然后执行mvn docker:build命令

如果需要将其存入仓库，可以添加maven
<peo>







E:\TSBrowserDownloads\gitblit-1.8.0>"E:\TSBrowserDownloads\gitblit-1.8.0\amd64\gitblit.exe"  
//IS//gitblit
--Description="a pure Java Git solution"                
--Startup=auto                  
--LogPath="E:\TSBrowserDownloads
LogLevel=INFO            
--LogPrefix=gitblit             
--StdOutput=auto                
--StdError=auto
\gitblit-1.8.0"                  
--StartClass=org.moxie.MxLauncher               
--StartMethod=main              
--StartPaseFolder;
E:\TSBrowserDownloads\gitblit-1.8.0\data"              
--StartMode=jvm                 
--StopPath="E:\TSBrowser
 --StopClass=org.moxie.MxLauncher                
 --StopMethod=main               
 --StopParams="--stop;--baseFolder;E:\TSB
"                --StopMode=jvm                 
 --Classpath="E:\TSBrowserDownloads\gitblit-1.8.0\gitblit.jar"
24

E:\TSBrowserDownloads\gitblit-1.8.0>





gitblib安装，只需要修改一下就可以了，然后在服务只能够开启。

    设置版本库的位置

    git.repositoriesFolder = C:/data/GitRepository

    设置端口号

    server.httpPort = 8088

    设置ip地址

    server.httpBindInterface = 192.168.137.128

    server.certificateAlias = localhost



docker中使用jenkins
    下载镜像 docker pull jenkins
    使用镜像 docker run -p 8080:8080 jenkins
    这个可前台运行，也可以后台运行，后台就看不到密码了，所以第一次启动的时候，不要使用后台运行。
    我们可以在里面增加用户，然后将自己的配置都写好之后，将其推送到自己的仓库中去，以便以后使用下载。

docker在阿里云的构建



docker-machine create --engine-registry-mirror=https://erwhn47e.mirror.aliyuncs.com -d virtualbox default
    
===========================================================================================
    Ubuntu下Docker
    更新镜像源
        sudo apt-get update
    安装docker.io
        sudo apt-get install docker.io
    sudo apt-get install curl
    curl -sSL https://get.docker.com | sh

    docker service start


    docker run --name=postgresql-redmine -d --env='DB_NAME=redmine_production' --env='DB_USER=redmine' --env='DB_PASS=password' sameersbn/postgrsql:9.4-12


    docker run --name gitlab-redis -d sameersbn/redis:latest

    docker run --name gitlabzz --link gitlab-postgresql1:postgresql --link gitlab-redis01:redisio --publish 10022:22 --publish 10080:80 --env 'GITLAB_PORT=10080' --env 'GITLAB_SSH_PORT=10022' --env 'GITLAB_SECRETS_DB_KEY_ABSE=long-and-random-alpha-numeric-string' sameersbn/gitlab:8.4.4