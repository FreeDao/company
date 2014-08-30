	var datagrid;
    var searchWin;
    var infoWin;
    var imgWin;
$(function(){
	//$('#imgDialog').dialog('close');
	
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
		  url:'../Backstage/news/selLoginUserVillage.action',
	      valueField:"id",   
	      textField:"name"
	}); 
	$('#typeId').combobox({   
		  url:'../Backstage/news/selMarketType.action',
	      valueField:"id",   
	      textField:"type"
	}); 
	
	 //表格开始
	 datagrid=$('#datagrid').datagrid({
		view: detailview,  
	    detailFormatter:function(index,row){  
	        return '<div style="padding:2px"><table id="ddv-' + index + '"></table></div>';  
	    },  
	    onExpandRow: function(index,row){
	        $('#ddv-'+index).datagrid({  
	            url:'../Backstage/news/selImg.action?type=1&id='+row.id,  
	            fitColumns:true,  
	            singleSelect:false,  
	            rownumbers:true,  
	            //fit:true,
	            loadMsg:'',  
	            height:'auto', 
	            frozenColumns: [[
	         	                { field: 'ck', checkbox: true }
	         				]],
	            columns:[[  
	                {field:'imgUrl',title:'图片',width:100,formatter:function(val){return '<image src="'+val+'" width="50" height = 50/>';}}
	            ]],
	            toolbar: [{
	                text: '新增',
	                iconCls: 'icon-add',
	                handler:function(){
	                	imgWin.dialog('open');
	                	$("#imgForm").form("clear");
	                	$("#imgForm").form("load", {compositeId:row.id,type:1});
	                }
	            } , '-', {
	                text: '删除',
	                iconCls: 'icon-remove',
	                handler: function(){
	                	  var rows = $('#ddv-'+index).datagrid('getSelections');
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
		              	                    url: '../Backstage/news/delImg.action',
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
	            }],
	            onResize:function(){  
	                $('#datagrid').datagrid('fixDetailRowHeight',index);  
	            },  
	            onLoadSuccess:function(){  
	                setTimeout(function(){  
	                    $('#datagrid').datagrid('fixDetailRowHeight',index);  
	                },0);  
	            }
	        });  
	        $('#datagrid').datagrid('fixDetailRowHeight',index);  
	    },
        title: '商户',
        iconCls: 'icon-save',
        methord: 'post',
        url: '../Backstage/news/selSellerByType.action?type=0',
        pageSize: 20,
        pageList:[5,10,15,20],
        frozenColumns: [[
	                { field: 'ck', checkbox: true }
				]],
        columns: [[
                   	{ field: 'id', title: '账号', width: 150,align:'center',formatter:function(val){return 100000+val;} },
                   	//{ title: '内容图片', width: 150,align:'center',formatter:function(val,row){return "<a style='cursor: pointer;color:blue;' onclick='imgManager("+row.id+")'>图片管理</a>";} },
					{ field: 'titile', title: '标题', width: 150,align:'center' },
					{ field: 'name', title: '产品简介', width: 150,align:'center'},
					{ field: 'brief', title: '公司简介', width: 150,align:'center'},
					{ field: 'recruitment', title: '招聘信息', width: 150,align:'center' },
					{ field: 'phone', title: '联系电话', width: 150,align:'center'},
					{ field: 'logo', title: 'logo', width: 150,align:'center',formatter:function(val){return '<image src="'+val+'" width="50" height = 50/>';} },
					{ field: 'addtime', title: '添加时间', width: 150,align:'center'}
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
	 
	 //图片对话框
	 imgWin = $('#imgTxl').dialog({
	        closed: true,
	        modal: true,
	        toolbar: [{
	            text: '保存',
	            iconCls: 'icon-save',
	            handler:function(){
	            	$('#imgForm').form('submit', {
	        	        url:'../Backstage/news/addImg.action',
	        	        success: function (data) {
	        	        	//$('#editForm').form("clear");
	        	        	imgWin.dialog('close');
	        	        	datagrid.datagrid('reload');
	        	        }
	        	    }); 
	            }
	        }, '-', {
	            text: '关闭',
	            iconCls: 'icon-no',
	            handler: function () {
	        	imgWin.dialog('close');
	            }
	        }]
	    });

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
	infoWin.dialog('open');
	}

 function saveTxl(){
	  $('#editForm').form('submit', {
	        url:'../Backstage/news/addSeller.action',
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
	                    url: '../Backstage/news/delSeller.action',
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