const APP_NAME = "/pro_supermarket_sys/";
let screen, timer;



$(() => {
    screen = $("#screen").attr("scrolling", "no");

    screen.on("load", () => {
        clearTimeout(timer);
        timer = setInterval(() => {
            let targetHeight = screen[0].contentWindow.document.body.scrollHeight;
            move(screen[0], {
                "target": {"height": targetHeight}, "period": 20
            });
        }, 100);
    })
});
