# OpenGLES程序详解

- openGLES 2.0开始支持Android和iso设备的，并且它具有向后兼容性。
- 它是一个桌面操作系统使用的跨平台的，广为接受的3D API.
- 在OpenGLES中除非加载了一个有效的顶点着色器和片段着色器才会正确的绘制出一个图像。顶点着色器和片段着色器是必须的。

## 创建着色器

### 编译和加载着色器

1.创建着色器：

```
glCreateShader(type);
```

2.加载源码

```
glShaderSource（shaderId,1,file,null）
```

3.编译

```
glCompileShader(shader);
```

4.检查错误：

如果发生错误，就将着色器删除掉。

### 创建程序链接

1、创建程序

```
glCreateProgram();
```

2.加载着色器

```
glAttachShader(programId,vtShader);
glAttachShader(programId,fgShader);
```

3.链接

```
glLinkProgram(programId)
```

4.使用程序

```
glUserProgram(programId);
```

## 视口和清除缓存

我们是EGL创建了渲染表面，并初始化和加载了着色器，glViewport(0,0,width,hight)指定创建的位置以及窗口的大小，视口就是现实图像的位置和大小，

```
glViewport(x,y,width,height);
```

清缓存

```
glClear(GL_COLOR_BUFFER_BIT);清掉缓存，使用glClearColor作为渲染颜色。
```



## 画图，清缓存

​	在执行的过程中，会有许多缓存，比如颜色、深度和摸版等，所以在画图之前，需要清除缓存。

## 加载几何图形

清除缓存以及将底部颜色设置之后，就可以加载图形，画出图元了。,顶点位置需要加载到GL中，并将其连接到属性的位置中，设置完毕之后，就可以设置图元了。

## 后台缓冲区

​	如何真正的显示出缓冲区中的内容，首先了解一下双缓存区，屏幕可见的缓冲区由一个二维数组组成的，显示的图像就是在绘制时简单的更新可见帧缓冲区的像素数据。但是这样会存在问题，就会会看到伪像。so，使用双缓存，一个是前台显示一个是后台渲染缓存，所有的渲染都在后台中执行的，当渲染完成的时候，缓存区被交换到前台上



## EGL

​	它是与平台无关的API，用于管理图像表面（窗体的一种类型），他提供了：与原生窗口通信、查询绘图表面的可用类型和配置，创建绘图表面，在OpenGLEs和其他的API之间同步渲染，管理贴图。

- 提供OpenGLES与计算机原生系统的之间的一个结合，在可以确定绘制的类型，他必须和窗口进行通信。
- 染赫使EGL的执行操作之前，都需要本地和EGL进行显示连接。



## 着色器和程序

​	创建对象，我们需要着色器和程序对象，着色器的加载编译、连接类似于c语言，编译程序，链接程序。着色器得到源代码，将其编译成一个目标文件--------->连接到一个对象，在连接阶段，就会生成目标机器语言。

- 创建着色器对象
- 加载代码到着色器
- 编译着色器
- 创建一个程序对象
- 着色器连接到程序

[^]: 



### 创建一个着色器API介绍

##### 创建着色器：

- GLuint glCreateShader(String type);

  - type：可以是片元，也可以是片段
  - 参数：GL_VERTEX_SHADER      GL_FRAGMENT_SHADER
  - 根据传参类型，创建出相应类型的着色器对象


##### 删除着色器

- void glDeleteShader（int shader）;

  - shader:着色器句柄，
  - 如果连接到一个程序对象，执行方法**不会理解执行**，等待不在连接任何程序对象的时候，才会删除。


##### 创建着色器代码

- void glShaderSource(shader,count,string,length)

  - 着色器
  - 字符串数量
  - 数组指针
  - 字符串长度

  在Android上也可以直接使用glShaderSource(shader,source);

##### 编译

- void glCompileShader(shader)

  - shader：着色器句柄
  - 将已经保存在着色器对象中的着色器源代码进行编译。

##### 查看着色器信息

