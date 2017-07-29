<%@page import="com.pramy.model.Role"%>
<%@page import="com.pramy.model.Section"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

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
<script type="text/javascript" src="../Js/partern.js"></script>
<script type="text/javascript">
function check(){
	var name =document.getElementById("sectionNameId").value;
	var level =document.getElementById("levelId").value;
	if(!isLegal(name)){
		alert("请输入合法字符");
		return false;
	}
	if(!isDigit(level)){
		alert("请输入小于20位数");
		return false;
	}
	return true;
}

</script>
<% Section section =(Section) session.getAttribute("sectionUpdate");
Role role = (Role)session.getAttribute("role");
if(role.getRoleName().equals("超级版主")){
%>
<jsp:include page="../section/sectionMenu.jsp"></jsp:include>
<%}%>
<jsp:include page="../user/userMenu.jsp"></jsp:include>
</head>

<body>
	<center>
		<div style="text-align: center; width:1000px; height:600px;	background: white;
	border-radius: 10px; box-shadow: 0 2px 10px #999;  margin:2% 0 0 0; padding-top: 5px;">
			<form onsubmit="return check();" action=SectionServlet?action=update  method="post" >
			版块名称：<input type="text" name="sectionName" id="sectionNameId" value="<%=section.getSectionName()%>"style="width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999;border-radius: 4px;"placeholder="版块名称" /><br>
			创建人：<input type="text" disabled="disabled" value="<%=section.getCreaterName()%>"style="width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999;border-radius: 4px;" /><br>
			创建时间：<input type="text" disabled="disabled" value="<%=section.getCreatTime()%>"style="width: 200px; height: 30px; margin-bottom: 10px; padding-left: 3px; border: 1px solid #999;border-radius: 4px;" /><br>
			权限：<input type="text" name="level" id="levelId" value="<%=section.getLevel() %>" style="width: 200px; height: 30px; margin-bottom: 16px;padding-left: 3px; border: 1px solid #999;border-radius: 4px;" placeholder="权限" /><br>
			<input type="hidden" name="sectionId" value="<%=section.getId()%>">
			<input type="submit" onClick="return confirm('确定修改?');" value="修改" style="width: 200px; height: 30px; background-color: #37D890; color: white; border: 0; border-radius: 4px;" />
			</form>
			</div>
	</center>
</body>
</html>
