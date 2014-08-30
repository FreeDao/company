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


	<link rel="stylesheet" type="text/css" href="css/signUp-ie.css">
	
    <script type="text/javascript" src="js/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js"></script>
	<!-- <script type="text/javascript" src="http://counter.sina.com.cn/ip/" charset="gb2312"></script> -->
  </head>
    <body onload="init()">
    <div class=content >
    	<div class=myForm >
	    	<form id="edit-profile" class="form-inline" action="/query!addSeller" method="post" >
	    		<br><br><input name="type" type="hidden" value="ie" />
				<div class="control-group">		
					<label class="control-label" for="title">城市</label>
					<select name="signUp.cityName" style="width: 146px;height: 25px;" >
						    <c:forEach items="${cities }" var="c">
						    	<option value="${c.cityName }" >${c.cityName }</option>
						    </c:forEach>
					    </select>
					<label for="company" id="companyLabel"  >公司名称</label>	<input placeholder=请填写公司名称  type="text" class="input-medium " name="signUp.company" id="company" data-placement="top" data-toggle="popover" data-content="请填写名称" data-original-title="公司名称不能为空" value=""/>
					<label for="attendee" id="nameLabel" >参会人姓名&nbsp;&nbsp;&nbsp;&nbsp;</label>	<input placeholder=请填写参会人姓名 type="text" class="input-medium" name="signUp.attendee" id="attendee" value="" data-placement="top" data-toggle="popover" data-content="请填写参会人" data-original-title="参会人不能为空"/>
				</div> <!-- /control-group -->
				<div class="control-group" id="down" >											
					<label for="job" id="jobLabel">职务</label>	<input placeholder=请填写职务 type="text" class="input-medium" name="signUp.job" id="job" value="" data-placement="left" data-toggle="popover" data-content="请填写职位" data-original-title="职位不能为空"/>
					<label for="tel" id="telLabel">联系方式</label>	<input placeholder=请填写联系方式 type="text" class="input-medium" name="signUp.tel" id="tel" value="" data-placement="bottom" data-toggle="popover" data-content="请填写联系电话" data-original-title="联系电话不能为空"/>
					<label for="inviter" id="inviterLabel">邀请人姓名/工号</label>	<input placeholder=请填邀请人信息 type="text" class="input-medium" name="signUp.inviter" id="inviter" value="" />
				</div> <!-- /control-group -->
				<div class="control-group" >											
					<label for="car" id="carLabel">车型</label>	<input placeholder=请填写车的型号  type="text" class="input-medium" name="signUp.car" id="car" value="" />
					<label >预留车位</label>	<input type="radio" name="signUp.carport" id="carportN" checked value=0 /><label for=carportN style="width: 10px;padding-left: 10px;padding-right: 10px;"  >否</label><input type="radio" name="signUp.carport" id=carportY value=1 /><label for=carportY style="width: 10px;padding-left: 10px;" >是</label>
				</div> <!-- /control-group -->
				<div class=mySubmit ><label onclick="formSubmit()" style="letter-spacing:8px;" >马上报名</label></div>
			</form>
		</div>
    </div>

	<script type="text/javascript">
	$(document).ready(function(){
	
	var options = document.getElementsByTagName("option");
	
	for(var index in options){
		if(options[index].value == remote_ip_info['city'] ) {
			options[index].selected = "selected";
		}
	}
	
	
	 if("${msg}" == "add"){
	 	alert("报名成功！");
	 }  
	 
	}); 

	function formSubmit(){
		
		if( '' == $("#company").val() || null == $("#company").val()){
			alert("公司名称不能为空！");
			$("#companyLabel").css("color","#b94a48");
			$("#company").css("border","1px solid #b94a48");
			return;
		}
		
		if( '' == $("#attendee").val() || null == $("#attendee").val()){
			alert("参会人姓名不能为空！");
			$("#nameLabel").css("color","#b94a48");
			$("#attendee").css("border","1px solid #b94a48");
			return;
		}
		
		if( '' == $("#job").val() || null == $("#job").val()){
			alert("职务不能为空！");
			$("#jobLabel").css("color","#b94a48");
			$("#job").css("border","1px solid #b94a48");
			return;
		}
		
		if( '' == $("#tel").val() || null == $("#tel").val()){
			alert("联系方式不能为空！");
			$("#telLabel").css("color","#b94a48");
			$("#tel").css("border","1px solid #b94a48");
			return;
		}
		
		if( '' == $("#inviter").val() || null == $("#inviter").val()){
			alert("邀请人不能为空！");
			$("#inviterLabel").css("color","#b94a48");
			$("#inviter").css("border","1px solid #b94a48");
			return;
		}
		
		var form = document.getElementById("edit-profile");
		form.submit();
	}
	
	</script>    
  </body>
</html>
