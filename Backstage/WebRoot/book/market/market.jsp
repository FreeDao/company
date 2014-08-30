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
  <script src="book/market/market.js" type="text/javascript"></script>
   </head>
   <body >
       <div id="datagrid"></div>  
           <div id="newTxl" title="增加窗口"  style="width: 600px; height: 300px;">
           <div style="padding: 20px 20px 40px 80px;">
            <form id="addForm" method="post"  enctype="multipart/form-data">
            <table>
                <tr>
                    <td>  类型：</td>
                    <td> <input name="type"  class="easyui-validatebox"     data-options="required:true" style="width: 300px;" /></td>
                </tr>
                <tr>
                    <td>  logo：</td>
                    <td><input  name="picture" type="file"/></td>
                </tr>
                 <tr>
                    <td> 城市</td>
                    <td>
                    	<select id="city" name="city" class="city" onchange="onType();">
                    		
                    	</select>
                    </td>
                </tr>
                <tr>
                    <td> 小区</td>
                    <td>
                    	<select id="villageId" name="villageId" class="villageId">
                    	
                    	</select>
                    </td>
                </tr>    
                    <tr>
                    <td>类型</td>
                    <td>
                    	<select id="typeEr" name="typeEr" class="typeEr">
                    		<option value="1">便民市场</option>
                    		<option value="2">小区市场</option>
                    	</select>
                    </td>
                </tr>                      
            </table>
            </form>
        </div>
    </div>
    
       <div id="editTxl" title="编辑窗口"  style="width: 600px; height: 300px;">
           <div style="padding: 20px 20px 40px 80px;">
            <form id="editForm" method="post">
            <div style="display:none"><input name="txlid"/></div>
            <table>
                 <tr>
                    <td>  标题：</td>
                    <td> 
                    <input type="hidden" value="id" name = "id">
                    <input name="title"  class="easyui-validatebox"     data-options="required:true" style="width: 300px;" /></td>
                </tr>
                <tr>
                    <td>  作者：</td>
                    <td> <input  name="author" class="easyui-validatebox"  data-options="required:true"  style="width: 300px;" /></td>
                </tr>
                 <tr>
                    <td>  内容：</td>
                    <td> <input  name="conent"  style="width: 300px;" data-options="required:true" style="width: 300px;" /></td>
                </tr>
            </table>
            </form>
        </div>
    </div>
    
    
     <div id="search" title="查询窗口" style="width: 400px; height: 250px;">
        <div style="padding: 20px 20px 40px 80px;">
            <form id="searchForm" method="post">
            <table>
                <tr>
                    <td>标题：</td>
                    <td><select name="title" id="title">
                    	<option value="1">便民市场</option>
                    	<option value="2">我的小区</option>
                    </select></td>
                </tr>
            </table>
            </form>
        </div>
        
        
        <div style="text-align: center; padding: 5px;">
            <a href="javascript:void(0)" onclick="searchTxl()" id="btn-search" icon="icon-ok">确定</a>
            <a href="javascript:void(0)" onclick="closeSearchWindow()" id="btn-search-cancel" icon="icon-cancel">取消</a>
        </div>
    </div> 
</body>
 </html>