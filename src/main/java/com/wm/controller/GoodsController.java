package com.wm.controller;

import com.github.pagehelper.PageInfo;
import com.wm.mapper.GoodsMapper;
import com.wm.pojo.Goods;
import com.wm.pojo.User;
import com.wm.service.GoodsService;
import com.wm.service.UserService;
import com.wm.until.BindingResultUtil;
import com.wm.until.JsonData;
import com.wm.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

/**
 * @author wm
 */
@RequestMapping("/api/goods/")
@Controller
public class GoodsController {

    private GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }


    @ResponseBody
    @RequestMapping("paging")
    public JsonData paging(Integer pageNum, Integer pageSize) {
        PageInfo<Goods> goods = goodsService.paging(pageNum, pageSize);
        if (goods == null) {
            return new JsonData(500, "分页查询失败");
        }
        return new JsonData(goods);

    }
    @ResponseBody
    @RequestMapping("selectAll")
    public JsonData selectAll() {
        List<Goods> goods = goodsService.selectAll();
        if (goods == null) {
            return new JsonData(500, "分页查询失败");
        }
        return new JsonData(goods);

    }

    @ResponseBody
    @RequestMapping("select-by-id")
    public JsonData selectById(@NotNull Integer id) {
        Goods goods = goodsService.selectById(id);
        if (goods == null) {
            return new JsonData(500, "详细信息查询失败");
        }
        return new JsonData(goods);

    }


    @ResponseBody
    @RequestMapping("delete-by-ids")
    public JsonData deleteById(Integer[] ids ) {
        goodsService.deleteByIds(ids);
        return new JsonData(200, "删除成功!");
    }



    @ResponseBody
    @RequestMapping("update-by-ids")
    public JsonData update(@NotNull Goods goods) {
        goodsService.updateById(goods);
        return new JsonData(200, "修改成功!");
    }

    @ResponseBody
    @RequestMapping("purchase")
    public JsonData purchase(@Validated(GoodsVo.Purchase.class) GoodsVo goodsVo, BindingResult bindingResult) throws IOException {

        if (bindingResult.hasErrors()){
            BindingResultUtil.print(bindingResult);
            return new JsonData(500, "添加失败");
        }

        goodsService.purchase(goodsVo);
    return new JsonData(200, "添加成功!");
    }



}
