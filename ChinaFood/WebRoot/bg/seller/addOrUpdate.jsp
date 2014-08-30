<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<%@include file="../../common.jsp"%>

<!-- this page specific styles -->
<link rel="stylesheet" href="css/compiled/new-user.css" type="text/css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="css/uploadify.css">

<style type="text/css">
.showLogo {
	background: rgba(174, 216, 252, 0.6);
	position: relative;
	width: 150px;
	height: 150px;
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

.delImg {
	cursor: pointer;
	width: 35px;
	height: 35px;
	display: none;
	background: rgba(56, 156, 240, 0.8);
	border-radius: 100%;
	position: absolute;
	left: 37%;
	top: 35%;
}

.delImg i {
	display: block;
	width: 18px;
	height: 22px;
	position: absolute;
	top: 7px;
	left: 8px;
}

#addImg img {
	border: 2px dashed #dee3e8;
	position: absolute;
	z-index: -1;
	width: 100px;
}

#addImg input {
	width: 100px;
	height: 100px;
	opacity: 0;
	cursor: pointer;
	filter: alpha(opacity = 0);
}

.subLabel {
	margin-left: 50px;
}

.tableTag {
	border-radius: 0 30px 30px 0;
	-moz-border-radius: 0 30px 30px 0;
	-webkit-border-radius: 0 30px 30px 0;
	-o-border-radius: 0 30px 30px 0;
	border: 1px solid #edeef1;
	border-left: 1px solid #fff;
	width: 130px;
	height: 30px;
	line-height: 30px;
	margin: 2px 0;
	text-align: center;
	cursor: pointer;
}

.tableTag:hover {
	font-weight: bold;
	color: rgba(91, 158, 214, 0.9);
}

.mySelected {
	font-weight: normal !important;
	background-color: rgba(91, 158, 214, 0.9);
	color: white !important;
	-moz-box-shadow: 2px 2px 3px #DADADA;
	box-shadow: 2px 2px 3px #DADADA;
}

.myHide {
	display: none;
}

.myShow {
	display: block;
}
</style>

<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>

