	var datagrid;
    var infoWindow;
    var searchWin;
    var imgWindow;
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
	      //url:'../Backstage/news/selVillageList.action',   
	      valueField:"id",   
	      textField:"name",
	      defaultText:"请选择"
}); 
	$('#shopId').combobox({   
	      url:'../Backstage/news/selShopId1.action',   
	      valueField:"id",   
	      textField:"type",
	      defaultText:"请选择",
	      onSelect:function(record){//绑定行别二级联动
	    	  var id = record.id;
	    	  $('#shopIdTwo').combobox('reload','../Backstage/news/selShopId2.action?id='+id);
	    	  $('#shopIdTwo').combobox('setValue','');
	          }
}); 
	$('#shopIdTwo').combobox({   
	      //url:'../Backstage/news/selShopId2.action',   
	      valueField:"id",   
	      textField:"name",
	      defaultText:"请选择"
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
		                	imgWindow.dialog('open');
		                	$("#imgFormt").form("clear");
		                	$("#imgFormt").form("load", {compositeId:row.id,type:1});
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
			              	                    typeew: 'post',
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
        title: '添加商品',
        iconCls: 'icon-save',
        methord: 'post',
        url: '../Backstage/news/selSellerByType.action?type=1',
        pageSize: 20,
        pageList:[5,10,15,20],
        frozenColumns: [[
	                { field: 'ck', checkbox: true }
				]],
        columns: [[
               	{ field: 'titile', title: '标题', width: 150,align:'center' },
				{ field: 'brief', title: '内容', width: 150,align:'center'},
				{ field: 'phone', title: '联系电话', width: 150,align:'center'},
				{ field: 'otherPrice', title: '市场价格', width: 150,align:'center' },
				{ field: 'price', title: '卖价', width: 150,align:'center' },
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
            handler: addWindow
        },{
            text: '修改',
            iconCls: 'icon-edit',
            handler: editWindow
        },{
            text: '删除',
            iconCls: 'icon-remove',
            handler: deleteWindow
        }, '-',  {
            text: '查找',
            iconCls: 'icon-search',
            handler:openSearch
        }]
    });
    //表格结束
    
//add对话框开始
	 infoWindow = $('#editWindow').dialog({
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
        	infoWindow.dialog('close');
            }
        }]
    });
//add对话框结束
	 
	 //图片对话框
	 imgWindow = $('#imgTxlt').dialog({
	        closed: true,
	        modal: true,
	        toolbar: [{
	            text: '保存',
	            iconCls: 'icon-save',
	            handler:function(){
	            	$('#imgFormt').form('submit', {
	        	        url:'../Backstage/news/addImg.action',
	        	        success: function (data) {
	        	        	//$('#editForm').form("clear");
	        	        	imgWindow.dialog('close');
	        	        	datagrid.datagrid('reload');
	        	        }
	        	    }); 
	            }
	        }, '-', {
	            text: '关闭',
	            iconCls: 'icon-no',
	            handler: function () {
	        	imgWindow.dialog('close');
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
function addWindow(){
	$("#editForm").form('clear');
	infoWindow.dialog('open');
	}

 function saveInfo(){
	  $('#editForm').form('submit', {
	        url:'../Backstage/news/addOrUpdateCommodity.action',
	        onSubmit: function () {
	            return $(this).form('validate');
	        },
	        success: function (data) {
	        	$('#editForm').form("clear");
	        	infoWindow.dialog('close');
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
    	//alert(row.shopId + ","+row.shopIdTwo);
    	  if (row) {
    		  $.getJSON("../Backstage/news/selVillageById.action", {"villageId":row.villageId},function(data)
  					{
  	    			  $('#city').combobox('setValue',data.village.cityId);
  	    			  $('#villageId').combobox('reload','../Backstage/news/selVillageOrId.action?villageId='+data.village.cityId);
  	    			});
    		  $('#shopIdTwo').combobox('reload','../Backstage/news/selShopId2.action?id='+row.shopId);
    		 infoWindow.dialog('open');
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