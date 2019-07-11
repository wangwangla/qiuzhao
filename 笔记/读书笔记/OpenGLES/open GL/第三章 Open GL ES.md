# OPENGL ES

它是open gl的一个分支，主要用于移动端或者游戏中。它是一个标准。它也可以看做是一个精简的OPENGL(乱说的)，添加复杂的去掉，仅仅留下了三角形，所有的图像都由三角形组成。

##### 三角形的世界

---

​	3D的基本单位是certex，它代表三维空间的一个点，通过点构建简单二维。通过二维可以创建三维。在es中仅仅支持三角形

​	OpenGL Es采样的是三维笛卡尔坐标系，3D都对应到此坐标中，通过xyz以顶点数组形式给出。

​	

​	背面剪裁，打开背面剪裁功能后，视角在背面是不渲染，仅仅在视角的方向上渲染三角形。三角形正反是通过，顶点顺序进行的。

	## 构造

- GLSurfaceView

  - 安卓平台提供了对ES是支持，GLSurfaceView为核心类，起到了连接ES和安装连接的桥梁。
  - ES库适应于安卓的声明周期方法。
  - 使得选择合适的Frame buffer像素变的更加的容易。
  - 创建和管理单独回头达到了一个平滑的效果
  - 提供了方便使用的调试工具来追踪ES函数

  ```
  设置的方法是setRenderer（GLSurfaceView.Renderer red）
  ```

- GLSurfaceView.Renderer定义了一个统一的原型绘制接口，定义了三个接口

  ```
  onSurfaceCreated(GL10 gl,EGLConfig config)
  onDrawFrame(GL10 g1)
  onSurfaceChanged(GL10 g1,int width,int height)
  ```

  - onSurfaceCreated(GL10 gl,EGLConfig config)

    在初次打开 时候调用，一般设置不变的参数，比如背景

  - onDrawFrame(GL10 g1)

    实际的绘画操作

  - onSurfaceChanged(GL10 g1,int width,int height)

    如果支持横纵向切换，那么就发生在横纵向切换时调用。

  ### 3D绘图基本概念

  3D基本要素，实现一个多边形绘制。一个3D由基本元素组成，每个都可以单独实现。

  ##### Vertex(顶点)

  建模使用的最小构成元素，顶点定义为两条或者多条交汇，在3D模型中一个顶点多条边、面、多边形。也可是光源或者照相机的位置

  #### 三角形

-------

步骤：

- 创建显示的页面
- 创建一个可触控件，GL，并将其加到页面的里面。
- 在GL中画图【渲染】，在GL加入渲染
- 加入触摸事件，根据距离，改变角度
- 在渲染中画出具体的图像
  - 在create中实例化对象、设置背景颜色。
  - change屏幕横竖屏变化的时候调用。
  - onDraw：绘制

