package com.weixin.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weixin.wxutil.MessageUtil;

/**
 * Servlet implementation class WeixinServlet
 */
public class WeixinServlet  extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");

    	resp.setCharacterEncoding("UTF-8");

    	PrintWriter out = resp.getWriter();

    	String str = null;

    	try {


    	Map<String,String> map = MessageUtil.parseXml(req);

    	//从集合中，获取XML各个节点的内容

    	String ToUserName = map.get("ToUserName");

    	String FromUserName = map.get("FromUserName");

    	String CreateTime = map.get("CreateTime");

    	String MsgType = map.get("MsgType");

    	String Content = map.get("Content");

    	String MsgId = map.get("MsgId");

    	if(MsgType.equals("text")){//判断消息类型是否是文本消息(text)

    	TestMessage message = new TestMessage();

    	message.setFromUserName(ToUserName);//原来【接收消息用户】变为回复时【发送消息用户】

    	message.setToUserName(FromUserName);

    	message.setMsgType("text");

    	message.setCreateTime(new Date().getTime());//创建当前时间为消息时间

    	message.setContent("您好，"+FromUserName+"\n我是："+ToUserName

    	+"\n您发送的消息类型为："+MsgType+"\n您发送的时间为"+CreateTime

    	+"\n我回复的时间为："+message.getCreateTime()+"您发送的内容是"+Content);

    	str = MessageUtil.objectToXml(message); //调用Message工具类，将对象转为XML字符串

    	}

    	} catch (Exception e) {

    	e.printStackTrace();

    	}

    	out.print(str); //返回转换后的XML字符串

    	out.close();
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        doGet(req, resp);
    }
}
