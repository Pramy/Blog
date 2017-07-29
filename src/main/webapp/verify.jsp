<%@page import="com.pramy.model.User"%>
<%@page import="com.pramy.model.Role"%>
<%@page import="com.pramy.model.Section"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>分区</title>
<style type="text/css">    
 body{    
      background-image: url(./images/loginBackground.jpg);    
      background-repeat: no-repeat;
      background-size: cover;    
 }</style>
<link rel="stylesheet" href="../Js/css.css" type="text/css" />
<script type="text/javascript">
function check(){
	var test=document.myfrom.mailCode.value;
	if(test==""){
		alert("验证码不能为空");
		return false;
	}
	return true;
}
</script>
</head>

<body>
	<center>
		<div style="text-align: center; width:1000px; height:600px;	background: white;
	box-shadow: 0 2px 10px #999;  margin:2% 0 0 0;">
			<div style="padding-top:250px;">
				<form name="myfrom" action="RegistServlet?action=verify" method="post" onsubmit="return check()">
			用户名：<input type="text" disabled="disabled"  name="userName" value="${temepUser.userName } "style="width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999;border-radius: 4px;" /><br>
			邮箱：<input type="text" disabled="disabled" name="mail" value="${temepUser.email }" style="width: 200px; height: 30px; margin-bottom: 16px;padding-left: 3px; border: 1px solid #999;border-radius: 4px;" /><br>
			<input type="text" name ="mailCode"style="width: 95px; height: 30px; margin-bottom: 16px; padding-left: 3px; border: 1px solid #999; border-radius: 4px;"
					placeholder="验证码">
			<input type="submit" value="确定" style="width: 200px; height: 30px; background-color: #37D890; color: white; border: 0; border-radius: 4px;" />
				</form>
			</div>
		</div>
	</center>
</body>
</html>
