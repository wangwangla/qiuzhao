## Open GL  ES代码循序渐进

​	Open GL基本原理的东西，也基本看调查不多了，现在开始使用它，实现一下小的练习，这里分为两个部分，第一部分是看别人的代码，了解主要步骤和核心代码，以及代码技巧，第二部分：自己根据自己喜好实现一下练习。

### 第一例  啥也不显示的最少代码

​	**目标实现出背景色。**

1.页面的内容实现：

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="kr.pe.burt.android.helloopengl.MainActivity">
	<!--引入布局-->
    <kr.pe.burt.android.helloopengl.OGLView
        android:id="@+id/oglView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
</RelativeLayout>
```

```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
```

2.Activity：

- 需要一个GLSurfaceView

  ```java
   private OGLView oglView;
  ```

  ```java
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          oglView = (OGLView) findViewById(R.id.oglView);
      }
  ```

- GLSurfaceView是有声明周期的

  ```java
    @Override
      protected void onPause() {
          super.onPause();
          oglView.onPause();
      }
  
      @Override
      protected void onResume() {
          super.onResume();
          oglView.onResume();
      }
  ```

- 创建一个GLSurfaceView实体,因为需要上下文，所以需要一个构造函数

  ```java
  public class OGLView extends GLSurfaceView {
      public OGLView(Context context) {
          super(context);
          init();
      }
  
      public OGLView(Context context, AttributeSet attrs) {
          super(context, attrs);
          init();
      }
  }
  ```

  

- 其次我们还需要设置使用open gl的版本和渲染器

  ```java
      private void init() {
          // 设置版本
          setEGLContextClientVersion(2);
          // 设置EGL，具体之后再说
          setPreserveEGLContextOnPause(true);
          // 设置渲染器
          setRenderer(new OGLRenderer());
      }
  ```

  

- 创建渲染器，渲染器实现Renderer，实现他们的三个方法

  ```java
  public class OGLRenderer implements GLSurfaceView.Renderer {
  
      @Override
      public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
      	//清除底部的颜色
          GLES20.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
      }
  
      @Override
      public void onSurfaceChanged(GL10 gl10, int i, int i1) {
  		屏幕转换时调用
      }
  
      @Override
      public void onDrawFrame(GL10 gl10) {
      	//清缓存，如果可将底部的全部覆盖，那么就不需要清缓存。
          GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
      }
  }
  ```

  现在将一个没有任何图像的应用创建完毕了。

### 第二例： 显示颜色动态变化的效果

- 其他代码不变，显示出一种，颜色变化的状态

```java
 @Override
    public void onDrawFrame(GL10 gl10) {
        GLES20.glClearColor((float)redValue, 0.0f, 0.0f, 1.0f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        redValue = ((Math.sin(System.currentTimeMillis() * 2 * Math.PI / DURATION_OF_FLASH) * 0.5) + 0.5);
    }
```



### 第三例  正方形

1.准备着色器

```c++
顶点着色器
//版本
#version 200 es
//获取顶点
attribute vac4 a_Position;
void main()
{
	gl_Position = a_Position;    
}

------------
片段着色器
#version 200 es
void main()
{
    gl_FragColor=vec4(1.0f,1.0f,1.0f,1.0f);
}
```

2.工具类介绍，在书写顶点的时候，一般都会是float，我们可以创建本地内存，并且是各种类型的。

```java
    public static FloatBuffer newFloatBuffer (int numFloats) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(numFloats * 4);
        buffer.order(ByteOrder.nativeOrder());
        return buffer.asFloatBuffer();
    }

    public static DoubleBuffer newDoubleBuffer (int numDoubles) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(numDoubles * 8);
        buffer.order(ByteOrder.nativeOrder());
        return buffer.asDoubleBuffer();
    }

    public static ByteBuffer newByteBuffer (int numBytes) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(numBytes);
        buffer.order(ByteOrder.nativeOrder());
        return buffer;
    }

    public static ShortBuffer newShortBuffer (int numShorts) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(numShorts * 2);
        buffer.order(ByteOrder.nativeOrder());
        return buffer.asShortBuffer();
    }

    public static CharBuffer newCharBuffer (int numChars) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(numChars * 2);
        buffer.order(ByteOrder.nativeOrder());
        return buffer.asCharBuffer();
    }

    public static IntBuffer newIntBuffer(int numInts) {
        ByteBuffer buffer = ByteBuffer.allocate(numInts * 4);
        buffer.order(ByteOrder.nativeOrder());
        return buffer.asIntBuffer();
    }

    public static LongBuffer newLongBuffer(int numLongs) {
        ByteBuffer buffer = ByteBuffer.allocate(numLongs * 8);
        buffer.order(ByteOrder.nativeOrder());
        return buffer.asLongBuffer();
    }
```

3.加载着色器shaderUtils

```java
public class ShaderProgram {
    private String vertexShaderSource;
    private String fragmentShaderSource;

    private int vertexShaderHandle;
    private int fragmentShaderHandle;
    private int programHandle;
    private String log = "";
    private boolean isCompiled;

