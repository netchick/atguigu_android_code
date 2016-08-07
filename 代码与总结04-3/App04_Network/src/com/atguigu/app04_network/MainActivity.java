package com.atguigu.app04_network;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

public class MainActivity extends Activity {

	private File apkFile;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void downloadAPK(View v) {
		//1). 主线程, 显示提示视图: ProgressDialog
		final ProgressDialog dialog = new ProgressDialog(this);
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		dialog.show();
		
		//准备用于保存APK文件的File对象 : /storage/sdcard/Android/package_name/files/xxx.apk
		apkFile = new File(getExternalFilesDir(null), "update.apk");
		
		//2). 启动分线程, 请求下载APK文件, 下载过程中显示下载进度
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					//1. 得到连接对象
					String path = "http://192.168.10.165:8080/Web_Server/L04_DataStorage.apk";
					URL url = new URL(path);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					//2. 设置
					//connection.setRequestMethod("GET");
					connection.setConnectTimeout(5000);
					connection.setReadTimeout(10000);
					//3. 连接
					connection.connect();
					//4. 请求并得到响应码200
					int responseCode = connection.getResponseCode();
					if(responseCode==200) {
						//设置dialog的最大进度
						dialog.setMax(connection.getContentLength());
						
						
						//5. 得到包含APK文件数据的InputStream
						InputStream is = connection.getInputStream();
						//6. 创建指向apkFile的FileOutputStream
						FileOutputStream fos = new FileOutputStream(apkFile);
						//7. 边读边写
						byte[] buffer = new byte[1024];
						int len = -1;
						while((len=is.read(buffer))!=-1) {
							fos.write(buffer, 0, len);
							//8. 显示下载进度
							dialog.incrementProgressBy(len);
							
							//休息一会(模拟网速慢)
							//Thread.sleep(50);
							SystemClock.sleep(50);
						}
						
						fos.close();
						is.close();
					}
					//9. 下载完成, 关闭, 进入3)
					connection.disconnect();
					
					//3). 主线程, 移除dialog, 启动安装
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							dialog.dismiss();
							installAPK();
						}
					});
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		//09-05 12:59:20.553: I/ActivityManager(1179): Displayed com.android.packageinstaller/.PackageInstallerActivity: +282ms
	}
	
	/**
	 * 启动安装APK
	 */
	private void installAPK() {
		Intent intent = new Intent("android.intent.action.INSTALL_PACKAGE");
		intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
		startActivity(intent);
	}
}
