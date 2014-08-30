<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <title>详细内容</title>
	
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
	<meta content="yes" name="apple-mobile-web-app-capable" />
	<meta content="black" name="apple-mobile-web-app-status-bar-style" />
	<meta content="telephone=no" name="format-detection" />
    
    <link rel="stylesheet" type="text/css" href="css/detail.css">
    
	<link rel="stylesheet" type="text/css" href="swipe/css/swipe.css">
	<script src="swipe/js/swipe.js"></script>

	<script type="text/javascript" src="js/requestParam.js"></script>
	
	<script type="text/javascript" >
	var productImgsVal = "${productImgs}";
	var briefImgsVal = "${briefImgs}";
	</script>
	
	
	<script type="text/javascript" src="js/detail.js"></script>
	
  </head>
  
  <body onload="detailInit()" >
  <div class=topBar >
  <header>
	<a id="back"  class=return onclick="window.history.back();" ><img src="img/return.png" width="40"/></a>
	<a href="addComment.html?sellerId=${sellerId }" class=comment ><img src="img/comment.png" width="40"/></a>
	<!-- <a href="index.html" class=share ><img src="img/share.png" width="40"/></a> -->
  </header>
  <section class=title >
  	<div class=head >
	  	<div class=left >
	  		<img id="logo" alt="" src="${logo }" width='81' height='62' />
	  	</div>
	  	<div class=right >
		  	<h3 id="title" >${title }</h3>
		  	<div class=star >
		  	<c:forEach begin="1" end="${level }">
		 	<img src="img/star_solid.png" width="12" height="12">
		 	</c:forEach>
		 	<c:forEach begin="${level+1 }" end="5">
		 	<img src="img/star_empty.png" width="12" height="12">
		 	</c:forEach>
		  	<!-- <img src="img/star_solid.png" width="12" height="12"><img src="img/star_empty.png" width="12" height="12"><img src="img/star_empty.png" width="12" height="12"><img src="img/star_empty.png" width="12" height="12"><img src="img/star_empty.png" width="12" height="12"> -->
		  	</div>
		  	<div class='bottom' ><a class=phoneBg href="tel:${phone }"  ><span class=phone id="phone" >${phone }</span></a>优惠：<span class=preferen id="preferen">${preferen }%</span></div>
	  	</div>
  	</div>
  </section>
  </div>
  <div class=content id=content  >
  <div class=address onclick="window.location.href='location.html?log=${log}&dim=${dim}'" ><div><img src='img/arrow_right.png' width='4' /><img src="img/address.png" width="20"><span id="address">${address }</span></div></div>
  <div class=searchway onclick="showRoute()" ><div><img src='img/arrow_right.png' width='4' /><img src="img/searchway.png" width="20"><span>路线查询</span></div></div>
  <script type="text/javascript">
  function showRoute(){
  	
	var routeBg = document.getElementById("routeBg");
	var routeContainer = document.getElementById("routeContainer");
	var content1 = document.getElementById("content");
	content1.style.overflow = "hidden";
	routeBg.style.display = "block";
	routeContainer.className = "routeContainerShow";
	
  }
  </script>
  <section class=service >
  	<h4>产品服务：</h4>
  	<p id="product">${product }</p>
  	<div id="productImgs" onclick="showImg(0)"></div>
  </section>
  <section class=brief >
  	<h4>商家介绍：</h4>
  	<p id="brief">${brief }</p>
  	<div id="briefImgs" onclick="showImg(1)" ></div>
  </section>
  <span class=comment_title >
 	<c:if test="${total == 0 }"> 
 		暂无评论
 	</c:if> 
  	<c:if test="${total != 0 }"> 
 		  评论：
 	</c:if> 
	</span>
  <img src="img/comment_line.png" width="100%" />
	<c:forEach items="${commentList }" var="cl" >
		<section class=comment_bottom >
	 	<div class=comment_content >
	 	<div class=starBottom >
	 	<c:forEach begin="1" end="${cl.level }">
	 	<img src="img/star_solid.png" width="12" height="12">
	 	</c:forEach>
	 	<c:forEach begin="${cl.level+1 }" end="5">
	 	<img src="img/star_empty.png" width="12" height="12">
	 	</c:forEach>
	 	</div>
	 	<p style="line-height: 15px;" >${cl.conent }</p>
	 	<span>${cl.addtime }</span>
	 	</div>
	 	<img src="img/comment_line_cutoff.png" width="100%" />
		</section>
	</c:forEach>
	
  	<c:if test="${total > 3 }"> 
 		<div class="allComment" onclick="addComment()" >
			<a  >点击查看全部评论</a>
		</div>
 	</c:if> 
	
  	<script type="text/javascript" >
  	function addComment(){
  		window.location.href = "appComment.action?sellerId=${sellerId }";
  	}
  	</script>
  	<br/>
  </div>
  
  
   <div class=imgChange id=imgChange onclick="hideImg()">
		<div class="addWrap">
			<div class="swipe" id="mySwipe">
		        <div class="swipe-wrap" id="swipe-wrap" style="width: 100%;">
		        </div>
		    </div>
		</div>
  </div>
  
  <div class="routeBg" id="routeBg" onclick="hideRoute()" ></div>
  <div class="routeContainerHidden" id="routeContainer" >
	<div class="routeContent" id="routeContent" >
		<div onclick="window.location.href='route.html?log=${log}&dim=${dim}&type=1'" >自驾</div>
		<div onclick="window.location.href='route.html?log=${log}&dim=${dim}&type=2'" >公交</div>
		<div onclick="hideRoute()" >取消</div>
	</div>
</div>
  <script type="text/javascript">
  	function hideRoute(){
	  	var routeBg = document.getElementById("routeBg");
		var routeContainer = document.getElementById("routeContainer");
		var content1 = document.getElementById("content");
		content1.style.overflow = "visible";
		routeBg.style.display = "none";
		routeContainer.className = "routeContainerHidden";
  	}
  </script>
  
  </body>
   
</html>
