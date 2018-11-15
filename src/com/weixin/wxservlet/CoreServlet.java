package com.weixin.wxservlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weixin.http.WXBizMsgCrypt;
import com.weixin.wxservice.CoreService;
import com.weixin.wxutil.SignUtil;

/**
 * 类名: CoreServlet </br>
 * 描述: 来接收微信服务器传来信息 </br>
 */
// @WebServlet("/CoreServlet") //20170721 王志宁 必须注释掉此句才行？
public class CoreServlet extends HttpServlet {
	private static final long serialVersionUID = 4323197796926899691L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CoreServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 20170722 必须利用微信设置界面提交执行，参数里才有值的。
		String signature = request.getParameter("signature"); // 微信加密签名
		String timestamp = request.getParameter("timestamp"); // 时间戳
		String nonce = request.getParameter("nonce"); // 随机数
		String echostr = request.getParameter("echostr"); // 随机字符串

		String action = request.getParameter("action");
		if (action != null && action.equals("viewtest")) {
			doPost(request, response);
		}
		PrintWriter out = response.getWriter();
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			System.out.println("check ok20170722 ok 验证");
			out.print(echostr);
			System.out.print("\n 微信验证signature  " + signature);
		}
		out.close();
		System.out.print("\n 微信验证out.close();    ");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response) 处理微信服务器发来的消息
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 消息的接收、处理、响应
		// // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");

		String msgsignature = request.getParameter("msg_signature"); // 微信加密签名
		String timestamp = request.getParameter("timestamp"); // 时间戳
		String nonce = request.getParameter("nonce"); // 随机数

		// 参数准备。 来自微信公众号的设置页面
		String token = "mscpwx1";
		String encodingAesKey = "b27DpZtN9DPaLIGn07pFGwhsBVDznYJY8RiyZ0DiiNk";
		String appId = "wxd426078cc6b4e46f";

		try {

			System.out.print("\n 微信验证doPost;    ");
			// 获取 请求数据流。包是密文。
			InputStream ins = request.getInputStream();
			StringBuffer postdata = new StringBuffer();
			byte[] b = new byte[4096];
			for (int n; (n = ins.read(b)) != -1;) {
				postdata.append(new String(b, 0, n));
				// postdata 含密文的字符串未 解析的密文。{ToUserName=gh_ca3f64f1f70e,
				// Encrypt=+FBpo9Tou0HQ2v0GmWZkQZqLmaCQhlQf0tqhYjzC6ZRWCwYAJdUA0Pi1mkfoFXaY0KrhSPTL5Nnbw8zN2TbcdzUZWPzwmhV7kBzCFsoRdhNyHBZHHLcy88eVqmB6tz4p26NXz+xrhKwotMXluzSd0gVqyMuYOrciOfIcEbmle+hmzOkWz8fb3GDEgsHPmxxPwAusFRyslpEQODbe5A6Mfj6TdBBiIU0Oml+f4CxzbONPQE/Z4/QqUmhV36XGQ4bIRVKBDbDQVfwj411KzwlT6VziUCKWhhOafCn3C8deQtc0Gufg9V36q2Pxmvw0JkJGRPc7QYSsU0mWQq4nellZUuCObeAs2Zn5z1EDUwL/6+SLdfJ50sxgdPjii8HPQ4lF0ad5kdMGfqe+950plZaAYf6H8avnZud5vcHJBbjUDURIsduEA4z6IUBwdsFrVPkQ9joZiW4QpN0o4RNG5ySgFQ==}
			}
			ins.close();
			System.out.print("\n 消息处理 密文下的请求消息postdata  " + postdata.toString());

			WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);

			// 第三方收到公众号平台发送的消息 进行解密。
			String afterDecrpt = pc.decryptMsg(msgsignature, timestamp, nonce, postdata.toString());

			System.out.print("\n 消息处理 密解析后的明文afterDecrpt  " + afterDecrpt.toString());
			// 利用 微信第三方代码 处理 微信发来的消息afterDecrpt 给出回应。respXml
			CoreService cs = new CoreService();
			String respXml = cs.processRequest(afterDecrpt);
			System.out.print("\n 服务器处理后的信息- 明文   " + respXml.toString() + "\n" + respXml);

			// respXml 消息群发
			String afterEncrpt = pc.encryptMsg(respXml, timestamp, nonce);
			System.out.print("\n 服务器处理后的信息 密文 为给服务器处理准备afterEncrpt  " + afterEncrpt.toString() + "\n");

			// request.setCharacterEncoding("UTF-8");
			// response.setCharacterEncoding("UTF-8");
			// System.out.print("\n 微信消息处理开始 CoreService.processRequest(request)
			// ");
			// if(request!=null){
			// System.out.print("\n 微信消息处理request.toString()
			// "+request.toString());
			// }
			//
			//
			// // 调用核心业务类接收消息、处理消息
			// String respXml = CoreService.processRequest();

			// 响应消息

			out.print(afterEncrpt);
			// System.out.print("\n 微信公众号当前是加密模式，必须接收到密文形式。");
			// System.out.print("\n 微信公众号中展示消息接收密文形式，展示明文
			// "+afterEncrpt.toString()+"\n"+respXml);
			//
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("\n错误 " + e.getMessage().toString());

		} finally {
			// out.close();
		}
	}

}
