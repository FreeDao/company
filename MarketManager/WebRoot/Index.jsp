<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html > 
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>同城生活圈</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
 <script src="js/jquery.min.js" type=text/javascript></script>
  <link type="text/css" href="../MarketManager/css/index.css" rel="stylesheet" />
  
  <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
  <body>
  
 <div class="login" align="left">
	        <form action="" method="get" class="form-horizontal" style="text-align: left;padding:216px 0 0 100px;"  role="form">
	        			<div class="control-group">
	        				<label class="control-label" for="userName">用户名</label>
	        				<div class="controls">
						    <input class="input-medium" style="width: 210px;" name="userName" id="userName" type="text" placeholder="用户名" />
						  	<span  style="color: red;" class="userName"></span>
						  	</div>
						  </div>
						 <div class="control-group">
						 <label class="control-label" for="passWord">密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
						 	<div class="controls">
    						<input style="width: 210px;" name="passWord" type="password" id="passWord" placeholder="密码" />
							<span  style="color: red;" class="passWord"></span>
	        				</div>
	        			</div>
	        			<div class="control-group">
		        			<div class="controls" >
		        			<button style="width: 225px;" type="button" class="btn btn-block btn-info" onclick="return login();">登陆</button>
		        			</div>
		        		</div>
	           <!--  <label>用户名</label>
	            <input name="userName" type="text" id="userName" class="input"/>
	            <label  style="color: red;" class="userName"></label>
	            <label>密码</label>
	            <input name="passWord" type="password" class="input" id="passWord"/>
	            <label  style="color: red;" class="passWord"></label>
	             <input name="" type="button" class="button" value="" onclick="return login();"/> -->
	        </form>
	        
	       
	</div> 
<!-- <div class="login">
        <form action="" method="get" class="form">
            <label>用户名</label>
            <input name="" type="text" class="input"/>
            <label>密码</label>
            <input name="" type="password" class="input"/>
            <input name="" type="submit" class="button" value=""/>
        </form>
	</div> -->
	<div class="foot">CopyRight © 重庆鹏优信息技术有限公司 2013 All Rights Reserved</div>
  </body>
  <script type="text/javascript">
  		function login()
  		{
  			var userName = document.getElementById("userName").value;
  			var passWord = document.getElementById("passWord").value;
  			if(userName == "")
  			{
  				$(".userName").html('<img src="../MarketManager/images/error.jpg">用户名为空');
  				return false;
  			}
  			else
  			{
  				$(".userName").html('<img src="../MarketManager/images/right.jpg">');
  			}
  			if(passWord == "")
  			{
  				$(".passWord").html('<img src="../MarketManager/images/error.jpg">密码为空');
  				return false;
  			}
  			else
  			{
  				$(".passWord").html('<img src="../MarketManager/images/right.jpg">');
  			}
  			$.ajax({
				type: "post",
				url: "../MarketManager/System/login.action",
				dataType:'json',
				data: "userName="+userName+"&passWord="+passWord,//提交表单，相当于CheckCorpID.ashx?ID=XXX
				success: function(data)
				{
					if(data.msg == 1)
					{
						$(".passWord").html('<img src="../MarketManager/images/error.jpg">用户名或密码错误');
						$(".userName").html("");
					}
					if(data.msg == 2)
					{
						$(".passWord").html('<img src="../MarketManager/images/error.jpg">登录异常');
						$(".userName").html("");
					}
					if(data.msg == 3)
					{
						location.href = "../MarketManager/System/pageUser.action";
					}
				}
			});
  		}
  </script>
</html>
