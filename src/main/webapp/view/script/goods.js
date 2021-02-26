const APP_NAME = "/pro_supermarket_sys/";
let pageSizeSel, navigatePageNumsOl;
let pageNum, pageSize, total, pages, navigatePageNums;

$(() => {
    pageSizeSel = $("#paging-size-sel").change(() => changePageSize());
    $("#delete-btn").click(() => deleteByIds());
    $("#insert-btn").click(() => insert());
    paging(1, 1);

});

function insert() {
    location.href = `${APP_NAME}view/html/purchase.html`
}

function changePageSize() {
    paging(1, pageSizeSel.val())
}

function deleteByIds() {
    if ($(".checkbox:checked").length === 0) {
        alert("不可以删除空元素");
    } else {
        if (confirm("删除商品信息会连同图片信息一起删除,确认删除吗？")) {
            $.ajax({
                "url": `${APP_NAME}api/goods/delete-by-ids`,
                "type": "post",
                "data": $("#delete-form").serialize(),
                "success": response => {
                    alert(response["msg"]);
                    if (response["status"] === 200) {
                        location.href = `${APP_NAME}/view/html/goods.html`;
                    }
                }
            });
        }

    }
}

function paging(pageNum, pageSize) {
    $.ajax({
        "url": `${APP_NAME}api/goods/paging`,
        "type": "post",
        "data": {"pageNum": pageNum, "pageSize": pageSize},
        "success": response => {
            if (response["status"] === 200) {
                renderTable(response["data"]["list"]);
                renderPaging(response["data"]);
            } else {
                console.log(response["msg"]);
            }
        }
    });
}

function renderTable(goods) {
    let goodsListTHead = $("#goods-list-thead").html("");
    let headTr = $(`<tr></tr>`).appendTo(goodsListTHead);
    $(`<th><label><input id="select-all-cbx" type="checkbox"/></label></th>`).appendTo(headTr);
    $(`<th>编号</th><th>商品名称</th><th>商品类型</th><th>商品价格</th><th>商品单位</th><th>供货商</th><th>操作</th>`).appendTo(headTr);
    let goodsListTbody = $("#goods-list-tbody").html("");
    $.each(goods, (i, v) => {
        let tr = $("<tr></tr>").appendTo(goodsListTbody);
        $(`<td><label><input name="ids" class="checkbox" type="checkbox" value='${v["id"]}'/></td></label>`).appendTo(tr);
        $(`<td>${i + 1}</td>`).appendTo(tr);
        $(`<td>${v["name"] !== undefined ? v["name"] : ""}</td>`).appendTo(tr);
        $(`<td>${v["type"] !== undefined ? v["type"] : ""}</td>`).appendTo(tr);
        $(`<td>${v["price"] !== undefined ? v["price"] : ""}</td>`).appendTo(tr);
        $(`<td>${v["unit"] !== undefined ? v["unit"] : ""}</td>`).appendTo(tr);
        $(`<td>${v["supplierId"]["name"] !== undefined ? v["supplierId"]["name"] : ""}</td>`).appendTo(tr);
        let updateTd = $(`<td></td>`).appendTo(tr);
        let informationBtn = $(`<button data-target="#information-sec" data-toggle="modal" type="button" class="btn btn-info">详细信息</button>`);

        informationBtn.click(() => {
            let modalBody = $("#modal-body");
            modalBody.html("");
            $(`<span>商品名称:</span><span>${v["name"] !== undefined ? v["name"] : ""}</span><br>`).appendTo(modalBody);
            $(`<span>商品类型:</span><span>${v["type"] !== undefined ? v["type"] : ""}</span><br>`).appendTo(modalBody);
            $(`<span>商品价格:</span><span>${v["price"] !== undefined ? v["price"] : ""}</span><br>`).appendTo(modalBody);
            $(`<span>商品单位:</span><span>${v["unit"] !== undefined ? v["unit"] : ""}</span><br>`).appendTo(modalBody);
            $(`<span>生产日期:</span><span>${v["productionDate"] !== undefined ? dateFormat(v["productionDate"]) : ""}</span><br>`).appendTo(modalBody);
            $(`<span>过期日期:</span><span>${v["expiryDate"] !== undefined ? dateFormat(v["expiryDate"]) : ""}</span><br>`).appendTo(modalBody);
            $(`<span>供货商名称:</span><span>${v["supplierId"]["name"] !== undefined ? v["supplierId"]["name"] : ""}</span><br>`).appendTo(modalBody);
            $(`<span>供货商地址:</span><span>${v["supplierId"]["loc"] !== undefined ? v["supplierId"]["loc"] : ""}</span><br>`).appendTo(modalBody);
            $(`<span>商品信息:</span><span>${v["information"] !== undefined ? v["information"] : ""}</span><br>`).appendTo(modalBody);

        }).appendTo(updateTd);

        let updateBtn = $(`<a href="javascript:" class="btn btn-danger">修改</a>`);
        updateBtn.click(() => {
            sessionStorage.setItem("id", v["id"]);
            location.href = `${APP_NAME}/view/html/goods-update.html`;
        }).appendTo(updateTd);

        let pictureBtn = $(`<a href="javascript:" class="btn btn-default" >相册</a>`);
        pictureBtn.click(() => {
            sessionStorage.setItem("id", v["id"]);
            location.href = `${APP_NAME}view/html/picture.html`;
        }).appendTo(updateTd);
    });

    let selectAllCbx = $("#select-all-cbx").click(() => selectSonCbx());
    let allCbx = $(".checkbox").click(() => selectSuperCbx());

    function selectSonCbx() {
        if (selectAllCbx[0].checked) {
            for (let e of allCbx) {
                e.checked = true;
            }
        } else {
            for (let e of allCbx) {
                e.checked = false;
            }
        }

    }

    function selectSuperCbx() {
        for (let e of allCbx) {
            if (e.checked) {
                selectAllCbx[0].checked = true;
            } else {
                selectAllCbx[0].checked = false;
            }
        }
    }

}

