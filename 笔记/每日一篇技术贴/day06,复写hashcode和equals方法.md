# 复写hashcode和equals

​	首先说一下，equals比较的是什么，是对象？是地址中的内容？答案是肯定的，比较的是里面的内容。hashcode是什么，是一个数据计算出来的一个数据。

​	上面是自己的理解，准不准确不知道，目前是这样认为的，下来有新的认识，在来更改。今天的主题是为什么要重写hashcode和equals方法。

	## hash计算的好处

​	一个长度为n的数组或者集合，我们要得到一个数据，那么我们需要将其中的数据下标得到，然后获取到，如果是无序，那么就将其参数依次的进行遍历查找，那么查找的次数就是n/2.

​	hash的求法：加入一个长度为11的线性表，如果我们需要将一个数据插入一个位置，我们先计算出索引，然后将数据放入索引，这样的好处就是，我们一次计算出，就可以将数据直接的写入一个位置中去，速度几乎为O(1)。

​	问题来了，hash冲突怎么办，那么将会在这个索引位置下将数据进行拉链，这样一个索引下可以存储多个数据，查找也是这个步骤，计算出key的索引，将索引中的数据取出，如果是个链表那么就循环链表的方式，将链表中的数据遍历出来。

	## 为什么要重写equals和hashcode方法

​	为了回答这个问题，那么先回答一下，不复写的后果，如果不复写，那么我们将在一个HashMap中存入两个对象，一个是instance01，我们不复写hashcode和equals方法，在instance01和instance02中都存入相同的数据，使用instance01存入数据，使用02获取数据，这个时候没有复写这两个方法，那么就会出现获取不到参数，因为，在key中存储的是两个地址，两个对象，所以两个地址，没有任何的问题，使用一个存，一个取，当然是不可以的，这个也是没有任何异议的。

​	所以存入对象的时候需要读写Object的默认方法。

	## 面试问题说明

​	



--------

那篇文章比较长，我只是写出了我的感受和我想要得到的东西。