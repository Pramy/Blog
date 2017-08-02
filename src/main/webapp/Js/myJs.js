function checkLogin(){
    var code = document.getElementsByName("code")[0];
    var userName = document.getElementsByName("userName")[0];
    var password = document.getElementsByName("userPassword")[0];
    if(userName.value.length<1){
        alert("用户名不能为空");
        return false;
    }
    if(password.value.length<1){
        alert("密码不能为空");
        return false;
    }
    if(code.value.length<1){
        alert("验证码不能为空");
        return false;
    }
    return true;
}
function go(form,url,type) {

        $.ajax({
            url:url,
            type:type,
            data:form.serialize(),
            success:function (mdata) {
                if(mdata.message!=='' ){
                    alert(mdata.message);
                    alert(mdata.url);
                }
                if(mdata.success){
                    location.href=mdata.url;
                }
            }
    })
}
function checkRegister(){
    var userName = document.getElementsByName("userName")[0].value;
    var userPassword = document.getElementsByName("userPassword")[0].value;
    var rePassword = document.getElementsByName("rePassword")[0].value;
    var email = document.getElementsByName("email")[0].value;
    var question = document.getElementsByName("question")[0].value;
    var answer = document.getElementsByName("answer")[0].value;
    var code = document.getElementsByName("code")[0].value;
    var sex = document.getElementsByName("sex");
    if(userName.length<1){
        alert("用户名不能为空");
        return false;
    }else if(userName.length<6 || userName.length>16){
        alert("用户名长度为6~16位");
        return false;
    }
    if (userPassword.length<1 || rePassword.length<1) {
        alert("密码不能为空");
        return false;
    }
    if(userPassword.length<6 || userPassword.length>16){
        alert("密码长度为6~16位");
        return false;
    }
    if(userPassword!==rePassword){
        alert("两次密码不同");
        return false;
    }
    if (email.length<1) {
        alert("邮箱不能为空");
        return false;
    }
    if (question.length<1) {
        alert("安全问题不能为空");
        return false;
    }
    if (answer.length<1) {
        alert("答案不能为空");
        return false;
    }
    if (code.length<1) {
        alert("验证码不能为空");
        return false;
    }
    if (!sex[0].checked && !sex[1].checked) {
        alert("请选择性别");
        return false;
    }
    return true;
}

function checkUpdatePassword(){
    var answer=document.getElementById("answerId").value;
    var email=document.getElementById("emailId").value;
    var password=document.getElementById("passwordId").value;
    var repassword=document.getElementById("repasswordId").value;
    if(!isLegal(answer) || !isLegal(password) || !isLegal(repassword)){
        alert("请输入合法的内容");
        return false;
    }
    if(password.length<6 ||repassword.length<6){
        alert("密码长度不能低于6位");
        return false;
    }
    if(!isEmail(email)){
        alert("邮箱格式有误");
        return false;
    }
    if(password!==repassword){
        alert("两次输入的密码不同");
        return false;
    }
    return true;
}

function checkfind(){
    var userName=document.getElementsByName("userName")[0].value;
    if(!isLegal(userName)){
        alert("请输入合法的内容");
        return false;
    }
    return true;
}
function refresh(){
    document.getElementById("verificationCode").src ="/code?"+ new Date().getTime();
}