- void glGetShderiv(shader,pname,*param)

  - 着色器

  - 执行 的操作

    | GL_COMPILE_STATUS       | FL_TRUE/GL_FALSE         |
    | ----------------------- | ------------------------ |
    | GL_DELETE_STATUS        | 标记是否为删除           |
    | GL_INFO_LOG_LENGTH      | 检查日志长度             |
    | GL_SHADER_SOURCE_LENGTH | 返回着色器源码代码的长度 |
    | GL_SHADER_TYPE          | 返回着色器类型           |

  - 存储的位置

### 创建和链接程序

着色器已准备好了，下来我们需要创建一个程序，将两个着色器对象连接起来。形成一个可执行的程序。

##### 创建一个程序

- GLuint glCreateProgram();
  - 仅仅返回一个执行新程序对象的句柄。


##### 删除程序

- void glDeleteProgram(proid);
  - 一个参数就是句柄


##### 将着色器连接，每一个程序对应一个顶点和片段。

- void glAttachShader(proid,shader);
  - 程序句柄
  - 着色器

  **这个在连接的过程中，着色器不一定要编译，不一定要有代码，唯一的要求，就是只能有一个片元和片段。，可以在任何时候进行连接**

##### 断开连接

- void glDetaShader(program,shader)

  - 指向句柄

  - 指向断开连接的着色器

    **到此准备工作做完了**

##### 最后的链接

- void glLinkProgram(proId)

  - 此时生成最终的可执行程序。
  - 连接程序检查各种对象数量，确保成功。
  - 确保输出变量和输入变量的值的类型相同。
  - 连接就是生成最终在硬件上执行的时候。

##### 使用程序

- void glUseProgram(projectId)

  - 使用程序



### 统一变量和属性

​	连接之后，就可以在对象上执行许多查询，

- 找出活动统一变量，存储应用程序通过OpenGLES传输给着色器只读的常数值变量。

- 统一变量分为两类：

  - 命名统一变量块：值有统一变量缓存区对象支持，【被分配一块统一变量块索引】
  - 默认统一变量块：没有名称或者统一块索引。

  **统一变量在顶点或者片段中均有，那么类型需要一致。值也需要相同，连接阶段，连接程序未程序中与默认统一变量块的相关活动指定位置。位置是加载的标识符 ，还为命名变量块相关的活动变量分配偏移和跨距。**

- 查询活动统一变量的列表，首先要用GL_ACTIVE_UNIFORMS参数调用glGetProgramiv,可以获取程序中活动同一变量的数量，这个数据主要包含了命名统一变量，默认统一变量以及着色器代码中内建的统一变量。如果被程序调用就认为是活动的，如果创建了一个变量但是一直没有使用，连接程序的似乎会将其去掉。

  使用API:gLGetActiveUniform(Unint program,GLuint index,GLsizei bufsize,GLsizer *length)可以获取所有统一变量的属性的名称和类

- 找出统一变量在程序的位置，这个位置用于后续加载统一变量值的后续调用。

  API:glGetUniformLocation(GLuint program,const GLchar *name); 这个必须是活动的。

- 位置获取之后，给他们设置统一变量的值。

  glUniform1f(Glint location,GLfloat x);

  glUniform1fv(Glint location,GLsizei count ,const GLFloat * value);

  glUniform1i(Glint location,GLfloat x);

  ……

- 统一变量缓存区

  - 统一变量数据，程序之间可以共享变量
  - 在调用的时候降低API开销、

### 获取和设置属性值



### 着色器编译

- 着色器代码通常解析为某种中间的表现形式，将其转换为机器可以值性的机器指令，这个主要是CPU做的 

### 二进制代码：

- 他是完全编译和连接的程序的二进制表现形式，他可以避免在线进行编译。
- 二进制取决于供应商，程序可移植性差，

这个可以使用GL_LINK_STATUS进行检测，如果不可以用，那么就在编译一次



## 顶点属性、顶点数组和缓存区对象

指定顶点属性和数据的方法，讨论顶点属性的概念，如何指定它们支持的数据类型，如何绑定顶点属性以用于顶点着色器。可以使用数组进行指定。

#### 顶点着色器属性

​	顶点属性使用一个顶点数组对每个顶点进行指定，也可以将一个常量用于图元的所有顶点。所有的必须支持16个顶点的属性。应用程序可以查询特定实现支持顶点属性的准确值

