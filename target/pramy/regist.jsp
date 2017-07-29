<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<style type="text/css">    
 body{    
      background-image: url(images/loginBackground.jpg);    
      background-repeat: no-repeat;
      background-size: cover;    
 }</style>
 <script language="javascript">
 	function refresh(){
 		document.getElementById("verificationCode").src ="VerificationCodeServlet?"+ new Date().getTime();
 	}
 	function check(){
 		var username = document.getElementsByName("username")[0].value;
 		var password = document.getElementsByName("password")[0].value;
 		var rePassword = document.getElementsByName("rePassword")[0].value;
 		var email = document.getElementsByName("email")[0].value;
 		var question = document.getElementsByName("question")[0].value;
 		var answer = document.getElementsByName("answer")[0].value;
 		var code = document.getElementsByName("code")[0].value;
 		var sex = document.getElementsByName("sex");
 		if(username.length<1){
 			alert("用户名不能为空");
 			return false;
 		}else if(username.length<6 || username.length>16){
 			alert("用户名长度为6~16位");
 			return false;
 		}
 		if (password.length<1 || rePassword.length<1) {
 			alert("密码不能为空");
 			return false;
 		}
 		if(password.length<6 || password.length>16){
 			alert("密码长度为6~16位");
 			return false;
 		}
 		if(password!=rePassword){
 			alert("两次密码不同");
 			return false;
 		}
 		if (email.length<1) {
 			alert("邮箱不能为空");
 			return false;
 		}
 		if (question.length<1) {
 			alert("安全问题不能为空");
 			return false;
 		}
 		if (answer.length<1) {
 			alert("答案不能为空");
 			return false;
 		}
 		if (code.length<1) {
 			alert("验证码不能为空");
 			return false;
 		}
 		if (!sex[0].checked && !sex[1].checked) {
 			alert("请选择性别");
 			return false;
 		}
 		return true;
 	}
 
 </script>
</head>
<body>
	<center>
		<div
			style="text-align: center; width: 400px; height: 550px; background: white; border-radius: 10px; box-shadow: 0 2px 10px #999; margin: 8% 0px 0px 30%; padding-top: 5px;">
			<h2>注册</h2>
			<form action="RegistServlet?action=regist" method="post" onsubmit="return check()">
				<input type="text" name="username"style="width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999; border-radius: 4px;"
					placeholder="帐号" /><br>
				<input type="password" name="password"style="width: 200px; height: 30px; margin-bottom: 16px; padding-left: 3px; border: 1px solid #999; border-radius: 4px;"
					placeholder="密码" /><br>
				<input type="password" name="rePassword"style="width: 200px; height: 30px; margin-bottom: 16px; padding-left: 3px; border: 1px solid #999; border-radius: 4px;"
					placeholder="重复密码" /><br>
				<input type="text" name ="email"style="width: 200px; height: 30px; margin-bottom: 16px; padding-left: 3px; border: 1px solid #999; border-radius: 4px;"
					placeholder="邮箱"><br>
				<input type="text" name ="question"style="width: 200px; height: 30px; margin-bottom: 16px; padding-left: 3px; border: 1px solid #999; border-radius: 4px;"
					placeholder="安全问题"><br>
				<input type="text" name ="answer"style="width: 200px; height: 30px; margin-bottom: 16px; padding-left: 3px; border: 1px solid #999; border-radius: 4px;"
					placeholder="答案"><br>
				<input type="text" name ="code"style="width: 90px; height: 30px; margin-bottom: 16px; padding-left: 3px; border: 1px solid #999; border-radius: 4px;"
					placeholder="验证码">
			 	<img src="VerificationCodeServlet" id="verificationCode" onclick="refresh()" style="vertical-align:middle">
				<br> 
				<input type = "radio" name = "sex" value="男">男
				<input type = "radio" name = "sex" value="女">女
				<br>
				<input type="submit" value="注册" style="width: 200px; height: 30px; background-color: #37D890; color: white; border: 0; border-radius: 4px;" />
			</form>
		  	<a href="index.jsp" style="font-size: 12px;">返回登录</a> <a
				href="regist.jsp" style="font-size: 12px;">找回密码</a>
		</div>
	</center>
</body>
</html>