<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>产品介绍</title>
<link rel="stylesheet" type="text/css" href="css/Universal.css">
<link rel="stylesheet" type="text/css" href="css/products.css">
<link rel="stylesheet" type="text/css" href="css/backgroundpic.css">
<link rel="stylesheet" type="text/css" href="css/GM ad.css">
<link rel="stylesheet" type="text/css" href="css/Market.css">
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
<div class="black"><a>当前位置：同城生活</a> </div>
<div class="div1">
			<img class="change" style="display: block;" src="images/img_1.jpg"/>
			<img class="change" src="images/img_2.jpg"/>
			<img class="change" src="images/img_3.jpg"/>
			<img class="change" src="images/img_4.jpg"/>
			<img class="change" src="images/img_5.jpg"/>
			<div class="leftdiv"></div>
			<div class="rightdiv"></div>
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
<div class="Two">
<ul>
<li class="Secondmenu2"><a href="query!tcsh">产品介绍</a></li>
<li class="Secondmenu2"><a href="query!hot">热门优惠</a></li>
<li class="Secondmenu2"><a href="query!cate">人气美食</a></li>
<li><a href="#" class="current2">市场</a></li>
<li class="Secondmenu2"><a href="query!app">应用汇</a></li>

</ul>
</div>

<div class="Maket">
<h1><img src="images/MK1.jpg" width="223" height="29"  alt=""/></h1> 
<div class="hengxian"></div>
<c:set var="i" value="1" />
<div class="shanghu">
<c:forEach items="${pu.data }" begin="0" end="2" var="pd">
<c:if test="${i == 1 }">

<dd>
<a href="query!seller?id=${pd[0] }"><img src="${pd[6] }" width="227" height="127"  alt=""/></a>
<li><div class="MerchantCategory">
<h1><a>${pd[1] }</a>
</h1>
</div></li>
</dd>
</c:if>
<c:if test="${i != 1 }">
<dd style="margin-left:148px;">
<a href="query!seller?id=${pd[0] }"><img src="${pd[6] }" width="227" height="127"  alt=""/></a>
<li><div class="MerchantCategory">
<h1><a>${pd[1] }</a>
</h1>
</div></li>
</dd>
</c:if>
<c:set var="i" value="${i+1 }" />
</c:forEach>
</div>


<div class="hengxian"></div>


<div class="shanghu">
<c:set var="i" value="1" />
<c:forEach items="${pu.data }" begin="3" end="5" var="pd">
<c:if test="${i == 1 }">
<dd>
<a href="query!seller?id=${pd[0] }"><img src="${pd[6] }" width="227" height="127"  alt=""/></a>
<li><div class="MerchantCategory">
<h1><a>${pd[1] }</a>
</h1>
</div></li>
</dd>
</c:if>
<c:if test="${i != 1 }">
<dd style="margin-left:148px;">
<a href="query!seller?id=${pd[0] }"><img src="${pd[6] }" width="227" height="127"  alt=""/></a>
<li><div class="MerchantCategory">
<h1><a>${pd[1] }</a>
</h1>
</div></li>
</dd>
</c:if>
<c:set var="i" value="${i+1 }" />
</c:forEach>
</div>

<div class="hengxian"></div>
<c:set var="i" value="1" />
<div class="shanghu">
<c:forEach items="${pu.data }" begin="6" end="8" var="pd">
<c:if test="${i == 1 }">
<dd>
<a href="query!seller?id=${pd[0] }"><img src="${pd[6] }" width="227" height="127"  alt=""/></a>
<li><div class="MerchantCategory">
<h1><a>${pd[1] }</a>
</h1>
</div></li>
</dd>
</c:if>
<c:if test="${i != 1 }">
<dd style="margin-left:148px;">
<a href="query!seller?id=${pd[0] }"><img src="${pd[6] }" width="227" height="127"  alt=""/></a>
<li><div class="MerchantCategory">
<h1><a>${pd[1] }</a>
</h1>
</div></li>
</dd>
</c:if>
<c:set var="i" value="${i+1 }" />
</c:forEach>
</div>

<div class="hengxian"></div>

<div class="shanghu">
<c:set var="i" value="1" />
<c:forEach items="${pu.data }" begin="9" end="11" var="pd">
<c:if test="${i == 1 }">
<dd>
<a href="query!seller?id=${pd[0] }"><img src="${pd[6] }" width="227" height="127"  alt=""/></a>
<li><div class="MerchantCategory">
<h1><a>${pd[1] }</a>
</h1>
</div></li>
</dd>
</c:if>
<c:if test="${i != 1 }">
<dd style="margin-left:148px;">
<a href="query!seller?id=${pd[0] }"><img src="${pd[6] }" width="227" height="127"  alt=""/></a>
<li><div class="MerchantCategory">
<h1><a>${pd[1] }</a>
</h1>
</div></li>
</dd>
</c:if>
<c:set var="i" value="${i+1 }" />
</c:forEach>
</div>
<div class="hengxian"></div>
<!-- /widget-content -->
<div class="fanye">
	<ul>
		<c:choose>
			<c:when test="${pu.isFrist }"><li>&laquo;</li></c:when>
			<c:otherwise>
				<li><a href="${pu.url }?pageNow=1">&laquo;</a></li></c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${pu.hasPre }">
				<li><a href="${pu.url }?pageNow=${pu.pageNow - 1}">&lsaquo;</a>
				</li>&nbsp;</c:when>
			<c:otherwise><li>&lsaquo;</li></c:otherwise>
		</c:choose>
		<c:forEach items="${pu.navigatePages }" var="pnp">
			<c:choose>
				<c:when test="${pu.pageNow == pnp }"><li>
					<c:out value="${pnp }"></c:out>&nbsp;</li></c:when>
				<c:otherwise>
					<li><a href="${pu.url }?pageNow=${pnp}"> <c:out
								value="${pnp }"></c:out> </a></li>&nbsp;</c:otherwise>
			</c:choose>

		</c:forEach>
		<c:choose>
			<c:when test="${pu.hasNext }">
				<li><a href="${pu.url }?pageNow=${pu.pageNow + 1}">&rsaquo;</a>
				</li>&nbsp;</c:when>
			<c:otherwise><li>&rsaquo;</li></c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${pu.isLast }"><li>&raquo;</li></c:when>
			<c:otherwise>
				<li><a href="${pu.url }?pageNow=${pu.pageTotal}">&raquo;</a></li>&nbsp;</c:otherwise>
		</c:choose>
	</ul>
</div>
<!-- <ul>
<li><img src="images/web772.jpg" width="25" height="25"  alt=""/></li>
<li><img src="images/web832.jpg" width="23" height="23"  alt=""/></li>
<li><img src="images/web85.jpg" width="23" height="23"  alt=""/></li>
<li><img src="images/web87.jpg" width="23" height="23"  alt=""/></li>
<li><img src="images/web89.jpg" width="23" height="23"  alt=""/></li>
<li><img src="images/web802.jpg" width="25" height="25"  alt=""/></li>
</ul> -->
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

</div>
</body>
</html>