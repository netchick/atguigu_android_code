package com.example.l12_fragment2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyFragment2 extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		//加载布局得到View对象并返回
		
		//创建一个视图对象, 设置数据并返回
		TextView  textView = new TextView(getActivity());
		textView.setText("fragment22222");
		
		return textView;
	}
}
