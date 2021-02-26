const APP_NAME = "/pro_supermarket_sys/";
const id = sessionStorage.getItem("id");

$(() => {
    selectById();
    $("#goods-update-reset-btn").click(() => selectById());
    $("#goods-update-submit-btn").click(() => updateById());

});

function selectById() {
    $.ajax({
        "url": `${APP_NAME}api/goods/select-by-id.action`,
        "data": {"id": id},
        "type": "post",
        "success": response => {
            if (response["status"] === 200) {
                renderForm(response["data"]);
            } else {
                console.log(response["msg"]);
            }

        }

    })
}

function renderForm(goods) {
    $("input[name=id]").val(goods["id"]);
    $("input[name=name]").val(goods["name"]);
    $("input[name='type']").val(goods["type"]);
    $("input[name=price]").val(goods["price"]);
    $("input[name=unit]").val(goods["unit"]);
    $("input[name=productionDate]").val(dateFormat(goods["productionDate"]));
    $("input[name=expiryDate]").val(dateFormat(goods["expiryDate"]));
    $("textarea[name=information]").val(goods["information"]);
    findAllSuppliers(goods["supplierId"]["id"]);
}

function findAllSuppliers(supplierId) {
    $.ajax({
        "url": `${APP_NAME}api/supplier/selectAll.action`,
        "type": "post",
        "success": response => {
            if (response["status"] === 200) {
                let supplierSel = $("#supplier-sel").html("");
                $.each(response["data"], (i, v) => {
                    let option = $(`<option value="${v['id']}" >${v["name"]}</option>`);
                    if (v["id"] === supplierId) {
                        option.attr("selected", "selected");
                    }
                    option.appendTo(supplierSel);
                })
            } else {
                console.log(response["msg"]);
            }

        }

    })

}

function updateById() {

    $.ajax({
        "url": `${APP_NAME}api/goods/update-by-id.action`,
        "data": $("#goods-update-form").serialize(),
        "type": "post",
        "success": response => {
            if (response["status"] === 200) {
                location.href = `${APP_NAME}/view/html/goods.html`;
            } else {
                console.log(response["msg"]);
            }

        }

    })
}