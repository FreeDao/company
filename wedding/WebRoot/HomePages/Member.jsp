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
    
    <title>会员首页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${pageContext.request.contextPath}/css/center.css" type="text/css" rel="stylesheet" />
  </head>
  
  <body>
    <!--会员中心首页-->
    <div id="totel">
         <div class="member">
              <div class="member1"><img src="../JunKeting/images/tu.png" width="20" height="15" />会员中心</div>
                   <div class="member3"><div class="qq">${model.userName},欢迎回来!</div></div>
         </div>
         <div class="ac">
              <div class="account">账户信息</div><div class="xiugai"><a href="../JunKeting/System/updatePassPage.action" target="mainFrame">修改密码</a></div><hr />
              <div class="nmb">会员账号：${model.userName}</div>
              <div class="alter">
                   <div><a href="../JunKeting/System/updateMe.action" target="mainFrame">修改基本信息</a></div>
              </div>
              <div class="type">Qq：${model.qq}</div>
              <div class="balance">电话：${model.iphone}</div>
         </div>
    </div>
  </body>
</html>