    private HashMap<String, Integer> uniforms = new HashMap<>();
    private HashMap<String, Integer> attributes = new HashMap<>();

	/**
	 构造函数中传入着色器 字符串，并对其进行效验，为null的时候，进行抛出异常，
	 如果没有问题就会执行编译。
	*/
    public ShaderProgram(String vertexShader, String fragmentShader) {
        if(vertexShader == null) throw new IllegalArgumentException("vertex shader must not be null");
        if(fragmentShader == null) throw new IllegalArgumentException("fragment shader must not be null");

        vertexShaderSource = vertexShader;
        fragmentShaderSource = fragmentShader;

        compileShaders(vertexShader, fragmentShader);

        if(isCompiled()) {
            fetchAttributes();
            fetchUniforms();
        }
    }

    // 检测是否执行了编译
    public boolean isValid() {
        return isCompiled;
    }

    // 开始使用程序
    public void begin() {
        GLES20.glUseProgram(programHandle);
    }

    // 使用结束
    public void end() {
        GLES20.glUseProgram(0);
    }

    // 销毁程序，销毁着色器
    public void destroy() {
        GLES20.glUseProgram(0);
        GLES20.glDeleteShader(vertexShaderHandle);
        GLES20.glDeleteShader(fragmentShaderHandle);
        GLES20.glDeleteProgram(programHandle);
    }

    private void compileShaders(String vertexShader, String fragmentShader) {
        //顶点句柄
        vertexShaderHandle = loadShader(GLES20.GL_VERTEX_SHADER, vertexShader);
        fragmentShaderHandle = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShader);
		//检测获取是否得到句柄成功，如果是-1.那么编译失败
        if(vertexShaderHandle == -1 || fragmentShaderHandle == -1) {
            isCompiled = false;
            return;
        }
		//连接程序
        programHandle = linkProgram(createProgram());
        //连接失败 ,那么编译失败
        if(programHandle == -1) {
            isCompiled = false;
            return;
        }

