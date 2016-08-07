package com.atguigu.l11_context;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		//取保存application对象中的数据
		
		//得到application对象
		MyApplication application = (MyApplication) getApplicationContext();
		//取数据
		String data = application.getData();
		Log.e("TAG", "service data="+data);
		
		return super.onStartCommand(intent, flags, startId);
	}
	

}
