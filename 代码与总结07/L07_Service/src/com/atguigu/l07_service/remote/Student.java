package com.atguigu.l07_service.remote;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Student  implements Parcelable{

	private int id;
	private String name;
	private double price;

	public Student(int id, String name, double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public Student() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", price=" + price
				+ "]";
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	//将当前对象的属性数据打成包: 写到包对象中
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		Log.e("TAG", "打包 writeToParcel()");
		//id
		dest.writeInt(id);
		//name
		dest.writeString(name);
		//price
		dest.writeDouble(price);
	}
	
	// 添加一个静态成员,名为CREATOR,该对象实现了Parcelable.Creator接口
	public static final Parcelable.Creator<Student> CREATOR = new  Parcelable.Creator<Student>() {

		//解包: 读取包中的数据并封装成对象
		@Override
		public Student createFromParcel(Parcel source) {
			Log.e("TAG", "解包 createFromParcel()");
			//id
			int id = source.readInt();
			//name
			String name = source.readString();
			//price
			double price = source.readDouble();
			
			return new Student(id, name, price);
		}

		//返回一个指定大小的对象容器
		@Override
		public Student[] newArray(int size) {
			return new Student[size];
		}
	};

}
