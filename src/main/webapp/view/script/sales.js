const APP_NAME = "/pro_supermarket_sys/";
let goodsNameSel, id = null, max = new Array(), name = null, type = null, price = null, addFormNum = 0, goodsInfoDiv;


$(() => {
    goodsNameSel = $("#goods-name-sel");
    goodsInfoDiv = $("#goods-info-div");
    $("#goods-add-btn").click(() => goodsAddBtn());
    $("#goods-name-btn").click(() => goodsNameBtn());
    $("#sale-add-btn").click(() => saleAddBtn());
    $("#goods-sale-delete-btn").click(() => goodsSaleDeleteBtn());
    $("#goods-sale-reset-btn").click(() => resetBtn());
    $("#goods-sale-submit-btn").click(() => submitBtn());

});
function submitBtn() {
    $.ajax({
        "url": `${APP_NAME}api/goodsCount/sale`,
        "type": "post",
        "data": $("#goods-sale-form").serialize(),
        "success": response => {
            if (response["status"] === 200) {
                goodsNameSel.html("");
                $(`<option value="">选择商品</option>`).appendTo(goodsNameSel);
                $.each(response["data"], (i, v) => {
                    $(`<option value=${v["id"]}>${v["name"]}</option>`).appendTo(goodsNameSel);
                })

            }
        }
    });
    
}

function goodsAddBtn() {
    $.ajax({
        "url": `${APP_NAME}api/goods/selectAll`,
        "type": "post",
        "success": response => {
            if (response["status"] === 200) {
                goodsNameSel.html("");
                $(`<option value="">选择商品</option>`).appendTo(goodsNameSel);
                $.each(response["data"], (i, v) => {
                    $(`<option value=${v["id"]}>${v["name"]}</option>`).appendTo(goodsNameSel);
                })

            }
        }
    });
}

function goodsNameBtn() {
    if (goodsNameSel.val() === "") {
        alert("请选择一个商品");
        return false;
    }

    $.ajax({
        "url": `${APP_NAME}api/goods/select-by-id`,
        "type": "post",
        "data": {"id": goodsNameSel.val()},
        "success": response => {
            if (response["status"] === 200) {
                goodsInfoDiv.html("");
                $(`<span>商品名称:</span><span>${response["data"]["name"] !== undefined ? response["data"]["name"] : ""}</span><br>`).appendTo(goodsInfoDiv);
                $(`<span>商品类型:</span><span>${response["data"]["type"] !== undefined ? response["data"]["type"] : ""}</span><br>`).appendTo(goodsInfoDiv);
                $(`<span>商品价格:</span><span>${response["data"]["price"] !== undefined ? response["data"]["price"] : ""}</span><br>`).appendTo(goodsInfoDiv);
                $(`<span>商品单位:</span><span>${response["data"]["unit"] !== undefined ? response["data"]["unit"] : ""}</span><br>`).appendTo(goodsInfoDiv);
                $(`<span>生产日期:</span><span>${response["data"]["productionDate"] !== undefined ? dateFormat(response["data"]["productionDate"]) : ""}</span><br>`).appendTo(goodsInfoDiv);
                $(`<span>过期日期:</span><span>${response["data"]["expiryDate"] !== undefined ? dateFormat(response["data"]["expiryDate"]) : ""}</span><br>`).appendTo(goodsInfoDiv);
                $(`<span>供货商名称:</span><span>${response["data"]["supplierId"]["name"] !== undefined ? response["data"]["supplierId"]["name"] : ""}</span><br>`).appendTo(goodsInfoDiv);
                $(`<span>供货商地址:</span><span>${response["data"]["supplierId"]["loc"] !== undefined ? response["data"]["supplierId"]["loc"] : ""}</span><br>`).appendTo(goodsInfoDiv);
                $(`<span>商品信息:</span><span>${response["data"]["information"] !== undefined ? response["data"]["information"] : ""}</span><br>`).appendTo(goodsInfoDiv);
                id = response["data"]["id"];
                name = response["data"]["name"];
                type = response["data"]["type"];
                price = response["data"]["price"];
            }
        }
    });

}

