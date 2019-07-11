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
		//以后无论谁使用，都需要将这个ID带上才可以使用这个对象，如果为0 ，那么就创建失败。
		final int ShaderObjectId = glCreateShader(glVertexShader);
		/*将代码上传，将代码和着色器绑定起来*/
		glShaderSource(ShaderObjectId, shaderCode);
		/*下来就可以进行编译*/
		glCompileShader(ShaderObjectId);
		/**
		 * 编译之后的状态
		 */
		final int[] compileStatus = new int[1];
		//读取这个着色器中的着色器状态，并写入
		glGetShaderiv(ShaderObjectId, GL_COMPILE_STATUS, compileStatus,0);
		if(compileStatus[0]==0) {
			glDeleteShader(ShaderObjectId);
			return 0;
		}
		return ShaderObjectId;
	}
	public static int linkProgram(int vertexShaderId,int fragmentShaderId) {
		//我们新建一个程序对象，并将那个对象存入一个id中
		final int prigramObjectId = glCreateProgram();
		if(prigramObjectId==0) {
			return 0;
		}
		glAttachShader(prigramObjectId, vertexShaderId);
		glAttachShader(prigramObjectId, fragmentShaderId);
		//连接程序
		glLinkProgram(prigramObjectId);
		final int[] linkStatus = new int[1];
		glGetProgramiv(prigramObjectId, GL_LINK_STATUS, linkStatus,0);
		//创建一个数组用来存储状态，检查是否有问题
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

