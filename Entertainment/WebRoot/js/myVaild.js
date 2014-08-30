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