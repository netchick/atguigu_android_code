package com.atguigu.app03_gridview;

import android.content.Context;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainAdapter extends BaseAdapter {

	private String[] names;
	private int[] icons;
	private Context context;
	
	public MainAdapter(Context context, String[] names, int[] icons) {
		super();
		this.context = context;
		this.names = names;
		this.icons = icons;
	}

	@Override
	public int getCount() {
		return names.length;
	}

	@Override
	public Object getItem(int position) {
		return names[position];
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView==null) {
			convertView = View.inflate(context, R.layout.item_main, null);
		}
		
		ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_item_icon);
		TextView textView = (TextView) convertView.findViewById(R.id.tv_item_name);
		imageView.setImageResource(icons[position]);
		textView.setText(names[position]);
		
		return convertView;
	}

}
