var productImgs = new Array();
var briefImgs = new Array();

function detailInit(){
	
	/*var title = document.getElementById("title");
	var logo = document.getElementById("logo");
	var preferen = document.getElementById("preferen");
	var phone = document.getElementById("phone");
	var address = document.getElementById("address");
	var product = document.getElementById("product");
	
	var brief = document.getElementById("brief");
	
	
	var titleVal = decodeURI(getQueryStringByName("title"));
	var logoVal = getQueryStringByName("logo");
	var preferenVal = getQueryStringByName("preferen")+"%";
	var phoneVal = getQueryStringByName("phone");
	var addressVal = decodeURI(getQueryStringByName("address"));
	var productVal = decodeURI(getQueryStringByName("product"));
	var briefVal = decodeURI(getQueryStringByName("brief"));
	
	//电话
	var phoneParent = phone.parentNode; 
	phoneParent.href="tel:"+phoneVal;
	
	title.innerHTML = titleVal;
	logo.src = logoVal;
	preferen.innerHTML = preferenVal;
	phone.innerHTML= phoneVal;
	address.innerHTML= addressVal;
	product.innerHTML= productVal;
	brief.innerHTML= briefVal;
	*/
	//alert("bodyClientHeight:"+document.body.clientHeight+"\n,htmlClientHeight:"+document.documentElement.clientHeight+"\n,bodyScrollHeight"+document.body.scrollHeight+"\n,htmlScrollHeight"+document.documentElement.scrollHeight+"\n,scrren:"+window.screen.height);

	var temp_height = Math.min(document.body.clientHeight,document.documentElement.clientHeight);

	var temp_content = document.getElementById("content");
	
	temp_content.style.height = (temp_height-127)+"px";
	
	
	var productImg = document.getElementById("productImgs");
	var briefImg = document.getElementById("briefImgs");
	
	productImgs = productImgsVal.split(",");
	
	briefImgs = briefImgsVal.split(",");
	
	var temp = 1;
	 	
	for(var index in productImgs){
		
		/*productImg.appendChild(index);*/
		var img = document.createElement("img");
		img.src = productImgs[index];
		img.className = "imgs";
		productImg.appendChild(img);
		
		temp++;
		
		if(temp > 4){
			temp = 1;
			break;
		}
	}
	
	for(var index in briefImgs){
		/*productImg.appendChild(index);*/
		var img = document.createElement("img");
		img.src = briefImgs[index];
		img.className = "imgs";
		briefImg.appendChild(img);
		
		temp++;
		
		if(temp > 4){
			break;
		}
	}
	
}

function showImg(sign) {
	
	var swipeDiv = document.getElementById("swipe-wrap");

	if(sign == 0){
		
		for(var index in productImgs){
			
			var div = document.createElement("div");
			var img = document.createElement("img");
			img.src = productImgs[index];
			img.className = "bigImgs";
			div.appendChild(img);
			swipeDiv.appendChild(div);
			/*var position = document.getElementById("position");
			var li = document.createElement("li");
			position.appendChild(li);*/
			
		}
		
	}else{
			
		for(var index in briefImgs){
			
			var div = document.createElement("div");
			var img = document.createElement("img");
			img.src = briefImgs[index];
			img.className = "bigImgs";
			div.appendChild(img);
			swipeDiv.appendChild(div);
			/*var position = document.getElementById("position");
			var li = document.createElement("li");
			position.appendChild(li);*/
			
		}
		
	}
		
//	var bullets = document.getElementById('position').getElementsByTagName('li');
	var banner = Swipe(document.getElementById('mySwipe'), {
		//auto: 2000, 
		continuous: true,
		disableScroll:false/*,
		callback: function(pos) {
			var i = bullets.length;
			while (i--) {
			  bullets[i].className = ' ';
			}
			bullets[pos].className = 'cur';
		}*/
	});
	
	
	var temp_content = document.getElementById("content");
	temp_content.style.overflow = "hidden";
	
	var imgChange = document.getElementById("imgChange");
	imgChange.style.top = 0;
	imgChange.style.bottom = 0;
}

function hideImg(){
	var swipeDiv = document.getElementById("swipe-wrap");
	swipeDiv.parentNode.removeChild(swipeDiv);
	var div = document.createElement("div");
	div.className="swipe-wrap";
	div.id = "swipe-wrap";
	div.style.width = "100%";
	var myswipe = document.getElementById("mySwipe");
	myswipe.appendChild(div);
	var temp_content = document.getElementById("content");
	temp_content.style.overflow = "visible";
	/*var imgChange = document.getElementById("imgChange");
	imgChange.style.bottom = screen.height + "px";
	var content = document.getElementById("content");
	content.style.height = "100%";*/
//	imgChange.style.bottom = "1px";
}
