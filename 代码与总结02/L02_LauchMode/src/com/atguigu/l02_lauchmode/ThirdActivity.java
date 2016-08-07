package com.atguigu.l02_lauchmode;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class ThirdActivity extends Activity {

	public ThirdActivity() {
		Log.e("TAG", "ThirdActivity()");
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
	}
}
