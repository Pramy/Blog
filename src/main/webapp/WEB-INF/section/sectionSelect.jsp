<%@page import="com.pramy.model.Role"%>
<%@page import="com.pramy.model.Section"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>分区</title>
<script type="text/javascript" src="../Js/partern.js"></script>
<script type="text/javascript">
function check(){
	var pageNo = document.getElementById("pageNo").value;
	var pageSize = document.getElementById("pageSize").value;
	if(!isDigit(pageNo) || pageNo==0 || !isDigit(pageSize) || pageSize==0){
		alert("请输正确页数！");
		return false;
	}
	if(pageNo >${totalPage}){
		alert("最多只有 "+${totalPage}+" 页");
		return false;
	}
	return ture;
}

</script>
<style type="text/css">    
 body{    
      background-image: url(../images/loginBackground.jpg);    
      background-repeat: no-repeat;
      background-size: cover;    
 }
</style>
 <%
 	List<Section> list = (List<Section>) request.getAttribute("selectSection");
 int pageNo = (Integer)request.getAttribute("pageNo");
 int totalPage = (Integer)request.getAttribute("totalPage");	
	Role role = (Role) session.getAttribute("role");
	if (role.getRoleName().equals("超级版主")) {
%>
<jsp:include page="sectionMenu.jsp"></jsp:include>
<%
	}
%>

<jsp:include page="../user/userMenu.jsp"></jsp:include>
</head>

<body>
	<center>
		<div style="text-align: center; width:1000px; height:600px;	background: white;
	border-radius: 10px; box-shadow: 0 2px 10px #999;  margin:2% 0 0 0; padding-top: 5px;">
		  <form onsubmit="return checksearch();" action="SectionServlet?action=select"
				method="post">
				<input type="text" name="select" id="selectId" value="${select }"
					style="width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999; border-radius: 4px;"
					placeholder="查询的条件" />
				<input type="hidden" name="pageNo" value="${pageNo }">
				<input type="hidden" name="pageSize" value="${pageSize }">
				<input type="submit" value="查询"
					style="width: 100px; height: 30px; background-color: #37D890; color: white; border: 0; border-radius: 4px;" />
			</form>
		  <table align="center" width="70%" border="1">
			<tr style=" color:blue">
				<th >版块名</th>
				<th >创建者</th>
				<th >创建时间</th>
				<th >权限</th>
				<th >操作</th>
			</tr>
		<c:forEach var="item" items="${SectionList }" varStatus="i">
			<tr style=" color:red">
				<th >${item.sectionName }</th>
				<th >${item.createrName }</th>
				<th >${item.creatTime }</th>
				<th >${item.level }</th>
				<td ><a href="SectionServlet?action=update&sectionId=${item.id }">更改</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href = "SectionServlet?action=delete&sectionId=${item.id }" onClick="return confirm('确定删除?');">删除</a></td>		
			</tr>
		</c:forEach>
		  </table>
		
			<a href="SectionServlet?action=select&pageNo=1&pageSize=${pageSize}&select=${select}">首页</a>
		<c:if test="${pageNo>1 }">
			<a href="SectionServlet?action=select&pageNo=${pageNo-1 }&pageSize=${pageSize}&select=${select}">上一页</a>
		</c:if>
		<c:if test="${pageNo<totalPage }">
			<a href="SectionServlet?action=select&pageNo=${pageNo+1 }&pageSize=${pageSize}&select=${select}">下一页</a>
		</c:if>
			<a href="SectionServlet?action=select&pageNo=${totalPage}&pageSize=${pageSize}&select=${select}">末页</a>
		<form onsubmit="return check();" action="SectionServlet?action=select&change=0" method="post">
			<h5 align="center">
				设置每页&nbsp;
				<input id="pageSize" type="text" value="${pageSize}" name="pageSize" size="1">
				&nbsp;条信息&nbsp;&nbsp;&nbsp;
				共${totalPage}页
				<input type="text" value="${pageNo}" name="pageNo" id="pageNo" size="1">
				<input type="hidden" value="${select}" name="select">
				<input type="submit" value="跳转">
			</h5>
		</form>
			</div>
	</center>
</body>
</html>
