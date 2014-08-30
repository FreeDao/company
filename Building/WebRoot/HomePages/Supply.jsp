<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>供求管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link type="text/css"
	href="${pageContext.request.contextPath}/css/center.css"
	rel="stylesheet" />
</head>

<body>
	<div id="all3">
		<div class="va">
			<div class="cc">
				<div class="pt">供求管理</div>
			</div>
			<div class="ee">
				<form action="../Building/System/selSupply.action"
					method="post">
					<table>
						<tr align="center" style="width: 10px;">
							<td style="width: 10px; height: 15px;">标题：<input type="text" name="title"></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input type="submit"
								value="搜索" id="sou" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="ce">供求管理</div>
		<div class="va1">
			<a href="../Building/System/addSupplyPage.action">添加供求信息</a>
			<div class="kk">
				<table border="1px" cellspacing="0px" cellpadding="0px">
					<tr height="50">
						<td align="center">编号</td>
						<td align="center">标题</td>
						<td align="center">内容</td>
						<td align="center">价格</td>
						<td align="center">地址</td>
						<td align="center">图片</td>
						<td align="center">公司</td>
						<td align="center">联系人</td>
						<td align="center">电话</td>
						<td align="center">订单量</td>
						<td align="center">截止日期</td>
						<td align="center">产品内容</td>
						<td align="center">操作</td>
					</tr>
					<s:iterator value="list_supply" var="d">
					<tr height="50px;">
						<td align="center" width="30px">${d.id}</td>
						<td align="center" width="30px">
							<div style="width: 30px; height:100px; overflow: hidden;">
								${d.title}
							</div>
						</td>
						<td align="center" width="30px">
							<div style="width: 30px; height:100px; overflow: hidden;">
								${d.content}
							</div>
						</td>
						<td align="center" width="30px">${d.price}</td>
						<td align="center">
							<div style="width: 60px; height:100px; overflow: hidden;">
								${d.address}
							</div>
						</td>
						<td align="center" width="30px">
							<img src="${d.logo}" width="30" height="50">
						</td>
						<td align="center" width="30px;">${d.corporate}</td>
						<td align="center" width="30px">${d.info}</td>
						<td align="center" width="30px">${d.phone}</td>
						<td align="center" width="30px">${d.orderNum}</td>
						<td align="center" width="30px">${d.matureTime}</td>
						<td align="center" width="30px;">
							<div style="width: 60px; height:100px; overflow: hidden;">
								${d.productcontent}
							</div>
						</td>
						<td align="center" width="30px">
							<a href="../Building/System/delSupply.action?id=${d.id}">删除</a>
							<a href="../Building/System/updateSupplyPage.action?id=${d.id}">修改</a>
						</td>
					</tr>
					</s:iterator>
				</table>
			</div>
			<div class="aa" align="center">
				<s:if test="%{pagenum==1}">上一页</s:if>
				<s:elseif test="%{pagenum!=1}">
					<a
						href="../Building/System/selSupply.action?pagenum=${pagenum-1}&name=${nameFeature}&good=${goods}">上一页</a>
				</s:elseif>
				<s:form name="form5"
					action="../Building/System/selSupply.action" namespace="/Web"
					method="post" theme="simple" style="display:inline-block;">
				第<s:textfield type="text" name="pagenum" id="textfield"
						cssClass="pagetext" size="2"></s:textfield>
					<input type="hidden" name="cityArea" value="${pageCount}" />
					<input type="hidden" name="name" value="${nameFeature}" />
					<input type="hidden" name="good" value="${goods}" />页 
				</s:form>
				<input onClick="tijiao();" value="提交" type="button"
					class="search-btn"> <span class="c_fl">共 <s:property
						value="pageCount"></s:property>页</span>
				<s:if test="%{pageCount>pagenum}">
					<a
						href="../Building/System/selSupply.action?pagenum=${pagenum+1}&name=${nameFeature}&good=${goods}">
						下一页 </a>
				</s:if>
				<s:elseif test="%{pagenum==pageCount}">
					下一页
				</s:elseif>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function tijiao() {
			var ppt = document.getElementById("textfield").value;
			var znengweishuz = /^[0-9]*$/;
			if (!znengweishuz.test(ppt)) {
				alert("页数只能为数字！");
				return false;
			}
			document.form5.submit();
		}
	</script>
</body>
</html>
