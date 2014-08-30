<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加地区</title>
    
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
    	 <form id="login" action="../JunKeting/System/updateArea.action"
		method="post" enctype="multipart/form-data">
    <table align="center">
    	<tr>
    		<td>
    			地区图片：
    			<input type="hidden" name="id" value="${area.id}">
    		</td>
    		<td>
    			<input type="file" name="picture" id="aa"/>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			地区名称：
    		</td>
    		<td>
    			<input type="text" name="areaName" id="aa" value="${area.areaName}"/>
    		</td>
    	</tr>
    	<tr align="center"><td colspan="2"><input type="submit" value="确定"/></td></tr>
    </table>
    </form>
  </body>
</html>
