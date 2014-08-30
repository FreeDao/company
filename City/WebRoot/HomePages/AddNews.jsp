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
    
    <title>添加咨询</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="js/jquery.js" type=text/javascript></script>
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
    			标题
    		</td>
    		<td>
    		<input type="hidden" value="${newsDetails.id}" id="id">
    			<input type="text" name="title" id="title" value="${newsDetails.title}">
    		</td>
    	</tr>
    	<tr>
    		<td>
    			作者
    		</td>
    		<td>
    			<input type="text" name="author" id="author" value="${newsDetails.author}">
    		</td>
    	</tr>
    	<tr>
    		<td>
    			内容
    		</td>
    		<td>
    		<textarea rows="60" cols="60" name="conent" id="conent" >${newsDetails.conent}</textarea>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			内容
    		</td>
    		<td>
    			
    		</td>
    	</tr>
    	<tr>
    		<td>
    			城市:
    		</td>
    		<td>
    			<select name="city" id="city">
    			<s:iterator value="listCity" var="d">
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
    	<tr align="center"><td colspan="2"><input type="submit" value="确定" onclick="return yanzheng();" /></td></tr>
    </table>
    <script  type="text/javascript">
    	//判断是否为空
    	function yanzheng()
    	{
    		var title = document.getElementById("title").value;
    		var author = document.getElementById("author").value;
    		var conent = document.getElementById("conent").value;
    		var city = document.getElementById("city").value;
    		var id = document.getElementById("id").value;
    		
    		if(title == "")
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请输入标题！</label>');
    			return false;
    		}
    		if(author == "")
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请输入作者名！</label>');
    			return false;
    		}
    		if(conent == "")
    		{
    			$(".yzm").html('<label class="userNameOne" id="yzm" class="yzm" style="color: red;">请输入内容！</label>');
    			return false;
    		}
    		$.ajax({
				type: "post",
				url: "../City/news/addNews.action",
				dataType:'json',
				data: "title="+title+"&author="+author+"&conent="+conent+"&city="+city+"&id="+id,//提交表单，相当于CheckCorpID.ashx?ID=XXX
				success: function(data)
				{
					if(data.mess == 1)
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
