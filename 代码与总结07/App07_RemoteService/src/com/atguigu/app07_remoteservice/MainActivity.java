package com.atguigu.app07_remoteservice;

import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

import com.android.internal.telephony.ITelephony;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void endCall(View v) throws Exception {
		//通过反射调用隐藏的API
			//得到隐藏类的Class对象
		Class c = Class.forName("android.os.ServiceManager");
			//得到方法所对应的Method对象
		Method method = c.getMethod("getService", String.class);
			//调用方法
		IBinder iBinder = (IBinder) method.invoke(null, Context.TELEPHONY_SERVICE);
		//得到接口对象
		ITelephony telephony = ITelephony.Stub.asInterface(iBinder);
		//结束通话
		telephony.endCall();
	}
}
