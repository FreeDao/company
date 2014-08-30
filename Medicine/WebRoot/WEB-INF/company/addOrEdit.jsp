<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
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
  <br/>
	<form id="edit-profile" class="form-horizontal" enctype="multipart/form-data" action="bg/company!addOrEdit" method="post" target="mainFrame" >
		<fieldset>
			<input type="hidden" name="company.id" value="${company.id }"/>
			
			<div class="control-group">											
				<label class="control-label" for="title">标题</label>
				<div class="controls">
					<input type="text" class="input-medium" name="company.title" id="title" value="${company.title }"/>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="quotation">引文</label>
				<div class="controls">
					<textarea name="company.quotation" id="quotation" >${company.quotation }</textarea>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="ct">内容</label>
				<div class="controls">
					<textarea name="company.content" id="ct">${company.content }</textarea>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="source">来源</label>
				<div class="controls">
					<textarea name="company.source" id="source">${company.source }</textarea>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="form-actions" style="height: 10px;">
				<button type="button" class="btn btn-primary" onclick="check('title','标题不能为空','ct','内容不能为空');">保存</button> 
			</div> <!-- /form-actions -->
		</fieldset>
		
	</form>
  </body>
</html>
