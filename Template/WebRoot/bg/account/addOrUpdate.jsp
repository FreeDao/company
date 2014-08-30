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
    <link href="css/lib/bootstrap.datepicker.css" type="text/css" rel="stylesheet" />
    <!-- this page specific styles -->
    <link rel="stylesheet" href="css/compiled/new-user.css" type="text/css" media="screen" />

	 <style type="text/css">
    .showLogo {
    	background: rgba(174, 216, 252, 0.6) ;
    	position:relative;
    	width: 150px;height: 150px;
    	border: 1px solid #eee;
    	border-radius: 5px;
		display: none;
	}
	
	.showLogo img {
		padding: 2px;
		width: 150px;
		height: 150px;
		border-radius: 5px;
	}
    
    .delImg{
    	cursor:pointer;
		width:35px ;
		height: 35px ;
		display: none ;
		background: rgba(56, 156, 240, 0.8) ;
		border-radius:100% ;
		position:absolute;
		left: 37%;
		top:35%;
	}
	
	.delImg i{
		display: block;
		width: 18px;
		height: 22px;
		position: absolute;
		top: 7px;
		left: 8px;
	}
	
	#addImg img{
		border: 2px dashed #dee3e8;
		position: absolute;
		z-index:-1;
		width: 100px;
	}
	
	#addImg input{
		width: 100px;
		height: 100px;
		opacity:0;
		cursor: pointer;
		filter:alpha(opacity=0);
	}
    </style>

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
  </head>
  
  <body style="background-color: white;">
  <form class="new_user_form inline-input" id="myForm" action="bg/system!addOrUpdateAccount" enctype="multipart/form-data" method="post"  >
    <div id="myBody">
    	<div class="container-fluid">
            <div id="pad-wrapper" class="new-user">
                <div class="row-fluid header" style="border-bottom:1px solid #edeef1 ;box-shadow:0px 3px 4px -1px #fafafa;">
                    <ul class="breadcrumb" style="background-color: white;margin-bottom: 0;">
					  <li><a href="welcome.jsp">首页</a> <span class="divider">/</span></li>
					  <li class="active"><a href="${url }" style="cursor: pointer;" >
							用户管理
						</a> <span class="divider">/</span></li>
					  <li class="active">${title }</li>
					</ul>
                </div>
 
                <div class="row-fluid form-wrapper">
                    <!-- left column -->
                    <div class="span9 with-sidebar" >
                        <div class="container">
                           
                            <input name="user.id" value="${user.id }" type="hidden">
                                <div class="span12 field-box">
                                    <label>账户:</label>
                                    <input id="phone" class="span9" type="text" name="user.phone" value="${user.phone }" placeholder="*必填" />
                                    <div class="tooltip fade right in" style="display: none;" id="phoneTip">
										<div class="tooltip-arrow"></div>
										<div class="tooltip-inner" style="max-width: 300px;" id="phoneContent" >请填写帐号</div>
									</div>
                                </div>
                                
                                 <div class="span12 field-box">
                                    <label>密码:</label>
                                    <input id="password" class="span9" type="password" name="user.passWord" value="${user.passWord }" placeholder="*必填" />
                                    <div class="tooltip fade right in" style="display: none;">
										<div class="tooltip-arrow"></div>
										<div class="tooltip-inner" style="max-width: 300px;" id="passwordTip">请填写密码</div>
									</div>
                                </div>
                                
                                <div class="span12 field-box">
                                    <label>昵称:</label>
                                    <input id="nick" class="span9" type="text" name="user.nick" value="${user.nick }" placeholder="选填" />
                                </div>
                                
                                <div class="span12 field-box">
                                    <label>姓名:</label>
                                    <input id="name" class="span9" type="text" name="user.name" value="${user.name }" placeholder="选填" />
                                </div>
                                
                                <div class="span12 field-box">
                                    <label>Email:</label>
                                    <input id="email" class="span9" type="text" name="user.email" value="${user.email }" placeholder="选填" />
                                </div>
                                
								<div class="span12 field-box">
									<label>详细地址:</label> <input id="source" class="span9"
										type="text" name="user.address" value="${user.address }"
										placeholder="选填" />
								</div>
                                
                               	<div class="span11 field-box actions">
                                    <input id="sm" type="button" class="btn-flat primary" value="提 交" onclick="validSubmit(this,'myForm','phone','password')" />
                                    <span>或者</span>
                                    <a class="btn-flat default" href="${url }" >返回</a>
                                </div>
                            
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
   </form>
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
    <script type="text/javascript" src="js/uploadPreview.min.js"></script>
    
    <script type="text/javascript">
		$(function(){
			if('${msg}' != ''){
				$('#tipContent').html('${msg}');
				$('#myModal').modal('show');
			}
			
			$("#phone").blur(function(){
				$.ajax({
					type : "post",
					url : "in/register!selAccount",
					data : {
						"user.phone" : $(this).val(),
						"user.id" : "${user.id}"
					},
					beforeSend : function(XMLHttpRequest) {
						//ShowLoading();
						$("#sm").attr("disabled","disabled");
					},
					success : function(data, textStatus) {
						if(data.isHave){
							$("#phoneContent").html(data.msg);
							$("#phoneTip").css("display","inline-block");
							$("#phone").focus();							
						}else{
							$("#sm").removeAttr("disabled");						
						}
					},
					complete : function(XMLHttpRequest, textStatus) {
						//HideLoading();
					},
					error : function() {
						//请求出错处理
						alert("查询出错");
					}
				});
				
				 if($(this).val() != ""){
					 $(this).next().hide();
					 $("#phoneContent").html("请填写帐号");
				 }
			 });
			
			$("#password").blur(function(){
				 if($(this).val() != ""){
					 $(this).next().hide();
				 }
			 });
			 
		});
		
     </script>
  </body>
</html>
