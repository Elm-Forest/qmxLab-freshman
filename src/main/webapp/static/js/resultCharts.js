var userList = [];
var result = [0, 0, 0, 0];
$(function () {
    userList = getUserList();
    setResultNum(userList);
    setG1();
    setG2();
})

function setG1(){
    var Gx1 = ['开发组', '智能组', '机械组', 'ACM'];
    echarts.init(document.getElementById('G1')).setOption({
        title: {
            text: '录取分布'
        },
        xAxis: {
            type: 'category',
            data: Gx1
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                data: result,
                type: 'bar',
                showBackground: true,
                backgroundStyle: {
                    color: 'rgba(180, 180, 180, 0.2)'
                }
            }
        ]
    });
}

function setG2(){
    var resultList = [
        {value: 0, name: '开发组'},
        {value: 0, name: '智能组'},
        {value: 0, name: '机械组'},
        {value: 0, name: 'ACM'}
    ]
    for(var i=0;i<4;i++){
        resultList[i].value=result[i];
    }
    echarts.init(document.getElementById('G2')).setOption({
        title: {
            text: '录取分布',
            left: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left'
        },
        series: [
            {
                name: 'Access From',
                type: 'pie',
                radius: '50%',
                data: resultList,
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    })
}

function setResultNum(userList) {
    for (var key in userList) {
        if (userList[key].target === '开发组') {
            if (userList[key].result === '录用') {
                result[0]++;
            }
        } else if (userList[key].target === '智能组') {
            if (userList[key].result === '录用') {
                result[1]++;
            }
        } else if (userList[key].target === '机械组') {
            if (userList[key].result === '录用') {
                result[2]++;
            }
        } else if (userList[key].target === 'ACM') {
            if (userList[key].result === '录用') {
                result[3]++;
            }
        }
    }
    $("#kaifaNum").text(result[0]);
    $("#zhinengNum").text(result[1]);
    $("#jixieNum").text(result[2]);
    $("#ACMNum").text(result[3]);
}

function getUserList() {
    var datas;
    $.ajax({
        type: "get",
        url: "../../../admin",
        dataType: "json",
        async: false,
        data: {
            "name": "",
            "grade": "",
            "target": "",
            "pageSize": "100000",
            "currentPageNum": "1",
            "method": "getUserList",
        },
        success: function (data) {
            datas = data;
            console.log("getUserList successful")
        },
        error: function (ERROR) {
            console.log("ajax/error:发生错误：" + ERROR.status);
        }
    });
    return datas;
}