        isCompiled = true;
    }
	//创建程序
    private int createProgram() {
        int program = GLES20.glCreateProgram();
        return program != 0 ? program : -1;
    }
	//连接程序
    private int linkProgram(int program) {
        if(program == -1)
            return -1;
        GLES20.glAttachShader(program, vertexShaderHandle);
        GLES20.glAttachShader(program, fragmentShaderHandle);
        GLES20.glLinkProgram(program);

        int[] linked = new int[1];
        GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, linked, 0);
        if(linked[0] == GLES20.GL_FALSE) {
            String infoLog = GLES20.glGetProgramInfoLog(program);
            log += infoLog;
            GLES20.glDeleteProgram(program);
            return  -1;
        }
        return program;
    }
	加载着色器
    private int loadShader(int shaderType, String shaderCode) {
        int shader = GLES20.glCreateShader(shaderType);
        if(shader == 0)
            return -1;

        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        int[] compiled = new int[1];
        GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0);

        if(compiled[0] == GLES20.GL_FALSE) {
            String infoLog = GLES20.glGetShaderInfoLog(shader);
            log += infoLog;
            GLES20.glDeleteShader(shader);
            return -1;
        }
        return shader;
    }

    public String getLog() {
        if(isCompiled) {
            log = GLES20.glGetProgramInfoLog(programHandle);
            return log;
        } else {
            return log;
        }
    }

    public boolean isCompiled() {
        return isCompiled;
    }
	//获取属性的位置
    private int fetchAttributeLocation(String name) {
        int location = attributes.get(name);
        if(location == -1) {
            location = GLES20.glGetAttribLocation(programHandle, name);
            if(location != -1) {
                attributes.put(name, location);
            }
        }
        return location;
    }

    private int fetchUniformLocation(String name) {
        int location = uniforms.get(name);
        if(location == -1) {
            location = GLES20.glGetUniformLocation(programHandle, name);
            if(location != -1) {
                uniforms.put(name, location);
            }
        }
        return location;
    }


    private void fetchAttributes() {

        attributes.clear();

        IntBuffer params = IntBuffer.allocate(1);
        IntBuffer type = IntBuffer.allocate(1);
        GLES20.glGetProgramiv(programHandle, GLES20.GL_ACTIVE_ATTRIBUTES, params);
        int numAttributes = params.get(0);

        for(int i=0; i<numAttributes; i++) {
            params.compact();
            params.put(0, 1);
            params.position(0);
            type.clear();
            String name = GLES20.glGetActiveAttrib(programHandle, i, params, type);
            int location = GLES20.glGetAttribLocation(programHandle, name);
            attributes.put(name, location);
        }
    }

    private void fetchUniforms() {

        uniforms.clear();

        IntBuffer params = IntBuffer.allocate(1);
        IntBuffer type = IntBuffer.allocate(1);
        GLES20.glGetProgramiv(programHandle, GLES20.GL_ACTIVE_UNIFORMS, params);
        int numUniform = params.get(0);

        for(int i=0; i<numUniform; i++) {
            params.compact();
            params.put(0, 1);
            type.clear();
            String name = GLES20.glGetActiveUniform(programHandle, i, params, type);
            int location = GLES20.glGetUniformLocation(programHandle, name);
            uniforms.put(name, location);
        }
    }

    /**
     * @param name name the name of the uniform
     * @return the location of the uniform or -1.
     */
    public int getUniformLocation(String name) {
        return uniforms.get(name) == null ? -1 : uniforms.get(name);
    }

    /**
     * @param name the name of the attribute
     * @return the location of the attribute or -1.
     */
    public int getAttributeLocation(String name) {
        return attributes.get(name) == null ? -1 : attributes.get(name);
    }

    /** @param name the name of the uniform
     * @return whether the uniform is available in the shader */
    public boolean hasUniform(String name) {
        return uniforms.containsKey(name);
    }

    /** @param name the name of the attribute
     * @return whether the attribute is available in the shader */
    public boolean hasAttribute(String name) {
        return attributes.containsKey(name);
    }


    /***********************************************************************************************
     * Set Uniforms and Attributes
     ***********************************************************************************************/

    /** Sets the uniform with the given name.
     *
     * @param name the name of the uniform
     * @param value the value */
    public void setUniformi(String name, int value) {
        int location = fetchUniformLocation(name);
        if(location == -1) return;
        GLES20.glUniform1i(location, value);
    }

    public void setUniformi(int location, int value) {
        if(location == -1) return;
        GLES20.glUniform1i(location, value);
    }

    /** Sets the uniform with the given name.
     *
     * @param name the name of the uniform
     * @param value1 the first value
     * @param value2 the second value */
    public void setUniformi (String name, int value1, int value2) {
        int location = fetchUniformLocation(name);
        if(location == -1) return;
        GLES20.glUniform2i(location, value1, value2);
    }

    public void setUniformi (int location, int value1, int value2) {
        if(location == -1) return;
        GLES20.glUniform2i(location, value1, value2);
    }

    /** Sets the uniform with the given name.
     *
     * @param name the name of the uniform
     * @param value1 the first value
     * @param value2 the second value
     * @param value3 the third value */
    public void setUniformi (String name, int value1, int value2, int value3) {
        int location = fetchUniformLocation(name);
        if(location == -1) return;
        GLES20.glUniform3i(location, value1, value2, value3);

    }

    public void setUniformi (int location, int value1, int value2, int value3) {
        if(location != -1)
            GLES20.glUniform3i(location, value1, value2, value3);
    }

    /** Sets the uniform with the given name.
     *
     * @param name the name of the uniform
     * @param value1 the first value
     * @param value2 the second value
     * @param value3 the third value
     * @param value4 the fourth value */
    public void setUniformi (String name, int value1, int value2, int value3, int value4) {
        int location = fetchUniformLocation(name);
        if(location == -1) return;
        GLES20.glUniform4i(location, value1, value2, value3, value4);
    }

    public void setUniformi (int location, int value1, int value2, int value3, int value4) {
        if(location == -1) return;
        GLES20.glUniform4i(location, value1, value2, value3, value4);
    }

    /** Sets the uniform with the given name.
     *
     * @param name the name of the uniform
     * @param value the value */
    public void setUniformf (String name, float value) {
        int location = fetchUniformLocation(name);
        if(location == -1) return;
        GLES20.glUniform1f(location, value);
    }

    public void setUniformf (int location, float value) {
        if(location == -1) return;
        GLES20.glUniform1f(location, value);
    }

    /** Sets the uniform with the given name.
     *
     * @param name the name of the uniform
     * @param value1 the first value
     * @param value2 the second value */
    public void setUniformf (String name, float value1, float value2) {
        int location = fetchUniformLocation(name);
        if(location == -1) return;
        GLES20.glUniform2f(location, value1, value2);
    }

    public void setUniformf (int location, float value1, float value2) {
        if(location == -1) return;
        GLES20.glUniform2f(location, value1, value2);
    }

    /** Sets the uniform with the given name.
     *
     * @param name the name of the uniform
     * @param value1 the first value
     * @param value2 the second value
     * @param value3 the third value */
    public void setUniformf (String name, float value1, float value2, float value3) {
        int location = fetchUniformLocation(name);
        if(location == -1) return;
        GLES20.glUniform3f(location, value1, value2, value3);
    }

    public void setUniformf (int location, float value1, float value2, float value3) {
        if(location == -1) return;
        GLES20.glUniform3f(location, value1, value2, value3);
    }

    /** Sets the uniform with the given name.
     *
     * @param name the name of the uniform
     * @param value1 the first value
     * @param value2 the second value
     * @param value3 the third value
     * @param value4 the fourth value */
    public void setUniformf (String name, float value1, float value2, float value3, float value4) {
        int location = fetchUniformLocation(name);
        if(location == -1) return;
        GLES20.glUniform4f(location, value1, value2, value3, value4);
    }

    public void setUniformf (int location, float value1, float value2, float value3, float value4) {
        if(location == -1) return;
        GLES20.glUniform4f(location, value1, value2, value3, value4);
    }

    public void setUniform1fv (String name, float[] values, int offset, int length) {
        int location = fetchUniformLocation(name);
        if(location == -1) return;
        GLES20.glUniform1fv(location, length, values, offset);
    }

    public void setUniform1fv (int location, float[] values, int offset, int length) {
        if(location == -1) return;
        GLES20.glUniform1fv(location, length, values, offset);
    }

    public void setUniform2fv (String name, float[] values, int offset, int length) {
        int location = fetchUniformLocation(name);
        if(location == -1) return;
        GLES20.glUniform2fv(location, length / 2, values, offset);
    }

    public void setUniform2fv (int location, float[] values, int offset, int length) {
        if(location == -1) return;
        GLES20.glUniform2fv(location, length / 2, values, offset);
    }

    public void setUniform3fv (String name, float[] values, int offset, int length) {
        int location = fetchUniformLocation(name);
        if(location == -1) return;
        GLES20.glUniform3fv(location, length / 3, values, offset);
    }

    public void setUniform3fv (int location, float[] values, int offset, int length) {
        if(location == -1) return;
        GLES20.glUniform3fv(location, length / 3, values, offset);
    }

    public void setUniform4fv (String name, float[] values, int offset, int length) {
        int location = fetchUniformLocation(name);
        if(location == -1) return;
        GLES20.glUniform4fv(location, length / 4, values, offset);
    }

    public void setUniform4fv (int location, float[] values, int offset, int length) {
        if(location == -1) return;
        GLES20.glUniform4fv(location, length / 4, values, offset);
    }

    /** Sets the uniform matrix with the given name.
     *
     * @param name the name of the uniform
     * @param matrix the matrix */
    public void setUniformMatrix (String name, Matrix4f matrix) {
        setUniformMatrix(name, matrix, false);
    }

    /** Sets the uniform matrix with the given name.
     *
     * @param name the name of the uniform
     * @param matrix the matrix
     * @param transpose whether the matrix should be transposed */
    public void setUniformMatrix (String name, Matrix4f matrix, boolean transpose) {
        setUniformMatrix(fetchUniformLocation(name), matrix, transpose);
    }

    public void setUniformMatrix (int location, Matrix4f matrix) {
        setUniformMatrix(location, matrix, false);
    }

    public void setUniformMatrix (int location, Matrix4f matrix, boolean transpose) {
        if(location == -1) return;
        GLES20.glUniformMatrix4fv(location, 1, transpose, matrix.getArray(), 0);
    }


    /** Sets the uniform matrix with the given name.
     *
     * @param name the name of the uniform
     * @param matrix the matrix */
    public void setUniformMatrix (String name, Matrix3f matrix) {
        setUniformMatrix(name, matrix, false);
    }

    /** Sets the uniform matrix with the given name.
     *
     * @param name the name of the uniform
     * @param matrix the matrix
     * @param transpose whether the uniform matrix should be transposed */
    public void setUniformMatrix (String name, Matrix3f matrix, boolean transpose) {
        setUniformMatrix(fetchUniformLocation(name), matrix, transpose);
    }

    public void setUniformMatrix (int location, Matrix3f matrix) {
        setUniformMatrix(location, matrix, false);
    }

    public void setUniformMatrix (int location, Matrix3f matrix, boolean transpose) {
        if(location == -1) return;
        GLES20.glUniformMatrix3fv(location, 1, transpose, matrix.getArray(), 0);
    }

    /** Sets an array of uniform matrices with the given name.
     *
     * @param name the name of the uniform
     * @param buffer buffer containing the matrix data
     * @param transpose whether the uniform matrix should be transposed */
    public void setUniformMatrix3fv (String name, FloatBuffer buffer, int count, boolean transpose) {
        buffer.position(0);
        int location = fetchUniformLocation(name);
        if(location == -1) return;
        GLES20.glUniformMatrix3fv(location, count, transpose, buffer);
    }

    /** Sets an array of uniform matrices with the given name.
     *
     * @param name the name of the uniform
     * @param buffer buffer containing the matrix data
     * @param transpose whether the uniform matrix should be transposed */
    public void setUniformMatrix4fv (String name, FloatBuffer buffer, int count, boolean transpose) {
        buffer.position(0);
        int location = fetchUniformLocation(name);
        if(location == -1) return;
        GLES20.glUniformMatrix4fv(location, count, transpose, buffer);
    }

    public void setUniformMatrix4fv (int location, float[] values, int offset, int length) {
        if(location == -1) return;
        GLES20.glUniformMatrix4fv(location, length / 16, false, values, offset);
    }

    public void setUniformMatrix4fv (String name, float[] values, int offset, int length) {
        setUniformMatrix4fv(fetchUniformLocation(name), values, offset, length);
    }

    /** Sets the uniform with the given name.
     *
     * @param name the name of the uniform
     * @param vector x and y as the first and second values respectively */
    public void setUniformf (String name, PointF vector) {
        setUniformf(name, vector.x, vector.y);
    }

    public void setUniformf (int location, PointF vector) {
        setUniformf(location, vector.x, vector.y);
    }


    /** Sets the uniform with the given name.
     *
     * @param name the name of the uniform
     * @param color r, g, b and a as the first through fourth values respectively */
    public void setUniformColor (String name, int color) {
        setUniformf(name, Color.red(color) / 255.0f, Color.green(color) / 255.0f, Color.blue(color) / 255.0f, Color.alpha(color) / 255.0f);
    }

    public void setUniformf (int location, int color) {
        setUniformf(location, Color.red(color) / 255.0f, Color.green(color) / 255.0f, Color.blue(color) / 255.0f, Color.alpha(color) / 255.0f);
    }

    /** Sets the vertex attribute with the given name.
     *
     * @param name the attribute name
     * @param size the number of components, must be >= 1 and <= 4
     * @param type the type, must be one of GL20.GL_BYTE, GL20.GL_UNSIGNED_BYTE, GL20.GL_SHORT,
     *           GL20.GL_UNSIGNED_SHORT,GL20.GL_FIXED, or GL20.GL_FLOAT. GL_FIXED will not work on the desktop
     * @param normalize whether fixed point data should be normalized. Will not work on the desktop
     * @param stride the stride in bytes between successive attributes
     * @param buffer the buffer containing the vertex attributes. */
    public void setVertexAttribute (String name, int size, int type, boolean normalize, int stride, Buffer buffer) {
        int location = fetchAttributeLocation(name);
        if (location == -1) return;
        GLES20.glVertexAttribPointer(location, size, type, normalize, stride, buffer);
    }

    public void setVertexAttribute (int location, int size, int type, boolean normalize, int stride, Buffer buffer) {
        if(location == -1) return;
        GLES20.glVertexAttribPointer(location, size, type, normalize, stride, buffer);
    }

    /** Sets the vertex attribute with the given name.
     *
     * @param name the attribute name
     * @param size the number of components, must be >= 1 and <= 4
     * @param type the type, must be one of GL20.GL_BYTE, GL20.GL_UNSIGNED_BYTE, GL20.GL_SHORT,
     *           GL20.GL_UNSIGNED_SHORT,GL20.GL_FIXED, or GL20.GL_FLOAT. GL_FIXED will not work on the desktop
     * @param normalize whether fixed point data should be normalized. Will not work on the desktop
     * @param stride the stride in bytes between successive attributes
     * @param offset byte offset into the vertex buffer object bound to GL20.GL_ARRAY_BUFFER. */
    public void setVertexAttribute (String name, int size, int type, boolean normalize, int stride, int offset) {
        int location = fetchAttributeLocation(name);
        if (location == -1)
            return;
        GLES20.glVertexAttribPointer(location, size, type, normalize, stride, offset);
    }

    public void setVertexAttribute (int location, int size, int type, boolean normalize, int stride, int offset) {
        if(location == -1) return;
        GLES20.glVertexAttribPointer(location, size, type, normalize, stride, offset);
    }

    /** Disables the vertex attribute with the given name
     *
     * @param name the vertex attribute name */
    public void disableVertexAttribute (String name) {
        int location = fetchAttributeLocation(name);
        if (location == -1) return;
        GLES20.glDisableVertexAttribArray(location);
    }

    public void disableVertexAttribute (int location) {
        if(location == -1) return;
        GLES20.glDisableVertexAttribArray(location);
    }

    /** Enables the vertex attribute with the given name
     *
     * @param name the vertex attribute name */
    public void enableVertexAttribute (String name) {
        int location = fetchAttributeLocation(name);
        if (location == -1) return;
        GLES20.glEnableVertexAttribArray(location);
    }

    public void enableVertexAttribute (int location) {
        if(location == -1) return;
        GLES20.glEnableVertexAttribArray(location);
    }

    /** Sets the given attribute
     *
     * @param name the name of the attribute
     * @param value1 the first value
     * @param value2 the second value
     * @param value3 the third value
     * @param value4 the fourth value */
    public void setAttributef (String name, float value1, float value2, float value3, float value4) {
        int location = fetchAttributeLocation(name);
        if(location == -1) return;
        GLES20.glVertexAttrib4f(location, value1, value2, value3, value4);
    }

    public void setAttributef (String name, float value1, float value2, float value3) {
        int location = fetchAttributeLocation(name);
        if(location == -1) return;
        GLES20.glVertexAttrib3f(location, value1, value2, value3);
    }

    public void setAttributef (String name, float value1, float value2) {
        int location = fetchAttributeLocation(name);
        if(location == -1) return;
        GLES20.glVertexAttrib2f(location, value1, value2);
    }
}
```



4.加载文件

```java
public class ShaderUtils {

