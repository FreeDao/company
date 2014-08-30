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
  <script src="book/city/official.js" type="text/javascript"></script>
   </head>
   <body >
       <div id="datagrid"></div>  
           <div id="newTxl" title="增加窗口"  style="width: 600px; height: 300px;">
           <div style="padding: 20px 20px 40px 80px;">
            <form id="addForm" method="post">
            <table>
                <tr>
                    <td>  标题：</td>
                    <td> <input  name="title"  class="easyui-validatebox"   data-options="required:true"  style="width: 300px;" />
                    </td>
                </tr>
                 <tr>
                    <td>  内容：</td>
                    <td> <input name="comment"  class="easyui-validatebox" data-options="required:true"  style="width: 300px;" /></td>
                </tr>                
                 <tr>
                    <td>  开奖时间：</td>
                    <td> <input  name="lotteryAddtime"  style="width: 300px;" class="easyui-datebox" data-options="required:true"  /></td>
                </tr>    
                <tr>
                    <td>  活动介绍：</td>
                    <td> <input  name="bife" type="text" style="width: 300px;height: 200px;" data-options="required:true"  /></td>
                </tr> 
                <tr>
                    <td>  活动奖品：</td>
                    <td> <input  name="prize"  style="width: 300px;height: 200px;" data-options="required:true"  /></td>
                </tr>
                <tr>
                    <td>  活动规则：</td>
                    <td> <input  name="rules"  style="width: 300px;height: 200px;" data-options="required:true"  /></td>
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
                    <td> <input  name="title"  class="easyui-validatebox"   data-options="required:true"  style="width: 300px;" />
                    <input  name="id" type="hidden" />
                    </td>
                </tr>
                 <tr>
                    <td>  内容：</td>
                    <td> <input name="comment"  class="easyui-validatebox" data-options="required:true"  style="width: 300px;" /></td>
                </tr>                
                 <tr>
                    <td>  开奖时间：</td>
                    <td> <input  name="lotteryAddtime"  style="width: 300px;" class="easyui-datebox" data-options="required:true"  /></td>
                </tr>    
                <tr>
                    <td>  活动介绍：</td>
                    <td> <input  name="bife" type="text" style="width: 300px;height: 200px;" data-options="required:true"  /></td>
                </tr> 
                <tr>
                    <td>  活动奖品：</td>
                    <td> <input  name="prize"  style="width: 300px;height: 200px;" data-options="required:true"  /></td>
                </tr>
                <tr>
                    <td>  活动规则：</td>
                    <td> <input  name="rules"  style="width: 300px;height: 200px;" data-options="required:true"  /></td>
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
                    <td>  姓名：</td>
                    <td><input name="name" id="name" style="width: 150px;" /> </td>
                </tr>
                 <tr>
                    <td>  邮箱：</td>
                    <td><input name="youxiang" id="youxiang" style="width: 150px;" /> </td>
                </tr>
                 <tr>
                    <td>  电话：</td>
                    <td><input name="phone" id="phone" style="width: 150px;" /> </td>
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