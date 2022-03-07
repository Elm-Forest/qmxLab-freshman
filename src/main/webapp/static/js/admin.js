let username;
$("#userList").click(function () {
    $.ajax({
        type: "get",

        url: "UserList.html",
        success: function (data) {
            $("#content").html(data);
        }
    })
})
const time = new Vue({
    el: '.time',
    data: {
        hello: Hello(),
        time: getDate(),
        users: getUser()
    },
    methods: {
        timeFresh: function () {
            setInterval(this.setTime, 1000);
        },
        setTime: function () {
            this.time = getDate();
        }
    }
})
const user = new Vue({
    el: '.user',
    data: {
        users: getUser()
    }
})

function getUser() {
    let adminName = '连接失败';
    $.ajax({
        type: "get",
        async: false,
        url: "../../../admin",
        dataType: "text",
        data: {
            "method": "getAdminName",
        },
        success: function (data) {
            adminName = data;
        }
    })
    return adminName;
}

$(function () {
    time.timeFresh();
    username = getUser();
})