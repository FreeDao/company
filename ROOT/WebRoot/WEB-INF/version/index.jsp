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

<title>My JSP 'test.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<%@include file="../common.jsp"%>

</head>

<body>

	<jsp:include page="../common_top.jsp"></jsp:include>
	<div id="content">
		<div class="container">
			<div class="row">
				<div class="span3">
					<jsp:include page="../common_left.jsp"></jsp:include>
				</div>
				<!-- /span3 -->
				<div class="span9">
					<h1 class="page-title">版本信息</h1>

					<div class="widget">

						<div class="widget-header">
							<h3>版本基本信息</h3>
						</div>
						<!-- /widget-header -->

						<div class="widget-content">



							<div class="tabbable">
								<ul class="nav nav-tabs">
									<li id="init" class="active">
									<a href="#1"
										data-toggle="tab">android</a></li>
									<li><a href="#2" data-toggle="tab">ios</a>
									</li>
								</ul>

								<br />
								<form id="edit-profile" class="form-horizontal"
									action="bg/user!updateVersion" method="post">
									<input type="hidden" name="iisForced" id="iisForced"
										value="${iisForced }" /> <input type="hidden" name="aisForced"
										id="aisForced" value="${aisForced }" />

									<div class="tab-content">
										<div class="tab-pane active" id="1">

											<fieldset>

												<div class="control-group">
													<label class="control-label" for="aversion">最新版本编号</label>
													<div class="controls">
														<input type="text" class="input-medium" id="aversion"
															name="aversion" value="${android.version }" />
														<p class="help-block">软件检测以此版本号为准.</p>
													</div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->


												<div class="control-group">
													<label class="control-label" for="aurl">下载地址</label>
													<div class="controls">
														<input type="text" class="input-medium" id="aurl"
															name="aurl" value="${android.url }" />
													</div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->


												<div class="control-group">
													<label class="control-label" for="aIsUpdate">是否强制更新</label>
													<div class="controls">
														<div class="btn-group">
															<button class="btn dropdown-toggle"
																data-toggle="dropdown" id="aforced">
																<c:if test="${aisForced == 0 }">否</c:if>
																<c:if test="${aisForced == 1 }">是</c:if>
																<span class="caret"></span>
															</button>
															<ul class="dropdown-menu">
																<li><a style="cursor: pointer;"
																	onclick="changeThis(this,1,1)">是</a>
																</li>
																<li><a style="cursor: pointer;"
																	onclick="changeThis(this,1,0)">否</a>
																</li>
															</ul>
														</div>
													</div>
												</div>
												<!-- /control-group -->


												<div class="control-group">
													<label class="control-label" for="acontent">版本更新内容</label>
													<div class="controls">
														<input type="text" class="input-large" id="acontent"
															name="acontent" value="${android.content }" />
													</div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->

											</fieldset>
										</div>

										<div class="tab-pane" id="2">
											<fieldset>

												<div class="control-group">
													<label class="control-label" for="iversion">最新版本编号</label>
													<div class="controls">
														<input type="text" class="input-medium " id="iversion"
															name="iversion" value="${ios.version }" />
														<p class="help-block">软件检测以此版本号为准.</p>
													</div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->


												<div class="control-group">
													<label class="control-label" for="iurl">下载地址</label>
													<div class="controls">
														<input type="text" class="input-medium" id="iurl"
															name="iurl" value="${ios.url }" />
													</div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->

												<div class="control-group">
													<label class="control-label" for="lastname">是否强制更新</label>
													<div class="controls">
														<div class="btn-group">
															<button class="btn dropdown-toggle"
																data-toggle="dropdown" id="iforced">
																<c:if test="${iisForced == 0 }">否</c:if>
																<c:if test="${iisForced == 1 }">是</c:if>
																<span class="caret"></span>
															</button>
															<ul class="dropdown-menu">
																<li><a style="cursor: pointer;"
																	onclick="changeThis(this,0,1)">是</a>
																</li>
																<li><a style="cursor: pointer;"
																	onclick="changeThis(this,0,0)">否</a>
																</li>
															</ul>
														</div>
													</div>
												</div>
												<!-- /control-group -->

												<div class="control-group">
													<label class="control-label" for="icontent">版本更新内容</label>
													<div class="controls">
														<input type="text" class="input-large" id="icontent"
															name="icontent" value="${ios.content }" />
													</div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->
											</fieldset>
										</div>

										<div class="form-actions">
											<button type="submit" class="btn btn-primary">保存</button>
										</div>
										<!-- /form-actions -->
									</div>
								</form>
							</div>
						</div>
						<!-- /widget-content -->
					</div>
					<!-- /widget -->
				</div>
				<!-- /span9 -->
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /content -->

	<jsp:include page="../common_bottom.jsp"></jsp:include>
	<script type="text/javascript">
		$(window).load(function() {
			$("#init").addClass("active");
			$("#1").addClass("active");
		});
		function changeThis(t, i, v) {
			if (i == 0) {
				$("#iforced").html(
						$.trim($(t).text())
								+ " \<span class='caret'\>\<\/span\>");
				$("#iisForced").val(v);
			} else {
				$("#aforced").html(
						$.trim($(t).text())
								+ " \<span class='caret'\>\<\/span\>");
				$("#aisForced").val(v);
			}
		}
	</script>
</body>
</html>
