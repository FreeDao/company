<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>

<!doctype html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>行业资讯</title>
<link  rel="stylesheet" type="text/css" href="css/Universal.css">
<link rel="stylesheet" type="text/css" href="css/information.css">
<link rel="stylesheet" type="text/css" href="css/backgroundpic.css">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="this is my page">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<script type="text/javascript">
<!--
var timeout         = 500;
var closetimer		= 0;
var ddmenuitem      = 0;

// open hidden layer
function mopen(id)
{	
	// cancel close timer
	mcancelclosetime();

	// close old layer
	if(ddmenuitem) ddmenuitem.style.visibility = 'hidden';

	// get new layer and show it
	ddmenuitem = document.getElementById(id);
	ddmenuitem.style.visibility = 'visible';

}
// close showed layer
function mclose()
{
	if(ddmenuitem) ddmenuitem.style.visibility = 'hidden';
}

// go close timer
function mclosetime()
{
	closetimer = window.setTimeout(mclose, timeout);
}

// cancel close timer
function mcancelclosetime()
{
	if(closetimer)
	{
		window.clearTimeout(closetimer);
		closetimer = null;
	}
}

// close layer when click-out
document.onclick = mclose; 
// -->	
</script>
</head>

<body>
<div class="float_window"><a href="Two-dimensional code.html"><img src="images/erweimaw.jpg" width="150" height="200"></a></div>
<div id="box">
<div style="min-height:80px; background-color:#269777; right:0;left:0;top:0; z-index:10000; position:fixed; ">
  <div style=" width:1005px;  margin:0px auto; " >
<div style="1005px; height:80px;">
<div style="width:283px; height:47px; margin-top:15px; margin-left:15px; float:left;"><a href="query!index"><img src="images/web_03.jpg" width="283" height="47"  alt=""/></a></div>
<div class="Typeface1" style="float:left; width:120px; height:20px; margin-top:15px; margin-left:10px;">欢迎来到同城生活圈</div>
<div id="Navigation">
<ul>
<li class="aboutme"><a href="query!index" >首页</a></li>
<li class="aboutme"><a href="query!tcsh" class="shouye1">同城生活</a></li>
<li class="aboutme"><a href="query!information">行业资讯</a></li>
<li ><a href="#">关于我们</a>
<ul>
<li class="aboutme"><a href="query!company">公司简介</a></li>
<li class="aboutme"><a href="us.jsp">联系我们</a></li>
</ul>
</li>
<li class="aboutme"><a href="#">查询业务</a>
<ul>
<li class="aboutme"><a href="http://api.tcshenghuo.org/AgentSystem/">市场注册</a></li>
<li class="aboutme"><a href="http://api.tcshenghuo.org/search/">市场查询</a></li>
</ul>
</li>
<li class="aboutme"><a href="query!job">招贤纳士</a></li>
</ul>
</div>
</div>
</div>
</div>
<div id="main">
<div class="title"><a>当前位置：行业资讯</a></div>
<div class="mainpic"></div>
<div class="mainall">
<div class="lastnews">
<div class="maintitle"><a>最新资讯</a> <a>Last News</a></div>
<div style=" width:682px; ">
  <img src="images/wec06.jpg" width="681" height="2"  alt=""/></div>
<div>
<c:forEach items="${informationTime }" var="it" >
<div class="lastnewsdiv">
<div class="picnew"><img src="${it.logo }" width="136" height="83"  alt=""/></div>
<div class="quicknews">
<h2>
<c:if test="${fn: length(it.title)>17}">${fn: substring(it.title, 0, 18)}...</c:if> 
<c:if test="${fn: length(it.title)<=17}">${it.title}</c:if>
</h2>
<b>
<c:if test="${fn: length(it.quotation)>72}">${fn: substring(it.quotation, 0, 73)}...</c:if> 
<c:if test="${fn: length(it.quotation)<=72}">${it.quotation}</c:if>
</b>
</div>
<div class="newsbutton"><a href="query!content?className=Information&id=${it.id }"><img src="images/web_3452.jpg" width="135" height="34"  alt=""/></a></div>
</div>
<div><img src="images/hengxian.png" width="681" height="1"  alt=""/></div>
</c:forEach>
</div>
</div>
<div class="rightnews">



<div style="width:244px; height:89px; margin-top:10px;">
<diV style="width:130px; height:89px; float:left;">
<div class="erwei"><img src="images/web_3535.jpg" width="130" height="39"  alt=""/></div>
<div class="erwei" style="margin-top:12px;"><img src="images/web_3537.jpg" width="130" height="39"  alt=""/></div>
</diV>
<div style="width:89px; height:89px; float:left; margin-left:23px; border:1px solid#ccc;"><img src="images/web_3536.jpg" width="89" height="89"  alt=""/></div>
</div>
</div>
</div>

