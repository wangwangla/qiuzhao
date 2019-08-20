### Jdk并发包

java中保证线程安全的操作，首先是volita/syn、wait/notify、join、sleep.

#### Synchronized:可重入锁

5.0加入冲入锁lock，6.0对syn进行优化，使得二者的差距不是很大。lock可以手动的加锁和取消锁，所以他是比较灵活的,重入锁有个好处就是重复的加锁。

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



### 中断响应

syn只有两种结果：一种是一直等待，第二种是得到锁继续执行，但是对于重入锁就会有第三个情况，就是等了一会中断等待、收到通知，自己停止等待。这个可以解决死锁问题。

中断锁使用的锁请求是lockInterruptibly();

```
t.interrupt();可以放弃锁，也可以放弃当前锁请求。
```

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





