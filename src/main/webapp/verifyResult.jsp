<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="Js/css.css" type="text/css" />

<title>${message}</title>
	<script type="text/javascript" >
onload=function(){  
    setInterval(go, 1000);  
};  
var x=3; 
function go(){  
 x--; 
    if(x>0){  
    document.getElementById("sp").innerHTML=x;  
    }else{  
    	window.location.href="/index.jsp";
    }  
}
</script>
</head>

<body >
	<center>
		<div style="text-align: center ; width:1000px; height:600px;	background: white;
	box-shadow: 0 2px 10px #999;  margin:2% 0 0 0; ">
			<p style="padding-top:250px;color: crimson">${message}</p>
			<div style=";">
				<span id="sp"></span>秒返回登陆界面<br>
				<a href="/index.jsp">浏览器没反应请手动点击返回</a>
			</div>
		</div>
	</center>
</body>
</html>
