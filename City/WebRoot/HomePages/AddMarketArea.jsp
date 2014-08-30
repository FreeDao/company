<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
		};
		$(obj).onblur = function(){
			if(this.value.length<1)this.value=this.defaultValue;
		};
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
    		<select name="cityId" id="cityId">
    			<c:forEach items="${listCity }" var="c">
    				<c:choose>
    					<c:when test="${c.id == area.cityId }"><option value="${c.id}" selected="selected">${c.cityName}</option></c:when>
    					<c:otherwise><option value="${c.id}">${c.cityName}</option></c:otherwise>
    				</c:choose>
    			</c:forEach>
    		</select>
    		</td>
    	</tr>
    	<tr>
    		<td>区域名称：</td>
    		<td>
    		<input type="hidden" name="id" id="id" value="${area.id}">
    		<input type="text" name="areaName" id="areaName" class="ip" value="${area.areaName}">
    	</tr>
    	<%--<tr>
    		<td>排序：</td>
    		<td>
    		<input type="text" name="sort" id="sort" class="ip" value="${model.sort}">
    	</tr>
    	--%><tr>
    		<td>提示：</td>
    		<td class="yzm" id="yzm">
    		</td>
    	</tr>
    	<tr align="center"><td colspan="2"><input type="submit" value="确定" onclick="return yz();"/></td></tr>
    </table>
    <script type="text/javascript">
    
    	function yz()
    	{
    		var areaName = document.getElementById("areaName").value;
    		var cityId = document.getElementById("cityId").value;
    		var id = document.getElementById("id").value;
    		var str = /^(0|[1-9][0-9]*)$/;
    		if(areaName == "")
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请输入区域名称</label>');
    			return false;
    		}
    		
    		$.ajax({
				type: "post",
				url: "../City/news/addMarketArea.action",
				dataType:'json',
				data: "areaName="+areaName+"&cityId="+cityId+"&id="+id,//提交表单，相当于CheckCorpID.ashx?ID=XXX
				success: function(data)
				{
					if(data.mess == 1)
					{
						$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">添加成功</label>');
					}
					else
					{
						$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">添加异常</label>');
					}
				}
			});
    		
    	}
    
    </script>
  </body>
</html>
