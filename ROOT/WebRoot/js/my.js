function check(){
	var isPass = false;
	//alert(123);
	for( var i = 0 ; i < arguments.length ; i+=2 ){
		//alert($("#"+arguments[i]).val());
		if($("#"+arguments[i]).val() == "" || $("#"+arguments[i]).val() == null){
				$("#"+arguments[i]).closest(".control-group").addClass("error");
				$("#"+arguments[i]).next("span").html(arguments[i+1]);
				isPass = true;
		}
	}
	if(isPass){
		return;
	}
	$(".modal-backdrop").remove();
	$("#modal").hide();
	$("form").submit();
}

$('body').on('hidden', '.modal', function () {
	$(this).removeData('modal');
	});