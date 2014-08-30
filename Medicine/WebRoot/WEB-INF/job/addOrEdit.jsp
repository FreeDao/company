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

	<form id="edit-profile" class="form-horizontal" enctype="multipart/form-data" action="bg/job!addOrEdit" method="post" target="mainFrame" >
		 <div style="height:400px;overflow:  auto;padding-top: 10px;">
		<fieldset>
			<input type="hidden" name="job.id" value="${job.id }"/>
			
			<div class="control-group">											
				<label class="control-label" for="name">名字</label>
				<div class="controls">
					<input type="text" class="input-medium" name="job.name" id="name" value="${job.name }" />
					<span class="help-inline"></span>
				</div> <!-- /controls -->			
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="sex" >性别</label>
				<div class="controls">
					<input type="text" class="input-medium" name="job.sex" id="sex" value="${job.sex }"/>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="age" >年龄</label>
				<div class="controls">
					<input type="text" class="input-medium" name="job.age" id="age" value="${job.age }"/>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="majror" >专业</label>
				<div class="controls">
					<input type="text" class="input-medium" name="job.major" id="major" value="${job.major }"/>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="degree" >学历学位</label>
				<div class="controls">
					<input type="text" class="input-medium" name="job.degree" id="degree" value="${job.degree }"/>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="workTime" >工作年限</label>
				<div class="controls">
					<input type="text" class="input-medium" name="job.workTime" id="workTime" value="${job.workTime }"/>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="school" >毕业学校</label>
				<div class="controls">
					<input type="text" class="input-medium" name="job.school" id="school" value="${job.school }"/>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="profession" >现有职称</label>
				<div class="controls">
					<input type="text" class="input-medium" name="job.profession" id="profession" value="${job.profession }"/>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="graduateTime" >毕业时间</label>
				<div class="controls">
					<input type="text" class="input-medium" name="job.graduateTime" id="graduateTime" value="${job.graduateTime }"/>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="home" >家庭住址</label>
				<div class="controls">
					<input type="text" class="input-medium" name="job.home" id="home" value="${job.home }"/>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="tel" >联系电话</label>
				<div class="controls">
					<input type="text" class="input-medium" name="job.tel" id="tel" value="${job.tel }"/>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="email" >邮箱</label>
				<div class="controls">
					<input type="text" class="input-medium" name="job.email" id="email" value="${job.email }"/>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="position" >职位</label>
				<div class="controls">
					<input type="text" class="input-medium" name="job.position" id="position" value="${job.position }"/>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="workAddr" >期望工作地址</label>
				<div class="controls">
					<input type="text" class="input-medium" name="job.workAddr" id="workAddr" value="${job.workAddr }"/>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="salary" >期望工资</label>
				<div class="controls">
					<input type="text" class="input-medium" name="job.salary" id="salary" value="${job.salary }"/>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="workExp">工作经验</label>
				<div class="controls">
					<input type="text" class="input-medium" name="job.workExp" id="workExp" value="${job.workExp }"/>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="englishLevel">英语等级</label>
				<div class="controls">
					<input type="text" class="input-medium" name="job.englishLevel" id="englishLevel" value="${job.englishLevel }"/>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="computerLevel">计算机等级</label>
				<div class="controls">
					<input type="text" class="input-medium" name="job.computerLevel" id="computerLevel" value="${job.computerLevel }"/>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="education">教育背景</label>
				<div class="controls">
					<textarea name="job.education" id="education">${job.education }</textarea>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
			<div class="control-group">											
				<label class="control-label" for="brief">自我评价</label>
				<div class="controls">
					<textarea name="job.brief" id="brief">${job.brief }</textarea>
					<span class="help-inline"></span>
				</div> <!-- /controls -->				
			</div> <!-- /control-group -->
			
		</fieldset>
		</div>
		<div class="form-actions" style="margin-top: 0px;height: 10px;">
				<button type="button" class="btn btn-primary" onclick="check('name','名字不能为空','position','职位不能为空');">保存</button> 
			</div> <!-- /form-actions -->
			
	</form>
	<script type="text/javascript">
	</script>
  </body>
</html>
