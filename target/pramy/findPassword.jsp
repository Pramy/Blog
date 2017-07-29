<%@page import="com.pramy.model.Role"%>
<%@page import="com.pramy.model.Section"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript" src="../Js/partern.js"></script>
<title>密码找回</title>
<style type="text/css">    
 body{    
      background-image: url(./images/loginBackground.jpg);    
      background-repeat: no-repeat;
      background-size: cover;    
 }</style>
 
<script type="text/javascript" src="./Js/partern.js">
function check(){
	var userName=document.getElementById("userId").value;
	if(!isLegal(userName)){
		alert("请输入合法的内容");
		return false;
	}
	return true;
}

</script>
</head>

<body>
	<center>
		<div style="text-align: center; width:1000px; height:600px;	background: white;
	border-radius: 10px; box-shadow: 0 2px 10px #999;  margin:2% 0 0 0; padding-top: 5px;">
			<form onsubmit="return check();" action=FindPasswordServlet?action=check  method="post" >
			<input type="text" name="userName" id="userId" style="width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999;border-radius: 4px;"placeholder="帐号" /><br>
			<input type="submit" value="下一步" style="width: 200px; height: 30px; background-color: #37D890; color: white; border: 0; border-radius: 4px;" /><br>
			<a href="./index.jsp" style="font-size:12px;">返回登录</a>
			<a href="./regist.jsp" style="font-size:12px;">点击注册</a>
			</form>
			</div>
	</center>
</body>
</html>
