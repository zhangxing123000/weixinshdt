package com.weixin.user;
/**
 * 20170726
 */
//消息群发根据标签
public class MessgeSend {
	Messgefilter filter; //用于设定是否向全部用户发送，值为true或false，选择true该消息群发给所有用户，选择false可根据tag_id发送给指定群组的用户
	MessgeContent text;//消息文本内容
	String msgtype;//消息类型
	public Messgefilter getFilter() {
		return filter;
	}
	public void setFilter(Messgefilter filter) {
		this.filter = filter;
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
