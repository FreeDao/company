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
					  <li class="active"><a href="${url }" style="cursor: pointer;" >菜单管理</a> <span class="divider">/</span></li>
					  <li class="active">${title }</li>
					</ul>
                </div>

                <div class="row-fluid form-wrapper">
                    <!-- left column -->
                    <div class="span9 with-sidebar">
                        <div class="container">
                            <form class="new_user_form inline-input" id="myForm" action="bg/system!addOrUpdateMenu" method="post"  />
                            <input name="menu.id" value="${menu.id }" type="hidden">
                                <div class="span12 field-box">
                                    <label>菜单名称:</label>
                                    <input id="name" class="span9" type="text" name="menu.name" value="${menu.name }" placeholder="*必填" />
                                    <div class="tooltip fade right in" style="display: none;">
										<div class="tooltip-arrow"></div>
										<div class="tooltip-inner" style="max-width: 300px;">请填写菜单名称</div>
									</div>
                                </div>
                                
                                <div class="span12 field-box"  >
                                    <label>列表类型:</label>
                                    <div class="ui-select span5">
                                        <select name="menu.listId" onchange="changeList(this)" >
                                        	<option value="0" >无</option>
	                                        <c:forEach items="${list }" var="l">
	                                        	<c:choose>
	                                        		<c:when test="${menu.listId == l.id }"><option selected="selected" value="${l.id }" >${l.name }</option></c:when>
	                                        		<c:otherwise>
	                                        		<option value="${l.id }" >${l.name }</option>
	                                        		</c:otherwise>
	                                        	</c:choose>
	                                        </c:forEach>
                                        </select>
                                         <script type="text/javascript">
			                                function changeList(t){
			                                	if($(t).val() != 0){
			                                		$("#url").hide();                                		
			                                	}else{
			                                		$("#url").show();
			                                	}
			                                }
			                             </script>
                                    </div>
                                </div>
                                
                                <div id="url" class="span12 field-box error">
                                    <label>菜单连接:</label>
                                    <input class="span9" type="text" name="menu.url" value="${menu.url }" placeholder="选填,若有列表类型，系统会自动生成连接" />
                                </div>
                                
                                <div class="span12 field-box">
                                    <label>菜单层级:</label>
                                    <div class="ui-select span5">
                                        <select name="menu.level" onchange="changeLevel(this)" >
                                        	<c:forEach begin="1" end="5" step="1" var="i">
                                        		<c:choose>
													<c:when test="${menu.level == i }"><option value="${i }" selected="selected" >${i } </option></c:when>       
													<c:otherwise><option value="${i }" >${i } </option></c:otherwise>                                 		
                                        		</c:choose>
                                        	</c:forEach>
                                        </select>
                                         <script type="text/javascript">
			                                function changeLevel(t){
			                                	if($(t).val() != 1){
			                                		$("#icon").hide();
			                                		$("#pid").show();                               		
			                                	}else{
			                                		$("#icon").show();
			                                		$("#pid").hide();
			                                	}
			                                }
			                                </script>
                                    </div>
                                </div>

                                <div id="icon" class="span12 field-box">
                                    <label>菜单图标:</label>
                                    <input class="span9" type="text" name="menu.icon" value="${menu.icon }" placeholder="选填,菜单层级为1时需要图标" />
                                </div>
                                
                                <div id="pid" class="span12 field-box" style="display: none;">
                                    <label>上级菜单:</label>
                                    <div class="ui-select span5">
                                        <select name="menu.pid">
                                        	<option value="0" >无</option>
	                                        <c:forEach items="${menus }" var="m">
	                                        	<c:choose>
	                                        		<c:when test="${menu.pid == m.id }"><option selected="selected" value="${m.id }" >${m.name }</option></c:when>
	                                        		<c:otherwise>
	                                        		<option value="${m.id }" >${m.name }</option>
	                                        		</c:otherwise>
	                                        	</c:choose>
	                                        </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                
                                <div class="span12 field-box">
                                    <label>菜单排序:</label>
                                    <input class="span9" type="text" name="menu.sort" value="${menu.sort }" placeholder="选填,只能填写数字" onkeyup="this.value=this.value.replace(/\D/g,'')" />
                                </div>
                                
                                <%--<div class="span12 field-box">
                                    <label>Website:</label>
                                    <input class="span9" type="text" />
                                </div>
                                <div class="span12 field-box">
                                    <label>Address:</label>
                                    <div class="address-fields">
                                        <input class="span12" type="text" placeholder="Street address" />
                                        <input class="span12 small" type="text" placeholder="City" />
                                        <input class="span12 small" type="text" placeholder="State" />
                                        <input class="span12 small last" type="text" placeholder="Postal Code" />
                                    </div>
                                </div>
                                <div class="span12 field-box textarea">
                                    <label>Notes:</label>
                                    <textarea class="span9"></textarea>
                                    <span class="charactersleft">90 characters remaining. Field limited to 100 characters</span>
                                </div>
                                --%><div class="span11 field-box actions">
                                    <input type="button" class="btn-flat primary" value="提 交" onclick="validSubmit(this,'myForm','name')" />
                                    <span>或者</span>
                                    <a class="btn-flat default" href="${url }" >返回</a>
                                </div>
                            </form>
                        </div>
                    </div>

                    <!-- side right column -->
                    <div class="span3 form-sidebar pull-right">
                        <%--<div class="btn-group toggle-inputs hidden-tablet">
                            <button class="glow left active" data-input="inline">INLINE INPUTS</button>
                            <button class="glow right" data-input="normal">NORMAL INPUTS</button>
                        </div>
                        --%>
                        <div class="alert alert-info hidden-tablet">
                            <i class="icon-lightbulb pull-left"></i>
                            	图标对应编码，这里只提供了部分常用图标对应编码，可以通过查看文档获取更多图标编码
                        </div>  
                        <ul style="display: inline-block;">
                                <li><i class="icon-home"></i>icon-home</li>
                                <li><i class="icon-signal"></i>icon-signal</li>
                                <li><i class="icon-ban-circle"></i>icon-ban-circle</li>
                                <li><i class="icon-bar-chart"></i>icon-bar-chart</li>
                                <li><i class="icon-barcode"></i> icon-barcode</li>
                                <li><i class="icon-calendar-empty"></i>icon-calendar-empty</li>
                                <li><i class="icon-th-large"></i>icon-th-large</li>
                                <li><i class="icon-bell"></i>icon-bell</li>
                                <li><i class="icon-bell-alt"></i>icon-bell-alt</li>
                                <li><i class="icon-bolt"></i>icon-bolt</li>
                                <li><i class="icon-book"></i>icon-book</li>
                                <li><i class="icon-bookmark"></i>icon-bookmark</li>
                                <li><i class="icon-bookmark-empty"></i>icon-bookmark-empty</li>
                                <li><i class="icon-briefcase"></i>icon-briefcase</li>
                                <li><i class="icon-bullhorn"></i>icon-bullhorn</li>
                                <li><i class="icon-calendar"></i>icon-calendar</li>
                                <li><i class="icon-camera"></i>icon-camera</li>
                                <li><i class="icon-camera-retro"></i>icon-camera-retro</li>
                                <li><i class="icon-certificate"></i>icon-certificate</li>
                            </ul>   
                            <ul style="display: inline-block;">
                                <li><i class="icon-check"></i>icon-check</li>
                                <li><i class="icon-group"></i>icon-group</li>
                                <li><i class="icon-edit"></i>icon-edit</li>
                                <li><i class="icon-picture"></i>icon-picture</li>
                                <li><i class="icon-cloud"></i>icon-cloud</li>
                                <li><i class="icon-cloud-download"></i>icon-cloud-download</li>
                                <li><i class="icon-cloud-upload"></i>icon-cloud-upload</li>
                                <li><i class="icon-coffee"></i>icon-coffee</li>
                                <li><i class="icon-cog"></i>icon-cog</li>
                                <li><i class="icon-cogs"></i>icon-cogs</li>
                                <li><i class="icon-comment"></i>icon-comment</li>
                                <li><i class="icon-comment-alt"></i>icon-comment-alt</li>
                                <li><i class="icon-comments"></i>icon-comments</li>
                                <li><i class="icon-comments-alt"></i>icon-comments-alt</li>
                                <li><i class="icon-credit-card"></i>icon-credit-card</li>
                                <li><i class="icon-dashboard"></i>icon-dashboard</li>
                                <li><i class="icon-desktop"></i>icon-desktop</li>
                                <li><i class="icon-download"></i>icon-download</li>
                                <li><i class="icon-download-alt"></i>icon-download-alt</li>
                            </ul>                   
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
			
			if('${menu.listId}' != 0){
				$('#url').hide();
			}
			
			if('${menu.level}' != 1 && '${menu.level}' != ''){
				$('#icon').hide();
        		$('#pid').show(); 
			}
			
		});
     </script>
  </body>
</html>
