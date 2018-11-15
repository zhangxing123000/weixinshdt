package com.weixin.user;


/**
 * 客户接口发送消息
 * @author Administrator
 *
 */
public class MessgeCustomSend {

	String touser;//普通用户openid
	MessgeContent text;//消息文本内容
	String msgtype;//消息类型
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public MessgeContent getText() {
		return text;
	}
	public void setText(MessgeContent text) {
		this.text = text;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	
	
	
}
