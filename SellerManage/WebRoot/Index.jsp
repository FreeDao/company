
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
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
  <link type="text/css" href="../SellerManage/css/index.css" rel="stylesheet" />
  <body>
  <center>
 <div class="login" align="left">
	        <form action="" method="get" class="form" style="text-align: left;">
	        	<table>
	        		<tr>
	        			<td>用户名</td>
	        		</tr>
	        		<tr>
	        			<td><input name="userName" type="text" id="userName" class="input"/><label  style="color: red;" class="userName"></label></td>
	        		</tr>
	        		<tr>
	        			<td>密码</td>
	        		</tr>
	        		<tr>
	        			<td><input name="passWord" type="password" class="input" id="passWord"/><label  style="color: red;" class="passWord"></label></td>
	        		</tr>
	        		<tr>
	        			<td><input name="" type="button" class="button" value="" onclick="return login();"/></td>
	        		</tr>
	        	</table>
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
	</center>
	<div class="foot">CopyRight © 重庆鹏优信息技术有限公司 2013 All Rights Reserved</div>
  </body>
  <script type="text/javascript">
  		function login()
  		{
  			var userName = document.getElementById("userName").value;
  			var passWord = document.getElementById("passWord").value;
  			if(userName == "")
  			{
  				$(".userName").html('<img src="../SellerManage/images/error.jpg">用户名为空');
  				return false;
  			}
  			else
  			{
  				$(".userName").html('<img src="../SellerManage/images/right.jpg">');
  			}
  			if(passWord == "")
  			{
  				$(".passWord").html('<img src="../SellerManage/images/error.jpg">密码为空');
  				return false;
  			}
  			else
  			{
  				$(".passWord").html('<img src="../SellerManage/images/right.jpg">');
  			}
  			$.ajax({
				type: "post",
				url: "../SellerManage/System/login.action",
				dataType:'json',
				data: "userName="+userName+"&passWord="+passWord,//提交表单，相当于CheckCorpID.ashx?ID=XXX
				success: function(data)
				{
					if(data.msg == 1)
					{
						$(".passWord").html('<img src="../SellerManage/images/error.jpg">用户名或密码错误');
						$(".userName").html("");
					}
					if(data.msg == 2)
					{
						$(".passWord").html('<img src="../SellerManage/images/error.jpg">登录异常');
						$(".userName").html("");
					}
					if(data.msg == 3)
					{
						location.href = "../SellerManage/System/pageUser.action";
					}
				}
			});
  		}
  </script>
</html>
