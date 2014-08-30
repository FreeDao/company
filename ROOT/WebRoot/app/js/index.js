

/*function changeSort(imgs){
	var tempCards = new Array();
	for(var i = imgs.length - 1 ; i >= 0 ; i--){
		var ramdomIndex = Math.floor(Math.random()*i);
		tempCards[i] =  imgs[ramdomIndex];
		imgs[ramdomIndex] = imgs[i];
		imgs.length--;
	}
	return tempCards;
}*/


function init(){
//	var tempImgs = ["img/1.png","img/2.png","img/3.png","img/4.png","img/5.png","img/6.png","img/2.png","img/5.png"];
	
//	var cardImgs = changeSort(tempImgs);

	
	var screenWidth = document.body.clientWidth;
	if(screenWidth > 640){
		screenWidth = 640;
	}

	
	var imgWidth = screenWidth/5;
	
	var marginWidth = screenWidth - imgWidth*4;
	
	var imgs = document.getElementsByTagName("img");
	
	var divs = document.getElementsByTagName("div");
	
	var imgHeight;
	for(var index in imgs){
		if(imgs[index].className == "one"  ){
			imgs[index].width = imgWidth;
			imgHeight = imgs[index].height;
		}
	}
	
	for(var index in divs){
		if(divs[index].className == "rq" ){
			divs[index].style.width = imgWidth+"px";
			divs[index].style.height = imgHeight+"px";
			divs[index].style.marginLeft = marginWidth/6+"px";
			/*var img = document.createElement("img");
			img.src = cardImgs[i++];
			img.width = imgWidth;
//			img.style.paddingTop = (imgHeight/2-23)+"px";
//			img.style.paddingRight = (imgWidth/2-18)+"px";
			img.className = "two";
			divs[index].appendChild(img);*/
		}
	}
	
	var share = document.getElementById("share");
	share.style.width = imgWidth*2+"px";
	share.style.margin = "0 auto";
	
	var shareImg = document.getElementById("shareImg");
	shareImg.width = imgWidth*2;
	shareImg.style.marginTop = "-5px";
	/*var cards = document.getElementsByTagName("div");
	for(var index in cards){
		if(cards[index].className == "rq"){
			
		}
	}*/
}