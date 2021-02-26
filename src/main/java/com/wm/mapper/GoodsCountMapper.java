package com.wm.mapper;

import com.wm.pojo.Goods;
import com.wm.pojo.GoodsCount;

import java.util.List;

/**
 * @author wm
 */

public interface GoodsCountMapper {



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
     * @param id 商品主键
     * @return 商品信息
     */
    GoodsCount selectById(Integer id);
    /**
     * 依据主键查询商品数量信息
     *
     * @param goodsCount 商品修改信息
     * @return 商品信息
     */
    void updateById(GoodsCount goodsCount);


}
