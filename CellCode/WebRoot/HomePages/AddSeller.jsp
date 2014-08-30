<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加管理员</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js" type=text/javascript></script>
	<style type="text/css">
	table{
		width:400px;
		height:400px;
		}
	</style>

  </head>
  
  <body>
    <table align="center">
    	<tr>
    		<td>
    			商&nbsp;户&nbsp;名:
    		</td>
    		<td>
    			<input type="text" name="title" id="name">
    		</td>
    	</tr>
    	<tr>
    		<td>
    			公司&nbsp;&nbsp;&nbsp;&nbsp;Logo:
    		</td>
    		<td>
    		<!-- 上传图片 -->
    			<input type="password" name="logo" id="password">
    		</td>
    	</tr>
    	<tr>
    		<td>
    			手&nbsp;&nbsp;&nbsp;&nbsp;机:
    		</td>
    		<td>
    			<input type="text" name="phone" id="nickname"/>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			性&nbsp;&nbsp;&nbsp;&nbsp;别:
    		</td>
    		<td>
    			<select name="sex" id="sex">
					<option value="0">请选着</option>
					<option value="1">男</option>   
					<option value="2">女</option>      			
    			</select>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			类&nbsp;&nbsp;&nbsp;&nbsp;别:
    		</td>
    		<td>
	    		<select name="isSuper" id="issuper">
	    			<option value="0">请选择</option>
	    			<option value="1">超级管理员</option>
	    			<option value="2">普通管理员</option>
	    		</select>
	    		<input type="hidden" value="${sessionScope.userid}" name="id">
    		</td>
    	</tr>
    	<tr>
    		<td>
    			权限名:
    		</td>
    		<td>
    		<select name="roles" id="roles">
    			<s:iterator value="listRoles" var = "d">
    			<option value="${d.id}">${d.rolesName}</option>
    			</s:iterator>
    		</select>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			电话号码:
    		</td>
    		<td>
    			<input type="text" name="phone" id="phone"/>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			Qq&nbsp;号&nbsp;码:
    		</td>
    		<td>
    			<input type="text" name="qq" id="qq"/>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			商家等级:
    		</td>
    		<td>
    			<input type="text" name="text" id="qq"/>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			Email:
    		</td>
    		<td>
    			<input type="text" name="email" id="email"/>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			提示:
    		</td>
    		<td>
    			<label class="userNameOne" id="yzm" style="color: red;"></label>
    		</td>
    	</tr>
    	<tr align="center"><td colspan="2"><input type="submit" value="确定" onclick="return yanzheng();" /></td></tr>
    </table>
    <script  type="text/javascript">
    	//判断是否为空
    	function yanzheng()
    	{
    		var name = document.getElementById("name").value;
    		var password = document.getElementById("password").value;
    		var nickname = document.getElementById("nickname").value;
    		var sex = document.getElementById("sex").value;
    		var issuper = document.getElementById("issuper").value;
    		var systemid = document.getElementById("roles").value;
    		var phone = document.getElementById("phone").value;
    		var qq = document.getElementById("qq").value;
    		var email = document.getElementById("email").value;
    		
    		var zz = /^[0-9]*$/;
    		var sji = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/;
    		var dz = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
    		
    		if(name == "")
    		{
    			document.getElementById("yzm").innerHTML="请输入用户名！";	
    			return false;
    		}
    		else
    		{
    			document.getElementById("yzm").innerHTML="";	
    		}
    		if(name.length>10)
    		{
    			document.getElementById("yzm").innerHTML="用户名过长请您重新输入！";	
    			return false;
    		}
    		else
    		{
    			document.getElementById("yzm").innerHTML="";
    		}
    		if(password == "")
    		{
    			document.getElementById("yzm").innerHTML="请输入密码！";	
    			return false;
    		}
    		else
    		{
    			document.getElementById("yzm").innerHTML="";
    		}
    		if(password.length<6 || password.length>15)
    		{
    			document.getElementById("yzm").innerHTML="密码必须为6-15位之间！";	
    			return false;
    		}
    		else
    		{
    			document.getElementById("yzm").innerHTML="";
    		}
    		if(nickname == "" || nickname.length>10)
    		{
    			document.getElementById("yzm").innerHTML="您未输入昵称或昵称输入过长！";	
    			return false;
    		}
    		else
    		{
    			document.getElementById("yzm").innerHTML="";
    		}
    		if(sex == 0)
    		{
    			document.getElementById("yzm").innerHTML="请选择性别！";
    			return false;
    		}
    		else
    		{
    			document.getElementById("yzm").innerHTML="";
    		}
    		if(issuper == 0)
    		{
    			document.getElementById("yzm").innerHTML="请选择类别！";
    			return false;
    		}
    		else
    		{
    			document.getElementById("yzm").innerHTML="";
    		}
    		if(systemid == "" || !zz.test(systemid))
    		{
    			document.getElementById("yzm").innerHTML="权限编号为空或不为数字！";
    			return false;
    		}
    		else
    		{
    			document.getElementById("yzm").innerHTML="";
    		}
    		if(phone == "" || !sji.test(phone))
    		{
    			document.getElementById("yzm").innerHTML="电话号码为空或格式不正确！";
    			return false;
    		}
    		else
    		{
    			document.getElementById("yzm").innerHTML="";
    		}
    		if(qq == "")
    		{
    			document.getElementById("yzm").innerHTML="请输入QQ号码！";
    			return false;
    		}
    		else
   			{
   				document.getElementById("yzm").innerHTML="";
    		}
    		if(qq.length<5 || qq.length>15)
    		{
    			document.getElementById("yzm").innerHTML="请您输入正确的qq号码！";
    			return false;
	    	}
	    	else
	    	{
	    		document.getElementById("yzm").innerHTML="";
	    	}
    		if(email == "" || !dz.test(email))
    		{
    			document.getElementById("yzm").innerHTML="电子邮箱不能为空或格式不正确！";
    			return false;
    		}
    		else
    		{
    			document.getElementById("yzm").innerHTML="";
    		}
    		
			var url = "../era/news/addAdmin.action";
			$.getJSON(url, {"ranum":Math.random(),"name":name,"password":password,"nickname":nickname,"sex":sex,"issuper":issuper,"systemid":systemid,"phone":phone,"qq":qq,"email":email,},function(data)
			{
				if(data.msg == "添加成功")
				{
					location.href="../era/System/adminUser.action";
					document.getElementById("yzm").innerHTML="";
				}
				else
				{
					document.getElementById("yzm").innerHTML=data.msg;
				}
			});
    	}
    </script>
  </body>
</html>
