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
    <title>添加商户</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="../City/js/jquery-1.7.1.js" type=text/javascript></script>
	<style type="text/css">
	table{
		width:400px;
		height:400px;
		}
	</style>

  </head>
  
  <body>
  <form action="../City/System/addSeller.action" method="post" enctype="multipart/form-data">
  <input type="hidden" name="sort" id="sort" value="${model.sort }"/>
  <input type="hidden" name="sortTime" id="sortTime" value="${model.sortTime }"/>
    <table align="center">
    	<tr>
    		<td>
    			商户名:
    		</td>
    		<td>
    			<input type="text" name="title" id="name" value="${model.titile}">
    		</td>
    	</tr>
    	<tr>
    		<td>
    			公司Logo:
    		</td>
    		<td>
    		<!-- 上传图片 -->
    			<input type="file" name="picture" id="aa"/>
    			<input type="hidden" id="bb" name="logo" value="${model.logo}">
    		</td>
    	</tr>
    	<tr>
    		<td>
    			手&nbsp;&nbsp;&nbsp;&nbsp;机:
    		</td>
    		<td>
    			<input type="text" name="phone" id="phone" value="${model.phone}"/>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			商家介绍:
    		</td>
    		<td>
    		<textarea rows="70" cols="70" name="brief" id="brief">${model.brief}</textarea>
    			<input type="text" name="brief" value="${model.brief}" id="brief" height="800" width="800">
    		</td>
    	</tr>
    	<tr>
    		<td>
    			优惠:
    		</td>
    		<td>
	    		<input type="text" name="preferential" value="${model.preferential}" id="preferential">
    		</td>
    	</tr>
    	<tr>
    		<td>
    			地址:
    		</td>
    		<td>
    		<input type="text" name="address" id="address" value="${model.address}">
    		<input type="hidden" name="product" value="${model.productId}">
    		<input type="hidden" name="image" value="${model.imgesId}">
    		<input type="hidden" name="id" value="${model.id}">
    		</td>
    	</tr>
    	<tr>
    		<td>
    			城市:
    		</td>
    		<td>
    			<select name="city" id="city" onchange="onType()">
    				<c:forEach items="${listCity }" var="ls" >
    					<c:choose>
    						<c:when test="${model.cityId == ls.id }"><option value="${ls.id}" selected="selected">${ls.cityName}</option></c:when>
    						<c:otherwise><option value="${ls.id}" >${ls.cityName}</option></c:otherwise>
    					</c:choose>
    				</c:forEach>
    				
    			</select>
    			
    		</td>
    	</tr>
    	<tr>
    		<td>
    			区域:
    		</td>
    		<td class="area" id="area">
    			<select name="areaid" id="areaid">
    			</select>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			市场:
    		</td>
    		<td class="market" id="market"  >
    			<select name="typeid" id="typeid" onchange="onMarketType()">
    				<option value="${ma.id }" selected="selected">${ma.type }</option>
    			</select>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			行业类别:
    		</td>
    		<td >
    			<select name="customType" id="customType">
    			</select>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			房间类型:
    		</td>
    		<td class="roomtype" id="roomtype">
    				<input type="text" name="type" id="type" value="${model.type}">
    		</td>
    	</tr>
    	<tr>
    		<td>
    			酒店类型:
    		</td>
    		<td class="holtel" id="holtel">
    			<input type="text" name="hotelType" id="hotelType" value="${model.hotelType}">
    		</td>
    	</tr>
    	<tr>
    		<td>
    			价钱:
    		</td>
    		<td class="price" id="prices">
    			<input type="text" name="price" id="price" value="${model.price}">
    		</td>
    	</tr>
    	<tr>
    		<td>
    			等级:
    		</td>
    		<td class="level" id="levels">
    			<input type="text" name="level" id="level" value="${model.level}">
    		</td>
    	</tr>
    	<tr>
    		<td>
    			是否推送
    		</td>
    		<td>
    			<select name="isHone">
    				<option value="no">不推送</option>
    				<option value="ok">推送</option>
    			</select>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			经纬度
    		</td>
    		<td>
    			经度<input type="text" name="log" id="log" value="${model.log}">
    			纬度<input type="text" name="lat" id="lat" value="${model.dim}">
    		</td>
    	</tr>
    	<tr>
    		<td>
    			活动:
    		</td>
    		<td>
    			<select name="act">
    				<option value="0">暂无活动</option>
    				<s:iterator value="listAct" var="d">
    					<option value="${d.id}">${d.title }</option>
    				</s:iterator>
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
    	//联动
    	$(document).ready(function() 
		{
		var noCache = Date();
	    	var city = document.getElementById("city").value;
	    	var url = "../City/news/selSellerMarket.action";
	    	$.getJSON(url, {"ranum":Math.random(),"noCache":noCache,"city":city},function(data)
				{
					var cc='';
					for(var i = 0;data.msg.length>i;i++)
					{
						if("${ma.id }" == data.msg[i].id){
							cc+='<option value="'+data.msg[i].id+'" selected="selected">'+data.msg[i].type+'</option>';
						}else{
							cc+='<option value="'+data.msg[i].id+'">'+data.msg[i].type+'</option>';
						}
						
					}
					$("#typeid").html(cc);
					
					
					var market = document.getElementById("typeid");
					$.getJSON("../City/news/selSellerCustomType.action", {"noCache":noCache,"marketId":market.value},function(data)
					{
						var cc='';
						for(var i = 0;data.length>i;i++)
						{
							if("${ct.id}" == data[i].id){
								cc+='<option value="'+data[i].id+'" selected="selected" >'+data[i].name+'</option>';
							}else{
								cc+='<option value="'+data[i].id+'">'+data[i].name+'</option>';
							}
						}
						$("#customType").html(cc);
					});
				});
				url = "../City/news/selSellerArea.action";
		    	$.getJSON(url, {"noCache":noCache,"city":city},function(data)
					{
						var cc='';
						for(var i = 0;data.msg.length>i;i++)
						{
							if("${ar.id}" == data.msg[i].id){
								cc+='<option value="'+data.msg[i].id+'" selected="selected" >'+data.msg[i].areaName+'</option>';
							}else{
								cc+='<option value="'+data.msg[i].id+'">'+data.msg[i].areaName+'</option>';
							}
						}
						$("#areaid").html(cc);
					});
					
					
    	}); 
    	
	   function  onType()
	   {
	   var noCache = Date();
	   	var city = document.getElementById("city").value;
		    	var url = "../City/news/selSellerMarket.action";
		    	$.getJSON(url, {"noCache":noCache,"city":city},function(data)
					{
						var cc='';
						for(var i = 0;data.msg.length>i;i++)
						{
							cc+='<option value="'+data.msg[i].id+'">'+data.msg[i].type+'</option>'
						}
						$("#typeid").html(cc);
						
						var market = document.getElementById("typeid");
					    var url = "../City/news/selSellerCustomType.action";
					    	$.getJSON(url, {"noCache":noCache,"marketId":market.value},function(data)
								{
									var cc='';
									for(var i = 0;data.length>i;i++)
									{
										cc+='<option value="'+data[i].id+'">'+data[i].name+'</option>'
									}
									$("#customType").html(cc);
								});
						
					});
					
		    	url = "../City/news/selSellerArea.action";
		    	$.getJSON(url, {"noCache":noCache,"city":city},function(data)
					{
						var cc='';
						for(var i = 0;data.msg.length>i;i++)
						{
							cc+='<option value="'+data.msg[i].id+'">'+data.msg[i].areaName+'</option>'
						}
						$("#areaid").html(cc);
					});
	   }
	   
	   function onMarketType(){
		   	var noCache = Date();
		   	var market = document.getElementById("typeid");
		    var url = "../City/news/selSellerCustomType.action";
		    	$.getJSON(url, {"noCache":noCache,"marketId":market.value},function(data)
					{
						var cc='';
						for(var i = 0;data.length>i;i++)
						{
							cc+='<option value="'+data[i].id+'">'+data[i].name+'</option>'
						}
						$("#customType").html(cc);
					});
					
	   }
	   
    	//判断是否为空
    	function yanzheng()
    	{
    		var name = document.getElementById("name").value;
    		var phone = document.getElementById("phone").value;
    		var brief = document.getElementById("brief").value;
    		var preferential = document.getElementById("preferential").value;
    		var address = document.getElementById("address").value;
    		var level = document.getElementById("level").value;
    		var sji = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/;
    		
    		if(name == "")
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">商户名！</label>');
    			return false;
    		}
    		if(phone == "")
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请输入手机号！</label>');
    			return false;
    		}
    		if(!sji.test(phone))
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请输入正确的手机号码！</label>');
    			return false;
    		}
    		if(brief == "")
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请输入商家介绍！</label>');
    			return false;
    		}
    		if(preferential == "")
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请输入优惠信息！</label>');
    			return false;
    		}
    		
    		if(address == "")
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请输入地址！</label>');
    			return false;
    		}
    		
    		if(level == "")
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请输入等级！</label>');
    			return false;
    		}
    		
    	}
    </script>
  </body>
</html>
