package com.atguigu.app10_animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText et_main_name;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		et_main_name = (EditText) findViewById(R.id.et_main_name);
	}
	
	public void login(View v) {
		//得到输入框的文本
		String name = et_main_name.getText().toString();
		//判断是否是空串, 如果为空串, 显示抖动动画
		if("".equals(name.trim())) {
			Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake);
			et_main_name.startAnimation(animation);
		} else {
			//否则, 提示登陆
			Toast.makeText(this, "去登陆", 0).show();
		}
		
	}
}
