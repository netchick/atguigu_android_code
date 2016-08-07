package com.atguigu.l02_lauchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * 界面二
 * 
 * @author Administrator
 *
 */
public class SecondActivity extends Activity {

	public SecondActivity() {
		Log.e("TAG", "SecondActivity()");
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
	}
	
	public void startFrist(View v) {
		startActivity(new Intent(this, MainActivity.class));
	}
	
	public void startThird(View v) {
		startActivity(new Intent(this, ThirdActivity.class));
	}
}
