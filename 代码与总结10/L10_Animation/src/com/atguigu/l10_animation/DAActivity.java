package com.atguigu.l10_animation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

/*
 * 测试: Drawable Animation
 */
public class DAActivity extends Activity implements OnClickListener {

	private ImageView iv_da_mm;
	private Button btn_da_start;
	private Button btn_da_stop;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation_da);
		iv_da_mm = (ImageView) findViewById(R.id.iv_da_mm);
		btn_da_start = (Button) findViewById(R.id.btn_da_start);
		btn_da_stop = (Button) findViewById(R.id.btn_da_stop);

		btn_da_start.setOnClickListener(this);
		btn_da_stop.setOnClickListener(this);
	}

	private AnimationDrawable animationDrawable;
	@Override
	public void onClick(View v) {
		if (v == btn_da_start) {
			if(animationDrawable==null) {
				//得到背景动画图片对象
				animationDrawable = (AnimationDrawable) iv_da_mm.getBackground();
				//启动
				animationDrawable.start();
			}
			
		} else if (v == btn_da_stop) {
			if(animationDrawable!=null) {
				animationDrawable.stop();
				animationDrawable = null;
			}
		}
	}
}
