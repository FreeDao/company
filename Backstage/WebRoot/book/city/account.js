	var datagrid;
    var searchWin;
    var infoWin;
$(function(){
	//$('#imgDialog').dialog('close');
	
	$('#city').combobox({   
	      url:'../Backstage/news/selVillageOrCity.action',   
	      valueField:"id",   
	      textField:"cityName"
	}); 
	
	 //表格开始
	 datagrid=$('#datagrid').datagrid({
        title: '城市账号',
        iconCls: 'icon-save',
        methord: 'post',
        url: '../Backstage/news/selCityUser.action',
        pageSize: 20,
        pageList:[5,10,15,20],
        frozenColumns: [[
	                { field: 'ck', checkbox: true }
				]],
        columns: [[
                   	{ field: 'userName', title: '账号', width: 150,align:'center' },
                   	//{ title: '内容图片', width: 150,align:'center',formatter:function(val,row){return "<a style='cursor: pointer;color:blue;' onclick='imgManager("+row.id+")'>图片管理</a>";} },
					{ field: 'cityName', title: '城市', width: 150,align:'center' },
					{ field: 'cityWord', title: '城市关键字', width: 150,align:'center' },
					{ field: 'addTime', title: '添加时间', width: 150,align:'center'}
				]],
        fit:true,
        pagination: true,
        rownumbers: true,
        fitColumns: true,
        singleSelect: false,
        toolbar: [{
            text: '新增',
            iconCls: 'icon-add',
            handler:newTxl
        }, '-', {
            text: '删除',
            iconCls: 'icon-remove',
            handler: deleteTxl
        }, '-', {
            text: '修改',
            iconCls: 'icon-edit',
            handler:edit
        }/*, '-',  {
            text: '查找',
            iconCls: 'icon-search',
            handler:openSearch
        }*/]
    });
    //表格结束
    
	//edit对话框开始
	 infoWin = $('#editTxl').dialog({
	        closed: true,
	        modal: true,
	        toolbar: [{
	            text: '保存',
	            iconCls: 'icon-save',
	            handler:saveTxl
	        }, '-', {
	            text: '关闭',
	            iconCls: 'icon-no',
	            handler: function () {
	        	infoWin.dialog('close');
	            }
	        }]
	    });
	 
	//edit对话框结束
	 
//查找窗口开始
 $('#btn-search,#btn-search-cancel').linkbutton();
   searchWin = $('#search').window({
        closed: true,
        modal: true
    });   
});
//查找窗口结束

//add开始
function newTxl(){
	$("#editForm").form('clear');
	$("#userName").css("display","");
	$("#passWord").css("display","");
	infoWin.dialog('open');
	}

 function saveTxl(){
	  $('#editForm').form('submit', {
	        url:'../Backstage/news/addOrUpdateCityUser.action',
	        onSubmit: function () {
	            return $(this).form('validate');
	        },
	        success: function (data) {
	        	$('#editForm').form("clear");
	        	infoWin.dialog('close');
	        	datagrid.datagrid('reload');
	        }
	    });  
  }
//add结束

 //delete开始	
 function deleteTxl(){
	  var rows = datagrid.datagrid('getSelections');
	  var num = rows.length;
	  var ids = [];
	  for (var i = 0; i < rows.length; i++) {
	        ids.push(rows[i].id);
	    }
//批量删除开始
	  if (num >0) { 
		  $.messager.confirm('提示信息', '您确认要删除吗?', function (data) {
	            if (data) {
	                $.ajax({
	                    url: '../Backstage/news/delCityUser.action',
	                    type: 'post',
	                    data: {items: ids.join(',')},	  
	                    success: function (data) {
	                    datagrid.datagrid('reload');
	                    }
	                });
	            }
	        });
//批量删除结束
	   } 	
	  else{
		  Msgslide('请先选择要删除的记录!');
	  }
	 
 }

    //delete开始	

//edit开始	
    function edit(){
    $("#userName").css("display","none");
    $("#passWord").css("display","none");
	var rows = datagrid.datagrid('getSelections');
	var num = rows.length;
    if (num == 0) {
        Msgslide('请选择一条记录进行修改!');
    }
    else if (num > 1) {
        Msgslide('您选择了多条记录,只能选择一条记录进行修改!'); 
    }
    else {
    	var row = datagrid.datagrid('getSelected');
    	  if (row) {
    		  $.getJSON("../Backstage/news/selVillageById.action", {"villageId":row.villageId},function(data)
    					{
    	    			  $('#city').combobox('setValue',data.village.cityId);
    	    			  $('#villageId').combobox('reload','../Backstage/news/selVillageOrId.action?villageId='+data.village.cityId);
    	    			});
    		 infoWin.dialog('open');
             $("#editForm").form("load", row);
    	  }
    }
  
	}
    
	//search开始	
   function openSearch(){
   searchWin.window('open');
   	}
   
   function searchTxl(){
	   var name= $('#name').val();
	   var shop= $('#shop').val();
	   datagrid=$('#datagrid').datagrid({
	        title: '通讯录',
	        iconCls: 'icon-save',
	        methord: 'post',
	        url: '../Backstage/news/selSeller.action',
	        queryParams: { name: name,shop:shop},
	        pageSize: 20,
	        pageList:[5,10,15,20],
	        frozenColumns: [[
		                { field: 'ck', checkbox: true }
					]],
	        columns: [[
{ field: 'titile', title: '标题', width: 150,align:'center' },
{ field: 'phone', title: '电话', width: 150,align:'center'},
{ field: 'brief', title: '简介', width: 150,align:'center'},
{ field: 'cityAdd', title: '别的地方的价格', width: 150,align:'center' },
{ field: 'postage', title: '邮费', width: 150,align:'center' },
{ field: 'price', title: '价格', width: 150,align:'center'},
{ field: 'address', title: '地址', width: 150,align:'center' },
{ field: 'logo', title: '图片', width: 150,align:'center',formatter:function(val){return '<image src="'+val+'" width="50" height = 50/>';}},
					]],
	        fit:true,
	        pagination: true,
	        rownumbers: true,
	        fitColumns: true,
	        singleSelect: false,
	        toolbar: [{
	            text: '新增',
	            iconCls: 'icon-add',
	            handler:newTxl
	        } , '-', {
	            text: '删除',
	            iconCls: 'icon-remove',
	            handler: deleteTxl
	        }, '-', {
	            text: '修改',
	            iconCls: 'icon-edit',
	            handler:edit
	        }, '-',  {
	            text: '查找',
	            iconCls: 'icon-search',
	            handler:openSearch
	        }]
	    });
	   
	   searchWin.window('close');
	   $('#searchForm').form("clear");
	  
	   
   }
    //search结束

	//消息提醒
	function Msgslide(msg) {
    $.messager.show({
        title: '提示',
        msg: msg,
        timeout: 3000,
        showType: 'slide'
    });
}
//查询关闭按钮
function closeSearchWindow() {
    searchWin.window('close');
}