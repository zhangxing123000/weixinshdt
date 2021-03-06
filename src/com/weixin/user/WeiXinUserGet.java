package com.weixin.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.weixin.http.HttpRequestUtil;


public class WeiXinUserGet {
	private final static String WECHAT_USER_GET_URL = "https://api.weixin.qq.com/cgi-bin/user/get"; //?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID  
	  
	public List<String> findWeiXinUserList(List<String> openidList, String accessToken, String nextOpenid){   
	    WeiXinUserList weixinUserList = null;  
	    Map<String,String> map = new TreeMap<String,String>();  
	    map.put("access_token", accessToken);  
	    if(nextOpenid.trim().length()>0){  
	        map.put("next_openid", nextOpenid);  
	    }  
	    String result = HttpRequestUtil.HttpsDefaultExecute(HttpRequestUtil.GET_METHOD, WECHAT_USER_GET_URL, map, "");  
	    if(result != null){  
	        try {  
	            weixinUserList = new Gson().fromJson(result, WeiXinUserList.class);  
	            openidList = new ArrayList<String>();  
	            if(weixinUserList.getCount() <= 10000 && weixinUserList.getCount() >0){  
	                openidList.addAll(weixinUserList.getData().getOpenid());  
	            }else if(weixinUserList.getCount()!=0){  
	                //如果大于10000的,继续查询  
	                findWeiXinUserList(openidList, accessToken, weixinUserList.getNext_openid());  
	            }     
	        } catch (JsonSyntaxException e) {  
	            openidList = null;  
	        }  
	    }  
	    return openidList;  
	}  
}
