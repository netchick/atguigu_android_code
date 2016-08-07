package com.example.l12_fragment2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/*
	添加Fragment对象显示
		onAttach()-->onCreate()-->onCreateView()-->onActivityCreated()-->onstart()-->onResume()
	home到桌面
		onPause()-->onStop()
	回到应用
		onStart()-->onResume()
	replace为其它Fragment
		onPause()-->onStop()-->onDestroyView()
	返回到本身的Fragment
		onCreateView()-->onActivityCreated()-->onstart()-->onResume()
	退出应用
		onPause()-->onstop()-->onDestroyView()-->onDestroy()-->onDetach()
 */
public class MyFragment1 extends Fragment {

	@Override
	public void onAttach(Activity activity) {
		Log.e("TAG", "onAttach()");
		super.onAttach(activity);
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.e("TAG", "onCreate()");
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.e("TAG", "onCreateView()");
		//加载布局得到View对象并返回
		
		//创建一个视图对象, 设置数据并返回
		TextView  textView = new TextView(getActivity());
		textView.setText("fragment11111");
		textView.setBackgroundColor(Color.RED);
		
		return textView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.e("TAG", "onActivityCreated()");
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onStart() {
		Log.e("TAG", "onStart()");
		super.onStart();
	}
	
	@Override
	public void onResume() {
		Log.e("TAG", "onResume()");
		super.onResume();
	}
	
	@Override
	public void onPause() {
		Log.e("TAG", "onPause()");
		super.onPause();
	}
	
	@Override
	public void onStop() {
		Log.e("TAG", "onStop()");
		super.onStop();
	}
	
	@Override
	public void onDestroyView() {
		Log.e("TAG", "onDestroyView()");
		super.onDestroyView();
	}
	
	@Override
	public void onDestroy() {
		Log.e("TAG", "onDestroy()");
		super.onDestroy();
	}
	
	@Override
	public void onDetach() {
		Log.e("TAG", "onDetach()");
		super.onDetach();
	}
}
