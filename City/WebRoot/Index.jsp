<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>鹏优信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
		<script src="js/jquery.js" type=text/javascript></script>
		
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/login.js"></script>
	
	<script type="text/javascript">
		window.onload = function()
		{
			J.onBlurText('user');
			J.onBlurText('pwd');
			J.onBlurText('validCode');
			createCode();
		};
		
	(function(){
	if(!window.J){ window.J = {};}
	
	/**
	 * 得到obj
	 */
	function $(){
		var elements = [];
		for(var i=0; i<arguments.length; i++){
			var element = arguments[i];
			if(typeof element == 'string'){element = document.getElementById(element);}
			if(arguments.length == 1){
				return element;
			}
			elements.push(element);
		}
		return elements;		
	}
	window.J.$ = $;
	
	/**
	 * 失去焦点
	 * @param {Object} obj
	 */
	function onBlurText(obj){
		$(obj).onfocus = function(){
			if(this.value==this.defaultValue)this.value='';
		}
		$(obj).onblur = function(){
			if(this.value.length<1)this.value=this.defaultValue;
		}
	}
	window.J.onBlurText = onBlurText;
	
	function objNull(obj){
		if ($(obj).value.length < 1) {
			alert('aa');
			return false;
		}
	}
	window.J.objNull = objNull;
	
	
})();


//验证码
var code ; //在全局 定义验证码
function createCode(){ 
	code = new Array();
	var codeLength = 6;//验证码的长度
	var checkCode = document.getElementById("checkCode");
	checkCode.value = "";
	
	var selectChar = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','S','T','U','V','W','X','Y','Z');
	
	for(var i=0;i<codeLength;i++) {
	   var charIndex = Math.floor(Math.random()*36);
	   code +=selectChar[charIndex];
	}
	if(code.length != codeLength){
	   createCode();
	}
	checkCode.value = code;
}

//判断输入条件是否为空
function CheckAdmin(){
	var name = document.getElementById("user").value;
	var pwd = document.getElementById("pwd").value;
	var inputCode = document.getElementById("validCode").value;
	var inputCode2 = document.getElementById("validCode").value.toUpperCase();
	if(name == "请输入用户名")
	{
		document.getElementById("yzm").innerHTML="请输入用户名!";
		return false;
	}else{
		document.getElementById("yzm").innerHTML="";
	}
	if(pwd == "")
	{
		document.getElementById("yzm").innerHTML="请输入密码！";
		return false;
	}else{
		document.getElementById("yzm").innerHTML="";
	}
	if(inputCode == "请输入验证码") {
	    document.getElementById("yzm").innerHTML="请输入验证码!";
	    return false;
	}else{
		document.getElementById("yzm").innerHTML="";
	}
	if(inputCode2 != code )
	{
	    document.getElementById("yzm").innerHTML="验证码输入错误!";
	    createCode();
	    return false;
	}else{
		document.getElementById("yzm").innerHTML="";
	}
	var url = "../City/news/adminLogin.action";
		$.getJSON(url, {"ranum":Math.random(),"name":name,"pwd":pwd},function(data)
		{
			document.getElementById("yzm").innerHTML=data.msg;
			if(data.msg == "登陆成功")
			{
				document.getElementById("yzm").innerHTML="";
				location.href="/City/System/pagesLogin.action";
			}
		});
}
	</script>
  </head>
  
  <body>
    <div id="main">
  <div id="wrapper">

      <div id="sys_name">同城生活圈管理系统</div>
      <ul id="cont">
        <li>
          <label class="lb" for="user">用户名</label>
          <input name="userName" id="user" type="text" class="ip" value="请输入用户名" maxlength="30" />
        </li>
        <li>
          <label class="lb" for="pwd">密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
          <input name="passWord" id="pwd" type="password" class="ip" maxlength="30" />
        </li>
        <li>
          <label class="lb" for="validCode">验证码</label>
          <input name="vCode" id="validCode" type="text" class="ip"  value="请输入验证码" maxlength="6" />
          <input type="button" id="checkCode" class="code" style="width:80px" onClick="createCode()" />
          <a href="#" onClick="createCode()">换一组</a>
        </li>
        <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="userNameOne" id="yzm" style="color: red;"></label></li>
        <li><span>
			 <input type="button" value = "登录"  onclick="return CheckAdmin()"/>
          	 <input type="reset" value="重置" />
          	 
          </span>
        </li>
      </ul>
      <p id="copy">Version 1.0 Copyright &copy; 2013 <a href="http://www.pengyou.com" target="_blank" >鹏优信息</a></p>
  
  </div>
</div>
  </body>
</html>
