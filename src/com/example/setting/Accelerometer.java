package com.example.setting;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Accelerometer extends Activity implements SensorEventListener {

	SensorManager sm;
	Bitmap ball;
	Float x, y, sensorX, sensorY;
	MyBringBackSurface ourSurfaceView;

	public class MyBringBackSurface extends SurfaceView implements Runnable {

		SurfaceHolder ourHolder;
		Thread ourThread;
		boolean isRunning = false;

		public MyBringBackSurface(Context context) {
			super(context);
			ourHolder = getHolder();

			// TODO Auto-generated constructor stub
		}

		public void run() {
			// TODO Auto-generated method stub

			while (isRunning) {
				if (!ourHolder.getSurface().isValid())
					continue;
				Canvas canvas = ourHolder.lockCanvas();
				canvas.drawRGB(02, 02, 150);
				float centerX = canvas.getWidth() / 2;
				float centerY = canvas.getHeight() / 2;
				canvas.drawBitmap(ball, centerX + sensorX * 20, centerY
						+ sensorY * 20, null);
				ourHolder.unlockCanvasAndPost(canvas);
			}

		}

		public void pause() {
			// TODO Auto-generated method stub
			isRunning = false;
			while (true) {
				try {
					ourThread.join();
					break;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			ourThread = null;
		}

		public void resume() {
			// TODO Auto-generated method stub
			isRunning = true;
			ourThread = new Thread(this);
			ourThread.start();

		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		if (sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
			Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);

			sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
		}
		ball = BitmapFactory.decodeResource(getResources(),
				R.drawable.greenball);
		x = y = sensorX = sensorY = (float) 0.0;
		ourSurfaceView = new MyBringBackSurface(this);
		ourSurfaceView.resume();
		setContentView(ourSurfaceView);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		sm.unregisterListener(this);
		super.onPause();
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	public void onSensorChanged(SensorEvent e) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(16);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sensorX = e.values[0];
		sensorY = e.values[1];
	}

}
