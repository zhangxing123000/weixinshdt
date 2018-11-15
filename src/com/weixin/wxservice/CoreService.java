package com.weixin.wxservice;

import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.servlet.http.HttpServlet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.weixin.user.MessgeContent;
import com.weixin.user.MessgeCustomSend;
import com.weixin.user.MessgeData;
import com.weixin.user.MessgeSend;
import com.weixin.user.MessgeTemplateData;
import com.weixin.user.MessgeTemplateSend;
import com.weixin.user.Messgefilter;
import com.weixin.user.TagInfo;
import com.weixin.user.UserOpenid;
import com.weixin.user.UserOpenidList;
import com.weixin.user.UserTags;
import com.weixin.user.UserbyTag;
import com.weixin.wxmessage.resp.TextMessage;
import com.weixin.wxpojo.AccessToken;
import com.weixin.wxutil.MessageUtil;
import com.weixin.wxutil.WeixinUtil;

import net.sf.json.JSONObject;

import com.weixin.user.WeiXinUserInfo;

/**
 * 类名: CoreService </br>
 * 描述: 核心服务类 </br>
 */
@WebService(endpointInterface = "com.weixin.wxservice.ICoreService")
public class CoreService extends HttpServlet implements ICoreService {
	public static String appId = "wxd426078cc6b4e46f";
	public static String appSecret = "1dfdcf7c1f280d9042fb2516baad57cb";

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return xml
	 */
	public String processRequest(String request) {

		// xml格式的消息数据
		String respXml = null;
		// 默认返回的文本消息内容
		String respContent = "未知的消息类型！";
		try {
			// 调用parseXml方法解析请求消息

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			StringReader sr = new StringReader(request);
			InputSource is = new InputSource(sr);
			Document document = db.parse(is);

			Element root = document.getDocumentElement();
			NodeList ndfromUserName = root.getElementsByTagName("FromUserName");
			NodeList ndtoUserName = root.getElementsByTagName("ToUserName");
			NodeList ndmsgType = root.getElementsByTagName("MsgType");
			NodeList ndmsgId = root.getElementsByTagName("MsgId");

			// 发送方帐号
			String fromUserName = ndfromUserName.item(0).getTextContent();
			// if (action != "" && action != null) {
			// if(action.equals("viewtest")){
			// return fromUserName;
			// }
			// }
			// 开发者微信号
			String toUserName = ndtoUserName.item(0).getTextContent();
			// 消息类型
			String msgType = ndmsgType.item(0).getTextContent();

			// String msgId = ndmsgId.item(0).getTextContent();

			//
			//
			// BaseMessage basemag=new BaseMessage();
			// basemag.setFromUserName(fromUserName.item(0).getTextContent());
			//

			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				respContent = "您发送的是文本消息！";

				// 根据回复的消息 添加分组 回复成功
				NodeList ndContent = root.getElementsByTagName("Content");

				// 获得文本
				String content = ndContent.item(0).getTextContent();
				// 调用接口获取access_token

				try {
					if ("场景模拟".equals(content)) {
						// 假设这里是其他的消息
						List cc = getIsNotMessage(fromUserName);

						if (cc.size() == 0) {
							// 进入这里说明近期没有给他发送的消息
							respContent = "请输有效入回复";
						} else {
							Map m = (Map) cc.get(0);
							String status = (String) m.get("status"); // 消息的状态
																		// 1：是，0：否
							String time = (String) m.get("time"); // 确认截止时间
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
							String newDate = df.format(new Date());
							if (compare_date(newDate, time)) {
								// 进入这里说明任务已超时，不可再参加
								respContent = "该任务报名已截止！";
							} else {
								if (status.equals("1")) {
									// 进入这里说明已经确认过参加
									respContent = "已确认参加,请勿重复回复！";
								} else if (status.equals("0")) {
									// 进入这里说明已经确认过不参加
									respContent = "已确认不参加,请勿重复回复！";
								} else {
									// 进入这里说明消息可以通过
									respContent = "确认参加";
								}
							}
						}
					} else if ("客服".equals(content)) {

						textMessage.setMsgType("transfer_customer_service");

					} else if ("分组".equals(content)) {
						respContent = "进行分组：\n 回复\"是\"分配到“专家组”,\n 回复\"否\"分配到“业余组”";
					} else if ("模板".equals(content)) {

						System.out.print("走到模版这里来了");
						respContent = SendTemplateData("100");
					}
					/*
					 * case "是": respContent = setTags(fromUserName, "是");
					 * break; case "否": respContent = setTags(fromUserName,
					 * "否"); break;
					 */
					else if ("消息".equals(content)) {
						respContent = "推送消息：\n 回复 ：\n发专家组+“消息”   发送消息给专家组”,\n 回复\n发业余组+“消息”   发送消息给业余组”";
					} else if ("群发消息".equals(content)) {
						respContent = "推送消息：\n 回复 ：\n群发专家组+“消息”   群发送消息给专家组”,\n 回复\n群发业余组+“消息”   群发送消息给业余组”";
					}
					/*
					 * case "1": respContent = "提交成功";// returnMessge("100");
					 * break; case "2": respContent = "提交失败";//
					 * returnMessge("102");//业余组群发消息 break;
					 */
					else if ("所有组".equals(content)) {
						respContent = getTagList();
					} else if ("专家组".equals(content)) {
						respContent = getUserList("100");
					} else if ("业余组".equals(content)) {
						respContent = getUserList("102");
					} else if ("星标组".equals(content)) {
						respContent = getUserList("2");
					} else if ("其他组".equals(content)) {
						respContent = getUserList("");
					}

					else {
						if (content.indexOf("群发业余组") >= 0) {

							respContent = returnCustomMessge("102", content.replace("群发专家组", ""));
						} else if (content.indexOf("群发专家组") >= 0) {

							respContent = returnCustomMessge("100", content.replace("群发专家组", ""));
						} else if (content.indexOf("发专家组") >= 0) {
							respContent = returnMessge("100", content.replace("发专家组", ""));

						} else if (content.indexOf("发业余组") >= 0) {

							respContent = returnMessge("102", content.replace("发专家组", ""));
						} else {
							respContent = "自动分组回复：分组  ";
							respContent += " \n 按标签发送消息回复：消息";
							respContent += " \n 客服群发消息回复: 群发消息";
							respContent += " \n 模板群发消息回复: 模板消息";
							respContent += " \n 获得分组消息回复: 所有组";
						}
					}

				} catch (Exception ex) {
					// TODO: handle exception

				}

			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
			}
			// 语音消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是语音消息！";
			}
			// 视频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				respContent = "您发送的是视频消息！";
			}
			// 视频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_SHORTVIDEO)) {
				respContent = "您发送的是小视频消息！";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = root.getElementsByTagName("Event").item(0).getTextContent();
				// 关注
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "感谢您的关注！请点击“菜单3”，选择“账号绑定”，进行账号绑定.";
				}
				// 取消关注
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
				}
				// 扫描带参数二维码
				else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
					// TODO 处理扫描带参数二维码事件
				}
				// 上报地理位置
				else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
					// TODO 处理上报地理位置事件
				}
				// 自定义菜单
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO 处理菜单点击事件
				}
			}
			// 设置文本消息的内容
			textMessage.setContent(respContent);

			// 20170724 wzn
			textMessage.setFuncFlag("0");

			System.out.print("\n 处理消息 CoreService processRequest(String request)  [" + request + "]");
			// 将文本消息对象转换成xml 加密转换
			respXml = MessageUtil.messageToXml(textMessage);

			System.out.print("\n 处理消息 CoreService respXml  [" + respXml + "]");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("\n 处理消息 CoreService processRequest(String request)  [" + request + "]" + e.getMessage());
		}
		return respXml;
	}

	/**
	 * 获取分组信息 接口
	 * 
	 * @return
	 */
	public String getTagList() {
		String respContent = "";

		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

		List<TagInfo> taglist = WeixinUtil.getTag(at.getAccessToken());
		respContent += "共" + taglist.size() + "个分组";
		for (TagInfo tag : taglist) {

			respContent += " \n\n分组 名称：" + tag.getName();
			respContent += "  \n分组 id： " + tag.getId();
			respContent += "  \n分组下人数：" + tag.getCount();

		}

		return respContent;
	}

	/**
	 * 根据分组获取人员列表 接口
	 * 
	 * @param tagidflag
	 * @return
	 */
	public String getUserList(String tagidflag) {

		String respContent = "";

		String flagstr = "";

		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
		UserbyTag userbytag = new UserbyTag();

		// 调用接口获取access_token

		if (tagidflag.equals("100")) {// 专家组
			flagstr = "专家组";
			userbytag.setTagid("100");
		} else if (tagidflag.equals("102")) {
			flagstr = "业余组";// 业余组
			userbytag.setTagid("102");
		} else if (tagidflag.equals("2")) {
			flagstr = "星际组";// 星际组
			userbytag.setTagid("2");
		} else {
			userbytag.setTagid(tagidflag);
		}

		List<UserOpenid> user_list = WeixinUtil.getUserbyTag(at.getAccessToken(), userbytag);
		if (user_list != null) {
			if (user_list.size() > 0) {
				UserOpenidList useropenidlist = new UserOpenidList();
				useropenidlist.setUser_list(user_list);
				List<WeiXinUserInfo> weixinuserinfolist = WeixinUtil.getUserDataList(at.getAccessToken(),
						useropenidlist);

				if (weixinuserinfolist != null) {
					respContent = flagstr + "信息获取成功！人数为：" + weixinuserinfolist.size();
				} else {
					respContent = flagstr + "信息获取失败";
				}
			} else {
				respContent = flagstr + "人员为0";
			}
		} else {
			respContent = flagstr + "openid获取失败";
		}

		return respContent;
	}

	/**
	 * 
	 * 给用户群发消息按标签 有次数限制 接口
	 * 
	 * @param tagidflag
	 *            标签
	 * @return
	 */
	public String returnMessge(String tagidflag, String content) {
		String respContent = "";

		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
		MessgeSend messgeSend = new MessgeSend();
		Messgefilter filter = new Messgefilter();
		filter.setIs_to_all(false);
		filter.setTag_id(Integer.parseInt(tagidflag));

		// 群发消息内容
		MessgeContent text = new MessgeContent();
		text.setContent(content);

		// 消息类型 文本
		messgeSend.setFilter(filter);
		messgeSend.setText(text);
		messgeSend.setMsgtype(MessageUtil.REQ_MESSAGE_TYPE_TEXT);

		respContent = WeixinUtil.messgeSend(at.getAccessToken(), messgeSend);
		return respContent;
	}

	/**
	 * 
	 * 给用户群发消息模板 接口
	 * 
	 * @param tagidflag
	 *            标签
	 * @return
	 */
	public String SendTemplateData(String tagidflag) {
		String respContent = "";

		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
		UserbyTag userbytag = new UserbyTag();

		/******** 模板消息群发 ，你可以根据需求给某个人员发 *******/
		// 获取便签下所有人员 //测试发的这个标签下的 只用拿到 Openid 就ok

		userbytag.setTagid(tagidflag);
		List<UserOpenid> user_list = WeixinUtil.getUserbyTag(at.getAccessToken(), userbytag);

		// 添加模板 获取模板id
		// String template_id=WeixinUtil.GetTemplateId(at.getAccessToken(),
		// "OPENTM206988051");
		String template_id = "R9lRNqzErULJ1FbF3d2QfPrzyxKvXECSmRew15jhtUM";
		// 循环给每一个人发消息
		for (UserOpenid useropenid : user_list) {

			MessgeTemplateSend messgeTemplateSend = new MessgeTemplateSend();

			messgeTemplateSend.setTouser(useropenid.getOpenid());

			MessgeTemplateData data = new MessgeTemplateData();
			data.setFirst(new MessgeData("您有一个会议需要参加"));
			data.setKeyword1(new MessgeData(" 标号：88888"));
			data.setKeyword2(new MessgeData(" 10万"));
			data.setKeyword3(new MessgeData(" 上海地铁"));
			data.setKeyword4(new MessgeData(" 18621522732"));
			data.setRemark(new MessgeData("参加请回复: 1     不参加请回复: 2"));

			messgeTemplateSend.setData(data);
			messgeTemplateSend.setTemplate_id(template_id);

			respContent = WeixinUtil.messgeTemplateSend(at.getAccessToken(), messgeTemplateSend);
		}
		return respContent;
	}

	/**
	 * 
	 * 给用户群发消息 用客服接口不限次数 接口
	 * 
	 * @param tagidflag
	 *            标签
	 * @return
	 */
	public String returnCustomMessge(String tagidflag, String content) {
		String respContent = "";

		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
		UserbyTag userbytag = new UserbyTag();

		/******** 我在这获取了一个标签下的所有人员依次给每个人发消息 ，你可以根据需求给某个人员发 *******/
		// 获取便签下所有人员
		userbytag.setTagid(tagidflag);
		List<UserOpenid> user_list = WeixinUtil.getUserbyTag(at.getAccessToken(), userbytag);

		// 循环给每一个人发消息
		for (UserOpenid useropenid : user_list) {
			MessgeCustomSend messgeCustomSend = new MessgeCustomSend();
			// 群发消息内容
			MessgeContent text = new MessgeContent();
			text.setContent(content);
			// 消息类型 文本 我群发的是文本消息
			messgeCustomSend.setTouser(useropenid.getOpenid());
			messgeCustomSend.setText(text);
			messgeCustomSend.setMsgtype(MessageUtil.REQ_MESSAGE_TYPE_TEXT);
			respContent = WeixinUtil.messgeCustomSend(at.getAccessToken(), messgeCustomSend);
		}
		return respContent;
	}

	// 给用户设置标签
	/**
	 * 给用户设置标签 接口
	 * 
	 * @param fromUserName
	 *            用户ID
	 * @param tagidflag
	 *            标签
	 * @return
	 */
	public String setTags(String fromUserName, String tagidflag) {

		String respContent = "";

		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

		if (null != at) {
			// 获取所有标签
			// WeixinUtil.getTag(at.getAccessToken());

			// 用户标签设置
			UserTags usertags = new UserTags();
			// 获取用户
			List<String> openidList = new ArrayList<String>();
			openidList.add(fromUserName);
			usertags.setOpenid_list(openidList);
			int result = 0;

			// 业余组102 专家组100
			if (tagidflag.equals("是")) {

				// 去掉业余组标签 用户有很多标签
				usertags.setTagid("102");
				WeixinUtil.usersTag(usertags, at.getAccessToken(), "取消");

				// 专家组
				usertags.setTagid("100");
				// 调用接口给用户更换标签
				result = WeixinUtil.usersTag(usertags, at.getAccessToken(), "添加");
			} else {
				// 去掉专家组标签
				usertags.setTagid("100");
				WeixinUtil.usersTag(usertags, at.getAccessToken(), "取消");

				// 业余组
				usertags.setTagid("102");
				// 调用接口给用户更换标签
				result = WeixinUtil.usersTag(usertags, at.getAccessToken(), "添加");
			}

			// 判断菜单创建结果
			if (0 == result) {
				System.out.println("用户标签更换成功");
				respContent = "操作成功";
			} else {
				System.out.println("用户标签更换失败");
				respContent = "操作失败";
			}

		}

		return respContent;
	}

	/**
	 * 创建菜单 接口 菜单创建（POST） 限100（次/天）
	 * 
	 * @param content
	 *            创建菜单的json数据 例子： 基础菜单： String content =
	 *            "{\"button\":[{\"name\":\"功能\",\"sub_button\":[{\"type\":\"view\",\"name\":\"订单号绑定\",\"url\":\"http:\\/\\/192.168.1.175:8080/weixinweixin/demo.html\"]}]},{\"name\":\"帮助\",\"sub_button\":[{\"type\":\"click\",\"name\":\"如何绑定\",\"key\":\"如何绑定\",\"sub_button\":[]}]}]}";
	 *
	 *            3个一级菜单，每个1级菜单下有5个二级菜单： String content =
	 *            "{\"button\":[{\"name\":\"本地服务\",\"sub_button\":[{\"type\":\"view\",\"name\":\"天气预报\",\"url\":\"http:\\/\\//\\/\\//www.baidu.com\"},{\"type\":\"view\",\"name\":\"微团购\",\"url\":\"http:\\/\\//\\/\\//www.baidu.com\"},{\"type\":\"view\",\"name\":\"喜帖贺卡\",\"url\":\"http:\\/\\//\\/\\//www.baidu.com\"},{\"type\":\"view\",\"name\":\"优惠活动\",\"url\":\"http:\\/\\/www.baidu.com\"},{\"type\":\"view\",\"name\":\"最新影讯\",\"url\":\"http:\\/\\/www.baidu.com\"}]},{\"name\":\"业务模块\",\"sub_button\":[{\"type\":\"view\",\"name\":\"产品动画\",\"url\":\"http:\\/\\/www.baidu.com\"},{\"type\":\"view\",\"name\":\"网络营销\",\"url\":\"http:\\/\\/www.baidu.com\"},{\"type\":\"view\",\"name\":\"400电话\",\"url\":\"http:\\/\\/www.baidu.com\"},{\"type\":\"view\",\"name\":\"网站制作\",\"url\":\"http:\\/\\/www.baidu.com\"},{\"type\":\"view\",\"name\":\"微信开发\",\"url\":\"http:\\/\\/www.baidu.com\"}]},{\"name\":\"爱博创网络\",\"sub_button\":[{\"type\":\"view\",\"name\":\"微网站\",\"url\":\"http:\\/\\/www.baidu.com\"},{\"type\":\"view\",\"name\":\"联系我们\",\"url\":\"http:\\/\\/www.baidu.com\"},{\"type\":\"view\",\"name\":\"微会员卡\",\"url\":\"http:\\/\\/www.baidu.com\"},{\"type\":\"view\",\"name\":\"游戏中心\",\"url\":\"http:\\/\\/www.baidu.com\"},{\"type\":\"view\",\"name\":\"每日签到\",\"url\":\"http:\\/\\/www.baidu.com\"}]}]}";
	 *
	 *            功能性菜单 例如：扫码，拍照，调用手机相册，发送位置： String content =
	 *            "{\"button\":[{\"name\":\"扫码\",\"sub_button\":[{\"type\":\"scancode_waitmsg\",\"name\":\"扫码带提示\",\"key\":\"rselfmenu_0_0\",\"sub_button\":[]},{\"type\":\"scancode_push\",\"name\":\"扫码推事件\",\"key\":\"rselfmenu_0_1\",\"sub_button\":[]}]},{\"name\":\"发图\",\"sub_button\":[{\"type\":\"pic_sysphoto\",\"name\":\"系统拍照发图\",\"key\":\"rselfmenu_1_0\",\"sub_button\":[]},{\"type\":\"pic_photo_or_album\",\"name\":\"拍照或者相册发图\",\"key\":\"rselfmenu_1_1\",\"sub_button\":[]},{\"type\":\"pic_weixin\",\"name\":\"微信相册发图\",\"key\":\"rselfmenu_1_2\",\"sub_button\":[]}]},{\"name\":\"发送位置\",\"type\":\"location_select\",\"key\":\"rselfmenu_2_0\"}]}";
	 * @return
	 */
	public String createMenu(String content) {
		String url = "";
		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

		if (null != at) {
			url = WeixinUtil.create_menu_url.replace("ACCESS_TOKEN", at.getAccessToken());
		}
		String result = "";
		try {
			JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", content);
			if (null != jsonObject) {
				if (0 != jsonObject.getInt("errcode")) {
					result = jsonObject.getString("errcode");
					// log.error("给用户标签失败 errcode:{} errmsg:{}",
					// jsonObject.getInt("errcode"),
					// jsonObject.getString("errmsg"));
				} else {
					result = "1";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "0";
			System.out.println(e.getMessage());

		}
		System.out.println(result);
		return result;
	}

	// 给用户设置标签
	/**
	 * 删除菜单 接口
	 * 
	 * @return 99：菜单删除成功，00：菜单删除失败,-1：其他错误
	 */
	public String deleteMenu() {
		String respContent = "";
		String url = "";
		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

		if (null != at) {
			url = WeixinUtil.delete_menu_url.replace("ACCESS_TOKEN", at.getAccessToken());
		}

		String result = "";
		Service service = new Service();
		Call call;
		Integer LIMIT = 60000;
		try {
			String content = "";
			JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", content);
			if (null != jsonObject) {
				if (0 != jsonObject.getInt("errcode")) {
					// result = jsonObject.getString("errcode");
					result = "00";
					// log.error("删除菜单失败 errcode:{} errmsg:{}",
					// jsonObject.getInt("errcode"),
					// jsonObject.getString("errmsg"));
				} else {
					result = "99";
				}
			}
			System.out.println(result);

		} catch (Exception e) {
			result = "-1";
			e.printStackTrace();
			System.out.println(e.getMessage());

		}
		return result;
	}

	/**
	 * 添加客服账号
	 */
	public String createKeFu(String content) {
		String url = "";
		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

		if (null != at) {
			url = WeixinUtil.create_KeFu_url.replace("ACCESS_TOKEN", at.getAccessToken());
		}
		String result = "";
		try {
			JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", content);
			if (null != jsonObject) {
				if (0 != jsonObject.getInt("errcode")) {
					result = jsonObject.getString("errcode");
					// log.error("给用户标签失败 errcode:{} errmsg:{}",
					// jsonObject.getInt("errcode"),
					// jsonObject.getString("errmsg"));
				} else {
					result = "1";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "0";
			System.out.println(e.getMessage());

		}
		System.out.println(result);
		return result;
	}

	/**
	 * 客服接口-发消息
	 */
	public String MessageKeFu(String content) {
		String url = "";
		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

		if (null != at) {
			// url = WeixinUtil.messge_templat_Send.replace("ACCESS_TOKEN",
			// at.getAccessToken());
			url = WeixinUtil.messge_custom_Send.replace("ACCESS_TOKEN", at.getAccessToken());
		}
		String result = "";
		try {
			JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", content);
			if (null != jsonObject) {
				if (0 != jsonObject.getInt("errcode")) {
					result = jsonObject.getString("errcode");
					// log.error("给用户标签失败 errcode:{} errmsg:{}",
					// jsonObject.getInt("errcode"),
					// jsonObject.getString("errmsg"));
				} else {
					result = "0";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "1";
			System.out.println(e.getMessage());
		}
		System.out.println("消息发送结果：" + result);
		return result;
	}

	/**
	 * 模板接口-发消息
	 */
	public String templateMessage(String content) {
		String url = "";
		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

		if (null != at) {
			url = WeixinUtil.messge_templat_Send.replace("ACCESS_TOKEN", at.getAccessToken());
		}
		String result = "";
		try {
			JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", content);
			if (null != jsonObject) {
				if (0 != jsonObject.getInt("errcode")) {
					result = jsonObject.getString("errcode");
					// log.error("给用户标签失败 errcode:{} errmsg:{}",
					// jsonObject.getInt("errcode"),
					// jsonObject.getString("errmsg"));
				} else {
					result = "0";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "1";
			System.out.println(e.getMessage());
		}
		System.out.println("消息发送结果：" + result);
		return result;
	}

	/**
	 * 获取openId
	 */
	public String getOpenId(String code) {
		String url = "";
		// 调用接口获取access_token
		// AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
		url = WeixinUtil.get_OpenId.replace("APPID", appId);
		url = url.replace("CODE", code);
		url = url.replace("SECRET", appSecret);
		String result = "";
		try {
			JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", "");
			if (null != jsonObject) {
				if (null == jsonObject.get("errcode")) {
					// 这是调用成功
					result = jsonObject.getString("openid");
					// result = "1";
				} else {
					// 这是调用失败
					result = "0";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "0";
			System.out.println(e.getMessage());
		}
		System.out.println("消息发送结果：" + result);
		return result;
	}

	/**
	 * 2个日期进行对比的方法
	 * 
	 * @param newDate
	 *            当前时间
	 * @param dateTime
	 *            需要进行对比的时间
	 * @return false：当前时间大于进行对比的时间 true：当前时间小于对比的时间
	 */
	public static boolean compare_date(String newDate, String dateTime) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		boolean flag = true;
		try {
			Date dt1 = df.parse(newDate);
			Date dt2 = df.parse(dateTime);
			if (dt1.getTime() >= dt2.getTime()) {
				System.out.println("dt1 在dt2前");
				flag = false;
			} else if (dt1.getTime() < dt2.getTime()) {
				System.out.println("dt1在dt2后");
				flag = true;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return flag;
	}

	// 模拟
	public static List getIsNotMessage(String openId) {
		List list = new ArrayList();
		Map mm = new HashMap();
		mm.put("status", "0");
		mm.put("time", "2017-09-15 09:00:00");
		list.add(mm);
		return list;
	}
}
