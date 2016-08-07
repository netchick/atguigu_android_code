package com.atguigu.l02_lauchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * 
 */
public class MainActivity extends Activity{

	public MainActivity() {
		Log.e("TAG", "MainActivity()");
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.e("TAG", "onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	

	public void startSecond(View v) {
		startActivity(new Intent(this, SecondActivity.class));
	}
	
	public void startFrist(View v) {
		startActivity(new Intent(this, MainActivity.class));
	}
}
