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
    
    <link rel="stylesheet" type="text/css" href="css/comment.css">

	<script type="text/javascript" src="js/js-ajax.js"></script>
	
  </head>
  
  <body onload="detailInit()" >
   <header>
	<a id="back"  class=return onclick="window.history.back();" ><img src="img/return.png" width="40"/></a>
	<a href="addComment.html?sellerId=${sellerId }" class=add ><img src="img/comment.png" width="40"/></a>
  </header>
  <div class=comment id=comment >
	<c:forEach items="${commentList }" var="cl" >
		<section class=comment_bottom >
		 	<div class=comment_content >
			 	<div class=starBottom >
				 	<c:forEach begin="1" end="${cl.level }">
				 	<img src="img/star_solid.png" width="12" height="12" class="starBottomImg" >
				 	</c:forEach>
				 	<c:forEach begin="${cl.level+1 }" end="5">
				 	<img src="img/star_empty.png" width="12" height="12" class="starBottomImg" >
				 	</c:forEach>
			 	</div>
			 	<p style="line-height: 15px;" >${cl.conent }</p>
			 	<span>${cl.addtime }</span>
		 	</div>
		 	<img src="img/comment_line_cutoff.png" width="100%" />
		</section>
	 </c:forEach>
  </div>
  	<div class="moreComment" id="moreComment" onclick="moreComment()" >
		<a >点击查看更多评论</a>
	 </div>
	<div class=loading id=loading >
		<img src="img/loading.gif" />
	</div>
	<script type="text/javascript">
	var pageNow = 2,limit = 15;
	function moreComment(){
		var comment = document.getElementById("comment");
	
		var moreComment = document.getElementById("moreComment");
		moreComment.style.display = "none";
		var loading = document.getElementById("loading");
		loading.style.display = "block";
		
		var url = "../dataExchange/getCommentList.action?sellerId=${sellerId }&pageNo="+ (pageNow++) +"&pageNum="+limit;
		js_ajax({
			type: "post",
			url : url,
			async : true,
			load : function(){
			},
			success: function(data){
				var json = eval("("+data+")");
				for(var i = 0; i < json.results.length ; i++ ){
					var section = document.createElement("section");
					section.className = "comment_bottom";
					var div1 =  document.createElement("div");
					div1.className = "comment_content";
					var div2 =  document.createElement("div");
					div2.className = "starBottom";
					for(var j = 0 ; j < json.results[i].level ; j++){
						var star = document.createElement("img");
						star.src = "img/star_solid.png";
						star.marginLeft = "0px";
						star.width = "12";
						star.heigth = "12";
						div2.appendChild(star);
					}
					for(var j = json.results[i].level ; j < 5 ; j++){
						var star = document.createElement("img");
						star.src = "img/star_empty.png";
						star.marginLeft = "0px";
						star.width = "12";
						star.heigth = "12";
						div2.appendChild(star);
					}
					div1.appendChild(div2);
					var p = document.createElement("p");
					p.style.lineHeight = "15px";
					p.innerHTML = json.results[i].conent;
					var span = document.createElement("span");
					span.innerHTML = json.results[i].addtime;
					div1.appendChild(p);
					div1.appendChild(span);
					var img1 = document.createElement("img");
					img1.src = "img/comment_line_cutoff.png";
					img1.className = "starImgBar";
					section.appendChild(div1);
					section.appendChild(img1);
					comment.appendChild(section);
				}
				
				loading.style.display = "none";
				
				if( pageNow*limit < (parseInt(json.countNum)+limit)  ){
					moreComment.style.display = "block";
				}
			}
		});
	}
  </script>
  </body>
   
</html>
