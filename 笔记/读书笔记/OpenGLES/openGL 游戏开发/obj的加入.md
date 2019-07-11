## obj加入方法

- obj加入的步骤：

  - 书写加载物体的渲染类

    初始化顶点坐标，

    初始化shader

    画图



---------

书写的项目摸版

工具类：

​	加载着色器

​	创建程序

应用：

​	创建坐标，放入内存。

​	获取变量，变量设置

​	画图





------

每次改变：	

​	创建一个顶点，声明位置信息，设置值

项目代码：

​	main：

```
创建项目
public class Demo01 extends Activity 
{
	创建Surface
	private MySurfaceView mGLSurfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
    	继承父类
        super.onCreate(savedInstanceState);         
        //设置为全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//设置为横屏模式
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		//初始化GLSurfaceView
        mGLSurfaceView = new MySurfaceView(this);
        setContentView(mGLSurfaceView);	
        mGLSurfaceView.requestFocus();//获取焦点
        mGLSurfaceView.setFocusableInTouchMode(true);//设置为可触控  
    }
声明周期
    @Override
    protected void onResume() {
        super.onResume();
        mGLSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLSurfaceView.onPause();
    }    
}
```

surface代码：

```]
class MySurfaceView extends GLSurfaceView 
{
	private final float TOUCH_SCALE_FACTOR = 180.0f/320;//角度缩放比例
    private SceneRenderer mRenderer;//场景渲染器    
    
    private float mPreviousY;//上次的触控位置Y坐标
    private float mPreviousX;//上次的触控位置X坐标
	
	public MySurfaceView(Context context) {
        super(context);
        this.setEGLContextClientVersion(2); //设置使用OPENGL ES2.0
        mRenderer = new SceneRenderer();	//创建场景渲染器
        setRenderer(mRenderer);				//设置渲染器		        
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);//设置渲染模式为主动渲染   
    }
	
	//触摸事件回调方法
    @Override 
    public boolean onTouchEvent(MotionEvent e) 
    {
        float y = e.getY();
        float x = e.getX();
        switch (e.getAction()) {
        case MotionEvent.ACTION_MOVE:
            float dy = y - mPreviousY;//计算触控笔Y位移
            float dx = x - mPreviousX;//计算触控笔X位移
            mRenderer.yAngle += dx * TOUCH_SCALE_FACTOR;//设置绕y轴旋转角度
            mRenderer.xAngle+= dy * TOUCH_SCALE_FACTOR;//设置绕x轴旋转角度
            requestRender();//重绘画面
        }
        mPreviousY = y;//记录触控笔位置
        mPreviousX = x;//记录触控笔位置
        return true;
    }

}
```

渲染器

```

	private class SceneRenderer implements GLSurfaceView.Renderer 
    {  
		float yAngle;//绕Y轴旋转的角度
    	float xAngle; //绕X轴旋转的角度
    	//从指定的obj文件中加载对象
		LoadedObjectVertexOnly lovo;
    	
        public void onDrawFrame(GL10 gl) 
        { 
        	//清除深度缓冲与颜色缓冲
            GLES20.glClear( GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);

            //坐标系推远
            MatrixState.pushMatrix();
            MatrixState.translate(0, -2f, -25f);   //ch.obj
            //绕Y轴、Z轴旋转
            MatrixState.rotate(yAngle, 0, 1, 0);
            MatrixState.rotate(xAngle, 1, 0, 0);
            
            //若加载的物体不为空则绘制物体
            if(lovo!=null)
            {
            	lovo.drawSelf();
            }   
            MatrixState.popMatrix();                  
        }  

        public void onSurfaceChanged(GL10 gl, int width, int height) {
            //设置视窗大小及位置 
        	GLES20.glViewport(0, 0, width, height); 
        	//计算GLSurfaceView的宽高比
            float ratio = (float) width / height;
            //调用此方法计算产生透视投影矩阵
            MatrixState.setProjectFrustum(-ratio, ratio, -1, 1, 2, 100);
            //调用此方法产生摄像机9参数位置矩阵
            MatrixState.setCamera(0,0,0,0f,0f,-1f,0f,1.0f,0.0f);
        }

        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            //设置屏幕背景色RGBA
            GLES20.glClearColor(0.0f,0.0f,0.0f,1.0f);    
            //打开深度检测
            GLES20.glEnable(GLES20.GL_DEPTH_TEST);
            //打开背面剪裁   
            GLES20.glEnable(GLES20.GL_CULL_FACE);
            //初始化变换矩阵
            MatrixState.setInitStack();
            //加载要绘制的物体
            lovo=LoadUtil.loadFromFile("ch.obj", MySurfaceView.this.getResources(),MySurfaceView.this);
        }
    }
```





-----------

手动默写一遍

main

```
public class MyActivity extends Activity {
	//创建Surfce
	private GLSurfaceView mGLSurfaceView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        //设置为全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//设置为横屏模式
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			
		//初始化GLSurfaceView
        mGLSurfaceView = new MySurface(this);
        setContentView(mGLSurfaceView);	
        mGLSurfaceView.requestFocus();//获取焦点
        mGLSurfaceView.setFocusableInTouchMode(true);//设置为可触控  
		
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mGLSurfaceView.onResume();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mGLSurfaceView.onPause();
	}
}
```

创建GLSurfaceView

```
public class MySurface extends GLSurfaceView{

	public MySurface(Context context) {
		super(context);
		//设置版本
		setEGLContextClientVersion(2);
		MyRenderer myRenderer = new MyRenderer(context);
		setRenderer(myRenderer);
	}
	
}
```

创建着色器

