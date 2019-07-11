package com.example.opengles_demo;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class OGLView extends GLSurfaceView{
	private Context context;
	public OGLView(Context context) {
		super(context);
		this.context = context;
		init();
	}

	public OGLView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}
	public void init() {
		setEGLContextClientVersion(2);
		setRenderer(new MyRenender(context));
	} 
	
}
