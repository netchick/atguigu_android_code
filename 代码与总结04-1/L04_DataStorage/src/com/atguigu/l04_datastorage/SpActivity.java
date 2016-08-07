package com.atguigu.l04_datastorage;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 测试sp存储的界面
 */
public class SpActivity extends Activity {

	private EditText et_sp_key;
	private EditText et_sp_value;
	
	private SharedPreferences sp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sp);
		
		et_sp_key = (EditText) findViewById(R.id.et_sp_key);
		et_sp_value = (EditText) findViewById(R.id.et_sp_value);
		
		//1. 得到sp对象
		sp = getSharedPreferences("atguigu", Context.MODE_PRIVATE);
	}
	
	public void save(View v) {
		//2. 得到editor对象
		Editor edit = sp.edit();
		//3. 得到输入的key/value
		String key = et_sp_key.getText().toString();
		String value = et_sp_value.getText().toString();
		//4. 使用editor保存key-value
		edit.putString(key, value).commit();
		//5. 提示
		Toast.makeText(this, "保存完成!", 0).show();
	}
	
	public void read(View v) {
		//1. 得到输入的key
		String key = et_sp_key.getText().toString();
		//2. 根据key读取对应的value
		String value = sp.getString(key, null);
		//3. 显示
		if(value==null) {
			Toast.makeText(this, "没有找到对应的value", 0).show();
		} else {
			et_sp_value.setText(value);
		}
	}
}
