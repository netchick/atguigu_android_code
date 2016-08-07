package com.atguigu.l05_handler;

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

	public void testHandler(View v) {
		startActivity(new Intent(this, HandlerTestActivity.class));
	}

	public void handlerDemo(View v) {
		startActivity(new Intent(this, HandlerDemoActivity.class));
	}

	public void testAsyncTask(View v) {
		startActivity(new Intent(this, AsyncTaskTestActivity.class));
	}
}
