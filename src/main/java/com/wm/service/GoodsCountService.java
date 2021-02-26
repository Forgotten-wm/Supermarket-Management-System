package com.wm.service;

import com.wm.pojo.Goods;
import com.wm.pojo.GoodsCount;
import com.wm.vo.GoodsCountVo;

/**
 * @author wm
 */

public interface GoodsCountService {

    /**
     * 依据主键查询商品数量信息
     *
     * @param goodsId 商品主键
     * @return 商品信息
     */
    GoodsCount selectByGoodsId(Integer goodsId);

    /**
     * 依据主键查询商品数量信息
     *
     * @param goodsCountVo 销售信息
     */
    void updateById(GoodsCountVo goodsCountVo);
}