​	通常使用它来加载指定的通用顶点属性

​	API:glVertexAttrib1f(GLuint index,GLfloat x);

```
(x,0.0,0.0,1.0)
```



​	API:glVertexAttrib2f(GLuint index,GLfloat x,GLfloat y);

```
(x,y,0.0,1.0)
```



​	API:glVertexAttrib3f(GLuint index,GLfloat x,GLfloat y,GLfloat z);

```
(x,y,z,1.0)
```



​	API:glVertexAttrib4f(GLuint index,GLfloat x,GLfloat y,GLfloat z,GLfloat w);

#### 顶点数组

指定每个顶点的属性，保存应用程序地址空间，

API:

```
glVertexAttribPointer(index,size,type,normalized,stride,*ptr)
index：顶点属性索引
size:为索引引用的ding单属性指定分量数量，
type:数据格式  GL_BYTE    GLUNSIGNED)BYTE   GL_SHORT    GL_UUNSIGNED_SHORT   GL_INT  GL_UNSIGNED_INT  
normalized:表示非浮点数据格式类型在浮点时是否需要格式化，偏移
ptr:准备的数据数组
```

- 一个缓冲区中存储顶点属性 的叫属性数组
- 单独缓存区保存单个属性的叫数组结结构



属性数组和数组结构：

​	属性数组：将所有的数据放入一个数组中，然后通过偏移进行获取，效率高。

​	数组结构：就是一种属性一个数组，那么在设置值的时候，写入的数组的地址即可。



那种效率最高呢

一般是结果数组比较的高，每个顶点的属性可以顺序的读取，so高效

数组结构，每次将值进行偏移 。





​	在使用的过程中通过type指定顶点属性书的格式，不仅影响效率，也会影响它的整体性能，，数据量越小，所占带宽越小，

​	建议使用数据类型

```
尽可能是GL_HALF_FLOAT，一般用在纹理、法线、向量
颜色是由GL_UNSIGNED_BYTE，每个颜色4个分量
顶点位置使用GL_FLOAT
```

glVertexAttribPointer中的规范化标志如何工作

 	着色器之前，顶点存储为单精度，如果鄙视单精度会进行转换为单精度，规范化标志控制非浮点型向浮点型的转化。为假，就直接转化为浮点型，数据类型的范围都会转化为[-1.0,1.0] 或者是[0.0,1.0].

#### 常量的顶点属性和顶点数组之间的选择

​	应用程序让openGL使用常量数据或者来自于顶点数组的数据。使用GLEnableVertexAttribArray和GlDisableVertexAttribArray使用或者禁用属性数组。



#### 顶点着色器中声明顶点属性变量

​	顶点属性的定义通过数组【数组结果或者结构数组】，我们也知道顶点的属性然后设置，下来介绍，如果在顶点着色器中声明属性变量。

​	使用in来声明顶点属性，属性变量也可以选择包含一个 布局限定符，提供属性的索引。

```c++
layout(location = 0)in vec4 a_Position;
layout(location = 1)in vec4 a_color;
```

**注意：只能读，不可以修改！**



三种方法将通用的订单属性索引映射到顶点着色器中的一个属性变量名称。

- 索引可以在顶点着色器的代码中指定layout
- openGL 将通用属性索引绑定到属性名称.
- 应用程序可以将属性索引绑定到属性名称.

方式一：

```
layout(location == 1)
```

方式二：	索引绑定变量

```c++
glBindAttribLocation(program,index,name)  程序  属性索引   属性名称
```

如果重复绑定就会进行覆盖，这个可以在连接之前执行，并且可以绑定任何属性。不存在的属性就会被忽略。

方式三：变量绑定索引,首先会检查，如果已经绑定，就是要绑定，如果没有绑定，就会随意的分配一个。

```
glGetAttribLocation(program,name);
```

#### 顶点缓冲区对象

使用顶点数组，数据需要将其复制图形内存，如果将其放在图像内存中，那么将会省电和快速。缓存区对象可以在高性能的图像内存中分配和缓冲订单，并且描述图元顶点索引的元素也可以进行缓存。



