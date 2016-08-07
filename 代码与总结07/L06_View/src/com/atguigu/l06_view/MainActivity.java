package com.atguigu.l06_view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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
	
	//强制重新绘
	public void forceDraw(View v) {
		//myTextView.invalidate();
		//myTextView.postInvalidate();
		new Thread(){
			public void run() {
				//myTextView.invalidate();//不可以, 只能在主线程执行
				myTextView.postInvalidate(); //可以在主线程和分线程执行
			}
		}.start();
		Toast.makeText(this, "强制重新绘", 0).show();
	}
	
	public void removeView(View v) {
		ViewGroup viewGroup = (ViewGroup) myTextView.getParent();
		viewGroup.removeView(myTextView);
		
		
	}
}
