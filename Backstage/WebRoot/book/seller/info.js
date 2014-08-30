$(function(){
	
	$('#infoForm').form('load','../Backstage/news/selSellerInfo.action');
	
	 $('#typeId').combobox({   
		      url:'../Backstage/news/selMarketType.action',   
		      valueField:"id",   
		      textField:"type"  
	 });  
	 
	 $.post("../Backstage/news/selSellerLogo.action",null,function(result){
		 	$("#logo").attr("src",result);
		  });
	 
});
