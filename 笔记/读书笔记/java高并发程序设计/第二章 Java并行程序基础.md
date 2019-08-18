## java并行程序基础

- 进程：操作系统调度的最小单元

- 线程：程序执行的最小单元

- 线程的生命周期

  ![1566108971279](phto/1266)

- 声明周期解释

  - new创建线程，还没运行
  - Runnable：执行了start方法后的线程，一起准备就绪
  - terminated：停止
  - blocked：阻塞，直到获得锁
  - wait:执行了等待方法的，他会等待notify方法执行。
  - join方法等待会等待目标线程终止



#### 线程的基本操作

- 创建线程

  ```
  Thread t = new Thread();
  t.start();
  start方法执行之后会启动一个线程去执行run方法
  ```

  问题1：直接调用run方法和start()执行有什么区别。

- 运行线程

  - 创建方式一：

  ````
  public static void main(String[] args) {
  		Thread t1 = new Thread() {
  			@Override
  			public void run() {
  				// TODO Auto-generated method stub
  				System.out.println("见贤思齐先生！");
  			}
  		};
  		t1.start();
  		//t1.start();  重复启动线程会报错。
  	}
  ````

  - 创建方式二

  ```
  Thread t2 = new Thread(new Runnable() {
  			
  			@Override
  			public void run() {
  				// TODO Auto-generated method stub
  				System.out.println("见贤思齐先生！");
  			}
  		});
  		t2.start();
  ```

  也可以继承Thread，重写run方法，也可以实现Runnable，重写run方法。

- 终止线程

  停止可以使用stop，但是已经废弃了，不在使用，它将执行一般的线程给停止了，导致数据不一致。stop停止，他会立即的将线程停止，并且释放锁，其他线程就可以进入。导致数据不一致。

  如何优雅的停止？？？

  我们告诉他们，什么时候停止线程即可

  - 在run 方法，while循环出设置一个标志位，如果标志位不符合条件，让他跳出去。

- 中断线程

  中断不是立即停止，也不是立即退出，而是给线程一个通知，告诉线程，你在合适的时候退出吧。所以什么时候退出自己说了算。

  中断处理的时候会有几个方法，第一个中断线程，第二个判断是否中断，第三个是判断是否中断，并清除中断状态。

  实例：

  ```
  	public static void main(String[] args) throws Exception{
  		Thread t = new Thread() {
  			@Override
  			public void run() {
  				// TODO Auto-generated method stub
  				Thread.yield();
  			}
  		}.start();
  		t.sleep(100);
  		t.interrupt();
  	}
  ```

  上面是使用了中断，但是这个中断的存在，他并不会造成线程的停止，线程依旧可以继续执行，因为中断了，但是并不清除什么时候退出循环，那么就需要使用如下代码，在中断 的时候退出while这个循环。

  ```
  	public static void main(String[] args) throws Exception{
  		Thread t = new Thread() {
  			@Override
  			public void run() {
  				// TODO Auto-generated method stub
  				while(true) {
  					=================
  					if(Thread.currentThread().isInterrupted()) {
  						System.out.println("wotuichu");
  						break;
  					}
  					System.out.println("zhongduan");
  					Thread.yield();
  					====================
  				}
  				
  			}
  		};
  		t.start();
  		t.sleep(100);
  		t.interrupt();
  	}
  }
  
  ```

  也许你会说，写个标志位岂不是更好，没有必要使用API提供的方法，其实是可以，但是中断肯定有它的优势。

- 出现了wait或者sleep，只能通过中断来识别了。

  ```
  	public static void main(String[] args) throws Exception{
  		Thread t = new Thread() {
  			@Override
  			public void run() {
  				while(true) {
  					if(Thread.currentThread().isInterrupted()) {
  						System.out.println("wotuichu");
  						break;
  					}
  					try {
  					    System.out.println("sleep");
  						Thread.sleep(100);
  					} catch (InterruptedException e) {
  						// TODO Auto-generated catch block
  						e.printStackTrace();
  						
  					}
  				}
  				
  			}
  		};
  		t.start();
  		t.sleep(100);
  		t.interrupt();
  	}
  ```

  执行结果为什么，执行一次sleep就睡了，然后有个中断标志，就退出了，但是结果不是那样的，因为异常在里面铺货了，需要在重新在中断一次才可以

  ```
  sleep
  sleep
  sleep
  ……
  ```

  

  我们知道sleep是不会释放锁的，在上面的代码中，sleep会抛出一个中断异常，但是他并不释放锁，因为这个异常已经被铺货了，我们需要退出去，就必须在来一次中断，然后退出去。

  ```
  	try {
  		Thread.sleep(100);
  	} catch (InterruptedException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  		Thread.currentThread().interrupt();
  	}				
  					}
  ```

  

- wait和notify方法

  这两个方法在Object中，所以它在每个里面都可以使用，

  举个例子：A线程在执行的过程中执行了一次wait方法，然后A就停止了运行，在等待中，知道其他线程调用了notify方法后。才可以继续执行

  问题1：执行wait并不至这一个，到底哪一个先执行呢，答案是只有一个执行，一般会在等待队列中随机选取一个【随机的】。

  问题2：如果想全部都唤醒，那就执行notifyAll();

  **注意：**wait和notify虽说是Object的方法，但是他可没有到处执行的权利，必须在syn代码块中（Syn是一个监视器），它必须获取一个监视器后，才可以执行。

  存在监视器就可以执行，执行之后就会释放监视器【这是它和sleep的区别】，说点别的，人不为己，天诛地灭，他需要别的线程来唤醒它，他肯定需要让位呀，所以他会释放，这个应该是必须释放吧。

  得到执行权的并不是直接就执行了，他什么时候执行，还是需要看自己的本事，获得锁才可以呀。

- suspend和resume挂起和继续执行

  已经废弃，算了不看他们了。

- join等待接收和谦让yeild

  join它可以做什么，一个线程的可能需要依赖其他线程的参数，或者中间结果，那么就需要等待其他线程执行执行，在一起执行。

  API中有两个方法，一个是一直等，一个是等等就溜了。

  一直等：就会一直阻塞当前的线程，直到目标指向完毕

  等等就溜了：他有一个最大等待时间，

  **案例**不是的时候

  ```
  public class Demo04 {
  	private static long num = 0;
  	public static void main(String[] args) {
  		Thread t = new Thread() {
  			@Override
  			public void run() {
  				// TODO Auto-generated method stub
  				for(int i=0;i<1000;i++) {
  					num++;
  				}
  			}
  		};
  		t.start();
  		
  		System.out.println(num);
  	}
  }
  ```

  结果为0，我们通过一个线程来给我计算，结果为0，对我来说有暖用。

  一个在主线程，一个在t线程中，t线程计算，主线程显示，那么我们就需要让主线程等待子线程，所以修改如下：他是让主线程等待，子线程也到这里了一起执行下去。

  ```
  		};
  		t.start();
  		t.join();//他在主线程【阻塞当前线程，等待子线程】
  		System.out.println(num);
  	}
  ```

  它的实现是通过wait来实现的，被等待的线程在退出前会notifyAll,通知所有的等待线程进行执行。

  - yield这个有意思，想起来小时候，哥哥和我玩，把饼干放在桌子上，然后我要去取，他就赶紧拿到手上。哈哈哈哈。

    yield将cpu释放，然后自己再去抢夺。