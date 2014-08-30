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
					  <li class="active">初始化</li>
					</ul>
                </div>

                <div class="row-fluid form-wrapper">
                    <!-- left column -->
                    <div class="span9 with-sidebar">
                        <div class="container">
                            <form class="new_user_form inline-input" id="myForm" action="bg/system!systemInitSave" method="post"  />
                            	<input type="hidden" name="sa.id" value="${sa.id }" />
                                <div class="span12 field-box">
                                    <label>模版类型:</label>
                                    <div class="ui-select span5">
                                      <select name="sa.model" onchange="showType(this)" >
											<c:if test="${sa.model == 1 }">
												<option value="1" selected="selected">模版1（展示型）</option>
												<option value="2">模版2（供求型）</option>
												<option value="3">模版3（购物型）</option>
											</c:if>
											<c:if test="${sa.model == 2 }">
												<option value="1" >模版1（展示型）</option>
												<option value="2" selected="selected">模版2（供求型）</option>
												<option value="3" >模版3（购物型）</option>
											</c:if>
											<c:if test="${sa.model == 3 }">
												<option value="1" >模版1（展示型）</option>
												<option value="2" >模版2（供求型）</option>
												<option value="3" selected="selected">模版3（购物型）</option>
											</c:if>
                                      </select>
                                     </div>
                                     <script type="text/javascript">
                                     function showType(t){
                                    	 if($(t).val() == 3){
                                    		 $("#type").show();
                                    	 }else{	
                                    		 $("#type").hide();
                                    	 }
                                     }
                                     </script>
                                </div>
                                <div id="type" style="display: none;">
	                               <div class="span12 field-box">
	                                    <label>商品类型编号:</label>
	                                     <input class="span7"
												type="text" name="sa.type" value="${sa.type}"
												placeholder="购物类型编号，用逗号分割对应类型名称，列如，类型：2，3，4，名称：颜色，尺码，规格（编号只能以2开始）" />
	                                </div>
	                                <div class="span12 field-box">
	                                    <label>商品类型名称:</label>
	                                     <input class="span7"
												type="text"  name="sa.typeName" value="${sa.typeName}"
												placeholder="购物类型名称，用逗号分割对应类型编号，列如，类型：2，3，4，名称：颜色，尺码，规格（编号只能以2开始）" />
	                                </div>
                                </div>
                                <div class="span11 field-box actions">
                                    <input type="button" class="btn-flat primary" value="提 交" onclick="validSubmit(this,'myForm')" />
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
	  	<a id="sure" class="btn-flat primary" data-dismiss="modal" aria-hidden="true" >确定</a>
	  
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
			
			if("${sa.model}" == 3){
				$("#type").show();
			}
		});
     </script>
  </body>
</html>
