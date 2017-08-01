<%@page import="com.pramy.model.Role"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
 <script type="text/javascript">
 function change(){
	 var box=document.getElementById("checkboxId");
	 if(box.checked){
		 box.value="Yes";
	 }else{
		 box.value="";
	 }
 }
 function init(){
	 var box=document.getElementById("checkboxId");
	 if(${not empty isLimit} | box.value=="Yes"){
		 box.checked=true;
	 } else{
		 box.checked=false;
	 }
 }
 function check() {
	 var pageNo = document.getElementById("pageNo").value;
	 if(pageNo>${totalPage}){
	     alert('不能比当前总页数大');
	     return false;
     }
     return true;
 }
 </script>
<% Role role = (Role)session.getAttribute("role");
if(role.getRoleName().equals("超级版主")){
%>
<jsp:include page="../section/sectionMenu.jsp"></jsp:include>
<%}%>
<jsp:include page="userMenu.jsp"></jsp:include>
</head>

<body onload="init()">
	<center>
	<div style="text-align: center; width:1000px; height:1000px;	background: white;
	border-radius: 10px; box-shadow: 0 2px 10px #999;  margin:2% 0 0 0; padding-top: 5px;">
		<c:if test="${role.roleName eq '普通用户' }">
		<jsp:forward page="userUpdate.jsp"></jsp:forward>
		</c:if>
			<form onsubmit="return checksearch();" action="UserServlet?action=select"
				method="post">
				<input type="text" name="select" id="selectId" value="${select }"
					style="width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999; border-radius: 4px;"
					placeholder="查询的条件" />
				<input type="hidden" name="pageNo" value="${pageNo }">
				<input type="hidden" name="pageSize" value="${pageSize }">
				<input type="submit" value="查询"
					style="width: 100px; height: 30px; background-color: #37D890; color: white; border: 0; border-radius: 4px;" />
				登录限制用户:<input type="checkbox" name="isLimit" id="checkboxId" value="${isLimit}" onclick="change();">
			</form>
		  <table align="center" width="70%" border="1">
			<tr style=" color:blue">
				<th >用户名</th>
				<th >性别</th>
				<th >最后登录时间</th>
				<th >创建时间</th>
				<th >登录受限</th>
				<th >操作</th>
			</tr>
		<c:forEach var="item" items="${UserList }" varStatus="i">
			<tr style=" color:red">
				<th >${item.userName }</th>
				<th >${item.sex }</th>
				<th >${item.lastLoginTime }</th>
				<th >${item.creatTime }</th>
				<td >${item.isLimit }</td>
				<td ><a href="UserServlet?action=selectOne&userName=${item.userName }">更改</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href = "UserServlet?action=delete&userName=${item.userName }" onClick="return confirm('确定删除?');">删除</a></td>		
			</tr>
		</c:forEach>
		  </table>
			<a href="UserServlet?action=select&pageNo=1&pageSize=${pageSize}&select=${select}">首页</a>
			<c:if test="${pageNo>1 }">
				<a
					href="UserServlet?action=select&pageNo=${pageNo-1 }&pageSize=${pageSize}&select=${select}">上一页</a>
			</c:if>
			<c:if test="${pageNo<totalPage }">
				<a
					href="UserServlet?action=select&pageNo=${pageNo+1 }&pageSize=${pageSize}&select=${select}">下一页</a>
			</c:if>
			<a
				href="UserServlet?action=select&pageNo=${totalPage}&pageSize=${pageSize}&select=${select}">末页</a>
		<form onsubmit="return check();" action="UserServlet?action=select" method="post">
			<h5 align="center">
				设置每页&nbsp;
				<input id="pageSize" type="text" value="${pageSize}" name="pageSize" size="1">
				&nbsp;条信息&nbsp;&nbsp;&nbsp;
				共${totalPage}页 
				<input id="pageNo" type="text" value="${pageNo}" name="pageNo" size="1">
				<input type="hidden" value="${select}" name="select">
				<input type="submit" value="跳转">
			</h5>
		</form>
			</div>
	</center>
</body>
</html>
