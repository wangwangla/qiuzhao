1，计算机基础。
2，python历史。

	宏观上：python2 与 python3 区别：
		python2 源码不标准，混乱，重复代码太多，
		python3 统一 标准，去除重复代码。
3，python的环境。python是解释型语言、

	编译型：一次性将所有程序编译成二进制文件。
		缺点：开发效率低，不能跨平台。
		优点：运行速度快。
		：C，C++等等。
	
	解释型：当程序执行时，一行一行的解释。
		优点：开发效率高，可以跨平台。
		缺点：运行速度慢。
		:python ,php,等等。


​		
	---------------
	java是解释型还是编译型，它既是编译型，也是解释型，编译型通过编译采取jvm的字节码，在JVM上是解释执行的。所以是解释型的。
4，python的发展。
5，python种类。

运行第一个py文件：
	python3x :python 文件路径 回车
	python2x :python2 文件路径 回车
	python2 python3 区别：python2默认编码方式是ascii码
						  解决方式：在文件的首行：#-*- encoding:utf-8 -*-
						  python3 默认编码方式utf-8

6，变量。
	变量：就是将一些运算的中间结果暂存到内存中，以便后续代码调用。
	1,必须由数字，字母，下划线任意组合，且不能数字开头。
	2，不能是python中的关键字。
	['and', 'as', 'assert', 'break', 'class', 'continue',
	'def', 'del', 'elif', 'else', 'except', 'exec',
	'finally', 'for', 'from', 'global', 'if', 'import', 
	'in', 'is', 'lambda', 'not', 'or', 'pass', 'print', 
	'raise', 'return', 'try', 'while', 'with', 'yield']
	3，变量具有可描述性。
	4,不能是中文。
7，常量。
	一直不变的量。     π
	BIR_OF_CHINA = 1949
	
​	
8，注释。
方便自己方便他人理解代码。
单行注释：#
多行注释：'''被注释内容'''  """被注释内容"""


9，用户交互。input
   1，等待输入，
   2，将你输入的内容赋值给了前面变量。
   3，input出来的数据类型全部是str




10，基础数据类型初始。
数字：int 12,3,45 
    + - * / ** 
	% 取余数
	ps:type()
		字符串转化成数字：int(str) 条件：str必须是数字组成的。
		数字转化成字符串：str(int)
字符串：str，python当中凡是用引号引起来的都是字符串。
	可相加:字符串的拼接。
	可相乘：str * int
bool:布尔值。 True False。

11，if。



if 条件:
	结果

12，while。

while 条件:
	循环体
	无限循环。
	终止循环：1，改变条件，使其不成立。
			  2,break

	continue
