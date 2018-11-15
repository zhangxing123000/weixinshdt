package com.weixin.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weixin.wxservice.CoreService;

public class Demo extends HttpServlet{
//	private final static String URL = "http://10.35.31.60:8080/weixinshdt?wsdl";
//
//	String urlname = "http://192.168.194.23:9080/Logon/services/Logon";
//	private final static Integer LIMIT = 60000;
//	public static void main(String args[]) throws Exception {
//		Service service = new Service();
//		Call call;
//		try {
////			String fromUserName ="asd";
////			String tagidflag ="是";
//			String content = "{\"button\":[{\"name\":\"功能\",\"sub_button\":[{\"type\":\"view\",\"name\":\"订单号绑定\",\"url\":\"http:\\/\\/192.168.1.175:8080/weixinshdt/demo.html\"]}]},{\"name\":\"帮助\",\"sub_button\":[{\"type\":\"click\",\"name\":\"如何绑定\",\"key\":\"如何绑定\",\"sub_button\":[]}]}]}";
//			call = (Call)service.createCall();
//			call.setTargetEndpointAddress(URL);
//			call.setOperationName(new QName("http://xml.apache.org/axis/wsdd/", "createMenu"));
//			call.addParameter("content", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
//			call.setUseSOAPAction(true);
//			call.setSOAPActionURI(URL);
//			call.setTimeout(LIMIT);
//			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
//			String result = (String) call.invoke(new Object[]{content});
//			System.out.println(result);
//
//		} catch (Exception e) {
//
//			// java.io.InterruptedIOException: Read timed out
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//
//		}
//
//	}
	
	  /**
     * @see HttpServlet#HttpServlet()
     */
    public Demo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
		System.out.print("servlet WeiXinServlet doGet start : \n ");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			String content = "{\"button\":[{\"name\":\"功能\",\"sub_button\":[{\"type\":\"view\",\"name\":\"首页2\",\"url\":\"http:\\/\\/majiabao.gnway.org\\/weixinshdt\\/index.html\"]}]},{\"name\":\"帮助\",\"sub_button\":[{\"type\":\"click\",\"name\":\"如何绑定\",\"key\":\"如何绑定\",\"sub_button\":[]}]}]}";
			CoreService cs = new CoreService();
			cs.deleteMenu();	//删除菜单
			cs.createMenu(content);	//创建菜单
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());

		}
	}

}