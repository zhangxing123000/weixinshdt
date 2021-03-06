package com.weixin.test.Message;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.weixin.test.MD5.MD5Test;

public class MessageKefu {

	public static void main(String[] args) {
		try {
			MD5Test md = new MD5Test();
			String urlname = "http://localhost:8080/weixinshdt/CoreService?wsdl";
//			String content = "{ \"kf_account\" : \"test1@qq.com\", \"nickname\" : \"客服1\",  \"password\" : \"" + password+ "\"  }";
			String content = "{\"touser\":\"oPP1dwS2I-W5-VOs_s360_Jr9UO0\",\"msgtype\":\"text\","
					+ "\"text\":{\"content\":\"Hello World回复场景模拟\"}}";
			// 直接引用远程的wsdl文件
			// 以下都是套路
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(urlname));
			/**
			 * 注意这里，要设置Namespace xsd:schema:namespace
			 */
			call.setOperationName(new QName("http://wxservice.shdt.com/", "MessageKeFu"));// WSDL里面描述的接口名称
			call.addParameter("content", XMLType.XSD_STRING, ParameterMode.IN);// 接口的参数
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
			String result = (String) call.invoke(new Object[] { content });
			// 给方法传递参数，并且调用方法
			System.out.println("result is :" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
