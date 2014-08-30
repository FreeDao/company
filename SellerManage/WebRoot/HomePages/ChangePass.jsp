<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
  </head>
  
  <body>
    <div id="chang">
    	<div class="pwd">修改密码</div>
    	<div class="change">
        	<form id="form1" name="form1" action="../AgentSystem/System/updatePass.action" method="post">
        	<table>
            	<tr>
                	<td align="right">旧密码:</td>
                    <td><input type="password" id="opass" name="opass" /></td>
                    <td class="opass"></td>
                </tr> 
                <tr>
                	<td align="right">新密码:</td>
                    <td><input type="password" id="npass1" name="npass1" /></td>
                    <td class="npass1"></td>
                </tr>
                <tr>
                	<td align="right">确认新密码:</td>
                    <td><input type="password" id="npass2" name="npass2" /></td>
                    <td class="npass2"></td>
                </tr>
                <tr>
                	<td align="right" class="yanz" colspan="1"></td>
                </tr>
                <tr>
                	<td align="center" colspan="3"><input type="button" value="确定" id="sure" name="sure" onclick="return updatePass();"/></td>
                </tr>
            </table>
            </form>
        </div>
    </div>
   <script type="text/javascript">
   		function updatePass()
   		{
   			var opass = document.getElementById("opass").value;
   			var npass1 = document.getElementById("npass1").value;
   			var npass2 = document.getElementById("npass2").value;
   			
   			if(opass == "")
   			{
   				$(".opass").html('<label  style="color: red;"><img src="../SellerManage/images/error.jpg">旧密码为空</label>');
   				return false;
   			}
   			else
   			{
   				$(".opass").html('<img src="../SellerManage/images/right.jpg">');
   			}
   			if(npass1 == "")
   			{
   				$(".npass1").html('<label  style="color: red;"><img src="../SellerManage/images/error.jpg">新密码为空</label>');
   				return false;
   			}
   			else
   			{
   				$(".npass1").html('<img src="../SellerManage/images/right.jpg">');
   			}
   			if(npass2 == "")
   			{
   				$(".npass2").html('<label  style="color: red;"><img src="../SellerManage/images/error.jpg">确认密码为空</label>');
   				return false;
   			}
   			else
   			{
   				$(".npass2").html('<img src="../SellerManage/images/right.jpg">');
   			}
   			if(npass2 != npass1)
   			{
   				$(".npass2").html('<label  style="color: red;"><img src="../SellerManage/images/error.jpg">新密码和确认密码不一样</label>');
   				return false;
   			}
   			else
   			{
   				$(".npass2").html("");
   			}
   			if(opass == npass1)
   			{
   				$(".npass2").html('<label  style="color: red;"><img src="../SellerManage/images/error.jpg">新密码和旧密码不能一样</label>');
   				return false;
   			}
   			$.ajax({
				type: "post",
				url: "../SellerManage/System/updatePass.action",
				dataType:'json',
				data: "passWord="+npass1+"&oPassWord="+opass,//提交表单，相当于CheckCorpID.ashx?ID=XXX
				success: function(data)
				{
					if(data.msg == 1)
					{
						$(".npass2").html('<img src="../SellerManage/images/right.jpg">');
						$(".opass").html('<img src="../SellerManage/images/right.jpg">');
						$(".npass1").html('<img src="../SellerManage/images/right.jpg">');
						$(".yanz").html('<img src="../SellerManage/images/right.jpg">修改成功');
					}
					if(data.msg == 2)
					{
						$(".npass2").html('<img src="../SellerManage/images/right.jpg">');
						$(".opass").html('<img src="../SellerManage/images/right.jpg">');
						$(".npass1").html('<img src="../SellerManage/images/right.jpg">');
						$(".yanz").html('<img src="../SellerManage/images/error.jpg">修改异常');
					}
					
				}
			});
   		}
   </script>
  </body>
</html>
