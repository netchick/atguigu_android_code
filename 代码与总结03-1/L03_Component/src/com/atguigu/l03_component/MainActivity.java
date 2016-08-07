package com.atguigu.l03_component;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.btn_main_test1).setOnClickListener(this);
		findViewById(R.id.btn_main_test2).setOnClickListener(this);
		findViewById(R.id.btn_main_test3).setOnClickListener(this);
		findViewById(R.id.btn_main_test4).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_main_test1://常用简单的Component
			startActivity(new Intent(this, SimpleComponentActivity.class));
			break;
		case R.id.btn_main_test2://Menu
			startActivity(new Intent(this, MenuActivity.class));
			break;
		case R.id.btn_main_test3://ProgressBar
			startActivity(new Intent(this, ProgressActivity.class));
			break;
		case R.id.btn_main_test4://Dialog
			startActivity(new Intent(this, DialogActivity.class));
			break;

		default:
			break;
		}
	}
}
