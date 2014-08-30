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
  <%@taglib uri="/struts-tags" prefix="s"%>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js" type=text/javascript></script>
 	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/WdatePicker.js"></script>
  <body>
    <div id="alls">
         <div class="aso"><img src="../Building/images/aou.png" width="90" height="70" /></div>
              <div class="update">修改基本信息</div>
              <div class="bg">
              <form enctype="multipart/form-data" method="post" action="../Building/System/addSeller.action">
              <input type="hidden" value="${seller.id}" name = "id" id="sellerId">
                  <table>
                     <tr>
                        <td align="right">商铺名称:</td>
                        <td><input type="text" id="featureName" size="15" name="sellerName" value="${seller.sellerName}"/></td>
                        <td class="featureName"></td>
                     </tr>
                     <tr>
			    		<td align="right">所属城市:</td>
			    		<td>
			    			<select name="city" style="width: 155px;">
			    				<option value="0">请选择</option>
			    				<s:iterator value="city_list" var="city">
			    					<option value="${city.id}">${city.cityName}</option>
			    				</s:iterator>
			    			</select>
			    		</td>
    					</tr>
    					<tr>
			    		<td align="right">所属行业:</td>
			    		<td>
			    			<select name="type" style="width: 155px;">
			    				<option value="0">请选择</option>
			    				<s:iterator value="type_list1" var="city">
			    					<option value="${city.id}">${city.name}</option>
			    				</s:iterator>
			    			</select>
			    		</td>
    					</tr>
    					<tr>
			    		<td align="right">所属用户:</td>
			    		<td>
			    			<select name="userId" style="width: 155px;">
			    				<option value="0">请选择</option>
			    				<s:iterator value="user_list" var="city">
			    					<option value="${city.id}">${city.email}</option>
			    				</s:iterator>
			    			</select>
			    		</td>
    					</tr>
    				<tr>
                       <td align="right">简介：</td>
                       <td><textarea name="content" style="width: 300;height: 100">${seller.content}</textarea></td>
                       <td class="bife"></td>
                    </tr>
        		    <tr>
                       <td align="right">地址：</td>
                       <td><input type="text" id="address" size="15" name="address" value="${seller.address}"/></td>
                       <td class="address"></td>
                     </tr>
                     <tr>
                       <td align="right">联系人：</td>
                       <td><input type="text" id="price" size="15" name="sellerOwner" value="${seller.sellerOwner}"/></td>
                       <td class="price"></td>
                     </tr>
                      <tr>
                       <td align="right">联系方式：</td>
                       <td><input type="text" id="time" size="15" name="phone" value="${seller.phone}"/></td>
                       <td class="time"></td>
                     </tr>
                      <tr>
                       <td align="right">邮件：</td>
                       <td><input type="text" id="leven" size="15" name="email" value="${seller.email}"/></td>
                       <td class="leven"></td>
                     </tr>
                      <tr>
                       <td align="right">图片：</td>
                       <td><input type="file" name="picture" id="aa" value="${seller.imgUrl}"/></td>
                       <td class="logo"></td>
                     </tr>
                     <tr>
                		<td align="right" class="yanz" colspan="2"></td>
               		 </tr>
                     <tr>
                       <td colspan="2" align="center"><input type="submit" value="确定" id="sure" name="sures" onclick="return updateSeller();"/></td>
                     </tr>
                  </table>
              </form>
          </div>
     </div>
     <script type="text/javascript">
     	function updateSeller()
     	{
     		var featureName = document.getElementById("featureName").value;
     		var address = document.getElementById("address").value;
     		var price = document.getElementById("price").value;
     		var time = document.getElementById("time").value;
     		var leven = document.getElementById("leven").value;
			var shouji = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/;
			var pre = /^\+?[1-9][0-9]*$/;
			if(featureName == "")
			{
				$(".featureName").html('<label  style="color: red;"><img src="../Building/images/error.jpg">店铺名称不能为空</label>');
   				return false;
			}
			else
			{
				$(".featureName").html('<img src="../Building/images/right.jpg">');
			}
			
			if(address == "")
			{
				$(".address").html('<label style="color: red;"><img src="../Building/images/error.jpg">地址不正确</label>');
   				return false;
			}
			else
			{
				$(".address").html('<img src="../Building/images/right.jpg">');
			}
			
			if(price == "")
			{
				$(".price").html('<label  style="color: red;"><img src="../Building/images/error.jpg">请填写联系人</label>');
   				return false;
			}
			else
			{
				$(".price").html('<img src="../Building/images/right.jpg">');
			}
			
			if(time == "")
			{
				$(".time").html('<label  style="color: red;"><img src="../Building/images/error.jpg">请填写联系方式</label>');
   				return false;
			}
			else
			{
				$(".time").html('<img src="../Building/images/right.jpg">');
			}
			
			if(leven == "")
			{
				$(".leven").html('<label  style="color: red;"><img src="../Building/images/error.jpg">请填写邮件</label>');
   				return false;
			}
			else
			{
				$(".leven").html('<img src="../Building/images/right.jpg">');
			}
     	}
     </script>
  </body>
</html>