package com.atguigu.l07_serviceclient;

import com.atguigu.l07_service.remote.IStudentService;
import com.atguigu.l07_service.remote.Student;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText et_aidl_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et_aidl_id = (EditText) findViewById(R.id.et_aidl_id);
	}

	private ServiceConnection conn;
	private IStudentService studentService;

	public void bindRemoteService(View v) {

		Intent intent = new Intent(
				"com.atguigu.l07_service.remote.MyRemoteService.Action");
		if (conn == null) {
			conn = new ServiceConnection() {

				@Override
				public void onServiceDisconnected(ComponentName name) {

				}

				@Override
				public void onServiceConnected(ComponentName name,
						IBinder service) {
					Log.e("TAG", "onServiceConnected()");
					studentService = IStudentService.Stub.asInterface(service);
				}
			};
			bindService(intent, conn, Context.BIND_AUTO_CREATE);
			Toast.makeText(this, "绑定Service", 0).show();
		} else {
			Toast.makeText(this, "已经绑定Service", 0).show();
		}

	}

	public void invokeRemote(View v) throws RemoteException {
		if(studentService!=null) {
			int id = Integer.parseInt(et_aidl_id.getText().toString());
			Student student = studentService.getStudentById(id);
			Toast.makeText(this, student.toString(), 0).show();
		}
	}

	public void unbindRemoteService(View v) {
		if (conn != null) {
			unbindService(conn);
			conn = null;
			studentService = null;
			Toast.makeText(this, "解绑Service", 0).show();
		} else {
			Toast.makeText(this, "还未绑定Service", 0).show();
		}
	}
}
