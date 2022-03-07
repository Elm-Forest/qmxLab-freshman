var flag1 = false;
var flag2 = false;
var flag3 = false;
$("#oldPassword").blur(function () {
    checkPassword();
})
$("#newPassword").blur(function () {
    if ($("#newPassword").val().length >= 6 && $("#newPassword").val().length <= 20) {
        $("#Tip2").text(" 可以使用！");
        $("#Tip2").css("color", "#7bb952");
        flag2 = true;
    } else {
        $("#Tip2").text(" 新密码应长度当在6到20位之间");
        $("#Tip2").css("color", "#ef3b3b");
        flag2 = false;
    }
})
$("#newPasswordTwo").blur(function () {
    if ($("#newPasswordTwo").val() !== $("#newPassword").val()) {
        $("#Tip3").text(" 两次输入密码不一致");
        $("#Tip3").css("color", "#ef3b3b");
        flag3 = false;
    } else {
        $("#Tip3").text(" 一致!");
        $("#Tip3").css("color", "#7bb952");
        flag3 = true;
    }
})

function UserPwdModify() {
    if (!(flag1 && flag2 && flag3)) {
        $(".tip").text("数据有误!");
        return;
    }
    $.ajax({
        type: "get",
        async: false,
        url: "../../../user",
        dataType: "text",
        data: {
            "oldPassword": $("#oldPassword").val(),
            "newPassword": $("#newPassword").val(),
            "newPasswordTwo": $("#newPasswordTwo").val(),
            "method": "userPwdModify"
        },
        success: function (data) {
            if (data === 'success') {
                $(".tip").text(" 修改成功！");
            } else {
                $(".tip").text(data);
            }
        },
        error: function (ERROR) {
            $(".tip").text(ERROR, " 详情见控制台");
            console.log("ajax/error:发生错误：" + ERROR.status);
        }
    });
}

function checkPassword() {
    $.ajax({
        type: "get",
        async: false,
        url: "../../../user",
        dataType: "text",
        data: {
            "oldPassword": $("#oldPassword").val(),
            "method": "checkUserPwd"
        },
        success: function (data) {
            if (data === "success") {
                $("#Tip1").text("密码正确！");
                $("#Tip1").css("color", "#7bb952");
                flag1 = true;
            } else {
                $("#Tip1").text(data);
                $("#Tip1").css("color", "#ef3b3b");
            }
        },
        error: function (ERROR) {
            flag1 = false;
            $("#Tip1").text("ajax/error:发生错误：" + ERROR.status);
            $("#Tip1").css("color", "#ef3b3b");
            console.log("ajax/error:发生错误：" + ERROR.status);
        }
    });
}