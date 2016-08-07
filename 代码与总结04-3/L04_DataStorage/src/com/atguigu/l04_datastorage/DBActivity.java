package com.atguigu.l04_datastorage;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * 测试Sqlite数据库存储
 * 
 * @author 张晓飞
 *
 */
public class DBActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_db);
	}

	/*
	 * 创建库
	 */
	public void testCreateDB(View v) {
		DBHelper dbHelper = new DBHelper(this, 1);
		//获取连接
		SQLiteDatabase database = dbHelper.getReadableDatabase();
		
		Toast.makeText(this, "创建数据库", 0).show();
	}

	/*
	 * 更新库
	 */
	public void testUpdateDB(View v) {
		DBHelper dbHelper = new DBHelper(this, 2);
		//获取连接
		SQLiteDatabase database = dbHelper.getReadableDatabase();
		
		Toast.makeText(this, "更新数据库", 0).show();
	}

	/*
	 * 添加记录
	 */
	public void testInsert(View v) {
		//1. 得到连接
		DBHelper dbHelper = new DBHelper(this, 2);
		SQLiteDatabase database = dbHelper.getReadableDatabase();
		//2. 执行insert  insert into person(name, age) values('Tom', 12)
		ContentValues values = new ContentValues();
		values.put("name", "Tom");
		values.put("age", 12);
		long id = database.insert("person", null, values);
		//3. 关闭
		database.close();
		//4. 提示
		Toast.makeText(this, "id="+id, 1).show();
	}

	/*
	 * 更新
	 */
	public void testUpdate(View v) {
		DBHelper dbHelper = new DBHelper(this, 2);
		SQLiteDatabase database = dbHelper.getReadableDatabase();
		//执行update  update person set name=Jack, age=13 where _id=4
		ContentValues values = new ContentValues();
		values.put("name", "jack");
		values.put("age", 13);
		int updateCount = database.update("person", values , "_id=?", new String[]{"4"});
		database.close();
		Toast.makeText(this, "updateCount="+updateCount, 1).show();
	}

	/*
	 * 删除
	 */
	public void testDelete(View v) {
		// 1. 得到连接
		DBHelper dbHelper = new DBHelper(this, 2);
		SQLiteDatabase database = dbHelper.getReadableDatabase();
		// 2. 执行delete delete from person where _id=2
		int deleteCount = database.delete("person", "_id=2", null);
		// 3. 关闭
		database.close();
		// 4. 提示
		Toast.makeText(this, "deleteCount=" + deleteCount, 1).show();
	}

	/*
	 * 查询
	 */
	public void testQuery(View v) {
		// 1. 得到连接
		DBHelper dbHelper = new DBHelper(this, 2);
		SQLiteDatabase database = dbHelper.getReadableDatabase();
		// 2. 执行query select * from person
		Cursor cursor = database.query("person", null, null, null, null, null, null);
		//cursor = database.query("person", null, "_id=?", new String[]{"3"}, null, null, null);
		//得到匹配的总记录数
		int count = cursor.getCount();
		
		//取出cursor中所有的数据
		while(cursor.moveToNext()) {
			//_id
			int id = cursor.getInt(0);
			//name
			String name = cursor.getString(1);
			//age
			int age = cursor.getInt(cursor.getColumnIndex("age"));
			Log.e("TAG", id+"-"+name+"-"+age);
		}
		// 3. 关闭
		cursor.close();
		database.close();
		// 4. 提示
		Toast.makeText(this, "count=" + count, 1).show();
	}

	/*
	 * 测试事务处理
	 * update person set age=16 where _id=1
	 * update person set age=17 where _id=3
	 * 
	 * 一个功能中对数据库进行的多个操作: 要就是都成功要就都失败
	 * 事务处理的3步:
	 * 1. 开启事务(获取连接后)
	 * 2. 设置事务成功(在全部正常执行完后)
	 * 3. 结束事务(finally中)
	 */
	public void testTransaction(View v) {
		
		SQLiteDatabase database = null;
		try{
			DBHelper dbHelper = new DBHelper(this, 2);
			database = dbHelper.getReadableDatabase();
			
			//1. 开启事务(获取连接后)
			database.beginTransaction();
			
			//执行update  update person set age=16 where _id=1
			ContentValues values = new ContentValues();
			values.put("age", 16);
			int updateCount = database.update("person", values , "_id=?", new String[]{"1"});
			Log.e("TAG", "updateCount="+updateCount);
			
			//出了异常
			boolean flag = true;
			if(flag) {
				throw new RuntimeException("出异常啦!!!");
			}
			
			//执行update  update person set age=17 where _id=3
			values = new ContentValues();
			values.put("age", 17);
			int updateCount2 = database.update("person", values , "_id=?", new String[]{"3"});
			Log.e("TAG", "updateCount2="+updateCount2);
			
			//2. 设置事务成功(在全部正常执行完后)
			database.setTransactionSuccessful();
			
		} catch(Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "出异常啦!!!", 1).show();
		} finally {
			//3. 结束事务(finally中)
			if(database!=null) {
				database.endTransaction();
				database.close();
			}
		}
		
	}

}
