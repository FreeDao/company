<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加城市</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	table{
		width:300px;
		height:210px;
		}
	</style>
  </head>
  
  <body>
    <form id="login" name="" action="../City/System/addCity.action"
		method="post">
    <table align="center">
    	<tr>
    		<td>城市名：</td>
    		<td><input type="text" name="city" id="adminid"/></td>
    	</tr>
    	<tr align="center"><td colspan="2"><input type="submit" value="确定" onclick="return yz();"/></td></tr>
    </table>
    </form>
    <script type="text/javascript">
    	function yz()
    	{
    		var id = document.getElementById("adminid").value;
    		if(id == "")
    		{
    			alert("请输入城市名！");
    			return false;
    		}
    		if(id.length>20)
    		{
    			alert("您输入的角色名过长！");
    			return false;
    		}    	
    		
    	}
    
    </script>
  </body>
</html>