```
public class MyRenderer implements Renderer{
	
	public MyRenderer(Context context) {

		
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		
	}

}

```

下来做什么？？创建物体，设置参数,NO，编写shader，加载shader,编译shader，连接shader.

Let‘s go

- 创建shader

  做什么？？      是什么？？   是否

  首先由变换，投影？？    uniform  u_MATRIX    Yes

  需要位置    attribute vec4 a_Position      Yes

  other ??       传递位置，求出位置   varying     Yes

  ```
  //干什么  变换
  uniform mat4 u_Matrix;
  
  //干什么    属性值
  attribute vec3 a_Position;
  
  //干什么   传数据
  varying vec3 v_Position;
  void main()
  {
  	//怎么做
  	gl_Position = u_Matrix*vec4(a_Position,1);
  	//传输参数
  	v_Position = a_Position;
  }
  ```

  

- 创建片段着色器

  传递过来有位置，位置就算参数，颜色自己搞定，因此

  varying vec4 a_Position;    yes

  god，精度没给呀，

  ```
  //干什么  设置精度
  precision mediump float;
  
  //干什么  传输的参数
  varying vec3 v_Position;
  
  void main()
  
  {
  	//创建变换的颜色
  	vec4 bcolor = vec4(0.5,0.5,0.5,0);
  	vec4 mcolor = vec4(0.5,0.5,0.5,0);
  	//只要y
  	float y = v_Position.y;
  	//计算
  	float ypo = mod((y+100.0)*4.0,4.0);
  	if(ypo > 1.8){
  		gl_FragColor = bcolor;
  	}else{
  		gl_FragColor = mcolor;
  	}
  }
  
  ```

  该干嘛了？？？？？？？？？？？？？？

  加载编译

  ```
  public class ShaderUtil 
  {
     //加载制定shader的方法
     public static int loadShader
     (
  		 int shaderType, //shader的类型  GLES20.GL_VERTEX_SHADER   GLES20.GL_FRAGMENT_SHADER
  		 String source   //shader的脚本字符串
     ) 
     {
  	    //创建一个新shader
          int shader = GLES20.glCreateShader(shaderType);
          //若创建成功则加载shader
          if (shader != 0) 
          {
          	//加载shader的源代码
              GLES20.glShaderSource(shader, source);
              //编译shader
              GLES20.glCompileShader(shader);
              //存放编译成功shader数量的数组
              int[] compiled = new int[1];
              //获取Shader的编译情况
              GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0);
              if (compiled[0] == 0) 
              {//若编译失败则显示错误日志并删除此shader
                  Log.e("ES20_ERROR", "Could not compile shader " + shaderType + ":");
                  Log.e("ES20_ERROR", GLES20.glGetShaderInfoLog(shader));
                  GLES20.glDeleteShader(shader);
                  shader = 0;      
              }  
          }
          return shader;
      }
      
     //创建shader程序的方法
     public static int createProgram(String vertexSource, String fragmentSource) 
     {
  	    //加载顶点着色器
          int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexSource);
          if (vertexShader == 0) 
          {
              return 0;
          }
          
          //加载片元着色器
          int pixelShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentSource);
          if (pixelShader == 0) 
          {
              return 0;
          }
  
          //创建程序
          int program = GLES20.glCreateProgram();
          //若程序创建成功则向程序中加入顶点着色器与片元着色器
          if (program != 0) 
          {
          	//向程序中加入顶点着色器
              GLES20.glAttachShader(program, vertexShader);
              checkGlError("glAttachShader");
              //向程序中加入片元着色器
              GLES20.glAttachShader(program, pixelShader);
              checkGlError("glAttachShader");
              //链接程序
              GLES20.glLinkProgram(program);
              //存放链接成功program数量的数组
              int[] linkStatus = new int[1];
              //获取program的链接情况
              GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, linkStatus, 0);
              //若链接失败则报错并删除程序
              if (linkStatus[0] != GLES20.GL_TRUE) 
              {
                  Log.e("ES20_ERROR", "Could not link program: ");
                  Log.e("ES20_ERROR", GLES20.glGetProgramInfoLog(program));
                  GLES20.glDeleteProgram(program);
                  program = 0;
              }
          }
          return program;
      }
      
     //检查每一步操作是否有错误的方法 
     public static void checkGlError(String op) 
     {
          int error;
          while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) 
          {
              Log.e("ES20_ERROR", op + ": glError " + error);
              throw new RuntimeException(op + ": glError " + error);
          }
     }
     
     //从sh脚本中加载shader内容的方法
     public static String loadFromAssetsFile(String fname,Resources r)
     {
     	String result=null;    	
     	try
     	{
     		InputStream in=r.getAssets().open(fname);
  			int ch=0;
  		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
  		    while((ch=in.read())!=-1)
  		    {
  		      	baos.write(ch);
  		    }      
  		    byte[] buff=baos.toByteArray();
  		    baos.close();
  		    in.close();
     		result=new String(buff,"UTF-8"); 
     		result=result.replaceAll("\\r\\n","\n");
     	}
     	catch(Exception e)
     	{
     		e.printStackTrace();
     	}    	
     	return result;
     }
  }
  
  ```

  --切记这个是工具，可以打成包自己使用。

  我们去准备顶点数据来使用它

  ```
  
  ```

  序列化数据

  ```
          ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
          vbb.order(ByteOrder.nativeOrder());//设置字节顺序
          mVertexBuffer = vbb.asFloatBuffer();//转换为Float型缓冲
          mVertexBuffer.put(vertices);//向缓冲区中放入顶点坐标数据
          mVertexBuffer.position(0);//设置缓冲区起始位置
  ```

  获取位置

