package com.weixin.user;

/**
 * 根据标签获取 粉丝  最多10000 
 * 可以上次拉取得最后一个作为本次的第一个继续拉取   20170725
 * @author Administrator
 *
 */
public class UserbyTag {

	private String tagid="";//标签id
	private String next_openid="";//第一个拉取的OPENID，不填默认从头开始拉取
	public String getTagid() {
		return tagid;
	}
	public void setTagid(String tagid) {
		this.tagid = tagid;
	}
	public String getNext_openid() {
		return next_openid;
	}
	public void setNext_openid(String next_openid) {
		this.next_openid = next_openid;
	}
}
