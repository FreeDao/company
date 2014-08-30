var locaLng,locaLat; 
var pageNow = 1,limit = 15;
if (navigator.geolocation)
{
  navigator.geolocation.getCurrentPosition(showPosition,function(){
	  alert("位置获取错误!请检查你的设备是否允许访问位置信息！");
	  showPosition(null);
  }
  ,{
      // 指示浏览器获取高精度的位置，默认为false
      enableHighAcuracy: true,
      // 指定获取地理位置的超时时间，默认不限时，单位为毫秒
      timeout: 5000,
      // 最长有效期，在重复获取地理位置时，此参数指定多久再次获取位置。
      maximumAge: 3000
  }
  );
}
else{
	alert("你的浏览器不支持获取地址！");
}

/*function handlerError(error){
	switch(error.code) {
	      case error.TIMEOUT:
	          showError("获取地理位置超时，请重试!");
	          break;
	      case error.POSITION_UNAVAILABLE:
	          showError('抱歉!无法获取到你当前位置！');
	          break;
	      case error.PERMISSION_DENIED:
	          showError('请允访问你的位置.');
	          break;
	      case error.UNKNOWN_ERROR:
	          showError('未知错误!');
	          break;
	}
}*/

function showPosition(position)
  {
	if( null == position ){
		locaLng = 0;
		locaLat = 0;
	}else{
		locaLng = position.coords.longitude;
		locaLat = position.coords.latitude;
	}
	
	var title = getQueryStringByName("type");
	var typeId = getQueryStringByName("typeId");
	var cityId = getQueryStringByName("cityId");

	var url ="../dataExchange/getSellerList.action?&pageNo="+ (pageNow++) +"&pageNum="+limit+"&sellerId=-1&cityId="+cityId+"&log="+locaLng+"&dim="+locaLat;
	if( null != typeId && "" != typeId ){
		url += "&typeId="+ typeId;
	}
	
	$("header h3").html(decodeURI(title));

	$.ajax({
				type : "get",
				url : url,
				beforeSend : function(XMLHttpRequest) {
					// ShowLoading();
				},
				success : function(data, textStatus) {
					$(".bg").hide();
					$(".loadingBg").hide();
					var json = eval("(" + data + ")");
					$.each(
						json.results,
						function(index, item) {
							$("#dataArea")
									.append(
											"<dt onclick=\"postForm({'type':\'"
													+ encodeURI(title)
													+ "\','typeId':\'"
													+ typeId
													+ "\','cityId':\'"
													+ cityId
													+ "\','logo':\'"
													+ item.logo
													+ "\','title':\'"
													+ encodeURI(item.titile)
													+ "\','level':\'"
													+ item.level
													+ "\','preferen':\'"
													+ item.preferential
													+ "\','phone':\'"
													+ item.phone
													+ "\','address':\'"
													+ encodeURI(item.address)
													+ "\','product':\'"
													+ encodeURI(item.name)
													+ "\','productImgs':\'"
													+ item.productImgs
													+ "\','brief':\'"
													+ encodeURI(item.brief)
													+ "\','briefImgs':\'"
													+ item.sellerImgs
													+ "\','sellerId':\'"
													+ item.id
													+ "\','log':\'"
													+ item.log
													+ "\','dim':\'"
													+ item.dim
													+ "\'})\" ><div class='lineDiv' ><div class='imgDiv' ><img src='"
													+ item.logo
													+ "' width='81' height='62' /></div> <div class=contentDiv ><div class='titleDiv' ><h4>"
													+ item.titile
													+ "</h4><span><img src='img/position.png' width='7' />"+GetDistance(locaLat,locaLng,item.dim,item.log)+"km</span></div><div class='quotation' ><img src='img/arrow_right.png' width='4' /><span style='float:left;' >"
													+ item.brief
													+ "</span></div><div class='infoDiv' ><a href='tel:"
													+ item.phone
													+ "' class='phoneSpan' ><span>"
													+ item.phone
													+ "</span></a><span class='sign' >优惠:<span>"
													+ item.preferential
													+ "%</span></span></div></div></div> </div> </dt>");
						});
						
						if(json.results.length == 0){
							$("#dataArea").append("<dt style='text-align:center;color:#aaa;border-bottom:0px;' ><h2>暂无数据</h2></dt>");
						}else{
							var more = document.getElementById("more");
							more.style.display = "block";
						}
						
				},
				complete : function(XMLHttpRequest, textStatus) {
					// HideLoading();
				},
				error : function() {
					// 请求出错处理
				}
			});

	/*	var iscrollObj = iscrollAssist.newVerScrollForPull($('#wrapper'),
				pulldownAction, pullupAction);
		iscrollObj.refresh();*/
	
  }

function postForm(data) {
	var myForm = document.createElement("form");
	myForm.method = "post";
	myForm.action = "appDetail.action";
	for ( var key in data) {
		var myInput = document.createElement("input");
		myInput.name = key;
		myInput.value = data[key];
		myInput.type = "hidden";
		myForm.appendChild(myInput);
	}
	document.body.appendChild(myForm);
	myForm.submit();
}

function Rad(d){
    return d * Math.PI / 180.0;//经纬度转换成三角函数中度分表形式。
 }
 //计算距离，参数分别为第一点的纬度，经度；第二点的纬度，经度
 function GetDistance(lat1,lng1,lat2,lng2){
	 var radLat1 = Rad(lat1);
	  var radLat2 = Rad(lat2);
	  var a = radLat1 - radLat2;
	  var b = Rad(lng1) - Rad(lng2);
	  var s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
	  Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
	  s = s *6378.137 ;// EARTH_RADIUS;
	  s = Math.round(s * 10000) / 10000; //输出为公里
	  //s=s.toFixed(4);
	  //alert(s.toString().length);
	  if(s.toString().length > 4){
		  return s.toString().substr(0,4);
	  }else{
		  return s.toFixed(2);
	  }
 }