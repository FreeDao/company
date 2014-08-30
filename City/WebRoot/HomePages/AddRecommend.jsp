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
    
    <title>添加应用汇</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  <form action="../City/System/addRecommend.action" enctype="multipart/form-data" method="post">
     <table align="center">
    	<tr>
    		<td>
    			名称
    		</td>
    		<td>
    		<input type="hidden" name="id" value="${recommend.id}" id="id">
    			<input type="text" name="name" id="name" value="${recommend.name}">
    		</td>
    	</tr>
    	<%-- <tr>
    		<td>
    			ios或安卓
    		</td>
    		<td>
    			<select name="isIos">
    				<c:if test="${ recommend.isIos != 1 }">
    					<option value="2" selected="selected" >安卓</option>
    					<option value="1">苹果</option>
    				</c:if>
    				<c:if test="${ recommend.isIos == 1 }">
    					<option value="2">安卓</option>
    					<option value="1" selected="selected" >苹果</option>
    				</c:if>
    			</select>
    		</td>
    	</tr> --%>
    	<tr>
    		<td>
    			热门或精品
    		</td>
    		<td>
    			<select name="ismend">
    				<c:if test="${recommend.ismend != 1 }">
	    				<option value="2" selected="selected">精品推荐</option>
	    				<option value="1">热门应用</option>
    				</c:if>
    				<c:if test="${recommend.ismend == 1 }">
	    				<option value="2">精品推荐</option>
	    				<option value="1" selected="selected">热门应用</option>
    				</c:if>
    			</select>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			是否免费
    		</td>
    		<td>
    			<select name="ifNo">
    				<c:if test="${recommend.ifNo != '否' }">
    					<option value="是" selected="selected">是</option>
    					<option value="否">否</option>
    				</c:if>
    				<c:if test="${recommend.ifNo == '否' }">
    					<option value="是" >是</option>
    					<option value="否" selected="selected">否</option>
    				</c:if>
    				
    			</select>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			下载地址(安卓)
    		</td>
    		<td>
    			<input type="text" name="aurl" id="aurl" value="${recommend.aurl}">
    		</td>
    	</tr>
    	<tr>
    		<td>
    			下载地址(ios)
    		</td>
    		<td>
    			<input type="text" name="iurl" id="iurl" value="${recommend.iurl}">
    		</td>
    	</tr>
    	<tr>
    		<td>
    			头像
    		</td>
    		<td>
    			<input type="file" name="picture" id="aa"/>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			简介
    		</td>
    		<td>
    		<textarea rows="60" cols="60" name="bife" id="bife" >${recommend.bife}</textarea>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			类型:
    		</td>
    		<td>
    			<select name="typeId" id="typeId">
    				<c:forEach items="${listRecommendType }" var="lrt">
    					<c:choose>
    						<c:when test="${lrt.id == recommend.typeId}">
    							<option value="${lrt.id}" selected="selected">${lrt.type}</option>
    						</c:when>
    						<c:otherwise><option value="${lrt.id}">${lrt.type}</option></c:otherwise>
    					</c:choose>
    				</c:forEach>
    			</select>
    		</td>
    	</tr>
    	<%-- <tr>
    		<td>
    			城市:
    		</td>
    		<td>
    			<select name="cityId" id="cityId">
    				<c:forEach items="${listCity }" var="lc">
    					<c:choose>
    						<c:when test="${lc.id == recommend.cityId}">
    							<option value="${lc.id}" selected="selected">${lc.cityName}</option>
    						</c:when>
    						<c:otherwise><option value="${lc.id}">${lc.cityName}</option></c:otherwise>
    					</c:choose>
    				</c:forEach>
    			</select>
    		</td>
    	</tr> --%>
    	<tr>
    		<td>
    			是否属于公司:
    		</td>
    		<td>
    			<select name="isCompany" id="isCompany">
    				<c:if test="${recommend.isCompany != 1 }">
    					<option value="0" selected="selected">是</option>
    					<option value="1" >否</option>
    				</c:if>
    				<c:if test="${recommend.isCompany == 1 }">
    					<option value="0" >是</option>
    					<option value="1" selected="selected">否</option>
    				</c:if>
    			</select>
    		</td>
    	</tr>
    	
    	<tr align="center"><td colspan="2"><input type="submit" value="确定" onclick="return yanzheng();" /></td></tr>
    </table>
    </form>
    
    <script type="text/javascript">
    	
    	function yanzheng()
    	{
    		var name = document.getElementById("name").value;
    		var aurl = document.getElementById("aurl").value;
    		var iurl = document.getElementById("iurl").value;
    		var bife = document.getElementById("bife").value;
    		if(name == null || name=="")
    		{
    			alert("请输入名称");
    			return false;
    		}
    		if(aurl == null || aurl=="")
    		{
    			alert("请输入安卓地址");
    			return false;
    		}
    		if(iurl == null || iurl=="")
    		{
    			alert("请输入ios地址");
    			return false;
    		}
    		if(bife == null || bife=="")
    		{
    			alert("请输入简介");
    			return false;
    		}
    	}
    </script>
    
  </body>
</html>
