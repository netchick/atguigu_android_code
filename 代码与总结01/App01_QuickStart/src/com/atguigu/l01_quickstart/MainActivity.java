package com.atguigu.l01_quickstart;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button btn_main_download;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//加载布局文件, 并在内存中生成对应的视图对象
		setContentView(R.layout.activity_main);
		//根据id在内存查找得到对应的视图对象
		btn_main_download = (Button) findViewById(R.id.btn_main_download);
		//设置点击监听
		btn_main_download.setOnClickListener(new View.OnClickListener() {//匿名内部类对象
			@Override
			public void onClick(View v) {//点击button的回调方法
				//如何得到外部类的当前对象? 类名.this
				//显示文本小提示
				/*
				Toast toast = Toast.makeText(MainActivity.this, "开始下载...", Toast.LENGTH_SHORT);
				toast.show();
				*/
				Toast.makeText(MainActivity.this, "开始下载...", Toast.LENGTH_SHORT).show();//方法链调用
				//更新button的文本
				btn_main_download.setText("下载中...");
			}
		});
	}
}
