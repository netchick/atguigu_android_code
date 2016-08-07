package com.atguigu.l09_resolver;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	/*
	 * 通过ContentResolver调用ContentProvider插入一条记录
	 */
	public void insert(View v) {
		//1. 得到ContentResolver对象
		ContentResolver resolver = getContentResolver();
		//2. 调用其insert
		Uri uri = Uri.parse("content://com.atguigu.l09_provider.personprovider/person");
		//uri = Uri.parse("content://com.atguigu.l09_provider.personprovider/person/3");
		ContentValues values = new ContentValues();
		values.put("name", "JACK");
		uri = resolver.insert(uri, values);
		
		Toast.makeText(this, uri.toString(), 1).show();
	}

	/*
	 * 通过ContentResolver调用ContentProvider更新一条记录
	 */
	public void update(View v) {
		//1. 得到ContentResolver对象
		ContentResolver resolver = getContentResolver();
		//2. 执行update
		Uri uri = Uri.parse("content://com.atguigu.l09_provider.personprovider/person/2");
		ContentValues values = new ContentValues();
		values.put("name", "JACK2");
		int updateCount = resolver.update(uri, values, null, null);
		
		Toast.makeText(this, "updateCount="+updateCount, 1).show();
	}

	/*
	 * 通过ContentResolver调用ContentProvider删除一条记录
	 */
	public void delete(View v) {
		//1. 得到ContentResolver对象
		ContentResolver resolver = getContentResolver();
		//2. 执行delete
		Uri uri = Uri.parse("content://com.atguigu.l09_provider.personprovider/person/2");
		int deleteCount = resolver.delete(uri, null, null);
		Toast.makeText(this, "deleteCount="+deleteCount, 1).show();
	}

	/*
	 * 通过ContentResolver调用ContentProvider查询所有记录
	 */
	public void query(View v) {
		//1. 得到ContentResolver对象
		ContentResolver resolver = getContentResolver();
		//2. 调用其query, 得到cursor
		Uri uri = Uri.parse("content://com.atguigu.l09_provider.personprovider/person/1");
		uri = Uri.parse("content://com.atguigu.l09_provider.personprovider/person");
		Cursor cusor = resolver.query(uri, null, null, null, null);
		//3. 取出cursor中的数据, 并显示
		while(cusor.moveToNext()) {
			int id = cusor.getInt(0);
			String name = cusor.getString(1);
			Toast.makeText(this, id+" : "+name, 1).show();
		}
		cusor.close();
	}
}
