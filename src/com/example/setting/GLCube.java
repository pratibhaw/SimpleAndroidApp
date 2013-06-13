package com.example.setting;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class GLCube extends Activity{
	
	GLSurfaceView ourSurface ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ourSurface = new GLSurfaceView(this);
		ourSurface.setRenderer(new GLCubeRendererEx());
		setContentView(ourSurface);
		
	}
	
	

}