<body style="background-color: white;">
	<form class="new_user_form inline-input" id="myForm"
		action="bg/seller!addOrUpdateSeller" enctype="multipart/form-data"
		method="post">
		<div id="myBody">
			<div class="container-fluid">
				<div id="pad-wrapper" class="new-user">
					<div class="row-fluid header"
						style="border-bottom:1px solid #edeef1 ;box-shadow:0px 3px 4px -1px #fafafa;">
						<ul class="breadcrumb"
							style="background-color: white;margin-bottom: 0;">
							<li><a href="welcome.jsp">首页</a> <span class="divider">/</span>
							</li>
							<li class="active"><a href="${url }"
								style="cursor: pointer;"> <c:forEach items="${tree}"
										var="tree">
										<c:if test="${ tree.id == menuId  }">${tree.name }</c:if>
									</c:forEach> </a> <span class="divider">/</span>
							</li>
							<li class="active">${title }</li>
						</ul>
					</div>

					<div class="row-fluid form-wrapper">
						<!-- left column -->
						<div class="span9 with-sidebar">
							<div class="container">
								<input name="seller.id" value="${seller.id }" type="hidden">
								<div id="info" class="myTag">
									<div class="span12 field-box">
										<label>名称:</label> <input id="name" class="span9" type="text"
											name="seller.name" value="${seller.name }" placeholder="*必填" />
											<div class="tooltip fade right in" style="display: none;">
												<div class="tooltip-arrow"></div>
												<div class="tooltip-inner" style="max-width: 300px;">请填写名称</div>
											</div>
										<div class="tooltip fade right in" style="display: none;">
											<div class="tooltip-arrow"></div>
											<div class="tooltip-inner" style="max-width: 300px;">请填写名称</div>
										</div>
									</div>

									<div class="span12 field-box">
										<label>联系人:</label> <input id="source" class="span9"
											type="text" name="seller.contact" value="${seller.contact }"
											placeholder="选填" />
									</div>

									<div class="span12 field-box">
										<label>联系电话:</label> <input id="source" class="span9"
											type="text" name="seller.tel" value="${seller.tel }"
											placeholder="选填" />
									</div>

									<div class="span12 field-box">
										<label>邮箱:</label> <input id="source" class="span9"
											type="text" name="seller.email" value="${seller.email }"
											placeholder="选填" />
									</div>
									
									<div class="span12 field-box">
										<label>商家价格/优惠:</label>
										<div class="span2" style="margin-left: 0px;">
										 <input id="preference" class="span12" onkeyup="validNumber(this,false)"
											type="text" name="seller.preference" value="${seller.preference }"
											placeholder="选填,必须为数字可以有小数" />
										</div>
										<div class="ui-select span1">
											<select name="seller.unit" id="unit">
												<c:choose>
													<c:when test="${seller.unit == '元' }">
														<option value="元" selected="selected">元</option>
														<option value="%">%</option>
													</c:when>
													<c:when test="${seller.unit == '%' }">
														<option value="元" >元</option>
														<option value="%" selected="selected">%</option>
													</c:when>
													<c:otherwise>
														<option value="元">元</option>
														<option value="%">%</option>
													</c:otherwise>
												</c:choose>
											</select>
										</div>
									</div>
									
									<div class="span12 field-box">
										<label>产品原价格:</label> <input id="preference" class="span9" onkeyup="validNumber(this,false)"
											type="text" name="seller.productPrice" value="${seller.productPrice }"
											placeholder="选填,必须为数字可以有小数" />
									</div>
									
									<div class="span12 field-box">
										<label>产品现价/优惠:</label>
										<div class="span2" style="margin-left: 0px;" >
											<input id="preference" class="span12" onkeyup="validNumber(this,false)"
											type="text" name="seller.productPreference" value="${seller.productPreference }"
											placeholder="选填,必须为数字可以有小数" />
										</div>
										<div class="ui-select span1">
											<select name="seller.productUnit" id="productUnit">
												<c:choose>
													<c:when test="${seller.productUnit == '元' }">
														<option value="元" selected="selected">元</option>
														<option value="%">%</option>
													</c:when>
													<c:when test="${seller.productUnit == '%' }">
														<option value="元" >元</option>
														<option value="%" selected="selected">%</option>
													</c:when>
													<c:otherwise>
														<option value="元">元</option>
														<option value="%">%</option>
													</c:otherwise>
												</c:choose>
											</select>
										</div>
									</div>

									<div class="span12 field-box">
										<label>省份:</label>
										<div class="ui-select span2">
											<select id="province" onchange="changeRegion(this,1)" name="seller.provinceId">
												<option value="0">请选择</option>
											</select>
										</div>
										<label class="subLabel">城市:</label>
										<div class="ui-select span2">
											<select name="seller.cityId" id="city"
												onchange="changeRegion(this,2)">
												<option value="0">请选择</option>
											</select>
										</div>
										<label class="subLabel">区域:</label>
										<div class="ui-select span2">
											<select name="seller.areaId" id="area">
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
											type="text" name="seller.address" value="${seller.address }"
											placeholder="选填" />
									</div>

									<div class="span12 field-box">
										<label>一级类别:</label>
										<div class="ui-select span3">
											<select name="seller.typeId" id="type"
												onchange="setSubType(this.value)">
												<option value="0">无</option>
											</select>
										</div>
										<label class="subLabel">二级类别:</label>
										<div class="ui-select span3">
											<select name="seller.subTypeId" id="subType">
												<option value="0">无</option>
											</select>
										</div>
									</div>

									<c:if test="${systemAgrs.model == 2 }">
										<div class="span12 field-box">
											<label>商铺帐号:</label>
											<div class="ui-select span3">
												<select id="userId" name="seller.userId">
													<c:forEach items="${users }" var="u">
														<c:if test="${u.id == seller.userId }">
															<option value="${u.id }" selected="selected">${u.nick
																}(${u.phone })</option>
														</c:if>
														<c:if test="${u.id != seller.userId }">
															<option value="${u.id }">${u.nick }(${u.phone
																})</option>
														</c:if>
													</c:forEach>
												</select>
											</div>
											<div class="tooltip fade right in" style="display: none;">
												<div class="tooltip-arrow"></div>
												<div class="tooltip-inner" style="max-width: 300px;">请先创建商铺</div>
											</div>
										</div>
									</c:if>

									<div class="span12 field-box">
										<label>是否推荐:</label>
										<div class="slider-frame primary">
											<span id="categoryButton" data-on-text="是" data-off-text="否"
												class="slider-button">否</span> <input id="category"
												type="hidden" name="seller.category"
												value="${seller.category}" />
										</div>
									</div>
									<div class="span12 field-box">
										<label>是否置顶:</label>
										<div class="slider-frame primary">
											<span id="isTopButton" data-on-text="是" data-off-text="否"
												class="slider-button">否</span> <input id="isTop"
												type="hidden" name="seller.isTop" value="${seller.isTop}" />
										</div>
									</div>

									<div class="span12 field-box">
										<label>logo:</label>

										<div id="showLogo" class="showLogo">
											<span class="delImg" onclick="delImg()"> <i
												class="gallery-trash"></i> </span>
											<c:if test="${seller.logo != '' && seller.logo != null }">
												<img src="${seller.logo}" />
											</c:if>
										</div>
										<div id="addImg" style="display: inline-block;">
											<img src="img/new-gallery-img.png"> <input id="type"
												type="file" name="img" onchange="myUploadImg(this,'addImg')" />
											<script type="text/javascript">
												function myUploadImg(file) {
													if (file.value == "") {
														return;
													}
													$("#showLogo").css(
															"display",
															"inline-block");
													if (file.files
															&& file.files[0]) {
														var reader = new FileReader();
														reader.onload = function(
																evt) {
															$("#showLogo")
																	.append(
																			"<img src='"+evt.target.result+"' />");
														};
														reader
																.readAsDataURL(file.files[0]);
													} else {
														file.select();
														$("#showLogo").focus();
														var src = document.selection
																.createRange().text;
														$("#showLogo").css(
																"filter",
																"progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src='"
																		+ src
																		+ "')");
													}
													$("#addImg").hide();
												}

												function delImg() {
													$("#showLogo").hide("slow");
													setTimeout(function() {
														$("#showLogo img")
																.remove();
														$("#addImg").show();
													}, 300);
													$("#type").val("");
												}
											</script>

										</div>
									</div>

									<div class="span12 field-box textarea">
										<label>产品介绍:</label>
										<textarea id="productBrief" class="span9"
											name="seller.productBrief" placeholder="选填">${seller.productBrief }</textarea>
									</div>

									<div class="span12 field-box textarea">
										<label>商家介绍:</label>
										<textarea id="sellerBrief" class="span9"
											name="seller.sellerBrief" placeholder="选填">${seller.sellerBrief }</textarea>
										<%--<span class="charactersleft">文章内容</span>--%>
									</div>
								</div>
								<div id="recruit" class="myTag myHide">
									<div class="span12 field-box">
										<label>职位:</label> <input id="job" class="span9" type="text"
											name="seller.job" value="${seller.job }" placeholder="选填" />
									</div>
									<div class="span12 field-box">
										<label>学历:</label> <input id="education" class="span9"
											type="text" name="seller.education"
											value="${seller.education }" placeholder="选填" />
									</div>
									<div class="span12 field-box">
										<label>经验:</label> <input id="experience" class="span9"
											type="text" name="seller.experience"
											value="${seller.experience }" placeholder="选填" />
									</div>
									<div class="span12 field-box">
										<label>待遇:</label> <input id="salary" class="span9"
											type="text" name="seller.salary" value="${seller.salary }"
											placeholder="选填" />
									</div>
								</div>


								<div class="span11 field-box actions">
									<input type="button" class="btn-flat primary" value="提 交"
										onclick="myValid(this)" /> <span>或者</span> <a
										class="btn-flat default" href="${url }">返回</a>
									<script type="text/javascript">
										function myValid(t) {
											if( '${systemAgrs.model}' == 2 &&  ($("#userId").val() == null || $("#userId").val() == "")){
												$("#userId").parent().next().css("display","inline-block");
												return;												
											}
											
											$(".mySelected").removeClass(
													"mySelected");
											$("#infoTag")
													.addClass("mySelected");
											$(".myTag").addClass("myHide");
											$("#info").removeClass("myHide");
											validSubmit(t, 'myForm', 'name');
										}
									</script>
								</div>
							</div>
						</div>

						<!-- side right column -->
						<div class="span3 form-sidebar" style="margin-left: 0px;">
							<%--<div class="btn-group toggle-inputs hidden-tablet">
                            <button class="glow left active" data-input="inline">INLINE INPUTS</button>
                            <button class="glow right" data-input="normal">NORMAL INPUTS</button>
                        </div>
                        --%>
							<div id="infoTag" class="tableTag mySelected"
								onclick="showTag(this,'info')">基本信息</div>
							<div class="tableTag" onclick="showTag(this,'recruit')">招聘信息</div>
							<script type="text/javascript">
								function showTag(t, id) {
									$(".mySelected").removeClass("mySelected");
									$(t).addClass("mySelected");
									$(".myTag").addClass("myHide");
									$("#" + id).removeClass("myHide");
								}
							</script>
						</div>

						<div class="span3" style="margin-top: 50px;">
							<h3>内容图片上传</h3>
							<div id="queue">
								<c:forEach items="${imgs }" var="img">
									<div id="sellerImg_${img.id }" class="uploadify-queue-item">
										<div class="cancel">
											<a href="javascript:myCancel('SWFUpload_0_0','${img.id }')">X</a>
										</div>
										<div>
											<div
												style="width:20%;display:inline-block;height:70px;margin:10px 5px 0 0;">
												<img
													src="${img.url }"
													style="width:100%;height:70px;border:1px solid #dff0fd;border-radius: 5px;">
											</div>
											<div class="myText"
												style="width: 70%; display: inline-block; margin: 10px 0px 0px;">
												${img.brief }
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
							<input id="file_add" name="file" type="file" multiple="true">
							<button id="file_upload" class="upload" type="button"
								onclick="uploadImg()">上传</button>
							<script type="text/javascript">
								function uploadImg() {
									$("#file_add").uploadify('upload', '*');
								}

								function myCancel(fileId,imgId) {

									$.ajax({
										type : "post",
										url : "in/img!delTemp",
										data : {
											"fileId" : fileId,
											"imgId":imgId
										},
										beforeSend : function(XMLHttpRequest) {
											//ShowLoading();
										},
										success : function(data, textStatus) {
											if(data.code == 1){
												$("#file_add").uploadify("cancel",
														fileId);	
											}else if(data.code == 2){
												$("#file_add").uploadify("cancel",
														"sellerImg_"+imgId);	
											}
										},
										complete : function(XMLHttpRequest,
												textStatus) {
											//HideLoading();
										},
										error : function() {
											//请求出错处理
											alert("删除临时图片出错");
										}
									});

									//$("#file_add").uploadify("cancel", fileId);
								}
							</script>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- Modal -->
	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="myModalLabel">提示信息</h3>
		</div>
		<div class="modal-body">
			<p id="tipContent"></p>
		</div>
		<div class="modal-footer">
			<a class="btn-flat white" href="${url }"><i class="icon-reply"></i>返回</a>
			<a id="sure" class="btn-flat primary" data-dismiss="modal"
				aria-hidden="true">继续<i class="icon-share-alt"></i>
			</a>

			<%--<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
	    <button class="btn btn-primary">确定</button>
	  --%>
		</div>
	</div>

	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/uploadPreview.min.js"></script>
	<script src="js/uploadify/jquery.uploadify.js" type="text/javascript"></script>


	<script type="text/javascript">
		$(function() {

			$("#name").blur(function(){
				 if($(this).val() != ""){
					 $(this).next().hide();
				 }
			 });
			
			$("#file_add")
					.uploadify(
							{
								//开启调试
								'debug' : false,
								//是否自动上传
								'auto' : false,
								//超时时间
								'successTimeout' : 99999,
								//附带值
								/*'formData':{
								    'userid':'用户id',
								    'username':'用户名',
								    'rnd':'加密密文'
								},*/
								//flash
								'swf' : "js/uploadify/uploadify.swf",
								//不执行默认的onSelect事件
								'overrideEvents' : [ 'onDialogClose' ],
								//文件选择后的容器ID
								'queueID' : 'queue',
								//服务器端脚本使用的文件对象的名称 $_FILES个['upload']
								'fileObjName' : 'file',
								//上传处理程序
								'uploader' : '../in/img!upload;jsessionid=${pageContext.session.id}',
								//浏览按钮的背景图片路径
								//'buttonImage':'img/gallery2.jpg',
								'buttonText' : '添加图片',
								'removeCompleted' : false,
								//浏览按钮的宽度
								'width' : '100',
								//浏览按钮的高度
								'height' : '32',
								//expressInstall.swf文件的路径。
								//'expressInstall':'expressInstall.swf',
								//在浏览窗口底部的文件类型下拉菜单中显示的文本
								'fileTypeDesc' : '支持的格式：',
								//允许上传的文件后缀
								'fileTypeExts' : '*.jpg;*.jpge;*.gif;*.png',
								//上传文件的大小限制
								'fileSizeLimit' : '5MB',
								//上传数量
								'queueSizeLimit' : 25,
								//每次更新上载的文件的进展
								'onUploadProgress' : function(file,
										bytesUploaded, bytesTotal,
										totalBytesUploaded, totalBytesTotal) {
									//有时候上传进度什么想自己个性化控制，可以利用这个方法
									//使用方法见官方说明
								},
								//选择上传文件后调用
								'onSelect' : function(file) {

								},
								//返回一个错误，选择文件的时候触发
								'onSelectError' : function(file, errorCode,
										errorMsg) {
									switch (errorCode) {
									case -100:
										alert("上传的文件数量已经超出系统限制的"
												+ $('#file_upload').uploadify(
														'settings',
														'queueSizeLimit')
												+ "个文件！");
										break;
									case -110:
										alert("文件 ["
												+ file.name
												+ "] 大小超出系统限制的"
												+ $('#file_upload').uploadify(
														'settings',
														'fileSizeLimit')
												+ "大小！");
										break;
									case -120:
										alert("文件 [" + file.name + "] 大小异常！");
										break;
									case -130:
										alert("文件 [" + file.name + "] 类型不正确！");
										break;
									}
								},
								//检测FLASH失败调用
								'onFallback' : function() {
									alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
								},
								'onUploadStart' : function(file) {
									var id = $(file).attr("id");
									var imgBrief = $("#" + id).find("textarea")
											.val();
									$("#file_add").uploadify("settings",
											"formData", {
												'imgBrief' : imgBrief,
												'fileId' : id
											});
									//在onUploadStart事件中，也就是上传之前，把参数写好传递到后台。  
								},
								//上传到服务器，服务器返回相应信息到data里
								'onUploadSuccess' : function(file, data,
										response) {
									var id = $(file).attr("id");
									data = $.parseJSON(data);
									$("#" + id).find("img").attr("src",
											data.url);
									$("#" + id).find(".fileName").hide("slow");
									$("#" + id).find(".data").hide("slow");
									$("#" + id).find(".uploadify-progress")
											.hide("slow");
									$("#" + id).find(".myText")
											.html(data.brief).css(
													"height", "auto");
								}
							});

			if ('${msg}' != '') {
				$('#tipContent').html('${msg}');
				$('#myModal').modal('show');
			}

			if ($("#isTop").val() == "" || $("#isTop").val() != 1) {
				$("#isTop").val(2);
			} else {
				$("#isTopButton").addClass('on').html(
						$("#isTopButton").data("on-text"));
			}

			if ($("#category").val() == "" || $("#category").val() != 1) {
				$("#category").val(0);
			} else {
				$("#categoryButton").addClass('on').html(
						$("#categoryButton").data("on-text"));
			}

			// Switch slide buttons
			$("#isTopButton").click(function() {
				if ($(this).hasClass("on")) {
					$("#isTop").val(2);
					$(this).removeClass('on').html($(this).data("off-text"));
				} else {
					$("#isTop").val(1);
					$(this).addClass('on').html($(this).data("on-text"));
				}
			});

			// Switch slide buttons
			$("#categoryButton").click(function() {
				if ($(this).hasClass("on")) {
					$("#category").val(0);
					$(this).removeClass('on').html($(this).data("off-text"));
				} else {
					$("#category").val(1);
					$(this).addClass('on').html($(this).data("on-text"));
				}
			});

			if ('${seller.logo}' != '' && '${seller.logo}' != null) {
				$("#showLogo").css("display", "inline-block");
				$("#addImg").hide();
			}

			$("#showLogo").hover(function() {
				$(".delImg").show();
			}, function() {
				$(".delImg").hide();
			});

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
							if (init == 1 && '${seller.provinceId}' != '' && '${seller.provinceId}' != 0) {
								$("#province").val(
										'${seller.provinceId}');
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
							if (init == 1 && '${seller.cityId}' != '' && '${seller.cityId}' != 0) {
								$("#city").val('${seller.cityId}');
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
							if (init == 1 && '${seller.areaId}' != '' && '${seller.areaId}' != 0) {
								$("#area").val('${seller.areaId}');
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
							if (init == 1 && '${seller.typeId}' != '' && '${seller.typeId}' != 0) {
								$("#type").val('${seller.typeId}');
								setSubType('${seller.typeId}', init);
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
							if (init == 1 && '${seller.subTypeId}' != '' && '${seller.subTypeId}' != 0) {
								$("#subType").val('${seller.subTypeId}');
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
