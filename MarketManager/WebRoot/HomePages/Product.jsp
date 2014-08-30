<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>商户图片</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link type="text/css" href="${pageContext.request.contextPath}/css/center.css" rel="stylesheet" />

  </head>
    	<%@include file="common.jsp" %>
  <body>
  
  
   <ul class="breadcrumb" style="border: 1px;border-color: #E8E8E8;border-style:solid;">
	  <li class="active">商户信息中心<span class="divider">/</span></li>
	  <li class="active">商户产品</li>
  </ul>
  
  <fieldset style="width: 98%;" >
	<legend>查询条件</legend>
	<form class="form-search" action="../MarketManager/System/selProductNum.action" method="post">
	产品名称：
	  <div class="input-append">
	    <input type="text" class="span2 search-query" name="name">
	    <button type="submit" class="btn" id="sou">查询</button>
	  </div>
	</form>
	</fieldset>
  
                            <table class="table table-bordered">
                            <tr class="info">
					       		<td colspan="5" style="text-align: center;">
					       		商户产品
					       		</td>
					       	</tr>
                                <tr>
                                    <td align="center">编号</td>
                                    <td align="center">产品名称</td>
                                    <td align="center">产品图片 </td>
                                    <td align="center">商户标题</td>
                                    <s:if test="#session.root==1"><td align="center">操作</td></s:if>
                                </tr>
                                <s:iterator value="listProduct" var="d">
                                <tr>
                                    <td align="center">${d[0].id}</td>
                                    <td align="center">${d[0].name}</td>
                                    <td align="center"><a href="../MarketManager/System/selProductImage.action?id=${d[0].id}">查看</a></td>
                                    <td align="center">${d[1].titile}</td>
                                    <s:if test="#session.root==1"><td align="center"><a href="../MarketManager/System/updateProductPage.action?id=${d[0].id}">修改</a>|<a href="../MarketManager/System/delProduct.action?id=${d[0].id}">删除</a>|<a href="../MarketManager/System/addProductImagesPage.action?id=${d[0].id}">添加图片</a></td></s:if>
                                </tr>
                                </s:iterator>
                            </table>
                            
                         <div align="center">
                         <s:form name="form5" action="../MarketManager/System/selProductNum.action" namespace="/Web" method="post" theme="simple" style="display:inline-block;">
                            <ul class="pager">
                            <s:if test="%{pagenum==1}"><li>上一页</li></s:if>
								<s:elseif test="%{pagenum!=1}">
								<li><a href="../MarketManager/System/selProductNum.action?pagenum=${pagenum-1}&name=${nameOne}">上一页</a></li>
								</s:elseif>
								<li>第<s:textfield type="text" name="pagenum" id="textfield" cssClass="pagetext" size="2" style="width:20px;"></s:textfield>
								<input type="hidden" name="cityArea" value="${pageCount}"/>
								<input type="hidden" name="name" value="${nameOne}"/>页 
								<input onClick="tijiao();" value="提交" type="button" class="btn" style="margin-bottom: 10px;">
								<span class="c_fl">共 <s:property value="pageCount"></s:property>页</span>
								</li>
								<s:if test="%{pageCount>pagenum}">
									<li><a href="../MarketManager/System/selProductNum.action?pagenum=${pagenum+1}&name=${nameOne}">
									下一页 </a></li>
								</s:if>
								<s:elseif test="%{pagenum==pageCount}">
									<li>下一页</li>
								</s:elseif>
							</ul>
							</s:form>
                        </div>
                <script type="text/javascript">
  				function tijiao()
  			 {
				var ppt = document.getElementById("textfield").value;
				var znengweishuz =/^[0-9]*$/;
				if(!znengweishuz.test(ppt)){ 
					alert("页数只能为数字！");
					return false;
				}
				document.form5.submit();
			 }
	
		</script>
  </body>
</html>
