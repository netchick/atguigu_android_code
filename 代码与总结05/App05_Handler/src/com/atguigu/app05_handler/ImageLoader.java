package com.atguigu.app05_handler;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * 用于加载图片并显示的类
 * @author 张晓飞
 *
 */
/*
	String iamgePath = http://192.168.10.165:8080//L05_Web/images/f10.jpg和ImageView对象
	1). 根据url从一级缓存中取对应的bitmap对象
		如果有, 显示(结束)
		如果没有, 进入2)
	2). 从二级缓存中查找: 得到文件名并在sd卡的缓存目录下加载对应的图片得到Bitmap对象
		如果有: 显示, 缓存到一级缓存中(结束)
		如果没有, 进入3)
	3). 显示代表提示正在加载的图片, 启动分线程联网请求得到Bitmap对象
			如果没有: 显示提示错误的图片(结束)
			如果有: 
				显示
				缓存到一级缓存
				缓存到二级缓存
 */
public class ImageLoader {
	
	private Context context;
	private int loadingImageRes;
	private int errorImageRes;

	public ImageLoader(Context context, int loadingImageRes, int errorImageRes) {
		super();
		this.context = context;
		this.loadingImageRes = loadingImageRes;
		this.errorImageRes = errorImageRes;
	}

	//用于缓存bitmap的容器对象
	private Map<String, Bitmap> cacheMap = new HashMap<String, Bitmap>();
	
	/**
	 * 加载图片并显示
	 * @param imagePath
	 * @param imageView
	 */
	public void loadImage(String imagePath, ImageView imageView) {
		
		//将需要显示的图片url保存到视图上
		imageView.setTag(imagePath);
		
		/*
		 1). 根据url从一级缓存中取对应的bitmap对象
			如果有, 显示(结束)
			如果没有, 进入2)
		 */
		Bitmap bitmap = getFromFirstCache(imagePath);
		if(bitmap!=null) {
			imageView.setImageBitmap(bitmap);
			return;
		}
		/*
		2). 从二级缓存中查找: 得到文件名并在sd卡的缓存目录下加载对应的图片得到Bitmap对象
				如果有: 显示, 缓存到一级缓存中(结束)
				如果没有, 进入3)
			
			/storage/sdcard/Android/data/packageName/files/图片文件名(xxx.jpg)
		 */
		bitmap = getFromSecondCache(imagePath);
		if(bitmap!=null) {
			imageView.setImageBitmap(bitmap);
			cacheMap.put(imagePath, bitmap);
			return;
		}
		
		/*
		 3). 显示代表提示正在加载的图片, 启动分线程联网请求得到Bitmap对象
			如果没有: 显示提示错误的图片(结束)
			如果有: 
				缓存到一级缓存(分线程)
				缓存到二级缓存(分线程)
				显示(主线程)
				
		 */
		
		loadBitmapFromThirdCache(imagePath, imageView);
	}

	/**
	 * 根据图片url从三级缓存中取对应的bitmap对象并显示
	 * @param imagePath
	 * @param imageView
	 * AsyncTask
	 * loadBitmapFromThirdCache("../b.jpg", imageView)
	 * loadBitmapFromThirdCache("../f.jpg", imageView)--->imageView.setTag("../f.jpg")
	 */
	private void loadBitmapFromThirdCache(final String imagePath, final ImageView imageView) {
		new AsyncTask<Void, Void, Bitmap>() {
			protected void onPreExecute() {
				imageView.setImageResource(loadingImageRes);
			}
			
			//联网请求得到bitmap对象
			@Override
			protected Bitmap doInBackground(Void... params) {
				//在分线程执行, 可能需要等待一定时间才会执行
				//在等待的过程中imageView中的tag值就有可能改变了
				//如果改变了, 就不应该再去加载图片(此图片此时不需要显示)
				
				Bitmap bitmap = null;
				try {
					
					//在准备请求服务器图片之前, 判断是否需要加载
					String newImagePath = (String) imageView.getTag();
					if(newImagePath!=imagePath) {//视图已经被复用了
						return null;
					}
					
					//得到连接
					URL url = new URL(imagePath);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					//设置
					connection.setConnectTimeout(5000);
					connection.setReadTimeout(5000);
					//连接
					connection.connect();
					//发请求读取返回的数据并封装为bitmap
					int responseCode = connection.getResponseCode();
					if(responseCode==200) {
						InputStream is = connection.getInputStream();//图片文件流
						//将is封装为bitmap
						bitmap = BitmapFactory.decodeStream(is);
						is.close();
						
						if(bitmap!=null) {
							//缓存到一级缓存(分线程)
							cacheMap.put(imagePath, bitmap);
							//缓存到二级缓存(分线程)
							// /storage/sdcard/Android/data/packageName/files/
							String filesPath = context.getExternalFilesDir(null).getAbsolutePath();
							// http://192.168.10.165:8080//L05_Web/images/f10.jpg
							String fileName = imagePath.substring(imagePath.lastIndexOf("/")+1);//  f10.jpg
							String filePath = filesPath+"/"+fileName;
							bitmap.compress(CompressFormat.JPEG, 100, new FileOutputStream(filePath));
						}
					}
					connection.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				return bitmap;
			}
			
			protected void onPostExecute(Bitmap bitmap) {//从联网请求图片到得到图片对象需要一定的时间, 视图可能被复用了,不需要显示
				//在主线程准备显示图片之前, 需要判断是否需要显示
				String newImagePath = (String) imageView.getTag();
				if(newImagePath!=imagePath) {//视图已经被复用了
					return;
				}
						
				//如果没有: 显示提示错误的图片(结束)
				if(bitmap==null) {
					imageView.setImageResource(errorImageRes);
				} else {//如果有, 显示
					imageView.setImageBitmap(bitmap);
				}
			}
		}.execute();
	}

	/**
	 * 根据图片url从二级缓存中取对应的bitmap对象
	 * @param imagePath
	 * @return
	 */
	private Bitmap getFromSecondCache(String imagePath) {
		
		// /storage/sdcard/Android/data/packageName/files/
		String filesPath = context.getExternalFilesDir(null).getAbsolutePath();
		// http://192.168.10.165:8080//L05_Web/images/f10.jpg
		String fileName = imagePath.substring(imagePath.lastIndexOf("/")+1);//  f10.jpg
		String filePath = filesPath+"/"+fileName;
		
		return BitmapFactory.decodeFile(filePath);
	}

	/**
	 * 根据图片url从一级缓存中取对应的bitmap对象
	 * @param imagePath
	 * @return
	 */
	private Bitmap getFromFirstCache(String imagePath) {
		return cacheMap.get(imagePath);
	}
}
