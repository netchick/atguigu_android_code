package com.atguigu.l11_graphics;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;

/**
 * Drawable 就是一个可画的对象，
	 * 其可能是一张位图（BitmapDrawable），
	 * 也可能是一个图形（ShapeDrawable），
	 * 还有可能是一个图层（LayerDrawable），
	 * 我们根据画图的需求，创建相应的可画对象
 */
public class DrawTestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MyView(this));
	}
	
	
	class MyView extends View {

		private ShapeDrawable shapeDrawable;
		private Paint paint;
		
		public MyView(Context context) {
			super(context);
			shapeDrawable = new ShapeDrawable(new OvalShape());
			shapeDrawable.getPaint().setColor(Color.RED);//指定颜色
			shapeDrawable.setBounds(10, 10, 200, 100);//指定位置
			
			paint = new Paint();
			paint.setColor(Color.BLUE); //颜色
			paint.setTextSize(20);//字体大小 
			paint.setTypeface(Typeface.DEFAULT_BOLD);//粗体字
			paint.setAntiAlias(true);//消除锯齿
		}
		
		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			
			//画绿色背景
			canvas.drawColor(Color.GREEN);
			//画椭圆
			shapeDrawable.draw(canvas);//将自己画到画布上
			//画文本
			canvas.drawText("来自尚硅谷的你, 很NB", 10, 120, paint);
		}
	}
}
