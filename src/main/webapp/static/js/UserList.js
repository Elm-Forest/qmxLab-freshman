var TotalNum;
var TotalPage;
var pageSize;
var nextFlag = 0;

function Delete(obj) {
    var tr = $(obj).parent().parent();
    var conf = [];
    tr.find('td').each(function (i, td) {
        if ($(td).find('a').length === 0) {
            conf.push($(td).html());
        }
        console.log("del")
    });
    $.ajax({
        type: "get",
        async: false,
        url: "../../../admin",
        dataType: "text",
        data: {
            "id": conf[0],
            "method": "UserDelete",
        },
        success: function (data) {
            $(".tip").text('Delete ' + data);
            obj.parentNode.parentNode.remove();
            $("#successMsg").text("删除");
            $(".successMsg").show(1000);
            setTimeout(function () {
                $(".successMsg").hide();
            }, 4000)

        },
        error: function (ERROR) {
            $("#errorMsg").text("删除");
            $("#serverMsgMsg").text("ajax/error:发生错误：" + ERROR.status);
            $(".errorMsg").show(1000);
            setTimeout(function () {
                $(".errorMsg").hide();
            }, 4000)
            console.log("ajax/error:发生错误：" + ERROR.status);
        }
    });
}

function userModify() {
    $.ajax({
        type: "get",
        async: false,
        url: "../../../admin",
        dataType: "text",
        data: {
            "ID": $("#UserID").val(),
            "name": $("#nameModify").val(),
            "studentID": $("#studentIDModify").val(),
            "grade": $("#gradeModify").val(),
            "target": $("#targetModify").val(),
            "major": $("#majorModify").val(),
            "mail": $("#mailModify").val(),
            "phone": $("#phoneModify").val(),
            "method": "UserModify",
        },
        success: function (data) {
            console.log(data);
            $("#successMsg").text("修改");
            $(".successMsg").show(1000);
            setTimeout(function () {
                $(".successMsg").hide();
            }, 4000)

        },
        error: function (ERROR) {
            $("#errorMsg").text("修改");
            $("#serverMsgMsg").text("ajax/error:发生错误：" + ERROR.status);
            $(".errorMsg").show(1000);
            setTimeout(function () {
                $(".errorMsg").hide();
            }, 4000)
            console.log("ajax/error:发生错误：" + ERROR.status);
        }
    });
}

$(function () {
    $('.selectpicker').selectpicker('refresh');
    $("#behind").attr({"disabled": "disabled"});
    getUserList();
    getUserNum();
})

var currentNum = new Vue({
    el: '#currentNum',
    data: {
        currentNum: 1
    },
    methods: {
        increaseNum: function () {
            this.currentNum++;
            getUserList();
            if (this.currentNum > 1) {
                $("#behind").removeAttr("disabled");
            }
            if (this.currentNum === TotalPage) {
                $("#next").attr({"disabled": "disabled"});
            }
        },
        decreaseNum: function () {
            this.currentNum--;
            getUserList();
            if (this.currentNum < TotalPage) {
                $("#next").removeAttr("disabled");
            }
            if (this.currentNum <= 1) {
                $("#behind").attr({"disabled": "disabled"});
            }
        }
    }
})

$("#check").click(function () {
    currentNum.currentNum = 1;
    nextFlag = 0;
    $("#behind").attr({"disabled": "disabled"});
    if (TotalPage !== 1) {
        $("#next").removeAttr("disabled");
    }
    getUserList();
})

function update(data) {
    var tr = $(data).parent().parent();
    var tdcon = [];
    tr.find('td').each(function (i, td) {
        if ($(td).find('a').length === 0) {
            tdcon.push($(td).html());
        }
    });
    $("#UserID").val(tdcon[0]);
    $("#nameModify").val(tdcon[1]);
    $("#studentIDModify").val(tdcon[2]);
    $("#gradeModify").val(tdcon[3]);
    $("#majorModify").val(tdcon[4]);
    $("#targetModify").val(tdcon[5]);
    $("#mailModify").val(tdcon[6]);
}

function getUserList() {
    pageSize = $("#pageSize").val();
    if ((parseInt(getUserNum()) < 2 || TotalPage === undefined) && nextFlag === 0) {
        $("#next").attr({"disabled": "disabled"});
        nextFlag++;
    }
    let result = "<thead><tr><th>ID</th><th>姓名</th><th>学号</th><th>年级</th><th>专业</th><th>志愿</th><th>邮箱</th></tr></thead><tbody>";
    $.ajax({
        type: "get",
        url: "../../../admin",
        dataType: "json",
        data: {
            "name": $("#username").val(),
            "grade": $("#grade").val(),
            "target": $("#target").val(),
            "pageSize": $("#pageSize").val(),
            "currentPageNum": currentNum.currentNum,
            "method": "getUserList",
        },
        success: function (data) {
            for (let i = 0; i < data.length; i++) {
                result += "<tr>" +
                    "<td>" + data[i].iD + "</td>" +
                    "<td>" + data[i].name + "</td>" +
                    "<td>" + data[i].studentID + "</td>" +
                    "<td>" + data[i].grade + "</td>" +
                    "<td>" + data[i].major + "</td>" +
                    "<td>" + data[i].target + "</td>" +
                    "<td>" + data[i].mail + "</td>" +
                    "<td>" +
                    "<a href=\"#\" class=\"btn btn-danger btn-sm\" data-toggle=\"modal\" data-target=\"#tip\" onclick=\"Delete(this)\">删除</a>&nbsp" +
                    "<a href=\"#\" class=\"btn btn-info btn-sm\" data-toggle=\"modal\" data-target=\"#editUser\" onclick=\"update(this)\">修改</a>" +
                    "</td>" +
                    "</tr>";
            }
            result += "</tbody>";
            $(".table").html(result);
        },
        error: function (ERROR) {
            console.log("ajax/error:发生错误：" + ERROR.status);
        }
    });
}

function getUserNum() {
    $.ajax({
        type: "get",
        url: "../../../admin",
        async: false,
        dataType: "text",
        data: {
            "name": $("#username").val(),
            "grade": $("#grade").val(),
            "target": $("#target").val(),
            "method": "getUserCount",
        },
        success: function (data) {
            if (data == null || data === "") {
                TotalNum = 0;
            } else {
                TotalNum = parseInt(data);
            }
            if (TotalNum % pageSize === 0) {
                TotalPage = parseInt(TotalNum / pageSize + "");
            } else {
                TotalPage = parseInt(TotalNum / pageSize + "") + 1;
            }
            console.log(TotalNum);
            console.log(TotalPage);
        },
        error: function (ERROR) {
            console.log("ajax/error:发生错误：" + ERROR.status);
        }
    });
    return TotalPage;
}

function addUser() {
    $.ajax({
        type: "get",
        async: false,
        url: "../../../userRegister",
        dataType: "text",
        data: {
            "name": $("#nameAdd").val(),
            "studentID": $("#studentIDAdd").val(),
            "grade": $("#gradeAdd").val(),
            "target": $("#targetAdd").val(),
            "major": $("#majorAdd").val(),
            "password": $("#passwordAdd").val(),
            "mail": $("#mailAdd").val(),
            "phone": $("#phoneAdd").val(),
            "method": "registerUser",
        },
        success: function (data) {
            $(".tip").text(data);
        },
        error: function (ERROR) {
            console.log("ajax/error:发生错误：" + ERROR.status);
        }
    });
}