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
    
    <title>添加市场入住</title>
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
  <form id="login" name="" action="../City/System/addBusmarSet.action" enctype="multipart/form-data"
		method="post">
    <table align="center">
    	<tr>
    		<td>
    			用&nbsp;户&nbsp;名:
    		</td>
    		<td>
    			<input type="text" name="bmsUserName" id="bmsUserName" value="${model.bmsUserName}">
    		</td>
    	</tr>
    	<tr>
    		<td>
    			电话号码:
    		</td>
    		<td>
    			<input type="text" name="Telephone" id="Telephone" value="${model.telephone}"/>
    			<input type="text" name="id" id="Telephone" value="${model.id}"/>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			市场介绍：
    		</td>
    		<td>
    			<input type="text" name="bmsIntroduction" id="bmsIntroduction" value="${model.bmsIntroduction}"/>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			城市：
    		</td>
    		<td>
    			<select name="city" id="city">
	        <s:iterator value="listCity" var ="d">
	        	<option value="${d.id}"  onclick="return onType();">${d.cityName}</option>
	        </s:iterator>
        </select>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			类型：
    		</td>
    		<td>
    			<select name="marketId" id="typeid">
    			
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
    <script  type="text/javascript">
    	//判断是否为空
    	function yanzheng()
    	{
    		var bmsUserName = document.getElementById("bmsUserName").value;
    		var Telephone = document.getElementById("Telephone").value;
    		var bmsIntroduction = document.getElementById("bmsIntroduction").value;
    		
    		var sji = /(^(\d{3,4}-)?\d{7,8})$|(13[0-9]{9})/;
    		if(bmsUserName == "")
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请输入用户名</label>');
    			return false;
    		}
    		if(bmsIntroduction == "")
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请输入市场简介</label>');
    			return false;
    		}
    		if(Telephone == "")
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请输入电话</label>');
    			return false;
    		}
    		
		} 
		$(document).ready(function() 
		{
	    	var city = document.getElementById("city").value;
	    	var url = "../City/news/selSellerMarket.action";
	    	$.getJSON(url, {"ranum":Math.random(),"city":city},function(data)
				{
					var cc='';
					for(var i = 0;data.msg.length>i;i++)
					{
						cc+='<option value="'+data.msg[i].id+'">'+data.msg[i].type+'</option>'
					}
					$("#typeid").html(cc);
				});
    	}); 
    	 function  onType()
	   {
	   	var city = document.getElementById("city").value;
			
				var url = "../City/news/selSellerMarket.action";
		    	$.getJSON(url, {"ranum":Math.random(),"city":city},function(data)
					{
						var cc='';
						for(var i = 0;data.msg.length>i;i++)
						{
							cc+='<option value="'+data.msg[i].id+'">'+data.msg[i].type+'</option>'
						}
						$("#typeid").html(cc);
					});
	   }
    </script>
  </body>
</html>
