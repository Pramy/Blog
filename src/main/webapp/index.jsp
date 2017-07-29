<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>欢迎来到部落格</title>
<style type="text/css">    
 body{    
      background-image: url(images/loginBackground.jpg);    
      background-repeat: no-repeat;
      background-size: cover;    
 }</style>
 
 <script type="text/javascript">
 	function check(){
 		var code = document.getElementsByName("code")[0];
 		var userName = document.getElementsByName("username")[0];
 		var password = document.getElementsByName("password")[0];
 		if(userName.value.length<1){
 			alert("用户名不能为空");
 			return false;
 		}
 		if(password.value.length<1){
 			alert("密码不能为空");
 			return false;
 		}
 		if(code.value.length<1){
 			alert("验证码不能为空");
 			return false;
 		}
 		return true;
 	}
 	function refresh(){
 		document.getElementById("verificationCode").src ="VerificationCodeServlet?"+ new Date().getTime();
 	}
 
 </script>
</head>

<body>
	<center>
		<div style="text-align: center; width:400px; height:300px;	background: white;
	border-radius: 10px; box-shadow: 0 2px 10px #999;  margin:8% 0px 0px 30% ; padding-top: 5px;">
			<h2 >欢迎登陆</h2>
			<form action="LoginServlet?action=login" method="post" onsubmit="return check()">
			<input type="text" name="username" style="width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999;border-radius: 4px;"placeholder="帐号" /><br>
			<input type="password" name="password" style="width: 200px; height: 30px; margin-bottom: 16px;padding-left: 3px; border: 1px solid #999;border-radius: 4px;" placeholder="密码" /><br>
			<input type="text" name ="code"style="width: 95px; height: 30px; margin-bottom: 16px; padding-left: 3px; border: 1px solid #999; border-radius: 4px;"
					placeholder="验证码">
			<img src="VerificationCodeServlet" id="verificationCode" onclick="refresh()" style="vertical-align:middle"><br>
			<input type="submit" value="登录" style="width: 200px; height: 30px; background-color: #37D890; color: white; border: 0; border-radius: 4px;" />
			</form>
			<a href="regist.jsp" style="font-size:12px;">点击注册</a>
			<a href="findPassword.jsp" style="font-size:12px;">修改密码</a>
			</div>
	</center>
</body>
</html>
