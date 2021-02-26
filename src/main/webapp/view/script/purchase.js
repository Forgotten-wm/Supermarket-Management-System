const APP_NAME = "/pro_supermarket_sys/";
let goodsPictureArea = 0;


$(() => {
    findAllSuppliers();
    $("#goods-picture-add-btn").click(() => goodsPictureAddBtn());
    $("#goods-picture-delete-btn").click(() => goodsPictureDeleteBtn());
    $("#goods-insert-reset-btn").click(() => goodsInsertResetBtn())
    $("#goods-insert-submit-btn").click(() => goodsInsertSubmitBtn())
});


function findAllSuppliers() {
    $.ajax({
        "url": `${APP_NAME}api/supplier/selectAll.action`,
        "type": "post",
        "success": response => {
            if (response["status"] === 200) {
                let supplierSel = $("#supplier-sel").html("");
                let option = $(`<option value="">请选择供货商家</option>`).appendTo(supplierSel);
                $.each(response["data"], (i, v) => {
                    let option = $(`<option value="${v['id']}" >${v["name"]}</option>`).appendTo(supplierSel);
                })
            } else {
                console.log(response["msg"]);
            }

        }

    })

}

function goodsPictureAddBtn() {
    let num = goodsPictureArea;
    goodsPictureArea = goodsPictureArea + 1;

    let goodsPictureAreaDiv = $("#goods-picture-area-div");
    $(`
 <article id="goodsPictureArea${num}" class="modal-body row">
                    <div class="col-xs-7">
                            <label hidden="hidden">
                            <input id="hidden-upload-ipt${num}" type="file" name="pictureFile">
                        </label>
                        <label class="input-group">
                            <input id="upload-ipt${num}"  name="picture.src" type="text" class="form-control" readonly="readonly"
                                   disabled="disabled" placeholder="请选择图片..." required="required">
                            <span class="input-group-btn">
                                <button id="upload-ipt-btn${num}" type="button" class="btn btn-primary">
                                    文件浏览
                                </button>
                            </span>
                        </label>

                        <label class="input-group">
                            <input id="picture-title-ipt${num}" name="picture.title" type="text" class="form-control"
                                   placeholder="请填写文件标题..." required="required">
                        </label>

                        <label>
                            <textarea id="picture-information-ipt${num}" name="picture.information" rows="5"
                                      class="form-control" placeholder="请填写文件信息..."></textarea>
                        </label>
                    </div>

                    <div class="col-xs-5">
                        <img id="preview-img${num}" src="/picture/default.jpg">
                    </div>
                </article>
`).appendTo($("#goods-picture-area-div"));

    $(`#upload-ipt-btn${num}`).click(() => realUploadBtn());
    $(`#hidden-goods-id-ipt${num}`);
    let previewImg = $(`#preview-img${num}`);
    let uploadIpt = $(`#upload-ipt${num}`);


    function realUploadBtn() {
        realUploadIpt = $(`#hidden-upload-ipt${num}`);
        realUploadIpt.click();


        realUploadIpt.change((e) => {
                preview = e.target.files[0];
                if (realUploadIpt.val() !== "") {
                    if (!RegExp(/\.jpg$|\.png$/i).test(realUploadIpt.val())) {
                        realUploadIpt.val("");
                        alert("选择必须是png格式或者jpg格式");
                    } else if (preview.size > 1024 * 1024 * 10) {
                        realUploadIpt.val("");
                        alert("图片过大");
                    } else {
                        renderUploadForm(preview["name"]);

                    }
                }

            }
        );
    }


    function renderUploadForm(fileName) {
        uploadIpt.val(fileName);
        renderPreviewImg();


    }

    function renderPreviewImg() {
        let reader = new FileReader();
        reader.readAsDataURL(preview);
        reader.onload = function () {
            previewImg.attr("src", this.result);
        }
    }

}

function goodsPictureDeleteBtn() {
    console.log(goodsPictureArea);
    $("#goods-picture-area-div")[0].removeChild($("#goods-picture-area-div")[0].childNodes[`${goodsPictureArea}`]);
    goodsPictureArea = goodsPictureArea - 1;

}

function goodsInsertResetBtn() {
    $("#goods-insert-form")[0].reset();
    goodsPictureArea = 0;
    $("#goods-picture-area-div")[0].remove()
}

function goodsInsertSubmitBtn() {
    $(`input[type="file"]`).each((i, v) => {
        if ($(v).val() === "") {
            alert("至少有一张图片信息不合法");
            console.log("至少有一张图片信息不合法");
            return false;
        }
    });
    if ($("#supplier-sel").val() === "") {
        alert("必须选择一个供货商");
        console.log("必须选择一个供货商");
        return false;
    }

    $.ajax({
        "url": `${APP_NAME}api/goods/purchase.action`,
        "type": "post",
        "data": new FormData($("#goods-insert-form")[0]),
        "processData": false,
        "contentType": false,
        "dataType": "json",
        "success": response => {
            if (response["status"] === 200) {
                location.href = `${APP_NAME}/view/html/goods.html`;
            } else {
                console.log(response["msg"]);
            }

        }

    })
}