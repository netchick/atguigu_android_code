package com.example.l12_fragment1;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

/*
 * 测试使用Fragment(静态加载)
 *	1. 定义Fragment的子类, 并加载一个布局文件
 *  2. 在布局文件中通过<fragment>指定指定自定义Fragment
 *  3. 我们的Activity必须继承于FragmentActivity
 *  每个Fragment本质上都会生成一个FrameLayout, 它加载的布局为其子布局
 */
public class MainActivity extends FragmentActivity {

	public MainActivity() {
		Log.e("TAG", "MainActivity()..");
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.e("TAG", "MainActivity onCreate()..");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
}
