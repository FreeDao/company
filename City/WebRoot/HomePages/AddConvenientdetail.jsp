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
    
    <title>添加便民详细</title>
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
    <table align="center">
    	<tr>
    		<td>
    			名称:
    		</td>
    		<td>
    			<input type="text" name="name" id="name" value="${model.name}">
    		</td>
    	</tr>
    	<tr>
    		<td>
    			电话:
    		</td>
    		<td>
    			<input type="text" name="phone" id="phone" value="${model.phone}">
    		</td>
    	<tr>
    		<td>
    			城市:
    		</td>
    		<td>
	    		<select name="city" id="ctiy">
	    			<s:iterator value="listCity" var = "d">
		    			<option value="${d.id}" onclick="return onType();">${d.cityName}</option>
	    			</s:iterator>
	    		</select>
	    		<input type="hidden" value="${model.id}" id = "id">
    		</td>
    	</tr>
    	<tr>
    		<td>
    			便民:
    		</td>
    		<td>
    		<select name="convenient" id="convenient">
    			
    		</select>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			地址:
    		</td>
    		<td>
    			<input type="text" name="addres" id="addres" value="${model.address}"/>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			提示:
    		</td>
    		<td class="yzm" id="yzm">
    			
    		</td>
    	</tr>
    	<tr align="center"><td colspan="2"><input type="button" value="确定" onclick="return yanzheng();" /></td></tr>
    </table>
    <script  type="text/javascript">
    
    	//联动
    	$(document).ready(function() 
		{
		var noCache = Date();
	    	var city = document.getElementById("ctiy").value;
	    	var url = "../City/news/cityConvenient.action";
	    	$.getJSON(url, {"ranum":Math.random(),"noCache":noCache,"city":city},function(data)
				{
					var cc='';
					for(var i = 0;data.msg.length>i;i++)
					{
						cc+='<option value="'+data.msg[i].id+'">'+data.msg[i].name+'</option>'
					}
					$("#convenient").html(cc);
				});
    	}); 
	   function  onType()
	   {
	   var noCache = Date();
	   	var city = document.getElementById("ctiy").value;
		    	var url = "../City/news/cityConvenient.action";
		    	$.getJSON(url, {"noCache":noCache,"city":city},function(data)
					{
						var cc='';
						for(var i = 0;data.msg.length>i;i++)
						{
							cc+='<option value="'+data.msg[i].id+'">'+data.msg[i].name+'</option>'
						}
						$("#convenient").html(cc);
					});
	   }
    
    	//判断是否为空
    	function yanzheng()
    	{
    		var name = document.getElementById("name").value;
    		var phone = document.getElementById("phone").value;
    		var ctiy = document.getElementById("ctiy").value;
    		var convenient = document.getElementById("convenient").value;
    		var addres = document.getElementById("addres").value;
    		var id = document.getElementById("id").value;
    		var zz = /^[0-9]*$/;
    		var sji = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/;
    		var dz = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
    		if(name == "")
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">名称不能为空</label>');
    			return false;
    		}
    		if(name.length>100)
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">名称过长</label>');
    			return false;
    		}
    		if(phone == "")
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">手机不能为空</label>');
    			return false;
    		}
    		if(dz.test(phone))
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">输入正确的手机号</label>');
    			return false;
    		}
    		if(addres == "")
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">地址不能为空</label>');
    			return false;
    		}
    		
    		
    		$.ajax({
				type: "post",
				url: "../City/news/addConvenientDetail.action",
				dataType:'json',
				data: "name="+name+"&phone="+phone+"&ctiy="+ctiy+"&convenient="+convenient+"&address="+addres+"&id="+id,//提交表单，相当于CheckCorpID.ashx?ID=XXX
				success: function(data)
				{
					if(data.mess == 1)
					{
						$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">地图未找到</label>');
					}
					else if(data.mess==2)
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
