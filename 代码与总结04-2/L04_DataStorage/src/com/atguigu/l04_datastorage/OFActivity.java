package com.atguigu.l04_datastorage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 测试手机外部文件存储
 * 
 * @author 张晓飞
 *
 */
public class OFActivity extends Activity {

	private EditText et_of_name;
	private EditText et_of_content;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_of);
		
		et_of_name = (EditText) findViewById(R.id.et_of_name);
		et_of_content = (EditText) findViewById(R.id.et_of_content);
	}

	public void save(View v) throws IOException {
		//1. 判断sd卡状态, 如果是挂载的状态才继续, 否则提示
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			//2. 读取输入的文件名/内容
			String fileName = et_of_name.getText().toString();
			String content = et_of_content.getText().toString();
			//3. 得到指定文件的OutputStream
				//1).得到sd卡下的files路径
			String filesPath = getExternalFilesDir(null).getAbsolutePath();
				//2).组成完整路径
			String filePath = filesPath+"/"+fileName;
				//3). 创建FileOutputStream
			FileOutputStream fos = new FileOutputStream(filePath);
			//4. 写数据 
			fos.write(content.getBytes("utf-8"));
			fos.close();
			//5. 提示
			Toast.makeText(this, "保存完成", 0).show();
		} else {
			Toast.makeText(this, "sd卡没有挂载", 0).show();
		}
		
	}

	public void read(View v) throws Exception {
		
		// 1. 判断sd卡状态, 如果是挂载的状态才继续, 否则提示
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			// 2. 读取输入的文件名
			String fileName = et_of_name.getText().toString();
			// 3. 得到指定文件的InputStream
				// 1).得到sd卡下的files路径
			String filesPath = getExternalFilesDir(null).getAbsolutePath();
				// 2).组成完整路径
			String filePath = filesPath + "/" + fileName;
				// 3). 创建FileInputStream
			FileInputStream fis = new FileInputStream(filePath);
			// 4. 读取数据, 成String
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = -1;
			while((len=fis.read(buffer))!=-1) {
				baos.write(buffer, 0, len);
			}
			String content = baos.toString();
			
			// 5. 显示
			et_of_content.setText(content);
		} else {
			Toast.makeText(this, "sd卡没有挂载", 0).show();
		}
	}

	//  /storage/sdcard/atguigu/xxx.txt
	public void save2(View v) throws IOException {
		//1. 判断sd卡状态, 如果是挂载的状态才继续, 否则提示
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			//2. 读取输入的文件名/内容
			String fileName = et_of_name.getText().toString();
			String content = et_of_content.getText().toString();
			//3. 得到指定文件的OutputStream
				//1). /storage/sdcard/
			String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
				//2). /storage/sdcard/atguigu/(创建文件夹)
			File file = new File(sdPath+"/atguigu");
			if(!file.exists()) {
				file.mkdirs();//创建文件夹
			}
				//3). /storage/sdcard/atguigu/xxx.txt
			String filePath = sdPath+"/atguigu/"+fileName;
				//4). 创建输出流
			FileOutputStream fos = new FileOutputStream(filePath);
			//4. 写数据 
			fos.write(content.getBytes("utf-8"));
			fos.close();
			//5. 提示
			Toast.makeText(this, "保存完成", 0).show();
		} else {
			Toast.makeText(this, "sd卡没有挂载", 0).show();
		}
	}

	public void read2(View v) throws Exception {
		// 1. 判断sd卡状态, 如果是挂载的状态才继续, 否则提示
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			// 2. 读取输入的文件名
			String fileName = et_of_name.getText().toString();
			// 3. 得到指定文件的InputStream
			String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
			String filePath = sdPath+"/atguigu/"+fileName;
			FileInputStream fis = new FileInputStream(filePath);
			// 4. 读取数据, 成String
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = -1;
			while((len=fis.read(buffer))!=-1) {
				baos.write(buffer, 0, len);
			}
			String content = baos.toString();
			fis.close();
			// 5. 显示
			et_of_content.setText(content);
		} else {
			Toast.makeText(this, "sd卡没有挂载", 0).show();
		}
	}
}