一个分为两块缓存指定顶点和图元数据：数组缓存区对象和元素缓存区对象

GL_ARRAY_BUFFER标志指定数组缓存区对象，用于创建保存顶点数据的缓存区对象，GL_ELEMENT_ARRAY_BUFFER标志指定的元素数组缓存区对象用户创建图元缓存区对象



实现：

​	使用缓冲对象渲染之前，需要分配缓冲区对象并顶点数据和元素索引上传到相应的缓存区对象。

```c++
创建两个缓存区名称(未用的)
glGenBuffers(2,vboIds);
指定当前缓冲区对象
glBindBuffer(GL_ARRAY_BUFFER,vboIds[0]);
保存一个属性或者多个属性   GL_STATIC_DRAW指定那种方式访问缓存区
glBufferData(GL_ARRAY_BUFFER,numberVecs*sizeof(v_type),vertBuffer,GL_STATIC_DRAW);
```

状态

```
GL_STATIC_DRAW：数据先修改一次，尅使用多次
GL_STATIC_READ：
```



##### ----------------未结束-------------待续---------------------------------



### 图元和光栅化

图元可以使用多种函数进行绘制，glDrawArraysInstanced和glDrawElementsInstanced等命令绘制。其他颜色定，与每个顶点相关联。



### 点精灵

​	它支持点精灵:GL_POINTS,点精灵指定绘制的，一般作为点并不是正方形绘制的，实现高效渲染，它需要指定位置和半径屏幕对其的正方形，位置是正中心，半径用于寻找4个点。

```
	半径设置  gl_Point = 10；
```

点精灵的位置？？？？？？？？

gl_PointCoord:只能在绘制点精灵的时候用于片段着色器内部内建变量，使用mediump精度限定一个vec2

```
#version 300 es
precision mediump float;
uniform samplers2D a_textSprite;
layout(location=0)out vec4 outColor;
void main()
{
    outColor = texture(s_textSprite,gl_PointCoord);
}
```



### 绘制图元

​	使用绘制图像的那几个API来完成这个操作，

### 图元重启

图元重启，可以一次绘制出多个不想连接的图元，可以降低API调用，，可以是一些所以列表来重启。



### 驱动顶点

如果没有限定符，那么在图元中使用的是线性插值，如果是平滑着色，那么没有插值，所以片段着色器中只有一个点的值可用。



图元装配包括：

​	裁剪------>透视分割----->视口变换







## 顶点着色器

​	顶点着色器可以用于传统顶点的操作，通过矩阵变换，计算照明方式以方程是生成逐点颜色以及纹理

1.顶点着色器提供顶点操作的可编程方法，

2.输入输出：

- 属性
- 统一变量和统一变量缓存区
- 采样器
- 着色器程序

3.顶点着色器的输出称作为顶点着色器的输出变量，光栅化阶段为片段着色器计算变量，并作为传入。

- gl_VertexID:输入变量，用于保存顶点的整数索引
- gl_InstanceID：输入变量，用于保存绘制图元的编号
- gl_Position:输出订单位置的裁剪坐标，图元裁剪、位置变换。
- gl_PointSize()；点精灵尺寸

4.唯一的内建窗口深度范围

uniform gl_DepthRangeParameters

5.内建常量

顶点属性的最大数量

  const mediump int gl_MaxVertexAttribs=16;

  const mediump int gl_MaxVertexUniformVectors=256;  

  const mediump int gl_MaxVertexOutputVectors = 16;

  const mediump int gl_MaxVertexTextureImageUnits = 16;

  const mediump int gl_MaxCombinedTextureImageUnits = 16;

  6.精度符

   highp、out、mediump

  默认的精度符

 precision highp float;

 precision mediump float;



### 顶点着色器的案例：

##### 矩阵变换

```
#version 300 es
uniform mat4 u_MvPMatrix;
in vec4 a_Position;
in vec4 a_Color;
out vec4 v_Color;
void main()
{
    v_Color=a_Color;计算插值之后的颜色
    gl_Position = u_MvPMatrix*a_Position;  使用变换后的顶点位置和图元类型
}
```

