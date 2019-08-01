## 目录

[]()

#### 总结

libGdx在不同平台上的实现，首先根据它的入口对整个程序进行初始化，设置日志、input、Andio、Graph、还有app，作为整个生命周期的东西创建出来，并将其设置给全局的变量。

```
jpublic class Gdx {
	public static Application app;
	public static Graphics graphics;
	public static Audio audio;
	public static Input input;
	public static Files files;
	public static Net net;

	public static GL20 gl;
	public static GL20 gl20;
	public static GL30 gl30;
}
```

##### input

实现通过AndroidInputFactory工厂进行创建AndroidInput通过反射的方式，在Android Input中，初始的时候，主要是对全局变量的一个设置，然后添加监听事件，包括触摸，点击等。在Android Input中容易发现，他实现了Android的接口input，并且实现了它的方法，比如onTouch，它将安卓的MotionEvent传入，获取事件。

- 我认为（不一定是对的）这样的目的是，在不同平台上都使用自身平台的方式，使用这个统一的接口，让他们按照这一个标准来做事，是达到跨平台的主要目的。

#### Audio

#### Graph

​	它的实现不得不提的就是openGLES的实现方式，首先创建一个surface这个创建也会根据版本的不同有所差异。然后会创建一个onSurfaceCreate将其创建出来，然后实现一个onDrawFrame，这个方法就会不断的进行执行。这个执行的时候，时间是一样的，也就是间隔一直是一样的 。

####  Net

​	网络它将http进行了封装

#### Gdx.app

可以获取到任何一个变量，因为其他的全局变量仅仅是获取了当前的一个，但是app他是整个类的，赋值是this,

