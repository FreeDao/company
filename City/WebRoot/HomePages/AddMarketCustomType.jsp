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
    <script src="js/jquery-2.0.3.min.js" type=text/javascript></script>
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
    		<select name="cityId" id="cityId" onchange="cityChange()">
    			<c:forEach items="${listCity }" var="c">
    				<c:choose>
    					<c:when test="${c.id == cityId }"><option value="${c.id}" selected="selected">${c.cityName}</option></c:when>
    					<c:otherwise><option value="${c.id}">${c.cityName}</option></c:otherwise>
    				</c:choose>
    			</c:forEach>
    		</select>
    		</td>
    	</tr>
    	<tr>
    		<td>市场：</td>
    		<td>
    		<select name="marketId" id="marketId">
    			<%-- <c:forEach items="${marketList }" var="m">
    				<c:choose>
    					<c:when test="${m.id == customType.marketId }"><option value="${m.id}" selected="selected">${m.type}</option></c:when>
    					<c:otherwise><option value="${m.id}">${m.type}</option></c:otherwise>
    				</c:choose>
    			</c:forEach> --%>
    		</select>
    		</td>
    	</tr>
    	<tr>
    		<td>行业类别名称：</td>
    		<td>
    		<input type="hidden" name ="id" id="id" value="${customType.id}">
    		<input type="text" name="name" id="name" class="ip" value="${customType.name}">
    	</tr>
    	<%-- <tr>
    		<td>排序：</td>
    		<td>
    		<input type="text" name="sort" id="sort" class="ip" value="${model.sort}">
    	</tr> --%>
    	<tr>
    		<td>提示：</td>
    		<td class="yzm" id="yzm">
    		</td>
    	</tr>
    	<tr align="center"><td colspan="2"><input type="submit" value="确定" onclick="return yz();"/></td></tr>
    </table>
    <script type="text/javascript">
	   	$(function(){
	    	$.ajax({
				type: "post",
				url: "../City/news/selSellerMarket.action",
				data:{city : $("#cityId").val()},
				beforeSend: function(XMLHttpRequest){
					//ShowLoading();
				},
				success: function(data, textStatus){
					$.each(data.msg,function(index,item){
						if(item.id == "${customType.marketId}"){
							$("#marketId").append("<option value="+item.id+" selected='selected' >"+item.type+"</option>");
						}else{
							$("#marketId").append("<option value="+item.id+">"+item.type+"</option>");
						}
					});
				},
				complete: function(XMLHttpRequest, textStatus){
					pageNow++;
					//HideLoading();
				},
				error: function(){
					//请求出错处理
				}
			});
	    });
    
    	function cityChange(){
    		$.ajax({
				type: "post",
				url: "../City/news/selSellerMarket.action",
				data:{city : $("#cityId").val()},
				beforeSend: function(XMLHttpRequest){
					//ShowLoading();
				},
				success: function(data, textStatus){
					$("#marketId").html("");
					$.each(data.msg,function(index,item){
						$("#marketId").append("<option value="+item.id+">"+item.type+"</option>");
					});
				},
				complete: function(XMLHttpRequest, textStatus){
					pageNow++;
					//HideLoading();
				},
				error: function(){
					//请求出错处理
				}
			});
    	}
    
    	function yz()
    	{
    		var name = document.getElementById("name").value;
    		var marketId = document.getElementById("marketId").value;
    		var id = document.getElementById("id").value;
    		var str = /^(0|[1-9][0-9]*)$/;
    		if(name == "")
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请输入行业名称</label>');
    			return false;
    		}
    		
    		$.ajax({
				type: "post",
				url: "../City/news/addMarketCustomType.action",
				dataType:'json',
				data: "name="+name+"&marketId="+marketId+"&id="+id,//提交表单，相当于CheckCorpID.ashx?ID=XXX
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
