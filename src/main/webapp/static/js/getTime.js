function getDate() {
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var dates = date.getDate();
    var day = date.getDay();
    var hour = date.getHours();
    var min = date.getMinutes();
    var sec = date.getSeconds();
    var arr = ['日','一', '二', '三', '四', '五', '六'];
    return year + '年' + setZero(month) + '月' + setZero(dates) + '日' + '星期' + arr[day]+ ' ' + setZero(hour) + ":" + setZero(min) + ":" + setZero(sec);
}

function setZero(n) {
    if (n <= 9) {
        return "0" + n;
    } else {
        return "" + n;
    }
}

function Hello() {
    var date = new Date();
    var hour = date.getHours();
    if (hour >= 0 && hour < 6) {
        return "夜深了，请早睡";
    } else if (hour >= 6 && hour < 8) {
        return "早晨好，";
    } else if (hour >= 8 && hour < 11) {
        return "上午好，";
    } else if (hour >= 8 && hour < 11) {
        return "上午好，";
    } else if (hour >= 11 && hour < 13) {
        return "中午好，";
    } else if (hour >= 13 && hour < 18) {
        return "下午好，";
    } else if (hour >= 18 && hour < 20) {
        return "傍晚好，";
    } else if (hour >= 20 && hour < 24) {
        return "晚上好，";
    }
}