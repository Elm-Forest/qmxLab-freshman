var nameInput = $(".usernameBox input");
var pwdInput = $(".passwordBox input");
var eye = $("#eye img");
var submit = $(".button");
var check = $(".check button");
var studentID = $("#studentID");
var studentIDTip = $("#studentIDTip");
var mail = $("#mail");
var mailTip = $("#mailTip");
var time = 60;
var flag = 0;
var nameFlag = false;
var studentIDFlag = false;
var passwordFlag = false;
var mailFlag = false;
var phoneFlag = true;

nameInput.click(function () {
    changeClickColor(nameInput);
});
nameInput.blur(function () {
    changeBlurColor(nameInput);
    if (!checkAll(nameInput)) {
        nameFlag = error($("#nameTip"));
    } else {
        nameFlag = success($("#nameTip"));
    }
})
studentID.click(function () {
    changeClickColor(studentID);
})
pwdInput.click(function () {
    changeClickColor(pwdInput);
})
pwdInput.blur(function () {
    changeBlurColor(pwdInput);
    if (!checkAll(pwdInput) || pwdInput.val().length < 6) {
        passwordFlag = error($("#passwordTip"));
        console.log(passwordFlag)
    } else {
        passwordFlag = success($("#passwordTip"));
        console.log(passwordFlag)
    }
})
$("#grade").click(function () {
    changeClickColor($("#grade"));
})
$("#grade").blur(function () {
    changeBlurColor($("#grade"));
})
$('#target').click(function () {
    changeClickColor($("#target"));
})
$('#target').blur(function () {
    changeBlurColor($("#target"));
})
$('#major').click(function () {
    changeClickColor($("#major"));
})
$('#major').blur(function () {
    changeBlurColor($("#major"));
})
mail.click(function () {
    changeClickColor(mail);
})
mail.blur(function () {
    changeBlurColor(mail);
    if (isEmpty(mail) || !checkEmail(mail.val())) {
        mailFlag = error($("#mailTip"));
    } else {
        mailFlag = success($("#mailTip"));
    }
})
$("#mailCode").click(function () {
    changeClickColor($("#mailCode"));
})
$("#mailCode").blur(function () {
    changeBlurColor($("#mailCode"));
})
$("#phone").blur(function () {
    changeBlurColor($("#phone"));
    if (isEmpty($("#phone"))) {
        $("#phoneTip").text("可以为空");
        $("#phoneTip").css("color", "#7bb952");
    } else {
        console.log("ok")
        if (!checkPhone($("#phone").val())) {
            phoneFlag = error($("#phoneTip"));
            console.log(phoneFlag)
        } else {
            phoneFlag = success($("#phoneTip"));
            console.log(phoneFlag)
        }
    }
})
$("#phone").click(function () {
    changeClickColor($("#phone"));
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
studentID.blur(function () {
    changeBlurColor(studentID)
    if (studentID.val() === "" || studentID.val() === null) {
        studentIDTip.text("学号不能为空");
        studentIDTip.css("color", "#ef3b3b");
        studentIDFlag = false;
        return;
    } else if (studentID.val().length > 12 || studentID.val().length < 10) {
        studentIDTip.text("学号不存在超过12位或小于8位");
        studentIDTip.css("color", "#ef3b3b");
        studentIDFlag = false;
        return;
    }
    $.ajax({
        type: "get",
        async: false,
        url: "../../userRegister",
        dataType: "text",
        data: {
            "method": "isExistUser",
            "studentID": studentID.val()
        },
        success: function (data) {
            if (data === "success") {
                studentIDTip.text("可以注册");
                studentIDTip.css("color", "#7bb952");
                studentIDFlag = true;
            } else {
                studentIDTip.text("该学号已注册");
                studentIDTip.css("color", "#ef3b3b");
                studentIDFlag = false;
            }
        },
        error: function (ERROR) {
            studentIDTip.text("通信异常,code:" + ERROR.status);
            studentIDTip.css("color", "#ef3b3b");
            console.log("异步通信发生错误，请检查连接是否正常，错误code：" + ERROR.status);
        }
    });
})
check.click(function () {
    console.log("check")
    if (mail.val() == null || mail.val() === "") {
        mailTip.text("邮箱不能为空");
        mailTip.css("color", "#ef3b3b");
        console.log(mail);
        return;
    } else if (!checkEmail(mail.val())) {
        console.log(mail.val());
        mailTip.text("邮箱格式不正确");
        mailTip.css("color", "#ef3b3b");
        return;
    }
    mailTip.text("可以注册");
    mailTip.css("color", "#7bb952");
    check.attr('disabled', true);
    setTimeout(Time, 1000);

    console.log("ok")
    $.ajax({
        type: "get",
        url: "../../userRegister",
        dataType: "text",
        data: {
            "method": "emailSend",
            "mail": mail.val(),
        },
        success: function (data) {
            if (data === "error") {
                alert(data);
            }
        },
        error: function (ERROR) {
            mailTip.css("color", "#ef3b3b");
            mailTip.text("通信异常,code:" + ERROR.status);
            console.log("异步通信发生错误，请检查连接是否正常，错误code：" + ERROR.status);
        }
    });
})
submit.click(function () {
    console.log(nameFlag)
    console.log(studentIDFlag)
    console.log(passwordFlag)
    console.log(mailFlag)
    console.log(phoneFlag)
    if ((nameFlag && studentIDFlag && passwordFlag && mailFlag && phoneFlag) === false) {
        console.log("失败")
        alert("注册信息有误！")
        return;
    }
    $.ajax({
        type: "get",
        async: false,
        url: "../../userRegister",
        dataType: "text",
        data: {
            "method": "emailCheck",
            "name": $("#name").val(),
            "studentID": studentID.val(),
            "grade": $("#grade").val(),
            "target": $("#target").val(),
            "major": $("#major").val(),
            "password": $("#password").val(),
            "mail": $("#mail").val(),
            "phone": $("#phone").val(),
            "mailCode": $("#mailCode").val()
        },
        success: function (data) {
            if (data === "success") {
                /*sleep(3000)*/
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

function Time() {
    if (time > 0) {
        check.text(time + "s");
        time--;
        check.css("background-color", "#8dbce8");
        setTimeout(Time, 1000);
    } else {
        time = 60;
        check.text("发送");
        check.attr('disabled', false);
        check.css("background-color", "#5dafea")
    }
}

function checkEmail(strEmail) {
    return /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/.test(strEmail);
}

function changeClickColor(object) {
    object.css({"color": "#151313", "border-bottom": " 3px solid #4CAF50"});
}

function changeBlurColor(object) {
    object.css("border-bottom", " 2px solid #b0a9a9");
}

function isEmpty(object) {
    return object.val() === "" || object.val() == null;
}

function lenthCheck(object) {
    return object.val().length < 20;
}

function checkAll(object) {
    return !isEmpty(object) && lenthCheck(object);
}

function success(object) {
    object.text("可以注册");
    object.css("color", "#7bb952");
    return true;
}

function error(object) {
    object.text("格式不正确");
    object.css("color", "#ef3b3b");
    return false;
}

function checkPhone(sMobile) {
    return /^1[3|4|5|8][0-9]\d{4,8}$/.test(sMobile);
}

let sleep = function (time) {
    let now = Date.now() // 获取当前毫秒数
    // 设置while循环，循环条件为：实时时间减去记录时间小于3s，否则则循环结束
    while (Date.now() - now < time) {
    }
}