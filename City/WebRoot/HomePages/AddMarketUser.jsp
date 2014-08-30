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



 function  onType()
	   {
	   	var city = document.getElementById("city").value;
			if(city == "c")
			{
				var cc='';
				cc+='<option value="">未选择</option>'
				$("#type").html(cc);
			}
			else
			{
				var url = "../City/news/selSellerMarket.action";
		    	$.getJSON(url, {"ranum":Math.random(),"city":city},function(data)
					{
						var cc='';
						for(var i = 0;data.msg.length>i;i++)
						{
							cc+='<option value="'+data.msg[i].id+'">'+data.msg[i].type+'</option>'
						}
						$("#type").html(cc);
					});
			}
	   }
 </script>
  <body>
    <table align="center">
    	<tr>
    		<td>用户名：</td>
    		<td>
    		<input type="text" name ="username" id="username"  >
    		</td>
    	</tr>
    	 <tr>
    	 <td>城市</td>
    		<td>
	    	 <select name="city" id="city">
	        	<option value="c"  onclick="return onType();">未选择</option>
		        <s:iterator value="listCity" var ="d">
		        	<option value="${d.id}"  onclick="return onType();">${d.cityName}</option>
		        </s:iterator>
	        </select>
	        </td>
        </tr>
    	<tr>
    		<td>市场类型：</td>
    		<td>
    		<select name="typeId" id="type">
    			<option value="">未选择</option>
    		<%-- <c:forEach items="${ms }" var="m">
    			<option value="${m.id}">${m.type}</option>
    		</c:forEach> --%>
    		</select>
    		</td>
    	</tr>
    	<tr>
    		<td>昵称：</td>
    		<td>
    		<input type="text" name ="qq" id="qq" >
    	</tr>
    	<tr>
    		<td>电话：</td>
    		<td>
    		<input type="text" name="telphone" id="telphone">
    	</tr>
    	<tr>
    		<td>email：</td>
    		<td>
    		<input type="text" name="email" id="email">
    		</td>
    	</tr>
    	<tr>
    		<td>
    			增删改权限：
    		</td>
    		<td>
    			<select name="root" id = "root">
    				<option value = 0>无</option>
    				<option value = 1>有</option>
    			</select>
    		</td>
    	</tr>
    	<tr>
    		<td>提示：</td>
    		<td class="yzm" id="yzm">
    		</td>
    	</tr>
    	<tr align="center"><td colspan="2"><input type="submit" value="确定" onclick="return yz();"/></td></tr>
    </table>
    <script type="text/javascript">
    
    	function yz()
    	{
    		var username = document.getElementById("username").value;
    		var type = document.getElementById("type");
    		var email = document.getElementById("email").value;
    		var telphone = document.getElementById("telphone").value;
    		var qq = document.getElementById("qq").value;
    		var root = document.getElementById("root").value;
    		var str = /^(0|[1-9][0-9]*)$/;
    		if(username == "" )
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请输入用户名</label>');
    			return false;
    		}
    		if(type.value == "" )
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请选择市场类型</label>');
    			return false;
    		}
    		
    		$.ajax({
				type: "post",
				url: "../City/news/addMarketUser.action",
				dataType:'json',
				data: "type="+type.value+"&username="+username+"&email="+email+"&telphone="+telphone+"&qq="+qq+"&root="+root,//提交表单，相当于CheckCorpID.ashx?ID=XXX
				success: function(data)
				{
					if(data.mess == 1)
					{
						$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">该用户已存在！</label>');
					}
					else if(data.mess==2)
					{
						type.remove(type.selectedIndex);
						$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">添加成功</label>');
					}
					else if(data.mess==3)
					{
						$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">添加异常</label>');
					}
					else{
						$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">该类型已存在！</label>');
					}
				}
			});
    		
    	}
    
    </script>
  </body>
</html>
