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
    
    <title>添加城市</title>
    <script src="js/jquery.js" type=text/javascript></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body>
  <form action="../City/System/addRoom.action" method="post" enctype="multipart/form-data">
    <table align="center">
    	<tr>
    		<td width="80px;">商户：</td>
    		<td>
    		<select name="sellerid" id="sellerid">
		        <s:iterator value="listSeller" var ="ls">
		        	<option value="${ls[0]}"  >${ls[1]}</option>
		        </s:iterator>
	        </select>
    		</td>
    	</tr>
    	 <tr>
    	 <td>房型名称：</td>
    		<td>
	    	<input type="text" name ="name" id="name"  >
	        </td>
        </tr>
    	<tr>
    		<td>价格：</td>
    		<td>
    		<input type="text" name ="price" id="price"  >
    		</td>
    	</tr>
    	<tr>
    		<td>图片：</td>
    		<td>
    		<input type="file" name="picture" id="aa"/>
    		<input type="hidden" id="bb" name="logo">
    	</tr>
    	<tr>
    		<td>房间介绍：</td>
    		<td>
    		<textarea rows="70" cols="70" name="brief" id="brief"></textarea>
    	</tr>
    	<tr>
    		<td>提示：</td>
    		<td class="yzm" id="yzm">
    		</td>
    	</tr>
    	<tr align="center"><td colspan="2"><input type="submit" value="确定" onclick="return yz();"/></td></tr>
    </table>
   </form>
    <script type="text/javascript">
    
    	function yz()
    	{
    		var name = document.getElementById("name").value;
    		var price = document.getElementById("price").value;
    		if(name == "" )
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请输入房型名称</label>');
    			return false;
    		}
    		if(price == "" )
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请选输入房间价格</label>');
    			return false;
    		}
    	}
    
    </script>
  </body>
</html>
