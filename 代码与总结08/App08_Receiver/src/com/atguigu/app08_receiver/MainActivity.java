package com.atguigu.app08_receiver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void startListenCall(View v) {
		//startService(new Intent(this, ListenCallService.class));
	}
	
	public void stopListenCall(View v) {
		//stopService(new Intent(this, ListenCallService.class));
	}
}
