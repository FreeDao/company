<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>图片管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link type="text/css" href="${pageContext.request.contextPath}/css/center.css" rel="stylesheet" />
  <%@include file="common.jsp" %>
  </head>
  
  <script type="text/javascript">
  function showconten(content,e,t){
  var x = e.clientX;
  var offset = $(t).offset();
  $("#showdiv").show();
  $("#showdiv").css({left:x,top:offset.top,opacity:.9 });
  $("#contentdiv").html("&nbsp;&nbsp;&nbsp;"+content);
  }
  </script>
  
  <body>
  <div id="showdiv" class="alert alert-info" style="position: absolute;display: none;"  ><!-- onmouseout="$('#showdiv').hide()" -->
  <button type="button" class="close" onclick="$('#showdiv').hide();">&times;</button>
  <div id="contentdiv"></div>
  </div>
  <ul class="breadcrumb" style="border: 1px;border-color: #E8E8E8;border-style:solid;">
	  <li class="active">图片管理中心<span class="divider">/</span></li>
	  <li class="active">图片管理</li>
  </ul>
	
	<table class="table table-bordered">
		<tr class="info">
       		<td colspan="8" style="text-align: center;">
       		图片信息
       		</td>
       	</tr>
      <tr style="background-color: #f2f2f2;">
          <td align="center" width="4%">编号</td>
          <td align="center" width="15%">产品图片</td>
          <td align="center" width="10%">产品名称</td>
          <s:if test="#session.root==1"><td align="center" width="4%">操作</td></s:if>
      </tr>
      <s:iterator value="listImage" var="d">
      <tr>
       <td align="center">
       ${d[0].id}</td>
                                    <td align="center"><img src="${d[0].imgUrl}" height="50" width="50"></td>
                                    <td align="center">${d[1].name}</td>
                                    <s:if test="#session.root==1"><td align="center"><a href="../MarketManager/System/delImages.action?id=${d[0].id}">删除</a></td></s:if>
      </tr>
      </s:iterator>
  </table>
  
  <div style="text-align: center;">
  <s:form name="form5" id="form5" action="../MarketManager/System/selProductImage.action" namespace="/Web" method="post" theme="simple" style="display:inline-block;">
  <ul class="pager">
  		 <s:if test="%{pagenum==1}"><li>上一页</li></s:if>
			<s:elseif test="%{pagenum!=1}">
			 <li><a href="../MarketManager/System/selProductImage.action?pagenum=${pagenum-1}&name=${nameOne}&id=${sellId}">上一页</a></li>
			</s:elseif>
			<li>第<s:textfield type="text" name="pagenum" id="textfield" cssClass="pagetext" size="2" style="width:20px;"></s:textfield>
			<input type="hidden" name="cityArea" value="${pageCount}"/>
			<input type="hidden" name="name" value="${nameOne}"/>页 
			<input onClick="tijiao();" value="提交" type="button" class="btn" style="margin-bottom: 10px;" />
			<span class="c_fl">共 <s:property value="pageCount"></s:property>页</span>
			</li>
			<s:if test="%{pageCount>pagenum}">
			<li><a href="../MarketManager/System/selProductImage.action?pagenum=${pagenum+1}&name=${nameOne}&id=${sellId}">下一页</a></li>
			</s:if>
			<s:elseif test="%{pagenum==pageCount}">
				<li>下一页</li>
			</s:elseif>
	</ul>
	<input type="hidden" value="${sellId}" name = "id">
  </s:form>
           </div>              
                <script type="text/javascript">
  function tijiao() {
	var ppt = document.getElementById("textfield").value;
		var znengweishuz =/^[0-9]*$/;
		if(!znengweishuz.test(ppt)){ 
				alert("页数只能为数字！");
				return false;
			}
		$("#form5").submit();
	}</script>
  </body>
</html>
