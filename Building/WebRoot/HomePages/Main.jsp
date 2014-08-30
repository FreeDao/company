<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
  <head>
    <base href="<%=basePath%>"></base>
    <title>会员中心</title>
	<meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0"></meta>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
	<meta http-equiv="description" content="This is my page"></meta>
	
	<link href="${pageContext.request.contextPath}/css/center.css" type="text/css" rel="stylesheet" />
	<script src="../Building/js/times.js" type="text/javascript"></script>
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
                	<img src="../Building/images/login-logo.png" width="80" height="60" />
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
            	<div class="leftl" ><img src="../Building/images/yuan.png" width="20" height="20" /><a href="../Building/System/selPageUser.action" target="mainFrame">会员中心首页</a></div>
                <div class="tu"><img src="../Building/images/yuan1.png" width="20" height="20" /><a href="javascript:show()">管理中心</a></div>
  				<ul class="circle" id="art">
                	<li><img src="../Building/images/yuan1.png" /><a href="../Building/System/selUserAll.action" target="mainFrame">用户管理</a></li>
                	<li><img src="../Building/images/yuan1.png" /><a href="../Building/System/selArea.action" target="mainFrame">区域管理</a></li>
                	<li><img src="../Building/images/yuan1.png" /><a href="../Building/System/selCollect.action" target="mainFrame">收藏管理</a></li>
                	<li><img src="../Building/images/yuan1.png" /><a href="../Building/System/selInformation.action" target="mainFrame">资讯管理</a></li>
                	<li><img src="../Building/images/yuan1.png" /><a href="../Building/System/selType.action" target="mainFrame">行业管理</a></li>
                	<li><img src="../Building/images/yuan1.png" /><a href="../Building/System/selImageAll.action" target="mainFrame">图片管理</a></li>
                	<li><img src="../Building/images/yuan1.png" /><a href="../Building/System/selSeller.action" target="mainFrame">商铺管理</a></li>
                	<li><img src="../Building/images/yuan1.png" /><a href="../Building/System/selSupply.action" target="mainFrame">供求管理</a></li>
                	<li><img src="../Building/images/yuan1.png" /><a href="../Building/System/UserLoginOut.action">退出</a></li>
                </ul>
            </div>
            <div class="right">
            	 <iframe id="mainFrame" src="../Building/System/selPageUser.action" frameborder="0" width="580"  height="745" name="mainFrame" style="margin: 0px;" scrolling="no"></iframe>
            </div>
        </div>
    </div>
    
    <div id="foot">
    	<div class="foot1"><img src="../Building/images/t.png" width="1024" height="5" /></div>
        <div class="rel"><a href="javascript:rela()">联系我们</a></div>
    </div>
  </body>
</html>
