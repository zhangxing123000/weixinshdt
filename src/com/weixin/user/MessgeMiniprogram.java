package com.weixin.user;

/**
 * 20170728
 * 
 * @author Administrator 模板发送消息 跳小程序所需数据，不需跳小程序可不用传该数据
 *
 */
public class MessgeMiniprogram {

	String appid;//所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系）
	String pagepath;//所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar）
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getPagepath() {
		return pagepath;
	}
	public void setPagepath(String pagepath) {
		this.pagepath = pagepath;
	}
	
}
