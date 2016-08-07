package com.atguigu.l07_service.remote;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * 远程Service
 * @author 张晓飞
 *
 */
public class MyRemoteService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		Log.e("TAG", "onBind()");
		return new StudentService();
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		Log.e("TAG", "onUnbind()");
		return super.onUnbind(intent);
	}
	
	//处理Student相关的业务逻辑类
	class StudentService extends IStudentService.Stub {
		@Override
		public Student getStudentById(int id) throws RemoteException {
			Log.e("TAG", "Service getStudentById() "+id);
			return new Student(id, "Tom", 10000);
		}
	}

}
