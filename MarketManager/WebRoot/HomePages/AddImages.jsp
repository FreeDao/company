<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加商户图片</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%@include file="common.jsp" %>
	<script src="../js/jquery.min.js" type=text/javascript></script>
  </head>
  
  <body>
  
    <!--修改会员信息-->
	<ul class="breadcrumb" style="border: 1px;border-color: #E8E8E8;border-style:solid;">
		<li class="active">商户图片<span class="divider">/</span></li>
		<li class="active">添加商户图片</li>
	</ul>
	<div id="alls">
              <div class="bg">
              <form action="${pageContext.request.contextPath}/System/addImagesPage.action" method="post"  class="form-horizontal"  enctype="multipart/form-data">
              <input type="hidden" value="${model.id}" name = "id" id="id">
                  <div class="control-group">
				  	  <label class="control-label" for="sellerName">商户名称：</label>
				  	  <div class="controls">
				  	  	<select name="sellerTitle" id="sellerTitle">
				  	  		<option value="0">请选择</option>
				  	  		<c:forEach items="${listSellers}" var="ls">
						    	<option value="${ls.id}">${ls.titile}</option>
						    </c:forEach>
					   </select>
					   <span class="sellerTitle"></span>
				  	  </div>
				  	  <br>
	                  <label class="control-label" for="titile">产品图片：</label>
	                  <div class="controls">
	                  	<input type="file" name="picture" id="aa"/>
	                  	<span class="titile"></span>
	                  </div>
                  </div>
                  <div class="control-group">
	                  <div class="controls">
	                  <button style="width: 210px;" type="submit"  id="sure" name="sures" class="btn  btn-block btn-primary" onclick="return vid();" >确定</button>
              		  </div>
         		  </div>
              
              </form>

          </div>
     </div>
     <script type="text/javascript">
     	function vid(){
     		var sellerTitle = document.getElementById("sellerTitle").value;
     		if (sellerTitle == "0") {
				$(".sellerTitle").html('<span style="color: red;"><img src="../MarketManager/images/error.jpg">请选择</span>');
				return false;
			} else {
				$(".sellerTitle").html('<img src="../MarketManager/images/right.jpg">');
			}
     	}
     </script>
  </body>
</html>
