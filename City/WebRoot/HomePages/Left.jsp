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
     <s:if test="#session.cityId==0">
          	 <li> <a class="head">角色管理</a>
      <ul>
        <li><a href="../City/System/selRoles.action" target="rightFrame">角色管理</a></li>
      </ul>
      </li>
       <li> <a class="head">管理员</a>
      <ul>
        <li><a href="../City/System/adminUser.action" target="rightFrame">管理员</a></li>
      </ul>
      </li>
       <li> <a class="head">城市管理</a>
      <ul>
        <li><a href="../City/System/selCity.action" target="rightFrame">城&nbsp;&nbsp;市</a></li>
      </ul>
    </li>
    <li> <a class="head">会员管理</a>
      <ul>
        <li><a href="../City/System/selUser.action" target="rightFrame">会&nbsp;&nbsp;员</a></li>
      </ul>
    </li>
     <li> <a class="head">抽奖管理</a>
      <ul>
        <li><a href="../City/System/pageFind.action" target="rightFrame">抽&nbsp;&nbsp;奖</a></li>
      </ul>
    </li>
            </s:if>
    <li> <a class="head">商户管理</a>
      <ul>
        <li><a href="../City/System/selSeller.action" target="rightFrame">商&nbsp;&nbsp;户</a></li>
      </ul>
    </li>
    
    <!-- <li> <a class="head">产品管理</a>
      <ul>
        <li><a href="../City/System/selProduct.action" target="rightFrame">产&nbsp;&nbsp;品</a></li>
      </ul>
    </li>
    <li> <a class="head">图片管理</a>
      <ul>
        <li><a href="../City/System/selImage.action" target="rightFrame">图&nbsp;&nbsp;片</a></li>
      </ul>
    </li> -->
     <li> <a class="head">资讯管理</a>
      <ul>
        <li><a href="../City/System/selNews.action" target="rightFrame">资&nbsp;&nbsp;讯</a></li>
      </ul>
    </li>
     <li> <a class="head">市场管理</a>
      <ul>
        <li><a href="../City/System/selMarket.action" target="rightFrame">市场</a></li>
      </ul>
    </li>
    <li> <a class="head">市场类型管理</a>
      <ul>
        <li><a href="../City/System/selMarketArea.action" target="rightFrame">区域管理</a></li>
        <li><a href="../City/System/selMarketCustomType.action" target="rightFrame">行业类别</a></li>
      </ul>
    </li>
    <li> <a class="head">便民管理</a>
      <ul>
        <li><a href="../City/System/selConvenient.action" target="rightFrame">便&nbsp;&nbsp;民</a></li>
      </ul>
    </li>
     <li> <a class="head">便民详情管理</a>
      <ul>
        <li><a href="../City/System/selConvenientDetail.action" target="rightFrame">便民详情</a></li>
      </ul>
    </li>
   <!--  <li> <a class="head">客户端管理</a>
      <ul>
        <li><a href="../City/System/selClient.action" target="rightFrame">客户端</a></li>
      </ul>
    </li> -->
    <li> <a class="head">评论管理</a>
      <ul>
        <li><a href="../City/System/selComment.action" target="rightFrame">评&nbsp;&nbsp;论</a></li>
      </ul>
    </li>
    <li> <a class="head">收藏管理</a>
      <ul>
        <li><a href="../City/System/selCollect.action" target="rightFrame">收&nbsp;&nbsp;藏</a></li>
      </ul>
    </li>
    <li> <a class="head">商户会员管理</a>
      <ul>
        <li><a href="../City/System/selSellSerManag.action" target="rightFrame">商户会员</a></li>
      </ul>
    </li>
    <li> <a class="head">市场入住</a>
      <ul>
        <li><a href="../City/System/selBusmarSet.action" target="rightFrame">市场入住</a></li>
      </ul>
    </li>
    <li> <a class="head">商户房间维护</a>
      <ul>
        <li><a href="../City/System/selRoomPage.action" target="rightFrame">房间维护</a></li>
      </ul>
    </li>
    <li> <a class="head">应用汇管理</a>
      <ul>
        <li><a href="../City/System/selRecommend.action" target="rightFrame">查询应用汇</a></li>
      </ul>
    </li>
      <li> <a class="head">市场账号管理</a>
      <ul>
        <li><a href="../City/System/selMarketUser.action" target="rightFrame">市场账号</a></li>
      </ul>
    
    </li>
  </ul>
</div>
  </body>
</html>
