package com.atguigu.l02_activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * 界面二
 * 
 * @author Administrator
 *
 */
public class SecondActivity extends Activity {

	private EditText et_second_message;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		et_second_message = (EditText) findViewById(R.id.et_second_message);
		
		//4). 得到intent对象
		Intent intent = getIntent();
		//5). 通过intent读取额外数据
		String message = intent.getStringExtra("MESSAGE");
		//6). 显示到EditText
		et_second_message.setText(message);
	}
	
	public void back1(View v) {
		//关闭当前界面
		finish();
	}
	
	public void back2(View v) {
		
		//保存一个结果
		int resultCode = 3;
			//准备一个带额外数据的intent对象
		Intent data = new Intent();
		String result = et_second_message.getText().toString();
		data.putExtra("RESULT", result);
			//设置结果
		setResult(resultCode, data );
		
		//关闭当前界面
		finish();
	}
}
