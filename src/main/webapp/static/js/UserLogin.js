var nameInput = $(".usernameBox input");
var pwdInput = $(".passwordBox input");
var eye = $("#eye img");
var submit = $("#user");
var flag = 0;
nameInput.click(function () {
    nameInput.css("border-bottom", " 3px solid #4CAF50");
})
nameInput.blur(function () {
    nameInput.css("border-bottom", " 2px solid #b0a9a9")
})
pwdInput.click(function () {
    pwdInput.css("border-bottom", " 3px solid #4CAF50");
})
pwdInput.blur(function () {
    pwdInput.css("border-bottom", " 2px solid #b0a9a9")
})
eye.click(function () {
    if (flag === 0) {
        pwdInput.attr('type', 'text');
        eye.attr('src', '../img/openeye.png');
        flag = 1;
    } else {
        pwdInput.attr('type', 'password');
        eye.attr('src', '../img/closeeye.png');
        flag = 0;
    }
})
submit.click(function () {
    if (nameInput.val().length > 20 || pwdInput.val().length > 20) {
        $("#tip").text("*输入格式错误,用户名和密码的长度不能超过20位");
        return;
    }
    $.ajax({
        type: "get",
        async: false,
        url: "../../userLogin",
        dataType: "text",
        data: {
            "method": "UserLogin",
            "studentID": nameInput.val(),
            "password": pwdInput.val()
        },
        success: function (data) {
            if (data === "success") {
                $(window).attr('location', 'user/user.html');
            } else {
                $("#tip").text("*" + data);
                alert(data);
            }
        },
        error: function (ERROR) {
            console.log("异步通信发生错误，请检查连接是否正常，错误code：" + ERROR.status);
        }
    });
})