<div class="main2">
<div style="width:317px; float:left;">
<div class="maintitle"><a>行业资讯</a> <a>Industry News</a></div>
<div><img src="images/wec28.jpg" width="317" height="2"  alt=""/></div>
<div style="width:317px; height:95px; margin-top:10px;">
<div style="width:149px; height:95px; float:left;"><img src="${informationTop[0].logo }" width="149" height="95"  alt=""/></div>

<div style="width:150px; height:95px; float:left; margin-left:15px;">
<div class="mm" >
<a href="query!content?className=Information&id=${informationTop[0].id }" >
<c:if test="${fn: length(informationTop[0].title)>20}">${fn: substring(informationTop[0].title, 0, 21)}...</c:if> 
<c:if test="${fn: length(informationTop[0].title)<=20}">${informationTop[0].title}</c:if>
</a>
</div>
<div style="margin-top:5px;">
<a class="mn">
<c:if test="${fn: length(informationTop[0].quotation)>28}">${fn: substring(informationTop[0].quotation, 0, 29)}...</c:if> 
<c:if test="${fn: length(informationTop[0].quotation)<=28}">${informationTop[0].quotation}</c:if>
</a>
</div>
</div>
</div>
<div style="margin-top:10px;"><img src="images/wec_32.jpg" width="317" height="1"  alt=""/></div>

<div class="lielist">
<c:forEach items="${informationTop }" var="it" begin="1">
<li>
<a><img src="images/web_3542.jpg" width="5" height="5"  alt=""/></a>
<a href="query!content?className=Information&id=${it.id }">
<c:if test="${fn: length(it.title)>28}">${fn: substring(it.title, 0, 29)}...</c:if> 
<c:if test="${fn: length(it.title)<=28}">${it.title}</c:if>
</a>
</li>
</c:forEach>
</div>
</div>

<div style="width:317px; float:left; margin-left:15px;">
<div class="maintitle"><a>产品动态</a> <a>Product News</a></div>
<div><img src="images/wec28.jpg" width="317" height="2"  alt=""/></div>
<div style="width:317px; height:95px; margin-top:10px;">
<div style="width:149px; height:95px; float:left;">
<img src="${product[0].logo }" width="149" height="95"  alt=""/></div>
<div style="width:150px; height:95px; float:left; margin-left:15px;">
<div class="mm">
<a href="query!content?className=Product&id=${product[0].id }">
<c:if test="${fn: length(product[0].title)>20}">${fn: substring(product[0].title, 0, 21)}...</c:if> 
<c:if test="${fn: length(product[0].title)<=20}">${product[0].title}</c:if>
</a>
</div>
<div style="margin-top:5px;"><a class="mn">
<c:if test="${fn: length(product[0].quotation)>28}">${fn: substring(product[0].quotation, 0, 29)}...</c:if> 
<c:if test="${fn: length(product[0].quotation)<=28}">${product[0].quotation}</c:if>
</a></div>
</div>
</div>
<div style="margin-top:10px;"><img src="images/wec_32.jpg" width="317" height="1"  alt=""/></div>
<div class="lielist">
<c:forEach items="${product }" var="it" begin="1">
<li>
<a><img src="images/web_3542.jpg" width="5" height="5"  alt=""/></a>
<a href="query!content?className=Product&id=${it.id }">
<c:if test="${fn: length(it.title)>28}">${fn: substring(it.title, 0, 29)}...</c:if> 
<c:if test="${fn: length(it.title)<=28}">${it.title}</c:if>
</a>
</li>
</c:forEach>
</div>
</div>