function renderPaging(pageInfo) {
    pageNum = pageInfo["pageNum"];
    pageSize = pageInfo["pageSize"];
    pages = pageInfo["pages"];
    total = pageInfo["total"];
    navigatePageNums = pageInfo["navigatepageNums"];
    navigatePageNumsOl = $("#paging-numbers-ol").html("");
    renderPrevAndFirstBtn();
    renderNavigatePageNums();
    renderLastAndNextBtn();
    renderPagingMessage();
    updatePagingSize();

    function renderPrevAndFirstBtn() {
        if (pageNum === 1) {
            $(`<li class="disabled"><a>&laquo;</a></li>`).appendTo(navigatePageNumsOl);
            $(`<li class="disabled"><a>首页</a></li>`).appendTo(navigatePageNumsOl);
        } else {
            $(`<li class="item"><a>&laquo;</a></li>`).click(() => paging(pageNum - 1, pageSize)).appendTo(navigatePageNumsOl);
            $(`<li class="item"><a>首页</a></li>`).click(() => paging(1, pageSize)).appendTo(navigatePageNumsOl);
        }
    }

    function renderNavigatePageNums() {
        $.each(navigatePageNums, (i, v) => {
            let li = $(`<li class="item"><a>${v}</a></li>`).appendTo(navigatePageNumsOl);
            if (v === pageNum) {
                li.addClass("active");
            } else {
                li.click(() => paging(v, pageSize));
            }
        });
    }

    function renderLastAndNextBtn() {
        if (pageNum === pages) {
            $(`<li class="disabled"><a>尾页</a></li>`).appendTo(navigatePageNumsOl);
            $(`<li class="disabled"><a>&raquo;</a></li>`).appendTo(navigatePageNumsOl);
        } else {
            $(`<li class="item"><a>尾页</a></li>`).click(() => paging(pages, pageSize)).appendTo(navigatePageNumsOl);
            $(`<li class="item"><a>&raquo;</a></li>`).click(() => paging(pageNum + 1, pageSize)).appendTo(navigatePageNumsOl);
        }
    }

    function renderPagingMessage() {
        $(`<li><a>当前是第&nbsp;${pageNum}&nbsp;/&nbsp;${pages}&nbsp;页，共&nbsp;${total}&nbsp;条数据</a></li>`).appendTo(navigatePageNumsOl);
    }

    function updatePagingSize() {
        pageSizeSel.val(pageSize);
    }


}



