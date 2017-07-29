<%@page import="com.pramy.model.Role"%>
<%@page import="com.pramy.model.Section"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="./Js/css.css" type="text/css" />

<title><%=request.getParameter("name") %></title>
<style type="text/css">    
 body{    
      background-image: url(images/loginBackground.jpg);    
      background-repeat: no-repeat;
      background-size: cover;    
 }</style>
<script type="text/javascript" >
onload=function(){  
    setInterval(go, 1000);  
};  
var x=3; 
function go(){  
x--;  
    if(x>0){  
    document.getElementById("sp").innerHTML=x;  //每次设置的x的值都不一样了。  
    }else{  
    	document.myFrom.submit();  
    }  
}
</script>
</head>

<body >
	<center>
		<div style="text-align: center; width:1000px; height:600px;	background: white;
	box-shadow: 0 2px 10px #999;  margin:2% 0 0 0;">
		${message }
		<form  action="${servlet }"name="myFrom" id="myfrom" method="post">
			<input type="hidden" value="${pageSize}" name="pageSize" >
			<input type="hidden" value="${pageNo}" name="pageNo" >
			<input type="hidden" value="${select}" name="select">
			<input type="hidden" value="${cc }" name="cc" >
		</form>
			<span id="sp"></span>跳转
			
		</div>
	</center>
</body>
</html>
