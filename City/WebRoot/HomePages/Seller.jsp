<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>商户信息列表</title>
    <script src="js/jquery.js" type=text/javascript></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="../City/js/jquery-1.7.1.js" type=text/javascript></script>
<style type="text/css">
<!--
html,body {height:100%; margin:0px; font-size:12px;}
.mydiv {
background-color: #FFCC66;
border: 1px solid #f00;
text-align: center;
line-height: 40px;
font-size: 12px;
font-weight: bold;
z-index:999;/*优先级*/
width: 300px;
height: 120px;
left:50%;
top:50%;
margin-left:-150px!important;/*FF IE7 该值为本身宽的一半 ，!important的出现就是为了让用户自己设置被执行语句的优先级*/
margin-top:-60px!important;/*FF IE7 该值为本身高的一半*/
margin-top:0px;
position:fixed!important;/* FF IE7*/
position:absolute;/*IE6*/
_top:       expression(eval(document.compatMode &&
            document.compatMode=='CSS1Compat') ?
            documentElement.scrollTop + (document.documentElement.clientHeight-this.offsetHeight)/2 :/*IE6*/
            document.body.scrollTop + (document.body.clientHeight - this.clientHeight)/2);/*IE5 IE5.5*/

}

.bg,.popIframe {
background-color: #666; display:none;
width: 100%;
height: 100%;
left:0;
top:0;/*FF IE7*/
filter:alpha(opacity=50);/*IE ，透明度*/
opacity:0.5;/*FF*/
z-index:1;/*优先级*/
position:fixed!important;/*FF IE7*/
position:absolute;/*IE6*/
_top:       expression(eval(document.compatMode &&
            document.compatMode=='CSS1Compat') ?
            documentElement.scrollTop + (document.documentElement.clientHeight-this.offsetHeight)/2 :/*IE6*/
            document.body.scrollTop + (document.body.clientHeight - this.clientHeight)/2);
}
.popIframe {
filter:alpha(opacity=0);/*IE 设置透明度*/
opacity:0;/*FF*/
}
-->
</style>
<style type="text/css">
<!--
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
}
.STYLE1 {
	color: #e1e2e3;
	font-size: 12px;
}
.STYLE6 {
	color: #000000; font-size: 12;
}
.STYLE10 {
	color: #000000; font-size: 12px;
 }
.STYLE19 {
	color: #344b50;
	font-size: 12px;
}
.STYLE21 {
	font-size: 12px;
	color: #3b6375;
}
.STYLE22 {
	font-size: 12px;
	color: #295568;
}

.page{ text-align:center; margin-bottom:10px;}
.page form{display:inline !important; zoom:1;}
-->
</style>
<style type="text/css">
<!--
html,body {height:100%; margin:0px; font-size:12px;}
.mydiv {
background-color: #FFCC66;
border: 1px solid #f00;
text-align: center;
line-height: 40px;
font-size: 12px;
font-weight: bold;
z-index:999;/*优先级*/
width: 300px;
height: 120px;
left:50%;
top:50%;
margin-left:-150px!important;/*FF IE7 该值为本身宽的一半 ，!important的出现就是为了让用户自己设置被执行语句的优先级*/
margin-top:-60px!important;/*FF IE7 该值为本身高的一半*/
margin-top:0px;
position:fixed!important;/* FF IE7*/
position:absolute;/*IE6*/
_top:       expression(eval(document.compatMode &&
            document.compatMode=='CSS1Compat') ?
            documentElement.scrollTop + (document.documentElement.clientHeight-this.offsetHeight)/2 :/*IE6*/
            document.body.scrollTop + (document.body.clientHeight - this.clientHeight)/2);/*IE5 IE5.5*/

}

.bg,.popIframe {
background-color: #666; display:none;
width: 100%;
height: 100%;
left:0;
top:0;/*FF IE7*/
filter:alpha(opacity=50);/*IE ，透明度*/
opacity:0.5;/*FF*/
z-index:1;/*优先级*/
position:fixed!important;/*FF IE7*/
position:absolute;/*IE6*/
_top:       expression(eval(document.compatMode &&
            document.compatMode=='CSS1Compat') ?
            documentElement.scrollTop + (document.documentElement.clientHeight-this.offsetHeight)/2 :/*IE6*/
            document.body.scrollTop + (document.body.clientHeight - this.clientHeight)/2);
}
.popIframe {
filter:alpha(opacity=0);/*IE 设置透明度*/
opacity:0;/*FF*/
}
-->
</style>
<style type="text/css">
<!--
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
}
.STYLE1 {
	color: #e1e2e3;
	font-size: 12px;
}
.STYLE6 {
	color: #000000; font-size: 12;
}
.STYLE10 {
	color: #000000; font-size: 12px;
 }
.STYLE19 {
	color: #344b50;
	font-size: 12px;
}
.STYLE21 {
	font-size: 12px;
	color: #3b6375;
}
.STYLE22 {
	font-size: 12px;
	color: #295568;
}

