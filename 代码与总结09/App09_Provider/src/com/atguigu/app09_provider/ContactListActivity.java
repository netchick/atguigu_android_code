package com.atguigu.app09_provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ContactListActivity extends ListActivity implements OnItemClickListener {

	private ListView listView;
	private ContactAdapter adapter;
	private List<Map<String, String>> data = new ArrayList<Map<String,String>>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_list);
		
		listView = getListView();
		adapter = new ContactAdapter();
		//查询得到联系人表数据
			//ContentResolver
		ContentResolver resolver = getContentResolver();
			//执行查询得到cursor
		String[] projection = {Phone.DISPLAY_NAME, Phone.NUMBER};
		Cursor cursor = resolver.query(Phone.CONTENT_URI, projection, null, null, null);
			//取出其中的数据保存到data
		while(cursor.moveToNext()) {
			String name = cursor.getString(0);
			String number = cursor.getString(1);
			Map<String, String> map = new HashMap<String, String>();
			map.put("name", name);
			map.put("number", number);
			data.add(map);
		}
		//显示列表
		listView.setAdapter(adapter);
		
		//给listView添加item点击监听
		listView.setOnItemClickListener(this);
		
	}
	
	class ContactAdapter extends BaseAdapter {

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
				convertView = View.inflate(ContactListActivity.this, R.layout.item_contact, null);
			}
			
			Map<String, String> map = data.get(position);
			TextView nameTV = (TextView) convertView.findViewById(R.id.tv_item_name);
			TextView nubmerTV = (TextView) convertView.findViewById(R.id.tv_item_number);
			nameTV.setText(map.get("name"));
			nubmerTV.setText(map.get("number"));
			
			return convertView;
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		//得到选择的号码
		String number = data.get(position).get("number");
		Intent intent = getIntent();
		intent.putExtra("NUMBER", number);
		//设置结果
		setResult(RESULT_OK, intent);
		//返回
		finish();
	}
}
