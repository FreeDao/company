<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
      <script type="text/javascript" src="../City/js/jquery-1.7.1.js"></script>
     <script type="text/javascript">
    	
        $(function ()
         {  
            setInterval("SetHits()", 500); //每隔5秒刷新点击量  
        });  
        function SetHits() { //获取最新点击量  
            $.ajax({  
                type: "post",  
                url: "../City/System/findAll.action?ttt=50",  
                data: "",  
                success: function (str)
                 {  
                	
                    /* var strArray = str.split(";");  
                    for (var i = 0; i < strArray.length; i++) {  
                        var strOptArr = strArray[i].split(",");  
                        $("#" + strOptArr[0]).children("span").text(strOptArr[1]); //更新点击量  
                    }   */
                }  
            });  
        } 
        
        
         
    </script> 
    <title>Dwr 抽奖</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>
  
  <body>
    <!-- <div><a href="../City/System/findAll.action">点击查看</a></div> -->
  </body>
</html>
