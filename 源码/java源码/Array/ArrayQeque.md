## ArrayQeque

### 介绍

- 线程不安全，不允许有null元素
- 动态数组双向循环
- 继承AbstractCollection，实现Dueue,, Cloneable, Serializable接口
- 在单端操作可以作为栈，双端操作可以作为队列
- 栈比stack 块，做队列对LinkList块
- 主要的插入移除方法为addFirst、addLast、pollFirst、pollLast，实现了Deque接口的方法，
  其他方法都是根据这些派生的。
- 循环队列具体实现细节：
  1. head指向第一个元素
  2. tail指向最后一个元素的后一个位置
  3. head == tail为空，add操作时head == nail队满自动扩容
  4. head < tail，下标区间[head,tail - 1]
  5. head > tail，下标区间[head,elements.length()-1] + [0, tail-1]

### 字段和常量

数组中元素是有序的，不允许存放为null的数据，容量大小为2的幂次项，数组是不会放满的，除非是调用了doubleCapcity，防止收尾指针相遇，保证非null元素。

```
transient Object[] elements; //包访问权限
```

初始化的空间的大小为8,它的大小一般为2的幂次项。

```
//最小的初始化容量。必须是2的幂，这里是8.
private static final int MIN_INITIAL_CAPACITY = 8;
```

首尾指针

```
//首尾指针
transient int head;
transient int tail;
```

**扩展：**

java语言的关键字，变量修饰符，如果用transient声明一个实例变量，当对象存储时，它的值不需要维持。换句话来说就是，用transient关键字标记的成员变量不参与序列化过程。 



### 构造函数

```java
  //初始化一个空的队列，大小是16
  public ArrayDeque() {
      elements = new Object[16];
  }
  /**
   * Constructs an empty array deque with an initial capacity
   * sufficient to hold the specified number of elements.
   *
   * @param numElements  lower bound on initial capacity of the deque
   */
   //自己指定大小，但是为2的幂次数，这里需要机型计算   
   public ArrayDeque(int numElements) {
      allocateElements(numElements);
  }
	//也可以直接的传入一个集合
    public ArrayDeque(Collection<? extends E> c) {
        allocateElements(c.size());
        addAll(c);
    }
```

### Size计算

```java
   private void allocateElements(int numElements) {
      int initialCapacity = MIN_INITIAL_CAPACITY;
      // Find the best power of two to hold elements.
      // Tests "<=" because arrays aren't kept full.
       如果给的参数为大于初始化最小参数，那么就会进行一系列的计算，然后将其后每一位都为1，然后在加上个1，最高位变为1，其他变为0.
      if (numElements >= initialCapacity) {
            initialCapacity = numElements;
            initialCapacity |= (initialCapacity >>>  1);
            initialCapacity |= (initialCapacity >>>  2);
            initialCapacity |= (initialCapacity >>>  4);
            initialCapacity |= (initialCapacity >>>  8);
            initialCapacity |= (initialCapacity >>> 16);
            initialCapacity++;

            if (initialCapacity < 0)   // Too many elements, must back off
                initialCapacity >>>= 1;// Good luck allocating 2 ^ 30 elements
        }
        elements = new Object[initialCapacity];
    }

    /**
     * Doubles the capacity of this deque.  Call only when full, i.e.,
     * when head and tail have wrapped around to become equal.
     */
	求出右边的数据的大小，然后将其参数扩大一倍，（当头等于尾的时候），将参数复制到后面的数组里面，
    private void doubleCapacity() {
        assert head == tail;
        int p = head;
        int n = elements.length;
        int r = n - p; // number of elements to the right of p
        int newCapacity = n << 1;
        if (newCapacity < 0)
            throw new IllegalStateException("Sorry, deque too big");
        Object[] a = new Object[newCapacity];
        System.arraycopy(elements, p, a, 0, r);
        System.arraycopy(elements, 0, a, r, p);
        elements = a;
        head = 0;
        tail = n;
    }
```





























