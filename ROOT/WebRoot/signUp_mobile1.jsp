<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
  <head>
    <title>signUp_moblie.html</title>
	
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
	<meta content="yes" name="apple-mobile-web-app-capable" />
	<meta content="black" name="apple-mobile-web-app-status-bar-style" />
	<meta content="telephone=no" name="format-detection" />
    
    <link rel="stylesheet" type="text/css" href="css/signUp-mobile.css">
	
	<script type="text/javascript" src="http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js"></script>

  </head>
  
  <script type="text/javascript">
  function init(){
  	var ops = document.getElementsByTagName("option");
  	for(var index in ops){
  		if(ops[index].value == remote_ip_info['city']){
  			ops[index].selected = "selected";
  		}
  	}
  	if("${msg}" == "add"){
  		alert("报名成功！");
  	}
  	
  }
  </script>
  
  <body onload="init()">
    <img src="img/signUp_mobile_top.gif" width="100%">
    <div class=content >
    <form id=myForm action="/query!addSeller" method="post" >
		<div>
		<label>城市：</label>
		<select id=city name=signUp.cityName >
			<c:forEach items="${cities }" var="cs"  >
				<option value="${cs.cityName }">${cs.cityName }</option>
			</c:forEach>
		</select>
		</div>
		<div>
    	<label id=companyLabel for=company >公司名称：</label>
    	<input type=text id=company name=signUp.company placeholder=请填写公司名称  />
		</div>
		<div>
    	<label id=attendeeLabel for=attendee >参会人姓名：</label>
    	<input type=text id=attendee name=signUp.attendee placeholder=请填写参会人姓名 />
		</div>
		<div>
    	<label id=jobLabel for=job >职务：</label>
    	<input type=text id=job name=signUp.job placeholder=请填写职务  />
		</div>
		<div>
    	<label id=telabel for=tel >联系方式：</label>
    	<input type=text id=tel name=signUp.tel placeholder=请填写联系方式   />
		</div>
		<div>
    	<label id=inviterLabel for=inviter >邀请人姓名/工号：</label>
    	<input type=text id=inviter name=signUp.inviter placeholder=请填写邀请人姓名/工号  />
		</div>
		<div>
    	<label id=carLabel for=car >车型：</label>
    	<input type=text id=address name=signUp.car placeholder=请填写车型  />
		</div>
		<div>
    	<label id=carportLabel for=carport >预留车位：</label>
    	<input type=radio name=signUp.carport value="0" checked="checked" style="width: 30px;" />否
    	<input type=radio name=signUp.carport value="1" style="width: 30px;" />是
		</div>
		<div>
			<button type="button" onclick="valid()">马上报名</button>
			<script type="text/javascript">
				function valid(){
				
					var company = document.getElementById("company");
					var companyLabel = document.getElementById("companyLabel");
					var attendee = document.getElementById("attendee");
					var attendeeLabel = document.getElementById("attendeeLabel");
					var job = document.getElementById("job");
					var jobLabel = document.getElementById("jobLabel");
					var tel = document.getElementById("tel");
					var telLabel = document.getElementById("telLabel");
					
					if(company.value == ""){
						alert("公司名称必须填写！");
						company.style.border = "1px solid #b94a48";
						companyLabel.style.color = "#b94a48";
						company.focus();
						return;
					}
					if(attendee.value == ""){
						alert("参会人姓名必须填写！");
						attendee.style.border = "1px solid #b94a48";
						attendeeLabel.style.color = "#b94a48";
						attendee.focus();
						return;
					}
					if(job.value == ""){
						alert("职务必须填写！");
						job.style.border = "1px solid #b94a48";
						jobLabel.style.color = "#b94a48";
						job.focus();
						return;
					}
					if(tel.value == ""){
						alert("联系电话必须填写！");
						tel.style.border = "1px solid #b94a48";
						telLabel.style.color = "#b94a48";
						tel.focus();
						return;
					}
					
					var myForm = document.getElementById("myForm");
					myForm.submit();
				}
			</script>
		</div>
    </form>
    </div>
    <img src="img/signUp_mobile_bottom.gif" width="100%">
  </body>
</html>
