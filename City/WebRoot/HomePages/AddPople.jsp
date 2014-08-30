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
    
    <title>添加便民</title>
     <script src="js/jquery.js" type=text/javascript></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<style type="text/css">
	table{
		width:400px;
		height:400px;
		}
	</style>

  </head>
  
  <body>
  <form action="../City/System/addConvenientId.action" method="post" enctype="multipart/form-data">
    <table align="center">
    	<tr>
    		<td>
    			名称:
    		</td>
    		<td>
    			<input type="text" name="adminName" id="nickname">
    		</td>
    	</tr>
    	<tr>
    		<td>
    			图片：
    		</td>
    		<td>
    			<input type="file" name="picture" id="aa"/>
    		</td>
    	</tr>
    	<tr>
    		<td>城市名：</td>
    		<td>
    		<select name="city" id="city">
    			<s:iterator value="listCity" var="d">
    				<option value="${d.id}">${d.cityName}</option>
    			</s:iterator>
    		</select>
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
    <script  type="text/javascript">
    	//判断是否为空
    	function yanzheng()
    	{
    		var nickname = document.getElementById("nickname").value;
    		if(nickname == "")
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请输入用户名</label>');
    			return false;
    		}
    		if(nickname.length>10)
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">用户名过长</label>');
    			return false;
    		}
    	}
    </script>
  </body>
</html>
