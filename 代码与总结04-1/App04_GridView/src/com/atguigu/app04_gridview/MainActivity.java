package com.atguigu.app04_gridview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MainActivity extends Activity implements OnItemLongClickListener {

	private GridView gv_main;
	private MainAdapter adapter;
	String[] names = new String[] { "手机防盗", "通讯卫士", "软件管理", "流量管理", "进程管理",
			"手机杀毒", "缓存清理", "高级工具", "设置中心" };

	int[] icons = new int[] { R.drawable.widget01, R.drawable.widget02,
			R.drawable.widget03, R.drawable.widget04, R.drawable.widget05,
			R.drawable.widget06, R.drawable.widget07, R.drawable.widget08,
			R.drawable.widget09 };

	private SharedPreferences sp;
	
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
		
		//给gridView的item添加长按监听(只能第一个有响应)
		gv_main.setOnItemLongClickListener(this);
		
		sp = getSharedPreferences("xfzhang", Context.MODE_PRIVATE);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		
		if(position==0) {
			
			//得到当前显示的名称
			final TextView textView = (TextView) view.findViewById(R.id.tv_item_name);
			String name = textView.getText().toString();
			//为dialog准备输入框对象
			final EditText editText = new EditText(this);
			editText.setHint(name);
			//显示AlertDialog
			new AlertDialog.Builder(this)
				.setTitle("修改名称")
				.setView(editText)
				.setPositiveButton("修改", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						String newName = editText.getText().toString();
						//1). 界面更新
						textView.setText(newName);
						//2). 将名称保存到sp中
						sp.edit().putString("NAME", newName).commit();
					}
				})
				.setNegativeButton("取消", null)
				.show();
		}
		return true;
	}
}
