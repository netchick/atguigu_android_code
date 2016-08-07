package com.atguigu.l03_component;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 简单的component
 * @author Administrator
 *
 */
public class SimpleComponentActivity extends Activity {

	private TextView tv_simple_message;
	private EditText et_simple_number;
	private Button btn_simple_submit;
	private ImageView iv_simple_icon;
	
	private CheckBox cb_simple_basket;
	private CheckBox cb_simple_foot;
	private CheckBox cb_simple_pingpang;
	private RadioGroup rg_simple_sex;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_component);
		
		//1. TextView
		tv_simple_message = (TextView) findViewById(R.id.tv_simple_message);
		tv_simple_message.setText("尚硅谷0712NB");
		
		//2. EditText
		et_simple_number = (EditText) findViewById(R.id.et_simple_number);
		
		//3. Button
		btn_simple_submit = (Button) findViewById(R.id.btn_simple_submit);
		btn_simple_submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//得到内容
				String number = et_simple_number.getText().toString();
				//提示
				Toast.makeText(SimpleComponentActivity.this, number, 0).show();
			}
		});
		
		//4. ImageView
		iv_simple_icon = (ImageView) findViewById(R.id.iv_simple_icon);
		iv_simple_icon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//设置背景图片
				iv_simple_icon.setBackgroundResource(android.R.drawable.alert_light_frame);
				//设置前景图片
				iv_simple_icon.setImageResource(android.R.drawable.ic_media_pause);
			}
		});
		
		//5. CheckBox
		cb_simple_basket = (CheckBox) findViewById(R.id.cb_simple_basket);
		cb_simple_foot = (CheckBox) findViewById(R.id.cb_simple_foot);
		cb_simple_pingpang = (CheckBox) findViewById(R.id.cb_simple_pingpang);
		//给cb_simple_foot设置选中状态改变的监听
		cb_simple_foot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked) {
					Toast.makeText(SimpleComponentActivity.this, "选中了足球", 0).show();
				} else {
					Toast.makeText(SimpleComponentActivity.this, "未选中足球", 0).show();
				}
			}
		});
		
		//6. RadioGroup/RadioButton
		rg_simple_sex = (RadioGroup) findViewById(R.id.rg_simple_sex);
		rg_simple_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {//checkedId 选中的radioButton的id
				//找到选中的radioButton
				RadioButton radioButton = (RadioButton) findViewById(checkedId);
				//得到文本
				String sex = radioButton.getText().toString();
				//提示
				Toast.makeText(SimpleComponentActivity.this, sex, 0).show();
			}
		});
	}
	
	public void confirm(View v) {
		StringBuffer sb = new StringBuffer();
		if(cb_simple_basket.isChecked()) {
			sb.append(cb_simple_basket.getText().toString()).append(" ");
		}
		
		if(cb_simple_foot.isChecked()) {
			sb.append(cb_simple_foot.getText().toString()).append(" ");
		}
		
		if(cb_simple_pingpang.isChecked()) {
			sb.append(cb_simple_pingpang.getText().toString());
		}
		
		//提示
		Toast.makeText(this, sb.toString(), 0).show();
	}
}
