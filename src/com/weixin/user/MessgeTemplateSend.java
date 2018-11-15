package com.weixin.user;

/**
 * 20170728
 * @author Administrator
 *
 */
public class MessgeTemplateSend {
	String touser;//接收者openid
	String template_id;//模板ID
	String url;//模板跳转链接
	MessgeMiniprogram miniprogram;//跳小程序所需数据，不需跳小程序可不用传该数据
	MessgeTemplateData data;//模板数据
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public MessgeMiniprogram getMiniprogram() {
		return miniprogram;
	}
	public void setMiniprogram(MessgeMiniprogram miniprogram) {
		this.miniprogram = miniprogram;
	}
	public MessgeTemplateData getData() {
		return data;
	}
	public void setData(MessgeTemplateData data) {
		this.data = data;
	}
	

}
