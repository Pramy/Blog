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
function go(form,url,type,local) {

        $.ajax({
            url:url,
            type:type,
            data:form.serialize(),
            success:function (mdata) {
                alert(mdata.message);
                if(mdata.success){
                    location.href=local;
                }
            }
    })
}

function refresh(){
    document.getElementById("verificationCode").src ="/code?"+ new Date().getTime();
}