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
function getXmlHttpObject() {
	var xmlHttp = null;
	try {
		xmlHttp = new XMLHttpRequest();
	} catch (e) {
		try {
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	return xmlHttp;
}

function getServerInfo() {
	http = getXmlHttpObject();
	var url = "GetMessageServlet?action=get&sectionId="+document.getElementById("myId").value+"&time=" + Math.random();
	http.onreadystatechange = getInfoBack;
	http.open("GET", url, true);
	http.send(null);
}

function getInfoBack() {
	if (http.readyState == 4) {
		if (http.status == 200) {
			var response = http.responseText;
			document.getElementById("chatbox").innerHTML += response
					+ "\r\n";
			getServerInfo();
		}
	}
}

function sendok(o) {
	myform.submit();
	document.getElementById(o).value = "";
	var txt = document.getElementById("chatbox");
	txt.scrollTop = txt.scrollHeight;
}
function seeChat(arg){
	var id=document.getElementById('myId').value;
	window.open("ChatDateServlet?action=select&sectionId="+id);  
}
</script>
</head>

<body onload="getServerInfo()">
	<center>
	<div style="text-align: center;float:right; width:150px; height:75px;	background: white; box-shadow: 0 2px 10px #999;  margin-right:5%;">	
	<a class="mya" style="width:150px; height: 75px; float:right; line-height:75px;text-decoration:none;" href="./section/SectionServlet" >返回首页</a>
	</div>
		<div style="text-align: center; width:1000px; height:600px;	background: white;
	border-radius: 10px; box-shadow: 0 2px 10px #999;  margin:2% 0 0 0; padding-top: 5px;">
		<input type="hidden" name="sectionId" id="myId" value=<%=request.getParameter("id")%>>
			聊天内容：
	<br>
	
	<textarea id="chatbox" rows="10" cols="50" disabled="disabled"
		style="background-color: white;width:850px;height:500px"></textarea>
	<br>
	
	<form style="display:inline-block;margin:20px 40px 0 40px;" name="myform" method="post" action="SendMessageServlet?action=send&sectionId=<%=request.getParameter("id")%>"  target="sform"  >
		<input style="width:340px;height:30px;" id="msginput" type="text" name="chatmsg" />
		<input style="width:80px;height:30px; background-color: #37D890; color: white; border: 0; border-radius: 4px;" type="button" value="发送" onclick="sendok('msginput')" />
	</form>
	<form style="display:inline-block;margin-top:20px;" name="myform1" method="post" action="FileServlet?action=upload" enctype="multipart/form-data">
		<input style="width:200px;height:30px;" type="file" name="file" >
		<input style="width:80px;height:30px;margin-right:20px; background-color: #37D890; color: white; border: 0; border-radius: 4px;" type="submit" value="上传">
		<input type="hidden" name="mytype" value="chat">
		<input style="width:80px;height:30px;margin-right:20px; background-color: #37D890; color: white; border: 0; border-radius: 4px;" type="button" value="聊天记录	" onclick="seeChat('${id}')">
	</form>
	
	<iframe width="0" height="0" style="display: none" name="sform"></iframe>
			</div>
	</center>
</body>
</html>
