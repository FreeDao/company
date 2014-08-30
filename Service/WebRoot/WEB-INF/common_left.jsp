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
    
  </head>
  <body>
			<div class="account-container">
				
					<div class="account-avatar">
						<img src="./img/headshot.png" alt="" class="thumbnail" />
					</div> <!-- /account-avatar -->
				
					<div class="account-details">
					
						<span class="account-name">${userSession.userName }</span>
						
						<span class="account-role">管理员</span>
						
						<!-- <span class="account-actions">
							<a href="javascript:;">账户设置</a> |
							
							<a href="javascript:;">修改密码</a>
						</span> -->
					
					</div> <!-- /account-details -->
				
				</div> <!-- /account-container -->
				
				<hr />
				
				<ul id="main-nav" class="nav nav-tabs nav-stacked">
					
					<li onclick="addClassActive(this)">
						<a href="bg/user!index">
							<i class="icon-home"></i>
							首页			
						</a>
					</li>
					
					<li  onclick="addClassActive(this)">
						<a href="bg/cate!index" >
							<i class="icon-pushpin"></i>
							餐饮管理
						</a>
					</li>
					
					<li onclick="addClassActive(this)">
						<a href="bg/clothing!index" >
							<i class="icon-th-list"></i>
							服装管理	
						</a>
					</li>
					
					<li onclick="addClassActive(this)">
						<a href="bg/education!index" >
							<i class="icon-th-large"></i>
							教育管理
							<!-- <span class="label label-warning pull-right">5</span> -->
						</a>
					</li>
					
					<li onclick="addClassActive(this)">
						<a href="bg/entertainment!index" >
							<i class="icon-folder-close"></i>
							娱乐管理
							<!-- <span class="label label-warning pull-right">5</span> -->
						</a>
					</li>
					
					<li onclick="addClassActive(this)">
						<a href="bg/hairdressing!index" >
							<i class="icon-star"></i>
							美容管理
							<!-- <span class="label label-warning pull-right">5</span> -->
						</a>
					</li>
					
					<li onclick="addClassActive(this)">
						<a href="bg/lease!index" >
							<i class="icon-book"></i>
							租赁管理
						</a>
					</li>
					
					<li onclick="addClassActive(this)">
						<a href="bg/medicine!index" >
							<i class="icon-heart"></i>
							医疗管理
						</a>
					</li>
					
					<li onclick="addClassActive(this)">
						<a href="bg/service!index" >
							<i class="icon-hand-up"></i>
							服务管理
						</a>
					</li>
					
					<li onclick="addClassActive(this)">
						<a href="bg/travel!index" >
							<i class="icon-flag"></i>
							旅游管理
						</a>
					</li>
					
					<li onclick="addClassActive(this)">
						<a href="bg/user!selAllFeedBack" >
							<i class="icon-user"></i>
							用户反馈信息管理					
						</a>
					</li>
					
					<li onclick="addClassActive(this)">
						<a href="bg/user!selVersion" >
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
	<script type="text/javascript">
	$(window).load(function() {
		$(".active").removeClass("active");
		$("a[href='${pu.url }']").parent().addClass("active");
	});
	function addClassActive(t){
		if("active" != $(t).attr("class")){
			$(".active").removeClass("active");
			$(t).addClass("active");
		}
		//
	}
				
	</script>
  </body>
</html>