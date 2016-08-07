package com.atguigu.web.bean;

/*
 * 商品的信息
 */
public class ShopInfo {

	private int id;
	private String name;
	private String imagepath;
	private double price;

	public ShopInfo() {
		super();
	}

	public ShopInfo(int id, String name, String imagepath, double price) {
		super();
		this.id = id;
		this.name = name;
		this.imagepath = imagepath;
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	@Override
	public String toString() {
		return "ShopInfo [id=" + id + ", name=" + name + ", imagepath="
				+ imagepath + ", price=" + price + "]";
	}

}
