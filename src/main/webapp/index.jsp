<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script type="text/javascript" src="Js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="Js/myJs.js"></script>
<script type="text/javascript">
	function test() {
		if(checkLogin()){
		    go($('#loginForm'),"/login","post","verifyResult.jsp");
		}
		return false;
	}
</script>
</head>

<body>
	<center>
		<div style="text-align: center; width:400px; height:300px;	background: white;
	border-radius: 10px; box-shadow: 0 2px 10px #999;  margin:8% 0px 0px 30% ; padding-top: 5px;">
			<h2 >欢迎登陆</h2>
			<form id="loginForm"  onsubmit="return test()">
			<input name="userName" style="width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999;border-radius: 4px;" placeholder="帐号" /><br>
			<input type="password" name="userPassword" style="width: 200px; height: 30px; margin-bottom: 16px;padding-left: 3px; border: 1px solid #999;border-radius: 4px;" placeholder="密码" /><br>
			<input name ="code" style="width: 95px; height: 30px; margin-bottom: 16px; padding-left: 3px; border: 1px solid #999; border-radius: 4px;"
				   placeholder="验证码">
			<img src="/code" id="verificationCode" onclick="refresh()" style="vertical-align:middle"><br>
			<input type="submit" value="登录" style="width: 200px; height: 30px; background-color: #37D890; color: white; border: 0; border-radius: 4px;" />
			</form>
			<a href="regist.jsp" style="font-size:12px;">点击注册</a>
			<a href="findPassword.jsp" style="font-size:12px;">修改密码</a>
			</div>
	</center>
</body>
</html>
