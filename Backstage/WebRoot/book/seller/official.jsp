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
  <script src="book/seller/official.js" type="text/javascript"></script>
   </head>
   <body >
       <div id="datagrid"></div>  
       <div id="editWindow" title="编辑窗口"  style="width: 600px; height: 300px;">
           <div style="padding: 20px 0px 40px 80px;">
            <form id="editForm" method="post" enctype="multipart/form-data">
            <br><br><br><input  name="id" type="hidden" />
            <table>
                <tr>
                    <td>  标题：</td>
                    <td> <input  name="title"  class="easyui-validatebox"   data-options="required:true"  style="width: 300px;" /></td>
                </tr>
                 <tr>
                    <td>  内容：</td>
                    <td> <input name="comment"  class="easyui-validatebox" data-options="required:true"  style="width: 300px;" /></td>
                </tr>                
                 <tr>
                    <td>  开奖时间：</td>
                    <td> <input  name="lotteryAddtime"  style="width: 300px;" class="easyui-datebox" data-options="required:true"  editable="false"/></td>
                </tr> 
                <tr>
                    <td>  中奖概率：</td>
                    <td> <input  name="rate"  style="width: 300px;" class="easyui-numberbox" data-options="required:true"  /></td>
                </tr> 
                <tr>
                    <td>  活动类型：</td>
                    <td> 
                    <!-- <input id="rootQode" class="easyui-combobox" 
					name="rootQode" style="width: 300px;"
					data-options="valueField: 'value',textField: 'label',data: [{label: '优惠劵',value: '1',selected:true},{label: '免单',value: '2'}]" /> -->
                    <select id="rootQode" name="rootQode" class="easyui-combobox" style="width: 300px;" data-options="required:true" editable="false">
                    	<option value="1">优惠劵</option>
                    	<option value="2">免单</option>
                    </select>
                   </td>
                </tr> 
                <tr>
                    <td>  联系电话：</td>
                    <td> <input  name="iphone"  style="width: 300px;" class="easyui-validatebox" /></td>
                </tr> 
                <tr>
                    <td>  价格：</td>
                    <td> <input  name="price"  style="width: 300px;" class="easyui-validatebox" data-options="required:true"  /></td>
                </tr>  
                <tr>
                    <td>  份数：</td>
                    <td> <input  name="num"  style="width: 300px;" class="easyui-validatebox" data-options="required:true"  /></td>
                </tr> 
                <tr>
                    <td>  活动介绍：</td>
                    <td> <textarea name="bife" style="width: 300px;height: 100px;"></textarea></td>
                </tr> 
                <tr>
                    <td>  活动奖品：</td>
                    <td> <textarea name="prize" style="width: 300px;height: 100px;"></textarea></td>
                </tr>
                <tr>
                    <td>  活动规则：</td>
                    <td> <textarea name="rules" style="width: 300px;height: 100px;"></textarea></td>
                </tr>
                <tr>
                	<td>图片上传：</td>
                	<td><input name="file" type="file"/></td>
                </tr>
            </table>
            </form>
        </div>
    </div>
    
    
    
       <!-- <div id="editTxl" title="编辑窗口"  style="width: 600px; height: 300px;">
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
    </div> -->
    
    
     <div id="search" title="查询窗口" style="width: 400px; height: 250px;">
        <div style="padding: 20px 20px 40px 80px;">
            <form id="searchForm" method="post">
            <table>
              <tr>
                    <td>  标题：</td>
                    <td><input name="name" id="name" style="width: 150px;" /> </td>
                </tr>
                <tr>
                    <td>  内容：</td>
                    <td><input name="youxiang" id="youxiang" style="width: 150px;" /> </td>
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