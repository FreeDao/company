<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page"><%--
	
  <link rel="stylesheet" type="text/css" href="css/uploadify.css">
  --%>
  <link href="css/bootstrap/bootstrap.css" type="text/css" rel="stylesheet" />
  <link href="css/lib/select2.css" type="text/css" rel="stylesheet" />
  </head>
    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/select2.min.js"></script>
    <body>
    
   <input type="text" value="asdf" class="input-large datepicker"/>
    
    
    <script type="text/javascript">
    $(function() {
		 $('.datepicker').datepicker({
			 	language:'zh-CN',
			    format: 'yyyy-mm-dd'
		 	}).on('changeDate', function (ev) {
               $(this).datepicker('hide');
           });
    });
    </script>
    
    <%--<script type="text/javascript" src="js/uploadify/swfobject.js"></script>
    <script src="js/uploadify/jquery.uploadify.js" type="text/javascript"></script><%--
	  <script type="text/javascript">
        $(document).ready(function() {
            $("#uploadify").uploadify({
                'uploader'       : 'js/uploadify/uploadify.swf',
                'script'         : 'in/img!upload',
                'cancelImg'      : 'images/cancel.png',
                'folder'         : 'uploads',
                'queueID'        : 'fileQueue',
                'auto'           : false,
                'multi'          : true,
                'simUploadLimit' : 2,
                'buttonText'     : 'BROWSE'
            });
        });
        </script>
 <body>
  
  
  	<h1>Uploadify Demo</h1>
	<div id="queue"></div>
	<input id="file_add" name="file" type="file" multiple="true">
	<button id="file_upload" class="upload"  type="button" onclick="uploadImg()" >上传</button>

	<script type="text/javascript">
	
	function uploadImg(){
		$("#file_add").uploadify('upload','*');
	}
	
	$(document).ready(function() {
	    $("#file_add").uploadify({
	        //开启调试
	        'debug' : false,
	        //是否自动上传
	        'auto':false,
	        //超时时间
	        'successTimeout':99999,
	        //附带值
	        'formData':{
	            'userid':'用户id',
	            'username':'用户名',
	            'rnd':'加密密文'
	        },
	        //flash
	        'swf': "js/uploadify/uploadify.swf",
	        //不执行默认的onSelect事件
	        'overrideEvents' : ['onDialogClose'],
	        //文件选择后的容器ID
	        'queueID':'queue',
	        //服务器端脚本使用的文件对象的名称 $_FILES个['upload']
	        'fileObjName':'file',
	        //上传处理程序
	        'uploader':'in/img!upload',
	        //浏览按钮的背景图片路径
	        //'buttonImage':'img/gallery2.jpg',
	        'buttonText':'多图片上传',
	        'removeCompleted':false,
	        //浏览按钮的宽度
	        'width':'100',
	        //浏览按钮的高度
	        'height':'32',
	        //expressInstall.swf文件的路径。
	        //'expressInstall':'expressInstall.swf',
	        //在浏览窗口底部的文件类型下拉菜单中显示的文本
	        'fileTypeDesc':'支持的格式：',
	        //允许上传的文件后缀
	        'fileTypeExts':'*.jpg;*.jpge;*.gif;*.png',
	        //上传文件的大小限制
	        'fileSizeLimit':'5MB',
	        //上传数量
	        'queueSizeLimit' : 25,
	        //每次更新上载的文件的进展
	        'onUploadProgress' : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {
	             //有时候上传进度什么想自己个性化控制，可以利用这个方法
	             //使用方法见官方说明
	        },
	        //选择上传文件后调用
	        'onSelect' : function(file) {
	                  
	        },
	        //返回一个错误，选择文件的时候触发
	        'onSelectError':function(file, errorCode, errorMsg){
	            switch(errorCode) {
	                case -100:
	                    alert("上传的文件数量已经超出系统限制的"+$('#file_upload').uploadify('settings','queueSizeLimit')+"个文件！");
	                    break;
	                case -110:
	                    alert("文件 ["+file.name+"] 大小超出系统限制的"+$('#file_upload').uploadify('settings','fileSizeLimit')+"大小！");
	                    break;
	                case -120:
	                    alert("文件 ["+file.name+"] 大小异常！");
	                    break;
	                case -130:
	                    alert("文件 ["+file.name+"] 类型不正确！");
	                    break;
	            }
	        },
	        //检测FLASH失败调用
	        'onFallback':function(){
	            alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
	        },
	        'onUploadStart': function (file) {
	        	var id = $(file).attr("id");
	        	var imgBrief = $("#"+id).find("textarea").val();
                $("#file_add").uploadify("settings", "formData", { 'imgBrief': imgBrief });  
                //在onUploadStart事件中，也就是上传之前，把参数写好传递到后台。  
            },
	        //上传到服务器，服务器返回相应信息到data里
	        'onUploadSuccess':function(file, data, response){
	        	var id = $(file).attr("id");
	        	data = $.parseJSON(data);
	        	$("#"+id).find("img").attr("src",data.url);
	        }
	    });
	});
	</script>
  
  
  
  
  
  
  
  <%--
  
   <div id="fileQueue"></div>
   <input type="file" name="uploadify" id="uploadify" />
   <p>
   <a href="javascript:jQuery('#uploadify').uploadifyUpload()">开始上传</a>&nbsp;
   <a href="javascript:jQuery('#uploadify').uploadifyClearQueue()">取消所有上传</a>
   </p>
	
	
--%></body>
</html>
