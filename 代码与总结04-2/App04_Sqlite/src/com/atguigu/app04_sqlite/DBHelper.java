package com.atguigu.app04_sqlite;

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

	public DBHelper(Context context) {
		super(context, "atguigu.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("TAG", "DBHelper onCreate()");
		//创建表
		db.execSQL("create table black_number(_id integer primary key autoincrement, number varchar)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
