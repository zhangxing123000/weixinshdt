window.onload=function(){ 
    // 初始化内容 
	alert("用户Id:"+GetQueryString("openId"));
}

function login() {// 传入表单参数
	var name = $("#name").val(); // 姓名
	var account = $("#account").val(); // 账号
	var password = $("#password").val(); // 密码
	var openId = GetQueryString("openId");	//微信用户表示
	var passwordAffirm = $("#passwordAffirm").val(); // 密码确认
	if (name == "" || $.trim(name) == null) { // 验证用户名是否为空
		alert("请输入姓名！");
		return false;
	}
	if (account == "" || $.trim(account) == null) { // 验证账号是否为空
		alert("请输入账号！");
		return false;
	}
	if (password == "" || $.trim(password) == null) { // 验证账号是否为空
		alert("请输入密码！");
		return false;
	}
	if (passwordAffirm == "" || $.trim(passwordAffirm) == null) {
		alert("请输入密码确认！");
		return false;
	}
	if(passwordAffirm == password || $.trim(passwordAffirm) == $.trim(password)){
		// 将登录信息连接成字符串，作为发送请求的参数
		var param = "../../indexD?fromUserName="+openId+"&name=" + name + "&account="
				+ account+"&password=" + password + "&passwordAffirm="
					+ passwordAffirm;
		$.ajax({
			url : param,
			type : "POST",
			dataType : "text",
			success : function(data) {
				alert("账号绑定");
//				var status = data;
//				alert(status);
			},
			error: function(e){
				alert(e);
			}
		});
	}else{
		alert("俩个密码不同");
		return false;
	}
	
}

function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
