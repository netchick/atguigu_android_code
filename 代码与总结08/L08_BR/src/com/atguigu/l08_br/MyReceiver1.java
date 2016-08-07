package com.atguigu.l08_br;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 静态注册的receiver
 * @author 张晓飞
 *
 */
public class MyReceiver1 extends BroadcastReceiver {

	public MyReceiver1() {
		Log.e("TAG", "MyReceiver1()");
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getStringExtra("action");
		Log.e("TAG", "MyReceiver1 onReceive() "+action);
	}

}
