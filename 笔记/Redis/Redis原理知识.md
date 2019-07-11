# 原理篇

### 线程IO模型

​	Redis是一个单线程的程序，nginx、nodejs都是单线程的。但是他也很快，这是因为数据都在内存中，计算是内存级别的。在处理O(n)的指令就会出现卡顿的问题。

​	Redis为什么可以处理多个客户端，这是因为它采用多路复用。

### 非阻塞IO

​	默认读写是阻塞的，最后可以是一个n，这个参数由套接字传递的，如果没有数据就不糊会返回，卡在哪里。直到有数据或者是断开连接，才会返回或者是继续处理。读的时候，只有放写满了才会阻塞。当有非堵塞的时候，就会出现一个问题，怎样就写完了，怎样就读完了，如果读不完怎么办。这个通过轮询来进行处理这个问题。

### 多路复用

最简单的时间轮询API Select函数，提供给用户的API.输入是read_fds和write_fds.输出是对应的可读事件，提供一个timeout.没有任何事件的时候，等timeout就会进入堵塞，有事件来就会返回。拿到事件就会进行轮询，进入一个死循环。

### 指令队列

Redis会将每一个客户端套接字都关联一个指令队列，通过队列来执行任务，先到先服务。

### 服务队列

同时提供一个响应队列，Redis通过队列将返回结果返回给客户端，为null，就不去获取写事件了。将客户端描述符拿出来。当队列有数据了在进行获取。

###  定时任务

单线程的任务，如果在一个IO上堵塞，那么定时任务到时怎么办？
Redis的计时放在一个堆中，将快要执行的任务放在最上面，每一个循环周期中，Redis都会对最小堆里面已经到时的进行处理，将快要执行的任务时间记下来，这个值是select的timeout。在这个时间可以安心睡了。



## 持久化

​	Redis数据存储在内存中，宕机就会消失，那么就可以使用一种策略，让其可以一种存在，第一种使用快照；第二种使用AOF日志。但是有个问题，AOF的增大，启动会变慢。快照是存在一个紧凑的二进制文件中。

### 快照原理

​	Redis是单线程，他需要执行多个客户端的操作。如果需要快照，那么就需要一边服务一边进行IO快照。并且IO操作不可以多路复用。

​	问题：快照的同时，数据还在改变，这怎么办。

​	Redis使用的是WOW(copy on write)

### fork

​	持久化会调用glibc函数fork产生一个子进程，快照持久化完全交给子进程来处理，父进程继续响应客户端。

在子进程进行数据持久化的时候，不会修改现有数据的内存数据结构，仅仅只是循环，将数据持久化到内存中。父进程对客户端进行处理，不断修改。

​	Copy on write在复制上写，将数据段复制一个，然后在上面进行修改。



### AOF原理

AOF存储的是Redis服务器执行的指令，仅仅记录了修改的指令。

AOF的流程为：收到指令，先对指令进行效验、逻辑处理，没有问题，就会立即将文本指令存储到AOF日志中**先执行，在写入日志**

AOF日志过大之后，需要对其进行瘦身处理。

### AOF日志重写

Redis提供了bgrewriteaof指令进行瘦身，原理是：开辟一个新的子进程对内存进行遍历，转化为一系列的指令，将指令放入到新的一个日志中，再将执行期间的日志追加，然后将旧的日志替换掉。瘦身就算结束了。

### fsyns

aof日志的形式放在内存中，但是aof读写的时候，先将数据放入内存中，然后在刷到磁盘。所以还有有数据的丢失，linux中glibc通过了fsync函数可以将指定文件强制刷到磁盘。只需要实时刷新就可以保证。

​	问题：一条数据一个IO，性能怎么办。

​	生产环境中，一般就每隔1s执行一次，可以进行配置【需要在数据库和redis之间做一个折中吧】。

一般为了保证的方式为：

​	操作系统自己搞定，还有一种就是上面的方法，但是都不会使用~~~~~~~~~~~~~~~~~。

