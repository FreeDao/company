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
    	  <!--修改会员信息-->
    <div id="alls">
         <div class="aso"><img src="../JunKeting/images/aou.png" width="90" height="70" /></div>
              <div class="update">修改基本信息</div>
              <div class="bg">
              <form enctype="multipart/form-data" method="post" action="../JunKeting/System/addFeature.action">
              <input type="hidden" value="${feature.id}" name = "id" id="sellerid">
                  <table>
                     <tr>
                        <td align="right">景点名称：</td>
                        <td><input type="text" id="featureName" size="15" name="featureName" value="${feature.featureName}"/></td>
                        <td class="featureName"></td>
                     </tr>
                     <tr>
                       <td align="right">地址：</td>
                       <td><input type="text" id="address" size="15" name="address" value="${feature.address}"/></td>
                       <td class="address"></td>
                     </tr>
                     <tr>
                       <td align="right">价格：</td>
                       <td><input type="text" id="price" size="15" name="price" value="${feature.price}"/></td>
                       <td class="price"></td>
                     </tr>
                      <tr>
                       <td align="right">开放时间：</td>
                       <td><input type="text" id="time" size="15" name="time" value="${feature.time}"/></td>
                       <td class="time"></td>
                     </tr>
                      <tr>
                       <td align="right">星级：</td>
                       <td><input type="text" id="leven" size="15" name="leven" value="${feature.leven}"/></td>
                       <td class="leven"></td>
                     </tr>
                      <tr>
                       <td align="right">图片：</td>
                       <td><input type="file" name="picture" id="aa" value="${feature.logo}"/></td>
                       <td class="logo"></td>
                     </tr>
                     <tr>
                       <td align="right">简介：</td>
                       <td><textarea name="bife" style="width: 300;height: 100">${feature.bife}</textarea></td>
                       <td class="bife"></td>
                     </tr>
                     <tr>
                       <td align="right">人文：</td>
                       <td><textarea name="humanity" style="width: 300;height: 200">${feature.humanity}</textarea></td>
                    
                       <td class="humanity"></td>
                     </tr>
                     <tr>
                       <td align="right">城市：</td>
                      <td>
                      	<select name="areaId">
                      		<s:iterator value="listArea" var="d">
                      				<option value="${d.id}">${d.areaName}</option>
                      		</s:iterator>
                      	</select>
                      </td>
                     </tr>
                     <tr>
                       <td align="right">推荐：</td>
                       <td>
                       <select name="good">
                       	<s:if test="feature.groom == 1">
                       		<option value="1">推荐</option>
                     		<option value="0">默认</option>
                       	</s:if>
                     	<s:else>
                     		<option value="0">默认</option>
                     		<option value="1">推荐</option>
                     	</s:else>
                     </select>
                     </td>
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
				$(".featureName").html('<label  style="color: red;"><img src="../JunKeting/images/error.jpg">景点名称不能为空</label>');
   				return false;
			}
			else
			{
				$(".featureName").html('<img src="../JunKeting/images/right.jpg">');
			}
			
			if(address == "")
			{
				$(".address").html('<label style="color: red;"><img src="../JunKeting/images/error.jpg">地址不正确</label>');
   				return false;
			}
			else
			{
				$(".address").html('<img src="../JunKeting/images/right.jpg">');
			}
			
			if(price == "" || !pre.test(price))
			{
				$(".price").html('<label  style="color: red;"><img src="../JunKeting/images/error.jpg">价格不正确</label>');
   				return false;
			}
			else
			{
				$(".price").html('<img src="../JunKeting/images/right.jpg">');
			}
			
			if(time == "")
			{
				$(".time").html('<label  style="color: red;"><img src="../JunKeting/images/error.jpg">请选择开放时间</label>');
   				return false;
			}
			else
			{
				$(".time").html('<img src="../JunKeting/images/right.jpg">');
			}
			
			if(leven == "" || !pre.test(leven))
			{
				$(".leven").html('<label  style="color: red;"><img src="../JunKeting/images/error.jpg">星级只能为数字</label>');
   				return false;
			}
			else
			{
				$(".leven").html('<img src="../JunKeting/images/right.jpg">');
			}
     	}
     </script>
  </body>
</html>
