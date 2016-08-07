package com.atguigu.l08_br;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 动态注册的receiver
 * @author 张晓飞
 *
 */
public class MyReceiver2 extends BroadcastReceiver {

	public MyReceiver2() {
		Log.e("TAG", "MyReceiver2()");
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getStringExtra("action");
		Log.e("TAG", "MyReceiver2 onReceive() "+action);
	}

}
