package com.weixin.test;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.AxisFault;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class TestWebservice {

	public static void main(String[] args) throws ServiceException, AxisFault {

		String result = null;
		try {
			String endpoint = "http://localhost:8080/weixinshdt/hello?wsdl";
			// 直接引用远程的wsdl文件
			// 以下都是套路
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(endpoint));
			/**
			 * 注意这里，要设置Namespace xsd:schema:namespace
			 */
			call.setOperationName(new QName("http://wxservice.shdt.com/", "greetings"));// WSDL里面描述的接口名称
			call.addParameter("name", XMLType.XSD_STRING, ParameterMode.IN);// 接口的参数
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
			result = (String) call.invoke(new Object[] { "hexin" });
			// 给方法传递参数，并且调用方法
			System.out.println("result is :" + result);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}
}
