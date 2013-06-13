package com.example.setting;

import android.R;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerOpenListener;

public class Sliding extends Activity implements OnClickListener,
		OnCheckedChangeListener, OnDrawerOpenListener {

	SlidingDrawer sd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(com.example.setting.R.layout.sliding);
		Button handle1 = (Button) findViewById(com.example.setting.R.id.handle1);
		Button handle2 = (Button) findViewById(com.example.setting.R.id.handle2);
		Button handle3 = (Button) findViewById(com.example.setting.R.id.handle3);
		Button handle4 = (Button) findViewById(com.example.setting.R.id.handle4);
		CheckBox cb = (CheckBox) findViewById(com.example.setting.R.id.cbslidable);
		sd = (SlidingDrawer) findViewById(com.example.setting.R.id.slidingD);
		sd.setOnDrawerOpenListener(this);
		handle1.setOnClickListener(this);
		handle2.setOnClickListener(this);
		handle3.setOnClickListener(this);
		handle4.setOnClickListener(this);
		cb.setOnCheckedChangeListener(this);
	}

	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		if (isChecked) {
			sd.lock();
		} else {
			sd.unlock();
		}

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case com.example.setting.R.id.handle1:
			sd.open();
			break;
		case com.example.setting.R.id.handle2:
			break;
		case com.example.setting.R.id.handle3:
			sd.toggle();
			break;
		case com.example.setting.R.id.handle4:
			sd.close();
			break;
		}
	}

	public void onDrawerOpened() {
		// TODO Auto-generated method stub
		MediaPlayer mp  = MediaPlayer.create(this, com.example.setting.R.raw.explosion);
		mp.start();
	}

}
