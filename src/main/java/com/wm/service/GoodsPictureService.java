package com.wm.service;

import com.wm.pojo.GoodsPicture;

import java.util.List;

/**
 * @author wm
 */

public interface GoodsPictureService {

    /**
     * 依据主键查询商品-图片信息
     * @param goodsId 商品主键
     * @return 商品-图片信息
     */
    List<GoodsPicture> selectByGoodsId(Integer goodsId);
}
