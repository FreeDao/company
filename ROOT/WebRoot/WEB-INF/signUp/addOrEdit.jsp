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
	<form id="edit-profile" class="form-horizontal" enctype="multipart/form-data" action="bg/news!addOrEdit" method="post" >
			<input name="news.top" value="0" type="hidden"/>
		<fieldset>
			<input type="hidden" name="news.id" value="${news.id }"/>
			
			<div class="control-group">											
				<label class="control-label" for="title">标题</label>
				<div class="controls">
					<input type="text" class="input-medium" name="news.title" id="title" value="${news.title }"/>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="quotation">引文</label>
				<div class="controls">
					<textarea name="news.quotation" id="quotation" >${news.quotation }</textarea>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="ct">内容</label>
				<div class="controls">
					<textarea name="news.content" id="ct">${news.content }</textarea>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<c:if test="${news.logo != null && news.logo != '' }">
				<div class="control-group">											
					<label class="control-label" >历史图片</label>
					<div class="controls">
						<img alt="" src="${news.logo }" width="80px" height="80px" class="img-polaroid">
					</div> <!-- /controls -->				
				</div> <!-- /control-group -->
			</c:if>
			
			<div class="control-group">											
				<label class="control-label" for="file">请选择上传图片</label>
				<div class="controls">
					<input id="fileUpload" type="file" class="input-large" name="files" />
					<%--<span style="color: red;">*可上传多张图片，第一张默认为logo,其他为内容展示图片</span>
				--%></div> <!-- /controls -->				
			</div> <!-- /control-group -->
				
			<%--<div class="form-actions" style="height: 10px;">
				<button type="button" class="btn btn-primary" onclick="check('title','标题不能为空','ct','内容不能为空');">保存</button> 
			</div> <!-- /form-actions -->
		--%></fieldset>
		
	</form>
	
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/fileupload/jquery.form.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/fileupload/jquery.MultiFile.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/fileupload/jquery.MultiFile.pack.js"></script>
	<script type="text/javascript">
	 $(function(){ // wait for document to load 
       $('#fileUpload').MultiFile({ 
        STRING: {
         remove: '<img class="icon-remove" height="16" width="16" alt="x"/>'
        }
       }); 
      });
	</script>
  --%></body>
</html>
