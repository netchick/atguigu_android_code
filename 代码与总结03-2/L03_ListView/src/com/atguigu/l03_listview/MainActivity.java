package com.atguigu.l03_listview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ListView lv_main;
	private List<ShopInfo> data;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lv_main = (ListView) findViewById(R.id.lv_main);
		
		//准备集合数据
		data = new ArrayList<ShopInfo>();
		data.add(new ShopInfo(R.drawable.f1, "name----1", "content----1"));
		data.add(new ShopInfo(R.drawable.f2, "name----2", "content----2"));
		data.add(new ShopInfo(R.drawable.f3, "name----3", "content----3"));
		data.add(new ShopInfo(R.drawable.f4, "name----4", "content----4"));
		data.add(new ShopInfo(R.drawable.f5, "name----5", "content----5"));
		data.add(new ShopInfo(R.drawable.f6, "name----6", "content----6"));
		data.add(new ShopInfo(R.drawable.f7, "name----7", "content----7"));
		data.add(new ShopInfo(R.drawable.f8, "name----8", "content----8"));
		data.add(new ShopInfo(R.drawable.f9, "name----9", "content----9"));
		data.add(new ShopInfo(R.drawable.f10, "name----10", "content----10"));
		//准备BaseAdapter对象
		MyAdapter adapter = new MyAdapter();
		//设置Adapter显示列表
		lv_main.setAdapter(adapter);
	}
	
	class MyAdapter extends BaseAdapter {

		//返回集合数据的数量
		@Override
		public int getCount() {
			Log.e("TAG", "getCount()");
			return data.size();
		}

		//返回指定下标对应的数据对象
		@Override
		public Object getItem(int position) {
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		/**
		 * 返回指定下标所对应的item的View对象
		 * position : 下标
		 * convertView : 可复用的缓存Item视图对象, 前n+1个为null
		 * parent : ListView对象
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			//Log.e("TAG", "getView() position="+position+" convertView="+convertView);
			
			//如果没有复用的
			if(convertView==null) {
				Log.e("TAG", "getView() position="+position+" convertView="+convertView);
				//加载item的布局, 得到View对象
				convertView = View.inflate(MainActivity.this, R.layout.item_simple_adapter, null);
			}
			
			//根据position设置对应的数据
				//得到当前行的数据对象
			ShopInfo shopInfo = data.get(position);
				//得到子View对象
			ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_item_icon);
			TextView nameTV = (TextView) convertView.findViewById(R.id.tv_item_name);
			TextView contentTV = (TextView) convertView.findViewById(R.id.tv_item_content);
				//设置数据
			imageView.setImageResource(shopInfo.getIcon());
			nameTV.setText(shopInfo.getName());
			contentTV.setText(shopInfo.getContent());
			
			return convertView;
		}
		
	}
}
