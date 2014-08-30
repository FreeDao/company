<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html >
<html>
<head>
<title>招贤纳士</title>
<link rel="stylesheet" type="text/css" href="css/Universal.css">
<link rel="stylesheet" type="text/css" href="css/Co.css">
<link rel="stylesheet" type="text/css" href="css/backgroundpic.css">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="this is my page">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<style type="text/css">
.myfont{
color: #999;
font-family: "微软雅黑";
font-size: 15px;
}
</style>
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
<div style="margin-top: 100px;"></div>
<div class="pic" ></div>
<div class="order">
<li>当前位置 招贤纳士</li>
<a>"同城生活圈"作为手机媒体中高端的信息载体APP，是集购物、生活、健身、交友、婚庆、医疗、美食、娱乐、汽车、教育、房产、文化活动等构建的一个圆形社区中心，是全国性的城市大型生活服务类信息平台，主要为广大智能手机用户提供简单、方便、快捷的服务，让你可以随时、随地、随身的一手掌握衣食住行吃喝玩乐购的优惠信息，是您专属的移动应用专家.</a>
</div>
<div class="zhaopin">
<div class="hang">
<dd>
<div class="job"><a>商务代表</a></div>
<a class="myfont">${job.swdb }</a>
</dd>
<dd style="margin-left:255px;">
<div class="job"><a>储备干部</a></div>
<a class="myfont">${job.cbgb }</a>
</dd>
</div>
<div style="background-color:#999; width:978px; height:1px; margin:0px auto;"></div>
<div class="hang">
<dd>
<div class="job"><a>业务经理</a></div>
<a class="myfont">${job.ywjl }</a>
</dd >
<dd style="margin-left:255px;">
<div class="job"><a>销售工程师</a></div>
<a class="myfont">${job.xsgcs }</a>
</dd>
</div>
<div style="background-color:#999; width:978px; height:1px; margin:0px auto;"></div>
<div class="hang">
<dd >
<div class="job"><a>商务经理</a></div>
<a class="myfont">${job.swjl }</a>
</dd>
<dd style="margin-left:255px;">
<div class="job"><a>培训讲师</a></div>
<a class="myfont">${job.pxjs }</a>
</dd>
</div>
</div>
<div></div>

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
<div style="height:38px; background-color:#efefef;"></div>
</div>
</div>
</body>
</html>
