package com.atguigu.app06_motionevent;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements OnTouchListener {

	private ImageView iv_main;
	private RelativeLayout parentView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		iv_main = (ImageView) findViewById(R.id.iv_main);
		
		parentView = (RelativeLayout) iv_main.getParent();
		/*
		int right = parentView.getRight(); //0
		int bottom = parentView.getBottom();   //0
		Toast.makeText(this, right+"---"+bottom, 1).show();
		*/
		//设置touch监听 
		iv_main.setOnTouchListener(this);
	}
	
	private int lastX;
	private int lastY;
	private int maxRight;
	private int maxBottom;
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		//得到事件的坐标
		int eventX = (int) event.getRawX();
		int eventY = (int) event.getRawY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			
			//得到父视图的right/bottom
			if(maxRight==0) {//保证只赋一次值
				maxRight = parentView.getRight();
				maxBottom = parentView.getBottom();
			}
			
			
			//第一次记录lastX/lastY
			lastX =eventX;
			lastY = eventY;
			break;
		case MotionEvent.ACTION_MOVE:
			//计算事件的偏移
			int dx = eventX-lastX;
			int dy = eventY-lastY;
			//根据事件的偏移来移动imageView
			int left = iv_main.getLeft()+dx;
			int top = iv_main.getTop()+dy;
			int right = iv_main.getRight()+dx;
			int bottom = iv_main.getBottom()+dy;
			
			//限制left  >=0
			if(left<0) {
				right += -left;
				left = 0;
			}
			//限制top
			if(top<0) {
				bottom += -top;
				top = 0;
			}
			//限制right <=maxRight
			if(right>maxRight) {
				left -= right-maxRight;
				right = maxRight;
			}
			//限制bottom <=maxBottom
			if(bottom>maxBottom) {
				top -= bottom-maxBottom;
				bottom = maxBottom;
			}
			
			iv_main.layout(left, top, right, bottom);
			//再次记录lastX/lastY
			lastX = eventX;
			lastY = eventY;
			break;

		default:
			break;
		}
		return true;//所有的motionEvent都交给imageView处理
	}
}
