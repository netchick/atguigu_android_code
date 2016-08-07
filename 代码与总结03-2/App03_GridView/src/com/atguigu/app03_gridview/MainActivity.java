package com.atguigu.app03_gridview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MainActivity extends Activity {

	private GridView gv_main;
	private MainAdapter adapter;
	String[] names = new String[] { "手机防盗", "通讯卫士", "软件管理", "流量管理", "进程管理",
			"手机杀毒", "缓存清理", "高级工具", "设置中心" };

	int[] icons = new int[] { R.drawable.widget01, R.drawable.widget02,
			R.drawable.widget03, R.drawable.widget04, R.drawable.widget05,
			R.drawable.widget06, R.drawable.widget07, R.drawable.widget08,
			R.drawable.widget09 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		gv_main = (GridView) findViewById(R.id.gv_main);
		adapter = new MainAdapter(this, names, icons);
		
		gv_main.setAdapter(adapter);
		
		//给gridView的Item设置点击监听
		gv_main.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//得到当前点击的名称
				String name = names[position];
				//提示
				Toast.makeText(MainActivity.this, name, 1).show();
			}
		});
	}
}
