<%@page import="com.pramy.model.Role"%>
<%@page import="com.pramy.model.Section"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>密码找回</title>
<link rel="stylesheet" href="/Js/css.css" type="text/css"/>
<script type="text/javascript" src="/Js/jquery-3.2.1.js"></script>
 <script type="text/javascript" src="/Js/myJs.js"></script>
<script type="text/javascript" src="/Js/partern.js"></script>
<script type="text/javascript" >
function check() {
	if(checkUpdatePassword()){
	    go($('#updateForm'),"/findPassword/update","post");
	}
    return false;
}

</script>
</head>

<body>
	<center>
		<div style="text-align: center; width:1000px; height:600px;	background: white;
	border-radius: 10px; box-shadow: 0 2px 10px #999;  margin:2% 0 0 0; padding-top: 5px;">
			<form id="updateForm" onsubmit="return check();"  method="post" >
			账户：<input name="userName" id="userId" readonly="readonly" value="${findUser.userName}" style="width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999;border-radius: 4px;" /><br>
			问题：<input name="question" id="questionId" readonly="readonly" value="${findUser.question}" style="width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999;border-radius: 4px;" /><br>
			答案：<input name="answer" id="answerId" style="width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999;border-radius: 4px;" placeholder="答案" /><br>
			邮箱：<input name="email" id="emailId" style="width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999;border-radius: 4px;" placeholder="注册邮箱" /><br>
			新密码：<input type="password" name="userPassword" id="passwordId"   style="width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999;border-radius: 4px;"placeholder="新密码" /><br>
			新密码：<input type="password" name="rePassword" id="repasswordId"   style="width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999;border-radius: 4px;"placeholder="重新输入密码" /><br>
			<input type="submit" value="修改" style="width: 200px; height: 30px; background-color: #37D890; color: white; border: 0; border-radius: 4px;" /><br>
			</form>
			<a href="/index.jsp" style="font-size:12px;">返回登录</a>
			<a href="/regist.jsp" style="font-size:12px;">点击注册</a>

			</div>
	</center>
</body>
</html>
