package com.atguigu.app08_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * 接收来了短信的广播的receiver
 * @author 张晓飞
 *
 */
public class SmsReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//1. 得到intent短信数据, 并封装为短信对象smsMessage
		Bundle extras = intent.getExtras();
		Object[] pdus = (Object[])extras.get("pdus");
		SmsMessage smsMessage = SmsMessage.createFromPdu((byte[])pdus[0]);
		//2. 取号码
		String number = smsMessage.getOriginatingAddress();
		String content = smsMessage.getMessageBody();
		Log.e("TAG", number +" : "+content);
		//3. 判断是否是黑名单号
		if("110".equals(number)) {
			//4. 如果是, 中断广播(拦截短信)
			abortBroadcast();
			Log.e("TAG", "拦截到一个黑名单短信");
		}
	}

}
