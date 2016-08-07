package com.atguigu.app04_sqlite;

import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ListActivity {

	private ListView lv_main;
	private BlackNumberAdapter adapter;
	private BlackNumberDao dao;
	private List<BlackNumber> data;
	private int position;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lv_main = getListView();
		adapter = new BlackNumberAdapter();
		dao = new BlackNumberDao(this);
		data = dao.getAll();
		
		lv_main.setAdapter(adapter);
		
		//给listView设置创建contextMenu的监听
		lv_main.setOnCreateContextMenuListener(this);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		//添加2个item
		menu.add(0, 1, 0, "更新");
		menu.add(0, 2, 0, "删除");
		
		//得到长按的position
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
		position = info.position;
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		
		//得到对应的BlackNumber对象
		BlackNumber blackNumber = data.get(position);
		switch (item.getItemId()) {
		case 1://更新
			//1. 显示更新的Dialog
			showUpdateDialog(blackNumber);
			break;
		case 2://删除
			//1). 删除数据表对应的数据
			dao.deleteById(blackNumber.getId());
			//2). 删除List对应的数据
			data.remove(position);
			//3). 通知更新列表
			adapter.notifyDataSetChanged();
			break;

		default:
			break;
		}
		
		
		return super.onContextItemSelected(item);
	}
	
	/**
	 * 显示更新的Dialog
	 * @param blackNumber
	 */
	private void showUpdateDialog(final BlackNumber blackNumber) {
		final EditText editText = new EditText(this);
		editText.setHint(blackNumber.getNumber());
		new AlertDialog.Builder(this)
			.setTitle("更新黑名单")
			.setView(editText)
			.setPositiveButton("更新", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//1). 更新List对应的数据
					String newNumber = editText.getText().toString();
					blackNumber.setNumber(newNumber);
					
					//2). 更新数据表对应的数据
					dao.update(blackNumber);
					
					//3). 通知更新列表 
					adapter.notifyDataSetChanged();
				}
			})
			.setNegativeButton("取消", null)
			.show();
	}

	public void add(View v) {
		
		//1. 显示添加的dialog(带输入框)
		final EditText editText = new EditText(this);
		editText.setHint("输入黑名单号");
		new AlertDialog.Builder(this)
			.setTitle("添加黑名单")
			.setView(editText)
			.setPositiveButton("添加", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//1). 保存数据表中
					String number = editText.getText().toString();
					BlackNumber blackNumber = new BlackNumber(-1, number);
					dao.add(blackNumber);
					//2). 保存数据到List
					//data.add(blackNumber);//已经有id了
					data.add(0, blackNumber);
					//3). 通知更新列表
					adapter.notifyDataSetChanged();
				}
			})
			.setNegativeButton("取消", null)
			.show();
	}
	
	class BlackNumberAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			if(convertView==null) {
				convertView = View.inflate(MainActivity.this, android.R.layout.simple_list_item_1, null);
			}
			
			BlackNumber blackNumber = data.get(position);
			TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
			textView.setText(blackNumber.getNumber());
			
			return convertView;
		}
	}
}
