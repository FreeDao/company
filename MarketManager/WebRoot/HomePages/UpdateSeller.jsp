<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>商户修改</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@include file="common.jsp" %>
  </head>
  <script src="../js/jquery.min.js" type=text/javascript></script>
  <body>
    	  <!--修改会员信息-->
    	  <ul class="breadcrumb" style="border: 1px;border-color: #E8E8E8;border-style:solid;">
			  <li class="active">商户信息中心<span class="divider">/</span></li>
			  <li><a href="System/selSeller.action">商户管理</a> <span class="divider">/</span></li>
			  <li class="active">商户信息修改</li>
		  </ul>
    	  
    <div id="alls">
              <div class="bg">
              <form class="form-horizontal"  enctype="multipart/form-data" method="post" action="../MarketManager/System/updateSeller.action">
              <input type="hidden" value="${model.id}" name = "SellerId" id="sellerid">
                  <div class="control-group">
	                  <label class="control-label" for="titile">标题：</label>
	                  <div class="controls">
	                  	 <input style="width: 210px;" id="titile" name="titile" type="text" placeholder="请输入标题"  value="${model.titile}"/>
	                  	 <span class="titile"></span>
	                  </div>
                  </div>
                  <div class="control-group">
	                  <label class="control-label" for="logo">商户Logo：</label>
	                  <div class="controls">
	                  	<img style="width: 150px;height: 150px;" src="${model.logo}">
	                  </div>
	                  <div class="controls">
	                  	 <input style="width: 210px;" type="file" name="picture"/>
	                  	 <span class="logo1" style="color: red;">只能上传一张logo图</span>
	                  </div>
                  </div>
                  <div class="control-group">
	                  <label class="control-label" for="phone">手机：</label>
	                  <div class="controls">
	                  	 <input style="width: 210px;" type="text" id="phone" size="15" name="phone" id="phone" value="${model.phone}" placeholder="请输入手机号码" />
	                  	 <span class="phone"></span>
	                  </div>
                  </div>
                  <div class="control-group">
	                  <label class="control-label" for="preferential">优惠：</label>
	                  <div class="controls">
	                  	 <input type="text" id="preferential" size="15" name="preferential" value="${model.preferential}" placeholder="请输入优惠额度" />
	                  	 <span class="preferential"></span>
	                  </div>
                  </div>
                  <div class="control-group">
	                  <label class="control-label" for="address">地址：</label>
	                  <div class="controls">
	                  	 <input type="text" id="address" name="address"  value="${model.address}" placeholder="请输入优惠额度" />
	                  	 <span class="address"></span>
	                  </div>
                  </div>
                   <div class="control-group">
	                  <label class="control-label" for="brief">简介：</label>
	                  <div class="controls">
	                  	<textarea style="width: 450px; height: 150px ;overflow-y:auto;"  id="brief" name="brief" placeholder="请输入简介" >${model.brief}</textarea>
	                  	<span class="brief"></span>
	                  </div>
                  </div>
                  <div class="control-group">
	                  <label class="control-label" for="brief">活动：</label>
	                  <div class="controls">
	                  	<select name="prodetId" id="prodetId">
	                  		<option value="0">暂无活动</option>
	                  		<s:iterator value="listAct" var="d">
	                  			<option value="${d.id}">${d.title}</option>
	                  		</s:iterator>
	                  	</select>
	                  	<span class="brief"></span>
	                  </div>
                  </div>
                  <div class="control-group">
                  	  <div class="controls">
	                  <span class="yanz">${msg}</span>
	                  </div>
                  </div>
                  <div class="control-group">
	                  <div class="controls">
	                  <button style="width: 210px;" type="submit" id="sure" name="sures" onclick="return updateSeller();" class="btn  btn-block btn-primary" >确定</button>
              		  </div>
         		  </div>
              </form>
          </div>
     </div>
     <script type="text/javascript">
     	function updateSeller()
     	{
     		var titile = document.getElementById("titile").value;
     		var phone = document.getElementById("phone").value;
     		var sellerid = document.getElementById("sellerid").value;
     		var brief = document.getElementById("brief").value;
     		var preferential = document.getElementById("preferential").value;
     		var address = document.getElementById("address").value;
     		var prodetId = document.getElementById("prodetId").value;
			var shouji = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/;
			//正浮点数
			//var pre = /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
			//非负浮点数(正浮点数 + 0)
			var pre = /^\d+(\.\d+)?$/;
			if(titile == "")
			{
				$(".titile").html('<span  style="color: red;"><img src="../MarketManager/images/error.jpg">标题不能为空</span>');
   				return false;
			}
			else
			{
				$(".titile").html('<img src="../MarketManager/images/right.jpg">');
			}
			
			if(phone == "" || !shouji.test(phone))
			{
				$(".phone").html('<span style="color: red;"><img src="../MarketManager/images/error.jpg">手机格式不正确</span>');
   				return false;
			}
			else
			{
				$(".phone").html('<img src="../MarketManager/images/right.jpg">');
			}
			
			if(brief == "")
			{
				$(".brief").html('<span  style="color: red;"><img src="../MarketManager/images/error.jpg">简介不能为空</span>');
   				return false;
			}
			else
			{
				$(".brief").html('<img src="../MarketManager/images/right.jpg">');
			}
			
			if(preferential == "" ||!pre.test(preferential))
			{
				$(".preferential").html('<span  style="color: red;"><img src="../MarketManager/images/error.jpg">优惠格式不正确</span>');
   				return false;
			}
			else
			{
				$(".preferential").html('<img src="../MarketManager/images/right.jpg">');
			}
			
			if(address == "")
			{
				$(".address").html('<span  style="color: red;"><img src="../MarketManager/images/error.jpg">地址不能为空</span>');
   				return false;
			}
			else
			{
				$(".address").html('<img src="../MarketManager/images/right.jpg">');
			}
			
			/* $.ajax({
				type: "post",
				url: "../MarketManager/System/updateSeller.action",
				dataType:'json',
				data: "titile="+titile+"&phone="+phone+"&brief="+brief+"&preferential="+preferential+"&address="+address+"&sellerid="+sellerid+"&prodetId="+prodetId,//提交表单，相当于CheckCorpID.ashx?ID=XXX
				success: function(data)
				{
					if(data.msg == 1)
					{
						$(".titile").html('<img src="../MarketManager/images/right.jpg">');
						$(".phone").html('<img src="../MarketManager/images/right.jpg">');
						$(".brief").html('<img src="../SMarketManager/images/right.jpg">');
						$(".preferential").html('<img src="../MarketManager/images/right.jpg">');
						$(".address").html('<img src="../MarketManager/images/right.jpg">');
						$(".yanz").html('<img src="../MarketManager/images/right.jpg">修改成功');
					}
					if(data.msg == 2)
					{
						$(".titile").html('<img src="../MarketManager/images/right.jpg">');
						$(".phone").html('<img src="../MarketManager/images/right.jpg">');
						$(".brief").html('<img src="../MarketManager/images/right.jpg">');
						$(".preferential").html('<img src="../MarketManager/images/right.jpg">');
						$(".address").html('<img src="../MarketManager/images/right.jpg">');
						$(".yanz").html('<img src="../MarketManager/images/error.jpg">修改异常');
					}
					if(data.msg == 3)
					{
						$(".titile").html('<img src="../MarketManager/images/right.jpg">');
						$(".phone").html('<img src="../MarketManager/images/right.jpg">');
						$(".brief").html('<img src="../MarketManager/images/right.jpg">');
						$(".preferential").html('<img src="../MarketManager/images/right.jpg">');
						$(".address").html('<img src="../MarketManager/images/right.jpg">');
						$(".yanz").html('<img src="../MarketManager/images/error.jpg">地图未找到位置');
					}
				}
			}); */	    		
     	}
     </script>
  </body>
</html>
