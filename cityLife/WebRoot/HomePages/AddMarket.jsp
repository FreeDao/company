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
    
    <title>添加城市</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js" type=text/javascript></script>
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
		width:300px;
		height:210px;
		}
	</style>
  </head>
 <script type="text/javascript">
 	window.onload = function()
		{
			J.onBlurText('type');
			J.onBlurText('sort');
			createCode();
		};
		(function(){
	if(!window.J){ window.J = {};}
 /**
	 * 得到obj
	 */
	function $(){
		var elements = [];
		for(var i=0; i<arguments.length; i++){
			var element = arguments[i];
			if(typeof element == 'string'){element = document.getElementById(element);}
			if(arguments.length == 1){
				return element;
			}
			elements.push(element);
		}
		return elements;		
	}
	window.J.$ = $;
	
	/**
	 * 失去焦点
	 * @param {Object} obj
	 */
	function onBlurText(obj){
		$(obj).onfocus = function(){
			if(this.value==this.defaultValue)this.value='';
		}
		$(obj).onblur = function(){
			if(this.value.length<1)this.value=this.defaultValue;
		}
	}
	window.J.onBlurText = onBlurText;
	
	function objNull(obj){
		if ($(obj).value.length < 1) {
			alert('aa');
			return false;
		}
	}
	window.J.objNull = objNull;
	
	
})();
 </script>
  <body>
    <table align="center">
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
    		<td>市场类型：</td>
    		<td>
    		<input type="text" name="type" id="type" class="ip" value="请输入市场类型">
    	</tr>
    	<tr>
    		<td>排序：</td>
    		<td>
    		<input type="text" name="sort" id="sort" class="ip" value="请输入数字">
    	</tr>
    	<tr>
    		<td>提示：</td>
    		<td>
    		<label class="userNameOne" id="yzm" style="color: red;"></label>
    		</td>
    	</tr>
    	<tr align="center"><td colspan="2"><input type="submit" value="确定" onclick="return yz();"/></td></tr>
    </table>
    <script type="text/javascript">
    
    	function yz()
    	{
    		var type = document.getElementById("type").value;
    		var sort = document.getElementById("sort").value;
    		var city = document.getElementById("city").value;
    		var str = /^(0|[1-9][0-9]*)$/;
    		if(type == "请输入市场类型")
			{
				document.getElementById("yzm").innerHTML="请输入市场类型!";
				return false;
			}
			else
			{
				document.getElementById("yzm").innerHTML="";
			}
    		if(sort == "请输入数字" || !str.test(sort))
			{
				document.getElementById("yzm").innerHTML="排序不能为空!";
				return false;
			}
			else
			{
				document.getElementById("yzm").innerHTML="";
			}
    		var url = "../era/news/adminLogin.action";
			$.getJSON(url, {"ranum":Math.random(),"name":name,"pwd":pwd},function(data)
			{
				document.getElementById("yzm").innerHTML=data.msg;
				if(data.msg == "登陆成功")
				{
					document.getElementById("yzm").innerHTML="";
					location.href="../era/System/pagesLogin.action";
				}
			});	
    	}
    
    </script>
  </body>
</html>
