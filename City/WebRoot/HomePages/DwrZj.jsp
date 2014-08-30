<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<script src='dwr/interface/userService.js'></script>
    <script src='dwr/engine.js'></script>
    <script src='dwr/util.js'></script>
    <script type="text/javascript">
    	 function findCus()
    	 {
       			userService.findAll(showCus);
     	 }
    
    </script>
  <head>
    <base href="<%=basePath%>">
    
    <title>抽奖中奖</title>
    
    
  
    
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
  					
    				
  </body>
</html>
