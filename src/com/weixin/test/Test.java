package com.weixin.test;

import com.weixin.wxpojo.AccessToken;
import com.weixin.wxutil.WeixinUtil;

import net.sf.json.JSONObject;


public class Test {
	
    private static final String APPID = "wxa88a68274c29258f";
    
    private static final String APPSECRET = "428733e61223ba49eb050caffc0d24c7";
    
	public static void main(String[] args) {
		
		
		//通过APPID、APPSECRET获取微信的AccessToken
		AccessToken token = WeixinUtil.getAccessToken(APPID, APPSECRET);
		System.out.println(token.getAccessToken());
		System.out.println(token.getExpiresIn());
		//把menu菜单对象转化成JSON对象
		JSONObject menu = JSONObject.fromObject(WeixinUtil.initMenu());
		
		System.out.println(menu.toString());
		
		//远程调用微信POST接口，并执行传入参数
		JSONObject jsonObject = WeixinUtil.httpRequest("https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+token.getAccessToken(),"POST",menu.toString());
		
		int result = -1;
		if(jsonObject !=null ){
			result = jsonObject.getInt("errcode");
		}
		System.out.println(result);
	}
	
	/*public static int createMenu(String token,String menu) throws Exception {

			int result = 0;
		
			String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
		
			
			JSONObject jsonObject = doPostStr(url, menu);
		
			if(jsonObject != null){
		
			result = jsonObject.getInt("errcode");
		
			}
		
			return result;

	　　　}*/

}
