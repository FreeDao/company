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
	<form id="edit-profile" class="form-horizontal" enctype="multipart/form-data" action="bg/invitation!addOrEdit" method="post" target="mainFrame" >
		<fieldset>
			<input type="hidden" name="invitation.id" value="${invitation.id }"/>
			
			<div class="control-group">											
				<label class="control-label" for="title">标题</label>
				<div class="controls">
					<input type="text" class="input-medium" name="invitation.title" id="title" value="${invitation.title }"/>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="sign">标签</label>
				<div class="controls">
					<input type="text" class="input-medium" name="invitation.sign" id="sign" value="${invitation.sign }"/>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="source">来源</label>
				<div class="controls">
					<input type="text" class="input-medium" name="invitation.source" id="source" value="${invitation.source }"/>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="type">类型</label>
				<div class="controls" >
					<select name="invitation.type" id="type">
						<c:if test="${invitation.type == 0 }"><option value="0" selected="selected">招标采购</option></c:if>
						<c:if test="${invitation.type != 0 }"><option value="0" >招标采购</option></c:if>
						<c:if test="${invitation.type == 1 }"><option value="1" selected="selected">更正通告</option></c:if>
						<c:if test="${invitation.type != 1 }"><option value="1" >更正通告</option></c:if>
						<c:if test="${invitation.type == 2 }"><option value="2" selected="selected">中标结果</option></c:if>
						<c:if test="${invitation.type != 2 }"><option value="2" >中标结果</option></c:if>
						
						
					</select>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="ct">内容</label>
				<div class="controls">
					<textarea name="invitation.content" id="ct">${invitation.content }</textarea>
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
