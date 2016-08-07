package com.atguigu.l04_datastorage;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import com.android.volley.Request.Method;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NetworkActivity extends Activity {

	private EditText et_network_url;
	private EditText et_network_result;
	private RequestQueue queue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_network);
		
		et_network_url = (EditText) findViewById(R.id.et_network_url);
		et_network_result = (EditText) findViewById(R.id.et_network_result);
		
		queue = Volley.newRequestQueue(this);
	}

	/*
	 * 使用httpUrlConnection提交get请求
	 */
	/*
		1. 显示ProgressDialog
		2. 启动分线程
		3. 在分线程, 发送请求, 得到响应数据
			1). 得到path, 并带上参数name=Tom1&age=11
			2). 创建URL对象
			3). 打开连接, 得到HttpURLConnection对象
			4). 设置请求方式,连接超时, 读取数据超时
			5). 连接服务器
			6). 发请求, 得到响应数据
				得到响应码, 必须是200才读取
				得到InputStream, 并读取成String
			7). 断开连接
		4. 在主线程, 显示得到的结果, 移除dialog
	 */
	public void testConnectionGet(View v) {
		//1. 显示ProgressDialog
		final ProgressDialog dialog = ProgressDialog.show(this, null, "正在请求中...");
		//2. 启动分线程
		new Thread(){
			//3. 在分线程, 发送请求, 得到响应数据
			public void run() {
				try {
					//1). 得到path, 并带上参数name=Tom1&age=11
					String path = et_network_url.getText().toString()+"?name=Tom1&age=11";
					//2). 创建URL对象
					URL url = new URL(path);
					//3). 打开连接, 得到HttpURLConnection对象
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					//4). 设置请求方式,连接超时, 读取数据超时
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(5000);
					connection.setReadTimeout(6000);
					//5). 连接服务器
					connection.connect();
					//6). 发请求, 得到响应数据
						//得到响应码, 必须是200才读取
					int responseCode = connection.getResponseCode();
					if(responseCode==200) {
						//得到InputStream, 并读取成String
						InputStream is = connection.getInputStream();
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						byte[] buffer = new byte[1024];
						int len = -1;
						while((len=is.read(buffer))!=-1) {
							baos.write(buffer, 0, len);
						}
						final String result = baos.toString();
						
						baos.close();
						is.close();
						
						//4. 在主线程, 显示得到的结果, 移除dialog
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								et_network_result.setText(result);
								dialog.dismiss();
							}
						});
					}
					//7). 断开连接
					connection.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
					//如果出了异常要移除dialog
					dialog.dismiss();
				}
			}
		}.start();
	}

	/*
	 * 使用httpUrlConnection提交post请求
	 */
	/*
		1. 显示ProgressDialog
		2. 启动分线程
		3. 在分线程, 发送请求, 得到响应数据
			1). 得到path
			2). 创建URL对象
			3). 打开连接, 得到HttpURLConnection对象
			4). 设置请求方式,连接超时, 读取数据超时
			5). 连接服务器
			6). 发请求, 得到响应数据
				得到输出流, 写请求体:name=Tom1&age=11
				得到响应码, 必须是200才读取
				得到InputStream, 并读取成String
			7). 断开连接
		4. 在主线程, 显示得到的结果, 移除dialog
	 */
	public void testConnectionPost(View v) {
		//1. 显示ProgressDialog
		final ProgressDialog dialog = ProgressDialog.show(this, null, "正在加载中...");
		//2. 启动分线程
		new Thread(new Runnable() {
			//3. 在分线程, 发送请求, 得到响应数据
			@Override
			public void run() {
				try {
					//1). 得到path
					String path = et_network_url.getText().toString();
					//2). 创建URL对象
					URL url = new URL(path);
					//3). 打开连接, 得到HttpURLConnection对象
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					//4). 设置请求方式,连接超时, 读取数据超时
					connection.setRequestMethod("POST");
					connection.setConnectTimeout(5000);
					connection.setReadTimeout(5000);
					//5). 连接服务器
					connection.connect();
					//6). 发请求, 得到响应数据
						//得到输出流, 写请求体:name=Tom1&age=11
					OutputStream os = connection.getOutputStream();
					String data = "name=Tom2&age=12";
					os.write(data.getBytes("utf-8"));
						//得到响应码, 必须是200才读取
					int responseCode = connection.getResponseCode();
					if(responseCode==200) {
						//得到InputStream, 并读取成String
						InputStream is = connection.getInputStream();
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						byte[] buffer = new byte[1024];
						int len = -1;
						while((len=is.read(buffer))!=-1) {
							baos.write(buffer, 0, len);
						}
						final String result = baos.toString();
						
						baos.close();
						is.close();
						
						//4. 在主线程, 显示得到的结果, 移除dialog
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								et_network_result.setText(result);
								dialog.dismiss();
							}
						});
					}
					os.close();
					//7). 断开连接
					connection.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
					dialog.dismiss();
				}
			}
		}).start();
	}

	/*
	 * 使用httpClient提交get请求
	 */
	public void testClientGet(View v) {
		//1. 显示ProgressDialog
		final ProgressDialog dialog = ProgressDialog.show(this, null, "正在请求中...");
		//2. 启动分线程
		new Thread(){
			//3. 在分线程, 发送请求, 得到响应数据
			public void run() {
				try {
					//1). 得到path, 并带上参数name=Tom1&age=11
					String path = et_network_url.getText().toString()+"?name=Tom3&age=13";
					
					//2). 创建HttpClient对象
					HttpClient httpClient = new DefaultHttpClient();
					//3). 设置超时
					HttpParams params = httpClient.getParams();
					HttpConnectionParams.setConnectionTimeout(params, 5000);
					HttpConnectionParams.setSoTimeout(params, 5000);
					//4). 创建请求对象
					HttpGet request = new HttpGet(path);
					//5). 执行请求对象, 得到响应对象
					HttpResponse response = httpClient.execute(request);
					
					int statusCode = response.getStatusLine().getStatusCode();
					if(statusCode==200) {
						//6). 得到响应体文本
						HttpEntity entity = response.getEntity();
						final String result = EntityUtils.toString(entity);
						//4. 要主线程, 显示数据, 移除dialog
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								et_network_result.setText(result);
								dialog.dismiss();
							}
						});
					}
					//7). 断开连接
					httpClient.getConnectionManager().shutdown();
				} catch (Exception e) {
					e.printStackTrace();
					//如果出了异常要移除dialog
					dialog.dismiss();
				}
			}
		}.start();
	}

	/*
	 * 使用httpClient提交post请求
	 */
	public void testClientPost(View v) {
		//1. 显示ProgressDialog
		final ProgressDialog dialog = ProgressDialog.show(this, null, "正在请求中...");
		//2. 启动分线程
		new Thread(){
			//3. 在分线程, 发送请求, 得到响应数据
			public void run() {
				try {
					//1). 得到path
					String path = et_network_url.getText().toString();
					
					//2). 创建HttpClient对象
					HttpClient httpClient = new DefaultHttpClient();
					//3). 设置超时
					HttpParams params = httpClient.getParams();
					HttpConnectionParams.setConnectionTimeout(params, 5000);
					HttpConnectionParams.setSoTimeout(params, 5000);
					//4). 创建请求对象
					HttpPost request = new HttpPost(path);
					//设置请求体
					List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
					parameters.add(new BasicNameValuePair("name", "Tom4"));
					parameters.add(new BasicNameValuePair("age", "14"));
					HttpEntity entity = new UrlEncodedFormEntity(parameters);
					request.setEntity(entity);
					
					//5). 执行请求对象, 得到响应对象
					HttpResponse response = httpClient.execute(request);
					
					int statusCode = response.getStatusLine().getStatusCode();
					if(statusCode==200) {
						//6). 得到响应体文本
						entity = response.getEntity();
						final String result = EntityUtils.toString(entity);
						//4. 要主线程, 显示数据, 移除dialog
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								et_network_result.setText(result);
								dialog.dismiss();
							}
						});
					}
					//7). 断开连接
					httpClient.getConnectionManager().shutdown();
				} catch (Exception e) {
					e.printStackTrace();
					//如果出了异常要移除dialog
					dialog.dismiss();
				}
			}
		}.start();
	}

	/*
	 * 使用Volley提交get请求
	 */
	/*
	 1. 创建请求队列对象(一次)
	 2. 创建请求对象StringRequest
	 3. 将请求添加到队列中
	 */
	public void testVolleyGet(View v) {
		
		final ProgressDialog dialog = ProgressDialog.show(this, null, "正在请求中...");
		
		//创建请求对象StringRequest
		String path = et_network_url.getText().toString()+"?name=Tom5&age=15";
		StringRequest request = new StringRequest(path, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {//在主线程执行
				et_network_result.setText(response);
				dialog.dismiss();
			}
		}, null);
		//将请求添加到队列中
		queue.add(request);
	}

	/*
	 * 使用Volley提交post请求
	 */
	public void testVolleyPost(View v) {
		final ProgressDialog dialog = ProgressDialog.show(this, null, "正在请求中...");
		
		//创建请求对象StringRequest
		String path = et_network_url.getText().toString();
		StringRequest request = new StringRequest(Method.POST, path, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				et_network_result.setText(response);
				dialog.dismiss();
			}
		}, null){
			//重写此方法返回参数的map作为请求体
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				map.put("name", "Tom6");
				map.put("age", "16");
				return map;
			}
		};
		//将请求添加到队列中
		queue.add(request);
	}

}
