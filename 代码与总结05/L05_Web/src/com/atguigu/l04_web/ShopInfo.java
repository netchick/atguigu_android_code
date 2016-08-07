package com.atguigu.l04_web;


public class ShopInfo {

	private int id; //一定要与json对象字符串中的key的名称要一致
	private String name;
	private double price;
	private String imagePath;

	public ShopInfo(int id, String name, double price, String imagePath) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.imagePath = imagePath;
	}

	public ShopInfo() {
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public String toString() {
		return "ShopInfo [id=" + id + ", name=" + name + ", price=" + price
				+ ", imagePath=" + imagePath + "]";
	}

}
