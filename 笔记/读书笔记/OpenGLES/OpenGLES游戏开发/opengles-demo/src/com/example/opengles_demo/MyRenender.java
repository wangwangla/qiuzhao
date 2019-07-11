package com.example.opengles_demo;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;

public class MyRenender implements Renderer {
    private Context context;
    private Square square;
	public MyRenender(Context context) {
		this.context = context;
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		GLES20.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
		square = new Square(context);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		GLES20.glViewport(0, 0, width, height);
	}
	private double redValue=1.0f;
	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		//GLES20.glClearColor((float)redValue, 0.0f, 0.0f, 1.0f);
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
		 //redValue = ((Math.sin(System.currentTimeMillis() * 2 * Math.PI / 10000.0) * 0.5) + 0.5); 
		square.draw();
	}
}
