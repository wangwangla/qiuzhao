### Vector 

#### 介绍

- Vector是矢量队列，继承了AbstractList,实现了List，RandomAccess,Cloneable和serialzalable接口。
  - 他是队列，支持增删改查变量功能。
  - 实现了RandomAccess，提供了随机访问
  - 实现了接口cloneable接口，实现了clone方法，可以被克隆
  - 他是线程安全的



#### 添加

- add方法将元素加到末尾

  ```java
  public synchronized boolean add(E e) {
      modCount++;
      // 确定插入后的容量没有超过最大容量，否则对Vector进行扩容
      ensureCapacityHelper(elementCount + 1);
      // 将e赋值给elementData[elementCount]，然后将Vector元素数量加1
      elementData[elementCount++] = e;
      return true;
  }
  ```

- 指定位置插入

  add(int index, E element)方法将元素插入到指定的index处

  ```java
  public void add(int index, E element) {
      // 直接调用insertElementAt方法
      insertElementAt(element, index);
  }
  ```

- 插入实现

  ```java
  public synchronized void insertElementAt(E obj, int index) {
      modCount++;
      if (index > elementCount) {
          throw new ArrayIndexOutOfBoundsException(index
                                                   + " > " + elementCount);
      }
      // 确定插入后的容量没有超过最大容量，否则对Vector进行扩容
      ensureCapacityHelper(elementCount + 1);
      // 使用System.arraycopy将elementData[index]及其之后的元素向后移动一个位置
      System.arraycopy(elementData, index, elementData, index + 1, elementCount - index);
      // 将obj赋值给elementData[index]
      elementData[index] = obj;
      // Vector元素数量加1
      elementCount++;
  }
  ```
  判断插入位置是否正确，然后进行判断是否扩容，将数据放入。 



#### 删除

指定删除位置

**步骤“**

- 判断输入是否合法
- 将原来的值取出
- 计算移动的个数
- 需要移动的时候在移动
- 将最后一个设置为null。
- 返回老值。

代码实现

```java
public synchronized E remove(int index) {
    modCount++;
    if (index >= elementCount)
        throw new ArrayIndexOutOfBoundsException(index);
    // 获取index处原先的值
    E oldValue = elementData(index);

    int numMoved = elementCount - index - 1;
    if (numMoved > 0)
        // 使用System.arraycopy将elementData[index]之后的元素向前移动一个位置
        System.arraycopy(elementData, index+1, elementData, index,
                         numMoved);
    // 将Vector最后一个元素设置为null（以便进行gc），然后将Vector元素数量减1
    elementData[--elementCount] = null;

    // 返回index处原先的值
    return oldValue;
}
```
删除指定元素的

会找到下标，然后在将其移除掉，如果有多个移除掉第一个。

#### 修改

get方法根据下标查找，底层实现是数组，作用直接使用下标取出参数就可以了。

先将原来的值取值，然后在设置值

```
public synchronized E set(int index, E element) {
    if (index >= elementCount)
        throw new ArrayIndexOutOfBoundsException(index);

    // 获取index处原先的值
    E oldValue = elementData(index);

    // 将element赋值给elementData[index]
    elementData[index] = element;
    // 返回index处原先的值
    return oldValue;
}
```

### 扩容

传入的容量大于当前的时候，进行扩容。































