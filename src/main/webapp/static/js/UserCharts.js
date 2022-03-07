var userList = [];
var adminNum;
$(function () {
    userList = getUserList();
    adminNum = getAdminNum();
    setNum(userList, adminNum);
    setTargetBar(userList);
    setTargetPie(userList);
    setGradeBar(userList);
    setGradePie(userList);
})

function setNum(userList, adminNum) {
    var targetList = [0, 0, 0, 0];
    var gradeList = [0, 0];
    $("#totalNum").text(userList.length);
    $("#adminNum").text(adminNum);
    for (var key in userList) {
        if (userList[key].target === '开发组') {
            targetList[0]++;
        } else if (userList[key].target === '智能组') {
            targetList[1]++;
        } else if (userList[key].target === '机械组') {
            targetList[2]++;
        } else if (userList[key].target === 'ACM') {
            targetList[3]++;
        }
        if (userList[key].grade === '2020') {
            gradeList[0]++;
        } else if (userList[key].grade === '2021') {
            gradeList[1]++;
        }
    }
    $('#gradeProportion').text(gradeList[0] + ":" + gradeList[1]);
    $("#targetProportion").text(targetList[0] + ":" + targetList[1] + ":" + targetList[2] + ":" + targetList[3]);
}

function setTargetBar(userList) {
    var targetX = ['开发组', '智能组', '机械组', 'ACM'];
    var targetY = [0, 0, 0, 0];
    for (var key in userList) {
        if (userList[key].target === '开发组') {
            targetY[0]++;
        } else if (userList[key].target === '智能组') {
            targetY[1]++;
        } else if (userList[key].target === '机械组') {
            targetY[2]++;
        } else if (userList[key].target === 'ACM') {
            targetY[3]++;
        }
    }
    echarts.init(document.getElementById('test1')).setOption({
        title: {
            text: '报名人数分布'
        },
        xAxis: {
            type: 'category',
            data: targetX
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                data: targetY,
                type: 'bar',
                showBackground: true,
                backgroundStyle: {
                    color: 'rgba(180, 180, 180, 0.2)'
                }
            }
        ]
    });
}

function setTargetPie(userList) {
    var targetY = [
        {value: 0, name: '开发组'},
        {value: 0, name: '智能组'},
        {value: 0, name: '机械组'},
        {value: 0, name: 'ACM'},
    ];
    for (var key in userList) {
        if (userList[key].target === '开发组') {
            targetY[0].value++;
        } else if (userList[key].target === '智能组') {
            targetY[1].value++;
        } else if (userList[key].target === '机械组') {
            targetY[2].value++;
        } else if (userList[key].target === 'ACM') {
            targetY[3].value++;
        }
    }
    echarts.init(document.getElementById('test2')).setOption({
        tooltip: {
            trigger: 'item'
        },
        legend: {
            top: '5%',
            left: 'center'
        },
        series: [
            {
                name: 'Access From',
                type: 'pie',
                radius: ['40%', '70%'],
                avoidLabelOverlap: false,
                itemStyle: {
                    borderRadius: 10,
                    borderColor: '#fff',
                    borderWidth: 2
                },
                label: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: '20',
                        fontWeight: 'bold'
                    }
                },
                labelLine: {
                    show: false
                },
                data: targetY
            }
        ]
    });
}

function setGradeBar(userList) {
    var gradeList = [
        ['product', '2020', '2021', 'Total'],
        ['开发组', 0, 0, 0],
        ['智能组', 0, 0, 0],
        ['机械组', 0, 0, 0],
        ['ACM集训队', 0, 0, 0]
    ];
    for (var key in userList) {
        if (userList[key].grade === '2020') {
            if (userList[key].target === '开发组') {
                gradeList[1][1]++;
            } else if (userList[key].target === '智能组') {
                gradeList[2][1]++;
            } else if (userList[key].target === '机械组') {
                gradeList[3][1]++;
            } else if (userList[key].target === 'ACM') {
                gradeList[4][1]++;
            }
        } else if (userList[key].grade === '2021') {
            if (userList[key].target === '开发组') {
                gradeList[1][2]++;
            } else if (userList[key].target === '智能组') {
                gradeList[2][2]++;
            } else if (userList[key].target === '机械组') {
                gradeList[3][2]++;
            } else if (userList[key].target === 'ACM') {
                gradeList[4][2]++;
            }
        }
    }
    gradeList[1][3] = gradeList[1][1] + gradeList[1][2];
    gradeList[2][3] = gradeList[2][1] + gradeList[2][2];
    gradeList[3][3] = gradeList[3][1] + gradeList[3][2];
    gradeList[4][3] = gradeList[4][1] + gradeList[4][2];


    echarts.init(document.getElementById('test3')).setOption({
        legend: {},
        tooltip: {},
        dataset: {
            source: gradeList
        },
        xAxis: {type: 'category'},
        yAxis: {},
        // Declare several bar series, each will be mapped
        // to a column of dataset.source by default.
        series: [{type: 'bar'}, {type: 'bar'}, {type: 'bar'}]
    });
}

function setGradePie(userList) {
    var gradeList = [
        {value: 0, name: '2020'},
        {value: 0, name: '2021'},
    ]
    for (var key in userList) {
        if (userList[key].grade === '2020') {
            gradeList[0].value++;
        } else if (userList[key].grade === '2021') {
            gradeList[1].value++;
        }
    }
    echarts.init(document.getElementById('test4')).setOption({
        title: {
            text: '年级分布',
            subtext: '2020与2021',
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
                data: gradeList,
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

function getAdminNum() {
    var datas;
    $.ajax({
        type: "get",
        url: "../../../admin",
        async: false,
        data: {
            "adminUserName": "",
            "method": "getAdminCount"
        },
        success: function (data) {
            datas = data;
            console.log("getAdminCount successful")
        },
        error: function (ERROR) {
            console.log("ajax/error:发生错误：" + ERROR.status);
        }
    });
    return datas;
}