设置了光栅化阶段使用变换后顶点位置和图元类型将图元进行光栅化为片段，对每个片段，计算插值，最后作为输出传递给片段着色器

##### 模型矩阵

​	使用矩阵对图像进行平移等操作。但是并不存在函数，所以需要自己来构建，一般步骤为：

- 初始化单位矩阵
- 将单位矩阵结合一个平移，使得物体远离观察者
- 将其进行其他操作。



##### 投影矩阵

​	在固定功能gl中，使用的是glFrustum或者openGL工具函数指定。Frustum是将其进行此





#### 顶点着色器的照明

​	通过观察直射光、点光源、聚光灯的照明方式示例。直接光就是平行的光



点光源：光照位置   xyzw

​		光照距离  xyzw

​		衰减距离 光源颜色、光照强度

## 纹理

​	3D图像渲染基本操作之一就是表面纹理，纹理有多种：2D纹理、2D纹理数组、3D纹理和立方纹理，通常使用到一个表面



### 2D纹理

​	2D纹理是openGLES中最基本和常用的纹理形式，2D纹理是一个图像数据的二维数组，一个单独的数据元素称为纹素。他可以有多种的表现个数

| 基本格式           | 纹理数据的描述                    |
| ------------------ | --------------------------------- |
| GL_RED             | 红                                |
| GL_RG              | 红、绿                            |
| GL_RGB             | 红、绿、蓝                        |
| GL_RGBA            | 红、绿、蓝、Alpha                 |
| GL_LUMINANCE       | 亮度                              |
| GL_LUMINANCE_ALPHA | 亮度，Alpha                       |
| GL_ALPHA           | Alpha                             |
| GL_DEPTH_COMPONENT | 深度                              |
| GL_DEPTH_STENCIL   | 深度、摸版                        |
| GL_RED_INTEGER     | 整数红                            |
| GL_RG_INTEGER      | 整数红、整数绿                    |
| GL_RGB_INTEGER     | 整数红、整数绿、整数蓝            |
| GL_RGBA_INTEGER    | 整数红、整数绿、整数蓝、整数Alpha |

左下角有st坐标指定。

### 立方图纹理

OpenGLES支持立方纹理，其实就是6个2D组成的纹理，虽说有很多特效，但是最常用环境贴图特效。



## 片段着色器

### 固定功能片段着色器

 在openGL ES 1.1中，使用一组有限的方程式，确定如何组合片段着色器的输入，固定管线，一般有三种：插值顶点颜色、纹理颜色、常量颜色。

- 顶点颜色：保存一个预先计算的颜色或者顶点照明计算的结果，
- 纹理颜色：来自于使用图元纹理坐标绑定的纹理读取的颜色值

1.在1.1中的组合函数

| RGB组合函数 | 方程式                              |
| ----------- | ----------------------------------- |
| REPLACE     | A                                   |
| MODULATE    | AXB                                 |
| ADD         | A+B                                 |
| ADD_SIGNED  | A+B-0.5                             |
| INTERPOLATE | AXC+BX(1-C)                         |
| SUBTRACT    | A-B                                 |
| DOT3_RGB    | 4X((A.r-0.5)X(B.r-0.5)+(A.g-0.5)……) |

从上面可以看出，它由很多函数，但是这些操作，只是使用已有的方程式，他是固定管线。

现在可以使用glsl语言进行操作，方便快捷。纹理的操作过程：采样器读取，使用一个2D纹理查找，然后纹理读取的结果从顶点着色器输入值相乘，顶点着色器将颜色传递给片段着色器。





**总结：固定管线是使用固定的方程式，所以比较的方便，但是操作局限性很大**

偏大着色器执行的操作与固定功能的设置执行的操作完全相同，纹理从一个采样器中读取，并用一个2D纹理坐标查找该值，，然后纹理读取的结果和从顶点着色器传递输入值相乘

### 精度

片段着色器没有固定的限定符，那么我们在使用的时候就需要自己声明一个。

### 着色器实现固定功能技术

openGL ES1.x和OpenGL中固定管线提供API,可以执行多重纹理、雾化、Alpha测试等，可以使用着色器实现。

#### 多重纹理

