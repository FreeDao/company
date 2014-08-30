<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addOrUpdate.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    
    <%@include file="../../common.jsp" %>
    
    <!-- this page specific styles -->
    <link rel="stylesheet" href="css/compiled/new-user.css" type="text/css" media="screen" />


    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
  </head>
  
  <body style="background-color: white;">
    <div id="myBody">
    	<div class="container-fluid">
            <div id="pad-wrapper" class="new-user">
                <div class="row-fluid header" style="border-bottom:1px solid #edeef1 ;box-shadow:0px 3px 4px -1px #fafafa;">
                    <ul class="breadcrumb" style="background-color: white;margin-bottom: 0;">
					  <li><a href="welcome.jsp">首页</a> <span class="divider">/</span></li>
					  <li class="active"><a href="${url }" style="cursor: pointer;" >类型管理</a> <span class="divider">/</span></li>
					  <li class="active">${title }</li>
					</ul>
                </div>

                <div class="row-fluid form-wrapper">
                    <!-- left column -->
                    <div class="span9 with-sidebar">
                        <div class="container">
                            <form class="new_user_form inline-input" id="myForm" action="bg/system!addOrUpdateType" method="post"  />
                            <input name="type.id" value="${type.id }" type="hidden">
                                <div class="span12 field-box">
                                    <label>类型名称:</label>
                                    <input id="name" class="span9" type="text" name="type.name" value="${type.name }" placeholder="*必填" />
                                 	<div class="tooltip fade right in" style="display: none;">
										<div class="tooltip-arrow"></div>
										<div class="tooltip-inner" style="max-width: 300px;">请填写类型名称</div>
									</div>
                                </div>
                                
                                <div class="span12 field-box"  >
                                    <label>列表类型:</label>
                                    <div class="ui-select span5">
                                        <select name="type.type" onchange="changeList(this)" >
                                        	<c:forEach items="${list }" var="list">
	                                        		<c:if test="${type.type == list.type }">
	                                        			<option value="${list.type }" selected="selected" >${list.name }</option>
	                                        		</c:if>
	                                        		<c:if test="${type.type != list.type }">
	                                        			<option value="${list.type }" >${list.name }</option>
	                                        		</c:if>
                                        	</c:forEach>
                                        </select>
                                         <script type="text/javascript">
			                                function changeList(t){
			                                	if($(t).val() != 1){
			                                		$("#category").hide();                                		
			                                	}else{
			                                		$("#category").show();
			                                	}
			                                }
			                             </script>
                                    </div>
                                </div>
                                
                                <div id="category"  >
	                                <div class="span12 field-box">
	                                    <label>类型层级:</label>
	                                    <div class="ui-select span5">
	                                        <select name="type.level" onchange="changeLevel(this)" >
	                                        	<c:forEach begin="1" end="5" step="1" var="i">
	                                        		<c:choose>
														<c:when test="${type.level == i }"><option value="${i }" selected="selected" >${i } </option></c:when>       
														<c:otherwise><option value="${i }" >${i } </option></c:otherwise>                                 		
	                                        		</c:choose>
	                                        	</c:forEach>
	                                        </select>
	                                         <script type="text/javascript">
				                                function changeLevel(t){
				                                	if($(t).val() != 1){
				                                		$("#pid").show();                               		
				                                	}else{
				                                		$("#pid").hide();
				                                	}
				                                }
				                                </script>
	                                    </div>
	                                </div>
	
	                                <div id="pid" class="span12 field-box" style="display: none;">
	                                    <label>上级菜单:</label>
	                                    <div class="ui-select span5">
	                                        <select name="type.pid">
	                                        	<option value="0" >无</option>
		                                        <c:forEach items="${types }" var="t">
		                                        	<c:choose>
		                                        		<c:when test="${type.pid == t.id }"><option selected="selected" value="${t.id }" >${t.name }</option></c:when>
		                                        		<c:otherwise>
		                                        		<option value="${t.id }" >${t.name }</option>
		                                        		</c:otherwise>
		                                        	</c:choose>
		                                        </c:forEach>
	                                        </select>
	                                    </div>
	                                </div>
                                </div>
                                <div class="span11 field-box actions">
                                    <input type="button" class="btn-flat primary" value="提 交" onclick="validSubmit(this,'myForm','name')" />
                                    <span>或者</span>
                                    <a class="btn-flat default" href="${url }" >返回</a>
                                </div>
                            </form>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
    
    <!-- Modal -->
	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="myModalLabel">提示信息</h3>
	  </div>
	  <div class="modal-body">
	    <p id="tipContent"></p>
	  </div>
	  <div class="modal-footer">
	  	<a class="btn-flat white" href="${url }"><i class="icon-reply" ></i>返回</a>
	  	<a id="sure" class="btn-flat primary" data-dismiss="modal" aria-hidden="true" >继续<i class="icon-share-alt" ></i></a>
	  
	    <%--<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
	    <button class="btn btn-primary">确定</button>
	  --%></div>
	</div>
          
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    
    <script type="text/javascript">
		$(function(){
			if('${msg}' != ''){
				$('#tipContent').html('${msg}');
				$('#myModal').modal('show');
			}

			$("#name").blur(function(){
				 if($(this).val() != ""){
					 $(this).next().hide();
				 }
			 });
			
			if('${type.type}' != 1 && '${type.type}' != ''){
				$("#category").hide();
			}
			
			if('${type.pid}' != 0){
				$("#pid").show();
			}
		});
     </script>
  </body>
</html>
