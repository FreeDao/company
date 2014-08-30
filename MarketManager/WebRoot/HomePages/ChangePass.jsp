<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改密码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="${pageContext.request.contextPath}/css/center.css" type="text/css" rel="stylesheet" />
	 <script src="../js/jquery.min.js" type=text/javascript></script>
 		<%@include file="common.jsp" %>
  </head>
  
  <body>
   <ul class="breadcrumb" style="border: 1px;border-color: #E8E8E8;border-style:solid;">
			  <li><i class="icon-user"></i><a href="System/selPageUser.action">会员中心</a> <span class="divider">/</span></li>
			  <li class="active">用户密码修改</li>
		  </ul>
  
        	<form class="form-horizontal" id="form1" name="form1" action="../AgentSystem/System/updatePass.action" method="post">
        	<div class="control-group">
				<label class="control-label" for="opass">旧密码:</label>
				<div class="controls">
					<input type="password" id="opass" size="15" name="opass" />
					<span class="opass"></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="npass1">新密码:</label>
				<div class="controls">
					<input type="password" id="npass1" size="15" name="npass1" />
					<span class="npass1"></span>
				</div>
			</div>
        	<div class="control-group">
				<label class="control-label" for="npass2">确认新密码:</label>
				<div class="controls">
					<input type="password" id="npass2" size="15" name="npass2" />
					<span class="npass2"></span>
				</div>
			</div>
			<div class="control-group">
           	  <div class="controls">
            <span class="yanz" ></span>
            </div>
           </div>
           <div class="control-group">
           	  <div class="controls" >
            <button style="width: 220px;" class="btn  btn-block btn-primary" type="button" id="sure" name="sure" onclick="return updatePass();">确定</button>
            </div>
           </div>
            </form>
   <script type="text/javascript">
   		function updatePass()
   		{
   			var opass = document.getElementById("opass").value;
   			var npass1 = document.getElementById("npass1").value;
   			var npass2 = document.getElementById("npass2").value;
   			
   			if(opass == "")
   			{
   				$(".opass").html('<span  style="color: red;"><img src="../MarketManager/images/error.jpg">旧密码为空</span>');
   				return false;
   			}
   			else
   			{
   				$(".opass").html('<img src="../MarketManager/images/right.jpg">');
   			}
   			if(npass1 == "")
   			{
   				$(".npass1").html('<span  style="color: red;"><img src="../MarketManager/images/error.jpg">新密码为空</span>');
   				return false;
   			}
   			else
   			{
   				$(".npass1").html('<img src="../MarketManager/images/right.jpg">');
   			}
   			if(npass2 == "")
   			{
   				$(".npass2").html('<span  style="color: red;"><img src="../MarketManager/images/error.jpg">确认密码为空</span>');
   				return false;
   			}
   			else
   			{
   				$(".npass2").html('<img src="../MarketManager/images/right.jpg">');
   			}
   			if(npass2 != npass1)
   			{
   				$(".npass2").html('<span  style="color: red;"><img src="../MarketManager/images/error.jpg">新密码和确认密码不一样</span>');
   				return false;
   			}
   			else
   			{
   				$(".npass2").html("");
   			}
   			if(opass == npass1)
   			{
   				$(".npass2").html('<span  style="color: red;"><img src="../MarketManager/images/error.jpg">新密码和旧密码不能一样</span>');
   				return false;
   			}
   			$.ajax({
				type: "post",
				url: "../MarketManager/System/updatePass.action",
				dataType:'json',
				data: "passWord="+npass1+"&oPassWord="+opass,//提交表单，相当于CheckCorpID.ashx?ID=XXX
				success: function(data)
				{
					if(data.msg == 1)
					{
						$(".npass2").html('<img src="../MarketManager/images/right.jpg">');
						$(".opass").html('<img src="../MarketManager/images/right.jpg">');
						$(".npass1").html('<img src="../MarketManager/images/right.jpg">');
						$(".yanz").html('<img src="../MarketManager/images/right.jpg">修改成功');
					}
					if(data.msg == 2)
					{
						$(".npass2").html('<img src="../MarketManager/images/right.jpg">');
						$(".opass").html('<img src="../MarketManager/images/right.jpg">');
						$(".npass1").html('<img src="../MarketManager/images/right.jpg">');
						$(".yanz").html('<img src="../MarketManager/images/error.jpg">修改异常');
					}
					
				}
			});
   		}
   </script>
  </body>
</html>