片段着色器中非常常见的操作，用于组合多个纹理贴图。在片段着色器中以不同的方式组合纹理很简单，就是采样着色器语言许多运算符合内建函数，可以轻松实现固定管线难以实现的函数方法、。

**多重纹理片段着色器**

```c++
#version 300 es
precision mediump float;
in vec2 v_textCoord;
layout(location=0)out vec4 outColor;
uniform sampler2D s_baseMap;
uniform sampler2D s_lightMap;
void main()
{
    vec4 baseColor;
    vec4 lightColor;
    baseColor = texture(s_baseMap,v_textCoord);
    lightColor = texture(s_lightMap,v_textCoord);
    outColr = baseColor * (lightColor + 0.25);
}

```

绑定数据

```
glActiveTexture(GL_TEXTURE0);
glBindTexture(GL_TEXTURE_2D,userData->baseMapTexId);
glUniformli(userData->baseMapLoc,0);

glActiveTexture(GL_TEXTURE1);
glBindTexture(GL_TEXTURE_2D,userData->lightMapTexId);
glUniformli(userData->lightMapLoc,1);
```

从上面可以看出，绑定的是纹理0和纹理1，为采样器设置数值，将采样器绑定到对于纹理上，上面是通过单一纹理坐标从两个贴图中读取。

#### 雾化

应用雾化是渲染3D场景的一种技术，在1.1中使用的是固定功能操作，使用的原因之一是：可以减少绘制距离，，并且消除突显。

**雾化几种方式**

- 进行可编程片段着色器，比局限于使用某种方程式。

  - 介绍如何用片段着色器计算线性雾化

  - 计算需要两个输入：眼睛距离以及雾化颜色

  - 还需要雾化所覆盖的最小和最大距离范围

  - 计算雾化因子，使用因子乘以颜色，然后通过片段总颜色进行线性插值，

  - 计算距离，最好在顶点中计算。

    ```
    #version 300 es
    uniform mat4 u_matViewProjection;
    uniform mat4 u_matView
    ```

    

- 



#### Alpha测试

​	3D应用程序使用常见特效之一是绘制某些片段中完全透明的图元，绘制链状非常有用，绘制珊栏需要大量图元，在纹理中存储一个遮拦值，控制那些是显示的那么些是遮拦的。

​	上面不理解呀，接着看也不一定理解

​	传统的做法是:使用alpha实现，允许一个比较测试，比较失败，就删除，通不过测试就删除。openGL ES3.0中没有Alpha测试，但是可以使用discard关键字实现相同的效果。

​	案例：

```
#version 300 es
precision mediump float;
uniform sample2D baseMap;
in vec2 v_textCoord;
layout(location=0)out vec4 outColor;
void main(void)
{
    vec4 baseColor = texture(baseMap,v_textCoord);
    if(baseColor.a<0.25)
    {
        discard;
    }
    else
    {
        outColor = baseColor;
    }
}
```



纹理是一个四通道的RGBA纹理，他会个参数进行测试， 测试通过就进行显示，如果测试没过，就不会显示。



#### 用户裁剪平面

​	所有图元根据组成视椎的6个平面进行裁剪，但是，有时候用户可能想要根据一个或者多个额外用户的裁剪平面进行裁剪，根据用户裁剪平面进行裁剪的原因是：比如渲染反射时，需要根据反射平面反转几何形状，然后将其渲染到屏幕外纹理中，渲染纹理时，需要反射屏幕裁剪几何形状。

​	1.1中用户裁剪需要通过平面方程式提供给API，裁剪将会自动处理。2.0也可以，但是必须在着色器中进行处理，还是使用上一节中的discard。

​	计算是否需裁剪，计算一个参数如果小于0，那么就需要裁剪，大于0 ，就不需要裁剪。

案例：

```
#version 300 es
uniform vec4 u_clipPlane;
uniform mat4 u_matrixProjection;
in vec4 a_vertex;

out float v_clipDist;
void main()
{
    v_clipDist = dot(a_vertex.xyz,u_clipPlane.xyz)+u_clipPlane.w;
    gl_Position = u_matViewProjection*a_vertex;
}
```

