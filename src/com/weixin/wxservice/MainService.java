package com.weixin.wxservice;

import javax.xml.ws.Endpoint;

public class MainService {
	//发布接口
	public static void main(String[] args) {
		String url = "http://10.35.31.60:8080/weixinshdt";
//		String className = "";
//		Endpoint endpoint = Endpoint.publish(url, new CoreService());
		Endpoint endpoint = Endpoint.publish(url, new CoreService());
	}

}
