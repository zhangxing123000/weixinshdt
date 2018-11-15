package com.weixin.wxservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface ICoreService {
	
	/**
	 * 获取分组信息 接口
	 * 
	 * @return
	 */
	@WebMethod
	public abstract String getTagList();
	
	/**
	 * 根据分组获取人员列表 接口
	 * 
	 * @param tagidflag
	 * @return
	 */
	@WebMethod
	public abstract String getUserList(String tagidflag);
	
	/**
	 * 
	 * 给用户群发消息按标签 有次数限制 接口
	 * 
	 * @param tagidflag
	 *            标签
	 * @return
	 */
	public abstract String returnMessge(String tagidflag, String content);
	
	/**
	 * 
	 * 给用户群发消息模板 接口
	 * 
	 * @param tagidflag
	 *            标签
	 * @return
	 */
	public abstract String SendTemplateData(String tagidflag);
	
	/**
	 * 
	 * 给用户群发消息 用客服接口不限次数 接口
	 * 
	 * @param tagidflag
	 *            标签
	 * @return
	 */
	public abstract String returnCustomMessge(String tagidflag, String content);
	
	/**
	 * 给用户设置标签 接口
	 * 
	 * @param fromUserName
	 *            用户ID
	 * @param tagidflag
	 *            标签
	 * @return
	 */
	@WebMethod
	public abstract String setTags(@WebParam(name="fromUserName")String fromUserName, @WebParam(name="tagidflag")String tagidflag);
	
	/**
	 * 创建菜单接口
	 * @param content
	 * @return
	 */
	@WebMethod
	public abstract String createMenu(@WebParam(name="content")String content);
	
	/**
	 * 删除菜单 接口
	 * @return
	 */
	public abstract String deleteMenu();
	
	/**
	 * 创建客服人员
	 * @param content 客服人员信息
	 * 参数格式："{ \"kf_account\" : \"test1@test\", \"nickname\" : \"客服1\",  \"password\" : \"pswmd5\",  }";
	 * @return
	 */
	public abstract String createKeFu(@WebParam(name="content")String content);
	
	/**
	 * 客服推送消息到指定用户
	 * @param content 客服人员信息
	 * 参数格式："{ \"kf_account\" : \"test1@test\", \"nickname\" : \"客服1\",  \"password\" : \"pswmd5\",  }";
	 * @return
	 */
	public abstract String MessageKeFu(@WebParam(name="content")String content);
	
	/**
	 * 发模板消息接口给指定用户
	 * @param content	{\"touser\":\"oPP1dwS2I-W5-VOs_s360_Jr9UO0\",\"template_id\":\"nchBgpWs4C7wQhJGCA_WA21eHGOKJRqTkkt0EOlKjOA\",\"url\":\"http://weixin.qq.com/download\",\"topcolor\":\"#FF0000\",\"data\":{\"first\":{\"value\":\"您好，您有一个新的会议。\",\"color\":\"#173177\"},\"keyword1\":{\"value\":\"思科交流会\",\"color\":\"#173177\"},\"keyword2\":{\"value\":\"2017年8月1日\",\"color\":\"#173177\"}}}
	 * @return
	 */
	public abstract String templateMessage(@WebParam(name="content")String content);
}