<div style="width:317px; float:left; margin-left:14px;">
<div class="maintitle"><a>公司动态</a> <a>Company News</a></div>
<div><img src="images/wec28.jpg" width="317" height="2"  alt=""/></div>
<div style="width:317px; height:95px; margin-top:10px;">
<div style="width:149px; height:95px; float:left;"><img src="${news[0].logo }" width="149" height="95"  alt=""/></div>
<div style="width:150px; height:95px; float:left; margin-left:15px;">
<div class="mm">
<a href="query!content?className=News&id=${news[0].id }">
<c:if test="${fn: length(news[0].title)>20}">${fn: substring(news[0].title, 0, 21)}...</c:if> 
<c:if test="${fn: length(news[0].title)<=20}">${news[0].title}</c:if>
</a>
</div>
<div style="margin-top:5px;"><a class="mn">
<c:if test="${fn: length(news[0].quotation)>28}">${fn: substring(news[0].quotation, 0, 29)}...</c:if> 
<c:if test="${fn: length(news[0].quotation)<=28}">${news[0].quotation}</c:if>
</a>
</div>
</div>
</div>
<div style="margin-top:10px;"><img src="images/wec_32.jpg" width="317" height="1"  alt=""/></div>
<div class="lielist">
<c:forEach items="${news }" var="it" begin="1">
<li>
<a><img src="images/web_3542.jpg" width="5" height="5"  alt=""/></a>
<a href="query!content?className=News&id=${it.id }">
<c:if test="${fn: length(it.title)>28}">${fn: substring(it.title, 0, 29)}...</c:if> 
<c:if test="${fn: length(it.title)<=28}">${it.title}</c:if>
</a>
</li>
</c:forEach>
</div>
</div>
</div>

</div>



<%-- <div id="main">
<div class="title"><a>当前位置：行业资讯</a></div>
<div class="mainpic"></div>
<div class="mainall">
<div class="lastnews">
<div class="maintitle">最新资讯</div>
<div style=" width:682px;"><img src="images/web_3451.jpg" width="679" height="2"  alt=""/></div>
<div>
<c:forEach items="${informationTime }" var="it">
<div class="lastnewsdiv">
<div class="picnew"><img src="${it.logo }" width="136" height="83"  alt=""/></div>
<div class="quicknews">
<h2>
<c:if test="${fn: length(it.title)>17}">${fn: substring(it.title, 0, 18)}...</c:if> 
<c:if test="${fn: length(it.title)<=17}">${it.title}</c:if>
</h2>
<b>
<c:if test="${fn: length(it.quotation)>86}">${fn: substring(it.quotation, 0, 87)}...</c:if> 
<c:if test="${fn: length(it.quotation)<=86}">${it.quotation}</c:if>
</b>
</div>
<div class="newsbutton"><a href="query!content?className=Information&id=${it.id }"><img src="images/web_3452.jpg" width="135" height="34"  alt=""/></a></div>
</div>
<div><img src="images/hengxian.png" width="681" height="1"  alt=""/></div>
</c:forEach>
</div>
</div>
<div class="rightnews">
<div class="right">
<div class="maintitle">产品动态</div>
<div style="width:247px; "><img src="images/web_3454.jpg" width="247" height="2"  alt=""/></div>
<div style="margin-top:10px;"><img src="images/web_3456.jpg" width="106" height="68"  alt=""/></div>
<div class="xinwen">
<c:forEach items="${product }" var="p">
<li><a href="query!content?className=News&id=${p.id }">
<c:if test="${fn: length(p.title)>12}">${fn: substring(p.title, 0, 13)}...</c:if> 
<c:if test="${fn: length(p.title)<=12}">${p.title}</c:if>
</a></li>
</c:forEach>
</div>
</div>
<div class="right2">
<div class="maintitle">公司动态</div>
<div style="width:247px; "><img src="images/web_3454.jpg" width="247" height="2"  alt=""/></div>
<div style="margin-top:10px;"><img src="images/web_3457.jpg" width="106" height="68"  alt=""/></div>
<div class="xinwen">
<c:forEach items="${news }" var="n">
<li><a href="query!content?className=News&id=${n.id }">
<c:if test="${fn: length(n.title)>12}">${fn: substring(n.title, 0, 13)}...</c:if> 
<c:if test="${fn: length(n.title)<=12}">${n.title}</c:if>
</a></li>
</c:forEach>
</div>
</div>
<div style="width:247px; height:1px; margin-top:10px;"><img src="images/fgd.jpg" width="247" height="1"  alt=""/></div>
<div style="width:247px; height:70px; margin-top:15px;">
<div class="picpic"><img src="images/1214.jpg" width="70" height="70"  alt=""/></div>
<div class="picpic" style="margin-left:18px;"><img src="images/1215.jpg" width="70" height="70"  alt=""/></div>
<div class="picpic" style="margin-left:18px;"><img src="images/1216.jpg" width="70" height="70"  alt=""/></div>
</div>
</div>

</div>

