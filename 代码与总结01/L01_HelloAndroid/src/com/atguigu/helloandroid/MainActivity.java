package com.atguigu.helloandroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * 主界面Activity类
 * 主界面:　点击应用图标启动的界面
 * extends Activity
 * 
 * @author Administrator
 *
 */
public class MainActivity extends Activity {

	/**
	 * 重写的方法
	 * onCreate: 在当前类(activity)对象创建的时候自动调用
	 * 回调方法: 不是我们调的, 是系统在一定条件下自动调用的, 基本都以on开头  onXXX
	 * 		   这些方法我们不需要调用, 一般只是去重写此类方法
	 * 
	 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //调用父类处理做一些默认的初始化工作
    	super.onCreate(savedInstanceState);
    	//设置窗口要显示的内容视图(界面/布局)
    	//指定布局文件在R所对应的变量, 加载布局文件最终显示到窗口中
       // setContentView(R.layout.activity_main);
        
        setContentView(R.layout.size_test);
        
        //测试Log
        Log.e("TAG", "e()的打印信息");
        Log.i("xfzhang", "i()的打印信息");
        System.out.println("system的打印....");
        
    }
}
