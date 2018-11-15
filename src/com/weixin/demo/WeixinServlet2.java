package com.weixin.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.weixin.wxutil.CheckUtil;
import com.weixin.wxutil.MessageUtil;

/**
 * Servlet implementation class WeixinServlet
 */
@WebServlet("/WeixinServlet")
public class WeixinServlet2  extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {String signature = req.getParameter("signature");
    String timestamp = req.getParameter("timestamp");
    String nonce = req.getParameter("nonce");
    String echostr = req.getParameter("echostr");
    
    PrintWriter out  = resp.getWriter();
    if(CheckUtil.checkSignature(signature, timestamp, nonce)){
        out.print(echostr);
    }
}

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
    doGet(req, resp);
}
}
