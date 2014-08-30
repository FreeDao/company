<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html > 
<html>
  <head>
    <base href="<%=basePath%>" />
    
    <title>会员中心</title>
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/css/center.css" type="text/css" rel="stylesheet" />
	<script src="${pageContext.request.contextPath}/js/times.js" type="text/javascript"></script>
	<script type="text/javascript" src="http://counter.sina.com.cn/ip/" charset="gb2312"></script>
	<script type="text/javascript" src="http://counter.sina.com.cn/ip/" charset="gb2312"></script>
	 
	 
<script language="javascript" type="text/javascript"> 
function dyniframesize(down) { 
var pTar = null; 
if (document.getElementById){ 
pTar = document.getElementById(down); 
} 
else{ 
eval('pTar = ' + down + ';'); 
} 
if (pTar && !window.opera){ 
//begin resizing iframe 
pTar.style.display="block";
if (pTar.contentDocument && pTar.contentDocument.body.offsetHeight){ 
//ns6 syntax 
pTar.height = pTar.contentDocument.body.offsetHeight +100; 
} 
else if (pTar.Document && pTar.Document.body.scrollHeight){ 
//ie5+ syntax 
pTar.height = pTar.Document.body.scrollHeight +100; 
} 
} 
} 
</script> 

	 
  </head>
  
  <body>
  
    <div id="head">
    	<div class="logo"></div>
        <div class="logo2"></div>
    </div>
    <!--center-->
	<div id="main">
        <div>
        	<div class="mhead" style="margin-left: 100px;">
            	<div class="lhead">
                	<img src="../MarketManager/images/login-logo.png" width="80px" height="60px" />
                </div>
                <div class="nhead">同城生活圈</div>
            </div>
            
            <div class="rhead" align="right" style="margin-right:  100px;">
            	<div class="rrhead">联系电话:4000-666-058<br />传真:023-89876966</div>
            </div>
        </div>
        
          <!-- <div  style="margin-bottom: 10px;background-color: #F2F2F2;" align="center">
        	取得当前时间
        	<div class="ll"><label id="time24"></label></div>
        	取得当店登录IP
            <div class="nn"><label><script type="text/javascript">document.writeln(""+ILData[0].substring(0,8)+"** "+ILData[2]+" "+ILData[4]+"");</script></label></div>
        </div> -->
        <br/>
        <div class="container-fluid">
      <div class="row-fluid">
        <div class="span2 bs-docs-sidebar">
          <div class="well sidebar-nav">
            <ul class="nav nav-list bs-docs-sidenav">
	              <li class="active"><a href="../MarketManager/System/selPageUser.action" target="mainFrame">首页</a></li>
	              <li class="nav-header">商户信息中心</li>
	              <li><a href="../MarketManager/System/selSeller.action" target="mainFrame">商户管理</a></li>
	              <li><a href="../MarketManager/System/selProductNum.action" target="mainFrame">商户产品</a></li>
	              <li><a href="../MarketManager/System/selImages.action" target="mainFrame">商户图片</a></li>
	              <li><a href="../MarketManager/System/UserLoginOut.action" target="mainFrame">退出</a></li>
            </ul>
          </div><!--/.well -->
        </div><!--/span-->
        <div class="span10">
			<iframe id="mainFrame" src="../MarketManager/System/selPageUser.action" frameborder="0" width="100%" height="100%"  onload="javascript:dyniframesize('mainFrame');" name="mainFrame"
                                style="margin: 0px;" scrolling="auto"></iframe>
                                
        </div><!--/span-->
      </div><!--/row-->
    </div><!--/.fluid-container-->
        
    </div>
    
    <div id="foot">
    	<div class="foot1"><img src="../MarketManager/images/t.png" width="1024" height="5" /></div>
        <div class="rel"><a href="javascript:rela()">联系我们</a></div>
    </div>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js" ></script>
  </body>
</html>
