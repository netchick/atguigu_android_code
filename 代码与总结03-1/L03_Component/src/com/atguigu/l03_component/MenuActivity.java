package com.atguigu.l03_component;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * 测试Menu
 * 
 * @author 张晓飞
 *
 */
/*
OptionMenu 
	1. 如何触发Menu的显示? 
		点击menu键 
	2. 如何向Menu中添加MenuItem? 
		重写onCreateOptionMenu()
		menu.add()或者加载菜单文件
	3. 选择某个MenuItem时如何响应? 
		重写onOptionsItemSelected(), 根据itemId做响应
ContextMenu
	1. 如何触发Menu的显示? 
		长按某个视图 
		view.setOnCreateContextMenuListener(this)
	2. 如何向Menu中添加MenuItem? 
		重写onCreateContextMenu()
		menu.add()
	3. 选择某个MenuItem时如何响应? 
		重写onContextItemSelected(), 根据itemId做响应
 */
public class MenuActivity extends Activity {

	private Button btn_test2_show_cm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		btn_test2_show_cm = (Button) findViewById(R.id.btn_test2_show_cm);
		//设置创建上下文菜单的监听
		btn_test2_show_cm.setOnCreateContextMenuListener(this);
	}
	
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		//添加菜单项
		menu.add(0, 1, 0, "添加");
		menu.add(0, 4, 0, "删除");
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case 1:
			Toast.makeText(this, "添加", 0).show();
			break;
		case 4:
			Toast.makeText(this, "删除", 0).show();
			break;
		default:
			break;
		}
		
		return super.onContextItemSelected(item);
	}
	

	// 用来显示optionmenu的方法: 向menu中添加Item
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// 纯编码方式
		menu.add(0, 2, 0, "添加");
		menu.add(0, 3, 0, "删除");
		return super.onCreateOptionsMenu(menu);
	}
	
	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// 菜单文件方式
			//1. 得到菜单加载器对象
		MenuInflater menuInflater = getMenuInflater();
			//2. 加载菜单文件
		menuInflater.inflate(R.menu.option_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}*/
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		//case R.id.add:
		case 2:
			Toast.makeText(this, "添加", 0).show();
			break;
		//case R.id.delete:
		case 3:
			Toast.makeText(this, "删除", 0).show();
			break;
		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
}
