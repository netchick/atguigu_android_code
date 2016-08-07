package com.atguigu.app05_handler;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends Activity {

	protected static final int WHAT_REQUEST_SUCCESS = 1;
	protected static final int WHAT_REQUEST_ERROR = 2;
	private ListView lv_main;
	private LinearLayout ll_main_loading;
	private List<ShopInfo> data;
	private ShopInfoAdapter adapter;
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case WHAT_REQUEST_SUCCESS:
				ll_main_loading.setVisibility(View.GONE);
				//显示列表
				lv_main.setAdapter(adapter);
				break;
			case WHAT_REQUEST_ERROR:
				ll_main_loading.setVisibility(View.GONE);
				Toast.makeText(MainActivity.this, "加载数据失败", 1).show();
				break;

			default:
				break;
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lv_main = (ListView) findViewById(R.id.lv_main);
		ll_main_loading = (LinearLayout) findViewById(R.id.ll_main_loading);
		adapter = new ShopInfoAdapter();
		
		//1. 主线程, 显示提示视图
		ll_main_loading.setVisibility(View.VISIBLE);
		//2. 分线程, 联网请求
		//启动分线程请求服务器动态加载数据并显示
		new Thread(){
			public void run() {
				//联网请求得到jsonString
				try {
					String jsonString = requestJson();
					//解析成List<ShopInfo>
					data = new Gson().fromJson(jsonString, new TypeToken<List<ShopInfo>>(){}.getType());
					//3. 主线程, 更新界面
					handler.sendEmptyMessage(WHAT_REQUEST_SUCCESS);//发请求成功的消息
				} catch (Exception e) {
					e.printStackTrace();
					handler.sendEmptyMessage(WHAT_REQUEST_ERROR);//发送请求失败的消息
				}
			}
		}.start();
		
		
	}
	
	/**
	 * 联网请求得到jsonString
	 * @return
	 * @throws Exception 
	 */
	private String requestJson() throws Exception {
		String result = null;
		String path = "http://192.168.10.165:8080/L05_Web/ShopInfoListServlet";
		//1. 得到连接对象
		URL url = new URL(path);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		//2. 设置
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);
		//连接
		connection.connect();
		//发请求并读取服务器返回的数据
		int responseCode = connection.getResponseCode();
		if(responseCode==200) {
			InputStream is = connection.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = is.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}
			baos.close();
			is.close();
			connection.disconnect();
			
			result = baos.toString();
		} else {
			//也可以抛出运行时异常
		}
		return result;
	}

	class ShopInfoAdapter extends BaseAdapter {

		private ImageLoader imageLoader;
		
		public ShopInfoAdapter() {
			imageLoader = new ImageLoader(MainActivity.this, R.drawable.loading, R.drawable.error);
		}
		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView==null) {
				convertView = View.inflate(MainActivity.this, R.layout.item_main, null);
			}
			//得到当前行的数据对象
			ShopInfo shopInfo = data.get(position);
			//得到当前行的子View
			TextView nameTV = (TextView) convertView.findViewById(R.id.tv_item_name);
			TextView priceTV = (TextView) convertView.findViewById(R.id.tv_item_price);
			ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_item_icon);
			//设置数据
			nameTV.setText(shopInfo.getName());
			priceTV.setText(shopInfo.getPrice()+"元");
			String imagePath = shopInfo.getImagePath();
			//根据图片路径启动分线程动态请求服务加载图片并显示
			imageLoader.loadImage(imagePath, imageView);
			return convertView;
		}
		
	}
}
