<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    
    <title>错误提示</title>
   
<style>
body{background:url(images/404.jpg) center 200px no-repeat;}
.content{margin:0 auto;width:860px;}
.ts{ margin:180px 0 0 460px;font-family:"microsoft yahei"; color:#FFF; font-size:14px;}
.ts h2{ font-size:16px;}
.re{ text-align:center; margin-top:180px;}
</style>
<script language="javascript">

 var mess=setInterval("num()",1000);
var nn = 4;

function num()
{
 document.getElementById("nuber").innerHTML = nn;
 
 if(nn==0){
   clearInterval(mess);
	 window.location="";
	 }
	 else{nn --;}
}
function SetNum()
{
 
}
</script>
  </head>
  
<body>
<div class="content">
<div><img src="images/logo.gif" width="117" height="48"/></div>
<div class="ts"><h2>发生错误!</h2><br/><br/><br/><br/><span id="nuber">5</span>秒内系统将<br/>
　　自动返回首页</div>
<div class="re"><a href=""><img src="images/404-re.gif" style="border:0 none;"/></a></div>
</div>

  </body>
</html>
