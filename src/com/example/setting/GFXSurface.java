package com.example.setting;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GFXSurface extends Activity implements OnTouchListener {

	MyBringBackSurface ourSurfaceView;
	float x, y, dx, dy, fx, fy, sx, sy, anix, aniy, scaledx, scaledy;
	Bitmap test, plus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ourSurfaceView = new MyBringBackSurface(this);
		ourSurfaceView.setOnTouchListener(this);
		x = 0;
		y = 0;
		dx = dy = fx = fy = sx = sy = anix = aniy = scaledx = scaledy = 0;
		test = BitmapFactory.decodeResource(getResources(),
				R.drawable.greenball);
		plus = BitmapFactory.decodeResource(getResources(), R.drawable.plus);
		setContentView(ourSurfaceView);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSurfaceView.pause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ourSurfaceView.resume();
	}

	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		x = arg1.getX();
		y = arg1.getY();

		switch (arg1.getAction()) {
		case MotionEvent.ACTION_UP:
			fx = arg1.getX();
			fy = arg1.getY();
			dx = fx - sx;
			dy = fy - sy;
			scaledx = dx / 30;
			scaledy = dy / 30;
			x = y = 0;

			break;
		case MotionEvent.ACTION_DOWN:
			sx = arg1.getX();
			sy = arg1.getY();
			dx = dy = fx = fy = anix = aniy = scaledx = scaledy = 0;
			break;
		}

		return true;
	}

	public class MyBringBackSurface extends SurfaceView implements Runnable {

		SurfaceHolder ourHolder;
		Thread ourThread;
		boolean isRunning = true;

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
				if (x != 0 && y != 0) {

					canvas.drawBitmap(test, x - (test.getWidth() / 2), y
							- (test.getHeight() / 2), null);
				}
				if (sx != 0 && sy != 0) {

					canvas.drawBitmap(plus, sx - (plus.getWidth() / 2), sy
							- (plus.getHeight() / 2), null);
				}
				if (fx != 0 && fy != 0) {

					canvas.drawBitmap(test, fx - (test.getWidth() / 2) - anix,
							fy - (test.getHeight() / 2) - aniy, null);
					canvas.drawBitmap(plus, fx - (plus.getWidth() / 2), fy
							- (plus.getHeight() / 2), null);
				}
				anix += scaledx;
				aniy += scaledy;
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

}
