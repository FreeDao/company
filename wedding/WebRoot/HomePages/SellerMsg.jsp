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
    
    <title>景点</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link type="text/css" href="${pageContext.request.contextPath}/css/center.css" rel="stylesheet" />
  </head>
  
  <body>
    <div id="all3">
                        <div class="va">
                        <div class="cc"><div class="pt">景点管理</div></div>
                        <div class="ee">
                        	<form action="../JunKeting/System/selfeatureAll.action?good=1" method="post">
                            <table>
                            <td width="10%" height="30"><a href="../JunKeting/System/upadeFeaturePage.action">新增</a></td>
                                <tr align="center">
                                    <td>商户标题：<input type="text" name="name"></td>
                                </tr>
                                <tr>
                                    <td colspan="2" align="center"><input type="submit" value="搜索" id="sou"/></td>
                                </tr>
                            </table>
                            </form>
                        </div>
                    </div>
                    <div class="ce">景点管理</div>
                    <div class="va1">
                       <div class="kk">
                            <table border="0" cellpadding="0" cellspacing="0">
                            <tr><td colspan="5"></td></tr>
                                <tr>
                                    <td align="center">编号</td>
                                    <td align="center">景点名称</td>
                                    <td align="center">地址</td>
                                    <td align="center">门票价格</td>
                                    <td align="center">开放时间</td>
                                    <td align="center">星级</td>
                                    <td align="center">简介</td>
                                    <td align="center">人文</td>
                                     <td align="center">地区</td>
                                    <td align="center">logo</td>
                                    <td align="center">推荐</td>
                                    <td align="center">操作</td>
                                </tr>
                                <s:iterator value="listFeature" var="d">
                                <tr>
                                    <td align="center">${d[0].id}</td>
                                    <td align="center"> <s:property value="#d[0].featureName.length()>5?#d[0].featureName.substring(0,5)+'...':#d[0].featureName" escape="false" escapeHtml="false"/></td>
                                    <td align="center">${d[0].address}</td>
                                    <td align="center">${d[0].price}</td>
                                    <td align="center">${d[0].time}</td>
                                    <td align="center">${d[0].leven}</td>
                                    <td align="center">
                                    <s:property value="#d[0].bife.length()>5?#d[0].bife.substring(0,5)+'...':#d[0].bife" escape="false" escapeHtml="false"/></td>
                                    <td align="center">
                                    <s:property value="#d[0].humanity.length()>5?#d[0].humanity.substring(0,5)+'...':#d[0].humanity" escape="false" escapeHtml="false"/>
     
                                    </td>
                                    <td align="center">${d[1].areaName}</td>
                                    <td align="center"><img src="${d[0].logo}" width="100" height="100"></td>
                                    <s:if test="%{d[0].groom==1}">
                                		<td align="center">推荐</td>
                                	</s:if>
                                	<s:else>
                                		<td align="center">默认</td>
                                	</s:else>
                                    <td align="center"><a href="../JunKeting/System/delFeature.action?id=${d[0].id}">删除</a> | <a href="../JunKeting/System/upadeFeaturePage.action?id=${d[0].id}">修改</a> | <a href="../JunKeting/System/addImagePage.action?id=${d[0].id}">添加更多图片</a> | <a href="../JunKeting/System/selImageAll.action?id=${d[0].id}">查询更多图片</a></td>
                                </tr>
                                </s:iterator>
                            </table>
                        </div>
                         <div class="aa" align="center">
                <s:if test="%{pagenum==1}">上一页</s:if>
				<s:elseif test="%{pagenum!=1}">
				<a href="../JunKeting/System/selfeatureAll.action?pagenum=${pagenum-1}&name=${nameFeature}&good=${goods}">上一页</a>
				</s:elseif>
				<s:form name="form5" action="../JunKeting/System/selfeatureAll.action" namespace="/Web" method="post" theme="simple" style="display:inline-block;">
				第<s:textfield type="text" name="pagenum" id="textfield" cssClass="pagetext" size="2"></s:textfield>
				<input type="hidden" name="cityArea" value="${pageCount}"/>
				<input type="hidden" name="name" value="${nameFeature}"/>
				<input type="hidden" name="good" value="${goods}"/>页 
				</s:form>
				<input onClick="tijiao();" value="提交" type="button" class="search-btn">
				<span class="c_fl">共 <s:property value="pageCount"></s:property>页</span>
				<s:if test="%{pageCount>pagenum}">
					<a href="../JunKeting/System/selfeatureAll.action?pagenum=${pagenum+1}&name=${nameFeature}&good=${goods}">
					下一页 </a>
				</s:if>
				<s:elseif test="%{pagenum==pageCount}">
					下一页
				</s:elseif>
                        </div>
                	</div>
                </div>
                <script type="text/javascript">
  function tijiao() {
	var ppt = document.getElementById("textfield").value;
		var znengweishuz =/^[0-9]*$/;
		if(!znengweishuz.test(ppt)){ 
				alert("页数只能为数字！");
				return false;
			}
		document.form5.submit();
	}</script>
  </body>
</html>
