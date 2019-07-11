## 基础和应用

1.Redis是远程调用技术的首字母缩写。

2.Redis可以用来做什么？

- Redis可以用来做缓存。
- 分布式锁

3.Redis的应用举例

- 记录帖子的点赞数、评论数和点击数。（使用HASH）
- 记录用户的帖子ID列表，便于快速显示用户的帖子列表。（ZSET）
- 记录帖子的标题、摘要、作者和封面信息，用于展示。（hash）
- 记录帖子的点怎用户ID和列表ID，用于显示和去重计数。（zset）
- 缓存近期热帖内容，减少数据库压力。（hash）
- 记录帖子相关文章ID,根据内容推荐相关帖子。（list）
- 如图帖子是正数递增，可以使用Redis来分配帖子ID。（计数器）
- 收藏集合帖子的之前的关系。（zset）
- 记录热榜的 ID列表、总热榜和分类榜
- 缓存用户行为的历史，过滤恶意行为（zset、hash）

## Redis安装

Redis安装可以在window、linux中安装，也可以使用docker安装。

- Docker安装方式

  ```shell
  拉取镜像
  docker  pull redis
  运行容器
  docker run --name myredis -d -p 6379:6379 redis
  后台执行
  docker exec -it myredis redis-cli
  ```

- github源码安装

  ```shell
  git clone 
  cd redis 
  编译
  make
  cd src
  运行服务
  ./redis-server --deamonize yes
  运行客户端
  ./redis-cli
  ```

  

- apt-get install     /yum install     /brew install 

```shell
yum install redis
redis-cli
```

## 5种基本的数据结构

​	Redis的数据结构，分为string、list、hash、set、zset

### string

-  它的内部是一个字符串数组，使用一个唯一的key作为字符串名称的，通过key获取数据。不同的值在于他们value的值不同。

- 字符串一般可以使用在缓存，将用户信息序列化之后放入字符串中。

- 实现原理：类似于Arraylist，使用阈值的方式开辟空间，一般会加倍，但是当数据大于1M的时候，每次加1M,最大的值是512M.

- 基本操作

  - 键值对

    ```shell
    test:0>set name value01
    "OK"
    test:0>get name
    "value01"
    test:0>exsits name
    "ERR unknown command 'exsits'"
    test:0>exists name
    "1"
    test:0>del name
    "1"
    test:0>get name
    null
    test:0>
    ```

    

  - 批量键值对

    ```shell
    test:0>set name1 value01
    "OK"
    test:0>set name2 value02
    "OK"
    test:0>mget name1 name2
     1)  "value01"
     2)  "value02"
    test:0>mset name1 kangwang name2 jiangyu name3 yjh
    "OK"
    test:0>mget name1 name2 name3
     1)  "kangwang"
     2)  "jiangyu"
     3)  "yjh"
    test:0>
    ```

    

  - 过期和set指令的扩展

    ```shell
    test:0>set name kangwang
    "OK"
    test:0>expire name 5
    "1"
    test:0>get name
    null
    ---------------
    test:0>setex name 5 code
    "OK"
    test:0>get name
    null
    -----------------
    test:0>setnx name kkkk
    "1"
    test:0>setnx name kkkk
    "0"
    ```

  - 计数

    ```shell
    test:0>set age 39
    "OK"
    test:0>incr age
    "40"
    test:0>incrby age 5
    "45"
    ```

### list

​	类似于java的linkList，他是链表不是数组。他可以作为消息队列

- 队列

```shell
test:0>lpush books java c++ c python
"4"
test:0>llen books
"4"
test:0>lpush books java c++ c python
"4"
test:0>llen books
"4"
test:0>rpop books
"java"
```

- 堆栈

```shell
左边入，左边出
```

- 角标获取数据

```
test:0>lindex books 0
"python"
test:0>lrange books 0 -1
 1)  "python"
 2)  "c"
 3)  "c++"
test:0>
```

- 快速列表

Redis底层使用的是一个快速列表，在数据小的时候，将数据存储在一块练习的内存中。当数据比较多的时候，普通链表太浪费空间，所以它将多个内存块的数据使用链表连接起来。

![1558161362770](E:/%E4%B8%93%E9%A2%98/%E8%AF%BB%E4%B9%A6%E7%AC%94%E8%AE%B0/OpenGLES%E6%B8%B8%E6%88%8F%E5%BC%80%E5%8F%91/1558161362770.png)



### Hash



典相当于java的HashMap，无序的字典，内部结构和HashMap类似。不同之处：

​	Redis字典相当于java的HashMap，无序的字典，内部结构和HashMap类似。不同之处：

- Redis只会是字符串。，并且hash的方式也不一样。
- java会一次的进行hash，但是redis会慢慢的进行hash，他会保存两个hash结构，然后在后续定时任务以及其他操作的时候，逐渐的进行迁移，当最后一个节点移动完毕的时候，就会将其取而代之。
- hash可以存储用户的信息，字符串会一次的进行序列化，并且会将所有的数据读取。浪费带宽。



### Set

​	Redis相当于hashSet，内部的建是无序的、唯一的。当最后一个数据删除，数据就会自己的删除，可以进行去重保证一次不会出现两次的数据。

```shell
test:0>sadd stu s1
"1"
test:0>sadd stu s2
"1"
test:0>smembers stu
 1)  "s2"
 2)  "s1"
test:0>sadd stu s
"1"
test:0>smembers stu
 1)  "s"
 2)  "s2"
 3)  "s1"
```

顺序也是不一致的。

其他操作

```java
是否存在数据
test:0>sismember stu s
"1"
数据的长度
test:0>scard stu
"3"
弹出
test:0>spop stu
"s2"
```

### zset

有序列表,它类似于sorted和HashMap，set保证唯一，Sort保证有序。最后一个数据撒喊出，那么数据结构也会删除。可以用来存储，评论和点赞。



### 通用规则

list、set、hash、zset如果不存在就会创建，再操作。没有数据了就删除了。



## 过期时间

过期他是一个对象进行过期，不是某一个元素，也不是key、如果设置了过期时间，在对其进行修改，就会将其过期时间删除。











[TOC]

