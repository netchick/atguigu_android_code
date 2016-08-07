package com.atguigu.l06_view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * 自定义TextView
 * @author 张晓飞
 *
 */
public class MyTextView extends TextView {

	//new创建对象
	public MyTextView(Context context) {
		super(context);
		Log.e("TAG", " MyTextView(Context)");
	}

	//布局文件创建对象
	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Log.e("TAG", " MyTextView(Context, AttributeSet)");
	}
	
	/**
	 * 只有布局的方式才会调用
	 * 重写它: 用于得到子View对象
	 */
	@Override
	protected void onFinishInflate() {
		Log.e("TAG", " onFinishInflate()");
		super.onFinishInflate();
	}

	/**
	 * 无论new还是布局都会调用此方法
	 * 重写它: 用于得到子View对象
	 */
	@Override
	protected void onAttachedToWindow() {
		Log.e("TAG", " onAttachedToWindow()");
		super.onAttachedToWindow();
	}
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		
		int measuredHeight = this.getMeasuredHeight();
		int measuredWidth = this.getMeasuredWidth();
		Log.e("TAG", "onMeasure() measuredHeight="+measuredHeight+" measuredWidth="+measuredWidth);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	public void layout(int l, int t, int r, int b) {
		Log.e("TAG","layout()");
		super.layout(l, t, r, b);
	}
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		Log.e("TAG","onLayout() changed="+changed);
		super.onLayout(changed, left, top, right, bottom);
	}
}
