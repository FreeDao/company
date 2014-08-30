<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<%@include file="../common.jsp" %>
    
     <script type="text/javascript"> 
  function dyniframesize(down) { 
	var pTar = null; 
	if (document.getElementById){ 
	pTar = document.getElementById(down); 
	} 
	else{ 
	eval('pTar = ' + down + ';'); 
	} 
	if (pTar && !window.opera){ 
	//begin resizing iframe 
	pTar.style.display="block";
	if (pTar.contentDocument && pTar.contentDocument.body.offsetHeight){ 
	//ns6 syntax 
	pTar.height = pTar.contentDocument.body.offsetHeight+30; 
	} 
	else if (pTar.Document && pTar.Document.body.scrollHeight){ 
	//ie5+ syntax 
	pTar.height = pTar.Document.body.scrollHeight+30; 
	} 
	} 
	} 
	
	function addClassActive(t){
		if("active" != $(t).attr("class")){
			$(".active").removeClass("active");
			$(t).addClass("active");
		}
		//
	}
	</script> 
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
			
			<a class="brand" >汽摩后台系统</a>
			
			<div class="nav-collapse">
			
				<ul class="nav pull-right">
					<li>
						<!-- <a href="#"><span class="badge badge-warning">7</span></a> -->
					</li>
					
					<li class="divider-vertical"></li>
					
					<li class="dropdown">
						
						<a data-toggle="dropdown" class="dropdown-toggle " href="#">
							${user.userName } <b class="caret"></b>							
						</a>
						
						<ul class="dropdown-menu">
							<!-- <li>
								<a href="./account.html"><i class="icon-user"></i> 账户设置 </a>
							</li>
							
							<li>
								<a href="./change_password.html"><i class="icon-lock"></i>修改密码</a>
							</li>
							
							<li class="divider"></li> -->
							
							<li>
								<a href="bg/user!logout"><i class="icon-off"></i> 注销</a>
							</li>
						</ul>
					</li>
				</ul>
				
			</div> <!-- /nav-collapse -->
			
		</div> <!-- /container -->
		
	</div> <!-- /navbar-inner -->
	
</div> <!-- /navbar -->

<div id="content">
	
	<div class="container">
		
		<div class="row">
		
			<div class="span3">
		
			<div class="account-container">
				
					<div class="account-avatar">
						<img src="./img/headshot.png" alt="" class="thumbnail" />
					</div> <!-- /account-avatar -->
				
					<div class="account-details">
					
						<span class="account-name">${user.userName }</span>
						
						<span class="account-role">管理员</span>
						
						<!-- <span class="account-actions">
							<a href="javascript:;">账户设置</a> |
							
							<a href="javascript:;">修改密码</a>
						</span> -->
					
					</div> <!-- /account-details -->
				
				</div> <!-- /account-container -->
				
				<hr />
				
				<ul id="main-nav" class="nav nav-tabs nav-stacked">
					
					<li class="active" onclick="addClassActive(this)">
						<a href="welcome.jsp" target="mainFrame">
							<i class="icon-home"></i>
							首页			
						</a>
					</li>
					
					<li class="" onclick="addClassActive(this)">
						<a href="bg/business!index" target="mainFrame">
							<i class="icon-pushpin"></i>
							医药商机信息管理
						</a>
					</li>
					
					<li onclick="addClassActive(this)">
						<a href="bg/company!index" target="mainFrame">
							<i class="icon-th-list"></i>
							特药企业管理	
						</a>
					</li>
					
					<li onclick="addClassActive(this)">
						<a href="bg/exhibition!index" target="mainFrame">
							<i class="icon-th-large"></i>
							展览展会管理
							<!-- <span class="label label-warning pull-right">5</span> -->
						</a>
					</li>
					
					<li onclick="addClassActive(this)">
						<a href="bg/invitation!index" target="mainFrame">
							<i class="icon-signal"></i>
							招标信息管理
						</a>
					</li>
					
					<li onclick="addClassActive(this)">
						<a href="bg/recruit!index" target="mainFrame">
							<i class="icon-list-alt"></i>
							招聘管理
						</a>
					</li>
					
					<li onclick="addClassActive(this)">
						<a href="bg/job!index" target="mainFrame">
							<i class="icon-briefcase"></i>
							求职管理
						</a>
					</li>
					
					<li onclick="addClassActive(this)">
						<a href="bg/pharmacy!index" target="mainFrame">
							<i class="icon-th"></i>
							药房动态管理
						</a>
					</li>
					
					<li onclick="addClassActive(this)">
						<a href="bg/trade!index" target="mainFrame">
							<i class="icon-align-center"></i>
							行业动态管理
						</a>
					</li>
					
					<li onclick="addClassActive(this)">
						<a href="bg/law!index" target="mainFrame">
							<i class="icon-book"></i>
							法律法规管理
						</a>
					</li>
					
					<li onclick="addClassActive(this)">
						<a href="bg/user!selAllFeedBack" target="mainFrame">
							<i class="icon-user"></i>
							用户反馈信息管理					
						</a>
					</li>
					
					<li onclick="addClassActive(this)">
						<a href="bg/user!selVersion" target="mainFrame">
							<i class="icon-lock"></i>
							版本信息管理	
						</a>
					</li>
					
				</ul>	
				
				
				
				<hr />
				
				<!-- <div class="sidebar-extra">
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud.</p>
				</div> .sidebar-extra
				 -->
				<br />
			
			</div> <!-- /span3 -->
			
			
			<div class="span9">
				<iframe id="mainFrame" src="welcome.jsp" frameborder="0" width="100%"  onload="javascript:dyniframesize('mainFrame');" name="mainFrame" scrolling="no"></iframe>
			</div> <!-- /span9 -->
		</div> <!-- /row -->
		
	</div> <!-- /container -->
	
</div> <!-- /content -->
					
	
<div id="footer">
	
	<div class="container">				
		<hr />
		<p>&copy; 2013 By Marck.</p>
	</div> <!-- /container -->
	
</div> <!-- /footer -->

<script type="text/javascript">
(function() {
	window.history.forward();
})(jQuery);
function check(){
	var isPass = false;
	//alert(123);
	for( var i = 0 ; i < arguments.length ; i+=2 ){
		//alert($("#"+arguments[i]).val());
		if($("#"+arguments[i]).val() == "" || $("#"+arguments[i]).val() == null){
				$("#"+arguments[i]).closest(".control-group").addClass("error");
				$("#"+arguments[i]).next("span").html(arguments[i+1]);
				isPass = true;
		}
	}
	if(isPass){
		return;
	}
	$(".modal-backdrop").remove();
	$("#modal").hide();
	$("form").submit();
}

</script>
  </body>
</html>