    public static String readShaderFileFromRawResource(final Context context, final int resourceId)
    {
        final InputStream inputStream = context.getResources().openRawResource(resourceId);
        final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String nextLine;
        final StringBuilder body = new StringBuilder();

        try
        {
            while ((nextLine = bufferedReader.readLine()) != null)
            {
                body.append(nextLine);
                body.append("\n");
            }
        }
        catch(IOException e)
        {
            return null;
        }

        return body.toString();
    }

    public static String readShaderFileFromFilePath(final Context context, final String filePath)
    {
        try
        {
            AssetManager assetManager = context.getAssets();
            InputStream ims = assetManager.open(filePath);

            String re = convertStreamToString(ims);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }

    public static String convertStreamToString(InputStream is)
    {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
```

5.画图

```java
public class OGLRenderer implements GLSurfaceView.Renderer {

    private Context context;
    private Square square;

    public OGLRenderer(Context context) {
        this.context = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        square = new Square(context);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int w, int h) {
        GLES20.glViewport(0, 0, w, h);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        GLES20.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        square.draw();
    }

}
```

6.准备图像

- 准备顶点
- 创建出程序
- 获取位置信息，设置参数
- 画图

```java
public class Square {

    private FloatBuffer vertexBuffer;
    private ShaderProgram shader;
    private int vertexBufferId;
    private int vertexCount;
    private int vertexStride;

    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;
    static final float squareCoords[] = {
            -0.5f,  0.5f, 0.0f,   // top left
            -0.5f, -0.5f, 0.0f,   // bottom left
             0.5f, -0.5f, 0.0f,   // bottom right

            -0.5f,  0.5f, 0.0f,   // top left
             0.5f, -0.5f, 0.0f,   // bottom right
             0.5f,  0.5f, 0.0f    // top right
    };

    public Square(Context context) {
        setupShader(context);
        setupVertexBuffer();
    }

    private void setupShader(Context context) {
        // compile & link shader
        shader = new ShaderProgram(
                ShaderUtils.readShaderFileFromRawResource(context, R.raw.simple_vertex_shader),
                ShaderUtils.readShaderFileFromRawResource(context, R.raw.simple_fragment_shader)
        );
    }

    private void setupVertexBuffer() {
        // initialize vertex float buffer for shape coordinates
        vertexBuffer = BufferUtils.newFloatBuffer(squareCoords.length);
        // add the coordinates to the FloatBuffer
        vertexBuffer.put(squareCoords);
        // set the buffer to read the first coordinate
        vertexBuffer.position(0);
		如果没有加入到缓冲中，那么每次都会从客户端向服务端进行复制，比较浪费资源
        //copy vertices from cpu to the gpu
        IntBuffer buffer = IntBuffer.allocate(1);
        GLES20.glGenBuffers(1, buffer);
        vertexBufferId = buffer.get(0);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vertexBufferId);
        //数据的长度是类型长度X数据的个数
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, squareCoords.length * 4, vertexBuffer, GLES20.GL_STATIC_DRAW);

        vertexCount = squareCoords.length / COORDS_PER_VERTEX;
        vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex
    }

    public void draw() {

        shader.begin();

        shader.enableVertexAttribute("a_Position");
        shader.setVertexAttribute("a_Position", COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexStride, 0);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vertexBufferId);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);

