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
      background-image: url(../images/loginBackground.jpg);    
      background-repeat: no-repeat;
      background-size: cover;    
 }</style>
<link rel="stylesheet" href="../Js/css.css" type="text/css" />
 
<script type="text/javascript">
function check(agr){	
	/* var sectionLevel = document.getElementById(agr).value; */
	/* var userLevel = document.getElementById('userLevel').value; */
 	if(agr >${user.level}){
		alert("权限不足，无法进入该讨论区");
		return false;
	}
	return true;
}

</script>
<% Role role = (Role)session.getAttribute("role");
   User user = (User)session.getAttribute("user");
if(role.getRoleName().equals("超级版主")){
%>
<jsp:include page="../section/sectionMenu.jsp"></jsp:include>
<%}%>
<jsp:include page="../user/userMenu.jsp"></jsp:include>
</head>

<body>
	<center>
		<div style="text-align: center; width:1000px; height:600px;	background: white;
	box-shadow: 0 2px 10px #999;  margin:2% 0 0 0;">
				<input type="hidden" id="userLevel" value="${user.level }">
			<c:if test="${not empty sectionList }">
				<c:forEach var="item" items="${sectionList }" begin="1" end="${fn:length(sectionList) }" varStatus="i">
					<c:choose>
					<c:when test="${item.id==2 }"> 
						<a class="mya" href="../FileServlet?action=select"
							style="margin:0 0px 5px;float:left;font-size: 20px;display:inline-block;height:100px;width:200px;line-height:100px;text-decoration:none;" onClick="return check('${item.level}');">${item.sectionName }</a>
  					</c:when>
					<c:otherwise>
						<c:choose>
						<c:when test="${item.id==3 }">
							<a class="mya" href="../ImageServlet?action=select&name=${item.sectionName}&id=${item.id}"
							style="margin:0 0 5px;float:left;font-size: 20px;display:inline-block;height:100px;width:200px;line-height:100px;text-decoration:none;" onClick="return check('${item.level}');">${item.sectionName }</a>
						</c:when>
						<c:otherwise>
							<a class="mya" href="../chatRoom.jsp?name=${item.sectionName}&id=${item.id}"
							style="margin:0 0px 5px;float:left;font-size: 20px;display:inline-block;height:100px;width:200px;line-height:100px;text-decoration:none;" onClick="return check('${item.level}');">${item.sectionName }</a>
						</c:otherwise>
						</c:choose>
   					</c:otherwise>
   					</c:choose>
                    <%-- <c:if test="${ i.index%5==0}"><br></c:if> --%>
                 </c:forEach>
			<c:if test="${role.roleName ne '普通用户' }">
			<!-- <br> -->
				<c:forEach  var="item" items="${sectionList }" begin="0" end="0"  varStatus="i">
					<a class="mya" href="../chatRoom.jsp?name=${item.sectionName}&id=${item.id}" style="margin:0 0px 5px;float:left;font-size:20px; display:inline-block;height:100px;width:200px;line-height:100px;text-decoration:none;"onClick="return check('${item.level}');">${item.sectionName }</a>
				</c:forEach>
			</c:if>
			</c:if>
			</div>
	</center>
</body>
</html>
