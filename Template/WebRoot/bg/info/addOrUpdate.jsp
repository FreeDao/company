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
  <form class="new_user_form inline-input" id="myForm" action="bg/info!addOrUpdateInfo" enctype="multipart/form-data" method="post"  >
    <div id="myBody">
    	<div class="container-fluid">
            <div id="pad-wrapper" class="new-user">
                <div class="row-fluid header" style="border-bottom:1px solid #edeef1 ;box-shadow:0px 3px 4px -1px #fafafa;">
                    <ul class="breadcrumb" style="background-color: white;margin-bottom: 0;">
					  <li><a href="welcome.jsp">首页</a> <span class="divider">/</span></li>
					  <li class="active"><a href="${url }" style="cursor: pointer;" >
							<c:forEach items="${tree}" var="tree">
								<c:if test="${ tree.id == menuId  }">${tree.name }</c:if> 	  
							  </c:forEach>
						</a> <span class="divider">/</span></li>
					  <li class="active">${title }</li>
					</ul>
                </div>
 
                <div class="row-fluid form-wrapper">
                    <!-- left column -->
                    <div class="span9 with-sidebar" >
                        <div class="container">
                           
                            <input name="info.id" value="${info.id }" type="hidden">
                                <div class="span12 field-box">
                                    <label>标题:</label>
                                    <input id="title" class="span9" type="text" name="info.title" value="${info.title }" placeholder="*必填" />
                                    <div class="tooltip fade right in" style="display: none;">
										<div class="tooltip-arrow"></div>
										<div class="tooltip-inner" style="max-width: 300px;">请填写到标题</div>
									</div>
                                </div>
                                
                                 <div class="span12 field-box">
                                    <label>来源:</label>
                                    <input id="source" class="span9" type="text" name="info.source" value="${info.source }" placeholder="选填" />
                                </div>
                                
                                <div class="span12 field-box">
									<label>省份:</label>
									<div class="ui-select span2">
										<select id="province" onchange="changeRegion(this,1)" name="info.provinceId">
											<option value="0">请选择</option>
										</select>
									</div>
									<label class="subLabel">城市:</label>
									<div class="ui-select span2">
										<select name="info.cityId" id="city"
											onchange="changeRegion(this,2)">
											<option value="0">请选择</option>
										</select>
									</div>
									<label class="subLabel">区域:</label>
									<div class="ui-select span2">
										<select name="info.areaId" id="area">
											<option value="0">请选择</option>
										</select>
									</div>
									<script type="text/javascript">
										function changeRegion(t, level) {
											if (level == 1) {
												setCity($(t).val());
											} else {
												setArea($(t).val());
											}
										}
									</script>
								</div>
								<div class="span12 field-box">
									<label>详细地址:</label> <input id="source" class="span9"
										type="text" name="info.address" value="${info.address }"
										placeholder="选填" />
								</div>

								<div class="span12 field-box">
									<label>一级类别:</label>
									<div class="ui-select span3">
										<select name="info.typeId" id="type"
											onchange="setSubType(this.value)">
											<option value="0">无</option>
										</select>
									</div>
									<label class="subLabel" style="margin-left: 50px;">二级类别:</label>
									<div class="ui-select span3">
										<select name="info.subTypeId" id="subType">
											<option value="0">无</option>
										</select>
									</div>
								</div>
                                
                                 <div class="span12 field-box">
                                    <label>时间:</label>
                                    <input id="time" name="info.time"  type="text" value="${info.time }" class="input-small datepicker span2"/>
                                 </div>
                                
                                 <div class="span12 field-box">
                                    <label>是否置顶:</label>
                                    <div class="slider-frame primary">
		                               <span data-on-text="是" data-off-text="否" class="slider-button">否</span>
		                               <input id="isTop" type="hidden" name="info.isTop" value="${info.isTop}" />
		                           </div>
                                </div>
                                
                                <div class="span12 field-box">
                                    <label >logo:</label>
                                    
                                    <div id="showLogo" class="showLogo">
                                    <span class="delImg" onclick="delImg()">
	                                    <i class="gallery-trash"></i>
	                                </span>
	                                <c:if test="${info.logo != '' && info.logo != null }">
	                                	<img src="${info.logo}" />
	                                </c:if>
                                    </div>
			                        <div id="addImg" style="display: inline-block;" >
			                        <img  src="img/new-gallery-img.png"  >
			                        <input id="type" type="file" name="img" onchange="uploadImg(this,'addImg')" />
			                        <script type="text/javascript">
			                        function uploadImg(file){
			                        	if(file.value == ""){
			                        		return;
			                        	}
			                        	$("#showLogo").css("display","inline-block");
			                        	if (file.files && file.files[0]) {
			                        		var reader = new FileReader();  
				                            reader.onload = function(evt){
				                            	$("#showLogo").append("<img src='"+evt.target.result+"' />");
				                            	}; 
				                            reader.readAsDataURL(file.files[0]);
			                        	}else{
			                        		file.select();
			                        		$("#showLogo").focus();
			                        		var src = document.selection.createRange().text;  
			                        		$("#showLogo").css("filter","progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src='"+src+"')");
			                        	}
			                        	$("#addImg").hide();
			                        }
			                        
			                        function delImg(){
			                        	$("#showLogo").hide("slow");
			                        	setTimeout(function(){
			                        		$("#showLogo img").remove();
			                        		$("#addImg").show();
			                        	}, 300);
			                        	$("#type").val("");
			                        }
			                        
			                        </script>
			                        
			                        </div>
                                </div>
                                
                                <div class="span12 field-box textarea">
                                    <label>摘要:</label>
                                    <textarea id="digest" class="span9" name="info.digest" placeholder="选填">${info.digest }</textarea>
                                </div>
                                
                                <div class="span12 field-box textarea">
                                    <label>内容:</label>
                                    <textarea id="content" class="span9" name="info.content" placeholder="*必填">${info.content }</textarea>
                                    <div class="tooltip fade right in" style="display: none;">
										<div class="tooltip-arrow"></div>
										<div class="tooltip-inner" style="max-width: 300px;">请填写正文</div>
									</div>
                                    <%--<span class="charactersleft">文章内容</span>--%>
                                </div>
                                
                               	<div class="span11 field-box actions">
                                    <input type="button" class="btn-flat primary" value="提 交" onclick="validSubmit(this,'myForm','title','content')" />
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
    <script type="text/javascript" src="js/bootstrap.datepicker.js" ></script>
	<script type="text/javascript" src="js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
    
    <script type="text/javascript">
		$(function(){
			if('${msg}' != ''){
				$('#tipContent').html('${msg}');
				$('#myModal').modal('show');
			}
			
			$('.datepicker').datepicker({
			 	language:'zh-CN',
			    format: 'yyyy-mm-dd'
		 	}).on('changeDate', function (ev) {
                $(this).datepicker('hide');
            });
			
			$("#title").blur(function(){
				 if($(this).val() != ""){
					 $(this).next().hide();
				 }
			 });
			
			$("#content").blur(function(){
				 if($(this).val() != ""){
					 $(this).next().hide();
				 }
			 });
			
			if($("#isTop").val() == "" || $("#isTop").val() != 1 ){
				$("#isTop").val(2);
			}else{
				$(".slider-button").addClass('on').html($(".slider-button").data("on-text"));
			}
			
			 // Switch slide buttons
            $('.slider-button').click(function() {
                if ($(this).hasClass("on")) {
                	$("#isTop").val(2);                	
                    $(this).removeClass('on').html($(this).data("off-text"));   
                } else {
                	$("#isTop").val(1);
                    $(this).addClass('on').html($(this).data("on-text"));
                }
            });
			 
			 if('${info.logo}' != '' && '${info.logo}' != null ){
				 $("#showLogo").css("display","inline-block");
				 $("#addImg").hide();
			 }
			 
			 $("#showLogo").hover(
				function(){
					$(".delImg").show();
				},
				function(){
					$(".delImg").hide();
				}
			 );
				 
			setProvince(0, 1);
			setType(1);
			 
		});
		
		
		function setProvince(pid, init) {
			$
					.ajax({
						type : "post",
						url : "in/general!region",
						data : {
							"region.level" : "1",
							"region.pid" : pid
						},
						beforeSend : function(XMLHttpRequest) {
							//ShowLoading();
						},
						success : function(data, textStatus) {
							$("#province").html("");
							for ( var index in data.lists) {
								$("#province").append(
										"<option value='"+data.lists[index].id+"'>"
												+ data.lists[index].name
												+ "</option>");
							}
						},
						complete : function(XMLHttpRequest, textStatus) {
							//HideLoading();
							if (init == 1 && '${info.provinceId}' != '' && '${info.provinceId}' != 0) {
								$("#province").val(
										'${info.provinceId}');
							}
							setCity($("#province").val(), init);
						},
						error : function() {
							//请求出错处理
							alert("获省份出错");
						}
					});
		}

		function setCity(pid, init) {
			$
					.ajax({
						type : "post",
						url : "in/general!region",
						data : {
							"region.level" : "2",
							"region.pid" : pid
						},
						beforeSend : function(XMLHttpRequest) {
							//ShowLoading();
						},
						success : function(data, textStatus) {
							$("#city").html("");
							for ( var index in data.lists) {
								$("#city").append(
										"<option value='"+data.lists[index].id+"'>"
												+ data.lists[index].name
												+ "</option>");
							}
						},
						complete : function(XMLHttpRequest, textStatus) {
							//HideLoading();
							if (init == 1 && '${info.cityId}' != 0 && '${info.cityId}' != '') {
								$("#city").val('${info.cityId}');
							}
							setArea($("#city").val(), init);
						},
						error : function() {
							//请求出错处理
							alert("获取城市出错");
						}
					});
		}

		function setArea(pid, init) {
			$
					.ajax({
						type : "post",
						url : "in/general!region",
						data : {
							"region.level" : "3",
							"region.pid" : pid
						},
						beforeSend : function(XMLHttpRequest) {
							//ShowLoading();
						},
						success : function(data, textStatus) {
							$("#area").html("");
							for ( var index in data.lists) {
								$("#area").append(
										"<option value='"+data.lists[index].id+"'>"
												+ data.lists[index].name
												+ "</option>");
							}
						},
						complete : function(XMLHttpRequest, textStatus) {
							//HideLoading();
							if (init == 1 && '${info.areaId}' != '' && '${info.areaId}' != 0) {
								$("#area").val('${info.areaId}');
							}
						},
						error : function() {
							//请求出错处理
							alert("获取区域出错");
						}
					});
		}

		function setType(init) {
			$
					.ajax({
						type : "post",
						url : "in/general!type",
						data : {
							"type.level" : 1,
							"type.type" : 1
						},
						beforeSend : function(XMLHttpRequest) {
							//ShowLoading();
						},
						success : function(data, textStatus) {
							for ( var index in data.lists) {
								$("#type").append(
										"<option value='"+data.lists[index].id+"'>"
												+ data.lists[index].name
												+ "</option>");
							}
						},
						complete : function(XMLHttpRequest, textStatus) {
							//HideLoading();
							if (init == 1 && '${info.typeId}' != '' && '${info.typeId}' != 0) {
								$("#type").val('${info.typeId}');
								setSubType('${info.typeId}', init);
							}
						},
						error : function() {
							//请求出错处理
							alert("获取一级类别出错");
						}
					});
		}

		function setSubType(pid, init) {
			if (pid == 0) {
				$("#subType").html("<option value='0'>无</option>");
				return;
			}
			$
					.ajax({
						type : "post",
						url : "in/general!type",
						data : {
							"type.level" : 2,
							"type.type" : 1,
							"type.pid" : pid
						},
						beforeSend : function(XMLHttpRequest) {
							//ShowLoading();
						},
						success : function(data, textStatus) {
							$("#subType").html("<option value='0'>无</option>");
							for ( var index in data.lists) {
								$("#subType").append(
										"<option value='"+data.lists[index].id+"'>"
												+ data.lists[index].name
												+ "</option>");
							}
						},
						complete : function(XMLHttpRequest, textStatus) {
							//HideLoading();
							if (init == 1 && '${info.subTypeId}' != '' && '${info.subTypeId}' != 0) {
								$("#subType").val('${info.subTypeId}');
							}
						},
						error : function() {
							//请求出错处理
							alert("获取一级类别出错");
						}
					});
		}
		
     </script>
  </body>
</html>
