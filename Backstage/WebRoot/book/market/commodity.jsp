<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%
 String path = request.getContextPath();
 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  <script type="text/javascript" src="book/js/datagrid-detailview.js"></script>   
  <script src="book/market/commodity.js" type="text/javascript"></script>
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
                    <td> <input  name="titile"  class="easyui-validatebox"   data-options="required:true"  style="width: 300px;" /></td>
                </tr>
                 <tr>
                    <td>  联系电话：</td>
                    <td> <input name="phone"  class="easyui-validatebox" data-options="required:true"  style="width: 300px;" /></td>
                </tr>                
                <tr>
                    <td>  市场价格：</td>
                    <td> <input  name="otherPrice"  style="width: 300px;" class="easyui-numberbox" data-options="required:true"  /></td>
                </tr>
                <tr>
                    <td>  卖价：</td>
                    <td> <input  name="price"  style="width: 300px;" class="easyui-numberbox" data-options="required:true"  /></td>
                </tr>  
                <!-- <tr>
                    <td>  优惠：</td>
                    <td> <input  name="preferential"  style="width: 300px;" class="easyui-validatebox" data-options="required:true"  /></td>
                </tr> --> 
                <tr>
                	<td>  星级：</td>
                    <td> 
                    <!-- <input id="rootQode" class="easyui-combobox" 
					name="rootQode" style="width: 300px;"
					data-options="valueField: 'value',textField: 'label',data: [{label: '优惠劵',value: '1',selected:true},{label: '免单',value: '2'}]" /> -->
                    <select id="level" name="level" class="easyui-combobox" style="width: 300px;" data-options="required:true" editable="false">
                    	<option value="1">★</option>
                    	<option value="1.5">★☆</option>
                    	<option value="2">★★</option>
                    	<option value="2.5">★★☆</option>
                    	<option value="3">★★★</option>
                    	<option value="3.5">★★★☆</option>
                    	<option value="4">★★★★</option>
                    	<option value="4.5">★★★★☆</option>
                    	<option value="5">★★★★★</option>
                    </select>
                   </td>
                </tr>
                <c:if test="${LoginUser.role == 1 }">
                <tr>
                    <td> 城市</td>
                    <td>
                    	<select id="city" name="city" class="easyui-combobox" data-options="required:true" style="width: 300px;" editable="false">
                    		
                    	</select>
                    </td>
                </tr>
                <tr>
                    <td>  所属小区：</td>
                    <td> 
                    <!-- <input id="rootQode" class="easyui-combobox" 
					name="rootQode" style="width: 300px;"
					data-options="valueField: 'value',textField: 'label',data: [{label: '优惠劵',value: '1',selected:true},{label: '免单',value: '2'}]" /> -->
                    <select id="villageId" name="villageId" class="easyui-combobox" style="width: 300px;" data-options="required:true" editable="false">
                    </select>
                   </td>
                </tr> 
                </c:if>
                 <tr>
                    <td>  一级栏目：</td>
                    <td> 
                    <!-- <input id="rootQode" class="easyui-combobox" 
					name="rootQode" style="width: 300px;"
					data-options="valueField: 'value',textField: 'label',data: [{label: '优惠劵',value: '1',selected:true},{label: '免单',value: '2'}]" /> -->
                    <select id="shopId" name="shopId" class="easyui-combobox" style="width: 300px;" data-options="required:true" editable="false">
                    </select>
                   </td>
                </tr> 
                <tr>
                    <td>  二级栏目：</td>
                    <td> 
                    <!-- <input id="rootQode" class="easyui-combobox" 
					name="rootQode" style="width: 300px;"
					data-options="valueField: 'value',textField: 'label',data: [{label: '优惠劵',value: '1',selected:true},{label: '免单',value: '2'}]" /> -->
                    <select id="shopIdTwo" name="shopIdTwo" class="easyui-combobox" style="width: 300px;" data-options="required:true" editable="false">
                    </select>
                   </td>
                </tr> 
                <tr>
                    <td>  商品介绍：</td>
                    <td> <textarea name="brief" style="width: 300px;height: 100px;"></textarea></td>
                </tr> 
                <tr>
                	<td>图片上传：</td>
                	<td>
						<input type="file" name="file" />
					</td>
                </tr>
                <!-- <tr>
                    <td>  运费：</td>
                    <td> <textarea name="prize" style="width: 300px;height: 100px;"></textarea></td>
                </tr> -->
            </table>
            </form>
        </div>
    </div>
    
     <div id="imgTxlt" title="图片窗口"  style="width: 350px; height: 150px;">
           <div style="padding: 10px 10px 20px 10px;">
            <form id="imgFormt" method="post" enctype="multipart/form-data" >
            <input  name="compositeId" type="hidden" />
            <input  name="type" type="hidden" value=1 />
            <table>
                 <tr>
                    <td>  图片：</td>
                    <td> <input type="file" name="file" /></td>
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
    
    
</body>
 </html>