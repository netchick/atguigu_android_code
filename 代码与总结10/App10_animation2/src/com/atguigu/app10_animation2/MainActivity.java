package com.atguigu.app10_animation2;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ImageView iv_main_scan;
	private TextView tv_main_scan;
	private ProgressBar pb_main_scan;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		iv_main_scan = (ImageView) findViewById(R.id.iv_main_scan);
		tv_main_scan = (TextView) findViewById(R.id.tv_main_scan);
		pb_main_scan = (ProgressBar) findViewById(R.id.pb_main_scan);
		
		//1. 显示扫描动画
		showScanAnimation();
		
		//2. 扫描,并显示扫描进度
		scan();
	}

	/**
	 * 扫描,并显示扫描进度
	 */
	private void scan() {
		//启动异步任务做
		new AsyncTask<Void, Void, Void>() {

			//1. 主线程, 显示提示视图
			protected void onPreExecute() {
				tv_main_scan.setText("手机杀毒引擎正在扫描中...");
			}
			
			//2. 分线程, 做长时间的工作(扫描应用)
			@Override
			protected Void doInBackground(Void... params) {
				int appCount = 60;
				//设置进度条的最大值
				pb_main_scan.setMax(appCount);
				for(int i=0;i<appCount;i++) {
					SystemClock.sleep(40);
					//扫描完成一个
					//发布进度
					publishProgress();
				}
				return null;
			}
			
			//在主线程执行, 更新进度
			protected void onProgressUpdate(Void[] values) {
				pb_main_scan.incrementProgressBy(1);//增加1
				//pb_main_scan.setProgress(pb_main_scan.getProgress()+1);
			}
			
			//3. 主线程, 更新界面
			protected void onPostExecute(Void result) {
				//隐藏进度条
				pb_main_scan.setVisibility(View.GONE);
				//更新文本
				tv_main_scan.setText("扫描完成, 未发现病毒应用, 请放心使用!");
				//停止扫描动画
				iv_main_scan.clearAnimation();
			}
		}.execute();
	}

	/**
	 * 显示扫描动画
	 * iv_main_scan的旋转动画 
	 */
	private void showScanAnimation() {
		//创建动画对象
		RotateAnimation animation = new RotateAnimation(0, 360, 
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		//设置
		animation.setDuration(1000);
		animation.setRepeatCount(Animation.INFINITE);
		animation.setInterpolator(new LinearInterpolator());
		//启动
		iv_main_scan.startAnimation(animation);
	}
}
