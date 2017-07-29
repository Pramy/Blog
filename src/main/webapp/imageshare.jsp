<%@page import="com.pramy.model.Role"%>
<%@page import="com.pramy.model.MyFile"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="./Js/css.css" type="text/css" />
<title>图片分享区</title>
<style type="text/css">
body {
	background-image: url(images/loginBackground.jpg);
	background-repeat: no-repeat;
	background-size: cover;
}
</style>
<script type="text/javascript" src="./Js/partern.js"></script>
<script type="text/javascript">
function check(){
	var pageNo = document.getElementById("pageNo").value;
	var pageSize = document.getElementById("pageSize").value;
	if(!isDigit(pageNo) || pageNo==0 || !isDigit(pageSize) || pageSize==0){
		alert("请输正确页数！");
		return false;
	}
	if(pageNo >${totalPage}){
		alert("最多只有"+${totalPage}+" 页");
		return false;
	}
	return ture;
}
function checksearch(){
	var name =document.getElementById("selectId").value;
	if(name==""){
		return true;
	}
	if(!isLegal(name)){
		alert("请输入合法字符");
		return false;
	}
	return true;
}

</script>

</head>

<body>
	<div style="text-align: center;float:right; width:150px; height:75px;	background: white; box-shadow: 0 2px 10px #999;  margin-right:5%;">	
	<a class="mya" style="width:150px; height: 75px; float:right; line-height:75px;text-decoration:none;" href="./section/SectionServlet" >返回首页</a>
	</div>
	<center>
		<div style="text-align: center; width: 1000px; height: 1000px; background: white; border-radius: 10px; box-shadow: 0 2px 10px #999; margin: 2% 0 0 0; padding-top: 5px;">
			<form style="display:inline-block;margin-top:20px;" onsubmit="return checksearch();" action="ImageServlet?action=select"
				method="post">
				<input type="text" name="select" id="selectId" value="${select }"
					style="width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999; border-radius: 4px;"
					placeholder="查询的条件" />
				<input type="hidden" name="pageNo" value="${pageNo }">
				<input type="hidden" name="pageSize" value="${pageSize }">
				<input type="submit" value="查询"
					style="width: 100px; height: 30px; background-color: #37D890; color: white; border: 0; border-radius: 4px;" />
			</form>
			<form style="display:inline-block;margin-top:20px;" name="myform1" method="post" action="FileServlet?action=upload&cc=image" enctype="multipart/form-data">
				<input type="file" name="file" >
				<input type="hidden" value="${pageSize}" name="pageSize" >
				<input type="hidden" value="${pageNo}" name="pageNo" >
				<input type="hidden" value="${select}" name="select">
				<input type="hidden" name="mytype" value="image">
				<input type="submit" value="上传" style="width: 100px; height: 30px; background-color: #37D890; color: white; border: 0; border-radius: 4px;">
			</form><br>
			<c:forEach var="item" items="${imageList }" varStatus="i">
				<img alt="${item.fileName}" src="${item.fileUrl}${item.fileName}"  style="width:250px;height:150px;"/>
	<%-- 			<c:if test="${ i.index%5==1}">
					<br>
				</c:if> --%>
			</c:forEach>
			<br>
			<a href="ImageServlet?action=select&pageNo=1&pageSize=${pageSize}&select=${select}">首页</a>
			<c:if test="${pageNo>1 }">
				<a
					href="ImageServlet?action=select&pageNo=${pageNo-1 }&pageSize=${pageSize}&select=${select}">上一页</a>
			</c:if>
			<c:if test="${pageNo<totalPage }">
				<a
					href="ImageServlet?action=select&pageNo=${pageNo+1 }&pageSize=${pageSize}&select=${select}">下一页</a>
			</c:if>
			<a
				href="ImageServlet?action=select&pageNo=${totalPage}&pageSize=${pageSize}&select=${select}">末页</a>
			<form id="2" name="form2" onsubmit="return check();" action="ImageServlet?action=select"
				method="post">
				<h5 align="center">
					设置每页&nbsp; 
					<input id="pageSize" type="text" value="${pageSize}" name="pageSize" id="pageSize" size="1"> &nbsp;条信息&nbsp;&nbsp;&nbsp;
					共${totalPage}页 
					 <input type="text" value="${pageNo}" name="pageNo" id="pageNo" size="1">
					 <input type="hidden" name="select" value="${select }">
					 <input type="submit" value="跳转" style="width: 50px; height: 30px; background-color: #37D890; color: white; border: 0; border-radius: 4px;">
				</h5>
			</form>
		</div>
	</center>
</body>
</html>
