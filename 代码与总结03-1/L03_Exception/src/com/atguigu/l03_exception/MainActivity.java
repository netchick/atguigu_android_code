package com.atguigu.l03_exception;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/*
 	常见的异常:
 		1. NullPointerException
 			原因: 
 		2. ClassCastException
 			原因: 
		3. ActivityNotFoundException: 
			原因: 
	基本常见异常的一般分析步骤:
		1. 
		2. 
 */
public class MainActivity extends Activity {

	private Button btn_main_start;
	private Button btn_main_start2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn_main_start = (Button) findViewById(R.id.btn_second_start);
		btn_main_start.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,
						SecondActivity.class));
			}
		});
		
		btn_main_start2 = (Button) findViewById(R.id.btn_main_start2);
		btn_main_start2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,
						SecondActivity.class));
			}
		});
	}
}
