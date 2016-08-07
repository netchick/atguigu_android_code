package com.atguigu.l11_orientation;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.e("TAG", "onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	protected void onDestroy() {
		Log.e("TAG", "onDestroy()");
		super.onDestroy();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		int orientation = newConfig.orientation;
		if(orientation==Configuration.ORIENTATION_PORTRAIT) {
			Toast.makeText(this, "竖屏", 0).show();
		} else {
			Toast.makeText(this, "横屏", 0).show();
		}
	}
	
	public void switchOrientation(View v) {
		//1. 得到当前方向
		int orientation = getResources().getConfiguration().orientation;
		//2. 设置新的方向
		if(orientation==Configuration.ORIENTATION_PORTRAIT) {
			//变为横屏
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		} else {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
	}
}
