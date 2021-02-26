
function dateFormat(timeInMilliseconds) {
    let date = new Date(timeInMilliseconds),
        year = date.getFullYear(),
        month = date.getMonth() + 1,
        day = date.getDate(),
        time = year + '-' + zeroPadding(month) + '-' + zeroPadding(day);//最后拼接时间
    return time;
}

function zeroPadding(num) {
    if (parseInt(num) < 10) {
        num = '0' + num;
    }
    return num;
}