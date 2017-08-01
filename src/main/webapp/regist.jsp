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
<script type="text/javascript" src="Js/myJs.js"></script>
    <script type="text/javascript" src="Js/jquery-3.2.1.js"></script>
	<script type="text/javascript">
		function check() {
			if(checkRegister()){
			 go($('#registerFrom'),"user/register","post");
			}
			return false;
        }
	</script>
</head>
<body>
	<center>
		<div
			style="text-align: center; width: 400px; height: 550px; background: white; border-radius: 10px; box-shadow: 0 2px 10px #999; margin: 8% 0px 0px 30%; padding-top: 5px;">
			<h2>注册</h2>
			<form id="registerFrom" method="post" onsubmit="return check()">
				<input name="userName" style="width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999; border-radius: 4px;"
					   placeholder="帐号" /><br>
				<input type="password" name="userPassword"style="width: 200px; height: 30px; margin-bottom: 16px; padding-left: 3px; border: 1px solid #999; border-radius: 4px;"
					placeholder="密码" /><br>
				<input type="password" name="rePassword"style="width: 200px; height: 30px; margin-bottom: 16px; padding-left: 3px; border: 1px solid #999; border-radius: 4px;"
					placeholder="重复密码" /><br>
				<input name ="email" style="width: 200px; height: 30px; margin-bottom: 16px; padding-left: 3px; border: 1px solid #999; border-radius: 4px;"
					   placeholder="邮箱"><br>
				<input name ="question" style="width: 200px; height: 30px; margin-bottom: 16px; padding-left: 3px; border: 1px solid #999; border-radius: 4px;"
					   placeholder="安全问题"><br>
				<input name ="answer" style="width: 200px; height: 30px; margin-bottom: 16px; padding-left: 3px; border: 1px solid #999; border-radius: 4px;"
					   placeholder="答案"><br>
				<input name ="code" style="width: 90px; height: 30px; margin-bottom: 16px; padding-left: 3px; border: 1px solid #999; border-radius: 4px;"
					   placeholder="验证码">
			 	<img src="/code" id="verificationCode" onclick="refresh()" style="vertical-align:middle">
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