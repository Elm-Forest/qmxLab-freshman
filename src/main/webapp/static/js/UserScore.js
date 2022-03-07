var TotalNum;
var TotalPage;
var pageSize;
var nextFlag = 0;

function userModify() {
    $.ajax({
        type: "get",
        async: false,
        url: "../../../admin",
        dataType: "text",
        data: {
            "score": $("#score").val(),
            "ID": $("#UserID").val(),
            "method": "setUserScore",
        },
        success: function (data) {
            console.log(data);
            $("#successMsg").text("修改");
            $(".successMsg").show(1000);
            setTimeout(function () {
                $(".successMsg").hide();
            }, 6000)

        },
        error: function (ERROR) {
            $("#errorMsg").text("修改");
            $("#serverMsgMsg").text("ajax/error:发生错误：" + ERROR.status);
            $(".errorMsg").show(1000);
            setTimeout(function () {
                $(".errorMsg").hide();
            }, 6000)
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
    $("#score").val(tdcon[1]);
}

function getUserList() {
    pageSize = $("#pageSize").val();
    if ((parseInt(getUserNum()) < 2 || TotalPage === undefined) && nextFlag === 0) {
        $("#next").attr({"disabled": "disabled"});
        nextFlag++;
    }
    let result = "<thead><tr><th>ID</th><th>分数</th><th>姓名</th><th>年级</th><th>志愿</th></tr></thead><tbody>";
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
                    "<td>" + data[i].score + "</td>" +
                    "<td>" + data[i].name + "</td>" +
                    "<td>" + data[i].grade + "</td>" +
                    "<td>" + data[i].target + "</td>" +
                    "<td>" +
                    "<a href=\"#\" class=\"btn btn-info btn-sm\" data-toggle=\"modal\" data-target=\"#editUser\" onclick=\"update(this)\">修改分数</a>" +
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