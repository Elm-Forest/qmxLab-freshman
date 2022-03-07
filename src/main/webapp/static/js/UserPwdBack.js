var mailBox = $("#mail");
var mailCheckBox = $(".mailCheckBox input");
var submit = $(".button");
var check = $("#send");
var mailCode;
var Email;
var time = 60;
var flag = false;
mailBox.click(function () {
    mailBox.css("border-bottom", " 3px solid #4CAF50");
})

mailBox.blur(function () {
    mailBox.css("border-bottom", " 2px solid #b0a9a9")
})

mailCheckBox.click(function () {
    mailCheckBox.css("border-bottom", " 3px solid #4CAF50");
})

mailCheckBox.blur(function () {
    mailCheckBox.css("border-bottom", " 2px solid #b0a9a9")
})

function checkEmail(strEmail) {
    return /^([a-zA-Z]|[0-9])(\w|-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/.test(strEmail);
}

check.click(function () {
    console.log("000")
    if (!checkEmail(mailBox.val())) {
        alert("email 格式不正确！");
        return;
    }
    $.ajax({
        type: "get",
        async: true,
        url: "../../userLogin",
        dataType: "text",
        data: {
            "method": "emailSend",
            "mail": mailBox.val(),
        },
        success: function (data) {
            if (data === "success") {
                flag = true;
                console.log("true")
            } else {
                $("#tip").text("邮箱不存在");
            }
        },
        error: function (ERROR) {
            console.log("异步通信发生错误，请检查连接是否正常，错误code：" + ERROR.status);
        }
    });
    check.attr('disabled', true);
    setTimeout(Time, 1000);
})

function Time() {
    if (time > 0) {
        check.text(time + "s")
        time--;
        setTimeout(Time, 1000);
    } else {
        time = 60;
        check.text("发送");
        check.attr('disabled', false);
    }
}

submit.click(function () {
    if (flag !== true) {
        alert("尚未验证邮箱");
        return;
    }
    $.ajax({
        type: "get",
        async: false,
        url: "../../userLogin",
        dataType: "text",
        data: {
            "method": "emailCheck",
            "mail": mailBox.val(),
            "mailCode": mailCheckBox.val()
        },
        success: function (data) {
            if (data === "success") {
                mailCode = mailCheckBox.val();
                Email = mailBox.val();
                pwdBack();
            } else {
                alert(data);
            }
        },
        error: function (ERROR) {
            console.log("异步通信发生错误，请检查连接是否正常，错误code：" + ERROR.status);
        }
    });
})

function pwdBack() {
    $.ajax({
        type: "get",
        async: false,
        url: "PwdBack.html",
        success: function (data) {
            $("#box").html(data);
        },
        error: function (ERROR) {
            console.log("异步通信发生错误，请检查连接是否正常，错误code：" + ERROR.status);
        }
    });
}

$("#reset").click(function () {
    var password = mailBox.val();
    var newpassword = $("#newPassword").val();
    if (password.length < 6 || password.length > 20) {
        $("#tip").text("长度应当在6到20位之间！")
        return;
    }
    if (password !== newpassword) {
        $("#tip").text("两次输入密码不一致！")
        return;
    }
    console.log(newpassword)
    $.ajax({
        type: "get",
        async: false,
        url: "../../userLogin",
        dataType: "text",
        data: {
            "method": "PwdBack",
            "mail": Email,
            "mailCode": mailCode,
            "password": password
        },
        success: function (data) {
            if (data === "success") {
                $(window).attr('location', 'UserLogin.html');
            } else {
                alert(data);
            }
        },
        error: function (ERROR) {
            console.log("异步通信发生错误，请检查连接是否正常，错误code：" + ERROR.status);
        }
    });
})