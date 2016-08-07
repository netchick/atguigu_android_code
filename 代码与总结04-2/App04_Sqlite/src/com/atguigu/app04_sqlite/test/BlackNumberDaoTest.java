package com.atguigu.app04_sqlite.test;

import java.util.List;

import com.atguigu.app04_sqlite.BlackNumber;
import com.atguigu.app04_sqlite.BlackNumberDao;

import android.test.AndroidTestCase;
import android.util.Log;

/**
 * BlackNumberDao的单元测试类
 * 
 * @author 张晓飞
 *
 */
public class BlackNumberDaoTest extends AndroidTestCase {

	public void testAdd() {
		// 创建dao对象
		BlackNumberDao dao = new BlackNumberDao(getContext());
		// 调用方法
		dao.add(new BlackNumber(-1, "123"));
	}

	public void testGetAll() {
		// 创建dao对象
		BlackNumberDao dao = new BlackNumberDao(getContext());
		// 调用方法
		List<BlackNumber> list = dao.getAll();
		Log.i("TAG", list.toString());
	}

	public void testUpdate() {
		// 创建dao对象
		BlackNumberDao dao = new BlackNumberDao(getContext());
		// 调用方法
		dao.update(new BlackNumber(2, "321"));
	}

	public void testDeleteById() {
		// 创建dao对象
		BlackNumberDao dao = new BlackNumberDao(getContext());
		// 调用方法
		dao.deleteById(2);
	}
}
