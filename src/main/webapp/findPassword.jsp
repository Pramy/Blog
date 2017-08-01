
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<link rel="stylesheet" href="Js/css.css" type="text/css"/>
<script type="text/javascript" src="Js/partern.js"></script>
	<script type="text/javascript" src="Js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="Js/myJs.js"></script>
<title>密码找回</title>

<script type="text/javascript" >
function checkMyFind() {
	if (checkfind()){
	    go($('#testForm'),"findPassword/get","post");
	}
	return false;
}

</script>
</head>

<body>
	<center>
		<div style="text-align: center; width:1000px; height:600px;	background: white;
	border-radius: 10px; box-shadow: 0 2px 10px #999;  margin:2% 0 0 0; padding-top: 5px;">
			<form id="testForm" onsubmit="return checkMyFind();">
			<input name="userName"  style="width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999;border-radius: 4px;" placeholder="帐号" /><br>
			<input type="submit" value="下一步" style="width: 200px; height: 30px; background-color: #37D890; color: white; border: 0; border-radius: 4px;" /><br>
			</form>
			<a href="/index.jsp" style="font-size:12px;">返回登录</a>
			<a href="/regist.jsp" style="font-size:12px;">点击注册</a>
			</div>
	</center>
</body>
</html>
