package com.atguigu.l09_provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

/**
 * 操作person表的provider类
 * @author 张晓飞
 *
 */
public class PersonProvider extends ContentProvider {

	//用来存放所有合法的Uri的容器
	private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
	//保存一些合法的uri
	// content://com.atguigu.l09_provider.personprovider/person 不根据id操作
	// content://com.atguigu.l09_provider.personprovider/person/3 根据id操作
	static {
		matcher.addURI("com.atguigu.l09_provider.personprovider", "/person", 1);
		matcher.addURI("com.atguigu.l09_provider.personprovider", "/person/#", 2);  //#匹配任意数字
	}
	private DBHelper dbHelper;
	public PersonProvider() {
		Log.e("TAG", "PersonProvider()");
	}
	@Override
	public boolean onCreate() {
		Log.e("TAG", "PersonProvider onCreate()");
		dbHelper = new DBHelper(getContext());
		
		return false;
	}

	/**
	 * content://com.atguigu.l09_provider.personprovider/person 不根据id查询 
	 * content://com.atguigu.l09_provider.personprovider/person/3 根据id查询 
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		Log.e("TAG", "PersonProvider query()");
		
		//得到连接对象
		SQLiteDatabase database = dbHelper.getReadableDatabase();
		
		//1.匹配uri, 返回code
		int code = matcher.match(uri);
		//如果合法, 进行查询
		if(code==1) {//不根据id查询
			Cursor cursor = database.query("person", projection, selection, selectionArgs, null, null, null);
			return cursor;
		} else if(code==2) {//根据id查询 
			//得到id
			long id = ContentUris.parseId(uri);
			//查询
			Cursor cursor = database.query("person", projection, "_id=?", new String[]{id+""}, null, null, null);
			return cursor;
		} else {//如果不合法, 抛出异常
			throw new RuntimeException("查询的uri不合法");
		}
	}

	/**
	 * content://com.atguigu.l09_provider.personprovider/person 插入
	 * content://com.atguigu.l09_provider.personprovider/person/3 根据id插入(没有)
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		Log.e("TAG", "PersonProvider insert()");
		//得到连接对象
		SQLiteDatabase database = dbHelper.getReadableDatabase();
		//匹配uri, 返回code
		int code = matcher.match(uri);
		//如果合法, 进行插入
		if(code==1) {
			long id = database.insert("person", null, values);
			//将id添加到uri中
			uri = ContentUris.withAppendedId(uri, id);
			database.close();
			return uri;
		} else {
			//如果不合法, 抛出异常
			database.close();
			throw new RuntimeException("插入的uri不合法");
		}
	}

	/**
	 * content://com.atguigu.l09_provider.personprovider/person 不根据id删除
	 * content://com.atguigu.l09_provider.personprovider/person/3 根据id删除
	 */
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		Log.e("TAG", "PersonProvider delete()");
		//得到连接对象
		SQLiteDatabase database = dbHelper.getReadableDatabase();
		//匹配uri, 返回code
		int code = matcher.match(uri);
		int deleteCount = -1;
		//如果合法, 进行删除
		if(code==1) {
			deleteCount = database.delete("person", selection, selectionArgs);
		} else if(code==2) {
			long id = ContentUris.parseId(uri);
			deleteCount = database.delete("person", "_id="+id, null);
		} else {
			//如果不合法, 抛出异常
			database.close();
			throw new RuntimeException("删除的uri不合法");
		}
		
		database.close();
		return deleteCount;
	}

	/**
	 * content://com.atguigu.l09_provider.personprovider/person 不根据id更新
	 * content://com.atguigu.l09_provider.personprovider/person/3 根据id更新
	 */
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		Log.e("TAG", "PersonProvider update()");
		//得到连接对象
		SQLiteDatabase database = dbHelper.getReadableDatabase();
		//匹配uri, 返回code
		int code = matcher.match(uri);
		int updateCount = -1;
		//如果合法, 进行更新
		if(code==1) {
			updateCount = database.update("person", values, selection, selectionArgs);
		} else if(code==2) {
			long id = ContentUris.parseId(uri);
			updateCount = database.update("person", values, "_id="+id, null);
		} else {
			//如果不合法, 抛出异常
			database.close();
			throw new RuntimeException("更新的uri不合法");
		}
		
		database.close();
		return updateCount;
	}
	
	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}
}
