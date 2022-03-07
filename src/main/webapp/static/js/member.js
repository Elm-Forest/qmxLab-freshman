const member = getUser();

function getUser() {
    let list;
    $.ajax({
        async: false,       //同步AJAX,信息返回后才会继续执行
        type: "get",
        url: "/qmx/winner",
        dataType: "json",
        success: function (data) {
            list = data;
        },
        error: function (ERROR) {
            console.log("member.getUser Ajax通信发生错误，错误代码为：" + ERROR.status);
        }
    });
    return list;
}