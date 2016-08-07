package com.atguigu.app10_animation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * 设置向导3界面
 * @author 张晓飞
 *
 */
public class SetupGuide3Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup_guide3);
	}
	
	public void confirm(View v) {
		startActivity(new Intent(this, MainActivity.class));
		overridePendingTransition(R.anim.bottom_in, R.anim.disappear_out);
	}
	
	public void pre(View v) {
		finish();
		overridePendingTransition(R.anim.left_in, R.anim.right_out);
	}
}
