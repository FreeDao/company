<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
    <meta
		content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"
		name="viewport" />
	<meta content="yes" name="apple-mobile-web-app-capable" />
	<meta content="black" name="apple-mobile-web-app-status-bar-style" />
	<meta content="telephone=no" name="format-detection" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<style type="text/css">
	body{
		margin: 0;
		padding: 0;		
		background-color: #eee;
	}
	
	section{
		width: 90%;
		margin: 0 5%;
	}
	
	.right{
		float:right;
		 
	}
	
	span,p{
		color: #888;
	}
	
	.content{
		font-size: 19px;
		color:#414141;
	}
	
	img{
		margin-top: 10px;
	}
	
	.digest{
		font-size: 17px;
		padding:10px 0;
		border-top:1px dashed #888; 
		border-bottom:1px dashed #888; 
	}
	</style>

  </head>
  
  <body>
    <section>
    <h1>${info.title}</h1>
    <span>${info.addTime}</span><span class="right" >来源：${info.source}</span>
    <p class="digest" >${info.digest }</p>
    <img src="${info.logo }" width="100%">
    <p class="content" >${info.content }</p>
    </section>  
    
  </body>
</html>
