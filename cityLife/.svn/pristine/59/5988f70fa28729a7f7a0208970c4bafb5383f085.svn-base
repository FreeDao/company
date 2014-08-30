/**
 * @author zyh
 */
(function(){
	if(!window.J){ window.J = {};}
	
	/**
	 * 得到obj
	 */
	function $(){
		var elements = [];
		for(var i=0; i<arguments.length; i++){
			var element = arguments[i];
			if(typeof element == 'string'){element = document.getElementById(element);}
			if(arguments.length == 1){
				return element;
			}
			elements.push(element);
		}
		return elements;		
	}
	window.J.$ = $;
	
	/**
	 * 失去焦点
	 * @param {Object} obj
	 */
	function onBlurText(obj){
		$(obj).onfocus = function(){
			if(this.value==this.defaultValue)this.value='';
		}
		$(obj).onblur = function(){
			if(this.value.length<1)this.value=this.defaultValue;
		}
	}
	window.J.onBlurText = onBlurText;
	
	function objNull(obj){
		if ($(obj).value.length < 1) {
			alert('aa');
			return false;
		}
	}
	window.J.objNull = objNull;
	
	
})();


///////////////////////////////////////////////////////////////////////	
var code ; //在全局 定义验证码
function createCode(){ 
code = new Array();
var codeLength = 6;//验证码的长度
var checkCode = document.getElementById("checkCode");
checkCode.value = "";

var selectChar = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','S','T','U','V','W','X','Y','Z');

for(var i=0;i<codeLength;i++) {
   var charIndex = Math.floor(Math.random()*36);
   code +=selectChar[charIndex];
}
if(code.length != codeLength){
   createCode();
}
checkCode.value = code;
}

function validate () {
	
var inputCode = document.getElementById("validCode").value.toUpperCase();
var name = document.getElementById("user").value;
var pwd = document.getElementById("pwd").value;

if (name == "") {
	alert("请您输入用户名!");
	return false;
}
	if (pwd == "") {
	alert("请您输入密码!");
	return false;
}

if(inputCode.length <=0) {
   alert("请输入验证码！");
   return false;
}
else if(inputCode != code ){
   alert("验证码输入错误！");
   createCode();
   return false;
}
}