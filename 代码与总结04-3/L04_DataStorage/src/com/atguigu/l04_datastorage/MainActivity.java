package com.atguigu.l04_datastorage;

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

	// 测试sp存储
	public void onClickSP(View v) {
		startActivity(new Intent(this, SpActivity.class));
	}

	// 测试手机内部文件存储
	public void onClickIF(View v) {
		startActivity(new Intent(this, IFActivity.class));
	}

	// 测试手机外部文件存储
	public void onClickOF(View v) {
		startActivity(new Intent(this, OFActivity.class));
	}

	// 测试Sqlite数据库存储
	public void onClickDB(View v) {
		startActivity(new Intent(this, DBActivity.class));
	}

	public void onClickNW(View v) {
		startActivity(new Intent(this, NetworkActivity.class));
	}
}
