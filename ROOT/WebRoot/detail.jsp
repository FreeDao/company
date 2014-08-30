<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title>商户详情</title>
<link rel="stylesheet" type="text/css" href="css/Universal.css" />
<link rel="stylesheet" type="text/css" href="css/products.css" />
<link rel="stylesheet" type="text/css" href="css/backgroundpic.css" />
<link rel="stylesheet" type="text/css" href="css/GM ad.css" />
<link rel="stylesheet" type="text/css" href="css/detail.css" />
<link rel="stylesheet" type="text/css" href="js/imgbox/imgbox.css" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="this is my page" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="js/jquery.1.3.min.js"></script>
<script type="text/javascript" src="js/imgbox/jquery.imgbox.pack.js"></script>

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
// 广告切换时间,根据需要自己设置 
			var changeTime = 5;
			var i = 0;
			var havachange = 1;
			
			$(function(){
				
				$("a.mybox").imgbox({
					 'zoomOpacity'        : true,
        			 'alignment'        : 'center'
				});
			
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
<div class="float_window" ><a href="Two-dimensional code.html"><img src="images/erweimaw.jpg" width="150" height="200"></a></div>
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
<div class="yemian">
<div class="title">${seller.titile }</div>
<div class="moreinformation">
<div class="mainimg">
<img src="${seller.logo }" width="260" height="185"  alt=""/>

<%-- <a class="mybox" title="" href="${seller.logo }"><img alt="" src="${seller.logo }" width="260" height="185"/></a> --%>


</div>
<div class="mainnews">
<div class="xingxing">
<ul>
<li><a>推荐指数：</a></li>
<li><a><img src="images/xingxing1.jpg" width="16" height="14"></a></li>
<li><a><img src="images/xingxing1.jpg" width="16" height="14"></a></li>
<li><a><img src="images/xingxing1.jpg" width="16" height="14"></a></li>
<li><a><img src="images/xingxing1.jpg" width="16" height="14"></a></li>
<li><a><img src="images/xingxing1.jpg" width="16" height="14"></a></li>
</ul>
</div>

<div class="youhui">
<a>优惠</a>
<span style="width:30px; height:30px; background-color:#ff6c00;" class="youhuizi">${seller.preferential }</span>%
</div>

<div class="youhuijieshao">
<div><a>商家介绍</a><b>Business description</b></div>
<b>&nbsp;&nbsp;&nbsp;&nbsp;<c:if test="${fn: length(seller.brief)>110}">  
    	${fn: substring(seller.brief, 0, 111)}...
    </c:if> <c:if test="${fn: length(seller.brief)<=110}">
    	${seller.brief}
    </c:if></b>
</div>

<div class="phone" style="margin-top: 8px;">
<ul>
<li style=" width:178px; height:28px; background:url(images/phone.jpg);">
<div style="margin-left:10px; margin-top:3px; float:left;">
<b><img src="images/iconphone.jpg" width="20" height="20"></b></div>
<div class="zimu"><a>${seller.phone }</a></div>
</li>
<li style="margin-left:10px; margin-top:3px;">
<img src="images/piontorange.jpg" width="14" height="22"></li>
<li class="adress"><a>${seller.address }</a></li>
</ul>
</div>
</div>
</div>

<div class="services"><img src="images/services.jpg" width="243" height="28"  alt=""/></div>
<div class="hengde"><img src="images/hengde.png" width="489" height="24"  alt=""/></div>
<div class="zutu">
<c:forEach items="${imgs }" var="i">
<a class="mybox" title="" href="${i.imgUrl }"><img alt="" src="${i.imgUrl }" width="187" height="116"/></a>
<%-- <a><img src="${i.imgUrl }" width="187" height="116"  alt=""/></a> --%>
</c:forEach>
</div>
<div class="hengde"><img src="images/hengdee.png" width="489" height="24"  alt=""/></div>
<div class="services"><img src="images/677.jpg" width="192" height="28"  alt=""/></div>
<div class="hengde"><img src="images/hengde.png" width="489" height="24"  alt=""/></div>
<div class="xianlu">
<ul>
<li class="lei">
<div class="xianlutu"><a href="#"><img src="images/bus.jpg" width="193" height="81"  alt=""/></a></div>
<div class="xianlubutton">
<a><img src="images/3434.jpg" width="91" height="20"  alt=""/></a>
<a href="#"><img src="images/web_69.jpg" width="91" height="27"  alt=""/></a>
</div>
</li>
<li class="fenge" style="margin-left:14px;">&nbsp;</li>
<li class="lei" style="margin-left:14px;">
<div class="xianlutu"><a href="#"><img src="images/car.jpg" width="193" height="81"  alt=""/></a></div>
<div class="xianlubutton"><img src="images/45454.jpg" width="91" height="20"  alt=""/><a href="#"><img src="images/web_69.jpg" width="91" height="27"  alt=""/></a>
</div>
</li>
<li class="fenge" style="margin-left:14px;"></li>
<li class="lei" style="margin-left:14px;">
<div class="xianlutu"><a href="#"><img src="images/walk.jpg" width="193" height="81"  alt=""/></a></div>
<div class="xianlubutton">
<a><img src="images/4545.jpg" width="91" height="20"  alt=""/></a>
<a href="#"><img src="images/web_69.jpg" width="91" height="27"  alt=""/></a>
</div>
</li>
</ul>
</div>

</div>

<div id="footer" style="margin-top:10px;">
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
