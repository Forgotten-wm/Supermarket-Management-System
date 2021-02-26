package com.wm.controller;

import com.wm.pojo.GoodsCount;
import com.wm.service.GoodsCountService;
import com.wm.until.BindingResultUtil;
import com.wm.until.JsonData;
import com.wm.vo.GoodsCountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author wm
 */
@RequestMapping("/api/goodsCount/")
@Controller
public class GoodsCountController {
    private GoodsCountService goodsCountService;

    @Autowired
    public GoodsCountController(GoodsCountService goodsCountService) {
        this.goodsCountService = goodsCountService;
    }

    @RequestMapping("select-by-goods-id")
    @ResponseBody
    public JsonData selectByGoodsId(@NotNull Integer goodsId) {

        GoodsCount goodsCount = goodsCountService.selectByGoodsId(goodsId);
        if (goodsCount == null) {
            return new JsonData(500, "结果异常");
        }
        return new JsonData(goodsCount);
    }

    @RequestMapping("sale")
    @ResponseBody
    public JsonData sale(@Valid GoodsCountVo goodsCountVo) {
        goodsCountService.updateById(goodsCountVo);
        return new JsonData(200, "测试");
    }

}
