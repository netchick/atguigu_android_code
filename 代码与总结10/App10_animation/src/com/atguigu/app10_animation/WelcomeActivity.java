package com.atguigu.app10_animation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

/**
 * 欢迎界面
 * @author 张晓飞
 *
 */
public class WelcomeActivity extends Activity {

	private RelativeLayout rl_welcome_root;
	private Handler handler  = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what==1) {
				startActivity(new Intent(WelcomeActivity.this, SetupGuide1Activity.class));
				//关闭自己
				finish();
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		rl_welcome_root = (RelativeLayout) findViewById(R.id.rl_welcome_root);
		
		//显示动画
		showAnimation();
		
		//发送延迟3s的消息
		handler.sendEmptyMessageDelayed(1, 3000);
	}

	/**
	 * 显示动画
	 * 
	 * 旋转动画  RotateAnimation: 0-->360 视图的中心点 2s
	 * 透明度动画 AlphaAnimation: 0-->1 2s
	 * 缩放动画 ScaleAnimation: 0-->1 视图的中心点 2s
	 */
	private void showAnimation() {
		//旋转动画  RotateAnimation: 0-->360 视图的中心点 2s
		RotateAnimation rotateAnimation = new RotateAnimation(0, 360, 
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		rotateAnimation.setDuration(2000);
		//透明度动画 AlphaAnimation: 0-->1 2s
		AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
		alphaAnimation.setDuration(2000);
		//缩放动画 ScaleAnimation: 0-->1 视图的中心点 2s
		ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		scaleAnimation.setDuration(2000);
		//创建复合动画,并添加
		AnimationSet animationSet = new AnimationSet(true);
		animationSet.addAnimation(rotateAnimation);
		animationSet.addAnimation(alphaAnimation);
		animationSet.addAnimation(scaleAnimation);
		//启动
		rl_welcome_root.startAnimation(animationSet);
	}
}
