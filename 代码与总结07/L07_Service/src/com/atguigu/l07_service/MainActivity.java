package com.atguigu.l07_service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.atguigu.l07_service.local.MyService;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	//启动服务
	public void startMyService(View v) {
		Intent intent = new Intent(this, MyService.class);
		startService(intent);
		Toast.makeText(this, "start service", 0).show();
	}
	
	public void stopMyService(View v) {
		Intent intent = new Intent(this, MyService.class);
		stopService(intent);
		Toast.makeText(this, "stop service", 0).show();
	}
	
	private ServiceConnection conn;
	//绑定服务
	public void bindMyService(View v) {
		Intent intent = new Intent(this, MyService.class);
		//创建连接对象
		if(conn==null) {
			conn = new ServiceConnection() {
				@Override
				public void onServiceDisconnected(ComponentName name) {
					Log.e("TAG", "onServiceDisconnected()");
				}
				@Override
				public void onServiceConnected(ComponentName name, IBinder service) {
					Log.e("TAG", "onServiceConnected()");
				}
			};
			//绑定Service
			bindService(intent, conn, Context.BIND_AUTO_CREATE);
			
			Toast.makeText(this, "bind service", 0).show();
		} else {
			Toast.makeText(this, "已经bindservice", 0).show();
		}
		
	}
	
	//解绑服务
	public void unbindMyService(View v) {
		if(conn!=null) {
			unbindService(conn);
			conn = null;
			Toast.makeText(this, "unbind service", 0).show();
		} else {
			Toast.makeText(this, "还没有bindservice", 0).show();
		}
		
	}
	
	//在Activity死亡之前调用
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(conn!=null) {
			unbindService(conn);
			conn = null;
		}
	}
	
	
}
