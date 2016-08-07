package com.atguigu.l03_component;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class DialogActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.activity_dialog);
	}
	
	/**
	 * 显示一般AlertDialog
	 * @param v
	 */
	public void showAD(View v) {
		//new AlertDialog.Builder(this).create().show();
		
		new AlertDialog.Builder(this)
				.setTitle("删除数据")//设置标题
				.setMessage("你确定删除数据吗")
				.setPositiveButton("删除", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(DialogActivity.this, "删除数据", 0).show();
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(DialogActivity.this, "取消删除数据", 0).show();
					}
				})
				.show();  //方法链调用
	}
	
	/**
	 * 显示单选列表AlertDialog
	 * @param v
	 */
	public void showLD(View v) {
		final String[] items = {"红", "蓝", "绿", "灰"}; //final的变量在方法执行完后还存在
		new AlertDialog.Builder(this)
				.setTitle("指定背景颜色")
				.setSingleChoiceItems(items, 2, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {//which就是选中的position
						//提示颜色
						Toast.makeText(DialogActivity.this, items[which], 0).show();
						//移除dilaog
						dialog.dismiss();
					}
				})
				.show();
	}
	
	/**
	 * 显示自定义AlertDialog
	 * @param v
	 */
	public void showCD(View v) {
		//动态加载布局文件, 得到对应的View对象
		View view = View.inflate(this, R.layout.dialog_view, null);
		//问题1:　view的真实类型?是布局文件根标签的类型, 包含了子View对象
		//问题2:　如何得到一个独立View的子View?  view.findViewById(id)
			//findViewById(id)是在setContentView()中的View中找
		
		final EditText nameET = (EditText) view.findViewById(R.id.et_dialog_name);
		final EditText pwdET = (EditText) view.findViewById(R.id.et_dialog_pwd);
		
		
		new AlertDialog.Builder(this)
		.setView(view)
		.setNegativeButton("取消", null)
		.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//读取用户名和密码
				String username = nameET.getText().toString();
				String pwd = pwdET.getText().toString();
				//提示
				Toast.makeText(DialogActivity.this, username+" : "+pwd, 0).show();
			}
		})
		.show();
	}
	
	/**
	 * 显示圆形进度ProgressDialog
	 * @param v
	 * @throws InterruptedException 
	 */
	public void showPD(View v){//回调方法: 主线程执行
		final ProgressDialog dialog = ProgressDialog.show(this, "数据加载", "数据加载中...");
		
		//模拟做一个长时间的工作
		//长时间的工作不能在主线程做, 得启动分线程完成
		new Thread(){
			public void run() {//分线程
				for(int i=0;i<20;i++) {
					//休息一会
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				//移除dialog
				dialog.dismiss();//方法在分线程执行, 但内部使用Handler实现主线程移除dialog
				
				//不能在分线程直接更新UI
				//显示toast
				//Toast.makeText(DialogActivity.this, "加载完成了!!!", 0).show();
				runOnUiThread(new Runnable() {
					@Override
					public void run() {//在主线程执行
						Toast.makeText(DialogActivity.this, "加载完成了!!!", 0).show();
					}
				});
				//runOnUiThread()在分线程执行
			}
		}.start();
		
	}
	/**
	 * 显示水平进度ProgressDialog
	 * @param v
	 */
	public void showPD2(View v) {
		//1. 创建dialog对象
		final ProgressDialog pd = new ProgressDialog(this);
		//2. 设置样式
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		//3. 显示
		pd.show();
		
		//4. 启动分线程, 加载数据, 并显示进度, 当加载完成移除dilaog
		new Thread(new Runnable() {
			@Override
			public void run() {
				int count = 20;
				//设置最大进度
				pd.setMax(count);
				for(int i=0;i<count;i++) {
					//休息一会
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					pd.setProgress(pd.getProgress()+1);
				}
				//移除dialog
				pd.dismiss();
			}
		}).start();
	}
	
	public void showDateAD(View v) {
		
		//创建日历对象
		Calendar calendar = Calendar.getInstance();
		//得到当前的年月日
		int year = calendar.get(Calendar.YEAR);//得到年份
		int monthOfYear = calendar.get(Calendar.MONTH);//月
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);//得到日
		Log.e("TAG", year+"-"+monthOfYear+"-"+dayOfMonth);
		
		new DatePickerDialog(this, new OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				Log.e("TAG", year+"--"+(monthOfYear+1)+"--"+dayOfMonth);
			}
		}, year, monthOfYear, dayOfMonth).show();
	}
	
	public void showTimeAD(View v) {
		
		Calendar c = Calendar.getInstance();
		int hourOfDay = c.get(Calendar.HOUR_OF_DAY); //得到小时
		int minute = c.get(Calendar.MINUTE); //得到分钟
		Log.e("TAG", hourOfDay+":"+minute);
		new TimePickerDialog(this, new OnTimeSetListener() {
			
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				Log.e("TAG", hourOfDay+"::"+minute);
			}
		}, hourOfDay, minute, true).show();
	}
}