function saleAddBtn() {

    if (id === null || name === null || price === null || type === null) {
        alert("请选择一个商品");
        return false;
    }
    addForm();

    goodsInfoDiv.html("");
    $("#sale-art").modal("hide");
    id = null;
    name = null;
    type = null;
    price = null;
}


function addForm() {
    let num = addFormNum;
    addFormNum = addFormNum + 1;
    $.ajax({
        "url": `${APP_NAME}api/goodsCount/select-by-goods-id`,
        "type": "post",
        "data": {"goodsId": id},
        "success": response => {
            if (response["status"] === 200) {
                max[num] = response["data"]["count"];
            }else {
                max[num] = 0;

            }

            renderForm(num,response["data"]["goodsId"],response["data"]);
        }
    });



}
function renderForm(num,data,goodsCount) {

    $(`<div id="goods${num}" name="goods-sale" class="col-xs-12">
 <label hidden="hidden">
                    <span>商品id</span>
                    <input name="ids" required="required" value="${goodsCount["id"]}">
                </label>
                <div  class="col-xs-1">
                <label>
                    <input id="goods-num${num}" type="checkbox" class="checkbox" value="${num}">
                </label>
                </div>
                <div class="col-xs-3">
                    <label class="input-group">
                        <span class="input-group-addon">名称</span>
                        <input class="form-control" placeholder="请输入商品名称" required="required" value="${data["name"]}">
                    </label>
                </div>
                <div class="col-xs-3">
                    <label class="input-group ">
                        <span class="input-group-addon">类型</span>
                        <input  class="form-control" placeholder="请输入商品类型" required="required"  value="${data["type"]}">
                    </label>
                </div>
                <div class="col-xs-2">
                    <label class="input-group ">
                        <span class="input-group-addon">单价</span>
                        <input id="price${num}" class="form-control" placeholder="请输入商品单价" required="required" value="${data["price"]}">
                        <span class="input-group-addon">元</span>
                    </label>
                </div>
                <div class="col-xs-2">
                    <label class="input-group ">
                        <span class="input-group-addon">数量</span>
                        <input id="count${num}" name="counts" type="number" min="0" max=${max} class="form-control" value="0"
                               required="required">
                    </label>
                </div>
                 <div hidden="hidden">
                    <label class="input-group ">
                        <span class="input-group-addon">总价</span>
                        <input id="total${num}"  class="form-control" value="0"
                               required="required">
                    </label>
                </div>
                  <div class="col-xs-1">
                    <label class="input-group ">
                        <span id="goods-span" style="color: red" ></span>
                    </label>
                </div>


</div>`).appendTo($("#goods-sale"));
    if (max[num] === 0) {
        $(`<span>缺货<span></span>`).appendTo($("#goods-span"));
    }

    $(`#count${num}`).change(() => {

        if ($(`#count${num}`).val() > max[num]) {
            $(`#count${num}`).val(max[num]);
        }
        $(`#total${num}`).val($(`#count${num}`).val() * $(`#price${num}`).val());
        totalPrice();

    });
}

function totalPrice() {
    let totalIpt = 0;
    for (let i = 0, j = addFormNum; i < j; i++) {
        totalIpt = totalIpt + Number($(`#total${i}`).val());
    }
    $("#total").val(totalIpt);
}

function goodsSaleDeleteBtn() {
    $.each($(`input[type="checkbox"]`), (i, v) => {
        if (v.checked) {
            $("#goods-sale")[0].removeChild($("#goods-sale")[0].childNodes[Number(v["value"])+1]);

        }
    });

}

function resetBtn() {
    $(`div[name="goods-sale"]`).remove();
    $("#goods-sale-form")[0].reset();
    id = null;max = new Array(); name = null; type = null; price = null; addFormNum = 0;
}