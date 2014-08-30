<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<%@include file="common.jsp" %>
    
  </head>
  <body>
<jsp:include page="common_top.jsp"></jsp:include>	
<div id="content">
	<div class="container">
		<div class="row">
			<div class="span3">
				<jsp:include page="common_left.jsp"></jsp:include>
			</div> <!-- /span3 -->
			<div class="span9">
				<jsp:include page="welcome.jsp"></jsp:include>
			</div> <!-- /span9 -->
		</div> <!-- /row -->
	</div> <!-- /container -->
</div> <!-- /content -->
					
<jsp:include page="common_bottom.jsp"></jsp:include>	
<script type="text/javascript">
(function() {
	window.history.forward();
})(jQuery);
</script>
  </body>
</html>
