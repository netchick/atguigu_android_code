package com.atguigu.l09_provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	public DBHelper(Context context) {
		super(context, "atguigu.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.e("TAG", "onCreate()...");
		//建表
		db.execSQL("create table person(_id integer primary key autoincrement, name varchar)");
		//插入初始化数据
		db.execSQL("insert into person (name) values ('Tom')");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
