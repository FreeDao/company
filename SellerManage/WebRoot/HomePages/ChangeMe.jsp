<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
  </head>
  
  <body>
    <!--修改会员信息-->
    <div id="alls">
         <div class="aso"><img src="../SellerManage/images/aou.png" width="90" height="70" /></div>
              <div class="update">修改基本信息</div>
              <div class="bg">
              <form action="../AgentSystem/System/updateAgent.action" enctype="multipart/form-data" method="post">
                  <table>
                     <tr>
                        <td align="right">Email：</td>
                        <td><input type="text" id="Email" size="15" name="Email" value="${model.email}"/></td>
                        <td class="email"></td>
                     </tr>
                     <tr>
                       <td align="right">Qq：</td>
                       <td><input type="text" id="qq" size="15" name="agentage" value="${model.qq}" /></td>
                       <td class="qq"></td>
                     </tr>
                     <tr>
                       <td align="right">手机：</td>
                       <td><input type="text" id="phone" size="15" name="agentphone" value="${model.telePhone}"/></td>
                       <td class="phone"></td>
                     </tr>
                     <tr>
                		<td align="right" class="yanz" colspan="2"></td>
               		 </tr>
                     <tr>
                       <td colspan="2" align="center"><input type="button" value="确定" id="sure" name="sures" onclick="return updatePass();"/></td>
                     </tr>
                  </table>
              </form>
          </div>
     </div>
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
   				$(".email").html('<label  style="color: red;"><img src="../SellerManage/images/error.jpg">邮箱格式不正确</label>');
   				return false;
   			}
   			else
   			{
   				$(".email").html('<img src="../SellerManage/images/right.jpg">');
   			}
   			
   			if(qq == "" || !shuzi.test(qq))
   			{
   				$(".qq").html('<label  style="color: red;"><img src="../SellerManage/images/error.jpg">qq格式不正确</label>');
   				return false;
   			}
   			else
   			{
   				$(".email").html('<img src="../SellerManage/images/right.jpg">');
   			}
   			if(phone == "" || !shouji.test(phone) || phone.length>11)
   			{
   				$(".phone").html('<label  style="color: red;"><img src="../SellerManage/images/error.jpg">手机格式不正确</label>');
   				return false;	
    		}
   			else
   			{
   				$(".phone").html('<img src="../SellerManage/images/right.jpg">');
   			}
   			$.ajax({
				type: "post",
				url: "../SellerManage/System/updatePass.action",
				dataType:'json',
				data: "email="+Email+"&qq="+qq+"&phone="+phone,//提交表单，相当于CheckCorpID.ashx?ID=XXX
				success: function(data)
				{
					if(data.msg == 1)
					{
						$(".email").html('<img src="../SellerManage/images/right.jpg">');
						$(".qq").html('<img src="../SellerManage/images/right.jpg">');
						$(".phone").html('<img src="../SellerManage/images/right.jpg">');
						$(".yanz").html('<img src="../SellerManage/images/right.jpg">修改成功');
					}
					if(data.msg == 2)
					{
						$(".email").html('<img src="../SellerManage/images/right.jpg">');
						$(".qq").html('<img src="../SellerManage/images/right.jpg">');
						$(".phone").html('<img src="../SellerManage/images/right.jpg">');
						$(".yanz").html('<img src="../SellerManage/images/error.jpg">修改异常');
					}
				}
			});
   		}
   		</script>
  </body>
</html>
