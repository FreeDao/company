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

	<form id="edit-profile" class="form-horizontal" enctype="multipart/form-data" action="bg/recruit!addOrEdit" method="post" target="mainFrame" >
		 <div style="height:400px;overflow:  auto;padding-top: 10px;">
		<fieldset>
			<input type="hidden" name="recruit.id" value="${recruit.id }"/>
			
			<div class="control-group">											
				<label class="control-label" for="companyName">公司名字</label>
				<div class="controls">
					<input type="text" class="input-medium" name="recruit.companyName" id="companyName" value="${recruit.companyName }" />
					<span class="help-inline"></span>
				</div> <!-- /controls -->			
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="position" >职位</label>
				<div class="controls">
					<input type="text" class="input-medium" name="recruit.position" id="position" value="${recruit.position }"/>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="city">待遇</label>
				<div class="controls">
					<input type="text" class="input-medium" name="recruit.salary" id="salary" value="${recruit.salary }"/>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="degree">学位要求</label>
				<div class="controls">
					<input type="text" class="input-medium" name="recruit.degree" id="degree" value="${recruit.degree }"/>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="addr">地址</label>
				<div class="controls">
					<input type="text" class="input-medium" name="recruit.addr" id="addr" value="${recruit.addr }"/>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<%--<div class="control-group">											
				<label class="control-label" for="endTime">结束时间</label>
				<div class="controls">
					<input type="text" class="input-medium" name="recruit.endTime" id="endTime" value="${recruit.endTime }"/>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			--%><div class="control-group">											
				<label class="control-label" for="workTime">工作年限</label>
				<div class="controls">
					<input type="text" class="input-medium" name="recruit.workTime" id="workTime" value="${recruit.workTime }"/>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="tel">联系电话</label>
				<div class="controls">
					<input type="text" class="input-medium" name="recruit.tel" id="tel" value="${recruit.tel }"/>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="positionBrief">职位简介</label>
				<div class="controls">
					<textarea name="recruit.positionBrief" id="positionBrief">${recruit.positionBrief }</textarea>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="companyBrief">公司简介</label>
				<div class="controls">
					<textarea name="recruit.companyBrief" id="companyBrief">${recruit.companyBrief }</textarea>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
		</fieldset>
		</div>
		<div class="form-actions" style="margin-top: 0px;height: 10px;">
				<button type="button" class="btn btn-primary" onclick="check('companyName','公司名字不能为空','position','职位不能为空');">保存</button> 
			</div> <!-- /form-actions -->
			
	</form>
	<script type="text/javascript">
	</script>
  </body>
</html>
