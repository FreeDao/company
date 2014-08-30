<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>添加区域</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
	table
	{
		width:300px;
		height:210px;
	}
	td
	{
		width:150px;
	}
	</style>
  </head>
  
  <body>
    <form id="login" action="System/addArea.action" method="post">
    <table align="center">
    	<tr>
    		<td align="right">地区名称:</td>
    		<td>
    			<input type="text" name="areaName" id="areaName" width="150px"/>
    		</td>
    	</tr>
    	<tr>
    		<td align="right">所属城市编号:</td>
    		<td>
    			<select name="city" style="width: 155px;">
    				<option value="0">请选择</option>
    				<s:iterator value="city_list" var="city">
    					<option value="${city.id}">${city.cityName}</option>
    				</s:iterator>
    			</select>
    		</td>
    	</tr>
    	<tr>
    		<td colspan="2" align="center">
    			<label id="areamessage" style="color: red;"></label>
    		</td>
    	</tr>
    	<tr align="center">
    		<td colspan="2">
    			<input type="button" value="确定" onclick="login()" id="ti"/>
    		</td>
    	</tr>
    </table>
    </form>
  </body>
  <script type="text/javascript">
  		function login()
  		{
  			var areaName = document.getElementById("areaName").value;
  			if(areaName == "")
  			{
  				document.getElementById("areamessage").innerHTML="地区名称为空";
  			}
  			else
  			{
  				document.getElementById("areamessage").innerText="";
  				document.getElementById("ti").type="submit";
  			}
  		}
  </script>
</html>
