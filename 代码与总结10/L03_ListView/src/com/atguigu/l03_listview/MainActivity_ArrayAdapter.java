package com.atguigu.l03_listview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity_ArrayAdapter extends Activity {

	private ListView lv_main;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lv_main = (ListView) findViewById(R.id.lv_main);
		
		//准备集合数据
		String[] data = {"A", "B", "C", "D", "E", "F","G", "H", "I", "J", "K", "L"};
		
		//准备ArrayAdapter对象
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_array_adapter, data);
		
		//设置Adapter显示列表
		lv_main.setAdapter(adapter);
	}
}
