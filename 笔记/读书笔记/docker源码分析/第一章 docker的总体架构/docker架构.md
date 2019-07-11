# docker架构
```
```
## docker总架构图
``` 
    docker是一个C/S模型，通过客户端与服务端建立通信
    docker的模块：
        docker client、dockerdeamon、docker registry、graph、driver、libcontainer以及docker container。
    用户通过与服务器建立连接，最总交由docker deamon来执行
    docker有能力接收请求、有处理的能力、任务交给engine来处理，每一项工作都以job形式存在
    libcontainer：一套独立的容器管理解决方案

```
##各个模块
```
    docker client:
        它于docker deamon建立连接，用户可以使用可执行文件作为docker client
        可以设置参数，保证通信的安全性

        docker client发送一次请求后，由deamon接收处理，接收到一次响应，那么一次完整的声明周期结束。
    docker deamon:
        一个后台进程，运行docker就是运行这个进程。
        作用：
            接收和处理请求
            管理docker容器
        deamon运行的时候会启动一个server，接收请求，然后通过调度，找到相应的Handler来处理请求。
        执行可执行文件使用docker，但是client和服务端的区分，根据传入的参数进行判别的
        docker deamon大致分为三块
            docker server、Engine、Job
            docker server:主要作用，接收client的请求，调度分发client的请求。
                启动过程：
                    docker先启动，然后启动路由【mux.Router】，然后添加有限的路由项【http/URL/Handler】
                    因为客户端使用的是http请求，所以在启动之后，会创建一个server，通过http。
                docker server监听接收client的访问请求，每一个client都会创建一个goroutine来服务
                （1）首先获取请求内容
                （2）解析工作
                （3）匹配路由
                （4）交由相应的handler来处理
                （5）处理结束之后交给客户端
            engine：是一个运行引擎，也是运行的核心模块，存储着大量的镜像信息，管理着大部分的job运行，通过engine匹配相应的job运行。
            在engine中有许多job各自运行的处理方式handler，当deamon退出时，engine负责将后续工作完成。

            job：是engine中最基本的工作执行单元，执行的每一个任务都会对应一个job
        docker Registry：
            是一个存储容器镜像的仓库，镜像是容器用来创建的系统内容

        Graph：是容器镜像的保管者，上传下载均由此负责
        Driver：是Docker的驱动模块，可以实现对于容器的定制

```
## Docker运行的实例
```
    docker运行的原理：
        docker pull：
            命令的作用：是deamon从仓库下载指定容器的镜像，并将其存储在本地的Graph中，以备之后使用。
        执行之后的执行过程：
            （1）Docker Client处理用户发起的docker pull命令，首先获取参数，然后解析参数，通过参数向仓库发送一次http请求，请求方式是post，请求地址下载镜像。
            （2）Docker Server接收以上Http后，交给mux.Router，根据URL以及参数来确定执行该请求的具体handler。
            （3）交给handler之后，创建一个job，在将其交给job执行。
            （4）从仓库中下载镜像
            （5）将下载的镜像交给Graph进行存储，以备之后的使用。
        docker run：
            这个命令的作用是创建一个容器，并在内部执行命令。
            收到这个命令之后，会有两个操作：
                (1)创建对象，并为其创建一个rootfs
                (2)创建容器的运行环境，如网络环境、资源限制。最终执行命令
            所以在执行的过程中，客户端向服务端发生了两次请求
        执行步骤：
            （1）Docker client处理用户发起的run命令，获取参数，解析，向server发生请求，请求方式是post，创建一个对象
            （2）docker server得到之后，将其交给mux.Router，mux.Router根据URL以及请求方法来确定执行的具体handler
            （3）mux.Router将其发送给handler
            （4）创建并初始化一个Job，并执行这个job，
            （5）从Graphdriver中获取这个Docker容器rootfs所需的所有镜像。
            （6）将所有的镜像联合加载到Docker容器指定的文件目录下
            ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                上面的操作执行完成，并没哟发生异常，那么客户端就会收到返回状态
                然后发送第二次请求。
            =========================================================== 
            （7）类似的步骤，再次到达一个handler,创建一个job，然后在触发这个job
            （8）启动start方法，完成一系列配置工作，分配网络资源，通过networkdriver分配
            （9）开始完成用户的命令                        
            （10）初始化内部环境
            （11）完成初始化，并最终执行用户要求启动的命令




