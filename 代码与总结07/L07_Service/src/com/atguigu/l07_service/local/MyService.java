package com.atguigu.l07_service.local;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * 自定义本地服务
 * @author 张晓飞
 *
 */
/*
1. startService(intent)
	第一次调用 : -->构造方法()-->onCreate()-->onStartCommand()
	(重要)后面再调用 : -->onStartCommand()
	stopService() : -->onDestory()
2. bindService(intent, serviceConnection)
	调用 : -->构造方法()-->onCreate()-->onBind()-->onServiceConnected()
	unbindService(): (中有当前Activity与Service连接)-->onUnbind()-->onDestroy()
		
 */
public class MyService extends Service {

	public MyService() {
		Log.e("TAG", "MyService()");
	}
	
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.e("TAG", "MyService onCreate()");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.e("TAG", "MyService onStartCommand()");
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.e("TAG", "MyService onDestroy()");
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		Log.e("TAG", "onBind()");
		return new Binder();
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		Log.e("TAG", "onUnbind()");
		return super.onUnbind(intent);
	}
}
