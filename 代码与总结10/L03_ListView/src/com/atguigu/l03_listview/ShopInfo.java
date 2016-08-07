package com.atguigu.l03_listview;

/**
 * 每行Item的数据信息封装类
 * @author 张晓飞
 *
 */
public class ShopInfo {

	private int icon;
	private String name;
	private String content;

	public ShopInfo(int icon, String name, String content) {
		super();
		this.icon = icon;
		this.name = name;
		this.content = content;
	}

	public ShopInfo() {
		super();
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ShopInfo [icon=" + icon + ", name=" + name + ", content="
				+ content + "]";
	}

}
