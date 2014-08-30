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
     <script src="js/jquery.js" type=text/javascript></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

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
    			用&nbsp;户&nbsp;名:
    		</td>
    		<td>
    			<input type="text" name="adminName" id="nickname">
    		</td>
    	</tr>
    	<tr>
    		<td>
    			密&nbsp;&nbsp;&nbsp;&nbsp;码:
    		</td>
    		<td>
    			<input type="password" name="passWrod" id="password">
    		</td>
    	</tr>
    	<tr>
    		<td>
    			昵&nbsp;&nbsp;&nbsp;&nbsp;称:
    		</td>
    		<td>
    			<input type="text" name="nickName" id="nickname"/>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			性&nbsp;&nbsp;&nbsp;&nbsp;别:
    		</td>
    		<td>
    			<select name="sex" id="sex">
					<option value="0">请选择</option>
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
    			Email:
    		</td>
    		<td>
    			<input type="text" name="email" id="email"/>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			城市:
    		</td>
    		<td>
    			<select name="cityId" id="cityId">
    				<option value="0">总管理员</option>
    				<s:iterator value="listCity" var = "d">
    					<option value="${d.id}">${d.cityName}</option>
    				</s:iterator>
    			</select>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			提示:
    		</td>
    		<td class="yzm" id="yzm">
    			
    		</td>
    	</tr>
    	<tr align="center"><td colspan="2"><input type="button" value="确定" onclick="return yanzheng();" /></td></tr>
    </table>
    <script  type="text/javascript">
    	//判断是否为空
    	function yanzheng()
    	{
    		var password = document.getElementById("password").value;
    		var nickname = document.getElementById("nickname").value;
    		var sex = document.getElementById("sex").value;
    		var issuper = document.getElementById("issuper").value;
    		var systemid = document.getElementById("roles").value;
    		var phone = document.getElementById("phone").value;
    		var qq = document.getElementById("qq").value;
    		var email = document.getElementById("email").value;
    		var cityId = document.getElementById("cityId").value;
    		var zz = /^[0-9]*$/;
    		var sji = /(^(\d{3,4}-)?\d{7,8})$|(13[0-9]{9})/;
    		var dz = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
    		if(nickname == "")
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请输入用户名</label>');
    			return false;
    		}
    		if(nickname.length>10)
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">用户名过长</label>');
    			return false;
    		}
    		if(password == "")
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">密码不能为空</label>');
    			return false;
    		}
    		if(password.length<6 || password.length>15)
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">密码必须为6-15位之间</label>');
    			return false;
    		}
    		if(sex == 0)
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请选择性别</label>');
    			return false;
    		}
    		if(issuper == 0)
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请选择类型</label>');
    			return false;
    		}
    		if( phone.length != 11 )
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">电话号码为空或格式不正确</label>');
    			return false;
    		}
    		if(qq == "")
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请输入QQ号码</label>');
    			return false;
    		}
    		if(qq.length<5 || qq.length>15)
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请输入正确qq</label>');
    			return false;
	    	}
	    	if(email == "" || !dz.test(email))
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请输入正确Email</label>');
    			return false;
    		}
    		$.ajax({
				type: "post",
				url: "../City/news/addAdmin.action",
				dataType:'json',
				data: "name="+nickname+"&password="+password+"&nickname="+nickname+"&sex="+sex+"&issuper="+issuper+"&systemid="+systemid+"&phone="+phone+"&qq="+qq+"&email="+email+"&cityId="+cityId,//提交表单，相当于CheckCorpID.ashx?ID=XXX
				success: function(data)
				{
					if(data.mess == 1)
					{
						$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">用户名重复</label>');
					}
					else if(data.mess == 2)
					{
						$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">添加成功</label>');
					}
					else
					{
						$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">添加异常</label>');
					}
				}
			});
			} 
    </script>
  </body>
</html>
