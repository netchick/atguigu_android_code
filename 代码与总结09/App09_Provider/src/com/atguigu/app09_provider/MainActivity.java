package com.atguigu.app09_provider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	private EditText et_main_number;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		et_main_number = (EditText) findViewById(R.id.et_main_number);
	}
	
	public void toContactList(View v) {
		//启动联系人列表界面
		startActivityForResult(new Intent(this, ContactListActivity.class), 1);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode==1 && resultCode==RESULT_OK) {
			//得到返回的number
			String number = data.getStringExtra("NUMBER");
			//显示
			et_main_number.setText(number);
		}
	}
}
