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
    
    <title>角色列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
   <s:form name="rolesAdd" action="../City/System/addRoles.action" method="post" namespace="/System"  theme="css_xhtml" validate="true">
   	<table class="table">
   	<tr>
			<td colspan="2" class="title" >角色添加</td>
		</tr>
		<tr>
			<td class="text">角色名称:</td>
			<td class="input">${roleName}<input type="hidden" value="${roleName}" name="roleName">
			<input type="hidden" name = "id" value="${id}">
			</td>
		</tr>
   		<tr>
			<td class="text">选择权限:</td>
			<td class="input">
				<table style="font-size:12px;width:800px;">
					<tr>
					    <td style="width:120px;">密码管理</td>
					    <td>
					    	<input type="checkbox" name="rolestring" id="rolestring" value="updatePassWordPage" <s:if test="'updatePassWordPage' in pro">checked</s:if>/>修改密码页面
					    	<input type="checkbox" name="rolestring" id="rolestring" value="updatePassWord" <s:if test="'updatePassWord' in pro">checked</s:if>/>更新密码
					     </td>
					  </tr>
					  <tr>
					    <td style="width:120px;">角色管理</td>
					    <td>
					    	<input type="checkbox" name="rolestring" id="rolestring" value="selRoles" <s:if test="'selRoles' in pro">checked</s:if>/>查询角色
						    <input type="checkbox" name="rolestring" id="rolestring" value="rolesAdd" <s:if test="'rolesAdd' in pro">checked</s:if>/>添加角色
						    <input type="checkbox" name="rolestring" id="rolestring" value="addRoles" <s:if test="'addRoles' in pro">checked</s:if>/>修改角色
					   		<input type="checkbox" name="rolestring" id="rolestring" value="pageRolesAdd" <s:if test="'pageRolesAdd' in pro">checked</s:if>/>进入添加角色
					    	<input type="checkbox" name="rolestring" id="rolestring" value="pagesRoles" <s:if test="'pagesRoles' in pro">checked</s:if>/>进入修改角色
					     </td>
					  </tr>
					  <tr>
					    <td style="width:120px;">管理员管理</td>
					    <td>
					    	<input type="checkbox" name="rolestring" id="rolestring" value="adminUser" <s:if test="'adminUser' in pro">checked</s:if>/>查询管理员
						    <input type="checkbox" name="rolestring" id="rolestring"  value="addAdmin" <s:if test="'addAdmin' in pro">checked</s:if>/>添加管理员
						    <input type="checkbox" name="rolestring" id="rolestring" value="delAdmin" <s:if test="'delAdmin' in pro">checked</s:if>/>删除管理员
						    <input type="checkbox" name="rolestring" id="rolestring"  value="addPages" <s:if test="'addPages' in pro">checked</s:if>/>跳转到添加管理员
					     </td>
					  </tr>
					  <tr>
					    <td style="width:120px;">会员管理</td>
					    <td>
					    	<input type="checkbox" name="rolestring" id="rolestring" value="selUser" <s:if test="'selUser' in pro">checked</s:if>/>查询会员
						    <input type="checkbox" name="rolestring" id="rolestring" value="delUser" <s:if test="'delUser' in pro">checked</s:if>/>删除会员
					     </td>
					  </tr>
					  <tr>
					    <td style="width:120px;">商户管理</td>
					    <td>
					    	<input type="checkbox" name="rolestring" id="rolestring" value="selSeller" <s:if test="'selSeller' in pro">checked</s:if>/>查询商户
						    <input type="checkbox" name="rolestring" id="rolestring"  value="addSeller" <s:if test="'addSeller' in pro">checked</s:if>/>添加商户
						    <input type="checkbox" name="rolestring" id="rolestring" value="delSeller" <s:if test="'delSeller' in pro">checked</s:if>/>删除商户
						    <input type="checkbox" name="rolestring" id="rolestring" value="sellerPage" <s:if test="'sellerPage' in pro">checked</s:if>/>跳转到添加商户
						    <input type="checkbox" name="rolestring" id="rolestring" value="updateSellerPage" <s:if test="'updateSellerPage' in pro">checked</s:if>/>跳转到更新商户
						    <input type="checkbox" name="rolestring" id="rolestring" value="moveType" <s:if test="'moveType' in pro">checked</s:if>/>改变商户类型
						     <input type="checkbox" name="rolestring" id="rolestring" value="upSeller" <s:if test="'upSeller' in pro">checked</s:if>/>商户置顶
					     </td>
					  </tr>
					  <tr>
					    <td style="width:120px;">产品管理</td>
					    <td>
						    <input type="checkbox" name="rolestring" id="rolestring" value="selProduct" <s:if test="'selProduct' in pro">checked</s:if>/>查询产品
						    <input type="checkbox" name="rolestring" id="rolestring"  value="addProduct" <s:if test="'addProduct' in pro">checked</s:if>/>添加产品
						    <input type="checkbox" name="rolestring" id="rolestring" value="delProduct" <s:if test="'delProduct' in pro">checked</s:if>/>删除产品
						    <input type="checkbox" name="rolestring" id="rolestring" value="addProductPage" <s:if test="'addProductPage' in pro">checked</s:if>/>跳转到添加产品
						    <input type="checkbox" name="rolestring" id="rolestring" value="updateProductPage" <s:if test="'updateProductPage' in pro">checked</s:if>/>跳转到更新产品
						    <input type="checkbox" name="rolestring" id="rolestring" value="tiaozhuan" <s:if test="'tiaozhuan' in pro">checked</s:if>/>查询产品
					     </td>
					  </tr>
					  <tr>
					    <td style="width:120px;">图片管理</td>
					    <td>
						    <input type="checkbox" name="rolestring" id="rolestring" value="selImage" <s:if test="'selImage' in pro">checked</s:if>/>查询图片
						    <input type="checkbox" name="rolestring" id="rolestring"  value="addImages" <s:if test="'addImages' in pro">checked</s:if>/>添加图片
						    <input type="checkbox" name="rolestring" id="rolestring" value="delImages" <s:if test="'delImages' in pro">checked</s:if>/>删除图片
						    <input type="checkbox" name="rolestring" id="rolestring" value="addImagesPage" <s:if test="'addImagesPage' in pro">checked</s:if>/>跳转到添加图片
					     </td>
					  </tr>
					  <tr>
					    <td style="width:120px;">资讯管理</td>
					    <td>
						    <input type="checkbox" name="rolestring" id="rolestring" value="selNews" <s:if test="'selNews' in pro">checked</s:if>/>查询资讯
						    <input type="checkbox" name="rolestring" id="rolestring"  value="addNews" <s:if test="'addNews' in pro">checked</s:if>/>添加资讯
						    <input type="checkbox" name="rolestring" id="rolestring" value="delNews" <s:if test="'delNews' in pro">checked</s:if>/>删除资讯
						    <input type="checkbox" name="rolestring" id="rolestring" value="addNewsPage" <s:if test="'addNewsPage' in pro">checked</s:if>/>跳转到添加资讯
						    <input type="checkbox" name="rolestring" id="rolestring" value="updateNewsPage" <s:if test="'updateNewsPage' in pro">checked</s:if>/>跳转到更新资讯
					     </td>
					  </tr>
					  <tr>
					    <td style="width:120px;">市场管理</td>
					    <td>
					    	<input type="checkbox" name="rolestring" id="rolestring" value="selMarket" <s:if test="'selMarket' in pro">checked</s:if>/>查询市场
						    <input type="checkbox" name="rolestring" id="rolestring"  value="addMarket" <s:if test="'addMarket' in pro">checked</s:if>/>添加市场
						    <input type="checkbox" name="rolestring" id="rolestring" value="delMarket" <s:if test="'delMarket' in pro">checked</s:if>/>删除市场
						    <input type="checkbox" name="rolestring" id="rolestring" value="addMarketPage" <s:if test="'addMarketPage' in pro">checked</s:if>/>跳转到添加市场
						    <input type="checkbox" name="rolestring" id="rolestring" value="updateMarketPage" <s:if test="'updateMarketPage' in pro">checked</s:if>/>跳转到更新市场
					     </td>
					  </tr>
					  <tr>
					    <td style="width:120px;" rowspan="2">市场类型管理</td>
					    <td>
					    	<input type="checkbox" name="rolestring" id="rolestring" value="selMarketArea" <s:if test="'selMarketArea' in pro">checked</s:if>/>查询区域
						    <input type="checkbox" name="rolestring" id="rolestring"  value="addMarketArea" <s:if test="'addMarketArea' in pro">checked</s:if>/>添加区域
						    <input type="checkbox" name="rolestring" id="rolestring" value="delMarketArea" <s:if test="'delMarketArea' in pro">checked</s:if>/>删除区域
						    <input type="checkbox" name="rolestring" id="rolestring" value="addMarketAreaPage" <s:if test="'addMarketAreaPage' in pro">checked</s:if>/>跳转到添加区域
						    <input type="checkbox" name="rolestring" id="rolestring" value="updateMarketAreaPage" <s:if test="'updateMarketAreaPage' in pro">checked</s:if>/>跳转到更新区域
					     </td>
					  </tr>
					  <tr><td valign="top">	<input type="checkbox" name="rolestring" id="rolestring" value="selMarketCustomType" <s:if test="'selMarketCustomType' in pro">checked</s:if>/>查询行业类型
						    <input type="checkbox" name="rolestring" id="rolestring"  value="addMarketCustomType" <s:if test="'addMarketCustomType' in pro">checked</s:if>/>添加行业类型
						    <input type="checkbox" name="rolestring" id="rolestring" value="delMarketCustomType" <s:if test="'delMarketCustomType' in pro">checked</s:if>/>删除行业类型
						    <input type="checkbox" name="rolestring" id="rolestring" value="addMarketCustomTypePage" <s:if test="'addMarketCustomTypePage' in pro">checked</s:if>/>跳转到添加行业类型
						    <input type="checkbox" name="rolestring" id="rolestring" value="updateMarketCustomTypePage" <s:if test="'updateMarketCustomTypePage' in pro">checked</s:if>/>跳转到更新行业类型
					     </tr><tr>
					    <td style="width:120px;">城市管理</td>
					    <td>
					    	<input type="checkbox" name="rolestring" id="rolestring" value="selCity" <s:if test="'selCity' in pro">checked</s:if>/>查询城市
						    <input type="checkbox" name="rolestring" id="rolestring"  value="addCity" <s:if test="'addCity' in pro">checked</s:if>/>添加城市
						    <input type="checkbox" name="rolestring" id="rolestring" value="delCity" <s:if test="'delCity' in pro">checked</s:if>/>删除城市
						    <input type="checkbox" name="rolestring" id="rolestring" value="addCityPage" <s:if test="'addCityPage' in pro">checked</s:if>/>跳转到添加城市
					     </td>
					  </tr>
					  <tr>
					    <td style="width:120px;">便民管理</td>
					    <td>
					    	<input type="checkbox" name="rolestring" id="rolestring" value="selConvenient" <s:if test="'selConvenient' in pro">checked</s:if>/>查询便民
						    <input type="checkbox" name="rolestring" id="rolestring"  value="addConvenient" <s:if test="'addConvenient' in pro">checked</s:if>/>添加便民
						    <input type="checkbox" name="rolestring" id="rolestring" value="delConvenient" <s:if test="'delConvenient' in pro">checked</s:if>/>删除便民
						    <input type="checkbox" name="rolestring" id="rolestring" value="addConvenientPage" <s:if test="'addConvenientPage' in pro">checked</s:if>/>跳转到添加便民
						    <input type="checkbox" name="rolestring" id="rolestring" value="updateConvenientPage" <s:if test="'updateConvenientPage' in pro">checked</s:if>/>跳转到更新便民
						 	<input type="checkbox" name="rolestring" id="rolestring" value="selConvenientId" <s:if test="'selConvenientId' in pro">checked</s:if>/>查询便民详情
						 	<input type="checkbox" name="rolestring" id="rolestring" value="selConvenientDetail" <s:if test="'selConvenientDetail' in pro">checked</s:if>/>便民详情
						 	<input type="checkbox" name="rolestring" id="rolestring" value="addConvenientId" <s:if test="'addConvenientId' in pro">checked</s:if>/>添加便民详情
						    <input type="checkbox" name="rolestring" id="rolestring" value="addPage" <s:if test="'addPage' in pro">checked</s:if>/>进入添加便民详情
						    <input type="checkbox" name="rolestring" id="rolestring" value="updaePage" <s:if test="'updaePage' in pro">checked</s:if>/>进入修改便民详情
						     <input type="checkbox" name="rolestring" id="rolestring" value="delConvenientDetail" <s:if test="'delConvenientDetail' in pro">checked</s:if>/>删除便民详情
					     </td>
					  </tr>
					  <tr>
					    <td style="width:120px;">客户端管理</td>
					    <td>
					    	<input type="checkbox" name="rolestring" id="rolestring" value="selClient" <s:if test="'selClient' in pro">checked</s:if>/>查询客户端
						    <input type="checkbox" name="rolestring" id="rolestring"  value="addClient" <s:if test="'addClient' in pro">checked</s:if>/>添加客户端
						    <input type="checkbox" name="rolestring" id="rolestring" value="delClient" <s:if test="'delClient' in pro">checked</s:if>/>删除客户端
						    <input type="checkbox" name="rolestring" id="rolestring" value="addClientPage" <s:if test="'addClientPage' in pro">checked</s:if>/>跳转到添加客户端
						    <input type="checkbox" name="rolestring" id="rolestring" value="updateClientPage" <s:if test="'updateClientPage' in pro">checked</s:if>/>跳转到更新客户端
					     </td>
					  </tr>
					  <tr>
					    <td style="width:120px;">评论管理</td>
					    <td>
					    	<input type="checkbox" name="rolestring" id="rolestring" value="selComment" <s:if test="'selComment' in pro">checked</s:if>/>查询评论
						    <input type="checkbox" name="rolestring" id="rolestring" value="delComment" <s:if test="'delComment' in pro">checked</s:if>/>删除评论
					     </td>
					  </tr>
					 <tr>
					    <td style="width:120px;">收藏管理</td>
					    <td>
					    	<input type="checkbox" name="rolestring" id="rolestring" value="selCollect" <s:if test="'selCollect' in pro">checked</s:if>/>查询收藏
						    <input type="checkbox" name="rolestring" id="rolestring" value="delCollect" <s:if test="'delCollect' in pro">checked</s:if>/>删除收藏
					     </td>
					  </tr>
					  <tr>
					    <td style="width:120px;">商户会员</td>
					    <td>
						    <input type="checkbox" name="rolestring" id="rolestring"  value="addsellSerManag" <s:if test="'addsellSerManag' in pro">checked</s:if>/>添加商户会员
						    <input type="checkbox" name="rolestring" id="rolestring" value="selSellSerManag" <s:if test="'selSellSerManag' in pro">checked</s:if>/>查询商户会员
						    <input type="checkbox" name="rolestring" id="rolestring" value="delSellSerManag" <s:if test="'delSellSerManag' in pro">checked</s:if>/>删除商户会员
						    <input type="checkbox" name="rolestring" id="rolestring" value="sellerManagPage" <s:if test="'sellerManagPage' in pro">checked</s:if>/>跳转到添加商户
						    <input type="checkbox" name="rolestring" id="rolestring" value="updateSellerManagerPage" <s:if test="'updateSellerManagerPage' in pro">checked</s:if>/>跳转到修改商户
					     </td>
					  </tr>
					   <tr>
					    <td style="width:120px;">市场入住</td>
					    <td>
						    <input type="checkbox" name="rolestring" id="rolestring"  value="addBusmarSet" <s:if test="'addBusmarSet' in pro">checked</s:if>/>添加市场入住
						    <input type="checkbox" name="rolestring" id="rolestring" value="selBusmarSet" <s:if test="'selBusmarSet' in pro">checked</s:if>/>查询市场入住
						    <input type="checkbox" name="rolestring" id="rolestring" value="delBusmarSet" <s:if test="'delBusmarSet' in pro">checked</s:if>/>删除市场入住
						    <input type="checkbox" name="rolestring" id="rolestring" value="addBusmarSetPage" <s:if test="'addBusmarSetPage' in pro">checked</s:if>/>跳转到添加市场入住
						    <input type="checkbox" name="rolestring" id="rolestring" value="updateBusmarSetPage" <s:if test="'updateBusmarSetPage' in pro">checked</s:if>/>跳转到修改市场入住
						    <input type="checkbox" name="rolestring" id="rolestring" value="updaeSort" <s:if test="'updaeSort' in pro">checked</s:if>/>移动市场排序
					     </td>
					  </tr>
					  <tr>
					    <td style="width:120px;">市场账号管理</td>
					    <td>
					    	<input type="checkbox" name="rolestring" id="rolestring"  value="addMarketUser" <s:if test="'addBusmarSet' in pro">checked</s:if>/>添加市场用户
					    	<input type="checkbox" name="rolestring" id="rolestring"  value="addMarketUserPage" <s:if test="'addBusmarSet' in pro">checked</s:if>/>跳转到添加市场用户页面
						    <input type="checkbox" name="rolestring" id="rolestring"  value="selMarketUser" <s:if test="'addBusmarSet' in pro">checked</s:if>/>查询市场用户
						    <input type="checkbox" name="rolestring" id="rolestring"  value="delMarketUser" <s:if test="'addBusmarSet' in pro">checked</s:if>/>删除市场用户
					     </td>
					  </tr>
					  <tr>
					    <td style="width:120px;">商户房间维护</td>
					    <td>
						    <input type="checkbox" name="rolestring" id="rolestring"  value="selRoomPage" <s:if test="'addBusmarSet' in pro">checked</s:if>/>查询商户房间
						    <input type="checkbox" name="rolestring" id="rolestring"  value="addRoomPage" <s:if test="'addBusmarSet' in pro">checked</s:if>/>跳转添加房型页面
						    <input type="checkbox" name="rolestring" id="rolestring"  value="addRoom" <s:if test="'addBusmarSet' in pro">checked</s:if>/>添加房型
						    <input type="checkbox" name="rolestring" id="rolestring"  value="delRoom" <s:if test="'addBusmarSet' in pro">checked</s:if>/>删除房型
					     </td>
					  </tr>
					  <tr>
					    <td style="width:120px;">应用汇管理</td>
					    <td>
						    <input type="checkbox" name="rolestring" id="rolestring"  value="selRecommend" <s:if test="'selRecommend' in pro">checked</s:if>/>查询应用汇
						    <input type="checkbox" name="rolestring" id="rolestring"  value="delRecommend" <s:if test="'delRecommend' in pro">checked</s:if>/>删除应用汇
					     </td>
					  </tr>
					  <tr>
					    <td style="width:120px;">应用汇管理</td>
					    <td>
						    <input type="checkbox" name="rolestring" id="rolestring"  value="addRecommendPage" <s:if test="'addRecommendPage' in pro">checked</s:if>/>查询应用汇
						    <input type="checkbox" name="rolestring" id="rolestring"  value="addRecommend" <s:if test="'addRecommend' in pro">checked</s:if>/>查询应用汇
					     </td>
					  </tr>
					  <tr>
					    <td style="width:120px;">应用汇管理</td>
					    <td>
						    <input type="checkbox" name="rolestring" id="rolestring"  value="pageFind" <s:if test="'pageFind' in pro">checked</s:if>/>查询应用汇
						    <input type="checkbox" name="rolestring" id="rolestring"  value="findAll" <s:if test="'findAll' in pro">checked</s:if>/>查询应用汇
						    <input type="checkbox" name="rolestring" id="rolestring"  value="pageFindText" <s:if test="'pageFindText' in pro">checked</s:if>/>查询应用汇
					    
					     </td>
					  </tr>
			  </table>
 			 </td>
 			  <td class="text"></td>
			<td class="submit"><s:submit value="提交" class="submit"  theme="css_xhtml"/></td>
  		</tr>
   </table>
   </s:form>
  </body>
</html>