        shader.disableVertexAttribute("a_Position");

        shader.end();
    }
}
```

第四例 star

使用总结：

使用的时候准备数据，建数据放入到缓存中，使用一个缓存的ID,直接引用换存的ID即可。使用的步骤：

- 准备数据

  ```
   float [] vertices = new float[] {
               0.37f, -0.12f, 0.0f,
               0.95f,  0.30f, 0.0f,
               0.23f,  0.30f, 0.0f,
  
               0.23f,  0.30f, 0.0f,
               0.00f,  0.90f, 0.0f,
              -0.23f,  0.30f, 0.0f,
  
              -0.23f,  0.30f, 0.0f,
              -0.95f,  0.30f, 0.0f,
              -0.37f, -0.12f, 0.0f,
  
              -0.37f, -0.12f, 0.0f,
              -0.57f, -0.81f, 0.0f,
               0.00f, -0.40f, 0.0f,
  
               0.00f, -0.40f, 0.0f,
               0.57f, -0.81f, 0.0f,
               0.37f, -0.12f, 0.0f,
      };
  ```

  - 获取着色器对象

    ```java
     private void setupShader() {
            // compile & link shader
            shader = new ShaderProgram(
                    ShaderUtils.readShaderFileFromRawResource(context, R.raw.simple_vertex_shader),
                    ShaderUtils.readShaderFileFromRawResource(context, R.raw.simple_fragment_shader)
            );
        }
    ```

    

  - 获取位置，设置参数

  ```java
          shader.begin();
        /**
           *   这里需要注意：
           *   	如果使用的是顶点属性数组，那么使用顶点数组，
           *   如果使用缓存区对象，那么使用偏移量
               shader.setVertexAttribute("a_Position", COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexStride, 0);
           */
      
  
          shader.enableVertexAttribute("a_Position");
          shader.setVertexAttribute("a_Position", 3, GLES20.GL_FLOAT, false, 3*4, 0);
  
          GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vertexBufferId);
          GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertices.length);
  
          shader.disableVertexAttribute("a_Position");
  
          shader.end();
  ```

  - 数据放入缓存中

    ```
    private void setupVertexBuffer() {
    
            //store vertices vertexBuffer
            vertexBuffer = BufferUtils.newFloatBuffer(vertices.length);
            vertexBuffer.put(vertices);
            vertexBuffer.position(0);
    		/**glGenBuffers(GLSize n,GLint *buffers);
    		返回的缓冲区对象名称的数量
    		buffers：指向n个条目的数组指针，该数组是分配缓存区的位置
    		
    		*/
            //copy vertices from cpu to the gpu
            IntBuffer buffer = IntBuffer.allocate(1);
            GLES20.glGenBuffers(1, buffer);
            vertexBufferId = buffer.get(0);
            GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vertexBufferId);
            GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, vertices.length * 4, vertexBuffer, GLES20.GL_STATIC_DRAW);
    
        }
    ```

    

### 第四例 三角形

1.准备数据

```
    static final float triangleCoords[] = {
             0.0f,  0.25f, 0.0f,    // TOP
            -0.5f, -0.25f, 0.0f,    // LEFT
             0.5f, -0.25f, 0.0f,    // RIGHT
    };
