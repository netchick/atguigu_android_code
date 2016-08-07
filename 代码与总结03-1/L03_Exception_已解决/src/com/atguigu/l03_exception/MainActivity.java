package com.atguigu.l03_exception;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 测试异常排查技巧主界面
 * @author 张晓飞
 *
 */
/*
 	常见的异常:
 		1. NullPointerException
 			原因: 调用值为null的对象的方法或属性
 		2. ClassCastException
 			原因: 执行强制转换, 但类型匹配
		3. ActivityNotFoundException: 
			原因: Activity没有找到, 很可能没有注册或注册不正确
	基本常见异常的一般分析步骤:
		1. 确定异常类型: 从下向上找, 最好能找到cause by
		2. 确定出异常的行号: 找到当前应用的代码行号, 双击定位
		3. 分析, 打印Log, debug调试
 */
public class MainActivity extends Activity {

	private Button btn_main_start;
	private Button btn_main_start2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_main_start = (Button) findViewById(R.id.btn_main_start);
		btn_main_start.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,
						SecondActivity.class));
			}
		});
		
		btn_main_start2 = (Button) findViewById(R.id.btn_main_start2);
		btn_main_start2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,
						SecondActivity.class));
			}
		});
	}
}
