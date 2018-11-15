package com.weixin.wxservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weixin.wxutil.SignUtil;



/**
 * Servlet implementation class MyAutoWeixinServlet
 * /**
 * 类名: MyAutoWeixinServlet </br> 
 * 描述: 来接收微信服务器传来信息 </br>  
 */

//@WebServlet("/MyAutoWeixinServlet")
public class MyAutoWeixinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	public MyAutoWeixinServlet() {
//		// TODO Auto-generated constructor stub
//	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *   确认请求来自微信服务器
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().append("Served at MyAutoWeixinServlet : ").append(req.getContextPath());
		
		System.out.print("微信自动服务器运行。");
		
		// 微信加密签名
        String signature = req.getParameter("signature");
        // 时间戳
        String timestamp = req.getParameter("timestamp");
        // 随机数
        String nonce = req.getParameter("nonce");
        // 随机字符串
        String echostr = req.getParameter("echostr");

        PrintWriter out = resp.getWriter();
        System.out.print("开始执行SignUtil echostr "+echostr);
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {

        	
            out.print(echostr);
        }
        
        out.close();
        out = null;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 处理微信服务器发来的消息
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		// 消息的接收、处理、响应
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        System.out.print("成功得到 "+req);
        // 调用核心业务类接收消息、处理消息
        String respXml = "";//CoreService.processRequest(req);

        // 响应消息
        PrintWriter out = resp.getWriter();
        System.out.print("成功得到 "+respXml);
        out.print(respXml);
        out.close();
	}

}
