package com.atguigu.l03_component;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * 测试进度条
 * @author 张晓飞
 *
 */
public class ProgressActivity extends Activity {

	private LinearLayout ll_progress_loading;
	private ProgressBar pb_progress_loading;
	private SeekBar sb_progress_loading;
	
	private OnSeekBarChangeListener onSeekBarChangeListener = new OnSeekBarChangeListener() {
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {//离开滑杆
			Log.e("TAG", "离开滑杆");
			//1. 得到seekBar的进度
			int progress = sb_progress_loading.getProgress();
			//2. 设置为ProgressBar的进度
			pb_progress_loading.setProgress(progress);
			
			//3. 判断是否达到最大值
			if(progress==sb_progress_loading.getMax()) {
				//如果达到了, 设置ll_progress_loading不可见
				//ll_progress_loading.setVisibility(View.INVISIBLE); //不可见, 但占用空间
				ll_progress_loading.setVisibility(View.GONE);////不可见, 且不占用空间
			} else {
				//如果没有达到 设置ll_progress_loading显示
				ll_progress_loading.setVisibility(View.VISIBLE);
			}
		}
		
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {//按下滑杆
			Log.e("TAG", "按下滑杆");
			
		}
		
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {//滑杆移动
			Log.e("TAG", "滑杆移动");
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress);
		
		ll_progress_loading = (LinearLayout) findViewById(R.id.ll_progress_loading);
		pb_progress_loading = (ProgressBar) findViewById(R.id.pb_progress_loading);
		sb_progress_loading = (SeekBar) findViewById(R.id.sb_progress_loading);
		
		//给seekbar设置监听
		sb_progress_loading.setOnSeekBarChangeListener(onSeekBarChangeListener );
	}
}