```

2初始化

```
private void setupShader(Context context) {
        // compile & link shader
        shader = new ShaderProgram(
                ShaderUtils.readShaderFileFromRawResource(context, R.raw.simple_vertex_shader),
                ShaderUtils.readShaderFileFromRawResource(context, R.raw.simple_fragment_shader)
        );
    }
```

3.放入数据

```
 // initialize vertex float buffer for shape coordinates
        vertexBuffer = BufferUtils.newFloatBuffer(triangleCoords.length);

        // add the coordinates to the FloatBuffer
        vertexBuffer.put(triangleCoords);

        // set the buffer to read the first coordinate
        vertexBuffer.position(0);
```

4.将数据放入缓存中

```
 //copy vertices from cpu to the gpu
        IntBuffer buffer = IntBuffer.allocate(1);
        GLES20.glGenBuffers(1, buffer);
        vertexBufferId = buffer.get(0);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vertexBufferId);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, triangleCoords.length * 4, vertexBuffer, GLES20.GL_STATIC_DRAW);
```

5.个数

```
vertexCount = triangleCoords.length / COORDS_PER_VERTEX;
vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex
```

6,画图

```
       shader.begin();

        shader.enableVertexAttribute("a_Position");
        shader.setVertexAttribute("a_Position", COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexStride, 0);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vertexBufferId);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);

        shader.disableVertexAttribute("a_Position");

        shader.end();
