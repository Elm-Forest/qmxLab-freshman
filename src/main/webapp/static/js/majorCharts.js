var userList = [];
$(function () {
    userList = getUserList();
    setRadarMap(userList)
})

function Max(a, b) {
    return a > b ? a : b;
}

function setRadarMap(userList) {
    var max = [0, 0, 0, 0, 0, 0, 0, 0, 0]
    var major = [
        {
            value: [0, 0, 0, 0, 0, 0, 0, 0, 0],
            name: '开发组'
        },
        {
            value: [0, 0, 0, 0, 0, 0, 0, 0, 0],
            name: '智能组'
        },
        {
            value: [0, 0, 0, 0, 0, 0, 0, 0, 0],
            name: '机械组'
        },
        {
            value: [0, 0, 0, 0, 0, 0, 0, 0, 0],
            name: 'ACM'
        }
    ]
    for (var key in userList) {
        if (userList[key].target === '开发组') {
            if (userList[key].major === '计算机与信息学院') {
                major[0].value[0]++;
            } else if (userList[key].major === '电气与新能源学院') {
                major[0].value[1]++;
            } else if (userList[key].major === '机械与动力学院') {
                major[0].value[2]++;
            } else if (userList[key].major === '水利与环境学院') {
                major[0].value[3]++;
            } else if (userList[key].major === '理学院') {
                major[0].value[4]++;
            } else if (userList[key].major === '医学院') {
                major[0].value[5]++;
            } else if (userList[key].major === '土木与建筑学院') {
                major[0].value[6]++;
            } else if (userList[key].major === '经济与管理学院') {
                major[0].value[7]++;
            } else if (userList[key].major === '其他学院') {
                major[0].value[8]++;
            }
        } else if (userList[key].target === '智能组') {
            if (userList[key].major === '计算机与信息学院') {
                major[1].value[0]++;
            } else if (userList[key].major === '电气与新能源学院') {
                major[1].value[1]++;
            } else if (userList[key].major === '机械与动力学院') {
                major[1].value[2]++;
            } else if (userList[key].major === '水利与环境学院') {
                major[1].value[3]++;
            } else if (userList[key].major === '理学院') {
                major[1].value[4]++;
            } else if (userList[key].major === '医学院') {
                major[1].value[5]++;
            } else if (userList[key].major === '土木与建筑学院') {
                major[1].value[6]++;
            } else if (userList[key].major === '经济与管理学院') {
                major[1].value[7]++;
            } else if (userList[key].major === '其他学院') {
                major[1].value[8]++;
            }
        } else if (userList[key].target === '机械组') {
            if (userList[key].major === '计算机与信息学院') {
                major[2].value[0]++;
            } else if (userList[key].major === '电气与新能源学院') {
                major[2].value[1]++;
            } else if (userList[key].major === '机械与动力学院') {
                major[2].value[2]++;
            } else if (userList[key].major === '水利与环境学院') {
                major[2].value[3]++;
            } else if (userList[key].major === '理学院') {
                major[2].value[4]++;
            } else if (userList[key].major === '医学院') {
                major[2].value[5]++;
            } else if (userList[key].major === '土木与建筑学院') {
                major[2].value[6]++;
            } else if (userList[key].major === '经济与管理学院') {
                major[2].value[7]++;
            } else if (userList[key].major === '其他学院') {
                major[2].value[8]++;
            }
        } else if (userList[key].target === 'ACM') {
            if (userList[key].major === '计算机与信息学院') {
                major[3].value[0]++;
            } else if (userList[key].major === '电气与新能源学院') {
                major[3].value[1]++;
            } else if (userList[key].major === '机械与动力学院') {
                major[3].value[2]++;
            } else if (userList[key].major === '水利与环境学院') {
                major[3].value[3]++;
            } else if (userList[key].major === '理学院') {
                major[3].value[4]++;
            } else if (userList[key].major === '医学院') {
                major[3].value[5]++;
            } else if (userList[key].major === '土木与建筑学院') {
                major[3].value[6]++;
            } else if (userList[key].major === '经济与管理学院') {
                major[3].value[7]++;
            } else if (userList[key].major === '其他学院') {
                major[3].value[8]++;
            }
        }
    }
    for (var i = 0; i < 9; i++) {
        max[i] = Max(Max(major[0].value[i], major[1].value[i]), Max(major[2].value[i], major[3].value[i]));
    }
    echarts.init(document.getElementById('majorMap')).setOption({
        title: {
            text: '专业分布'
        },
        legend: {
            data: ['开发组', '智能组', '机械组', 'ACM']
        },
        radar: {
            indicator: [
                {name: '计算机与信息学院', max: max[0]},
                {name: '电气与新能源学院', max: max[1]},
                {name: '机械与动力学院', max: max[2]},
                {name: '水利与环境学院', max: max[3]},
                {name: '理学院', max: max[4]},
                {name: '医学院', max: max[5]},
                {name: '土木与建筑学院', max: max[6]},
                {name: '经济与管理学院', max: max[7]},
                {name: '其他学院', max: max[8]}
            ]
        },
        series: [
            {
                name: 'Budget vs spending',
                type: 'radar',
                data: major
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