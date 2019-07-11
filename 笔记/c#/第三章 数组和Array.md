[TOC]



## 数组

**定义：**数组是内存中一块连续区域，用于存放多个相同类型的数据。数组中的每个数据使用相同的数组名，但是使用不同的唯一标识。

#### 数组的定义

1.定义数组：

```
float[]  scores ; 
```

2.数组初始化

- 静态初始化　

  数据类型[]  数组名  =  { 值1 , 值2，....值n }

  ```
  float[]  scores = { 60.5f,  80.5f, 70.0f, 65.5f, 90.0f } ; 
  ```

  

- 动态初始化

  ```
  　int[]   ages   =   new  int[5] { 20 , 22 , 24 , 26 , 30 } ;
  ```

  错误的写法：大括号中有4个元素，但中括号中定义时长度是5，两处不一致，所以不正确。

  ```
  int[] ages = new int[5] { 20 , 22 , 24 , 26 } ; 
  ```

#### 数组的操作

1.获取长度

```
scores.Length
```

2.数组的遍历

```
 int[]  ages  =  { 20, 21, 22, 25 } ; 
 for ( int  i = 0 ;  i < ages.Length ;  i++ )
 {
	Console.WriteLine (  ages[i] ) ; 
 }
```

3.foreach

```
foreach 是一种更简单更明了的读取数组元素的语句。
特点：
    --用于读取容器类数据（数组、集合等）
    -- 适合一次性读取全部元素
    --不能修改元素
    
 基本语法：
 	foreach(  数据类型   变量名   in    数组名) 
    {
   	    变量名此时表示的就是数组中的每一个元素 
   	}
```



4,数组的长度

```
 最后一个元素的下标为：
　　　　数组长度 - 1 ，即：数组名.Length - 1 
```



## Array

Arrays 类提供了一系列快速操作数组的方法，如：排序、反转等操作，

- [ ] 复制数组元素：Array.Copy ( )   数组名.CopyOf( )
- [ ] 清除数组元素：Array.Clear( )
- [ ] 反转数组元素：Array.Reverse( )
- [ ] 数组元素排序：Array.Sort( )
- [ ] 克隆数组元素：Array.Clone( )
- [ ] 查找数组元素：Array.IndexOf( )

#### 数组复制

1.Array.copy(原，目标，数组长度);

![1555131242218](c#笔记图片/1555131242218.png)

![1555131448192](c#笔记图片/1555131448192.png)

#### 数组排序

1.数组排序：数值型数组排序时，默认按照数字从小到大升序排序

![1555131536945](c#笔记图片/1555131536945.png)

2.排序算法

- 冒泡

  ![1555131586568](c#笔记图片/1555131586568.png)

  

- 选择排序

  ​	![1555131666324](c#笔记图片/1555131666324.png)

  



## 二维数组

```
string[,] array01 = new  string[3,2];
```

-    -- 初始化 + 赋值

  ```
  int[,] array02 = new int[,]{ {1,2},{3,4} };
  ```

  

- 二维数组取值

  ​	![1555131858055](c#笔记图片/1555131858055.png)

--------

#### 数组代码分析

1.数组的定义

```
   static void Main1(string[] args)
   {
            //声明
            int[] array;
            //初始化
            array = new int[5];
            //赋值
            array[0] = 1;
            array[1] = 2;
            array[3] = 4;

            //显示：命名空间.类名
            //Console.WriteLine(array);

            //读取数组所有元素
            //array.Length  表示数组长度(元素总数)  5
            for (int i = 0; i < array.Length; i++)
            {
                //Console.WriteLine(array[i]);
                array[i] = 0;
            }
            //倒序获取所有元素
            //4   3   2  1 0 
            for (int i = array.Length - 1; i >= 0; i--)
            {
                Console.WriteLine(array[i]);
            }

            //依次获取数组全部元素
            //优点：使用简洁。方便
            /*foreach (元素类型 变量名 in 数组名)
            { 
                 变量名 就是 数组每个元素
            }*/

            foreach (int element in array)
            {
                Console.WriteLine(element);
                //element = 0;  不能修改数组元素
            }

            //练习1：在控制台中录入学生成绩：float[]
            //要求：成绩范围0--100
            //"请输入学生总数："
            //"请输入第1个学生成绩："
            //"输入的成绩有误"

            //练习2：计算数组元素最大值float[]
        }
```

2.数组定义+赋值

```
 static void Main2()
        {
            float[] scoreArray = CreateScoreArray();

            float max = GetMax(scoreArray);

            Console.WriteLine("最高分为：" + max);

            //数组写法1：声明 + 初始化 
            int[] arr01 = new int[10];

            //数组写法2：初始化 +  赋值
            int[] arr02 = new int[3] { 1, 2, 3 };

            //数组写法3：声明 + 初始化 +  赋值
            int[] arr03 = { 1, 2, 3 };

            PrintArray(new int[3] { 1, 2, 3 });
```

3.排序

```
   private static float[] CreateScoreArray()
        {
            Console.WriteLine("请输入学生总数：");
            int count = int.Parse(Console.ReadLine());
            float[] scoreArray = new float[count];

            for (int i = 0; i < count; )
            {
                Console.WriteLine("请输入第{0}个学生成绩：", i + 1);
                float score = float.Parse(Console.ReadLine());
                if (score >= 0 && score <= 100)
                    scoreArray[i++] = score;
                else
                    Console.WriteLine("成绩输入有误");
            }

            return scoreArray;
        }
```

