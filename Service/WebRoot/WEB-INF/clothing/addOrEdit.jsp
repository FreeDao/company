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
	<form id="edit-profile" class="form-horizontal" enctype="multipart/form-data" action="bg/clothing!addOrEdit" method="post" >
		<fieldset>
			<input type="hidden" name="clothing.id" value="${clothing.id }"/>
			
			<div class="control-group">											
				<label class="control-label" for="title">标题</label>
				<div class="controls">
					<input type="text" class="input-medium" name="clothing.title" id="title" value="${clothing.title }"/>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="quotation">引文</label>
				<div class="controls">
					<textarea name="clothing.quotation" id="quotation" >${clothing.quotation }</textarea>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="addr">地址</label>
				<div class="controls">
					<textarea name="clothing.addr" id="addr" >${clothing.addr }</textarea>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="tel">联系电话</label>
				<div class="controls">
					<textarea name="clothing.tel" id="tel" >${clothing.tel }</textarea>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="level">星级</label>
				<div class="controls">
					<select name="clothing.level">
						<c:choose>
							<c:when test="${clothing.level == 1}">
								<option value="1" selected="selected">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</c:when>
							<c:when test="${clothing.level == 2}">
								<option value="1">1</option>
								<option value="2" selected="selected">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</c:when>
							<c:when test="${clothing.level == 3}">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3" selected="selected">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</c:when>
							<c:when test="${clothing.level == 4}">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4" selected="selected">4</option>
								<option value="5">5</option>
							</c:when>
							<c:when test="${clothing.level == 5}">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5" selected="selected">5</option>
							</c:when>
							<c:otherwise>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</c:otherwise>
						</c:choose>
					</select>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="ct">内容</label>
				<div class="controls">
					<textarea name="clothing.content" id="ct">${clothing.content }</textarea>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<c:if test="${clothing.logo != null && clothing.logo != '' }">
				<div class="control-group">											
					<label class="control-label" >历史图片</label>
					<div class="controls">
						<img alt="" src="${clothing.logo }" width="80px" height="80px" class="img-polaroid">
					</div> <!-- /controls -->				
				</div> <!-- /control-group -->
			</c:if>
			
			<div class="control-group">											
				<label class="control-label" for="file">请选择上传图片</label>
				<div class="controls">
					<input id="fileUpload" type="file" class="input-large" name="files"  />
					<span style="color: red;">*可上传多张图片，第一张默认为logo,其他为内容展示图片</span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
				
			<%--<div class="form-actions" style="height: 10px;">
				<button type="button" class="btn btn-primary" onclick="check('title','标题不能为空','ct','内容不能为空');">保存</button> 
			</div> <!-- /form-actions -->
		--%></fieldset>
		
	</form>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/js/fileupload/jquery.form.js"></script>
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
  </body>
</html>
