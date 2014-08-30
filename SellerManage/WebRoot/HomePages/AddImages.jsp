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
    
    <title>添加图片</title>
    
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
    	 <form id="login" action="../SellerManage/System/addImagesPage.action"
		method="post" enctype="multipart/form-data">
    <table align="center">
    	<tr>
    	<td>商户名称</td>
    	<td>
    	<select name="compositeId">
    			<s:iterator value="listSeller" var="d">
    				<option value="${d.id}">${d.titile}</option>
    			</s:iterator>
    		</select>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			图片：
    		</td>
    		<td>
    			<input type="file" name="picture" id="aa"/>
    		</td>
    	</tr>
    	<tr align="center"><td colspan="2"><input type="submit" value="确定"/></td></tr>
    </table>
    </form>
  </body>
</html>
