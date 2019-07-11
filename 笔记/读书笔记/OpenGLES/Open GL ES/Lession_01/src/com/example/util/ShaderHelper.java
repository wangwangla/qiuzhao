package com.example.util;

import static android.opengl.GLES10.GL_VERTEX_ARRAY;
import static android.opengl.GLES20.GL_COMPILE_STATUS;
import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.GL_LINK_STATUS;
import static android.opengl.GLES20.GL_VALIDATE_STATUS;
import static android.opengl.GLES20.GL_VERTEX_SHADER;
import static android.opengl.GLES20.glAttachShader;
import static android.opengl.GLES20.glCompileShader;
import static android.opengl.GLES20.glCreateProgram;
import static android.opengl.GLES20.glCreateShader;
import static android.opengl.GLES20.glDeleteShader;
import static android.opengl.GLES20.glGetProgramiv;
import static android.opengl.GLES20.glGetShaderiv;
import static android.opengl.GLES20.glLinkProgram;
import static android.opengl.GLES20.glShaderSource;
import static android.opengl.GLES20.glValidateProgram;;

public class ShaderHelper {
	private static final String TAG = "ShaderHelper";
	public static int compileVertexShader(String shaderCode) {
		return compileShader(GL_VERTEX_SHADER,shaderCode);
	}

	public static int comileFileFragmentShader(String shaderCode) {
		return compileShader(GL_FRAGMENT_SHADER ,shaderCode);
	}
	
	private static int compileShader(int glVertexShader, String shaderCode) {
		// TODO Auto-generated method stub
		//�Ժ�����˭ʹ�ã�����Ҫ�����ID���ϲſ���ʹ������������Ϊ0 ����ô�ʹ���ʧ�ܡ�
		final int ShaderObjectId = glCreateShader(glVertexShader);
		/*�������ϴ������������ɫ��������*/
		glShaderSource(ShaderObjectId, shaderCode);
		/*�����Ϳ��Խ��б���*/
		glCompileShader(ShaderObjectId);
		/**
		 * ����֮���״̬
		 */
		final int[] compileStatus = new int[1];
		//��ȡ�����ɫ���е���ɫ��״̬����д��
		glGetShaderiv(ShaderObjectId, GL_COMPILE_STATUS, compileStatus,0);
		if(compileStatus[0]==0) {
			glDeleteShader(ShaderObjectId);
			return 0;
		}
		return ShaderObjectId;
	}
	public static int linkProgram(int vertexShaderId,int fragmentShaderId) {
		//�����½�һ��������󣬲����Ǹ��������һ��id��
		final int prigramObjectId = glCreateProgram();
		if(prigramObjectId==0) {
			return 0;
		}
		glAttachShader(prigramObjectId, vertexShaderId);
		glAttachShader(prigramObjectId, fragmentShaderId);
		//���ӳ���
		glLinkProgram(prigramObjectId);
		final int[] linkStatus = new int[1];
		glGetProgramiv(prigramObjectId, GL_LINK_STATUS, linkStatus,0);
		//����һ�����������洢״̬������Ƿ�������
		if(linkStatus[0]==0) {
			return 0;
		}
		return prigramObjectId;
	}
	
	public static boolean validateProgram(int programObjectId) {
		glValidateProgram(programObjectId);
		final int [] validateStatus = new int[1];
		glGetProgramiv(programObjectId, GL_VALIDATE_STATUS, validateStatus,0);
		return validateStatus[0]!=0;
	}
}

