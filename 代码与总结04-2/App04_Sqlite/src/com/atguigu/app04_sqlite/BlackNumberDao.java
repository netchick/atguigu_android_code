package com.atguigu.app04_sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * 操作black_number表的DAO类
 * @author 张晓飞
 *
 */
public class BlackNumberDao {

	private DBHelper dbHelper;
	
	public BlackNumberDao(Context context) {
		dbHelper = new DBHelper(context);
	}
	/**
	 * 添加一条记录
	 * @param blackNumber
	 */
	public void add(BlackNumber blackNumber) {
		//1. 得到连接
		SQLiteDatabase database = dbHelper.getReadableDatabase();
		//2. 执行insert insert into black_number (number) values(xxx)
		ContentValues values = new ContentValues();
		values.put("number", blackNumber.getNumber());
		long id = database.insert("black_number", null, values);
		Log.i("TAG", "id="+id);
		
		//设置id
		blackNumber.setId((int) id);
		//3. 关闭
		database.close();
	}
	
	/**
	 * 根据id删除一条记录
	 */
	public void deleteById(int id) {
		//1. 得到连接
		SQLiteDatabase database = dbHelper.getReadableDatabase();
		//2. 执行delete delete from black_number where _id=id
		int deleteCount = database.delete("black_number", "_id=?", new String[]{id+""});
		Log.i("TAG", "deleteCount="+deleteCount);
		//3. 关闭
		database.close();
	}
	
	/**
	 * 更新一条记录
	 */
	public void update(BlackNumber blackNumber) {
		//1. 得到连接
		SQLiteDatabase database = dbHelper.getReadableDatabase();
		//2. 执行update update black_number set number=xxx where _id=id
		ContentValues values = new ContentValues();
		values.put("number", blackNumber.getNumber());
		int updateCount = database.update("black_number", values , "_id="+blackNumber.getId(), null);
		Log.i("TAG", "updateCount="+updateCount);
		//3. 关闭
		database.close();
	}
	
	/**
	 * 查询所有记录封装成List<BLackNumber>
	 */
	public List<BlackNumber> getAll() {
		
		List<BlackNumber> list = new ArrayList<BlackNumber>();
		//1. 得到连接
		SQLiteDatabase database = dbHelper.getReadableDatabase();
		//2. 执行query select * from black_number
		Cursor cursor = database.query("black_number", null, null, null, null, null, "_id desc");
		//3. 从cursor中取出所有数据并封装到List中
		while(cursor.moveToNext()) {
			//id
			int id = cursor.getInt(0);
			//number
			String number = cursor.getString(1);
			list.add(new BlackNumber(id, number));
		}
		//4. 关闭
		cursor.close();
		database.close();
		
		return list;
	}
}
