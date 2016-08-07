package com.atguigu.l05_handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 使用Handler的小DEMO
 * @author 张晓飞
1. 手动增加/减少
2. 自动增加/减少
3. 限制数字的最大和最小值 [1,20]
4. 限制Button可操作性
 */
public class HandlerDemoActivity extends Activity implements OnClickListener {

	private static final int WHAT_INCREASE = 1;
	private static final int WHAT_DECREASE = 2;
	
	
	private TextView tv_demo_number;
	private Button btn_demo_increase;
	private Button btn_demo_decrease;
	private Button btn_demo_pause;
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			//得到当前显示的数值
			int number = Integer.parseInt(tv_demo_number.getText().toString());
			switch (msg.what) {
			case WHAT_INCREASE:
				//限制number<=20
				if(number==20) {
					//设置暂停不能操作
					btn_demo_pause.setEnabled(false);
					Toast.makeText(HandlerDemoActivity.this, "已经达到最大值", 0).show();
					return;
				}
				
				number++;
				tv_demo_number.setText(number+"");
				//发送增加的延迟消息
				handler.sendEmptyMessageDelayed(WHAT_INCREASE, 1000);
				break;
			case WHAT_DECREASE:
				//限制number>=1
				if(number==1) {
					//设置暂停不能操作
					btn_demo_pause.setEnabled(false);
					Toast.makeText(HandlerDemoActivity.this, "已经达到最小值", 0).show();
					return;
				}
				number--;
				tv_demo_number.setText(number+"");
				//发送减少的延迟消息
				handler.sendEmptyMessageDelayed(WHAT_DECREASE, 1000);
				break;
			default:
				break;
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_handler_demo);
		init();
	}

	private void init() {
		tv_demo_number = (TextView) findViewById(R.id.tv_demo_number);
		btn_demo_increase = (Button) findViewById(R.id.btn_demo_increase);
		btn_demo_decrease = (Button) findViewById(R.id.btn_demo_decrease);
		btn_demo_pause = (Button) findViewById(R.id.btn_demo_pause);
		
		btn_demo_increase.setOnClickListener(this);
		btn_demo_decrease.setOnClickListener(this);
		btn_demo_pause.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v==btn_demo_increase) {//自动增加  what =1
			//限制Button可操作性
			btn_demo_increase.setEnabled(false);
			btn_demo_decrease.setEnabled(true);
			btn_demo_pause.setEnabled(true);
			
			//停止减少(移除未处理的减少的消息)
			handler.removeMessages(WHAT_DECREASE);
			//发消息(增加)
			handler.sendEmptyMessage(WHAT_INCREASE);
		} else if(v==btn_demo_decrease) {//自动减少 what=2
			//限制Button可操作性
			btn_demo_increase.setEnabled(true);
			btn_demo_decrease.setEnabled(false);
			btn_demo_pause.setEnabled(true);
			
			//停止增加(移除未处理的增加的消息)
			handler.removeMessages(WHAT_INCREASE);
			
			//发消息(减少)
			handler.sendEmptyMessage(WHAT_DECREASE);
		} else if(v==btn_demo_pause) {//暂停
			
			//限制Button可操作性
			btn_demo_increase.setEnabled(true);
			btn_demo_decrease.setEnabled(true);
			btn_demo_pause.setEnabled(false);
			
			//停止增加/减少(移除未处理的减少/增加的消息)
			handler.removeMessages(WHAT_INCREASE);
			handler.removeMessages(WHAT_DECREASE);
		}
	}
}
