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
   <s:form name="rolesAdd" action="../era/System/addRoles.action" method="post" namespace="/System"  theme="css_xhtml" validate="true">
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
				<table style="font-size:12px;width:500px;">
					  <tr>
					    <td style="width:120px;">角色管理</td>
					    <td>
						    <input type="checkbox" name="rolestring" id="rolestring"  value="pagesRoles" <s:if test="'pagesRoles' in pro">checked</s:if>/>进入修改角色
						    <input type="checkbox" name="rolestring" id="rolestring" value="addRoles" <s:if test="'addRoles' in pro">checked</s:if>/>修改角色
						     <input type="checkbox" name="rolestring" id="rolestring" value="selRoles" <s:if test="'selRoles' in pro">checked</s:if>/>查询角色
						     <input type="checkbox" name="rolestring" id="rolestring" value="rolesAdd" <s:if test="'rolesAdd' in pro">checked</s:if>/>添加角色
					   		<input type="checkbox" name="rolestring" id="rolestring" value="pageRolesAdd" <s:if test="'pageRolesAdd' in pro">checked</s:if>/>进入添加角色
					     </td>
					  </tr>
					  <tr>
					    <td style="width:120px;">管理员管理</td>
					    <td>
						    <input type="checkbox" name="rolestring" id="rolestring"  value="addAdminUser" <s:if test="'addAdminUser' in pro">checked</s:if>/>添加管理员
						    <input type="checkbox" name="rolestring" id="rolestring" value="pagesAdminUser" <s:if test="'pagesAdminUser' in pro">checked</s:if>/>跳转到添加管理员
						    <input type="checkbox" name="rolestring" id="rolestring" value="delectAdminUser" <s:if test="'delectAdminUser' in pro">checked</s:if>/>删除管理员
						    <input type="checkbox" name="rolestring" id="rolestring" value="adminUser" <s:if test="'adminUser' in pro">checked</s:if>/>查询管理员
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
