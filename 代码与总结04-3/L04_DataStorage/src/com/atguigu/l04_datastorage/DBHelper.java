package com.atguigu.l04_datastorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 数据库操作的帮助类
 * @author 张晓飞
 *
 */
public class DBHelper extends SQLiteOpenHelper {

	public DBHelper(Context context,int version) {
		super(context, "atguigu.db", null, version);
	}

	/**
	 * 什么时候才会创建数据库文件?
	 * 	1). 数据库文件不存在
	 *  2). 连接数据库
	 * 
	 * 什么时候调用?
	 * 	当数据库文件创建时调用(1次)
	 * 在此方法中做什么?
	 * 	建表
	 * 	插入一些初始化数据
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.e("TAG", "DBHelper onCreate()");
		//建表
		String sql = "create table person(_id integer primary key autoincrement, name varchar,age int)";
		db.execSQL(sql);
		//插入一些初始化数据
		db.execSQL("insert into person (name, age) values ('Tom1', 11)");
		db.execSQL("insert into person (name, age) values ('Tom2', 12)");
		db.execSQL("insert into person (name, age) values ('Tom3', 13)");
	}

	//当传入的版本号大于数据库的版本号时调用
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.e("TAG", "DBHelper onUpgrade()");
	}

}
