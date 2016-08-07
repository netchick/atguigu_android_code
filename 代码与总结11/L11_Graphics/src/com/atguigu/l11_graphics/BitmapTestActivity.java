package com.atguigu.l11_graphics;

import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/*
 Bitmap: 加载一张图片数据到内存中, 都可以封装成一个Bitmap对象
	 需求1: 加载资源文件中的图片资源并显示
	 需求2: 加载存储空间中的图片资源并显示
	 需求3: 将一个bitmap对象保存到存储空间中
 */
public class BitmapTestActivity extends Activity {

	private ImageView iv_bitmap1;
	private ImageView iv_bitmap2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bitmap);

		iv_bitmap1 = (ImageView) findViewById(R.id.iv_bitmap1);
		iv_bitmap2 = (ImageView) findViewById(R.id.iv_bitmap2);
		
		//需求1: 加载资源文件中的图片资源并显示
		iv_bitmap1.setImageResource(R.drawable.ic_launcher);
		
		//需求2: 加载存储空间中的图片资源并显示
		Bitmap bitmap = BitmapFactory.decodeFile("/storage/sdcard/ic_launcher.png");
		iv_bitmap2.setImageBitmap(bitmap);
	}

	public void saveImage(View v) throws FileNotFoundException {
		// 需求3: 将一个bitmap对象保存到存储空间中
		Bitmap bitmap = BitmapFactory.decodeFile("/storage/sdcard/ic_launcher.png");
		bitmap.compress(CompressFormat.PNG, 100, openFileOutput("ic_launcher.png", Context.MODE_PRIVATE));
		Toast.makeText(this, "保存完成", 0).show();
	}
}
