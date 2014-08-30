<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html >
<html>
<head>
<title>同城生活圈</title>
<link rel="stylesheet" type="text/css" href="css/index.css">
<link rel="stylesheet" type="text/css" href="css/GM ad2.css">
<script type="text/javascript" src="js/jquery-1.4.2.2.min.js"></script>
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
$(function(){
				$(".float_window").show();
				resize(10);
				$(window).scroll(function(){
					resize(10);
				});
				$(window).resize(function(){
					resize(10);
				});
			});
			
			function resize(_left){
				var windowH = parseInt(document.documentElement.clientHeight);
					var scrollTop = parseInt($(document).scrollTop());
					var eH = parseInt($(".float_window").height());
					$(".float_window").css("top", (windowH - eH) / 2 + scrollTop + "px");
					
					var windowW = parseInt(document.documentElement.clientWidth);
					var scrollLeft = parseInt($(document).scrollLeft());
					var eW = parseInt($(".float_window").width());
					$(".float_window").css("left", windowW - eW - _left + scrollLeft + "px");
			}
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
// 广告切换时间,根据需要自己设置 
			var changeTime = 5;
			var i = 0;
			var havachange = 1;
			
			$(function(){
				change();
				
				$(".item img").click(function(){
					havachange = 0;
					i = $(this).parent().find("input").val();
					var change_obj = $(".change");
					var length = change_obj.length;
					change_obj.hide();
					$(change_obj[i % length]).show();
					
					$(".item img").removeClass("imgborder");
					$($(".item img")[i % length]).addClass("imgborder");
				});
				
				$(".leftdiv").click(function(){
					havachange = 0;
					i = i + 4;
					var change_obj = $(".change");
					var length = change_obj.length;
					change_obj.hide();
					$(change_obj[i % length]).show();
					
					$(".item img").removeClass("imgborder");
					$($(".item img")[i % length]).addClass("imgborder");
				});
				
				$(".rightdiv").click(function(){
					havachange = 0;
					i = i + 1;
					var change_obj = $(".change");
					var length = change_obj.length;
					change_obj.hide();
					$(change_obj[i % length]).show();
					
					$(".item img").removeClass("imgborder");
					$($(".item img")[i % length]).addClass("imgborder");
				});
			});
			
			function change() {
				if (havachange = 1) {
					var change_obj = $(".change");
					var length = change_obj.length;
					change_obj.hide();
					$(change_obj[i % length]).show();
					
					$(".item img").removeClass("imgborder");
					$($(".item img")[i % length]).addClass("imgborder");
					
					i++;
				}
				havachange = 1;
				setTimeout("change()", changeTime*1000);
			}			
		
		</script>
</head>

<body>
<div class=signUp ><a href="query!signUp"><img src="img/signUp_hot.jpg" width="170" /></a></div>
<div class="float_window" ><a href="Two-dimensional code.html"><img src="images/erweimaw.jpg" width="150" height="200"></a></div>
<div id="box">
<div style="min-height:80px; background-color:#269777; right:0;left:0;top:0; z-index:10000; position:fixed; ">
  <div style=" width:1055px;  margin:0px auto; " >
<div style="1005px; height:80px;">
<div style="width:283px; height:47px; margin-top:15px; margin-left:15px; float:left;"><a href="#"><img src="images/web_03.jpg" width="283" height="47"  alt=""/></a></div>
<div class="Typeface1" style="float:left; width:120px; height:20px; margin-top:15px; margin-left:10px;">欢迎来到同城生活圈</div>
<div id="Navigation">
<ul>
<li class="aboutme"><a href="#" >首页</a></li>
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
<li class="aboutme"><a href="http://api.tcshenghuo.org/NewCity">市场注册</a></li>
<li class="aboutme"><a href="http://api.tcshenghuo.org/NewCity/zsAgent/">市场查询</a></li>
<li class="aboutme"><a href="http://api.tcshenghuo.org/NewCity/zsView/">证书查询</a></li> 
</ul>
</li>
<li class="aboutme"><a href="query!job">招贤纳士</a></li>
<li class="aboutme"><a href="#">用户登入</a>
<ul>
<li class="aboutme"><a href="http://www.tcshenghuo.org:8799/SellerManage/">商家登入</a></li>
<li class="aboutme"><a href="http://www.tcshenghuo.org:8799/MarketManager/">市场主登入</a></li>
</ul>
</li>
</ul>
</div>
</div>
</div>
</div>
<div style="margin-top: 100px;"></div>
<div class="div1" >
<img class="change" style="display: block;" src="images/WEB_141.jpg"/>
			<img class="change" src="images/WEB151.jpg"/>
			<img class="change" src="images/WEB161.jpg"/>
			<img class="change" src="images/WEB171.jpg"/>
			<img class="change" src="images/WEB181.jpg"/>
			
			
			<div class="item">
				<i>
					<input type="hidden" value="0"/>
					<img class="imgborder" src="images/img01.jpg"/>
				</i>
				<i>
					<input type="hidden" value="1"/>
					<img src="images/img02.jpg"/>
				</i>
				<i>
					<input type="hidden" value="2"/>
					<img src="images/img03.jpg"/>
				</i>
				<i>
					<input type="hidden" value="3"/>
					<img src="images/img04.jpg"/>
				</i>
				<i>
					<input type="hidden" value="4"/>
					<img src="images/img05.jpg"/>
				</i>
			</div>
