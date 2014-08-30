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
  
  <body>
     <div id="all3">
                    <div class="ce">景点图片</div>
                    <div class="va1">
                       <div class="kk">
                            <table border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td align="center">编号</td>
                                    <td align="center">商户图片</td>
                                    <td align="center">操作</td>
                                </tr>
                                <s:iterator value="listImage" var="d">
                                <tr>
                                    <td align="center">${d.id}</td>
                                    <td align="center"><img src="${d.imgUrl}" height="50" width="50"></td>
                                    <td align="center"><a href="../JunKeting/System/delImage.action?id=${d.id}">删除</a></td>
                                </tr>
                                </s:iterator>
                            </table>
                        </div>
                         <div class="aa" align="center">
                            <s:if test="%{pagenum==1}">上一页</s:if>
								<s:elseif test="%{pagenum!=1}">
								<a href="../JunKeting/System/selImages.action?pagenum=${pagenum-1}">上一页</a>
								</s:elseif>
								<s:form name="form5" action="../JunKeting/System/selImageAll.action" namespace="/Web" method="post" theme="simple" style="display:inline-block;">
								第<s:textfield type="text" name="pagenum" id="textfield" cssClass="pagetext" size="2"></s:textfield>
								<input type="hidden" name="cityArea" value="${pageCount}"/>
								<input type="hidden" name="name" value="${nameOne}"/>页 
								</s:form>
								<input onClick="tijiao();" value="提交" type="button" class="search-btn">
								<span class="c_fl">共 <s:property value="pageCount"></s:property>页</span>
								<s:if test="%{pageCount>pagenum}">
									<a href="../JunKeting/System/selImageAll.action?pagenum=${pagenum+1}">
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