```





第五例 颜色变化的正方形

将颜色和顶点放入到同一个数组中。

1.准备数据

````
static final float squareCoords[] = {
            -0.5f,  0.5f, 0.0f,   1.0f, 0.0f, 0.0f, 1.0f,// top left
            -0.5f, -0.5f, 0.0f,   0.0f, 1.0f, 0.0f, 1.0f,// bottom left
             0.5f, -0.5f, 0.0f,   0.0f, 0.0f, 1.0f, 1.0f,// bottom right
             0.5f,  0.5f, 0.0f,   1.0f, 1.0f, 0.0f, 1.0f,// top right
    };
    
        static final short indexes[] = {
            0, 1, 2,
            0, 2, 3,
    };
````

2.数据放入本地缓存

```
private void setupVertexBuffer() {
        // initialize vertex float buffer for shape coordinates
        vertexBuffer = BufferUtils.newFloatBuffer(squareCoords.length);

        // add the coordinates to the FloatBuffer
        vertexBuffer.put(squareCoords);

        // set the buffer to read the first coordinate
        vertexBuffer.position(0);


        //copy vertices from cpu to the gpu
        IntBuffer buffer = IntBuffer.allocate(1);
        GLES20.glGenBuffers(1, buffer);
        vertexBufferId = buffer.get(0);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vertexBufferId);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, squareCoords.length * SIZE_OF_FLOAT, vertexBuffer, GLES20.GL_STATIC_DRAW);

        vertexCount = squareCoords.length / (COORDS_PER_VERTEX + COLORS_PER_VERTEX);
        vertexStride = (COORDS_PER_VERTEX + COLORS_PER_VERTEX) * SIZE_OF_FLOAT; // 4 bytes per vertex
    }




 private void setupIndexBuffer() {
        // initialize index short buffer for index
        indexBuffer = BufferUtils.newShortBuffer(indexes.length);
        indexBuffer.put(indexes);
        indexBuffer.position(0);

        IntBuffer buffer = IntBuffer.allocate(1);
        GLES20.glGenBuffers(1, buffer);
        indexBufferId = buffer.get(0);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, indexBufferId);
        GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, indexes.length * SIZE_OF_SHORT, indexBuffer, GLES20.GL_STATIC_DRAW);
    }
```

3，画图

```
   public void draw() {

        shader.begin();

        shader.enableVertexAttribute("a_Position");
        shader.setVertexAttribute("a_Position", COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexStride, 0);

        shader.enableVertexAttribute("a_Color");
        shader.setVertexAttribute("a_Color", COLORS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexStride, COORDS_PER_VERTEX * SIZE_OF_FLOAT);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vertexBufferId);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, indexBufferId);
        GLES20.glDrawElements(
                GLES20.GL_TRIANGLES,        // mode
                indexes.length,             // count
                GLES20.GL_UNSIGNED_SHORT,   // type
                0);                         // offset

        shader.disableVertexAttribute("a_Position");
        shader.disableVertexAttribute("a_Color");

        shader.end();
    }
