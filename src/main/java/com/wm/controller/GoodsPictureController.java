package com.wm.controller;

import com.wm.pojo.GoodsPicture;
import com.wm.service.GoodsPictureService;
import com.wm.service.GoodsService;
import com.wm.until.JsonData;
import com.wm.vo.PictureVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BulkBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author wm
 */
@RequestMapping("/api/goodsPicture/")
@Controller
public class GoodsPictureController {
    private GoodsPictureService goodsPictureService;

    @Autowired
    public GoodsPictureController(GoodsPictureService goodsPictureService) {
        this.goodsPictureService = goodsPictureService;
    }

    @ResponseBody
    @RequestMapping("select-picture")
    public JsonData selectPicture(@NotNull Integer goodsId) {

        List<GoodsPicture> goodsPictures = goodsPictureService.selectByGoodsId(goodsId);

        if (goodsPictures == null) {
            return new JsonData(500, "查询图片失败");
        }

        return new JsonData(goodsPictures);
    }



}
