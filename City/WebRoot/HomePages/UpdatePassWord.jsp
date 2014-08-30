<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'AdminInfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <form id="form1" name="form1" action="../City/System/updatePassWord.action"
		method="post">
    <table align="center">
    	<tr>
    		<td>原始密码:</td>
    		<td><input type="password" name="opasswordjiu" id = "xmima"/> <input type="hidden" value="${sessionScope.userid}" name="id"></td>
    	</tr>
    	<tr>
    		<td>新&nbsp;密&nbsp;码:</td>
    		<td><input type="password" name="npassword" id = "jiumima"/></td>
    	</tr>
    	<tr>
    		<td>确认新密码:</td>
    		<td><input type="password" name="npasswordone" id = "jiumimaone"/></td>
    	</tr>
    	<tr align="center"><td colspan="2"><input type="submit" value="确定" onclick="return yanz()"/></td></tr>
    </table>
   </form>
  </body>
  <script type="text/javascript">
  	function yanz()
  	{
  		var xmima = document.getElementById("xmima").value;
  		var jiumima = document.getElementById("jiumima").value;
  		var jiumimaone = document.getElementById("jiumimaone").value;
  		if(xmima == "")
  		{
  			alert("请输入旧密码！");
  			return false;
  		}
  		if(jiumima == "")
  		{
  			alert("请输入新密码！");
  			return false;
  		}
  		if(jiumimaone == "")
  		{
  			alert("请输入确认新密码！");
  			return false;
  		}
  		if(jiumimaone != jiumima)
  		{
  			alert("两次新密码输入不一样请重新输入！");
  			return false;
  		}
  	}
  </script>
</html>
