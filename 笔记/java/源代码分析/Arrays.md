## Arrays

### 常量&变量 

```java
并行排序的最小数组，小的数组会导致竞争内存造成效率降低。
private static final int MIN_ARRAY_SORT_GRAN = 1 << 13; 
```

    ### 方法

```java
私有方法观察是否越界
    /**
    	检查代码的开始index和最终的index是否则范围内，如果不在就抛出异常。
    	
    	实现思路：
    		- 检测 fromIndex和toIndex的大小关系
    		- 检测formIndex是否小于0
    		- 检测toIndex是否大于数组的长度
     * Checks that {@code fromIndex} and {@code toIndex} are in
     * the range and throws an exception if they aren't.
     */
    private static void rangeCheck(int arrayLength, int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException(
                    "fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
        }
        if (fromIndex < 0) {
            throw new ArrayIndexOutOfBoundsException(fromIndex);
        }
        if (toIndex > arrayLength) {
            throw new ArrayIndexOutOfBoundsException(toIndex);
        }
    }
```

### 排序

```java
    /**
	- 快排的思想是分治，方法是递归
	- 单轴快排只有一个划分点，对点两侧的区间进行递归
	- 双轴快排有两个划分点，将区间分为三段，效率会高一些
     */
    public static void sort(int[] a) {
        DualPivotQuicksort.sort(a, 0, a.length - 1, null, 0, 0);
    }
```



 ## 并排

对基本类型的并行排序，以int[]为例。提供范围排序 

```java
排序将数组分为几个子数组分布排序然后合并排序，当数据的长度达到最下粒子，或者设定的最小粒子，使用类似于Arrays.sort()方法来排序，否则使用FJint进行排序，它使用一个比自身小的数组来存储。

public static void parallelSort(int[] a) {
    int n = a.length, p, g;
    //如果数组长度小于分组的最小粒度或者只有一个执行线程，使用DualPivotQuicksort
    if (n <= MIN_ARRAY_SORT_GRAN ||
        (p = ForkJoinPool.getCommonPoolParallelism()) == 1)
        DualPivotQuicksort.sort(a, 0, n - 1, null, 0, 0);
    else
        //g表示粒度，参数4、5、6分别为排序数组开始位置，需要排序的长度和额外空间的开始位置
        //g = n / (p << 2)不可小于最小粒度,否则使用最小粒度
        new ArraysParallelSortHelpers.FJInt.Sorter
            (null, a, new int[n], 0, n, 0,
             ((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
             MIN_ARRAY_SORT_GRAN : g).invoke();
}
```

它除此之外还有一个为指定的数组位置进行排序,上面提到过，将数据分为两个部分来进行排序，小于粒子和大于某个粒子，如果是整个数组，那么就是有整个数组长度，如果是数组的一端就是有一段作为数数组的粒子的大小。

````java
public static void parallelSort(int[] a, int fromIndex, int toIndex) {
    rangeCheck(a.length, fromIndex, toIndex);
    int n = toIndex - fromIndex, p, g;
    if (n <= MIN_ARRAY_SORT_GRAN ||
        (p = ForkJoinPool.getCommonPoolParallelism()) == 1)
        DualPivotQuicksort.sort(a, fromIndex, toIndex - 1, null, 0, 0);
    else
        new ArraysParallelSortHelpers.FJInt.Sorter
            (null, a, new int[n], fromIndex, n, 0,
             ((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
             MIN_ARRAY_SORT_GRAN : g).invoke();
}
````

可以清楚的看到，如果是int类型，那么就可以使用，之前的排序算法，对于Object等其它的类型TimSort.sort(a, 0, n, NaturalOrder.INSTANCE, null, 0, 0); 进行排序。



### 相等和深度相等

```java
/**
比较相等
	- 相同对象      	  true
	- 为null       		false
	- 长度不相同    		 false
    	- 不相同比较每个元素
    		-相同  		true
    		-不相同 	   false
 
    
*/
public static boolean equals(int[] a, int[] a2) {
    if (a==a2)
        return true;
    //存在null，则false
    if (a==null || a2==null)
        return false;

    int length = a.length;
    if (a2.length != length)
        return false;
    //依次比较
    for (int i=0; i<length; i++)
        if (a[i] != a2[i])
            return false;
    return true;
}
```

上面的方法在short、char等简单的里面都是可以使用的，但是在object就不可以了，Object如下：

```java
for (int i=0; i<length; i++) {
   Object o1 = a[i];
   Object o2 = a2[i];
   if (!(o1==null ? o2==null : o1.equals(o2)))
    return false;
}
这里使用equals
```

深度比较，用在比较多维度的数组中

```java
//深入比较，即比较多维数组。deepHashcode和deepToString也是如此。
public static boolean deepEquals(Object[] a1, Object[] a2) {
    if (a1 == a2)
        return true;
    if (a1 == null || a2==null)
        return false;
    int length = a1.length;

    //长度不同直接false
    if (a2.length != length)
        return false;

    for (int i = 0; i < length; i++) {
        Object e1 = a1[i];
        Object e2 = a2[i];

        if (e1 == e2)
            continue;
        if (e1 == null)
            return false;

        //递归比较，记录是否相等
        boolean eq = deepEquals0(e1, e2);

        if (!eq)
            return false;
    }
    return true;
}
```



### 填充

使用给定的值，填充数组

```java
public static void fill(int[] a, int val) {
    for (int i = 0, len = a.length; i < len; i++)
        a[i] = val;
}
数组传进啦，将参数循环进数组中
```

还可以给指定范围内填充参数

```java
    public static void fill(long[] a, int fromIndex, int toIndex, long val) {
        rangeCheck(a.length, fromIndex, toIndex);
        for (int i = fromIndex; i < toIndex; i++)
            a[i] = val;
    }
使用指定的数组位置填充参数，因为是给值，所以这个就会有范围效验。
```

### 数组和集合的桥梁

```java
@SafeVarargs
@SuppressWarnings("varargs")
public static <T> List<T> asList(T... a) {
    return new ArrayList<>(a);
}
```

将一个任意类型参数传入作为一个list返回

### 二分查找

```
private static int binarySearch0(int[] a, int fromIndex, int toIndex,
                                 int key) {
    int low = fromIndex;
    int high = toIndex - 1;

    while (low <= high) {
        int mid = (low + high) >>> 1;
        int midVal = a[mid];

        if (midVal < key)
            low = mid + 1;
        else if (midVal > key)
            high = mid - 1;
        else
            return mid; // 找到了
    }
    return -(low + 1);  // 没找着，保证为负
}
二分很明显：
	需要有序，二分查找，就是将数组分半，
	分一半，取中间
		如果大：就去前一半找，如果小就去后一半找。
```



### 数组复制

```
使用System.arrayCopy实现,先创建数组大小，然后使用使用System.arrayCopy进行复制。
```









-----------------------------

总结：

​	Arrays检测数组是否合法的操作，比较数组的角标是否越界，使用\- 快排的思想是分治，方法是递归，他听多种数据类型的排序方式，它还有一个常量，如果大于这个常量，使用的是Arrays.sort() ，如果大于这个算法需要一个不大于原数组大小的额外空间，使用ForkJoin common pool ForkJoinPool#commonPool()）来执行并行的排序任务。 第三个就是相等判断，先进行直接判断，在进行null判断，在进行长度判断， 在进行每个参数判断，最后得到结果，这里需要注意，==和equals的区别，这个在判断一般数据类型和Object的时候有所区别。提供了数组的填充，并且可以设置范围。提供数组和集合的桥梁，以及二分查找和最后的一个数组复制，数组的复制使用的是System.copy方法进行的。