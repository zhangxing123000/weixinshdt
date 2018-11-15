package com.weixin.test;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.catalina.util.URLEncoder;

public class Demo2 {
	public static String appId = "wxd426078cc6b4e46f";
	public static String appSecret = "1dfdcf7c1f280d9042fb2516baad57cb";
	// public static String content =
	// "{\"button\":[{\"name\":\"功能\",\"sub_button\":"
	// +
	// "[{\"type\":\"view\",\"name\":\"订单号绑定\",\"url\":\"http://192.168.1.175:8080/weixinshdt/demo.html\"]}]},"
	// + "{\"name\":\"帮助\",\"sub_button\":[{\"type\":\"click\","
	// + "\"name\":\"如何绑定\",\"key\":\"如何绑定\",\"sub_button\":[]}]}]}";

	// 3个一级菜单，每个1级菜单下有5个二级菜单
//	public static String content = "{\"button\":[{\"name\":\"本地服务\",\"sub_button\":[{\"type\":\"view\",\"name\":\"天气预报\",\"url\":\"http://////www.baidu.com\"},{\"type\":\"view\",\"name\":\"微团购\",\"url\":\"http://////www.baidu.com\"},{\"type\":\"view\",\"name\":\"喜帖贺卡\",\"url\":\"http://////www.baidu.com\"},{\"type\":\"view\",\"name\":\"优惠活动\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"最新影讯\",\"url\":\"http://www.baidu.com\"}]},{\"name\":\"业务模块\",\"sub_button\":[{\"type\":\"view\",\"name\":\"产品动画\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"网络营销\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"400电话\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"网站制作\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"微信开发\",\"url\":\"http://www.baidu.com\"}]},{\"name\":\"爱博创网络\",\"sub_button\":[{\"type\":\"view\",\"name\":\"微网站\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"联系我们\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"微会员卡\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"游戏中心\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"每日签到\",\"url\":\"http://www.baidu.com\"}]}]}";
	//http://majiabao.gnway.org/weixinshdt/ViewServlet?action=viewtest&response_type=code&scope=snsapi_base&state=1#wechat_redirect
	//菜单
	//https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appId+
	//"&redirect_uri="+i+"&response_type=code&scope=snsapi_base#wechat_redirect
//	public static String content = "{\"button\":[{\"name\":\"菜单1\",\"sub_button\":[{\"type\":\"view\",\"name\":\"平台首页\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"评标邀请\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"定向推送\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"首页\",\"url\":\"http://www.baidu.com\"}]},{\"name\":\"菜单2\",\"sub_button\":[{\"type\":\"view\",\"name\":\"公告\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"采购组织\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"供应商\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"产品\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"搜索\",\"url\":\"http://www.baidu.com\"}]},{\"name\":\"菜单3\",\"sub_button\":[{\"type\":\"view\",\"name\":\"在线客服\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"收件箱\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"账号绑定\",\"url\":\"http://10.35.31.24:9090/mscpmh/PF/BS/index.html\"},{\"type\":\"view\",\"name\":\"注册\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"我的\",\"url\":\"http://www.baidu.com\"}]}]}";
	public static String content = "{\"button\":[{\"name\":\"菜单1\",\"sub_button\":[{\"type\":\"view\",\"name\":\"平台首页\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"评标邀请\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"定向推送\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"首页\",\"url\":\"http://www.baidu.com\"}]},{\"name\":\"菜单2\",\"sub_button\":[{\"type\":\"view\",\"name\":\"公告\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"采购组织\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"供应商\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"产品\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"搜索\",\"url\":\"http://www.baidu.com\"}]},{\"name\":\"菜单3\",\"sub_button\":[{\"type\":\"view\",\"name\":\"在线客服\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"收件箱\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":"
			+ "\"账号绑定2\",\"url\":\"http://liushaolin.gnway.org/weixinshdt/CoreServlet\"},{\"type\":\"view\",\"name\":\"注册\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"我的\",\"url\":\"http://www.baidu.com\"}]}]}";
	public static void main(String[] args) {
		try {
			URLEncoder ue = new URLEncoder();
			String i = ue.encode("http://liushaolin.gnway.org/weixinshdt/ViewServlet?action=viewtest");
			String uu = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appId+
					"&redirect_uri="+i+"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
			String content = "{\"button\":[{\"name\":\"菜单1\",\"sub_button\":[{\"type\":\"view\",\"name\":\"平台首页\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"评标邀请\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"定向推送\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"首页\",\"url\":\"http://www.baidu.com\"}]},{\"name\":\"菜单2\",\"sub_button\":[{\"type\":\"view\",\"name\":\"公告\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"采购组织\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"供应商\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"产品\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"搜索\",\"url\":\"http://www.baidu.com\"}]},{\"name\":\"菜单3\",\"sub_button\":[{\"type\":\"view\",\"name\":\"在线客服\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"收件箱\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":"
					+ "\"账号绑定\",\"url\":\""+uu+"\"},{\"type\":\"view\",\"name\":\"注册\",\"url\":\"http://www.baidu.com\"},{\"type\":\"view\",\"name\":\"我的\",\"url\":\"http://www.baidu.com\"}]}]}";
//			MD5Test md = new MD5Test();
//			String password = md.getMD5("frontpage0828");
			String urlname = "http://localhost:8080/weixinshdt/CoreService?wsdl";
//			String content = "{\"kf_account\":\"hx346538728\",\"nickname\":\"客服1\",\"password\":\"123456\"}";
			// urlname =
			// "http://10.35.31.60:8080/weixinshdt/wxservices/CoreService";

			// 直接引用远程的wsdl文件
			// 以下都是套路
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(urlname));
			/**
			 * 注意这里，要设置Namespace xsd:schema:namespace
			 */
			call.setOperationName(new QName("http://wxservice.shdt.com/", "createMenu"));// WSDL里面描述的接口名称
			call.addParameter("content", XMLType.XSD_STRING, ParameterMode.IN);// 接口的参数
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
			String result = (String) call.invoke(new Object[] { content });
			// 给方法传递参数，并且调用方法
			System.out.println("result is :" + result);

			// Object[] fn02 = { "john", "john2", null, null };
			//
			// String va2 = (String) call.invoke(fn02);
			//
			// System.out.println("getSecurityToken(wrong):" + va2);

		} catch (Exception e) {

			// java.io.InterruptedIOException: Read timed out

			System.out.println(e.getMessage());

		}
	}
}
