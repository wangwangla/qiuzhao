[TOC]



### 介绍

java.util中的工具类，提供数组相关的常用操作，排序、比较、填充、二分查找等功能

based on jdk8

### 常量和变量

```java
    （1）并行排序的最小数组长度，小于这个参数就不进行划分数组，数组长度较小会导致任务竞争，而导致内存效率低
        private static final int MIN_ARRAY_SORT_GRAN = 1 << 13;
    （2）小于这个参数，就使用插入排序
        private static final int INSERTIONSORT_THRESHOLD = 7;

```
### 范围检查

```
    1.私有方法，检查是否越界
    /**
     * 
     * @param arrayLength  数组的长度
     * @param fromIndex  数组的开始位置
     * @param toIndex   到达的位置
     */
    private static void rangeCheck(int arrayLength, int fromIndex, int toIndex) 
    （1）实现
        如果开始大于介绍的大小，就会抛出异常
        如果开始坐标小于0，就会抛出异常
        如果结束的位置大于总的长度，也会抛出异常


```
### 排序

```
    对于int[]、byte[]、long[]等基本类型数组的排序，使用DualPivotQuicksort类进行排序，可选范围。

    注意这个类对改动了双轴快排的策略，使用了其他的排序方法,查看其源码可以知道还使用了计数排序、插入排序、归并排序。很多会导致其他版本快排退化到O(n^2)的数据集使用这个类仍能保证O(nlogn)

    [单轴快排和双轴快排](https://blog.csdn.net/Holmofy/article/details/71168530)

-    快排的思想是分治，方法是递归
-    单轴快排只有一个划分点，对点两侧的区间进行递归
-    双轴快排有两个划分点，将区间分为三段，效率会高一些

```
我的总结：
```
    （1）int类型的排序
        简单可以使用DualPivotQuicksort的静态方法直接的进行排序。
        ·只输入数组：就直接排序
        ·输入排序的范围：对指定快进行排序
    （2）Object的排序，主要使用legacyMergeSort()方法


```
### 并行排序

```
思想：将一个数组的排序分成几个数组的方式来实现排序，然后将其进行合并
    当数组的大小，下于最小粒度或者只有一个线程执行的时候，使用DualPivotQuicksort进行排序，他需要一个不大于原数组大小的额外数字进行排序任务。
    if (n <= MIN_ARRAY_SORT_GRAN ||(p = ForkJoinPool.getCommonPoolParallelism()) == 1)
    也可以使用一个数组，那就对整个数组进行执行，也可以指定位置
```
### equals&deepEquals

```
    equals：
    （1）参数：数组一和数组二
    （2）步骤：
        （1）如果两个数组属于同一个对象，那么就直接返回true
        （2）两者是否有null,那么直接返回false
        （3）判断二者的长度，不一样就直接的不进行比较了。
        （4）否则:使用for循环进行比较。
    deepEquals：
    （1）先比较是否为同一个对象
    （2）二者是否有一个为null
    （3）比较长度
    （4）比较里面元素是否为同一个对象
    //深入比较，即比较多维数组。deepHashcode和deepToString也是如此。
```
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

### fill

​    用于给定值充数组，提供范围操作
    （1）fill(int[]a,int val)
     fill(object[]a,int val)
     遍历，填入

### asList

​    asList（a）这个是将数据转换为一个list,它的实现是new ArrayList(s)
    所以它起到了桥梁的作用

### binarySearch

​    二分查找，显然数组必须是有序的，如果有重复的，那么就不知道找到的是哪一个了
    函数基本都是调用自己私有的


### copyOf

```java
//给出数组新长度，拷贝到新数组中，使用System.arrayCopy实现，newLenght >= oldLength
public static short[] copyOf(short[] original, int newLength) {
    short[] copy = new short[newLength];
    System.arraycopy(original, 0, copy, 0,
                     Math.min(original.length, newLength));
    return copy;
}


```



[目录](https://github.com/wangwangla/biji/tree/master/README.md)



