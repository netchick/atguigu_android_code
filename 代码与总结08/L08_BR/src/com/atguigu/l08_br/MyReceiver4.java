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
public class MyReceiver4 extends BroadcastReceiver {

	public MyReceiver4() {
		Log.e("TAG", "MyReceiver4()");
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getStringExtra("action");
		Log.e("TAG", "MyReceiver4 onReceive() "+action);
		
		if(isOrderedBroadcast()) {
			//中断广播
			abortBroadcast();
		}
		
	}

}