u_clipPlane保存裁剪平面的方程式，并有glUniform4f传递着色器  clipDist可变变量存储计算后的距离。

片段着色器

````
#version 300 es
precision mediump float;
in float v_clipDist;
layout(location=0)out vec4 outColor;
void main()
{
    if(v_clipDist<0)
    {
        discard；
    }
    outColor = vec4(0.5,0.5,0.5,0.0);
}
````



## 片段操作

​	片段输出的是：颜色和深度值。片段着色器之后会执行，裁剪区域测试、摸版测试、深度测试、多重采样、混合、抖动。

#### 缓冲区

​	每种缓冲区中有不同的数据：颜色缓冲区、深度缓冲区、摸版缓冲区，颜色缓冲区一般会有红绿蓝alpha、颜色深度是颜色的总和。深度和摸版于此相反，它是单一表示像素深度。

​	颜色缓冲区有可能是两个：一个用于显示，一个用于将要显示。通过后台和前台缓冲的交换进行设计的一种。

​	EGL是可以进行选择使用缓冲区的，但是EGL实现必须提供一个包含了3个缓冲区的配置。

- 请求更多的缓冲区：

  - 需要zaiEGL中请求他们。

- 缓冲区的操作

  - 删除缓存区

    openGL ES是一个交互渲染系统，每次开始绘制，需要将所有内容清除掉

    ````
    glClear(mask);
    ````

    使用mask指定缓冲区，掩码参数有：

    | GL_COLOR_BUFFER_BIT   |
    | --------------------- |
    | GL_DEPTH_BUFFER_BIT   |
    | GL_STENCIL_BUFFER_BIT |
    |                       |

    没有必要全部清除，

  - 函数指定清除值，每一个也会有一个默认的值

    - glClearColor(r,g,b,a)  GLfloat
    - glClearDeth(depth);    GLfloat
    - glClearStencil(s):清除摸版   GLint

  - 如果有多个缓存区，那么就会有下面的方法

    - glClearBufferiv(buffer, drawbuffer, value)

    - glClearBufferuiv(buffer, drawbuffer, value)

    - glClearBufferfv(buffer, drawbuffer, value)

      缓存区类型   缓存区名称   指定缓存区的四元素向量

    - 终极办法：glClearBufferfi(buffer, drawbuffer, depth，stencil)

      类型  名称  深度缓冲    摸版缓冲区

  - 



缓存和缓冲的区别：

​	缓存：为了提高CPU 和内存之间的速度

​	缓冲：为了磁盘IO和和内存之间的交换



## 帧缓冲区对象

#### 为什么需要帧缓存区

​	绘制之前，需要创建上下文和绘制表面，一般由EGL提供。上下文包含正确操作锁对应的状态。

帧缓冲区对象是一个2D图像缓存区，可以用于分配和存储颜色、深度、摸版





## 同步对象和珊栏

​	openGL ES3.0为应用程序提供了等待一组openGL操作在GPU上执行结束的机制，可以同步多个图形上下文和线程的GL操作。

#### 刷新和结束

​	openGL ES 3.0继承了客户-服务器模型，发指令-->openGL服务器处理。openGL中，客户端可以存在网络的不同机器上。这里openGL ES一般是一段设备，所以会在一个机器上。

​	客户端指令不一定立即发送，如果是存在网络上，每次发送效率低，数据缓存在客户端稍后写发送到服务器端，所以需要知道服务端什么时候完成任务。

#### 为什么使用同步对象

openGL ES3.0引入了珊栏，为应用程序提供了通知GPU在一组操作完成之前先等待。然后在将更多执行命令送入队列。

#### 创建和删除同步对象

为了在GL命令流中插入珊栏命令并创建同步对象，可以钓鱼如下函数：

glFenceSync(condition,flags);

- condition：指定同步对象必须符合的条件
- flags:指定控制同步对象行为的标志位组合，当前必须为0；

每次同步操作都需要创建一个。

删除

glDeleteSync(sync)

- 指定删除的对象

删除不会立即执行，同步对象在没有其他状态等待他的时候才会删除，可以在同步对象等待之前调用

案例：



```

```



## 高级编程

