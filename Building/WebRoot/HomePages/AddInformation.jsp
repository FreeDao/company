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
    <title>添加资讯</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
    <form id="login" action="System/addInformation.action" method="post" enctype="multipart/form-data">
    <table align="center">
    	<tr>
    		<td align="right" width="50px">标题:</td>
    		<td width = "400px">
    			<input type="text" name=title id="title"/>
    		</td>
    	</tr>
    	<tr>
    		<td align="right">内容:</td>
    		<td>
    			<input type="text" name="content" id="content"/>
    		</td>
    	</tr>
    	<tr>
    		<td align="right">图片:</td>
    		<td>
    			<input type="file" name="picture" id="imgUrl"/>
    		</td>
    	</tr>
    	<tr>
    		<td align="right">摘要:</td>
    		<td>
    			<input type="text" name="abstractInfo" id="abstractInfo"/>
    		</td>
    	</tr>
    	<tr align="center">
    		<td colspan="2">
    			<input type="submit" value="添加"/>
    		</td>
    	</tr>
    </table>
    </form>
  </body>
</html>
