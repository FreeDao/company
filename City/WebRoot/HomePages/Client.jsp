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
    
    <title>客户端列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

<style type="text/css">
<!--
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
}
.STYLE1 {
	color: #e1e2e3;
	font-size: 12px;
}
.STYLE6 {
	color: #000000; font-size: 12;
}
.STYLE10 {
	color: #000000; font-size: 12px;
 }
.STYLE19 {
	color: #344b50;
	font-size: 12px;
}
.STYLE21 {
	font-size: 12px;
	color: #3b6375;
}
.STYLE22 {
	font-size: 12px;
	color: #295568;
}

.page{ text-align:center; margin-bottom:10px;}
.page form{display:inline !important; zoom:1;}
-->
</style>
  </head>
  
  <body>
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="STYLE1"> 客户端信息列表</span></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  
  <tr>
  	<td><table width="100%" border="0" cellpadding="0" cellspacing="1">
  	<s:form id="login" action="../City/System/selClient.action" method="post">
    	<tr>
    	<td width="10%" height="30"><a href="../City/System/addClientPage.action">新增</a></td>
    	<td width="90%" height="30">
        <div align="center">
        标题:<input name="name" type="text" /><input name="title" type="submit" value="搜索" />
        </div>
        </td></tr>
         </s:form>
    </table></td>
  </tr>
  
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
      <tr>
        <td width="3%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">编号</span></div></td>
        <td width="8%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">市场名字</span></div></td>
        <td width="9%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">客户端名字</span></div></td>
        <td width="8%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">城市名</span></div></td>
        <td width="8%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">图片地址</span></div></td>
        <td width="8%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">客户端简介</span></div></td>
        <td width="8%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">下载地址</span></div></td>
        <td width="8%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">二维码</span></div></td>
        <td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">基本操作</span></div></td>
      </tr>
      <s:iterator value="list" var="d">
      <tr>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${d[0].id}</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${d[2].type}</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${d[0].name}</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${d[1].cityName}</div></td>
		<td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><img src="${pageContext.request.contextPath}${d[0].imgUrl}" width="100" height="100"></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${d[0].conent}</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${d[0].url}</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${d[0].qrUrl}</div></td>
        <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE21"><a href="../City/System/delClient.action?id=${d[0].id}">删除</a>|<a href="../City/System/updateClientPage.action?id=${d[0].id}">修改</a></div></td>
      </tr>
      </s:iterator>
    </table></td>
  </tr>
  
  <tr>
  <td class="page">
  
  <s:if test="%{pagenum==1}">上一页</s:if>
				<s:elseif test="%{pagenum!=1}">
				<a href="../City/System/selClient.action?pagenum=${pagenum-1}">上一页</a>
				</s:elseif>
				<s:form name="form5" action="../City/System/selClient.action" namespace="/Web" method="post" theme="simple" style="display:inline-block;">
				第<s:textfield type="text" name="pagenum" id="textfield" cssClass="pagetext" size="2"></s:textfield>
				<input type="hidden" name="cityArea" value="${pageCount}"/>页 
				</s:form>
				<input onClick="tijiao();" value="提交" type="button" class="search-btn">
				<span class="c_fl">共 <s:property value="pageCount"></s:property>页</span>
				<s:if test="%{pageCount>pagenum}">
					<a href="../City/System/selClient.action?pagenum=${pagenum+1}">
					下一页 </a>
				</s:if>
				<s:elseif test="%{pagenum==pageCount}">
					下一页
				</s:elseif>
  </td>
  </tr>
</table>
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
