/* 完美运动框架 des by joezhou
 * obj：要发生运动的元素，object类型。
 * params：运动参数，json类型：
 *     target: 运动目标，json类型。
 *     speedNum：缓冲常数，数值越大，幅度越大，默认10。
 *     period：运动周期，数值越大，运动越快，默认50毫秒。
 *     callback：运动结束后的回调函数，可以省略。
 * */
function move(obj, params) {
    const speedNum = params["speedNum"] ? params["speedNum"] : 10;
    const period = params["period"] ? params["period"] : 50;
    const target = params["target"] ? params["target"] : null;
    const callback = params["callback"] ? params["callback"] : null;
    if (!target) return;

    clearInterval(obj.timer);
    obj.timer = setInterval(() => {
        for (let key in target) {
            if (target.hasOwnProperty(key)) {
                let targetValue = parseInt(target[key]);
                let currentValue = parseInt(css(obj, key));
                if (currentValue === targetValue) {
                    clearInterval(obj.timer);
                    if (callback) callback();
                    return;
                }
                let speed = (targetValue - currentValue) / speedNum;
                speed = speed > 0 ? Math.ceil(speed) : Math.floor(speed);
                obj["style"][key] = currentValue + speed + "px";
            }
        }
    }, period);

    function css(obj, key) {
        return obj["currentStyle"] ? obj["currentStyle"][key] : getComputedStyle(obj)[key];
    }
}