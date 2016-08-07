package com.atguigu.l08_br;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	private MyReceiver2 receiver;
	/*
	 * 注册广播接收器
	 */
	public void registBR(View v) {
		
		if(receiver==null) {
			//创建receiver对象
			receiver = new MyReceiver2();
			//创建过滤器对象
			IntentFilter filter = new IntentFilter("com.atguigu.l08_br.MyReceiver1.action");
			//注册receiver
			registerReceiver(receiver, filter);
			Toast.makeText(this, "注册广播接收器", 0).show();
		} else {
			Toast.makeText(this, "已经注册了广播接收器", 0).show();
		}
		
	}
	public void unRegistBR(View v) {
		if(receiver!=null) {
			unregisterReceiver(receiver);
			receiver = null;
			Toast.makeText(this, "解注册广播接收器", 0).show();
		} else {
			Toast.makeText(this, "还没有注册广播接收器", 0).show();
		}
		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(receiver!=null) {
			unregisterReceiver(receiver);
			receiver = null;
		}
	}
}
