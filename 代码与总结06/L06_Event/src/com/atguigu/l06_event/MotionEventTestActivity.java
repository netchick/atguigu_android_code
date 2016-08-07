package com.atguigu.l06_event;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * 测试MotionEvent的界面
 * @author 张晓飞
 *
 */
public class MotionEventTestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_touch);
		
		//得到MYimageView
		MyImageView iv_touch_test = (MyImageView) findViewById(R.id.iv_touch_test);
		//设置touch监听
		iv_touch_test.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.e("TAG", "MYimageView Listener onTouch() "+event.getAction());
				
				if(event.getAction()==MotionEvent.ACTION_DOWN){
					return true;
				}
				
				//return true;
				return false;
			}
		});
		
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		Log.i("TAG", "Activity dispatchTouchEvent() "+ev.getAction());
		return super.dispatchTouchEvent(ev);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.i("TAG", "Activity onTouchEvent() "+event.getAction());
		return super.onTouchEvent(event);
	}
}
