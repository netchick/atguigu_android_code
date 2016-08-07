package com.atguigu.app11_graphics;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemLongClickListener, OnClickListener {

	private ListView lv_main;
	private List<AppInfo> data;
	private AppAdapter adapter;
	
	private PopupWindow pw;
	private View pwView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//初始化成员变量
		lv_main = (ListView) findViewById(R.id.lv_main);
		data = getAllAppInfos();
		adapter = new AppAdapter();
		//显示列表
		lv_main.setAdapter(adapter);
		
		//给ListView设置item的点击监听
		lv_main.setOnItemClickListener(new OnItemClickListener() {
			/**
			 * parent : ListView
			 * view : 当前行的item视图对象
			 * position : 当前行的下标
			 */
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//提示当前行的应用名称
				String appName = data.get(position).getAppName();
				//提示
				//Toast.makeText(MainActivity.this, appName, 0).show();
				
				if(pw==null) {
					pwView = View.inflate(MainActivity.this, R.layout.pw_layout, null);
					//得到子view设置点击监听
					pwView.findViewById(R.id.ll_pw_uninstall).setOnClickListener(MainActivity.this);
					pwView.findViewById(R.id.ll_pw_run).setOnClickListener(MainActivity.this);
					pwView.findViewById(R.id.ll_pw_share).setOnClickListener(MainActivity.this);
					
					pw = new PopupWindow(pwView, view.getWidth()-80, view.getHeight());
					pw.setBackgroundDrawable(new BitmapDrawable());
				}
				
				//如果正在显示, 移除
				if(pw.isShowing()) {
					pw.dismiss();//只是不显示了, 但对象仍然在内存中
				}
				
				//(再)显示
				pw.showAsDropDown(view, 40, -view.getHeight());
				
				//缩放动画
				ScaleAnimation animation = new ScaleAnimation(0, 1, 0, 1);
				animation.setDuration(1000);
				//启动动画
				pwView.startAnimation(animation);
			}
		});
		
		//给LitView设置Item的长按监听
		lv_main.setOnItemLongClickListener(this);
		//设置listView的滚动监听
		lv_main.setOnScrollListener(new OnScrollListener() {
			
			
			/*
			 * SCROLL_STATE_IDLE : 空闲(不动)
			 * SCROLL_STATE_TOUCH_SCROLL : 跟着手指滚动
			 * SCROLL_STATE_FLING : 快速滚动
			 */
			//当listView的滚动状态发生改变时调用
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				Log.e("TAG", "onScrollStateChanged()..."+scrollState);
				if(scrollState==OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
					//移除正在显示的pw
					if(pw!=null && pw.isShowing()) {
						pw.dismiss();
					}
				}
			}
			
			//当listView正在滚动时调用(产生move时不断调用)
			@Override
			public void onScroll(AbsListView view, int firstVisibleItemPosition,
					int visibleItemCount, int totalItemCount) {
				Log.e("TAG", "onScroll()...");
			}
		});
	}
	
	
	class  AppAdapter extends BaseAdapter {

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
			// TODO Auto-generated method stub
			return 0;
		}

		//返回带数据当前行的Item视图对象
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			//1. 如果convertView是null, 加载item的布局文件
			if(convertView==null) {
				Log.e("TAG", "getView() load layout");
				convertView = View.inflate(MainActivity.this, R.layout.item_main, null);
				
			}
			//2. 得到当前行数据对象
			AppInfo appInfo = data.get(position);
			//3. 得到当前行需要更新的子View对象
			ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_item_icon);
			TextView textView = (TextView) convertView.findViewById(R.id.tv_item_name);
			//4. 给视图设置数据
			imageView.setImageDrawable(appInfo.getIcon());
			textView.setText(appInfo.getAppName());
			
			//返回convertView
			return convertView;
		}
		
	}
	
	
	/*
	 * 得到手机中所有应用信息的列表
	 * AppInfo
	 *  Drawable icon  图片对象
	 *  String appName
	 *  String packageName
	 */
	protected List<AppInfo> getAllAppInfos() {

		List<AppInfo> list = new ArrayList<AppInfo>();
		// 得到应用的packgeManager
		PackageManager packageManager = getPackageManager();
		// 创建一个主界面的intent
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		// 得到包含应用信息的列表
		List<ResolveInfo> ResolveInfos = packageManager.queryIntentActivities(
				intent, 0);
		// 遍历
		for (ResolveInfo ri : ResolveInfos) {
			// 得到包名
			String packageName = ri.activityInfo.packageName;
			// 得到图标
			Drawable icon = ri.loadIcon(packageManager);
			// 得到应用名称
			String appName = ri.loadLabel(packageManager).toString();
			// 封装应用信息对象
			AppInfo appInfo = new AppInfo(icon, appName, packageName);
			// 添加到list
			list.add(appInfo);
		}
		return list;
	}


	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		//删除当前行
			//删除当前行的数据
		data.remove(position);
			//更新列表
		//lv_main.setAdapter(adapter);//显示列表, 不会使用缓存的item的视图对象
		adapter.notifyDataSetChanged();//通知更新列表, 使用所有缓存的item的视图对象
		
		return true;
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_pw_uninstall:
			pw.dismiss();
			Toast.makeText(this, "卸载", 0).show();
			break;
		case R.id.ll_pw_run:
			pw.dismiss();
			Toast.makeText(this, "运行", 0).show();
			break;
		case R.id.ll_pw_share:
			pw.dismiss();
			Toast.makeText(this, "分享", 0).show();
			break;

		default:
			break;
		}
	}
}
