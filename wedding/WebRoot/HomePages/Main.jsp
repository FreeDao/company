<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>会员中心</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="${pageContext.request.contextPath}/css/center.css" type="text/css" rel="stylesheet" />
	<script src="../JunKetinge/js/times.js" type="text/javascript"></script>
	<script type="text/javascript" src="http://counter.sina.com.cn/ip/" charset="gb2312"></script>
	
  </head>
  
  <body>
    <div id="head">
    	<div class="logo"></div>
        <div class="logo2"></div>
    </div>
    <!--center-->
	<div id="main">
        <div id="middle">
        	<div class="mhead">
            	<div class="lhead">
                	<img src="../JunKeting/images/login-logo.png" width="80" height="60" />
                </div>
                <div class="nhead">同城生活圈</div>
            </div>
            
            <div class="rhead">
            	<div class="rrhead">联系电话:4000-666-058<br />传真:023-89876966</div>
            </div>
        </div>
        <div class="mm">
        	<!-- 取得当前时间 -->
        	<div class="ll"><label id="time24"></label></div>
        	<!-- 取得当店登录IP -->
            <div class="nn"><label><script type="text/javascript">document.writeln(""+ILData[0].substring(0,8)+"** "+ILData[2]+" "+ILData[4]+"");</script></label></div>
        </div>
        <div class="middle1">
        	<div class="left"><!--左边菜单-->
            	<div class="leftl" ><img src="../JunKeting/images/yuan.png" width="20" height="20" /><a href="../JunKeting/System/selPageUser.action" target="mainFrame">会员中心首页</a></div>
                <div class="tu"><img src="../JunKeting/images/yuan1.png" width="20" height="20" /><a href="javascript:show()">管理中心</a></div>
  				<ul class="circle" id="art">
                	<li><img src="../JunKeting/images/yuan1.png" /><a href="../JunKeting/System/selUserAll.action" target="mainFrame">用户管理</a></li>
                	<li><img src="../JunKeting/images/yuan1.png" /><a href="../JunKeting/System/selAreaAllId.action" target="mainFrame">区域管理</a></li>
                	<li><img src="../JunKeting/images/yuan1.png" /><a href="../JunKeting/System/selfeatureAll.action?good=0" target="mainFrame">景点</a></li>
                	<li><img src="../JunKeting/images/yuan1.png" /><a href="../JunKeting/System/selfeatureAll.action?good=1" target="mainFrame">推荐景点</a></li>
                	<li><img src="../JunKeting/images/yuan1.png" /><a href="../JunKeting/System/UserLoginOut.action" target="mainFrame">退出</a></li>
                </ul>
            </div>
            
            <div class="right">
            	 <iframe id="mainFrame" src="../JunKeting/System/selPageUser.action" frameborder="0" width="580"  height="745" name="mainFrame"
                                style="margin: 0px;" scrolling="no"></iframe>
            </div>
        </div>
    </div>
    
    <div id="foot">
    	<div class="foot1"><img src="../JunKeting/images/t.png" width="1024" height="5" /></div>
        <div class="rel"><a href="javascript:rela()">联系我们</a></div>
    </div>
  </body>
</html>
