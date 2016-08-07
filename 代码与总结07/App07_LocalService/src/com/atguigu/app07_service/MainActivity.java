package com.atguigu.app07_service;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button btn_main_play;
	private Button btn_main_stop;
	private Button btn_main_pause;
	private Button btn_main_exit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn_main_play = (Button) findViewById(R.id.btn_main_play);
		btn_main_stop = (Button) findViewById(R.id.btn_main_stop);
		btn_main_pause = (Button) findViewById(R.id.btn_main_pause);
		btn_main_exit = (Button) findViewById(R.id.btn_main_exit);

		btn_main_play.setOnClickListener(this);
		btn_main_stop.setOnClickListener(this);
		btn_main_pause.setOnClickListener(this);
		btn_main_exit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, MusicService.class);
		if (btn_main_play == v) {//播放
			intent.putExtra("action", "play");
			startService(intent);
		} else if (btn_main_stop == v) {//停止播放
			intent.putExtra("action", "stop");
			startService(intent);
		} else if (btn_main_pause == v) {//暂停音乐
			intent.putExtra("action", "pause");
			startService(intent);
		} else if (btn_main_exit == v) {//退出并停止音乐
			//停止服务
			stopService(intent);
			finish();
		}
	}
}
