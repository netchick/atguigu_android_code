package com.atguigu.l11_context;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {

	public MyApplication() {
		Log.e("TAG", "MyApplication()");
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.e("TAG", "MyApplication onCreate()");
	}

	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
