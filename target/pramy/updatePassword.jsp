<%@page import="com.pramy.model.Role"%>
<%@page import="com.pramy.model.Section"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript" src="./Js/partern.js"></script>
<title>密码找回</title>
<style type="text/css">    
 body{    
      background-image: url(./images/loginBackground.jpg);    
      background-repeat: no-repeat;
      background-size: cover;    
 }</style>
 
<script type="text/javascript" >
function check(){
	var answer=document.getElementById("answerId").value;
	var email=document.getElementById("emailId").value;
	var password=document.getElementById("passwordId").value;
	var repassword=document.getElementById("repasswordId").value;
	if(!isLegal(answer) | !isLegal(password) | !isLegal(repassword)){
		alert("请输入合法的内容");
		return false;
	}
	if(password.length<6 |repassword.length<6){
		alert("密码长度不能低于6位");
		return false;
	}
	if(!isEmail(email)){
		alert("邮箱格式有误");
		return false;
	}
	if(password!=repassword){
		alert("两次输入的密码不同");
		return false;
	}
	return true;
}
function isEmail(str){
	   var re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
	   if(re.test(str)){
	       return true;
	   }else{
	       return false;
	   }
}
</script>
</head>

<body>
	<center>
		<div style="text-align: center; width:1000px; height:600px;	background: white;
	border-radius: 10px; box-shadow: 0 2px 10px #999;  margin:2% 0 0 0; padding-top: 5px;">
			<form onsubmit="return check();" action=FindPasswordServlet?action=update  method="post" >
			账户：<input type="text" name="userName" id="userId"  readonly="readonly"  value="${findUser.userName}" style="width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999;border-radius: 4px;" /><br>
			问题：<input type="text" name="question" id="questionId"  readonly="readonly"  value="${findUser.question}" style="width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999;border-radius: 4px;" /><br>
			答案：<input type="text" name="answer" id="answerId"  style="width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999;border-radius: 4px;"placeholder="答案" /><br>
			邮箱：<input type="text" name="email" id="emailId"  style="width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999;border-radius: 4px;"placeholder="注册邮箱" /><br>
			新密码：<input type="password" name="password" id="passwordId"   style="width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999;border-radius: 4px;"placeholder="新密码" /><br>
			新密码：<input type="password" name="repassword" id="repasswordId"   style="width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999;border-radius: 4px;"placeholder="重新输入密码" /><br>
			<input type="submit" value="修改" style="width: 200px; height: 30px; background-color: #37D890; color: white; border: 0; border-radius: 4px;" /><br>
			<a href="./index.jsp" style="font-size:12px;">返回登录</a>
			<a href="./regist.jsp" style="font-size:12px;">点击注册</a>
			</form>
			</div>
	</center>
</body>
</html>
