$(()=>{
    $("#content").html("欢迎你：" + sessionStorage.getItem("username") + "!")
});