### 运维

快照使用子进程是通过一个子进程完成， 它会比较的浪费资源的操作。

1.遍历整个内存，会增加系统负担。

2.io操作，降低redis性能。



一般都是主备，备用的进行持久化。



### Redis 4.0混合持久化

rdb快照，一般不用，数据丢失太严重。但是aof数据大的时候它的操作慢。

Redis 4.0处理方法
	将rdb和aof日志放在一起，aof不是全量，仅仅是在持久化这一段时间的增量。

​	重启的时候先将rdb的数据读取出来，然后在将aof的全量文件重放。



## 管道

 Redis中的管道是由客户端提供的，不是由服务端提供的，

在管道中的数据越多，它的效果越好。

- 压力测试

### 管道的本质

管道本质：

（1）将数据写到操作系统内核为套接字分配的发生缓冲区。

（2）客户端操作系统内核将数据发生缓存的内容发生到网卡，网卡发生到目标网卡。

（3）服务操作系统内核将网卡数据放到内核为套接字分配的接收缓存区中。

（4）服务器进程读取消息进行处理。

（5）处理结束将数据放入到发生缓存区。

write并不是收到才返回，他仅仅将数据放入发送缓存即可，如果缓存满了，那就要等待了。

read也仅仅是将数据读取出来就可以了，如果缓存为空，就需要等待。



## 事务

Redis数据库不想其他数据库那么的复杂和难以使用，所以也可以不用想使用其他数据库一样的方式使用它们。

### 基本使用

每个事务都有begin、commit、rollback，begin指示事务的开始，commit提交事务。rollback进行回滚。

```java
begin();
try{
    commit()
}catch(Exception e){
    rollback();
}
```

```shell

```

一个完整的事务操作，所有的指令先进入到队列中，然后在exec之前并进行执行，服务器收到exec就会立即执行。单线程也不会担心在执行的过程中被打断。

multi开始事务   exec:执行事务  discard：事务丢弃

```shell
test:0>multi
"OK"
test:0>incr books
"QUEUED"
test:0>incr books
"QUEUED"
test:0>exec
 1)  "1"
 2)  "2"
test:0>get books
"2"
```

### 原子性

事务要么成功，要么失败，Redis中原子性？

```shell
test:0>multi
"OK"
test:0>set books iam
"QUEUED"
test:0>incr books
"QUEUED"
test:0>set poorman ide
"QUEUED"
test:0>exec
 1)  "OK"
 2)  "ERR value is not an integer or out of range"
 3)  "OK"
test:0>get books
"iam"
test:0>get poorman
"ide"
上面的代码说明遇到错误后面的代码还可以继续执行。也可以验证不是立即执行的。
```

结论：：原子性他是不存在的，他仅仅是一个隔离，不被打断。

### discard(丢弃)

用于丢弃事务中的指令，用于丢弃所有的指令。

```shell
test:0>multi
"OK"
test:0>incr books
"QUEUED"
test:0>incr books 
"QUEUED"
test:0>discard
"OK"
test:0>exec
"ERR EXEC without MULTI"
```

在执行了discard之后，事务有关的指令被丢弃。

### 优化

上面的事务，每次发送一个就会有一次网络请求和返回，但是他并不会执行指令，所以可以将多次网络操作变为一次。一般可以使用pipeline一起使用。

```python
pipe = redis.pipeline(transaction=true);
pipe.muti();
pipe.incr("books");
pipe.ince("books");
values = pipe.execute();
```

### watch

​	场景描述：两个用户对一个金额进行并发修改，Redis是单线程，所以在执行的时候，基本不会出现并发，这个场景是需要乘以一个数据，Redis并没有这类的方法，操作是将数据的获取到，操作之后见数据写回去，两个就会出现问题。

​	redis提供了一个分布式锁，也可以使用watch进行操作。

​	watch的操作是：在事务启动之前顶住一个或者多个关键字，事务执行的时候，服务器收到exec指令，执行指令队列，Redis会检测监听的变量是否发生改变，如果改变了就会返回null。





