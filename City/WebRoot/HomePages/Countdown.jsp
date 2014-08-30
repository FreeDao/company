<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <script src="js/jquery.js" type=text/javascript></script>
    <title>抽奖倒计时</title>
    <script type="text/javascript">
    
	$(function(){
		countDown("2013/12/30 10:30:00","#demo01 .day","#demo01 .hour","#demo01 .minute","#demo01 .second");
	});

	function countDown(time,day_elem,hour_elem,minute_elem,second_elem){
	//if(typeof end_time == "string")
	var end_time = new Date(time).getTime(),//月份是实际月份-1
	//current_time = new Date().getTime(),
	sys_second = (end_time-new Date().getTime())/1000;
	var timer = setInterval(function(){
		if (sys_second > 0) {
			sys_second -= 1;
			var day = Math.floor((sys_second / 3600) / 24);
			var hour = Math.floor((sys_second / 3600) % 24);
			var minute = Math.floor((sys_second / 60) % 60);
			var second = Math.floor(sys_second % 60);
			day_elem && $(day_elem).text(day);//计算天
			$(hour_elem).text(hour<10?"0"+hour:hour);//计算小时
			$(minute_elem).text(minute<10?"0"+minute:minute);//计算分
			$(second_elem).text(second<10?"0"+second:second);// 计算秒
		} else { 
			clearInterval(timer);
			luckyDraw();
		}
	}, 1000);
	/* 抽奖 */
	function luckyDraw()
	{
		$.getJSON("../City/news/findAll.action", {"ranum":Math.random(),"ttt":3},function(data)
		{
			var fl = "";
			fl+='<br><br><div align="left">三等奖</div><br><br>'
			fl+='<table align="center" border="1">'
			fl+='<tr align="center">'
			for(var i = 0;i<data.msg.length ;i++)
			{
				fl+='<td>'+data.msg[i].iphone+'</td>'
			}
			fl+='</tr>'
			fl+='</table>'
			$(".yanz").append(fl);
		});
		erdengjiang();
	}
	
	function erdengjiang()
	{
		$.getJSON("../City/news/findAll.action", {"ranum":Math.random(),"ttt":2},function(data)
		{
			var fl = "";
			fl+='<br><br><div align="left">二等奖</div><br><br>'
			fl+='<table border="1">'
			fl+='<tr>'
			for(var i = 0;i<data.msg.length ;i++)
			{
				fl+='<td>'+data.msg[i].iphone+'</td>'
			}
			fl+='</tr>'
			fl+='</table>'
			$(".erdengjiang").append(fl);
		});
		yidengjiang();
	}
	function yidengjiang()
	{
		$.getJSON("../City/news/findAll.action", {"ranum":Math.random(),"ttt":1},function(data)
		{
			var fl = "";
			fl+='<div align="center" style="font-size: 30px;">中奖名单如下</div>'
			fl+='<br><br><div align="left">一等奖</div><br><br>'
			fl+='<table border="1">'
			fl+='<tr>'
			for(var i = 0;i<data.msg.length ;i++)
			{
				fl+='<td>'+data.msg[i].iphone+'</td>'
			}
			fl+='</tr>'
			fl+='</table>'
			$(".yideng").append(fl);
		});
	}
}
</script>
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
    <style type="text/css">
*{margin:0;padding:0;list-style-type:none;}
a,img{border:0;}
body{font:12px/180% Arial, Helvetica, sans-serif,"宋体";}
/* colockbox */
.colockbox{width:283px;height:76px;margin:50px auto;background:url(../City/images/colockbg.png) no-repeat;}
.tt{width:283px;height:76px;margin:20px auto;}
.colockbox span{float:left;display:block;width:58px;height:48px;line-height:48px;font-size:26px;text-align:center;color:#ffffff;margin:0 17px 0 0;}
.colockbox span.second{margin:0;}
</style>
<br>
<br>
<br>
<div align="center" style="font-size: 20px;">同城生活圈开奖倒计时</div>
<div class="colockbox" id="demo01">
	<span class="day">-</span>
	<span class="hour">-</span>
	<span class="minute">-</span>
	<span class="second">-</span>
</div>
<div class="yideng" id="yideng"></div>
<div class="erdengjiang" id="erdengjiang" ></div>
<div class="yanz" id="yanz"></div>
  </body>
</html>
