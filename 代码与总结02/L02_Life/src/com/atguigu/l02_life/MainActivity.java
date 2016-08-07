package com.atguigu.l02_life;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * 
 * @author Administrator
 1）界面从“死亡”-->“运行" 
    创建对象-->onCreate()-->onStart()-->onResume()---可见可操作(运行状态)
2) 界面从“运行”-->“死亡" 
    onPause()-->onStop()-->onDestroy()-->Activity对象成为垃圾对象---不可见也不存在死亡状态)
3) 界面从“运行”-->“停止" 
    onPause()-->onStop()---不可见但存在
4) 界面从“停止” -->“运行"
    onRestart()-->onStart()-->onResume()
5) 界面从“运行”-->“暂停" 
    onPause()
6) 界面从“暂停” -->“运行"
   onResume()
   
   重要的:
   1. onCreate(): 在Activity对象创建后调用, 只执行一次
   2. onDestroy(): 在Activity死亡之前调用, 只执行一次
   3. onResume(): 界面只有经历此方法才能可见可操作
 */
public class MainActivity extends Activity{

	public MainActivity() {
		Log.e("TAG", "MainActivity()");
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.e("TAG", "onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	protected void onStart() {
		Log.e("TAG", "onStart()");
		super.onStart();
	}
	
	@Override
	protected void onResume() {
		Log.e("TAG", "onResume()");
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		Log.e("TAG", "onPause()");
		super.onPause();
	}
	
	@Override
	protected void onStop() {
		Log.e("TAG", "onStop()");
		super.onStop();
	}
	
	@Override
	protected void onRestart() {
		Log.e("TAG", "onRestart()");
		super.onRestart();
	}
	
	@Override
	protected void onDestroy() {
		Log.e("TAG", "onDestroy()");
		super.onDestroy();
	}
	
	public void startSecond(View v) {
		startActivity(new Intent(this, SecondActivity.class));
	}
}
