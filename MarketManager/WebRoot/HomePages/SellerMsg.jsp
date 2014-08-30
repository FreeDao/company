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
    
    <title>商户管理</title>
    
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
	  <li class="active">商户信息中心<span class="divider">/</span></li>
	  <li class="active">商户管理</li>
  </ul>
  
  <fieldset style="width: 98%;" >
	<legend>查询条件</legend>
	<form class="form-search" action="../MarketManager/System/selSeller.action" method="post">
	商户标题：
	  <div class="input-append">
	    <input type="text" class="span2 search-query" name="name">
	    <button type="submit" class="btn" id="sou">查询</button>
	    
	  </div>
	</form>
	<s:if test="#session.root==1"><a href="../MarketManager/System/addSelectPage.action">添加商户</a></s:if>
	</fieldset>
	
	<table class="table table-bordered">
		<tr class="info">
       		<td colspan="8" style="text-align: center;">
       		商户信息
       		</td>
       	</tr>
      <tr style="background-color: #f2f2f2;">
          <td align="center" width="4%">编号</td>
          <td align="center" width="15%">商户标题 </td>
          <td align="center" width="9%">电话 </td>
          <td align="center" width="8%">商户Logo </td>
          <td align="center" width="15%">简介</td>
          <td align="center" width="3%">优惠</td>
          <td align="center" width="11%">地址</td>
          <s:if test="#session.root==1"><td align="center" width="9%">操作</td></s:if>
      </tr>
      <s:iterator value="listSeller" var="d">
      <tr>
          <td align="center">${d.id}</td>
          <td align="center">${d.titile}</td>
          <td align="center">${d.phone}</td>
          <td align="center"><img src="${d.logo}" width="70" height="40"></td>
          <td align="center" style="cursor: pointer;">
          <div>
          <div id="showdiv" style="padding: 5px;font-size: 13;display: none;position: absolute;background-color: #E0FFFF;"  ><!-- onmouseout="$('#showdiv').hide()" -->
			</div>
                           <a onclick="showconten('${d.brief}',event,this)" ><%-- onmouseover="showconten('${d.brief}',event,this)" --%>
          <c:if test="${fn: length(d.brief)>11}">  
          	${fn: substring(d.brief, 0, 12)}...
          </c:if> 
          <c:if test="${fn: length(d.brief)<=11}">
          	${d.brief}
          </c:if> 
          </a>
           </div></td>
          <td align="center" >${d.preferential}</td>
          <td align="center" style="cursor: pointer;"  >
          <a onclick="showconten('${d.address}',event,this)">
           <c:if test="${fn: length(d.address)>11}">  
           	${fn: substring(d.address, 0, 12)}...
           </c:if> 
           <c:if test="${fn: length(d.address)<=11}">
           	${d.address}
           </c:if> 
           </a>
          </td>
          <s:if test="#session.root==1"><td align="center"><a href="../MarketManager/System/updatesellerPage.action?SellerId=${d.id}">修改</a> | 
          <a href="../MarketManager/System/delSeller.action?SellerId=${d.id}">删除</a> | 
          <a href="../MarketManager/System/updateProductPage.action?sellerId=${d.id}">添加产品</a>
          </td></s:if>
      </tr>
      </s:iterator>
  </table>
  
  <div style="text-align: center;">
  <s:form name="form5" id="form5" action="../MarketManager/System/selSeller.action" namespace="/Web" method="post" theme="simple" style="display:inline-block;">
  <ul class="pager">
  		 <s:if test="%{pagenum==1}"><li>上一页</li></s:if>
			<s:elseif test="%{pagenum!=1}">
			 <li><a href="../MarketManager/System/selSeller.action?pagenum=${pagenum-1}&name=${nameOne}">上一页</a></li>
			</s:elseif>
			<li>第<s:textfield type="text" name="pagenum" id="textfield" cssClass="pagetext" size="2" style="width:20px;"></s:textfield>
			<input type="hidden" name="cityArea" value="${pageCount}"/>
			<input type="hidden" name="name" value="${nameOne}"/>页 
			<input onClick="tijiao();" value="提交" type="button" class="btn" style="margin-bottom: 10px;" />
			<span class="c_fl">共 <s:property value="pageCount"></s:property>页</span>
			</li>
			<s:if test="%{pageCount>pagenum}">
			<li><a href="../MarketManager/System/selSeller.action?pagenum=${pagenum+1}&name=${nameOne}">下一页</a></li>
			</s:if>
			<s:elseif test="%{pagenum==pageCount}">
				<li>下一页</li>
			</s:elseif>
	</ul>
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
