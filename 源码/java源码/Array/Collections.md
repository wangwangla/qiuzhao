## Collections

- 提供了一些集合常见的方法

##### 常量和变量

- 阈值



### sort
```java
    // 排序，传入集合和比较器
    public static <T> void sort(List<T> list, Comparator<? super T> c) {
        // 直接调用list的排序方法
        list.sort(c);
    }
```

```java
    default void sort(Comparator<? super E> c) {
        // 将集合转化为数组
        Object[] a = this.toArray();
        // 调用Arrays的排序方法
        Arrays.sort(a, (Comparator) c);
        // 迭代添加排好序的元素
        ListIterator<E> i = this.listIterator();
        for (Object e : a) {
            i.next();
            i.set((E) e);
        }
    }
```

```java
    public static <T> void sort(T[] a, Comparator<? super T> c) {
        // 比较器为null时，直接调用不使用迭代器的排序方法
        if (c == null) {
            sort(a);
        } else {
            // 兼容老版本，当用户设置时，调用老的排序
            if (LegacyMergeSort.userRequested)
                legacyMergeSort(a, c);
            else
                // 使用TimSort进行排序
                TimSort.sort(a, 0, a.length, c, null, 0, 0);
        }
    }
```

```java
    static <T> void sort(T[] a, int lo, int hi, Comparator<? super T> c,
                         T[] work, int workBase, int workLen) {
        assert c != null && a != null && lo >= 0 && lo <= hi && hi <= a.length;
        // 需要排序的元素
        int nRemaining  = hi - lo;
        // 当小于2时代表不需要排序
        if (nRemaining < 2)
            return;  // Arrays of size 0 and 1 are always sorted

        // If array is small, do a "mini-TimSort" with no merges
        // 当元素数量较小时(32)，使用mini-TimSort
        if (nRemaining < MIN_MERGE) {
            // 获取连续升序元素的个数
            int initRunLen = countRunAndMakeAscending(a, lo, hi, c);
            // 二分插入排序，跳过升序元素进行排序
            binarySort(a, lo, hi, lo + initRunLen, c);
            return;
        }

        /**
         * March over the array once, left to right, finding natural runs,
         * extending short natural runs to minRun elements, and merging runs
         * to maintain stack invariant.
         */
        TimSort<T> ts = new TimSort<>(a, c, work, workBase, workLen);
        // 计算run的数量
        int minRun = minRunLength(nRemaining);
        do {
            // Identify next run
            // 获取连续递增最大数量
            int runLen = countRunAndMakeAscending(a, lo, hi, c);

            // If run is short, extend to min(minRun, nRemaining)
            // 当数量小于minRun时，先使用二分插入排序
            if (runLen < minRun) {
                int force = nRemaining <= minRun ? nRemaining : minRun;
                binarySort(a, lo, lo + force, lo + runLen, c);
                runLen = force;
            }

            // Push run onto pending-run stack, and maybe merge
            // 压入堆栈，等待合并
            ts.pushRun(lo, runLen);
            // 归并
            ts.mergeCollapse();

            // Advance to find next run
            lo += runLen;
            nRemaining -= runLen;
        } while (nRemaining != 0);

        // Merge all remaining runs to complete sort
        assert lo == hi;
        // 归并所有的run
        ts.mergeForceCollapse();
        assert ts.stackSize == 1;
    }
```

```java
    // 计算出最小的run的长度，数组大小为二次幂，则直接返回16，否则找到介于16-32之间的数
    private static int minRunLength(int n) {
        assert n >= 0;
        int r = 0;      // Becomes 1 if any 1 bits are shifted off
        while (n >= MIN_MERGE) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }
```

```java
    private static <T> void binarySort(T[] a, int lo, int hi, int start,
                                       Comparator<? super T> c) {
        assert lo <= start && start <= hi;
        if (start == lo)
            start++;
        for ( ; start < hi; start++) {
            // 选择起始元素为轴
            T pivot = a[start];

            // Set left (and right) to the index where a[start] (pivot) belongs
            int left = lo;
            int right = start;
            assert left <= right;
            /*
             * Invariants:
             *   pivot >= all in [lo, left).
             *   pivot <  all in [right, start).
             */
             // 使用二分进行缩小排序范围
            while (left < right) {
                int mid = (left + right) >>> 1;
                if (c.compare(pivot, a[mid]) < 0)
                    right = mid;
                else
                    left = mid + 1;
            }
            assert left == right;

            /*
             * The invariants still hold: pivot >= all in [lo, left) and
             * pivot < all in [left, start), so pivot belongs at left.  Note
             * that if there are elements equal to pivot, left points to the
             * first slot after them -- that's why this sort is stable.
             * Slide elements over to make room for pivot.
             */
            int n = start - left;  // The number of elements to move
            // Switch is just an optimization for arraycopy in default case
            // 移动元素
            switch (n) {
                case 2:  a[left + 2] = a[left + 1];
                case 1:  a[left + 1] = a[left];
                         break;
                default: System.arraycopy(a, left, a, left + 1, n);
            }
            // 将轴归位
            a[left] = pivot;
        }
    }
```
### frequency

计算出里面一共出现的次数，如果为null,那么就直接的计算出null的个数，如果不为null，那么就直接使用equals进行判断。

```java
    // 求list中某一元素的出现频率
    public static int frequency(Collection<?> c, Object o) {
        int result = 0;
        // 当对象为null时直接判断null的数量，否则使用equals进行判断
        if (o == null) {
            for (Object e : c)
                if (e == null)
                    result++;
        } else {
            for (Object e : c)
                if (o.equals(e))
                    result++;
        }
        return result;
    }
```
### synchronizedCollection
```java
    // 返回一个被synchronized包装的集合，方法全部使用synchronized怼代码块加锁
    public static <T> Collection<T> synchronizedCollection(Collection<T> c) {
        return new SynchronizedCollection<>(c);
    }
```
### nCopies
```java
    // 生成一个含有n个o的不可变list
    public static <T> List<T> nCopies(int n, T o) {
        // n小于0时直接抛出
        if (n < 0)
            throw new IllegalArgumentException("List length = " + n);
        // 生成一个复制list(无set方法)
        return new CopiesList<>(n, o);
    }
```
### copy
```java
    public static <T> void copy(List<? super T> dest, List<? extends T> src) {
        // 源list长度
        int srcSize = src.size();
        // 当源list长度大于目标list长度时，直接抛出
        if (srcSize > dest.size())
            throw new IndexOutOfBoundsException("Source does not fit in dest");
        // 当源list长度小于10或者两个list都是随机访问列表时
        if (srcSize < COPY_THRESHOLD ||
            (src instanceof RandomAccess && dest instanceof RandomAccess)) {
            // 遍历赋值
            for (int i=0; i<srcSize; i++)
                dest.set(i, src.get(i));
        } else {
            // 使用迭代器进行赋值
            ListIterator<? super T> di=dest.listIterator();
            ListIterator<? extends T> si=src.listIterator();
            for (int i=0; i<srcSize; i++) {
                di.next();
                di.set(si.next());
            }
        }
    }
```