package com.example.lession_01;

import android.R.bool;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {
	//引入一个gLSurfaceView
	private GLSurfaceView gLSurfaceView;
	//设置GLSurfaceView的状态
	private boolean renderSet = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		gLSurfaceView = new GLSurfaceView(this);
		//获取信息，是否支持OpenGLES
		final ActivityManager activityManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
		final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
		final boolean supportsEs2 = configurationInfo.reqGlEsVersion>=0x20000;
		System.out.println(supportsEs2+"=========support参数=========");
		//判断是否支持openGLES
		if(supportsEs2) {
			gLSurfaceView.setEGLContextClientVersion(2);
			gLSurfaceView.setRenderer(new MyRenderer(MainActivity.this));
			renderSet=true;
		}else {
			Toast.makeText(this, "支持", Toast.LENGTH_LONG).show();
			return ;
		}
		setContentView(gLSurfaceView);
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		gLSurfaceView.onPause();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		gLSurfaceView.onResume();
	}
}
