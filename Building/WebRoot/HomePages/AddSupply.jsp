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
    <title>修改供求信息</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js" type=text/javascript></script>
 	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/WdatePicker.js"></script>
 	
 	<script type="text/javascript">
 	function init(){
 		var myType = document.getElementById("myType");
 		if("${supply.statue}" == 1){
 			myType.style.display = "table-row";
 		}else{
 			myType.style.display = "none";
 		}
 	}
 	
 	</script>
  <body onload="init()">
    <div id="alls">
         <div class="aso"><img src="../Building/images/aou.png" width="90" height="70" /></div>
              <div class="update">供求管理</div>
              <div class="bg">
              <form enctype="multipart/form-data" method="post" action="System/addSupply.action" onsubmit="return updateSeller()">
              <input type="hidden" value="${supply.id}" name = "id" id="supply">
                  <table>
                     <tr>
                        <td align="right">标题:</td>
                        <td><input type="text" id="title" size="15" name="title" value="${supply.title}"/><span class=title ></span></td>
                     </tr>
                     <tr>
                        <td align="right">价格:</td>
                        <td><input type="text" id="price" size="15" name="price" value="${supply.price}"/><span class=price ></span></td>
                     </tr>
                     <tr>
			    		<td align="right">所属城市:</td>
			    		<td>
			    			<select name="city" id="city" style="width: 120px;">
			    				<option value="0">请选择</option>
			    				<c:forEach items="${city_list }" var="c">
			    					<c:choose>
			    						<c:when test="${c.id == supply.cityId}"><option value="${c.id}" selected="selected">${c.cityName}</option></c:when>
			    						<c:otherwise><option value="${c.id}">${c.cityName}</option></c:otherwise>
			    					</c:choose>
			    				</c:forEach>
			    			</select>
			    			<span class=city ></span>
			    		</td>
    					</tr>
    					<tr>
				    		<td align="right">所属行业:</td>
				    		<td>
				    			<select name="typeId" id="typeId" style="width: 120px;">
				    				<option value="0">请选择</option>
				    				<c:forEach items="${type_list1 }" var="t">
				    					<c:choose>
				    						<c:when test="${t.id == supply.typeId}"><option value="${t.id}" selected="selected">${t.name}</option></c:when>
				    						<c:otherwise><option value="${t.id}" >${t.name}</option></c:otherwise>
				    					</c:choose>
				    				</c:forEach>
				    			</select>
				    			<span class=typeId ></span>
				    		</td>
    					</tr>
    					<tr>
	                       <td align="right">所属类型：</td>
	                       <td>
	                       		<select name="type" id="type" style="width: 120px;" onchange="showType(this)">
		                       		<option value="0">请选择</option>
		                       		<s:if test="%{supply.statue == 1}" >
					    				<option value="1" selected="selected" >供求</option>
		                       			<option value="2">矿业</option>
					    			</s:if>
					    			<s:else>
					    				<option value="1">供求</option>
		                       			<option value="2" selected="selected" >矿业</option>
					    			</s:else>
	                       		</select>
	                       		<script type="text/javascript">
	                       		function showType(t){
	                       			var myType = document.getElementById("myType");
	                       			if(t.value == 1){
	                       				
	                       				myType.style.display = "table-row";
	                       			}else{
	                       				myType.style.display = "none";
	                       			}
	                       		}
	                       		</script>
	                       		<span class=type ></span>
	                       </td>
	                    </tr>
	                    <tr>
	                        <td align="right">所属商铺:</td>
	                        <td>
		                        <select name="sellerId" id="sellerId" style="width: 120px;">
				    				<option value="0">请选择</option>
				    				<c:forEach items="${seller_list1 }" var="s">
				    					<c:choose>
				    						<c:when test="${s.id == supply.sellerId}"><option value="${s.id}" selected="selected">${s.sellerName}</option></c:when>
				    						<c:otherwise><option value="${s.id}" >${s.sellerName}</option></c:otherwise>
				    					</c:choose>
				    				</c:forEach>
				    			</select>
				    			<span class=sellerId ></span>
				    		</td>
                     	</tr>
    					<tr id="myType">
	                        <td align="right">求购或供应:</td>
	                        <td>
	                        <select id="statue" name="statue" style="width: 120px;">
				    				<option value="0">请选择</option>
				    				<s:if test="%{supply.statue == 1}" >
					    				<option value="1" selected="selected">供应</option>
					    				<option value="2">需求</option>
					    			</s:if>
					    			<s:else>
					    				<option value="1" >供应</option>
					    				<option value="2" selected="selected">需求</option>
					    			</s:else>
				    			</select><%--
	                        <input type="text" id="statue" size="15" name="statue" value="${supply.statue}"/>
	                        --%>
	                        <span class=statue ></span>
	                        </td>
                     	</tr>
	    				<tr>
	    				
        		   		<tr>
	                       <td align="right">订单量：</td>
	                       <td><input type="text" id="orderNum" size="15" name="orderNum" value="${supply.orderNum}"/></td>
	                     </tr>
                    	 <tr>
	                       <td align="right">截止日期：</td>
	                       <td><input type="text" id="matureTime" size="15" name="matureTime" value="${supply.matureTime}"/></td>
	                     </tr>
	                     <tr>
	                       <td align="right">求购数量：</td>
	                       <td><input type="text" id="buyNum" size="15" name="buyNum" value="${supply.buyNum}"/></td>
	                     </tr>
	                     <tr>
	                       <td align="right">产品介绍：</td>
	                       <td>
	                       <textarea rows="5" cols="30" name="productcontent" id="productcontent">${supply.productcontent}</textarea>
	                       </td>
	                     </tr>
	                     <tr>
	                        <td align="right">内容:</td>
	                        <td>
	                        <textarea rows="5" cols="30" name="content" id="content">${supply.content}</textarea>
	                        </td>
	                     </tr>
	                     <tr>
	                        <td align="right">地址:</td>
	                        <td>
	                        <textarea rows="5" cols="30" name="address" id="address">${supply.address}</textarea>
	                        </td>
	                     </tr>
	                     <tr>
	                       <td align="right">图片：</td>
	                       <td><input type="file" name="picture" id="logo" value="${supply.logo}"/></td>
	                       <td class="logo"></td>
	                     </tr>
                     <tr>
                       <td colspan="2" align="center">
                       	<input type="submit" value="确定" name="sures"/></td>
                     </tr>
                  </table>
              </form>
          </div>
     </div>
     <script type="text/javascript">
     	function updateSeller()
     	{
     		var title = document.getElementById("title").value;
     		var price = document.getElementById("price"),value;
     		var city = document.getElementById("city").value;
     		var typeId = document.getElementById("typeId").value;
     		var type = document.getElementById("type").value;
     		var sellerId = document.getElementById("sellerId").value;
     		var statue = document.getElementById("statue").value;
     		
			if(title == "")
			{
				$(".title").html('<label  style="color: red;"><img src="../Building/images/error.jpg">店铺名称不能为空</label>');
   				return false;
			}
			if(price == "")
			{
				$(".price").html('<label style="color: red;"><img src="../Building/images/error.jpg">价格不能为空</label>');
   				return false;
			}
			if(city == 0)
			{
				$(".city").html('<label style="color: red;"><img src="../Building/images/error.jpg">城市不能为空</label>');
   				return false;
			}
			if(typeId == 0)
			{
				$(".typeId").html('<label style="color: red;"><img src="../Building/images/error.jpg">行业不能为空</label>');
   				return false;
			}
			if(type == 0)
			{
				$(".type").html('<label style="color: red;"><img src="../Building/images/error.jpg">类型不能为空</label>');
   				return false;
			}
			if(sellerId == 0)
			{
				$(".sellerId").html('<label style="color: red;"><img src="../Building/images/error.jpg">商铺不能为空</label>');
   				return false;
			}
			if(statue == "")
			{
				$(".statue").html('<label style="color: red;"><img src="../Building/images/error.jpg">供求类型不能为空</label>');
   				return false;
			}
			
			
     	}
     </script>
  </body>
</html>
