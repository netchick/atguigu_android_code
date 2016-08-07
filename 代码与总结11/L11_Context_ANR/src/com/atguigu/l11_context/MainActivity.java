package com.atguigu.l11_context;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Application application = getApplication();
		
		Context context = getApplicationContext();
		Log.e("TAG", "application==context = "+(application==context)); //true
		
		//在application对象中保存数据
		MyApplication myApp = (MyApplication) application;
		myApp.setData("atguigu.com");
		
		//启动服务
		startService(new Intent(this, MyService.class));
		
		Toast.makeText(getApplicationContext(), "----", 0).show();
	}
	
	public void testARN(View v) {
		Log.e("TAG", "testARN()....");
		SystemClock.sleep(10000);
		Log.e("TAG", "testARN2()....");
	}
}
