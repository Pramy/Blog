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
                alert(mdata.message);
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
    if(userPassword!=rePassword){
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
function refresh(){
    document.getElementById("verificationCode").src ="/code?"+ new Date().getTime();
}