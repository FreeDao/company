<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<%@include file="../../common.jsp" %>
    <link href="${pageContext.request.contextPath}/css/pages/faq.css" rel="stylesheet" /> 
    
  </head>
  <body>

<h1 class="page-title">&nbsp; 
					求职信息管理					 
				</h1>
				
				<div class="widget widget-table">
					
					<div style="margin-bottom: 5px;">
						<a data-trigger="modal" href="bg/job!goAddOrEdit" data-title="添加求职信息" class="btn btn-primary"><i class="icon-plus" ></i>添加</a>
					</div>
					
					<div class="widget-header">
						<h3>列表信息</h3>
					</div> <!-- /widget-header -->
														
					<div class="widget-content">
						
								<table class="table table-bordered table-hover">
									<thead>
										<tr>
											<th>姓名	</th>
											<th>性别</th>
											<th>年龄</th>
											<th>专业</th>
											<th>学历</th>
											<th>工作年限</th>
											<th>毕业学校</th>
											<th>现有职称</th>
											<th>联系电话</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${pu.data }" var="pd">
										<tr>
											<td>${pd.name }</td>
											<td>${pd.sex }</td>
											<td>${pd.age }</td>
											<td>${pd.major }</td>
											<td>${pd.degree }</td>
											<td>${pd.workTime }</td> 
											<td>${pd.school }</td>
											<td>${pd.profession }</td>
											<td>${pd.tel }</td>
											<td>
											<a class="btn" data-trigger="modal" href="bg/job!goAddOrEdit?job.id=${pd.id }" data-title="修改求职信息">
											<i class="icon-pencil"></i>
											</a>
											<a data-trigger="confirm" href="bg/job!delJob?job.id=${pd.id}" class="btn">
											<i class="icon-remove" style="cursor: pointer;"></i>
											</a>
											</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>	
						
					</div> <!-- /widget-content -->
					<ul class="pager">
					<c:choose>
						<c:when test="${pu.isFrist }">首页&nbsp;</c:when>
						<c:otherwise>
							<li><a href="${pu.url }?pageNow=1">首页</a></li>&nbsp;</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${pu.hasPre }">
							<li><a href="${pu.url }?pageNow=${pu.pageNow - 1}">上一页</a></li>&nbsp;</c:when>
						<c:otherwise>上一页&nbsp;</c:otherwise>
					</c:choose> 
					<c:forEach items="${pu.navigatePages }" var="pnp">
						<c:choose>
							<c:when test="${pu.pageNow == pnp }">
								<c:out value="${pnp }"></c:out>&nbsp;</c:when>
							<c:otherwise>
								<li><a href="${pu.url }?pageNow=${pnp}">
								<c:out	value="${pnp }"></c:out></a></li>&nbsp;</c:otherwise>
						</c:choose>

					</c:forEach>
					 <c:choose>
						<c:when test="${pu.hasNext }">
							<li><a href="${pu.url }?pageNow=${pu.pageNow + 1}">下一页</a></li>&nbsp;</c:when>
						<c:otherwise>下一页&nbsp;</c:otherwise>
					</c:choose>
					 <c:choose>
						<c:when test="${pu.isLast }">末页&nbsp;</c:when>
						<c:otherwise>
							<li><a href="${pu.url }?pageNow=${pu.pageTotal}">末页</a></li>&nbsp;</c:otherwise>
					</c:choose> 总共${pu.total }条记录
					</ul>
				</div> <!-- /widget -->
<script type="text/javascript">
$(window).load(function() {
           if('${msg}' != null && "" != '${msg}'){
           		if('${msg}' == 1){
           			$.scojs_message("操作成功", $.scojs_message.TYPE_OK);
           		}else{
           			$.scojs_message("操作失败", $.scojs_message.TYPE_ERROR);
           		}
           }
        });
        
</script>		
  </body>
</html>
