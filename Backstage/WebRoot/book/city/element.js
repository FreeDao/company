	var datagrid;
    var searchWin;
    var editWin;
$(function(){
	
	$('#city').combobox({   
	      url:'../Backstage/news/selVillageOrCity.action',   
	      valueField:"id",   
	      textField:"cityName",
	      onSelect:function(record){//绑定行别二级联动
	    	  var id = record.id;
	    	  $('#villageId').combobox('reload','../Backstage/news/selVillageOrId.action?villageId='+id);
	    	  $('#villageId').combobox('setValue','');
	          }
}); 
	$('#villageId').combobox({   
	      //url:'../Backstage/news/selShopId2.action',   
	      valueField:"id",   
	      textField:"name"
}); 
	
//表格开始
	 datagrid=$('#datagrid').datagrid({
        title: '发布物业',
        iconCls: 'icon-save',
        methord: 'post',
        url: '../Backstage/news/selCustom.action',
        pageSize: 20,
        pageList:[5,10,15,20],
        frozenColumns: [[
	                { field: 'ck', checkbox: true }
				]],
        columns: [[
					{ field: 'name', title: '名称', width: 150,align:'center' },
					{ field: 'iphone', title: '联系电话', width: 150,align:'center'},
					{ field: 'addtime', title: '添加时间', width: 150,align:'center'}
				]],
        fit:true,
        pagination: true,
        rownumbers: true,
        fitColumns: true,
        singleSelect: false,
        toolbar: [ {
            text: '新增',
            iconCls: 'icon-add',
            handler:newTxl
        }, '-',{
            text: '修改',
            iconCls: 'icon-edit',
            handler:editTxl
        } , '-', {
            text: '删除',
            iconCls: 'icon-remove',
            handler: deleteTxl
        }/*, '-',  {
            text: '查找',
            iconCls: 'icon-search',
            handler:openSearch
        }*/]
    });
    //表格结束
    
	 
	//edit对话框开始
	 editWin = $('#editTxl').dialog({
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
	            	editWin.dialog('close');
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
	editWin.dialog('open');
	}

 function saveTxl(){
	  $('#editForm').form('submit', {
	        url:'../Backstage/news/addCustom.action',
	        onSubmit: function () {
	            return $(this).form('validate');
	        },
	        success: function (data) {
	        	$('#editForm').form("clear");
	        	editWin.dialog('close');
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
	                    url: '../Backstage/news/delCustom.action',
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
    function editTxl(){
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
    		 editWin.dialog('open');
             $("#editForm").form("load", row);
    	  }
    }
  
	}
    
	//edit开始	
	
	//search开始	
   function openSearch(){
   searchWin.window('open');
   	}
   
   function searchTxl(){
	   var title= $('#title').val();
	   var conent= $('#conent').val();
	   datagrid=$('#datagrid').datagrid({
	        title: '通讯录',
	        iconCls: 'icon-save',
	        methord: 'post',
	        url: '../Backstage/news/selNewsDetailBife.action',
	        queryParams: { title: title,conent:conent},
	        pageSize: 20,
	        pageList:[5,10,15,20],
	        frozenColumns: [[
		                { field: 'ck', checkbox: true }
					]],
	        columns: [[
	{ field: 'title', title: '标题', width: 150,align:'center' },
					{ field: 'addtime', title: '添加时间', width: 150,align:'center'},
					{ field: 'author', title: '作者', width: 150,align:'center'},
					{ field: 'conent', title: '内容', width: 150,align:'center'}
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
