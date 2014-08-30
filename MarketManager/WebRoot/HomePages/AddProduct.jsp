<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

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
			  <li class="active">添加产品中心<span class="divider">/</span></li>
			  <li><a href="System/selProductNum.action">产品管理</a> <span class="divider">/</span></li>
			  <li class="active">添加产品修改</li>
		  </ul>
    	  
    <div id="alls">
              <div class="bg">
              <form enctype="multipart/form-data" method="post" class="form-horizontal">
              <input type="hidden" value="${model.id}" name = "id" id="id">
                  <div class="control-group">
	                  <label class="control-label" for="titile">标题：</label>
	                  <div class="controls">
	                  	 <input style="width: 210px;" id="titile" name="titile" type="text" placeholder="请输入标题"  value="${model.name}"/>
	                  	 <input type="hidden" name="compositeId" value="${model.seller}" id="compositeId">
	                  	 <input type="hidden" name="compositeIdTwo" value="${sellId}" id="compositeIdTwo">
	                  	 <span class="titile"></span>
	                  </div>
                  </div>
                   <div class="control-group">
                  	  <div class="controls">
	                  <span class="yanz" ></span>
	                  </div>
                  </div>
                  <div class="control-group">
	                  <div class="controls">
	                  <button style="width: 210px;" type="button"  id="sure" name="sures" onclick="return updateSeller();" class="btn  btn-block btn-primary" >确定</button>
              			</div>
         		  </div>
              </form>
          </div>
     </div>
     <script type="text/javascript">
     	function updateSeller()
     	{
     		var titile = document.getElementById("titile").value;
     		var id = document.getElementById("id").value;
     		var compositeId = document.getElementById("compositeId").value;
     		var compositeIdTwo = document.getElementById("compositeIdTwo").value;
     		var tt = "";
     		if(compositeId == null || compositeId=="")
     		{
     			tt = compositeIdTwo;
     		}
     		else
     		{
     			tt = compositeId;
     		}
			if(titile == "")
			{
				$(".titile").html('<span  style="color: red;"><img src="../MarketManager/images/error.jpg">标题不能为空</span>');
   				return false;
			}
			else
			{
				$(".titile").html('<img src="../MarketManager/images/right.jpg">');
			}
			$.ajax({
				type: "post",
				url: "../MarketManager/System/addProduct.action",
				dataType:'json',
				data: "product="+titile+"&id="+id+"&compositeId="+tt,//提交表单，相当于CheckCorpID.ashx?ID=XXX
				success: function(data)
				{
					if(data.msg == 1)
					{
						$(".titile").html('<img src="../MarketManager/images/right.jpg">');
						$(".yanz").html('<img src="../MarketManager/images/right.jpg">修改成功');
					}
					if(data.msg == 2)
					{
						$(".titile").html('<img src="../MarketManager/images/right.jpg">');
						$(".yanz").html('<img src="../MarketManager/images/error.jpg">修改异常');
					}
					if(data.msg == 3)
					{
						$(".titile").html('<img src="../MarketManager/images/right.jpg">');
						$(".yanz").html('<img src="../MarketManager/images/error.jpg">地图未找到位置');
					}
				}
			});	    		
     	}
     </script>
  </body>
</html>
