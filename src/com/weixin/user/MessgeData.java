package com.weixin.user;

public class MessgeData {

	String value;
	String color;//模板内容字体颜色，不填默认为黑色
	
	public MessgeData(String value) {
		// TODO Auto-generated constructor stub
		this.value=value;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	

}
