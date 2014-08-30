<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<!-- bootstrap -->
    <link href="css/bootstrap/bootstrap.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />

    <!-- libraries -->
    <link href="css/lib/jquery-ui-1.10.2.custom.css" rel="stylesheet" type="text/css" />
    <link href="css/lib/font-awesome.css" type="text/css" rel="stylesheet" />

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="css/layout.css" />
    <link rel="stylesheet" type="text/css" href="css/elements.css" />
    <link rel="stylesheet" type="text/css" href="css/icons.css" />

    <!-- this page specific styles -->
    <link rel="stylesheet" href="css/compiled/index.css" type="text/css" media="screen" />    


    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

  </head>
  
  <body>
   <div class="navbar navbar-inverse">
        <div class="navbar-inner">
            <button type="button" class="btn btn-navbar visible-phone" id="menu-toggler">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            
            <a class="brand" ><img src="img/logo-solid.png" /></a>

            <ul class="nav pull-right">                
                <li class="hidden-phone">
                    <input class="search" type="text" />
                </li>
                <li class="settings hidden-phone">
                    <a  role="button">
                        <i class="icon-cog"></i>
                    </a>
                </li>
                <li class="settings hidden-phone">
                    <a href="bg/user!loginOut" role="button">
                        <i class="icon-share-alt"></i>
                    </a>
                </li>
            </ul>            
        </div>
    </div>
    <!-- end navbar -->

    <!-- sidebar -->
    <div id="sidebar-nav">
        <ul id="dashboard-menu">
            <li class="active" onclick="mySelected(this,'')" >
                <div class="pointer">
                    <div class="arrow"></div>
                    <div class="arrow_border"></div>
                </div>
                <a href="welcome.jsp" target="myIframe" >
                    <i class="icon-home"></i>
                    <span>主页</span>
                </a>
            </li>    
        
        <c:if test="${userSession.role == 0 }">
        	 <c:forEach items="${tree }" var="tree">
				 <li onclick="mySelected(this,'${tree.childs}')" >
				 	<c:choose>
				 		<c:when test="${not empty tree.childs }">
				 			<a class="dropdown-toggle" href="#">
			                    <i class="${tree.icon}"></i>
			                    <span>${tree.name}</span>
			                    <i class="icon-chevron-down"></i>
			                </a>
				 		</c:when>
				 		<c:otherwise>
				 			<a href="${tree.url}" target="myIframe" >
			                    <i class="${tree.icon}"></i>
			                    <span>${tree.name}</span>
			                </a>
				 		</c:otherwise>
				 	</c:choose>
	                
	                <c:if test="${not empty tree.childs }">
			            <ul class="submenu">
			            	<c:forEach items="${tree.childs }" var="child">
		            			 <li><a href="${child.url}" target="myIframe" onclick="myActive(this)" >${child.name}</a></li>
	            			</c:forEach>
		                </ul>
		            </c:if>  
	            </li> 
			</c:forEach>
          </c:if>
                  
        </ul>
        <script type="text/javascript">
        function mySelected(t,c){
        	if(c == ""){
        		$(".pointer").remove();
            	$("li").removeClass("active");
            	$(".submenu").hide();
            	$("a").removeClass("active");
            	//$("a").removeClass("active");
    			$(t).addClass("active");
            	$(t).append("<div class='pointer'><div class='arrow'></div><div class='arrow_border'></div></div>");
        	}
        }
        
        function myActive(t){
        	$("li").removeClass("active");
        	$(".pointer").remove();
        	$(t).parent().parent().parent().addClass("active");
        	$(t).parent().parent().parent().append("<div class='pointer'><div class='arrow'></div><div class='arrow_border'></div></div>");
        	$("a").removeClass("active");
        	$(t).addClass("active");
        }
        </script>
    </div>
    <!-- end sidebar -->


	<!-- main container -->
    <div class="content">
    	<iframe id="myIframe" name="myIframe" class="myIframe" frameborder="0" width="100%" src="welcome.jsp" scrolling="auto" onload="test(this)" ></iframe>
    	<script type="text/javascript">
    	function test(t){
    		$("#myIframe").height($("#myBody",t.contentDocument).height()+130);
    	}
    	</script>
        <!-- settings changer 
        <div class="skins-nav">
            <a href="#" class="skin first_nav selected">
                <span class="icon"></span><span class="text">Default skin</span>
            </a>
            <a href="#" class="skin second_nav" data-file="css/skins/dark.css">
                <span class="icon"></span><span class="text">Dark skin</span>
            </a>
        </div>-->
           
    </div>


	<!-- scripts -->
    <script src="js/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/theme.js"></script>

  </body>
</html>
