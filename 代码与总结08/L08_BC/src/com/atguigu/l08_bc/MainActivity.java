package com.atguigu.l08_bc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	//发送一般广播
	public void sendNormalBC(View v) {
		Intent intent = new Intent("com.atguigu.l08_br.MyReceiver1.action");
		intent.putExtra("action", "gaogao");
		sendBroadcast(intent );
		Toast.makeText(this, "发送一般广播", 0).show();
	}
	//发送有序广播
	public void sendOrderBC(View v) {
		Intent intent = new Intent("com.atguigu.l08_br.MyReceiver.action2");
		intent.putExtra("action", "TATA");
		sendOrderedBroadcast(intent, null);
		Toast.makeText(this, "发送有序广播", 0).show();
	}
}
