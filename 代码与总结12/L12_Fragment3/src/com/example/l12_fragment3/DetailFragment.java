package com.example.l12_fragment3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 *显示详情的Fragment
 * @author 张晓飞
 *
 */
public class DetailFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		TextView textView = new TextView(getActivity());
		//取出保存的数据
		String detail = getArguments().getString("DETAIL");
		//设置
		textView.setText(detail);
		return textView;
	}
}
