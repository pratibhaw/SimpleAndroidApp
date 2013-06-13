package com.example.setting;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

public class Tabs extends Activity implements OnClickListener {

	TabHost th;
	long start, stop;
	TextView showresults ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		Button startwatch = (Button) findViewById(R.id.button1);
		Button stopwatch = (Button) findViewById(R.id.button2);
		Button baddtab = (Button) findViewById(R.id.baddTab);
		showresults = (TextView) findViewById(R.id.textView1);
		startwatch.setOnClickListener(this);
		stopwatch.setOnClickListener(this);
		baddtab.setOnClickListener(this);
		th = (TabHost) findViewById(R.id.tabhost);
		th.setup();
		TabSpec spec = th.newTabSpec("tag1");
		spec.setContent(R.id.tab1);
		spec.setIndicator("StopWatch");
		spec = th.newTabSpec("tag2");
		spec.setContent(R.id.tab2);
		spec.setIndicator("Tab2");
		spec = th.newTabSpec("tag3");
		spec.setContent(R.id.tab3);
		spec.setIndicator("Add a Tab");
		th.addTab(spec);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.baddTab:
			TabSpec ourspec = th.newTabSpec("tag1");
			ourspec.setContent(new TabHost.TabContentFactory() {

				public View createTabContent(String tag) {
					// TODO Auto-generated method stub
					TextView tv = new TextView(Tabs.this);
					tv.setText("You have created new Tab");
					return tv;
				}
			});
			ourspec.setIndicator("New");
			th.addTab(ourspec);
			break;
		case R.id.button1:
			start = System.currentTimeMillis();
			break;
		case R.id.button2:
			if(start!=0){
				stop = System.currentTimeMillis();
				showresults.setText(Long.toString(stop-start));
				//start=0;
			}
			break;
		}

	}
}
