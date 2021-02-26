const APP_NAME = "/pro_supermarket_sys/";
const goodsId = sessionStorage.getItem("id");
let previewImg, uploadPictureForm, realUploadIpt, preview;


$(() => {
    selectPicture();
    $("#upload-picture-btn").click(() => upload());
    $("#upload-ipt-btn").click(() => realUploadBtn());
    $("#hidden-goods-id-ipt").val(goodsId);
    previewImg = $("#preview-img");


});

function selectPicture() {
    $.ajax({
        "url": `${APP_NAME}api/goodsPicture/select-picture.action`,
        "type": "post",
        "data": {"goodsId": goodsId},
        "success": response => {
            if (response["status"] === 200) {
                let pictureSec = $("#picture-sec").html("");
                $.each(response["data"], (i, v) => {
                    $(`<section class="col-xs-3 col-md-3 col-lg-2">
                        <article class="thumbnail">
                            <div style="padding-bottom: 5px;border-bottom: 1px solid #eee;">
                                <a href="javascript:deletePictureByPictureId('${v["pictureId"]["id"]}','${v["pictureId"]["src"]}');" class="btn btn-danger">删除</a>
                            </div>
                            <img src="/picture/${v["pictureId"]["src"]}" alt="lost..."/>
                            <div class="caption" title="${v["pictureId"]["information"]}">
                                <h4 style="text-align: center">${v["pictureId"]["title"]}</h4>
                                <p class="text-muted">${v["pictureId"]["information"]}</p>
                            </div>
                        </article>
                    </section>                 
`).appendTo(pictureSec);

                })
            } else {
                console.log("msg")
            }
        }


    })
}

function deletePictureByPictureId(pictureIdId, pictureIdSrc) {
    $.ajax({
        "url": `${APP_NAME}api/picture/delete-by-id-and-src.action`,
        "type": "post",
        "data": {"picture.id": pictureIdId, "picture.src": pictureIdSrc},
        "success": response => {
            if (response["status"] === 200) {
                selectPicture();
            } else {
                console.log(response["msg"]);
            }
        }

    })

}

function upload() {
    uploadPictureForm = $("#upload-picture-form");
    $.ajax({
        "url": `${APP_NAME}api/picture/insert-for-goods.action`,
        "data": new FormData(uploadPictureForm[0]),
        "type": "post",
        "processData": false,
        "contentType": false,
        "dataType": "json",
        "success": response => {
            if (response["status"] === 200) {
                selectPicture();
            } else {
                console.log(response["msg"]);
            }
            $("#upload-sec").modal("hide");
            uploadPictureForm[0].reset();
            previewImg.attr("src", `${APP_NAME}picture/default.jpg"`)

        }

    })
}

function realUploadBtn() {
    realUploadIpt = $("#hidden-upload-ipt");
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
    $("#upload-ipt").val(fileName);
    renderPreviewImg();


}

function renderPreviewImg() {
    let reader = new FileReader();
    reader.readAsDataURL(preview);
    reader.onload = function () {
        previewImg.attr("src", this.result);
    }
}