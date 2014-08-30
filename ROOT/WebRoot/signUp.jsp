<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>signUp.html</title>
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">

	<link href="css/bootstrap.min.css" rel="stylesheet">

	<link rel="stylesheet" type="text/css" href="css/signUp.css">
	<link rel="stylesheet" type="text/css" href="css/square/red.css">
	
    <script type="text/javascript" src="js/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/icheck.min.js"></script>
	<script type="text/javascript" src="http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js"></script>
	<!-- <script type="text/javascript" src="http://counter.sina.com.cn/ip/" charset="gb2312"></script> -->
  </head>
  <script type="text/javascript"> 
	if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion .split(";")[1].replace(/[ ]/g,"")=="MSIE6.0") 
	{ 
	window.location.href="query!signUp?type=ie";
	} 
	else if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion .split(";")[1].replace(/[ ]/g,"")=="MSIE7.0") 
	{ 
	window.location.href="query!signUp?type=ie";
	} 
	</script>
    <body>
    <div class=content >
    	<div class=myForm >
	    	<form id="edit-profile" class="form-inline" action="/query!addSeller" method="post" >
	    		<br><br><input name="signUp.cityName" type="hidden" id="cityName" />
				<div class="control-group">		
					<label class="control-label" for="title">城市</label>	
					<div class="btn-group ">
					  <a class="btn dropdown-toggle citybtn" data-toggle="dropdown" href="#" id="cityA" >
					    <script type="text/javascript">document.writeln(remote_ip_info['city']);</script>
					    <span class="caret"></span>
					  </a>
					  <ul class="dropdown-menu">
					    <!-- dropdown menu links -->
					    <c:forEach items="${cities }" var="c">
					    	<li><a onclick="changeCity(this)" >${c.cityName }</a></li>
					    </c:forEach>
					  </ul>
					</div>
					<label for="company" id="companyLabel"  >公司名称</label>	<input placeholder=请填写公司名称(必填)  type="text" class="input-medium " name="signUp.company" id="company" data-placement="top" data-toggle="popover" data-content="请填写名称"  value=""/>
					<label for="attendee" id="nameLabel" >参会人姓名</label>	<input placeholder=请填写参会人姓名(必填) type="text" class="input-medium" name="signUp.attendee" id="attendee" value="" data-placement="top" data-toggle="popover" data-content="请填写参会人" />
				</div> <!-- /control-group -->
				<div class="control-group" id="down" >											
					<label for="job" id="jobLabel">职务</label>	<input placeholder=请填写职务(必填) type="text" class="input-medium" name="signUp.job" id="job" value="" data-placement="left" data-toggle="popover" data-content="请填写职位" />
					<label for="tel" id="telLabel">联系方式</label>	<input placeholder=请填写联系方式(必填) type="text" class="input-medium" name="signUp.tel" id="tel" value="" data-placement="bottom" data-toggle="popover" data-content="请填写联系电话" />
					<label for="inviter" id="inviterLabel">邀请人姓名/工号</label>	<input placeholder=请填邀请人信息(必填) type="text" class="input-medium" name="signUp.inviter" id="inviter" value="" data-placement="right" data-toggle="popover" data-content="请填写联系电话"  />
				</div> <!-- /control-group -->
				<div class="control-group" >											
					<label for="car" id="carLabel">车型</label>	<input placeholder=请填写车的型号  type="text" class="input-medium" name="signUp.car" id="car" value="" />
					<label >预留车位</label>	<input type="radio" name="signUp.carport" id="carportN" checked value=0 /><label for=carportN style="width: 10px;padding-left: 10px;padding-right: 10px;"  >否</label><input type="radio" name="signUp.carport" id=carportY value=1 /><label for=carportY style="width: 10px;padding-left: 10px;" >是</label>
				</div> <!-- /control-group -->
				<div class=mySubmit ><label onclick="formSubmit()" style="letter-spacing:8px;" >马上报名</label></div>
			</form>
		</div>
    </div>

<!-- Modal -->
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header" >
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel" >提示</h3>
  </div>
  <div class="modal-body">
    <h4>报名成功</h4>
  </div>
  <div class="modal-footer">
    <button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
</div>


	<script type="text/javascript">
	$(document).ready(function(){
	
		$('input').iCheck({
		    checkboxClass: 'icheckbox_square-red',
		    radioClass: 'iradio_square-red',
		    increaseArea: '20%' // optional
		  });
	
	 if("${msg}" == "add"){
	 	$("#myModal").modal('show');
	 }  
	
	var temp = false;
	$(".dropdown-menu a").each(function(){
		if($(this).html() == remote_ip_info['city']){
			temp = true;
		}
	});
	
	if(temp){
		$("#cityName").val(remote_ip_info['city']);
	}else{
		$("#cityName").val("北京");
		$("#cityA").html("北京<span class='caret'></span>");		
	}
     
	}); 

	function changeCity(t){
		$("#cityA").html($(t).html()+"&nbsp;<span class='caret'></span>");
		$("#cityName").val($(t).html());
	}
	
	function formSubmit(){
	
		if( '' == $("#company").val() || null == $("#company").val()){
			$("#companyLabel").css("color","#b94a48");
			$("#company").css("border","1px solid #b94a48");
			$("#company").popover('show');
			return;
		}
		
		if( '' == $("#attendee").val() || null == $("#attendee").val()){
			$("#nameLabel").css("color","#b94a48");
			$("#attendee").css("border","1px solid #b94a48");
			$("#attendee").popover('show');
			return;
		}
		
		if( '' == $("#job").val() || null == $("#job").val()){
			$("#jobLabel").css("color","#b94a48");
			$("#job").css("border","1px solid #b94a48");
			$("#job").popover('show');
			return;
		}
		
		if( '' == $("#tel").val() || null == $("#tel").val()){
			$("#telLabel").css("color","#b94a48");
			$("#tel").css("border","1px solid #b94a48");
			$("#tel").popover('show');
			return;
		}
		
		if( '' == $("#inviter").val() || null == $("#inviter").val()){
			$("#inviterLabel").css("color","#b94a48");
			$("#inviter").css("border","1px solid #b94a48");
			$("#inviter").popover('show');
			return;
		}
		
		
		var form = document.getElementById("edit-profile");
		form.submit();
	}
	
	</script>    
  </body>
</html>
