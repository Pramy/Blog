/**
 * 
 */
// 判断输入是否是数字  
function isDigit(s){   
	var patrn=/^[0-9]{1,20}$/;   
	if (!patrn.test(s)) return false   
	return true   
}
//判断输入是否是中文或者英文
function isLegal(s){
	var regex = new RegExp("^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z0-9_]){1,10}$");
	return regex.test(s);
}
//邮箱
function isEmail(str){
   var re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
   if(re.test(str)){
       return true;
   }else{
       return false;
   }
}
