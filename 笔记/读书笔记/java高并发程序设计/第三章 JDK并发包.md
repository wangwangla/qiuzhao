### Jdk并发包

java中保证线程安全的操作，首先是volita/syn、wait/notify、join、sleep.

#### Synchronized:可重入锁

5.0加入冲入锁lock，6.0对syn进行优化，使得二者的差距不是很大。lock可以手动的加锁和取消锁，所以他是比较灵活的,重入锁有个好处就是重复的加锁。可以手动的进行加锁和解锁，但是一定要记得取消锁，否则其他的线程就无法进入到临界区域了。

```
创建锁
ReentrantLock r = new ReentrantLock();

//使用
r.lock();
代码
r.unlock();

//重入代码  重入锁还有一个就是锁几次，释放几次。
r.lock();
r.lock();
代码
r.unlock();
r.unlock();
```

可以多次加入同一个锁，如果不是这样就会造成死锁的情况

### 中断响应

syn只有两种结果：一种是一直等待，第二种是得到锁继续执行，但是对于重入锁就会有第三个情况，就是等了一会中断等待、收到通知，自己停止等待。这个可以解决死锁问题。

中断锁使用的锁请求是lockInterruptibly();

```
t.interrupt();可以放弃锁，也可以放弃当前锁请求。
```

锁的使用

```
lock1.lockInterruptibly()
//执行代码
lock.unlock();


启动线程
Thread t = new Thread(lock);
t.start();

//中断
lock.interrupt();
```

假如有多个线程执行，就可以中断他们，最后看到的结果如果t在等，在收到中断之前还没有锁，那么就不等了退出。



### 锁申请等待是时限

死锁解决方法二就是使用时限锁，时间到位获得锁那么就放弃

```
tryLock(时间，时间单位)；返回一个boolean值。
```

还有一个方法就是无参数的，

```
tryLock();锁空闲，那么就直接的返回true，锁不空闲，那就返回false
```

### 公平锁

多个线程同时对同一个锁进行请求，但是执行哪一个确实不知道的，什么时候可以获得也是不知道的，他会随机的挑出一个进行执行。公平锁会遵循先到先得的顺序，也会遵循一个顺序。synchronized是不公平的锁，lock我们可以对其进行设置。

```
public ReentrantLock(boolean fair);
```

参数为true的时候就是公平锁，公平锁需要维护一个队列，所以他的速度效率就会降低，公平锁的效果就是二者都可以执行。



总结一下ReentrantLock

- 构造函数可以设置公平和非公平
- lock给线程加锁
- lockInteruptibly()可以响应中断
- tryLock()如果锁空闲返回true，否则返回false
- tryLock(time,时间单位)，等待多久等待不到，那么就放弃了
- unlock()释放锁





### condition

它和wait和notify的作用一样，但是他们是和syn一起使用，condition是和重入锁一起使用的。在某一时刻等待，在某一时刻继续执行。

- 提供的方法
  - wait()
    - 使得当前线程等待，释放锁，需要其他线程执行signal方法重新获取锁，继续执行，或者当前被中断是，也会跳出等待。
  - waitUniteruptibly()
    - 和wait一样，但是他不会响应中断
  - long awaitNanos(long timeOut)
    - 
  - boolean await(long time,TimeUnit unit)
  - boolean awaitUntil(Date deasline)
  - void signal();
  - void signalAll();

在执行的过程中，执行了wait方法之后，就会释放锁，然后在执行了signal方法之后，会停止其他线程继续执行，但是重入锁都是由自己搞定的，所以需要执行一次释放锁。

注意的就是上面的两个方法都是在锁里面执行的。

**使用场景：在ArrayBlockedQueue里面就是使用重入锁，队列为空，就执行等待，当有数据就会通知，它所使用的锁式不会响应中断的**

### 允许多个线程同时访问

信号量Semaphore,他为多线程提供了强大的控制，之前的syn或者是重入，每次执行有一个在执行，但是信号量可以有多个线程同时访问。

- 构造方法

  ```
  public semaphore(int num)//准入线程个数，每次有多少线程可以同时访问
  public semaphone(int num,boolean fair)//是否公平
  ```

- 

### 读写锁

简单就是一句话，读的时候是不需要加锁的，但是写的时候必须加锁，也可以理解为有一个是写的情况都需要加锁。所以在读的频率远大于写的时候就可以使用读写锁了。

```
	ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	Lock read = readWriteLock.readLock();
	Lock write = readWriteLock.writeLock();
	读写锁
```





### CountDownLactch

等待线程，可以传入个数，表示线程等待的个数，可以通过构造方法传入，每个线程完成就执行一个方法，让计数器减去一，

加入等待线程是10，那么就会等待10个线程，线程都到达了，在一起执行

### 珊栏

他会计数，到达一次计数一次，通过减一的方式进行的，比如说有是个线程，是个线程都到达了，我们将计数器归零，再次计数，也就是说他是可以循环利用的一个，并不是只能用一次。他是一个加法，但是CountDownLatch是一个减法。

### LockSupport

阻塞工具类：可以让线程在任何位置阻塞，他和rusume相比不会导致线程无法执行，和Object.wait()相比，他是不需要获得锁，也不会抛出异常



## 多线程

线程可以帮助我们完成任何，但是不加以限制有可能导致资源耗尽，比如oom异常，也有可能造成GC加长。线程在一定范围内可以提高效率，如果低于就会导致内存大量使用，拖垮应用。

**概念**就是不用每次都去创建一个线程，而是将线程放在一个池子里面，每次从池子里面取出来使用，使用结束在放回去。

线程池ThreadPoolExecutor,Executors是一个线程工厂，可以用于取出一个特定功能的线程池，ThreadPoolExecutor继承了Executor，所以他会执行任何Runnable对象。

**方法：**

- newFixedThreadPool(int nThread)//创建指定数量的线程池，有空闲就处理，没有空闲就等待，直到有空闲了在处理。
- newSingleThreadExecutor():该方法返回一个线程，如果其他资源来访问，就会等待，放入一个任务队列中，先入先出（相对比较公平），直到线程空闲，。
- newCacheThreadPool():不是固定的，他可以改变的，线程中都在忙的时候，如果有提交新任务，就会创建一个线程继续执行，最终放回线程池继续使用。

```
Executor es = Executors.newFixThreadPool(5);
for(int i=0;i<6;i++){
    es.submit(thread);
}
```





### 计划任务







## 线程实现原理

上面的线程基本都是通过ThreadPoolExecutor(),

- 方法的参数
  - 指定线程数量
  - 指定线程的最大数量
  - keepLiveTime在超过线程数量的时候，在多久之后会死掉。
  - unit:时间单位
  - waitQueue任务队列，提交但是未执行的
  - threadFactory线程工厂，用于创建线程，一般默认即可
  - 拒绝策略，任何任务来不及处理，就会拒绝
- 任务用来存储Runnable，他可以使用BlackingQueue接口
  - 









