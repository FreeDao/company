$(function(){
	/*$.ajax({ url: "dataExchange/getMarketList.action?&pageNo=1&pageNum=12&cityId=18", context: document.body, success: function(){
        $(this).addClass("done");
      }});*/
	
	/*alert($(window).height());
	alert($(window).height()-82);
	alert($(document).height());
	alert($(document).height()-82);*/
	
	var cityId = getQueryStringByName("cityId");
	
	$.ajax({
		async: false,
		type: "get",
		url: "../dataExchange/getCity.action",
		beforeSend: function(XMLHttpRequest){
			//ShowLoading();
		},
		success: function(data, textStatus){
			var json = eval("("+data+")");
			$.each(json.results,function(index,item){
				if( null != cityId && "" != cityId ){
					if(item.id == cityId){
						$("#city").append("<option value="+item.id+" selected=selected >"+item.cityName+"</option>");
					}else{
						$("#city").append("<option value="+item.id+" >"+item.cityName+"</option>");
					}
				}else{
					if(ILData[2].indexOf(item.cityName) != -1){
						$("#city").append("<option value="+item.id+" selected=selected >"+item.cityName+"</option>");
						cityId = item.id;
					}else{
						$("#city").append("<option value="+item.id+" >"+item.cityName+"</option>");
					}
				}
			});
		},
		complete: function(XMLHttpRequest, textStatus){
			//HideLoading();
		},
		error: function(){
			//请求出错处理
		}
	});
	
	if( null == cityId || "" == cityId ){
		cityId = 10;
	}
	
	
	var header_search_length = $(".header").height() + $(".search").height();
	var temp_height = $(window).height() - 10 - header_search_length;
	var lineNum = parseInt(temp_height/81);
	
	var temp = $(window).width();
	if(temp > 700){
		temp = 700;
	}
	var rowNum = parseInt(temp/75-1);
	var temp_width = temp - 75*(rowNum);
	
	//$(".swipeLine a").css("margin-left",temp_width/(temp/75-1));

	$.ajax({
		type: "get",
		url: "../dataExchange/getMarketList.action?&pageNo=1&pageNum=50&cityId="+cityId,
		beforeSend: function(XMLHttpRequest){
			//ShowLoading();
		},
		success: function(data, textStatus){
			$(".bg").hide();
			$(".loadingBg").hide();
			var json = eval("("+data+")");
			var total = lineNum*rowNum;
			var sign = 1;
			var temp_id= "swipeLine"+sign;
			var i = 1;
			if(json.countNum != 0){
				$("#position").append("<li class='cur'></li>");
			}
			$.each(json.results,function(index,item){
				if(i <= total){
					$("#"+temp_id).append("<div class='imgCell' style='margin-left:"+temp_width/(rowNum+1)+"px;'><a href='list.html?type="+encodeURI(item.type)+"&typeId="+item.id+"&cityId="+item.cityId+"' ><label >"+item.type+"</label><img id='tete' src='img/circle.png' width='75'/></a><div>");
					i++;
				}else{
					sign++;
					temp_id = "swipeLine"+sign;
					i= 2;
					$(".swipe-wrap").append("<div class="+temp_id+" id="+temp_id+" ><div class='imgCell' style='margin-left:"+temp_width/(rowNum+1)+"px;'><a href='list.html?type="+encodeURI(item.type)+"&typeId="+item.id+"&cityId="+item.cityId+"' ><label >"+item.type+"</label><img id='tete' src='img/circle.png' width='75'/></a></div></div>");
					$("#position").append("<li ></li>");
				}
			});
			
			$("#temp").remove();
			/*$("#test").html(data);*/

			/*$(".ajax.ajaxResult").html("");
			$("item",data).each(function(i, domEle){
				$(".ajax.ajaxResult").append("<li>"+$(domEle).children("title").text()+"</li>");
			});*/
			
			
			var bullets = document.getElementById('position').getElementsByTagName('li');
			var banner = Swipe(document.getElementById('mySwipe'), {
				/* auto: 2000, */
				continuous: true,
				disableScroll:false,
				callback: function(pos) {
					var i = bullets.length;
					while (i--) {
					  bullets[i].className = ' ';
					}
					bullets[pos].className = 'cur';
				}
			});
			
		},
		complete: function(XMLHttpRequest, textStatus){
			//HideLoading();
		},
		error: function(){
			//请求出错处理
		}
});
	
	
	/*$.get("dataExchange/getMarketList.action?&pageNo=1&pageNum=12&cityId=18", function(result){
	    $("div").html(result);
	  });*/
})();
