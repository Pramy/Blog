<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.pramy.model.Role"%>
<%@page import="com.pramy.model.Section"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>分区</title>
<script type="text/javascript" src="<c:url value="/Js/partern.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/Js/css.css"/>" type="text/css"/>
<script type="text/javascript" src="<c:url value="/Js/myJs.js"/>"></script>
<script type="text/javascript" src="<c:url value="/Js/jquery-3.2.1.js"/>"></script>
<% Role role = (Role)session.getAttribute("role");
if(role.getRoleName().equals("超级版主")){
%>
<jsp:include page="/section/sectionMenu.jsp"></jsp:include>
<%}%>
<jsp:include page="/user/userMenu.jsp"></jsp:include>
<script type="text/javascript">
function checkadd(){
	var name =document.getElementById("sectionId").value;
	var level =document.getElementById("levelId").value;
	if(!isLegal(name)){
		alert("请输入合法字符");
		return false;
	}
	if(!isDigit(level)){
		alert("请输入有效的3位数");
		return false;
	}
	return true;
}
function check() {
	if(checkadd()){
	    go($('#addSectionForm'),"/section/add.do","post");
	}
	return false;
}
</script>
</head>

<body>
	<center>

		<div style="text-align: center; width:1000px; height:600px;	background: white;
	border-radius: 10px; box-shadow: 0 2px 10px #999;  margin:2% 0 0 0; padding-top: 5px;">
			<form onsubmit="return check();" id="addSectionForm"  method="post" >
			<input name="sectionName" id="sectionId" style="width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999;border-radius: 4px;" placeholder="版块名称" /><br>
			<input name="level" id="levelId" style="width: 200px; height: 30px; margin-bottom: 16px;padding-left: 3px; border: 1px solid #999;border-radius: 4px;" placeholder="权限" /><br>
			<input type="submit" value="创建" style="width: 200px; height: 30px; background-color: #37D890; color: white; border: 0; border-radius: 4px;" />
			</form>
			</div>
	</center>
</body>
</html>