</div>
<div style="width:1005px; margin:0px auto; margin-top:10px;">
<span style="margin-left:15px;"><span class="offer">我们为您提供</span> <span class="what">What can we do for you</span></span><span class="what"> ?</span>
</div>
<div style="width:1005px; margin:0px auto; height:240px;">
<dd style=" margin-left:15px; margin-top:17px;"><a href="query!market"><img   src= "images/WEB_17.jpg "   onmouseover= "javascript:this.src= 'images/web_118.jpg ' "   onmouseout= "javascript:this.src= 'images/WEB_17.jpg ' "></a></dd>
<dd style="margin-top:17px; margin-left:24px;"><a href="query!hot"><img   src= "images/web_19.jpg "   onmouseover= "javascript:this.src= 'images/web_119.jpg ' "   onmouseout= "javascript:this.src= 'images/web_19.jpg ' "></a></dd>
<dd style="margin-top:17px; margin-left:24px;"><a href="query!cate"><img   src= "images/web_21.jpg "   onmouseover= "javascript:this.src= 'images/web_211.jpg ' "   onmouseout= "javascript:this.src= 'images/web_21.jpg ' "></a></dd>
</div>

<div style="width:1005px; margin:0px auto; height:177px;">
<div id="news" style="margin-left:15px; width:632px;">
<span><span class="offer">我们正在做</span> <span class="what">What are we doing</span></span>
<div style="width:632px; height:138px;">
<div style="width:192px; height:128px; margin-top:10px; float:left;"><img src="${objs[0].logo }" width="192" height="128"  alt=""/></div>
<div style="width:440px; float:left; margin-top:10px; height:128px;">

<span><span class="tongchen">
<c:if test="${fn: length(objs[0].title)>20}">${fn: substring(objs[0].title, 0, 21)}...</c:if> 
<c:if test="${fn: length(objs[0].title)<=20}">${objs[0].title}</c:if>
</span></span>
<div class="maintypefacel" style="margin-top:10px; margin-left:10px; padding-top:5px;">
<c:if test="${fn: length(objs[0].quotation)>160}">${fn: substring(objs[0].quotation, 0, 161)}...</c:if> 
<c:if test="${fn: length(objs[0].quotation)<=160}">${objs[0].quotation}</c:if>
</div>
<div class="gengduo"><a href="query!content?className=News&id=${objs[0].id }">点击查看详情</a></div>
</div>
</div>
</div>
<div id="news" style="width:309px; margin-left:33px;">
<span><span class="offer">新闻动态</span> <span class="what">News</span></span>
<div>
<ul>
<c:forEach items="${objs }" var="o" begin="1">
	<li><img src="images/WEB_28.png" width="6" height="10"  alt=""/> <a href="query!content?className=News&id=${o.id }">
	<c:if test="${fn: length(o.title)>19}">${fn: substring(o.title, 0, 20)}...</c:if> 
	<c:if test="${fn: length(o.title)<=19}">${o.title}</c:if>
	</a></li>
</c:forEach>
</ul>
</div>
</div> 
</div>
<div style="width:1005px; margin:0px auto; margin-top:15px;">
<span style="margin-left:15px;"><span class="offer">我们的客户这样说</span> <span class="what">Our customer say</span></span>
<div><img src="images/WEB_33.jpg" width="989" height="504"  alt=""/></div>
</div>

<div id="footer">
<div style="height:1px; background-color:#999; margin-top:0px;"></div>
<div style="width:1005px; margin:0px auto;height:30px;">
<div style="float:left; width:360px; height:30px;" class="footer ul li">
<ul>
<li class="footerdaohang"><a href="us.jsp">联系我们</a></li>
<li ><img src="images/web_36.jpg" width="1" height="12"  style="margin-top:8px;" alt=""/></li>
<li class="footerdaohang"><a href="query!job">人才招聘</a></li>
<li ><img src="images/web_36.jpg" width="1" height="12"  style="margin-top:8px;" alt=""/></li>
<li class="footerdaohang">隐私条款</li>
<li ><img src="images/web_36.jpg" width="1" height="12"  style="margin-top:8px;" alt=""/></li>
<li class="footerdaohang"><a href="query!company">公司简介</a></li>
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
