<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>小区通管理平台</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- <link href="css/login.css" rel="stylesheet" type="text/css" /> -->
  <link href="book/css/default/easyui.css" rel="stylesheet" type="text/css" />
  <link href="book/css/icon.css" rel="stylesheet" type="text/css" /> 
  <script src="book/js/jquery-1.10.2.js" type="text/javascript"></script>
  <script src="book/js/jquery.easyui.min.js" type="text/javascript"></script>
  <script src="book/js/easyui-lang-zh_CN.js" type="text/javascript"></script>
  <script type="text/javascript" src="book/js/datagrid-detailview.js"></script>   
  </head>
<body>

	<div id="loginWindow" class="easyui-window" title="登陆窗口" iconCls="icon-login" style="width:300px;height:180px;padding:5px;background: #fafafa;">
	        <form action="../Backstage/System/adminLogin.action" id="submit_form" method="post">
	        <div border="false" style=" padding-left:30px; background:#fff;border:1px solid #ccc;">
	                <label>用户名：</label>
					<input type="text" size="20" class="login_input" required="true" validType="length[4,12]" missingMessage="用户名不能为空" name="name" />
					<label>密&nbsp;&nbsp;码：</label>
					<input type="password" size="20" class="login_input" required="true" validType="length[4,12]" missingMessage="密码不能为空" name="pwd" />
					<label>类&nbsp;&nbsp;型：</label>
						<select name="type">
							<option value="1">工作人员</option>
							<option value="2">城市管理人员</option>
							<option value="3">小区主</option>
							<option value="4">商家</option>
							<option value="5">物业</option>
						</select>
	        </div>
	        <div border="false" style="text-align:center;height:30px;line-height:30px; margin-top:6px;">
	            <!-- <a class="easyui-linkbutton" iconCls="icon-thereof" href="javascript:void(0)" id="aboutSys">关于</a> -->
	            <input id="sub" class="easyui-linkbutton" type="submit" value="登 陆" />
	           <!--  <a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" id="btnLogin">登陆</a> -->
	        </div>
	        </form>
	        <div id="error" align="center" style="color: red">${map.msg }</div>
	    </div>



	<!-- <div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<a href="#"><img src="images/login_logo.gif" /></a>
			</h1>
			<div class="login_headerContent">
				<div class="navList"></div>
				<h2 class="login_title"><img src="images/login_title.png" /></h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				
				
					<p>
						</p>
					<p>
					</p>
					<p>
						
					</p>
					<div class="login_bar">
						<input id="sub" class="sub" type="submit" value=" " />
					</div>
				
			</div>
			<div class="login_banner"><img src="images/login_banner.jpg" /></div>
			<div class="login_main">
				<ul class="helpList"></ul>
				<div class="login_inner"></div>
			</div>
		</div>
		<div id="login_footer">
			
		</div>
	</div> -->
</body>
</html>