<%@page import="com.pramy.model.Role"%>
<%@page import="com.pramy.model.Section"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>用户信息</title>
<style type="text/css">    
 body{    
      background-image: url(../images/loginBackground.jpg);
      background-repeat: no-repeat;
      background-size: cover;    
 }</style>
  <script type="text/javascript">

 	function check(){
 		var email = document.getElementsByName("email")[0].value;
 		var question = document.getElementsByName("question")[0].value;
 		var answer = document.getElementsByName("answer")[0].value;
 		var sex = document.getElementsByName("sex");
 		var level = document.getElementsByName("level");
 		if (email.length<1) {
 			alert("邮箱不能为空");
 			return false;
 		}
 		if (question.length<1) {
 			alert("安全问题不能为空");
 			return false;
 		}
 		if (answer.length<1) {
 			alert("答案不能为空");
 			return false;
 		}
 		if (!sex[0].checked && !sex[1].checked) {
 			alert("请选择性别");
 			return false;
 		}
 		if (!level[0].checked && !level[1].checked) {
 			alert("请选择登录权限");
 			return false;
 		}
 		return true;
 	}
 function init(arg){
	 var limit = document.getElementsByName("isLimit");
	 var sex = document.getElementsByName("sex");
	if("${user.isLimit}"=="Yes"){
		limit[1].checked=true;
	}else{
		limit[0].checked=true;
	}
	if("${user.sex}"=="男"){
		sex[0].checked=true;
	}else{
		sex[1].checked=true;
	}
 }
 </script>
<% Role role = (Role)session.getAttribute("role");
if(role.getRoleName().equals("超级版主")){
%>
<jsp:include page="../section/sectionMenu.jsp"></jsp:include>
<%}%>
<jsp:include page="userMenu.jsp"></jsp:include>
</head>

<body onload="init('Yes')">
	<center>
		<div style="text-align: center; width:550px; height:500px;	background: white;
	border-radius: 10px; box-shadow: 0 2px 10px #999;  margin:2% 0 0 0; padding-top: 50px;">
			<form onsubmit="return check();" action=UserServlet?action=update  method="post" >
			<span style="color:red">用&ensp;户&ensp;名：<input type="text" readonly="readonly" name="userName" id="userNameId" value="${user.userName }"style="width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999;border-radius: 4px;color:red;" /></span><br>
			邮&emsp;&emsp;箱：<input type="text" name="email" id="emailId" value="${user.email }"style="width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999;border-radius: 4px;" /><br>
			<span style="width:250px;padding-right:117px;">性&emsp;&emsp;别：<input type = "radio" name = "sex" value="男">男
				<input type = "radio" name = "sex" value="女">女</span>
				<br><br>
			安全问题：<input type="text" name="question" id="questionId" value="${user.question }" style="width: 200px; height: 30px; margin-bottom: 16px;padding-left: 3px; border: 1px solid #999;border-radius: 4px;"  /><br>
			答&emsp;&emsp;案：<input type="text" name="answer" id="answerId" value="${user.answer }" style="width: 200px; height: 30px; margin-bottom: 16px;padding-left: 3px; border: 1px solid #999;border-radius: 4px;"  /><br>
			<span style="color:red">创建时间：<input type="text" readonly="readonly" name="creatTime" id="creatTimeId" value="${user.creatTime }"style="color:red;width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999;border-radius: 4px;" /></span><br>
			<span style="color:red">最后登陆：<input type="text" readonly="readonly" name="lastLoginTime" id="lastLoginTimeId" value="${user.lastLoginTime }"style="color:red;width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999;border-radius: 4px;" /></span><br>
			<c:choose>
				<c:when test="${role.roleName ne '普通用户'}">
			权&emsp;&emsp;限：<input type="text"  name="level" id="levelId" value="${user.level }"style="width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999;border-radius: 4px;" /><br>
			<span style="width:250px;padding-right:20px;">登录限制：<input type = "radio" name = "isLimit" value="No">允许登录&nbsp;
					<input type = "radio" name = "isLimit" value="Yes">禁止登录</span><br>
				</c:when>
				<c:otherwise>
			<span style="color:red">权&emsp;&emsp;限：<input type="text" readonly="readonly" name="level" id="levelId" value="${user.level }"style="color:red;width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999;border-radius: 4px;" /><br></span>
			<span style="color:red">登录限制：<input type="text" readonly="readonly" name="isLimit" id="isLimitId" value="${user.isLimit }"style="color:red;width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999;border-radius: 4px;" /><br></span>				
				</c:otherwise>
			</c:choose>
			<input type="hidden" name="id" value="${user.id }">
			<input type="submit"  value="修改" style="margin-top:30px;width: 200px; height: 30px; background-color: #37D890; color: white; border: 0; border-radius: 4px;" />
			</form>
	</div>
	</center>
</body>
</html>
