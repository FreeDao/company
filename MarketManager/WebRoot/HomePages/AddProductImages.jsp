<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>商户修改</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@include file="common.jsp" %>
  </head>
  <script src="../js/jquery.min.js" type=text/javascript></script>
  <body>
    	  <!--修改会员信息-->
    	  <ul class="breadcrumb" style="border: 1px;border-color: #E8E8E8;border-style:solid;">
			  <li class="active">添加产品中心<span class="divider">/</span></li>
			  <li><a href="System/selProductNum.action">产品管理</a> <span class="divider">/</span></li>
			  <li class="active">添加产品图片</li>
		  </ul>
    	  
    <div id="alls">
              <div class="bg">
              <form action="${pageContext.request.contextPath}/System/addtiew.action" method="post"  class="form-horizontal"  enctype="multipart/form-data">
              <input type="hidden" value="${model.id}" name = "id" id="id">
                  <div class="control-group">
	                  <label class="control-label" for="titile">产品图片：</label>
	                  <div class="controls">
	                  <input type="hidden" value="${ide}" name="ttt">
	                  	<input type="file" name="picture" id="aa"/>
	                  	 <span class="titile"></span>
	                  </div>
                  </div>
                  <div class="control-group">
	                  <div class="controls">
	                  <button style="width: 210px;" type="submit"  id="sure" name="sures" class="btn  btn-block btn-primary" >确定</button>
              			</div>
         		  </div>
              
              </form>

          </div>
     </div>
    
  </body>
</html>
