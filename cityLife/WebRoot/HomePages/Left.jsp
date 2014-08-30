<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Left.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/chili-1.7.pack.js"></script>
<script type="text/javascript" src="js/jquery.easing.js"></script>
<script type="text/javascript" src="js/jquery.dimensions.js"></script>
<script type="text/javascript" src="js/jquery.accordion.js"></script>
<script language="javascript">
	jQuery().ready(function(){
		jQuery('#navigation').accordion({
			header: '.head',
			navigation1: true, 
			event: 'click',
			fillSpace: true,
			animated: 'bounceslide'
		});
	});
</script>
<style type="text/css">
<!--
body {
	margin:0px;
	padding:0px;
	font-size: 12px;
}
#navigation {
	margin:0px;
	padding:0px;
	width:147px;
}
#navigation a.head {
	cursor:pointer;
	background:url(images/main_34.gif) no-repeat scroll;
	display:block;
	font-weight:bold;
	margin:0px;
	padding:5px 0 5px;
	text-align:center;
	font-size:12px;
	text-decoration:none;
}
#navigation ul {
	border-width:0px;
	margin:0px;
	padding:0px;
	text-indent:0px;
}
#navigation li {
	list-style:none; display:inline;
}
#navigation li li a {
	display:block;
	font-size:12px;
	text-decoration: none;
	text-align:center;
	padding:3px;
}
#navigation li li a:hover {
	background:url(images/tab_bg.gif) repeat-x;
		border:solid 1px #adb9c2;
}
-->
</style>
  </head>
  
  <body>
    <div  style="height:100%;">
  <ul id="navigation">
    <li> <a class="head">角色管理</a>
      <ul>
        <li><a href="../era/System/selRoles.action" target="rightFrame">角色管理</a></li>
      </ul>
    <li> <a class="head">管理员</a>
      <ul>
        <li><a href="../era/System/adminUser.action" target="rightFrame">管理员</a></li>
      </ul>
    <li> <a class="head">会员管理</a>
      <ul>
        <li><a href="../era/System/selUser.action" target="rightFrame">会&nbsp;&nbsp;员</a></li>
      </ul>
    </li>
    <li> <a class="head">商户管理</a>
      <ul>
        <li><a href="../era/System/selSeller.action" target="rightFrame">商&nbsp;&nbsp;户</a></li>
      </ul>
    </li>
    <li> <a class="head">产品管理</a>
      <ul>
        <li><a href="../era/System/selProduct.action" target="rightFrame">产&nbsp;&nbsp;品</a></li>
      </ul>
    </li>
    <li> <a class="head">图片管理</a>
      <ul>
        <li><a href="../era/System/selProduct.action" target="rightFrame">图&nbsp;&nbsp;片</a></li>
      </ul>
    </li>
     <li> <a class="head">咨询管理</a>
      <ul>
        <li><a href="../era/System/selProduct.action" target="rightFrame">咨&nbsp;&nbsp;询</a></li>
      </ul>
    </li>
     <li> <a class="head">市场类型管理</a>
      <ul>
        <li><a href="../era/System/selMarket.action" target="rightFrame">市场类型</a></li>
      </ul>
    </li>
     <li> <a class="head">城市管理</a>
      <ul>
        <li><a href="../era/System/selCity.action" target="rightFrame">城&nbsp;&nbsp;市</a></li>
      </ul>
    </li>
    <li> <a class="head">评论管理</a>
      <ul>
        <li><a href="../era/System/selComment.action" target="rightFrame">评&nbsp;&nbsp;论</a></li>
      </ul>
    </li>
    <li> <a class="head">收藏管理</a>
      <ul>
        <li><a href="../era/System/selCollect.action" target="rightFrame">收&nbsp;&nbsp;藏</a></li>
      </ul>
    </li>
  </ul>
</div>
  </body>
</html>