**注意事项：**不可以在multi和exec之间执行。



## Pubsub

Redis的消息队列，它不支持多播。

### 消息多播

消息多播允许生产者值生产一次消息，由中间件负责将消息复制到多个消息队列，每个消息队列由相应的消息组进行消费，

用于将消费组逻辑进行拆分，消费组的逻辑就可以放在不同的子系统中。

普通的是将多个消费组串起来进行消费。

### pubsub

Redis为了消息多播，不依赖于5中基本的数据结构，而是使用一种新的方式Pubsub，来支持消费。

客户端发起订阅，redis会返回一个消息通知订阅成功。网络原因，客户端先发生，在通过getmessage将消息获取。

如果服务端发布消息，通过get_message获取到数据，如果没发布消息，就返回空，并不会进行堵塞。



他们的生成和消费是在不同的连接里，也就是说使用两个连接，在生产环境中，不会讲二者放在同一个线程的。

伪代码

```java
消费者
client.pubsub();
while true:
	msg = p.get_message();
	if not msg:
		time.sleep(1);
		continue();
	print msg
```

```java
client.publish("","");
client.publish("","");
client.publish("","");
client.publish("","");
```

上面消费者也可以使用监听的方式进行。

```python
for msg in p.listen():
    print msg
```

### 订阅模式

消费者订阅一个主题就必须指定一个主题的名称，如果订阅多个，那就subscribe多个名称

```

```



消费

```java
test:0>publish sub01 kangwang
"1"
```



订阅多个

```
test:0>subscribe col.io col.i
Switch to Pub/Sub mode. Close console tab to stop listen for messages.
 1)  "subscribe"
 2)  "col.io"
 3)  "1"
 1)  "message"
 2)  "col.io"
 3)  "i"
 1)  "message"
 2)  "col.i"
 3)  "u"
```

发布

```
test:0>publish col.io i
"1"
test:0>publish col.i u 
"1"
```



### 缺点

PubSub生产者传递一个消息，会直接找到消费者消费掉，没有消费者就会丢弃，如果多个消费者，如果消费者死了，消息就永远的没了。





## 小对象压缩

Redis将数据放入在数据库中，如果不节约内存就会导致崩溃，所以需要优化存储。

### 32bit和64bit

redis如果使用32bit进行编译，那么它的数据指针就会占用少一半，如果内存不超过4GB，可以使用32bit进行编译，

###  小对象压缩存储

内部管理集合数据结构很小，会使用紧凑的形式压缩存储。

Redis的是zplist是一个进村的字节数组结构，每个元素紧挨着，





### 内存回收机制

​	Redis总是将空闲的立即归还操作系统，  Redis的内存有10GB,如果删除10GB，内存变化不大，原因是通过页来回收的，只有还有一个key使用，就不会被回收，虽然删除，但是这一个GB，是分散在不同的位置，其他的页上还有key，所以就不会被回收。

​	如果做个狠人，执行了flushdb，内存就会发生大的变换，它将所有数据都干掉了。。

​	所说不会立即的删除key的内存，但是位置在，下一波数据来了还可以继续存放。



## 内存分配算法

内存分配是一个非常复杂的课题，适当的算法划分内存页，Redis将内存管理交给第三方来进行管理，使用jemalloc进行内存管理。也可以切换到tcmalloc，j的性能比t的好一点。

```
info memary 查看内存分配算法
test:0>info memory
"# Memory
used_memory:773960
used_memory_human:755.82K
used_memory_rss:737048
used_memory_rss_human:719.77K
used_memory_peak:808448
used_memory_peak_human:789.50K
total_system_memory:0
total_system_memory_human:0B
used_memory_lua:37888
used_memory_lua_human:37.00K
maxmemory:104857600
maxmemory_human:100.00M
maxmemory_policy:noeviction
mem_fragmentation_ratio:0.95
mem_allocator:jemalloc-3.6.0
"
```





































[TOC]

