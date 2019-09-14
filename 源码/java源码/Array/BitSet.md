### 介绍

    可以看做是Vector，容量可以动态改变的
    实现了serializable接口和cloneable
    里面的默认值都是false
    不属于集合只是和List比较像
    线程不安全

### 常量

每个bit都是64位，并且每一个位都是1

```
    private final static int ADDRESS_BITS_PER_WORD = 6;
    private final static int BITS_PER_WORD = 1 << ADDRESS_BITS_PER_WORD;
    private final static int BIT_INDEX_MASK = BITS_PER_WORD - 1;

```





[目录](https://github.com/wangwangla/biji/tree/master/README.md)