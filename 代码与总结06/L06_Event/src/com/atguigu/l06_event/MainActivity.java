package com.atguigu.l06_event;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
/**
 * 用来测试MotionEvent和KeyEvent的主界面
 * @author 张晓飞
 *
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.btn_main_test1).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, MotionEventTestActivity.class));
			}
		});
		
		findViewById(R.id.btn_main_test2).setOnLongClickListener(new View.OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				startActivity(new Intent(MainActivity.this, KeyEventTestActivity.class));
				return true;
			}
		});
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		//监听back键
		if(event.getKeyCode()==KeyEvent.KEYCODE_BACK) {
			//显示确定的dialog
			new AlertDialog.Builder(this)
				.setMessage("你确定退出吗?")
				.setPositiveButton("退出", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//退出
						finish();
					}
				})
				.setNegativeButton("再看看", null)
				.show();
			return true;//不会退出了
		}
		return super.onKeyUp(keyCode, event);
	}
}

