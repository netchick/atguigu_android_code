package com.atguigu.l06_view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

	private MyTextView myTextView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*
			MyTextView textView = new MyTextView(this);
			textView.setText("www.atguigu.com");
			setContentView(textView);
		*/
		
		//setContentView(R.layout.activity_main);
		
		LinearLayout layout = (LinearLayout) View.inflate(this, R.layout.activity_main, null);
		
		//根据下标得到子View
		myTextView = (MyTextView) layout.getChildAt(0);
		
		setContentView(layout);
	}
	
	@Override
	protected void onResume() {
		Log.i("TAG", "onResume()");
		super.onResume();
	}
	
	//强制重新布局
	public void forceLayout(View v) {
		myTextView.requestLayout();
		Toast.makeText(this, "强制重新布局", 0).show();
	}
}
