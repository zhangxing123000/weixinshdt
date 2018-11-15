package com.weixin.wxutil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.weixin.http.MyX509TrustManager;
import com.weixin.menu.Button;
import com.weixin.menu.ClickButton;
import com.weixin.menu.Menu;
import com.weixin.menu.ViewButton;
import com.weixin.user.MessgeCustomSend;
import com.weixin.user.MessgeSend;
import com.weixin.user.MessgeTemplateSend;
import com.weixin.user.TagInfo;
import com.weixin.user.UserOpenid;
import com.weixin.user.UserOpenidList;
import com.weixin.user.UserTags;
import com.weixin.user.UserbyTag;
import com.weixin.user.WeiXinUserInfo;
import com.weixin.wxpojo.AccessToken;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class WeixinUtil {
	
	
    private static final String APPID = "wxa88a68274c29258f";
    
    private static final String APPSECRET = "428733e61223ba49eb050caffc0d24c7";

	// 获取access_token的接口地址（GET） 限200（次/天）
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	

	// 标签功能目前支持公众号为用户打上最多20个标签。
	public static String user_tags_url = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=ACCESS_TOKEN";

	// 批量为用户取消标签
	public static String user_deltags_url = "https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token=ACCESS_TOKEN";

	// 获取公众号已创建的标签 http请求方式：GET（请使用https协议）
	public static String get_tags_url = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token=ACCESS_TOKEN";

	// 获取标签下粉丝列表
	public static String get_usersbytag_url = "https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token=ACCESS_TOKEN";

	// 批量获取用户基本信息
	public static String get_userslist_url = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";

	// 根据标签进行群发
	public static String messge_Send = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";

	// 客服接口-发消息接口
	public static String messge_custom_Send = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	
	//根据CODE获取openId接口
	public static String get_OpenId = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	//自定义菜单创建接口
	public static String create_menu_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	//删除菜单接口
	public static String delete_menu_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	
	//添加客服账号 客服创建接口
	public static String create_KeFu_url = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=ACCESS_TOKEN";
	
	// 获得模板ID   模板库中模板的编号 从公众平台 后台获取
	public static String template_id= "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN";

	// 发送模板消息
	public static String messge_templat_Send= "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	
	/**
	 * 获取模板ID
	 * @param accessToken
	 * @param template_id_short
	 * @return
	 */
	public static String GetTemplateId(String accessToken,String template_id_short) {
		String requestUrl = template_id.replace("ACCESS_TOKEN", accessToken);
		String jsonmessgeSend = "{\"template_id_short\":\""+template_id_short+"\"}";
		System.out.println("获得模板ID" + jsonmessgeSend + "");

		JSONObject jsonObject = httpRequest(requestUrl, "POST", jsonmessgeSend);
		
		
		String template_id=null;
		if (jsonObject != null) {

			try {
				if (jsonObject.getString("errcode").equals("0")) {
					template_id= jsonObject.getString("template_id");
				}else{
					System.out.println("获得模板ID{" + jsonObject.toString()+ "}");
				}

			} catch (Exception ex) {
				// TODO: handle exception
				System.out.println("群发消息失败 errcode:{" + jsonObject.getString("errcode") + "} errmsg:{"
						+ jsonObject.getString("errmsg") + "}");
			
				return null;
			}
		}
		return template_id;
	}
	

	/**
	 * 用模板给用户发送消息
	 * @param accessToken
	 * @param messgetemplatesend
	 * @return
	 */
	public static String messgeTemplateSend(String accessToken, MessgeTemplateSend messgetemplatesend) {

		String requestUrl = messge_templat_Send.replace("ACCESS_TOKEN", accessToken);
		String jsonmessgeSend = JSONObject.fromObject(messgetemplatesend).toString();
		System.out.println("群发消息成poset 数据" + jsonmessgeSend + "");

		String errmsg = "";
		JSONObject jsonObject = httpRequest(requestUrl, "POST", jsonmessgeSend);
		System.out.println("群发消息jsonObject" + jsonObject + "}");
		if (jsonObject != null) {

			try {
				if (jsonObject.getString("errcode").equals("0")) {
					System.out.println("群发消息成功 errcode:{" + jsonObject.getString("errcode") + "}");
				}
			} catch (Exception ex) {
				// TODO: handle exception
				System.out.println("群发消息失败 errcode:{" + jsonObject.getString("errcode") + "} errmsg:{"
						+ jsonObject.getString("errmsg") + "}");
				errmsg = "发送失败";
				return errmsg;
			}
		}
		return errmsg;

	}
	
	
	
	
	/**
	 *  根据tag标签 消息发送
	 * @param accessToken
	 * @param messgeSend
	 * @return
	 */
	public static String messgeSend(String accessToken, MessgeSend messgeSend) {

		String requestUrl = messge_Send.replace("ACCESS_TOKEN", accessToken);
		String jsonmessgeSend = JSONObject.fromObject(messgeSend).toString();
		System.out.println("群发消息成poset 数据" + jsonmessgeSend + "");

		String errmsg = "";
		JSONObject jsonObject = httpRequest(requestUrl, "POST", jsonmessgeSend);
		System.out.println("群发消息jsonObject" + jsonObject + "}");
		if (jsonObject != null) {

			try {
				if (jsonObject.getString("errcode").equals("0")) {
					System.out.println("群发消息成功 errcode:{" + jsonObject.getString("errcode") + "}");
				}
				switch (Integer.parseInt(jsonObject.getString("errcode"))) {
				case 0:
					errmsg = "发送成功	";
					break;
				case 45065:
					errmsg = "发送失败，发此消息之前已经群发不可以再次发送";
					break;
				case 45066:
					errmsg = "发送失败，发此消息重试速度过快，请间隔1分钟重试	";
					break;
				case 45067:
					errmsg = "发送失败， 长度超过限制";
					break;
				default:
					break;
				}

			} catch (Exception ex) {
				// TODO: handle exception
				System.out.println("群发消息失败 errcode:{" + jsonObject.getString("errcode") + "} errmsg:{"
						+ jsonObject.getString("errmsg") + "}");
				errmsg = "发送失败";
				return errmsg;
			}
		}
		return errmsg;

	}
	
	/**
	 *  给某个人发送 消息
	 * @param accessToken
	 * @param messgecustomSend 
	 * @return
	 */
	public static String messgeCustomSend(String accessToken, MessgeCustomSend messgecustomSend) {

		String requestUrl = messge_custom_Send.replace("ACCESS_TOKEN", accessToken);
		String jsonmessgeSend = JSONObject.fromObject(messgecustomSend).toString();
		System.out.println("群发消息成poset 数据" + jsonmessgeSend + "");

		String errmsg = "";
		JSONObject jsonObject = httpRequest(requestUrl, "POST", jsonmessgeSend);
		System.out.println("群发消息jsonObject" + jsonObject + "}");
		if (jsonObject != null) {

			try {
				if (jsonObject.getString("errcode").equals("0")) {
					System.out.println("群发消息成功 errcode:{" + jsonObject.getString("errcode") + "}");
				}
				switch (Integer.parseInt(jsonObject.getString("errcode"))) {
				case 0:
					errmsg = "发送成功	";
					break;
				case 45065:
					errmsg = "发送失败，发此消息之前已经群发不可以再次发送";
					break;
				case 45066:
					errmsg = "发送失败，发此消息重试速度过快，请间隔1分钟重试	";
					break;
				case 45067:
					errmsg = "发送失败， 长度超过限制";
					break;
				default:
					break;
				}

			} catch (Exception ex) {
				// TODO: handle exception
				System.out.println("群发消息失败 errcode:{" + jsonObject.getString("errcode") + "} errmsg:{"
						+ jsonObject.getString("errmsg") + "}");
				errmsg = "发送失败";
				return errmsg;
			}
		}
		return errmsg;

	}

	/**
	 * 获取所有标签
	 * 
	 * @param String
	 *            accessToken 调用接口凭据
	 * @return result
	 */
	public static List<TagInfo> getTag(String accessToken) {
		String requestUrl = get_tags_url.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		List<TagInfo> taglist = null;
		// 如果请求成功
		if (null != jsonObject) {
			try {

				taglist = new ArrayList();
				JSONArray jsonarray = jsonObject.getJSONArray("tags");
				for (int i = 0; i < jsonarray.size(); i++) {
					JSONObject json = jsonarray.getJSONObject(i);
					TagInfo tag = new TagInfo();
					tag.setCount(json.getString("count"));
					tag.setName(json.getString("name"));
					tag.setId(json.getString("id"));
					taglist.add(tag);
					System.out.println("标签名称 ：" + tag.getName());
					System.out.println("       标签 id ：" + tag.getId());
					System.out.println("       标签下人数 ：" + tag.getCount());
				}
				System.out.println("所有标签" + jsonObject.getString("tags"));

			} catch (JSONException e) {
				accessToken = null;
				// 获取token失败
				System.out.println("标签更换失败 errcode:{" + jsonObject.getInt("errcode") + "} errmsg:{"
						+ jsonObject.getString("errmsg") + "}");
			}
		}
		return taglist;
	}

	/**
	 * 
	 * @param accessToken
	 * @param UserbyTag
	 */
	public static List<UserOpenid> getUserbyTag(String accessToken, UserbyTag UserbyTag) {
		String requestUrl = get_usersbytag_url.replace("ACCESS_TOKEN", accessToken);

		// 将标签下获取粉丝对象转换成json字符串
		String jsonUserbyTag = JSONObject.fromObject(UserbyTag).toString();
		System.out.println("获取标签下的用户" + jsonUserbyTag);
		List<UserOpenid> user_list = null;
		JSONObject jsonObject = httpRequest(requestUrl, "GET", jsonUserbyTag);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				// 获取用户openID
				user_list = new ArrayList();
				if (jsonObject.getInt("count") > 0) {
					System.out.println("这次获取的粉丝数量" + jsonObject.getString("count"));
					System.out.println("拉取列表最后一个用户的openid" + jsonObject.getString("next_openid"));
					JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("openid");
					for (int i = 0; i < jsonArray.size(); i++) {
						UserOpenid userOpenid = new UserOpenid();
						String openid = jsonArray.getString(i);
						userOpenid.setOpenid(openid);
						user_list.add(userOpenid);
					}

					// 调取批量获取
				} else {
					System.out.println("这次获取的粉丝数量" + jsonObject.getString("count"));
				}

			} catch (JSONException e) {
				accessToken = null;
				// 获取token失败
				System.out.println("标签更换失败 errcode:{" + jsonObject.getInt("errcode") + "} errmsg:{"
						+ jsonObject.getString("errmsg") + "}");
			}
		}
		return user_list;
	}

	/**
	 * 
	 * @param accessToken
	 * @param UserbyTag
	 */
	public static List<WeiXinUserInfo> getUserDataList(String accessToken, UserOpenidList useropenidlist) {
		String requestUrl = get_userslist_url.replace("ACCESS_TOKEN", accessToken);

		// 将标签下获取粉丝对象转换成json字符串
		String jsonUserbyTag = JSONObject.fromObject(useropenidlist).toString();
		System.out.println("批量获取用户信息" + jsonUserbyTag);

		JSONObject jsonObject = httpRequest(requestUrl, "POST", jsonUserbyTag);
		List<WeiXinUserInfo> weixinuserinfolist = null;
		// 如果请求成功
		if (null != jsonObject) {
			try {

				// 获取用户信息
				weixinuserinfolist = new ArrayList();
				JSONArray jsonArray = jsonObject.getJSONArray("user_info_list");
				// weixinuserinfolist.sort();
				for (int i = 0; i < jsonArray.size(); i++) {
					// 循环获取每个成员的json
					jsonObject = jsonArray.getJSONObject(i);
					// 初始化人员信息类
					WeiXinUserInfo weixinuserinfo = new WeiXinUserInfo();
					weixinuserinfo.setCity(jsonObject.getString("city"));

					weixinuserinfo.setCountry(jsonObject.getString("country"));
					weixinuserinfo.setGroupid(jsonObject.getInt("groupid"));
					weixinuserinfo.setHeadimgurl(jsonObject.getString("headimgurl"));
					weixinuserinfo.setLanguage(jsonObject.getString("language"));
					weixinuserinfo.setNickname(jsonObject.getString("nickname"));
					weixinuserinfo.setOpenid(jsonObject.getString("openid"));
					weixinuserinfo.setProvince(jsonObject.getString("province"));
					weixinuserinfo.setRemark(jsonObject.getString("remark"));
					weixinuserinfo.setSex(jsonObject.getInt("sex"));
					weixinuserinfo.setSubscribe(jsonObject.getInt("subscribe"));
					weixinuserinfo.setSubscribe_time(jsonObject.getString("subscribe_time"));
					weixinuserinfo.setTagid_list(jsonObject.getString("tagid_list"));
					weixinuserinfo.setUnionid(jsonObject.getString("unionid"));
					//
					weixinuserinfolist.add(weixinuserinfo);
					System.out.println("用户信息获" + weixinuserinfo.getOpenid());

				}

				// 展示人员信息
				for (WeiXinUserInfo wuser : weixinuserinfolist) {

					System.out.println("分组id名称:" + wuser.getTagid_list());
					// 重新获取一下就可以了
					System.out.println("分组名称 :" + wuser.getTagid_list());
					System.out.println("        人员名称 :" + wuser.getNickname());
					System.out.println("        城市 :" + wuser.getCity());
				}

			} catch (JSONException e) {
				accessToken = null;
				// 获取token失败
				System.out.println("用户信息获取失败 errcode:{" + jsonObject.getInt("errcode") + "} errmsg:{"
						+ jsonObject.getString("errmsg") + "}");
				return weixinuserinfolist;
			}
		}
		return weixinuserinfolist;
	}

	/**
	 * 给用户更换标签
	 * 
	 * @param mebgroup
	 *            用户标签实例
	 * @param accessToken
	 *            有效的access_token
	 * @return 0表示成功，其他值表示失败
	 */
	public static int usersTag(UserTags mebtag, String accessToken, String flag) {

		int result = 0;
		// 拼装更换标签

		String url = user_tags_url.replace("ACCESS_TOKEN", accessToken);
		if (flag == "取消") {
			// 删除标签
			url = user_deltags_url.replace("ACCESS_TOKEN", accessToken);
		} else {
			// 添加标签
			url = user_tags_url.replace("ACCESS_TOKEN", accessToken);
		}
		// 将菜单对象转换成json字符串
		String jsonmebgroup = JSONObject.fromObject(mebtag).toString();
		System.out.println("给用户设置标签" + jsonmebgroup);
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", jsonmebgroup);
		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				// log.error("给用户标签失败 errcode:{} errmsg:{}",
				// jsonObject.getInt("errcode"),
				// jsonObject.getString("errmsg"));
			}
		}

		return result;
	}

	/**
	 * 获取access_token
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 */
	public static AccessToken getAccessToken(String appid, String appsecret) {

		AccessToken accessToken = null;

		String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setAccessToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));

				System.out.println("access_token   :\n" + accessToken.getAccessToken());
			} catch (JSONException e) {
				accessToken = null;
				// 获取token失败
				// log.error("获取token失败 errcode:{} errmsg:{}",
				// jsonObject.getInt("errcode"),
				// jsonObject.getString("errmsg"));
			}
		}
		return accessToken;
	}

	/**
	 * 描述: 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);

			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			// log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			// log.error("https request error:{}", e);
		}
		return jsonObject;
	}
	
	
	/**

	 * 组装菜单

	 * @return

	 */

	public static Menu initMenu(){

	Menu menu = new Menu();

	ViewButton button11 = new ViewButton();

	button11.setName("互联网+");

	button11.setType("view");

	button11.setUrl("http://tctzmj.natappfree.cc/emp/emp/login");

	 

	ViewButton button12 = new ViewButton();

	button12.setName("服务之窗");

	button12.setType("view");

	button12.setUrl("http://tctzmj.natappfree.cc/emp/emp/login");
	 

	ViewButton button13 = new ViewButton();

	button13.setName("暖心行动");

	button13.setType("view");

	button13.setUrl("http://tctzmj.natappfree.cc/emp/emp/login");
	
	ViewButton button14 = new ViewButton();

	button14.setName("工会工作");

	button14.setType("view");

	button14.setUrl("http://tctzmj.natappfree.cc/emp/emp/login");
	
	
	ViewButton button21 = new ViewButton();

	button21.setName("签到");

	button21.setType("view");

	button21.setUrl("http://tctzmj.natappfree.cc/emp/emp/login");

	 

	ViewButton button22 = new ViewButton();

	button22.setName("抽奖");

	button22.setType("view");

	button22.setUrl("http://tctzmj.natappfree.cc/emp/emp/login");
	
	ViewButton button23 = new ViewButton();

	button23.setName("积分兑换");

	button23.setType("view");

	button23.setUrl("http://tctzmj.natappfree.cc/emp/emp/login");

	 

	ViewButton button31 = new ViewButton();

	button31.setName("我的活动");

	button31.setType("view");

	button31.setUrl("http://tctzmj.natappfree.cc/emp/emp/login");
	
	 

	ViewButton button32 = new ViewButton();

	button32.setName("我的积分");

	button32.setType("view");

	button32.setUrl("http://tctzmj.natappfree.cc/emp/emp/login");

	ViewButton button33 = new ViewButton();

	button33.setName("我的订单");

	button33.setType("view");

	button33.setUrl("http://tctzmj.natappfree.cc/emp/emp/login");
	
	 

	ViewButton button34 = new ViewButton();

	button34.setName("我的收藏");

	button34.setType("view");

	button34.setUrl("http://tctzmj.natappfree.cc/emp/emp/login");
	
	
	ViewButton button35 = new ViewButton();

	button35.setName("我的点赞");

	button35.setType("view");

	button35.setUrl("http://www.baidu.com");

	 
	 

	Button button1 = new Button();

	button1.setName("互联网+"); //将11/12两个button作为二级菜单封装第一个一级菜单

	button1.setSub_button(new Button[]{button11});

	 

	Button button2 = new Button();

	button2.setName("历史文章"); //将21/22两个button作为二级菜单封装第二个二级菜单

	button2.setSub_button(new Button[]{button31});


	Button button3 = new Button();

	button3.setName("工会介绍"); //将21/22两个button作为二级菜单封装第二个二级菜单

	button3.setSub_button(new Button[]{button31});
	 

	menu.setButton(new Button[]{button11,button2,button3});// 将31Button直接作为一级菜单

	return menu;

	}
	
	//从微信后台拿到APPID和APPSECRET 并封装为常量
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
    String urls = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="; 
    
    
}
