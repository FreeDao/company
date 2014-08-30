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
    
    <title>产品图片</title>
    
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
  
  <body>
    <div id="all3">
             <div class="va">
                        <div class="cc"><div class="pt">产品图片</div></div>
                        <div class="ee">
                        	<form action="../MarketManager/System/selProductImage.action" method="post">
                            <table>
                                <tr align="center">
                                    <td>产品名称：<input type="text" name="name">
                                    <input type="hidden" value="${ide}" name="id">
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2" align="center"><input type="submit" value="搜索" id="sou"/></td>
                                </tr>
                            </table>
                            </form>
                        </div>
                    </div>
                    <div class="ce">产品图片</div>
                    <div class="va1">
                    <div><a href="../MarketManager/System/addProductImagesPage.action?id=${ide}">添加图片</a></div>
                       <div class="kk">
                            <table border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td align="center">编号</td>
                                    <td align="center">产品图片</td>
                                    <td align="center">产品名称</td>
                                    <td align="center">操作</td>
                                </tr>
                                <s:iterator value="listImage" var="d">
                                <tr>
                                    <td align="center">${d[0].id}</td>
                                    <td align="center"><img src="${d[0].imgUrl}" height="50" width="50"></td>
                                    <td align="center">${d[1].addtime}</td>
                                    <td align="center"><a href="../MarketManager/System/delImages.action?id=${d[0].id}">删除</a></td>
                                </tr>
                                </s:iterator>
                            </table>
                        </div>
                         <div class="aa" align="center">
                            <s:if test="%{pagenum==1}">上一页</s:if>
								<s:elseif test="%{pagenum!=1}">
								<a href="../MarketManager/System/selProductImage.action?pagenum=${pagenum-1}&name=${nameOne}&id=${ide}">上一页</a>
								</s:elseif>
								<s:form name="form5" action="../MarketManager/System/selProductImage.action" namespace="/Web" method="post" theme="simple" style="display:inline-block;">
								第<s:textfield type="text" name="pagenum" id="textfield" cssClass="pagetext" size="2"></s:textfield>
								<input type="hidden" name="cityArea" value="${pageCount}"/>
								<input type="hidden" name="name" value="${nameOne}"/>
								<input type="hidden" name="id" value="${ide}"/>
								页 
								</s:form>
								<input onClick="tijiao();" value="提交" type="button" class="search-btn">
								<span class="c_fl">共 <s:property value="pageCount"></s:property>页</span>
								<s:if test="%{pageCount>pagenum}">
									<a href="../MarketManager/System/selProductImage.action?pagenum=${pagenum+1}&name=${nameOne}&id=${ide}">
									下一页 </a>
								</s:if>
								<s:elseif test="%{pagenum==pageCount}">
									下一页
								</s:elseif>
                        </div>
                	</div>
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
