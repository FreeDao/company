/**
 * 检查对id是否为空，第一个参数为form的id用于提交表单
 */
function validSubmit(){
	for(var i = 2 ; i < arguments.length ; i++){
		if($("#"+arguments[i]).val() == ""){
			$("#"+arguments[i]).next().css("display","inline-block");
			$("#"+arguments[i]).focus();
			return;
		}
	}
	$(arguments[0]).attr("disabled","disabled");
	$("#"+arguments[1]).submit();
	
}

/**
 * 检查输入的只能为数字，sign为true代表是整数，false代表非整数
 * @param obj this
 * @param sign true 或者 false
 */
function validNumber(obj,sign){   
	
	//sign为ture代表整数，false代表非整数
	if(sign){
		obj.value=obj.value.replace(/[^\d]/g,'');
		return;	
	}
		
	obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  

	 obj.value = obj.value.replace(/^\./g,"");  //验证第一个字符是数字而不是. 

	  obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的.   

	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");

	}