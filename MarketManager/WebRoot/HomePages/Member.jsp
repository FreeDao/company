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
    
    <title>会员首页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${pageContext.request.contextPath}/css/center.css" type="text/css" rel="stylesheet" />
  </head>
   <%@include file="common.jsp" %>
  <body>
  
  	<div class="alert alert-info" >
  		<div >
  		<div style="float: right;">${model.userName},欢迎回来!</div>
  		<div ><i class="icon-user"></i>会员中心</div>
  		</div>
</div>
  
  <table class="table table-bordered">
  	<thead>
  		<tr style="background-color: #f2f2f2;">
  			<th colspan="4">
  				账户信息
  				<a href="../MarketManager/System/updateMe.action" >修改基本信息</a>
  				<a href="../MarketManager/System/updatePassPage.action" >修改密码</a>
  			</th>
  		</tr>
  	</thead>
  	<tbody>
  		<tr>
  			<td width="15%">会员账号</td>
  			<td width="35%">${model.userName} 
  			</td>
  		</tr>
  		<tr>
  			<td>电话</td>
  			<td >${model.telePhone}</td>
  		</tr>
  		<tr>
  			<td width="15%">qq</td>
  			<td width="35%">${model.qq}	</td>
  		</tr>
  		<tr>
  			<td>Email</td>
  			<td>${model.email}</td>	
  		</tr>
  	</tbody>
  </table>
  </body>
</html>
