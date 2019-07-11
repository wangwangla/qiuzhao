package com.example.lession_01;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.example.util.ShaderHelper;
import com.example.util.TextResourceReader;

import static android.opengl.GLES10.glViewport;
import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.GL_LINES;
import static android.opengl.GLES20.GL_POINTS;
import static android.opengl.GLES20.GL_TRIANGLES;
import static android.opengl.GLES20.GL_TRIANGLE_FAN;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniform4f;
import static android.opengl.GLES20.glUniformMatrix4fv;
import static android.opengl.GLES20.glUseProgram;
import static android.opengl.GLES20.glVertexAttribPointer;
import static android.opengl.Matrix.orthoM;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import static android.opengl.GLES10.glClearColor;
import static android.opengl.GLES10.glClear;
import static android.opengl.GLES10.GL_COLOR_BUFFER_BIT;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;

public class MyRenderer implements Renderer {
	//加入矩阵数组
	private static final String U_MATRIX = "u_Matrix";
	//定义顶点数组存储矩阵
	private final float[] projectMatrix = new float[16];
	//我们需要一个整形来保存位置
	private int uMatrixLocation ;
	//每个顶点的坐标个数
	private static final int POSITION_COMPONENT_COUNT = 2;
	//颜色的个数
	private static final int COLOR_COMPONENT_COUNT=3; 
	//颜色
	private static final String A_COLOR="a_Color";
	//每个数据的字节数
	private static final int BYTES_PER_FLOAT = 4;
	//每次跳转的个数
	private static final int STRDE = (POSITION_COMPONENT_COUNT+COLOR_COMPONENT_COUNT)*BYTES_PER_FLOAT;
	//复制到本地存储的位置
	private final FloatBuffer vertexData;
	//定义Context
	private Context context;
	//得到连接程序ID
	private int progarm ;
	private int uColorLocation ;
	//获取属性位置
	private static final String A_POSITION="a_Position";
	private int aPosition ;
	private int aColorLocation;
	public MyRenderer(Context context) {
		this.context = context;
		float []tableVertices = {
				/*
				 * 三角扇顶点
				 * 
				 * 00被公用，最后的-0.5变为一周，被重合
				 * 为了可以表示颜色，这个时候在点坐标的最后加上颜色
				 * */
	               0f,    0f,   1f,   1f,   1f,         
	               -0.5f, -0.5f, 0.7f, 0.7f, 0.7f,            
	                0.5f, -0.5f, 0.7f, 0.7f, 0.7f,
	                0.5f,  0.5f, 0.7f, 0.7f, 0.7f,
	               -0.5f,  0.5f, 0.7f, 0.7f, 0.7f,
	               -0.5f, -0.5f, 0.7f, 0.7f, 0.7f,

	               // Line 1
	               -0.5f, 0f, 1f, 0f, 0f,
	                0.5f, 0f, 1f, 0f, 0f,

	               // Mallets
	               0f, -0.25f, 0f, 0f, 1f,
	               0f,  0.25f, 1f, 0f, 0f
		};

		vertexData = ByteBuffer.allocateDirect(BYTES_PER_FLOAT*tableVertices.length).order(ByteOrder.nativeOrder()).asFloatBuffer();
		vertexData.put(tableVertices);
	}
	@Override
	public void onDrawFrame(GL10 arg0) {
		// TODO Auto-generated method stub
		glClear(GL_COLOR_BUFFER_BIT);
		glUniformMatrix4fv(uMatrixLocation, 1, false, projectMatrix,0);
		glDrawArrays(GL_TRIANGLE_FAN, 0, 6);

	}

	@Override
	public void onSurfaceChanged(GL10 arg0, int width, int height) {
		glViewport(0,0,width,height);
		final float aspectRatio = width>height?(float)width / (float)height:
			height / (float)width;
		if(width>height) {
			orthoM(projectMatrix, 0, -aspectRatio, aspectRatio, -1f, 1f, -1f, 1f);
		}else {
			orthoM(projectMatrix, 0, -1f, 1f, -aspectRatio, aspectRatio, -1f, 1f);
		}
	}

	@Override
	public void onSurfaceCreated(GL10 arg0, EGLConfig arg1) {
		glClearColor(1.0f,0.0f,0.0f,0.0f);
		//加载顶点着色器
		String vertextShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.first_vertext_shaper);
		//加载片段着色器
		String fragmentShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.first_fragment_shader);
	
		int vertextShader = ShaderHelper.compileVertexShader(vertextShaderSource);
		int fragmentShader = ShaderHelper.comileFileFragmentShader(fragmentShaderSource);
		
		/*上面创建了顶点，着色器，以及将二者绑定在一起了，下载就是将着色器放入到OpenGLES*/
		progarm = ShaderHelper.linkProgram(vertextShader, fragmentShader);
		ShaderHelper.validateProgram(progarm);
		glUseProgram(progarm);
		aColorLocation = glGetAttribLocation(progarm, A_COLOR);
		aPosition = glGetAttribLocation(progarm, A_POSITION);
		uMatrixLocation = glGetUniformLocation(progarm, U_MATRIX);
		vertexData.position(0);
		glVertexAttribPointer(aPosition, POSITION_COMPONENT_COUNT, GL_FLOAT, false, STRDE, vertexData);
		glEnableVertexAttribArray(aPosition);
		vertexData.position(POSITION_COMPONENT_COUNT);
		glVertexAttribPointer(aColorLocation, COLOR_COMPONENT_COUNT, GL_FLOAT, false, STRDE, vertexData);
		glEnableVertexAttribArray(aColorLocation);
	}
}
