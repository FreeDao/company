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
    
    <title>添加客户端</title>
    
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
  <form action="../City/System/addClient.action" enctype="multipart/form-data" method="post">
    <table align="center">
    	<tr>
    		<td>
    			市场
    		</td>
    		<td>
    			<select name="market">
    				<s:iterator value="listMarket" var="d">
    				<option value="${d.id}">${d.type}</option>
    				</s:iterator>
    			</select>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			客户端名称
    		</td>
    		<td>
    			<input type="text" name = "name" id="name" value="${model.name}">
    		</td>
    	</tr>
    	<tr>
    		<td>
    			客户端简介
    		</td>
    		<td>
    			<input type="text" name="conent" id="conent" value="${model.conent}"/>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			下载地址
    		</td>
    		<td>
    			<input type="text" name="url" id="url" value="${model.url}">
    		</td>
    	</tr>
    	<tr>
    		<td>
    			城市
    		</td>
    		<td>
	    		<select name="city" id="city">
	    			<s:iterator value="listCity" var="d">
	    				<option value="${d.id}">${d.cityName}</option>
	    			</s:iterator>
	    		</select>
	    		<input type="hidden" value="${model.id}" name="id">
    		</td>
    	</tr>
    	<tr>
    		<td>
    			图片地址：
    		</td>
    		<td>
    			<input type="file" name="picture" id="a"/>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			二维码地址：
    		</td>
    		<td>
    			<input type="file" name="qrurl" id="aa"/>
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
    <script  type="text/javascript">
    	//判断是否为空
    	function yanzheng()
    	{
    		var name = document.getElementById("name").value;
    		var conent = document.getElementById("conent").value;
    		var url = document.getElementById("url").value;
    		
    		if(name == "")
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">未输入名称</label>');
    			return false;
    		}
    		if(conent == "")
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请输入内容</label>');
    			return false;
    		}
    		if(url == "")
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请输入下载地址</label>');
    			return false;
    		}
    	}
    </script>
  </body>
</html>
