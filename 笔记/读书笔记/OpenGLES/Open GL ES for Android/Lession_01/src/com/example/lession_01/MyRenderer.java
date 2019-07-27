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
	//�����������
	private static final String U_MATRIX = "u_Matrix";
	//���嶥������洢����
	private final float[] projectMatrix = new float[16];
	//������Ҫһ������������λ��
	private int uMatrixLocation ;
	//ÿ��������������
	private static final int POSITION_COMPONENT_COUNT = 2;
	//��ɫ�ĸ���
	private static final int COLOR_COMPONENT_COUNT=3; 
	//��ɫ
	private static final String A_COLOR="a_Color";
	//ÿ�����ݵ��ֽ���
	private static final int BYTES_PER_FLOAT = 4;
	//ÿ����ת�ĸ���
	private static final int STRDE = (POSITION_COMPONENT_COUNT+COLOR_COMPONENT_COUNT)*BYTES_PER_FLOAT;
	//���Ƶ����ش洢��λ��
	private final FloatBuffer vertexData;
	//����Context
	private Context context;
	//�õ����ӳ���ID
	private int progarm ;
	private int uColorLocation ;
	//��ȡ����λ��
	private static final String A_POSITION="a_Position";
	private int aPosition ;
	private int aColorLocation;
	public MyRenderer(Context context) {
		this.context = context;
		float []tableVertices = {
				/*
				 * �����ȶ���
				 * 
				 * 00�����ã�����-0.5��Ϊһ�ܣ����غ�
				 * Ϊ�˿��Ա�ʾ��ɫ�����ʱ���ڵ��������������ɫ
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
		//���ض�����ɫ��
		String vertextShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.first_vertext_shaper);
		//����Ƭ����ɫ��
		String fragmentShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.first_fragment_shader);
	
		int vertextShader = ShaderHelper.compileVertexShader(vertextShaderSource);
		int fragmentShader = ShaderHelper.comileFileFragmentShader(fragmentShaderSource);
		
		/*���洴���˶��㣬��ɫ�����Լ������߰���һ���ˣ����ؾ��ǽ���ɫ�����뵽OpenGLES*/
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
