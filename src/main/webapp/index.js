const APP_NAME = "/pro_supermarket_sys/";
let login_from, error_msg;

$(() => {
    login_from = $("#login-form");
    error_msg = $("#error-msg")

    login_from.submit(submitLoginFrom);
    $("#rest-btn").click(resetLoginFrom)
});

function submitLoginFrom() {

    $.ajax({
        "url": `${APP_NAME}api/user/login.action`,
        "type": "post",
        "data": $(this).serialize(),
        "success": response => {
            console.log(response)
            if (response["status"] === 200) {
                sessionStorage.setItem("username", response["data"]["username"]);
                location.href = `${APP_NAME}view/html/main.html`;
            } else {
                resetLoginFrom();
                error_msg.text("对不起，您输入的账号或密码有误，请重新输入！").show();
            }
        }

    })
}

function resetLoginFrom() {
    if(error_msg.css("display") === "block"){
        error_msg.css("display","none");
    }

    login_from[0].reset();
    $("#username-ipt").focus();
}