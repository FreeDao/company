<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link type="text/css" href="${pageContext.request.contextPath}/css/center.css" rel="stylesheet" />
	<script src="../js/jquery.min.js" type=text/javascript></script>
  	<%@include file="common.jsp" %>
  </head>
  
  <body>
  
  
    <!--修改会员信息-->
    <ul class="breadcrumb" style="border: 1px;border-color: #E8E8E8;border-style:solid;">
			  <li><i class="icon-user"></i><a href="System/selPageUser.action">会员中心</a> <span class="divider">/</span></li>
			  <li class="active">用户基本信息修改</li>
		  </ul>
    
              <form action="../MarketManager/System/updateAgent.action" enctype="multipart/form-data" method="post" class="form-horizontal">
					<div class="control-group">
						<label class="control-label" for="Email">Email：</label>
						<div class="controls">
							<input type="text" id="Email" size="15" name="Email" value="${model.email}"/>
							<span class="email"></span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="qq">QQ：</label>
						<div class="controls">
							<input type="text" id="qq" size="15" name="agentage" value="${model.qq}"/>
							<span class="qqtip"></span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="phone">手机：</label>
						<div class="controls">
							<input type="text" id="phone" size="15" name="agentphone" value="${model.telePhone}"/>
							<span class="phone"></span>
						</div>
					</div>
					<div class="control-group">
                  	  <div class="controls">
	                  <span class="yanz" ></span>
	                  </div>
                  </div>
                  <div class="control-group">
                  	  <div class="controls" >
	                  <button style="width: 220px;" class="btn  btn-block btn-primary" type="button" id="sure" name="sures" onclick="return updatePass();">确定</button>
	                  </div>
                  </div>
              </form>
     <script type="text/javascript">
   		function updatePass()
   		{
   			var Email = document.getElementById("Email").value;
   			var qq = document.getElementById("qq").value;
   			var phone = document.getElementById("phone").value;
   			
   			var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
   			var shouji = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/;
   			var shuzi=/^[0-9]*$/;
   			if(Email == "" || !reg.test(Email))
   			{
   				$(".email").html('<span  style="color: red;"><img src="../MarketManager/images/error.jpg">邮箱格式不正确</span>');
   				return false;
   			}
   			else
   			{
   				$(".email").html('<img src="../MarketManager/images/right.jpg">');
   			}
   			
   			if(qq == "" || !shuzi.test(qq))
   			{
   				$(".qqtip").html('<span  style="color: red;"><img src="../MarketManager/images/error.jpg">qq格式不正确</span>');
   				return false;
   			}
   			else
   			{
   				$(".qqtip").html('<img src="../MarketManager/images/right.jpg">');
   			}
   			
   			if(phone == "" || !shouji.test(phone) || phone.length>11)
   			{
   				$(".phone").html('<span  style="color: red;"><img src="../MarketManager/images/error.jpg">手机格式不正确</span>');
   				return false;	
    		}
   			else
   			{
   				$(".phone").html('<img src="../MarketManager/images/right.jpg">');
   			}
   			$.ajax({
				type: "post",
				url: "../MarketManager/System/updatePass.action",
				dataType:'json',
				data: "email="+Email+"&qq="+qq+"&phone="+phone,//提交表单，相当于CheckCorpID.ashx?ID=XXX
				success: function(data)
				{
					if(data.msg == 1)
					{
						$(".email").html('<img src="../MarketManager/images/right.jpg">');
						$(".qq").html('<img src="../MarketManager/images/right.jpg">');
						$(".phone").html('<img src="../MarketManager/images/right.jpg">');
						$(".yanz").html('<img src="../MarketManager/images/right.jpg">修改成功');
					}
					if(data.msg == 2)
					{
						$(".email").html('<img src="../MarketManager/images/right.jpg">');
						$(".qq").html('<img src="../MarketManager/images/right.jpg">');
						$(".phone").html('<img src="../MarketManager/images/right.jpg">');
						$(".yanz").html('<img src="../MarketManager/images/error.jpg">修改异常');
					}
				}
			});
   		}
   		</script>
  </body>
</html>
