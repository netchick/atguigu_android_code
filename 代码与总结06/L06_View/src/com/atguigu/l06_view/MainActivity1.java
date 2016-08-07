package com.atguigu.l06_view;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class MainActivity1 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//设置id为content的布局的子View
		//setContentView(R.layout.activity_main);
		
		/*
		 * 1. setContentView(int layoutId)
		 * 2. setContentView(View view)
		 * 		2.1. 动态加载布局文件得到视图对象
		 * 		2.2. 动态创建视图对象
		 */
		//2.1. 动态加载布局文件得到视图对象
		//View view = View.inflate(this, R.layout.activity_main, null);
		
		//2.2. 动态创建视图对象
		TextView view  = new TextView(this);
		view.setBackgroundColor(Color.RED);
		view.setText("atguigu.com");
		setContentView(view);
		
		Window window = getWindow();
		View decorView = window.getDecorView();
		Log.e("TAG", decorView.toString());
		Log.e("TAG", decorView.getClass().getSuperclass().getName());
		
	}
}
