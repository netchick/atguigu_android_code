package com.atguigu.l06_event;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

public class KeyEventTestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_key);
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		Log.e("TAG", "dispatchKeyEvent() action="+event.getAction()+" code="+event.getKeyCode());
		return super.dispatchKeyEvent(event);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.e("TAG", "onKeyDown() action="+event.getAction()+" code="+event.getKeyCode());
		
		event.startTracking();//追踪事件, 用于长按监听
		
		return true; //返回必须是true
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		Log.e("TAG", "onKeyUp() action="+event.getAction()+" code="+event.getKeyCode());
		
		return super.onKeyUp(keyCode, event); //不执行super就可以不退出界面
		//return true;
	}
	
	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		Log.i("TAG", "onKeyLongPress() action="+event.getAction()+" code="+event.getKeyCode());
		return super.onKeyLongPress(keyCode, event);
	}
}