```

### 第五例 使用一个model

```
public class Model {
    private static final int COORDS_PER_VERTEX = 3;
    private static final int COLORS_PER_VERTEX = 4;
    private static final int SIZE_OF_FLOAT = 4;
    private static final int SIZE_OF_SHORT = 2;

    private ShaderProgram shader;
    private String name;
    private float vertices[];
    private short indices[];


    private FloatBuffer vertexBuffer;
    private int vertexBufferId;
    private int vertexStride;

    private ShortBuffer indexBuffer;
    private int indexBufferId;

    public Model(String name, ShaderProgram shader, float[] vertices, short[] indices) {
        this.name = name;
        this.shader = shader;
        this.vertices = Arrays.copyOfRange(vertices, 0, vertices.length);
        this.indices = Arrays.copyOfRange(indices, 0, indices.length);

        setupVertexBuffer();
        setupIndexBuffer();
    }

    private void setupVertexBuffer() {
        vertexBuffer = BufferUtils.newFloatBuffer(vertices.length);
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        IntBuffer buffer = IntBuffer.allocate(1);
        GLES20.glGenBuffers(1, buffer);
        vertexBufferId = buffer.get(0);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vertexBufferId);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, vertices.length * SIZE_OF_FLOAT, vertexBuffer, GLES20.GL_STATIC_DRAW);
        vertexStride = (COORDS_PER_VERTEX + COLORS_PER_VERTEX) * SIZE_OF_FLOAT; // 4 bytes per vertex
    }

    private void setupIndexBuffer() {
        // initialize index short buffer for index
        indexBuffer = BufferUtils.newShortBuffer(indices.length);
        indexBuffer.put(indices);
        indexBuffer.position(0);

        IntBuffer buffer = IntBuffer.allocate(1);
        GLES20.glGenBuffers(1, buffer);
        indexBufferId = buffer.get(0);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, indexBufferId);
        GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, indices.length * SIZE_OF_SHORT, indexBuffer, GLES20.GL_STATIC_DRAW);
    }

    public void draw() {

        shader.begin();

        shader.enableVertexAttribute("a_Position");
        shader.setVertexAttribute("a_Position", COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexStride, 0);

        shader.enableVertexAttribute("a_Color");
        shader.setVertexAttribute("a_Color", COLORS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexStride, COORDS_PER_VERTEX * SIZE_OF_FLOAT);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vertexBufferId);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, indexBufferId);
        GLES20.glDrawElements(
                GLES20.GL_TRIANGLES,        // mode
                indices.length,             // count
                GLES20.GL_UNSIGNED_SHORT,   // type
                0);                         // offset

        shader.disableVertexAttribute("a_Position");
        shader.disableVertexAttribute("a_Color");

        shader.end();
    }
}

```

```
public class Square extends Model {



    static final float squareCoords[] = {
            -0.5f,  0.5f, 0.0f,   1.0f, 0.0f, 0.0f, 1.0f,// top left
            -0.5f, -0.5f, 0.0f,   0.0f, 1.0f, 0.0f, 1.0f,// bottom left
             0.5f, -0.5f, 0.0f,   0.0f, 0.0f, 1.0f, 1.0f,// bottom right
             0.5f,  0.5f, 0.0f,   1.0f, 1.0f, 0.0f, 1.0f,// top right
    };

    static final short indices[] = {
            0, 1, 2,
            0, 2, 3,
    };

    public Square(ShaderProgram shader) {
        super("square", shader, squareCoords, indices);
    }
}

```



### 第六例 设置移动

1.设置变换矩阵

```
    public Matrix4f modelMatrix() {
        Matrix4f mat = new Matrix4f(); // make a new identitiy 4x4 matrix
        mat.translate(position.x, position.y, position.z);
        mat.rotate(rotationX, 1.0f, 0.0f, 0.0f);
        mat.rotate(rotationY, 0.0f, 1.0f, 0.0f);
        mat.rotate(rotationZ, 0.0f, 0.0f, 1.0f);
        mat.scale(scale, scale, scale);
        return mat;
    }

```

2.将矩阵传给着色器

```
shader.setUniformMatrix("u_ModelViewMatrix", modelMatrix());
```



准备变换数据，准备变换矩阵

```
    public Matrix4f modelMatrix() {
        Matrix4f mat = new Matrix4f(); // make a new identitiy 4x4 matrix
        mat.translate(position.x, position.y, position.z);
        mat.rotate(rotationX, 1.0f, 0.0f, 0.0f);
        mat.rotate(rotationY, 0.0f, 1.0f, 0.0f);
        mat.rotate(rotationZ, 0.0f, 0.0f, 1.0f);
        mat.scale(scale, scale, scale);
        return mat;
    }
```





变换动作

```
    public void updateWithDelta(long dt) {

        final float secsPerMove = 2.0f * ONE_SEC;
        square.setPosition(new Float3(
                (float)(Math.sin(System.currentTimeMillis() * 2 * Math.PI / secsPerMove)),
                square.position.y,
                square.position.z)
        );

        square.draw(dt);
    }
```



### 光照