.page{ text-align:center; margin-bottom:10px;}
.page form{display:inline !important; zoom:1;}
-->
</style>
<script language="javascript" type="text/javascript">
var sortId;

function showDiv(bb,id){
document.getElementById('popDiv').style.display='block';
document.getElementById('popIframe').style.display='block';
document.getElementById('bg').style.display='block';
	
		
				var url = "../City/news/selSellerMarket.action";
		    	$.getJSON(url, {"city":bb},function(data)
					{
						var cc='<option value="">未选择</option>';
						for(var i = 0;i<data.msg.length;i++)
						{
							cc+='<option value="'+data.msg[i].id+'">'+data.msg[i].type+'</option>'
						}
						$("#typeid1").html(cc);
					});
		
	
			fl=''
			fl+='移动市场排序'
			fl+='<form action="../City/System/moveType.action" method="post">'
			fl+='<input type="hidden" value="'+id+'" name="selid"/>'
			
			fl+='<select name="city" id="city1">'
        	fl+='<option value="c"  onclick="return onType1();">未选择</option>'
	        fl+='<s:iterator value="listCity" var ="d">'
	        fl+='<option value="${d.id}"  onclick="return onType1();">${d.cityName}</option>'
	        fl+='</s:iterator></select>'
			fl+='<select name="typeid" id="typeid1"></select>'
			fl+='<br/><input type="submit" value="移动" onclick="return checkMove()">'
			fl+='</form>'
			fl+='<a href="javascript:closeDiv()">关闭层</a>'
			$(".mydiv").html(fl);

}

function checkMove(){
	if($("#city1").val() == "c"){
		alert("请选择城市！");
		return false;
	}else if($("typeid1").val() == "d"){
		alert("请选择类型");
		return false;
	}
}

function closeDiv(){
document.getElementById('popDiv').style.display='none';
document.getElementById('bg').style.display='none';
document.getElementById('popIframe').style.display='none';
}
</script>
  </head>
  
  <body >
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
    
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="STYLE1"> 商户信息列表</span></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  	<tr>
  	<td><table width="100%" border="0" cellpadding="0" cellspacing="1">
  	<s:form id="login" action="../City/System/selSeller.action" method="post">
    	<tr>
    	<td width="10%" height="30"><a href="../City/System/sellerPage.action">新增</a></td>
    	<td width="90%" height="30">
        <div align="center">
        商户名称:<input name="name" type="text" />
        <input type="hidden" value="${sessionScope.cityId}" name="citytt" id = "citytt">
        类型：<select name="typeid" id="typeid">
    				
    			</select>
        <input name="title" type="submit" value="搜索" />
        </div>
        </td></tr>
         </s:form>
    </table></td>
  </tr>
  <tr>
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
      <tr>
        <td width="3%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">编号</span></div></td>
        <td width="8%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">标题</span></div></td>
        <td width="9%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">公司图片</span></div></td>
        <td width="8%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">电话</span></div></td>
        <td width="4%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">公司产品</span></div></td>
        <td width="8%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">公司图片</span></div></td>
        <td width="12%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">简介</span></div></td>
        <td width="12%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">添加时间</span></div></td>
        <td width="12%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">优惠</span></div></td>
        <td width="12%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">地址</span></div></td>
        <td width="8%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">市场类型名称</span></div></td>
        <td width="12%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">操作</span></div></td>
      </tr>
      <s:iterator value="listSeller" var="d">
      <tr>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${d[0].id}</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${d[0].titile}</div></td>
		<td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19"><img src="${d[0].logo}" width="100" height="100"></span></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${d[0].phone}</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><a href="../City/System/selProduct.action?seller=${d[0].id}">查看</a></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><a href="../City/System/selImage.action?type=1&id=${d[0].id}">查看</a></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19">
        <div align="center">
        <c:if test="${fn: length(d[0].brief)>11}">  
       		${fn: substring(d[0].brief, 0, 12)}...
       	</c:if> 
       	<c:if test="${fn: length(d[0].brief)<=11}">
       		${d[0].brief}
       	</c:if>
       	</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${d[0].addtime}</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${d[0].preferential}</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${d[0].address}</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${d[1].type}</div></td>
        <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE21"><a href="../City/System/delSeller.action?did=${d[0].id}&namett=${nameOne}&city=${cityExt}&typeid=${typeide}">删除</a>|<a href="../City/System/updateSellerPage.action?id=${d[0].id}">修改</a>
        |<span  onclick="showSort('${d[0].id}')" style="color:rgb(0,0,238);cursor: pointer;text-decoration: underline;"  >排序</span>
        <script type="text/javascript">
        function showSort(id){
        	$("#sortContent").css("top", $("body").scrollTop()+200);
        	$("#sortBg").css({ opacity: .7 });
        	$("#sortBg").fadeIn();
        	$("#sortContent").fadeIn();
        	$("body").css("overflow","hidden");
        	$("#sortContent").css({ opacity: 10 });
			sortId = id;
        }
        </script>
        <%-- |<a href="javascript:;" onclick="showDiv(${d[0].cityId},${d[0].id})">移动市场</a> --%></div></td>
      </tr>
      </s:iterator>
    </table></td>
  </tr>
  
  <tr>
  <td class="page">
 
  <s:if test="%{pagenum==1}">上一页</s:if>
				<s:elseif test="%{pagenum!=1}">
				<a href="../City/System/selSeller.action?pagenum=${pagenum-1}&name=${nameOne}&typeid=${typeide}&city=${cityExt}">上一页</a>
				</s:elseif>
				<s:form name="form5" action="../City/System/selSeller.action" namespace="/Web" method="post" theme="simple" style="display:inline-block;">
				第<s:textfield type="text" name="pagenum" id="textfield" cssClass="pagetext" size="2"></s:textfield>
				<input type="hidden" name="cityArea" value="${pageCount}"/>
				<input type="hidden" name="name" value="${nameOne}"/>
				<input type="hidden" name="typeid" value="${typeide}"/>
				<input type="hidden" name="city" value="${cityExt}"/>页 
				</s:form>
				<input onClick="tijiao();" value="提交" type="button" class="search-btn">
				<span class="c_fl">共 <s:property value="pageCount"></s:property>页</span>
				<s:if test="%{pageCount>pagenum}">
					<a href="../City/System/selSeller.action?pagenum=${pagenum+1}&name=${nameOne}&typeid=${typeide}&city=${cityExt}">
					下一页 </a>
				</s:if>
				<s:elseif test="%{pagenum==pageCount}">
					下一页
				</s:elseif>
  </td>
  </tr>
