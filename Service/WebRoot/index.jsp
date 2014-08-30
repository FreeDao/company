<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<%@include file="common.jsp" %>
	<link href="${pageContext.request.contextPath}/css/pages/login.css" rel="stylesheet" /> 
     
	
  </head>
  
  <body>

<div class="navbar navbar-fixed-top">
	
	<div class="navbar-inner">
		
		<div class="container">
			
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 				
			</a>
			
			<a class="brand" href="./">后台管理系统</a>
			
			<div class="nav-collapse">
			
				<ul class="nav pull-right">
					
					<li class="">
						
						<a href="javascript:;"><i class="icon-chevron-left"></i>首页</a>
					</li>
				</ul>
				
			</div> <!-- /nav-collapse -->
			
		</div> <!-- /container -->
		
	</div> <!-- /navbar-inner -->
	
</div> <!-- /navbar -->

<br/>
<div id="login-container">
	
	
	<div id="login-header">
		
		<h3>中国服务业系统</h3>
		
	</div> <!-- /login-header -->
	<span style="color: red;">${msg }</span>
	<div id="login-content" class="clearfix">
	
	<form action="bg/user!login" method="post">
				<fieldset>
					<div class="control-group">
						<label class="control-label" for="username">账号</label>
						<div class="controls">
							<input type="text" class="" id="username" name="user.userName" value="${user.userName }" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="password">密码</label>
						<div class="controls">
							<input name="user.passWord" type="password" type="password" class="" id="password" />
						</div>
					</div>
				</fieldset>
				
				<div id="remember-me" class="pull-left">
					<input type="checkbox" name="remember" id="remember" />
					<label id="remember-label" for="remember">记住账号</label>
				</div>
				
				<div class="pull-right">
					<button type="submit" class="btn btn-warning btn-large">
						登陆
					</button>
				</div>
			</form>
			
		</div> <!-- /login-content -->
		
		
	
</div> <!-- /login-wrapper -->


<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<script type="text/javascript">
$(document).ready(function(){
jQuery.getScript("http://api.pymob.cn:8091/cityLife/dataExchange/getSellerList.action?&pageNo=1&pageNum=15&sellerId=-1&cityId=18&log=-180.000000&dim=-180.000000&productId=2", 
        function(){
        	alert(responseCode);            
        });     
});
</script>

  </body>
</html>
