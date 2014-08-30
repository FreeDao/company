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
    
    <title>便民详细信息</title>
    
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
    <table width="50%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="STYLE1">便民详细信息</span></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="50%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" align="center">
      <tr>
        <td width="3%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">编号</span></div></td>
        <td width="3%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">${model.id}</span></div></td>
      </tr>
      <tr>
        <td width="3%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">名称</span></div></td>
        <td width="3%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">${model.name}</span></div></td>
      </tr>
      <tr>
        <td width="3%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">图片</span></div></td>
        <td width="3%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10"><img src="${pageContext.request.contextPath}${model.imgUrl}" width="80" height="50"></span></div></td>
      </tr>
      <tr>
        <td width="3%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">城市</span></div></td>
        <td width="3%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">${city.cityName}</span></div></td>
      </tr>
      <tr>
        <td width="3%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">电话</span></div></td>
        <td width="3%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">${detail.phone}</span></div></td>
      </tr>
      <tr>
        <td width="3%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">经度</span></div></td>
        <td width="3%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">${detail.log}</span></div></td>
      </tr>
      <tr>
        <td width="3%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">维度</span></div></td>
        <td width="3%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">${detail.dim}</span></div></td>
      </tr>
      <tr>
        <td width="3%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">地址</span></div></td>
        <td width="3%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">${detail.address}</span></div></td>
      </tr>
    </table></td>
  </tr>
</table>
  </body>
</html>
