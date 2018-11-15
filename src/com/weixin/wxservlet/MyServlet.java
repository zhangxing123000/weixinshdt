package com.weixin.wxservlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.weixin.http.HttpRequestUtil;
import com.weixin.user.WeiXinUserGet;
import com.weixin.user.WeiXinUserInfo;
import com.weixin.user.WeiXinUserList;

public class MyServlet extends HttpServlet {
	/**
		 * 
		 */
	private static final long serialVersionUID = 3L;
	public MyServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		// super.doGet(req, resp);
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doPost(req, resp);
		//处理微信服务器发来的消息
		resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        
		System.out.print("servlet MyServlet start :");
		 
		WeiXinUserList weixinUserList = null;  
	    Map<String,String> map = new TreeMap<String,String>();  
//	    map.put("grant_type", "client_credential");    
//	    map.put("appid", "wxd8209608ede65103");    
//	    map.put("secret", "e1e5bd415e6fa891b036ecde583d11f7"); 
	    
	    
	     
	    map.put("grant_type", "client_credential");    
	    map.put("appid", "wxd426078cc6b4e46f");    
	    map.put("secret", "1dfdcf7c1f280d9042fb2516baad57cb"); 
	    
	    String result2 = HttpRequestUtil.HttpsDefaultExecute(HttpRequestUtil.GET_METHOD, "https://api.weixin.qq.com/cgi-bin/token", map, "");
	    String access_token="";
	    if(result2.split(",")[0].indexOf("\"access_token\"")>=0){
	    access_token=result2.split(",")[0].substring(16).replace("\"", "");
	    access_token.trim();
		
	    }
  
	    WeiXinUserGet wxuser=new WeiXinUserGet();
	    List<String> userListtest= new ArrayList();
	    List<String> userList= wxuser.findWeiXinUserList(userListtest, access_token, "");
	    userList.size();
	    
	    for(int i=0;i<userList.size();i++){
	    	 weixinUserList = null;  
		    Map<String,String> map1 = new TreeMap<String,String>();  
		    map1.put("access_token", access_token);    
		    map1.put("openid", userList.get(i));
		    String result1 = HttpRequestUtil.HttpsDefaultExecute(HttpRequestUtil.GET_METHOD, "https://api.weixin.qq.com/cgi-bin/user/info", map1, "");  
		    result1.trim();
		    
		    
		    
		    //System.out.print(result1+"||");
		    System.out.print(result1);
		    System.out.print(result1+"\n"); //换行 显示人员信息。
	    }
	    
//	    // 获取用户信息     JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);     if (null != jsonObject) {       try {         weixinUserInfo = new WeixinUserInfo();         // 用户的标识         weixinUserInfo.setOpenId(jsonObject.getString("openid"));         // 关注状态（1是关注，0是未关注），未关注时获取不到其余信息         weixinUserInfo.setSubscribe(jsonObject.getInt("subscribe"));         // 用户关注时间         weixinUserInfo.setSubscribeTime(jsonObject.getString("subscribe_time"));         // 昵称         weixinUserInfo.setNickname(jsonObject.getString("nickname"));         // 用户的性别（1是男性，2是女性，0是未知）         weixinUserInfo.setSex(jsonObject.getInt("sex"));         // 用户所在国家         weixinUserInfo.setCountry(jsonObject.getString("country"));         // 用户所在省份         weixinUserInfo.setProvince(jsonObject.getString("province"));         // 用户所在城市         weixinUserInfo.setCity(jsonObject.getString("city"));         // 用户的语言，简体中文为zh_CN         weixinUserInfo.setLanguage(jsonObject.getString("language"));         // 用户头像         weixinUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));       } catch (Exception e) {         if (0 == weixinUserInfo.getSubscribe()) {           log.error("用户{}已取消关注", weixinUserInfo.getOpenId());         } else {           int errorCode = jsonObject.getInt("errcode");           String errorMsg = jsonObject.getString("errmsg");           log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);         }       }     }     return weixinUserInfo; 
//	    for(int i=0;i<userList.size();i++){
//		  
//		    WeiXinUserInfo mwx=   getUserInfo(access_token,userList.get(i));
//		    //System.out.print(result1+"||");
//		    System.out.print("微信openid："+mwx.getOpenid());
//		    System.out.print("微信号："+mwx.getNickname());
//		    System.out.print("微信标签id："+mwx.getTagid_list());
//		    System.out.print("关注事件："+mwx.getSubscribe_time());
//	    }
	    
	}
	
	/**
	 * * 获取用户信息 * * @param accessToken 接口访问凭证 * @param openId 用户标识 * @return
	 * WeixinUserInfo 问题。
	 */
	public static WeiXinUserInfo getUserInfo(String accessToken, String openId) {
		WeiXinUserInfo weixinUserInfo = null; // 客户类 准备接受来自网页请求字符流的 客户信息。
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId); // 获取用户信息
		//JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		WeiXinUserInfo jsonObject  = new Gson().fromJson(requestUrl, WeiXinUserInfo.class);  
		if (null != jsonObject) {
			
			try {
				weixinUserInfo = new WeiXinUserInfo(); // 用户的标识
				weixinUserInfo.setOpenid(jsonObject.getOpenid()); // 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
//				weixinUserInfo.setSubscribe(jsonObject.getInt("subscribe")); // 用户关注时间
//				weixinUserInfo.setSubscribeTime(jsonObject.getString("subscribe_time")); // 昵称
//				weixinUserInfo.setNickname(jsonObject.getString("nickname")); // 用户的性别（1是男性，2是女性，0是未知）
//				weixinUserInfo.setSex(jsonObject.getInt("sex")); // 用户所在国家
//				weixinUserInfo.setCountry(jsonObject.getString("country")); // 用户所在省份
//				weixinUserInfo.setProvince(jsonObject.getString("province")); // 用户所在城市
//				weixinUserInfo.setCity(jsonObject.getString("city")); // 用户的语言，简体中文为zh_CN
//				weixinUserInfo.setLanguage(jsonObject.getString("language")); // 用户头像
//				weixinUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
			} catch (Exception e) {
				System.out.print(e.toString()+" ");
			}
		}
		return weixinUserInfo;
	}

	


}
