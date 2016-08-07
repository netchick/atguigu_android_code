package com.atguigu.l10_animation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	/*
	 * 测试View Annimation
	 */
	public void toTestVA(View v) {
		startActivity(new Intent(this, VAActivity.class));
	}

	/*
	 * 测试Drawable Annimation
	 */
	public void toTestDA(View v) {
		startActivity(new Intent(this, DAActivity.class));
	}
}
