package com.example.setting;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

public class MyBringBack extends View {

	Bitmap gBall;
	float changingY;
	Typeface font;

	public MyBringBack(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		gBall = BitmapFactory.decodeResource(getResources(),
				R.drawable.greenball);
		changingY = 0;
		font = Typeface.createFromAsset(context.getAssets(), "G-Unit.TTF");
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
		
		Paint textPaint = new Paint();
		textPaint.setARGB(50,254,10,50);
		textPaint.setTextSize(50);
		textPaint.setTextAlign(Align.CENTER);
		textPaint.setTypeface(font);
		
		canvas.drawText("MyBringBack", canvas.getWidth()/2, 200, textPaint);
		
		canvas.drawBitmap(gBall, canvas.getWidth() / 2, changingY, null);
		if (changingY < canvas.getHeight())
			changingY += 10;
		else
			changingY = 0;

		Rect middlerect = new Rect();
		middlerect.set(0,400,canvas.getWidth(),550);
		Paint ourBlue = new Paint();
		ourBlue.setColor(Color.BLUE);
		canvas.drawRect(middlerect, ourBlue);
		invalidate();
	}

}
