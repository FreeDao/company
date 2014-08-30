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
  <script src="book/city/account.js" type="text/javascript"></script>
   </head>
   <body >
       <div id="datagrid"></div>  
           <div id="editTxl" title="增加窗口"  style="width: 500px; height: 230px;">
           <div style="padding: 20px 10px 40px 45px;">
            <form id="editForm" method="post" enctype="multipart/form-data" >
            <input  name="id" type="hidden" />
            <table>
                <tr id="userName">
                    <td>  账号：</td>
                    <td> <input  name="userName"  class="easyui-validatebox"     data-options="required:true" style="width: 300px;" /></td>
                </tr>
                <tr id="passWord">
                    <td>  密码：</td>
                    <td> <input type="password" name="passWord" class="easyui-validatebox"  data-options="required:true"  style="width: 300px;" /></td>
                </tr>
                <tr>
                    <td> 城市</td>
                    <td>
                    	<select id="city" name="cityId" class="easyui-combobox" data-options="required:true" style="width: 300px;" editable="false">
                    		
                    	</select>
                    </td>
                </tr>
            </table>
            </form>
        </div>
    </div>
    
        <div style="text-align: center; padding: 5px;">
            <a href="javascript:void(0)" onclick="searchTxl()" id="btn-search" icon="icon-ok">确定</a>
            <a href="javascript:void(0)" onclick="closeSearchWindow()" id="btn-search-cancel" icon="icon-cancel">取消</a>
        </div>
        
        <!-- <div id="imgDialog" class="easyui-dialog" title="图片管理" style="width:400px;height:400px;" >    
			<table id="tt" class="easyui-datagrid"     
			        url="datagrid24_getdata.php" toolbar="#tb"    
			        iconCls="icon-save"    
			        rownumbers="true">    
			    <thead>    
			        <tr>    
			        	<th field="ck" ></th>    
			            <th field="itemid" width="80">图片</th>    
			            <th field="productid" width="80">地址</th>  
			            <th field="productid" width="80">名称</th>    
			        </tr>    
			    </thead>    
			</table> 		    
		</div>
        
        <div id="tb">    
		    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:alert('Add')">Add</a>    
		    <a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="javascript:alert('Cut')">Cut</a>    
		    <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:alert('Save')">Save</a>    
		</div>   -->
    </div> 
    <script type="text/javascript">
    
    	/* function imgManager(t){
    		$("#imgDialog").dialog('open');
		} */
		
    	function erji()
    	{
    		var id = document.getElementById("shopId").value;
		    	var url2 = "../Backstage/news/selShopIdE.action";
		    	
		    		id = document.getElementById("shopId").value;
				$.getJSON(url2, {"ranum":Math.random(),"Yid":id},function(data)
			{
					var cc='';
				for(var i = 0;data.msg.length>i;i++)
				{
					cc+='<option value="'+data.msg[i].id+'">'+data.msg[i].name+'</option>';
				}
				$("#shopIdTwo").html(cc);
			});
    	}
    	function erji11()
    	{
    		var id = document.getElementById("shopId").value;
		    	var url2 = "../Backstage/news/selShopIdE.action";
		    		id = document.getElementById("shopId1").value;
				$.getJSON(url2, {"ranum":Math.random(),"Yid":id},function(data)
			{
					var cc='';
				for(var i = 0;data.msg.length>i;i++)
				{
					cc+='<option value="'+data.msg[i].id+'">'+data.msg[i].name+'</option>';
				}
				$("#shopIdTwo1").html(cc);
			});
    	}
    </script>
</body>
 </html>