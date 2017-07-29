<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../Js/css.css" type="text/css" />
</head>
<body>
<div style="text-align: center;float:right; width:150px; height:300px;	background: white; box-shadow: 0 2px 10px #999;  margin-right:5%;">
	<%--<c:if test="${role.roleName ne '普通用户'}">--%>
	<a class="mya" style="width:150px; height: 75px; float:right; border-bottom: 1px solid #999;line-height:75px;text-decoration:none;" href="../user/UserServlet?action=select" >用户信息</a>
	<%--</c:if>--%>
	<a class="mya" style="width:150px; height: 75px; float:right; border-bottom: 1px solid #999;line-height:75px;text-decoration:none;" href="../user/userUpdate.jsp" >修改信息</a>
	<a class="mya" style="width:150px; height: 75px; float:right; border-bottom: 1px solid #999;line-height:75px;text-decoration:none;" href="../section/SectionServlet" >返回首页</a>
	<a class="mya" style="width:150px; height: 75px; float:right; line-height:75px;text-decoration:none;" href="../logout.jsp?message=注销成功" >注销</a>
</div>
</body>
</html>