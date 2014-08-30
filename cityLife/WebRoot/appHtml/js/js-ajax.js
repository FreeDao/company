var xmlhttp;
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }

/**author : Marck
 * js调用ajax
 * @param param {type,url,async,data,function_load,function_success}
 */
function js_ajax(param){
	
	xmlhttp.onreadystatechange=function()
	{
	if( xmlhttp.status == 404 ){
		alert("url无效！");
	}	
	if( xmlhttp.readyState < 4){
		param.load();
	}	
	if (xmlhttp.readyState==4 && xmlhttp.status==200)
	  {
		param.success(xmlhttp.responseText);
	  }
	};
	
	if(param.type == "get"){
		xmlhttp.open(param.type,param.url,param.async);
		xmlhttp.send();
	}else{
		xmlhttp.open(param.type,param.url,param.async);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		var temp = "" ;
		for(var index in param.data){
			temp += index+"="+param.data[index]+"&";
		}
		xmlhttp.send(temp);
	}
	
}
