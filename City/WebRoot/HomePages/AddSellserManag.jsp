<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新增商户</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="js/jquery.js" type=text/javascript></script>
	<style type="text/css">
	table{
		width:400px;
		height:400px;
		}
	</style>
  </head>
  
  <body>
    <form action="../City/System/addsellSerManag.action" method="post" enctype="multipart/form-data">
    <table align="center">
    	<tr>
    		<td>
    			商户账号:
    		</td>
    		<td>
    			<input type="text" name="userName" id="userName" value="${model.userName}">
    			<input type="hidden" name="id" value="${model.id}">
    		</td>
    	</tr>
    	<tr>
    		<td>
    			商户QQ:
    		</td>
    		<td>
    			<input type="text" name="qq" id="qq" value="${model.qq}"/>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			商家手机:
    		</td>
    		<td>
    			<input type="text" name="phone" id="phone" value="${model.telePhone}"/>
    			<input type="hidden" name = "passWord" value="${model.userPwd}">
    		</td>
    	</tr>
    	<tr>
    		<td>
    			商户邮箱:
    		</td>
    		<td>
	    		<input type="text" name="email" id="email" value="${model.email}" />
    		</td>
    	</tr>
    	<tr>
    		<td>
    			商户:
    		</td>
    		<td>
    			<select name="sellerId">
	    			<c:forEach items="${listSeller }" var="ls">
		    			<c:choose>
		    				<c:when test="${ls.id == model.sellerId }"><option value="${ls.id }" selected="selected">${ls.titile }</option></c:when>
		    				<c:otherwise><option value="${ls.id }">${ls.titile }</option></c:otherwise>
		    			</c:choose>
	    			</c:forEach>
    			</select>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			提示:
    		</td>
    		<td class="yzm" id="yzm">
    		</td>
    	</tr>
    	<tr align="center"><td colspan="2"><input type="submit" value="确定" onclick="return yanzheng();" /></td></tr>
    </table>
    </form>
    <script type="text/javascript">
    	function yanzheng()
    	{
    		var userName = document.getElementById("userName").value;
    		var passWord = document.getElementById("passWord").value;
    		var qq = document.getElementById("qq").value;
    		var phone = document.getElementById("phone").value;
    		var email = document.getElementById("email").value;
    		var sji = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/;
    		if(userName == "")
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请输入商户账号！</label>');
    			return false;
    		}
    		if(passWord == "")
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请输入商户密码！</label>');
    			return false;
    		}
    		if(qq == "")
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请输入QQ！</label>');
    			return false;
    		}
    		if(!sji.test(phone))
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请输入正确的手机号！</label>');
    			return false;
    		}
    		if(email == "")
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请输入邮箱！</label>');
    			return false;
    		}
    	}
    </script>
  </body>
</html>
