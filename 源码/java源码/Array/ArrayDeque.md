[介绍](#介绍)

### 介绍

deque：双端队列

- 线程不安全，队列不许有空元素

- 动态队列的双向循环队列
- 继承AbstractCollection，实现了Deque、cloneable、serializable接口

```java
public class ArrayDeque<E> extends AbstractCollection<E>implements Deque<E>, Cloneable, Serializable
```

- 双向可操作，单端操作为栈，双向操作为队列
- 用做队列时比LinkList快
- 主要的插入删除方法有addFirst/addList/pollFrist/pollList
- 循环队列的实现细节

 1.head指向第一个元素
 2.tail指向最后一个元素
 3.head==tail为空，增加操作时队满自动扩容
 4.head<tail，下标区间[head，tail-1]
 5.head>tail,下标区间[head,elements.length()-1]+[0,tail]

### 常量和字段

- Resizable-array implementation of the {@link Deque} interface.  Array

尺寸可变的数组实现了这个接口

 * deques have no capacity restrictions; they grow as necessary to support
  数组的双端队列没有空间限制  它的增长是必须要支持使用惯例
 * usage.  They are not thread-safe; in the absence of external
    他是不安全的                             增长是没有进行同步的
 * synchronization, they do not support concurrent access by multiple threads.
         它不支持多线程
 * Null elements are prohibited.  This class is likely to be faster than
    空元素他是不允许的                  它的速度很快超过栈
 * {@link Stack} when used as a stack, and faster than {@link LinkedList}
 * when used as a queue.
    超过LinkedLsit
 * <p>Most {@code ArrayDeque} operations run in amortized constant time.
    许多的x
******************************************************************************

###常量和空间

/**
     * The array in which the elements of the deque are stored.
     这个数组的中的元素是有序的
     * The capacity of the deque is the length of this array, which is
     队列的容量就是数据的大小
     * always a power of two. The array is never allowed to become
     它一直都是2的幂次            数组绝不会允许它放满
     * full, except transiently within an addX method where it is
     除了，我们使用增加方法  当调增大小的时候
     * resized (see doubleCapacity) immediately upon becoming full,
     * thus avoiding head and tail wrapping around to equal each
     我们需要避免收尾指针相等
     * other.  We also guarantee that all array cells not holding
     保证队列内啡队列元素为null
     * deque elements are always null.
     */
   
​    transient Object[] elements; // non-private to simplify nested class access

    /**
     * The index of the element at the head of the deque (which is the
     * element that would be removed by remove() or pop()); or an
     * arbitrary number equal to tail if the deque is empty.
     *头指针，当他们想要的时候，说明队列是空
     */
    ```
    transient int head;
    
     /**
     * The minimum capacity that we'll use for a newly created deque.
     * Must be a power of 2.
     *最小的容量   我们新创建一个队列的时候  他必须是2的幂次
     */
    ```
    private static final int MIN_INITIAL_CAPACITY = 8;

###构造函数和容量相关的
    两个构造函数
        一个是有参数的
            就会计算出一个大小为2的幂次方的大小。
            实现：
                先进行比较是否小于最小的个数，如果小于就直接创建最小数据大小8，如果大于，那么就使用运算，求出2的幂次作为大小
                函数中，通过非运算，将低为全都变为1，然加上1，就变为2的幂次
        一个么有参数
            没有参数的时候，会创建一个默认的16
        如果传入的是一个集合
            就会首先得到集合的数据大小，然后创建出集合，然后将和数据加入到里面

    /**
     * Constructs an empty array deque with an initial capacity
     * sufficient to hold 16 elements.
     */
    public ArrayDeque() {
        elements = new Object[16];
    }
    
    /**
     * Constructs an empty array deque with an initial capacity
     * sufficient to hold the specified number of elements.
     *
     * @param numElements  lower bound on initial capacity of the deque
     */
    public ArrayDeque(int numElements) {
        allocateElements(numElements);
    }
    
    /**
     * Constructs a deque containing the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.  (The first element returned by the collection's
     * iterator becomes the first element, or <i>front</i> of the
     * deque.)
     *
     * @param c the collection whose elements are to be placed into the deque
     * @throws NullPointerException if the specified collection is null
     */
    public ArrayDeque(Collection<? extends E> c) {
        allocateElements(c.size());
        addAll(c);
    }

扩容是双倍进行扩容的

###大小的计算
    计算是通过尾减去头和容量相与得到。
    /**
     * Returns the number of elements in this deque.
     *
     * @return the number of elements in this deque
     */
    public int size() {
        return (tail - head) & (elements.length - 1);
    }

###增加  插入
    插入一个特殊的元素在双向队列的前面，它是不可以插入null的，会抛出异常
    如果在增加完之后，发现head和tail相同，那么就双倍增加
    通过head = (head - 1) & (elements.length - 1)计算出插入的位置

    /**
     * Inserts the specified element at the front of this deque.
     *
     * @param e the element to add
     * @throws NullPointerException if the specified element is null
     */
    public void addFirst(E e) {
        if (e == null)
            throw new NullPointerException();
        elements[head = (head - 1) & (elements.length - 1)] = e;
        if (head == tail)
            doubleCapacity();
    }
    
    /**
     * Inserts the specified element at the end of this deque.
     *
     * <p>This method is equivalent to {@link #add}.
     *
     * @param e the element to add
     * @throws NullPointerException if the specified element is null
     */
    public void addLast(E e) {
        if (e == null)
            throw new NullPointerException();
        elements[tail] = e;
        if ( (tail = (tail + 1) & (elements.length - 1)) == head)
            doubleCapacity();
    }
    说白了也是使用增加到最前面，然后返回数据类型发生了变化
    /**
     * Inserts the specified element at the front of this deque.
     *
     * @param e the element to add
     * @return {@code true} (as specified by {@link Deque#offerFirst})
     * @throws NullPointerException if the specified element is null
     */
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }
    
    /**
     * Inserts the specified element at the end of this deque.
     *
     * @param e the element to add
     * @return {@code true} (as specified by {@link Deque#offerLast})
     * @throws NullPointerException if the specified element is null
     */
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }




[目录](https://github.com/wangwangla/biji/tree/master/README.md)