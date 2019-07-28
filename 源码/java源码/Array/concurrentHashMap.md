### 介绍
```
    线程安全，分段锁，效率比较高
    比HashTable的粒度更加的细

```
### 常量
```

    数组的最大长度为2>>30,前两位用于32的hash
    because the top two bits of 32bit hash fields
    private static final int MAXIMUM_CAPACITY = 1 << 30;

    默认值的初始大小为16，他必须是2的倍数，最大为MAXIMUM_CAPACITY
    The default initial table capacity.  Must be a power of 2 
    and at most MAXIMUM_CAPACITY.
    private static final int DEFAULT_CAPACITY = 16;

    数组的最大大小Integer.MAX_VALUE-8;
     The largest possible (non-power of two) array size.
     Needed by toArray and related methods.
    static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    默认的并发级数，已经不适应，为了兼容之间的版本
     The default concurrency level for this table. Unused but
     defined for compatibility with previous versions of this class.
    private static final int DEFAULT_CONCURRENCY_LEVEL = 16;

    负载因子
    private static final float LOAD_FACTOR = 0.75f;

    将链表转换为红黑树
    static final int TREEIFY_THRESHOLD = 8;

    红黑树转换为链表，在扩容的时候，才会发生。
    static final int UNTREEIFY_THRESHOLD = 6;

    树化的最小容量
    static final int MIN_TREEIFY_CAPACITY = 64;

    // 进行树化的最小容量，防止在调整容量和形态时发生冲突
    static final int MIN_TREEIFY_CAPACITY = 64;

    // 作为下界避免遇到过多的内存争用
    private static final int MIN_TRANSFER_STRIDE = 16;

    // 用于sizeCtl产生标记的bit数量
    private static int RESIZE_STAMP_BITS = 16;

    // 可帮助调整的最大线程数
    private static final int MAX_RESIZERS = (1 << (32 - RESIZE_STAMP_BITS)) - 1;

    // sizeCtl移位大小标记
    private static final int RESIZE_STAMP_SHIFT = 32 - RESIZE_STAMP_BITS;

    // 转移的hash
    static final int MOVED     = -1; 

    // 树根的hash
    static final int TREEBIN   = -2; 

    // ReservationNode的hash
    static final int RESERVED  = -3; 

    // 可用普通节点的hash
    static final int HASH_BITS = 0x7fffffff; 

    // 当前cpu可用的数量
    static final int NCPU = Runtime.getRuntime().availableProcessors();

```
### put
```
    public V put(K key,V value){
        return putVal(key,value,false);
    }
    返回的是加入的值
    基本实现步骤：
        （1）如果key或者value为null,那么就直接抛出空指针异常
        （2）求出key的hash值，二次hash
        （3）执行死循环直到结束
            ·判断是否初始化，未初始化就执行初始化
            ·判断这个节点是否为空，为空的时候，就表示没有发生碰撞，那就直接通过CAS进行存储，操作结束就退出
            ·如果节点不为null，那就先判断是否在扩容，如果扩容，就执行帮助扩容【就说明下面拉链了】
            ·对这个节点进行加锁【双层锁】，进行死循环遍历，判断hash值是否相同，如果相关，那么就水命冲突了，然后将老的值返回，将新值赋值给老值。不相同就开始查找下一个节点。如果下一个为null的时候，就会进行实例化。
            ·如果说是属性结果，当属性结果赋值成功就设置旧值。

            最后判断是否需要将链表转成红黑树

```
### get
```

    获取元素最开始先进行hash求出hash值。
    当所有节点不为null的时候，并可以找到相应节点的时候，进行操作，否则直接返回null.
    获取头结点，判断hash值是否相同，如果相同在判断key是否相同
    为树的时候，通过树进行查找，否则就进行遍历查找。



[目录](https://github.com/wangwangla/biji/tree/master/README.md)