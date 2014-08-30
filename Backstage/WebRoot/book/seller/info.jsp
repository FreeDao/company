<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%
 String path = request.getContextPath();
 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>
 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 <html>
   <head>
     <base href="<%=basePath%>">
     <title>管理员</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">    
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <link href="book/css/default/easyui.css" rel="stylesheet" type="text/css" />
  <link href="book/css/icon.css" rel="stylesheet" type="text/css" /> 
  <script src="book/js/jquery-1.10.2.js" type="text/javascript"></script>
  <script src="book/js/jquery.easyui.min.js" type="text/javascript"></script>
  <script src="book/js/easyui-lang-zh_CN.js" type="text/javascript"></script>   
  <script src="book/seller/info.js" type="text/javascript"></script>
   </head>
   <body >
   <script type="text/javascript">
   if('${msg}' == "0"){
   		alert("修改成功！");
   };
   </script>
   <h1 align="center">个人信息修改</h1>
   <div style="background:#fafafa;padding:10px;width:600px;height:300px;margin: 0 auto;">  
        <form id="infoForm" method="post" enctype="multipart/form-data" action="../Backstage/System/addOrUpdateSeller.action">  
        
        <div style="width: 280px;float:right;margin-top: 10px;">
        <img alt="" src=""  width="280px" height="180px" id="logo"/>
        <div align="center"><input type="file" name="file" /></div>
        </div>
        
        <div style="width: 300px;">
            <div style="margin-top: 10px;">  
                <label for="titile">标题:</label>  
                <input class="easyui-validatebox" type="text" name="titile" style="width: 300px;"/>  
            </div>  
            <div style="margin-top: 10px;">  
                <label for="phone">联系电话:</label>  
                <input class="easyui-validatebox" type="text" name="phone" style="width: 300px;"/>  
            </div> 
             <div style="margin-top: 10px;">  
                <label for="address">地址:</label>  
                <input name="address" style="width: 300px;" class="easyui-validatebox" data-options="required:true"/>
            </div>  
            <div style="margin-top: 10px;">
             	<label for="phone">类型:</label>  
                <select id="typeId" name="typeId" style="width: 300px;" data-options="required:true" editable="false"></select>
            </div> 
        </div>
            <div style="margin-top: 10px;">  
                <label for="brief">商家简介:</label>  
                <textarea class="easyui-validatebox" name="brief" style="width: 600px;height:80px;"></textarea>  
            </div> 
            <div style="margin-top: 10px;">  
                <label for="brief">产品简介:</label>  
                <textarea class="easyui-validatebox" name="name" style="width: 600px;height:80px;"></textarea>  
            </div> 
            <div style="margin-top: 10px;">  
                <label for="brief">招聘信息:</label>  
                <textarea class="easyui-validatebox" name="recruitment" style="width: 600px;height:80px;"></textarea>  
            </div> 
            
            <div style="margin-top: 10px;" align="center">  
                <input type="submit" value="提交">  
            </div>  
        </form>  
    </div>  
</body>
 </html>