<div class="main2">
<div class="maintitle">行业资讯</div>
<div style="width:980px; "><img src="images/web_3453.jpg" width="980" height="2"  alt=""/></div>
<div class="main3">
<c:set var="i" value="1"/>
<c:forEach items="${informationTop }" var="it" begin="0" end="3">
<c:if test="${i == 1 }">
	<dd>
	<a href="query!content?className=Information&id=${it.id }"><img src="${it.logo }" width="168" height="84"  alt=""/></a>
	<li>
	<c:if test="${fn: length(it.title)>18}">${fn: substring(it.title, 0, 19)}...</c:if> 
	<c:if test="${fn: length(it.title)<=18}">${it.title}</c:if>
	</li>
	<div class="information">
	<a>
	<c:if test="${fn: length(it.quotation)>83}">${fn: substring(it.quotation, 0, 84)}...</c:if> 
	<c:if test="${fn: length(it.quotation)<=83}">${it.quotation}</c:if>
	</a>
	</div>
	</dd>
</c:if>
<c:if test="${i != 1 }">
	<dd style="margin-left: 100px;">
	<a href="query!content?className=Information&id=${it.id }"><img src="${it.logo }" width="168" height="84"  alt=""/></a>
	<li>
	<c:if test="${fn: length(it.title)>18}">${fn: substring(it.title, 0, 19)}...</c:if> 
	<c:if test="${fn: length(it.title)<=18}">${it.title}</c:if>
	</li>
	<div class="information">
	<a>
	<c:if test="${fn: length(it.quotation)>83}">${fn: substring(it.quotation, 0, 84)}...</c:if> 
	<c:if test="${fn: length(it.quotation)<=83}">${it.quotation}</c:if>
	</a>
	</div>
	</dd>
</c:if>
<c:set var="i" value="${i+1 }"/>
</c:forEach>
</div>
<div class="main3">
<c:set var="i" value="1"/>
<c:forEach items="${informationTop }" var="it" begin="4" end="7">
<c:if test="${i == 1 }">
	<dd>
	<a href="query!content?className=Information&id=${it.id }"><img src="${it.logo }" width="168" height="84"  alt=""/></a>
	<li>
	<c:if test="${fn: length(it.title)>18}">${fn: substring(it.title, 0, 19)}...</c:if> 
	<c:if test="${fn: length(it.title)<=18}">${it.title}</c:if>
	</li>
	<div class="information">
	<a>
	<c:if test="${fn: length(it.quotation)>83}">${fn: substring(it.quotation, 0, 84)}...</c:if> 
	<c:if test="${fn: length(it.quotation)<=83}">${it.quotation}</c:if>
	</a>
	</div>
	</dd>
</c:if>
<c:if test="${i != 1 }">
	<dd style="margin-left: 100px;">
	<a href="query!content?className=Information&id=${it.id }"><img src="${it.logo }" width="168" height="84"  alt=""/></a>
	<li>
	<c:if test="${fn: length(it.title)>18}">${fn: substring(it.title, 0, 19)}...</c:if> 
	<c:if test="${fn: length(it.title)<=18}">${it.title}</c:if>
	</li>
	<div class="information">
	<a>
	<c:if test="${fn: length(it.quotation)>83}">${fn: substring(it.quotation, 0, 84)}...</c:if> 
	<c:if test="${fn: length(it.quotation)<=83}">${it.quotation}</c:if>
	</a>
	</div>
	</dd>
</c:if>
<c:set var="i" value="${i+1 }"/>
</c:forEach>
</div>
</div>
</div> --%>
</div>

<div id="footer">
<div style="height:1px; background-color:#999; margin-top:0px;"></div>
<div style="width:1005px; margin:0px auto;height:30px;">
<div style="float:left; width:360px; height:30px;" class="footer ul li">
<ul>
<li><a href="us.jsp">联系我们</a></li>
<li><img src="images/web_36.jpg" width="1" height="12"  alt=""/></li>
<li><a href="query!job">人才招聘</a></li>
<li><img src="images/web_36.jpg" width="1" height="12"  alt=""/></li>
<li>隐私条款</li>
<li><img src="images/web_36.jpg" width="1" height="12"  alt=""/></li>
<li><a href="query!company">公司简介</a></li>
<li ><img src="images/web_36.jpg" width="1" height="12"  style="margin-top:8px;" alt=""/></li>
<li class="footerdaohang"><a href="http://119.61.8.17:7032">公司OA</a></li>
</ul>
</div>

<div style="margin-left:260px; width:370px; float:left; height:30px;">
<ul>
<li>Copyright Reserved 同城生活圈</li>
<li>重庆鹏优信息技术有限公司</li>
</ul>
</div>
</div>
<div style="height:30px; background-color:#efefef;"></div>
</div>
</body>
</html>

