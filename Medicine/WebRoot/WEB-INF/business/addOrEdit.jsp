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
	<form id="edit-profile" class="form-horizontal" enctype="multipart/form-data" action="bg/business!addOrEdit" method="post" target="mainFrame" >
		<div style="height:400px;overflow:  auto;padding-top: 10px;">
		<fieldset>
			<input type="hidden" name="business.id" value="${business.id }"/>
			
			<div class="control-group">											
				<label class="control-label" for="title">标题</label>
				<div class="controls">
					<input type="text" class="input-medium" name="business.title" id="title" value="${business.title }"/>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="quotation">引文</label>
				<div class="controls">
					<textarea name="business.quotation" id="quotation" >${business.quotation }</textarea>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="ct">内容</label>
				<div class="controls">
					<textarea name="business.content" id="ct">${business.content }</textarea>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="addr">地址</label>
				<div class="controls">
					<textarea name="business.addr" id="addr">${business.addr }</textarea>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="name">名字</label>
				<div class="controls">
					<textarea name="business.name" id="name">${business.name }</textarea>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="tel">电话</label>
				<div class="controls">
					<textarea name="business.tel" id="tel">${business.tel }</textarea>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="type">类型</label>
				<div class="controls">
					<select name="business.type" id="type">
					<c:forEach items="${types }" var="t">
						<c:if test="${t.id == business.type }"><option value="${t.id }" selected="selected">${t.name }</option></c:if>
						<c:if test="${t.id != business.type }"><option value="${t.id }" >${t.name }</option></c:if>
					</c:forEach>
					</select>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<c:if test="${business.logo != null && business.logo != '' }">
				<div class="control-group">											
					<label class="control-label" >历史图片</label>
					<div class="controls">
						<img alt="" src="${business.logo }" width="80px" height="80px" class="img-polaroid">
					</div> <!-- /controls -->				
				</div> <!-- /control-group -->
			</c:if>
			
			<div class="control-group">											
				<label class="control-label" for="file">请选择上传图片</label>
				<div class="controls">
					<input type="file" class="input-large" name="file"  />
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
				
		</fieldset>
		</div>
		<div class="form-actions" style="margin-top: 0px;height: 10px;">
			<button type="button" class="btn btn-primary" onclick="check('title','标题不能为空','ct','内容不能为空');">保存</button> 
		</div> <!-- /form-actions -->
	</form>
  </body>
</html>
