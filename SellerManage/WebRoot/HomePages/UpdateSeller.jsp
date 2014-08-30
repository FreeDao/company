<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>商户修改</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <script src="../js/jquery.min.js" type=text/javascript></script>
  <body>
    	  <!--修改会员信息-->
    <div id="alls">
         <div class="aso"><img src="../SellerManage/images/aou.png" width="90" height="70" /></div>
              <div class="update">修改基本信息</div>
              <div class="bg">
              <form enctype="multipart/form-data" method="post">
              <input type="hidden" value="${model.id}" name = "SellerId" id="sellerid">
                  <table>
                     <tr>
                        <td align="right">标题：</td>
                        <td><input type="text" id="titile" size="15" name="titile" value="${model.titile}"/></td>
                        <td class="titile"></td>
                     </tr>
                     <tr>
                       <td align="right">手机：</td>
                       <td><input type="text" id="phone" size="15" name="phone" value="${model.phone}"/></td>
                       <td class="phone"></td>
                     </tr>
                     <tr>
                       <td align="right">简介：</td>
                       <td><input type="text" id="brief" size="15" name="brief" value="${model.brief}"/></td>
                       <td class="brief"></td>
                     </tr>
                      <tr>
                       <td align="right">优惠：</td>
                       <td><input type="text" id="preferential" size="15" name="preferential" value="${model.preferential}"/></td>
                       <td class="preferential"></td>
                     </tr>
                      <tr>
                       <td align="right">地址：</td>
                       <td><input type="text" id="address" size="15" name="address" value="${model.address}"/></td>
                       <td class="address"></td>
                     </tr>
                     <tr>
                		<td align="right" class="yanz" colspan="2"></td>
               		 </tr>
                     <tr>
                       <td colspan="2" align="center"><input type="button" value="确定" id="sure" name="sures" onclick="return updateSeller();"/></td>
                     </tr>
                  </table>
              </form>
          </div>
     </div>
     <script type="text/javascript">
     	function updateSeller()
     	{
     		var titile = document.getElementById("titile").value;
     		var phone = document.getElementById("phone").value;
     		var sellerid = document.getElementById("sellerid").value;
     		var brief = document.getElementById("brief").value;
     		var preferential = document.getElementById("preferential").value;
     		var address = document.getElementById("address").value;
			var shouji = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/;
			var pre = /^\+?[1-9][0-9]*$/;
			if(titile == "")
			{
				$(".titile").html('<label  style="color: red;"><img src="../SellerManage/images/error.jpg">标题不能为空</label>');
   				return false;
			}
			else
			{
				$(".titile").html('<img src="../SellerManage/images/right.jpg">');
			}
			
			if(phone == "" || !shouji.test(phone))
			{
				$(".phone").html('<label style="color: red;"><img src="../SellerManage/images/error.jpg">手机格式不正确</label>');
   				return false;
			}
			else
			{
				$(".phone").html('<img src="../SellerManage/images/right.jpg">');
			}
			
			if(brief == "")
			{
				$(".brief").html('<label  style="color: red;"><img src="../SellerManage/images/error.jpg">简介不能为空</label>');
   				return false;
			}
			else
			{
				$(".brief").html('<img src="../SellerManage/images/right.jpg">');
			}
			
			if(preferential == "" ||!pre.test(preferential))
			{
				$(".preferential").html('<label  style="color: red;"><img src="../SellerManage/images/error.jpg">优惠格式不正确</label>');
   				return false;
			}
			else
			{
				$(".preferential").html('<img src="../SellerManage/images/right.jpg">');
			}
			
			if(address == "")
			{
				$(".address").html('<label  style="color: red;"><img src="../SellerManage/images/error.jpg">地址不能为空</label>');
   				return false;
			}
			else
			{
				$(".address").html('<img src="../SellerManage/images/right.jpg">');
			}
			$.ajax({
				type: "post",
				url: "../SellerManage/System/updateSeller.action",
				dataType:'json',
				data: "titile="+titile+"&phone="+phone+"&brief="+brief+"&preferential="+preferential+"&address="+address+"&sellerid="+sellerid,//提交表单，相当于CheckCorpID.ashx?ID=XXX
				success: function(data)
				{
					if(data.msg == 1)
					{
						$(".titile").html('<img src="../SellerManage/images/right.jpg">');
						$(".phone").html('<img src="../SellerManage/images/right.jpg">');
						$(".brief").html('<img src="../SellerManage/images/right.jpg">');
						$(".preferential").html('<img src="../SellerManage/images/right.jpg">');
						$(".address").html('<img src="../SellerManage/images/right.jpg">');
						$(".yanz").html('<img src="../SellerManage/images/right.jpg">修改成功');
					}
					if(data.msg == 2)
					{
						$(".titile").html('<img src="../SellerManage/images/right.jpg">');
						$(".phone").html('<img src="../SellerManage/images/right.jpg">');
						$(".brief").html('<img src="../SellerManage/images/right.jpg">');
						$(".preferential").html('<img src="../SellerManage/images/right.jpg">');
						$(".address").html('<img src="../SellerManage/images/right.jpg">');
						$(".yanz").html('<img src="../SellerManage/images/error.jpg">修改异常');
					}
					if(data.msg == 3)
					{
						$(".titile").html('<img src="../SellerManage/images/right.jpg">');
						$(".phone").html('<img src="../SellerManage/images/right.jpg">');
						$(".brief").html('<img src="../SellerManage/images/right.jpg">');
						$(".preferential").html('<img src="../SellerManage/images/right.jpg">');
						$(".address").html('<img src="../SellerManage/images/right.jpg">');
						$(".yanz").html('<img src="../SellerManage/images/error.jpg">地图未找到位置');
					}
				}
			});	    		
     	}
     </script>
  </body>
</html>
