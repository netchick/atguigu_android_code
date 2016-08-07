package com.atguigu.l10_animation;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

/*
 * 编码实现View Animation
 * 	1. Code方式
 *  2. Xml方式
 */
public class VAActivity extends Activity {

	private ImageView iv_animation;
	private TextView tv_animation_msg;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation_va);
	
		iv_animation = (ImageView) findViewById(R.id.iv_animation);
		tv_animation_msg = (TextView) findViewById(R.id.tv_animation_msg);
	}

	/*
	 * 1.1  编码实现: 缩放动画 
	 * ScaleAnimation
	 */
	/*
	 	//1. 创建动画对象
		//2. 设置
		//3. 启动动画
	 */
	public void startCodeScale(View v) {
		tv_animation_msg.setText("Code缩放动画: 宽度从0.5到1.5, 高度从0.0到1.0, 缩放的圆心为顶部中心点,延迟1s开始,持续2s,最终还原");
		//1. 创建动画对象
		ScaleAnimation animation = new ScaleAnimation(0.5f, 1.5f, 0, 1,
				Animation.ABSOLUTE, iv_animation.getWidth()/2, Animation.ABSOLUTE, 0);
		animation = new ScaleAnimation(0.5f, 1.5f, 0, 1,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0);
		//2. 设置
		//延迟1s开始
		animation.setStartOffset(1000);
		//持续2s
		animation.setDuration(2000);
		//最终还原
		animation.setFillBefore(true);
		//3. 启动动画
		iv_animation.startAnimation(animation);
	}

	/*
	 * 1.2 xml实现: 缩放动画 
	 * <scale>
	 */
	/*
	 1. 定义动画文件
	 2. 加载动画文件得到动画对象
	 3. 启动动画
	 */
	public void startXmlScale(View v) {
		tv_animation_msg.setText("Xml缩放动画: Xml缩放动画: 宽度从0.0到1.5, 高度从0.0到1.0, 延迟1s开始,持续3s,圆心为右下角, 最终固定");
		
		//1. 定义动画文件
		//2. 加载动画文件得到动画对象
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale_test);
		//3. 启动动画
		iv_animation.startAnimation(animation);
	}

	/*
	 * 2.1 编码实现: 旋转动画 
	 * RotateAnimation
	 */
	public void startCodeRotate(View v) {
		tv_animation_msg.setText("Code旋转动画: 以图片中心点为中心, 从负90度到正90度, 持续5s");
		//1. 创建动画对象
		RotateAnimation animation = new RotateAnimation(-90, 90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		//2. 设置
		animation.setDuration(5000);
		//3. 启动动画
		iv_animation.startAnimation(animation);
	}

	/*
	 * 2.2 xml实现: 旋转动画 
	 * <rotate>
	 */
	public void startXmlRotate(View v) {
		tv_animation_msg.setText("Xml旋转动画: 以左顶点为坐标, 从正90度到负90度, 持续5s"); 
		//1. 定义动画文件
		//2. 加载动画文件得到动画对象
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_test);
		//3. 启动动画
		iv_animation.startAnimation(animation);
	}

	/*
	 * 3.1 编码实现: 透明度动画 
	 * 完全透明 : 0
	 * 完全不透明 : 1
	 * AlphaAnimation
	 */
	public void startCodeAlpha(View v) {
		tv_animation_msg.setText("Code透明度动画: 从完全透明到完全不透明, 持续2s");  
		//1. 创建动画对象
		AlphaAnimation animation = new AlphaAnimation(0, 1);
		// 2. 设置
		animation.setDuration(4000);
		// 3. 启动动画
		iv_animation.startAnimation(animation);
	}

	/*
	 * 3.2 xml实现: 透明度动画 
	 * <alpha>
	 */
	public void startXmlAlpha(View v) {
		tv_animation_msg.setText("Xml透明度动画: 从完全不透明到完全透明, 持续4s");
		//1. 定义动画文件
		//2. 加载动画文件得到动画对象
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha_test);
		animation.setFillAfter(true);
		//3. 启动动画
		iv_animation.startAnimation(animation);
	}

	
	/*
	 * 4.1 编码实现: 平移动画 
	 * TranslateAnimation
	 */
	public void startCodeTranslate(View v) {
		tv_animation_msg.setText("Code移动动画: 向右移动一个自己的宽度, 向下移动一个自己的高度, 持续2s");
		//1. 创建动画对象
		TranslateAnimation animation = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.RELATIVE_TO_SELF, 1, Animation.ABSOLUTE, 0, Animation.RELATIVE_TO_SELF, 1);
		//2. 设置
		animation.setDuration(2000);
		//3. 启动动画
		iv_animation.startAnimation(animation);
	}

	/*
	 * 4.2 xml实现: 平移动画 
	 * <translate>
	 */
	public void startXmlTranslate(View v) {
		tv_animation_msg.setText("xml移动动画: 从屏幕的右边逐渐回到原来的位置, 持续2s"); //***此效果用于界面切换的动画效果
		//1. 定义动画文件
		//2. 加载动画文件得到动画对象
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate_test);
		//3. 启动动画
		iv_animation.startAnimation(animation);
	}

	/*
	 * 5.1 编码实现: 复合动画 
	 * AnimationSet
	 */
	public void startCodeAnimationSet(View v) {
		tv_animation_msg.setText("Code复合动画: 透明度从透明到不透明, 持续2s, 接着进行旋转360度的动画, 持续1s");
		//1. 创建透明动画并设置
		AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
		alphaAnimation.setDuration(2000);
		//2. 创建旋转动画并设置
		RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		rotateAnimation.setDuration(1000);
		rotateAnimation.setStartOffset(2000);//延迟
		//3. 创建复合动画对象
		AnimationSet animationSet = new AnimationSet(true);
		//4. 添加两个动画
		animationSet.addAnimation(alphaAnimation);
		animationSet.addAnimation(rotateAnimation);
		//5. 启动复合动画对象
		iv_animation.startAnimation(animationSet);
	}

	/*
	 * 5.2  xml实现: 复合动画 
	 * <set>
	 */
	public void startXmlAnimationSet(View v) {
		tv_animation_msg.setText("Xml复合动画: 透明度从透明到不透明, 持续2s, 接着进行旋转360度的动画, 持续2s");
		//1. 定义动画文件
		//2. 加载动画文件得到动画对象
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.set_test);
		//3. 启动动画
		iv_animation.startAnimation(animation);
	}
	
	/*
	 * 6. 测试动画监听
	 */
	public void testAnimationListener(View v) {
		tv_animation_msg.setText("测试动画监听");
		//tv_animation_msg.setText("Xml旋转动画: 以左顶点为坐标, 从正90度到负90度, 持续5s"); 
		//1. 定义动画文件
		//2. 加载动画文件得到动画对象
		Animation animation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		animation.setDuration(1000);
		//设置线性变化
		animation.setInterpolator(new LinearInterpolator());
		//设置动画重复次数
		animation.setRepeatCount(Animation.INFINITE);//重复3次
		//设置动画监听
		animation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				Log.e("TAG", "动画开始");
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				Log.e("TAG", "动画重复");
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				Log.e("TAG", "动画结束");
			}
		});
		
		//3. 启动动画
		iv_animation.startAnimation(animation);
		
	}
}