</table>
<div id="popDiv" class="mydiv" style="display:none;">
			
</div>
<div id="bg" class="bg" style="display:none;"></div>
		<br><br>
		<iframe id='popIframe' class='popIframe' frameborder='0' ></iframe>
  <script type="text/javascript">
  
  
$(document).ready(function(){
 
	$("#sortBg").height($(document).height());

	 var city = document.getElementById("citytt").value;
			if(city == "c")
			{
				var cc='';
				cc+='<option value="d">未选择</option>'
				$("#typeid").html(cc);
			}
			else
			{
				var url = "../City/news/selSellerMarket.action";
		    	$.getJSON(url, {"ranum":Math.random(),"city":city},function(data)
					{
						var cc='';
						cc='<option value="">未选择</option>';
						for(var i = 0;data.msg.length>i;i++)
						{
							cc+='<option value="'+data.msg[i].id+'">'+data.msg[i].type+'</option>'
						}
						$("#typeid").html(cc);
					});
			}
}); 

  function tijiao() {
	var ppt = document.getElementById("textfield").value;
		var znengweishuz =/^[0-9]*$/;
		if(!znengweishuz.test(ppt)){ 
				alert("页数只能为数字！");
				return false;
			}
		document.form5.submit();
	}
	

	    function  onType1()
	   {
	   	var city = document.getElementById("city1").value;
			if(city == "c")
			{
				var cc='';
				cc+='<option value="d">未选择</option>'
				$("#typeid1").html(cc);
			}
			else
			{
				var url = "../City/news/selSellerMarket.action";
		    	$.getJSON(url, {"ranum":Math.random(),"city":city},function(data)
					{
						var cc='';
						for(var i = 0;data.msg.length>i;i++)
						{
							cc+='<option value="'+data.msg[i].id+'">'+data.msg[i].type+'</option>'
						}
						$("#typeid1").html(cc);
					});
			}
	   }
	</script>
	
	<div style="background-color: gray;position: absolute;width: 100%;height: 100%;top: 0;display: none;" id=sortBg onclick="hideSortBg()" ></div>
	<script type="text/javascript">
	function hideSortBg(){
		$("#sortBg").fadeOut();
		$("#sortContent").fadeOut();
		$("body").css("overflow","auto");
	}
	</script>
	<div style="display: none; position: absolute ;width: 220px;height: 140px; top : 35%;left : 43% ;border: 1px solid #ccc ; background-color: white;border-radius:5px;" id=sortContent >
		<div style="height: 30px;width: 100%;border-bottom: 1px solid #ccc" >
			<h3 style="margin: 10px;">设置排序</h3>
		</div>
		<ul style="list-style-type:none;">
			<li >
				<label>排序名次：</label>
				<select id=sortVal >
					<option value="10" >1</option>
					<option value="9" >2</option>
					<option value="8" >3</option>
					<option value="7" >4</option>
					<option value="6" >5</option>
					<option value="5" >6</option>
					<option value="4" >7</option>
					<option value="3" >8</option>
					<option value="2" >9</option>
					<option value="1" >10</option>
					<option value="0" >取消VIP</option>
				</select>
			</li>
		</ul>
		<div style="text-align: center;">
			<button onclick="submitSort()">确定</button>
		<script type="text/javascript">
		function submitSort(){
			window.location.href = "../City/System/upSeller.action?id="+sortId+"&sort="+$("#sortVal").val();
		}
		</script>
		</div>
	</div>
	
  </body>
</html>
  