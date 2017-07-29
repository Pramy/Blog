<%@page import="com.pramy.model.MyFile"%>
<%@page import="com.pramy.model.Role"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%List<MyFile> list =(List<MyFile>)request.getAttribute("MyFileList") ;

%>
<script type="text/javascript" src="./Js/partern.js"></script>
<title>文件下载</title>
<link rel="stylesheet" href="./Js/css.css" type="text/css" />
<style type="text/css">    
 body{    
      background-image: url(images/loginBackground.jpg);    
      background-repeat: no-repeat;
      background-size: cover;    
 }</style>
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
</head>

<body >
	<center>
	<div style="text-align: center;float:right; width:150px; height:75px;	background: white; box-shadow: 0 2px 10px #999;  margin-right:5%;">	
	<a class="mya" style="width:150px; height: 75px; float:right; line-height:75px;text-decoration:none;" href="./section/SectionServlet" >返回首页</a>
	</div>
	<div style="text-align: center; width:1000px; height:800px;	background: white;
	border-radius: 10px; box-shadow: 0 2px 10px #999;  margin:2% 0 0 0; padding-top: 5px;">
				<form onsubmit="return checksearch();" action="FileServlet?action=select"
				method="post">
				<input type="text" name="select" id="selectId" value="${select }"
					style="width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999; border-radius: 4px;"
					placeholder="查询的条件" />
				<input type="hidden" name="pageNo" value="${pageNo }">
				<input type="hidden" name="pageSize" value="${pageSize }">
				<input type="submit" value="查询"
					style="width: 100px; height: 30px; background-color: #37D890; color: white; border: 0; border-radius: 4px;" />
			</form>
		<form name="myform1" method="post" action="FileServlet?action=upload" enctype="multipart/form-data">
		<input type="file" name="file" >
			<input type="hidden" name="type" value="file">
			<input type="hidden" value="${pageNo}" name="pageNo" >
			<input type="hidden" value="${pageSize}" name="pageSize" >
			<input type="hidden" value="${select}" name="select">
			<input type="hidden" value="file" name="mytype">
			<input type="submit" value="上传">
		</form>
			<table align="center" width="70%" border="1">
			<tr style=" color:blue">
				<th >文件名</th>
				<th >类型</th>
				<th >上传者</th>
				<th >创建时间</th>
				<th >操作</th>
			</tr>
		<%for(MyFile file :list){%>	
			<tr style=" color:red">
				<th ><%=file.getFileName().substring((file.getFileName().indexOf("_"))+1)%></th>
				<th ><%=file.getFileType()%></th>
				<th ><%=file.getUserName()%></th>
				<th ><%=file.getCreatTime()%></th>
				<td ><form action="FileServlet?action=down" method="post">
					<input type="hidden" name="url" value="<%=file.getFileUrl()%>">
					<input type="hidden" name="fileName" value="<%=file.getFileName()%>">
					<input type="submit" value="下载">
				</form>
			</tr>
			<%}%>
		  </table>
			<a href="FileServlet?action=select&pageNo=1&pageSize=${pageSize}&select=${select}">首页</a>
		<c:if test="${pageNo>1 }">
			<a href="FileServlet?action=select&pageNo=${pageNo-1 }&pageSize=${pageSize}&select=${select}">上一页</a>
		</c:if>
		<c:if test="${pageNo<totalPage }">
			<a href="FileServlet?action=select&pageNo=${pageNo+1 }&pageSize=${pageSize}&select=${select}">下一页</a>
		</c:if>
			<a href="FileServlet?action=select&pageNo=${total}&pageSize=${pageSize}&select=${select}">末页</a>
		<form onsubmit="return check();" action="FileServlet?action=select&change=0" method="post">
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
