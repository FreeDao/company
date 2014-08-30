	var datagrid;
    var editWindow;
    var searchWin;
$(function(){
//表格开始
	 datagrid=$('#datagrid').datagrid({
        title: '官方抽奖',
        iconCls: 'icon-save',
        methord: 'post',
        url: '../Backstage/news/selOfficialTwo.action',
        pageSize: 20,
        pageList:[5,10,15,20],
        frozenColumns: [[
	                { field: 'ck', checkbox: true }
				]],
        columns: [[
					{ field: 'title', title: '标题', width: 150,align:'center' },
					{ field: 'comment', title: '内容', width: 150,align:'center'},
					{ field: 'addtime', title: '添加时间', width: 150,align:'center'},
					{ field: 'lotteryAddtime', title: '开奖时间', width: 150,align:'center'},
					{ field: 'bife', title: '活动介绍', width: 150,align:'center' },
					{ field: 'rules', title: '活动规则', width: 150,align:'center' },
					{ field: 'prize', title: '活动奖品', width: 150,align:'center'}
				]],
        fit:true,
        pagination: true,
        rownumbers: true,
        fitColumns: true,
        singleSelect: false,
        toolbar: [{
            text: '新增',
            iconCls: 'icon-add',
            handler: addWindow
        },{
            text: '修改',
            iconCls: 'icon-edit',
            handler: editWindow
        },{
            text: '删除',
            iconCls: 'icon-remove',
            handler: deleteWindow
        }/*, '-',  {
            text: '查找',
            iconCls: 'icon-search',
            handler:openSearch
        }*/]
    });
    //表格结束
    
//add对话框开始
	 editWindow = $('#editWindow').dialog({
        closed: true,
        modal: true,
        toolbar: [{
            text: '保存',
            iconCls: 'icon-save',
            handler:saveInfo
        }, '-', {
            text: '关闭',
            iconCls: 'icon-no',
            handler: function () {
        	editWindow.dialog('close');
            }
        }]
    });
//add对话框结束
	 
	 
//查找窗口开始
 $('#btn-search,#btn-search-cancel').linkbutton();
   searchWin = $('#search').window({
        closed: true,
        modal: true
    });   
});
//查找窗口结束


//add开始
function addWindow(){
	$("#editForm").form('clear');
	editWindow.dialog('open');
	}

 function saveInfo(){
	  $('#editForm').form('submit', {
	        url:'../Backstage/news/addOfficial.action',
	        onSubmit: function () {
	            return $(this).form('validate');
	        },
	        success: function (data) {
	        	$('#editForm').form("clear");
	        	editWindow.dialog('close');
	        	datagrid.datagrid('reload');
	        }
	    });  
  }
//add结束

 //delete开始	
 function deleteWindow(){
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
	                    url: '../Backstage/news/delOfficial.action',
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
    function editWindow(){
    	//alert($("#rootQode"));.combobox('setValue', '001');
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
    		 editWindow.dialog('open');
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
	   var name= $('#name').val();
	   var youxiang= $('#youxiang').val();
	   datagrid=$('#datagrid').datagrid({
	        title: '通讯录',
	        iconCls: 'icon-save',
	        methord: 'post',
	        url: '../Backstage/news/selOfficialTwo.action',
	        queryParams: {name:name,youxiang:youxiang},
	        pageSize: 20,
	        pageList:[5,10,15,20],
	        frozenColumns: [[
		                { field: 'ck', checkbox: true }
					]],
	        columns: [[
						{ field: 'title', title: '标题', width: 150,align:'center' },
					{ field: 'comment', title: '内容', width: 150,align:'center'},
					{ field: 'addtime', title: '添加时间', width: 150,align:'center'},
					{ field: 'lotteryAddtime', title: '开奖时间', width: 150,align:'center'},
					{ field: 'bife', title: '活动介绍', width: 150,align:'center' },
					{ field: 'rules', title: '活动规则', width: 150,align:'center' },
					{ field: 'prize', title: '活动奖品', width: 150,align:'center'}
					]],
	        fit:true,
	        pagination: true,
	        rownumbers: true,
	        fitColumns: true,
	        singleSelect: false,
	        toolbar: [{
	            text: '删除',
	            iconCls: 'icon-remove',
	            handler: deleteWindow
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