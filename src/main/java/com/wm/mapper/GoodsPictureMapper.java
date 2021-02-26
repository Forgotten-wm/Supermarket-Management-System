package com.wm.mapper;

import com.wm.pojo.GoodsPicture;
import com.wm.pojo.User;

import java.util.List;

/**
 * @author wm
 */

public interface GoodsPictureMapper {

    /**
     * 添加商品-图片信息
     * @param goodsPicture 商品-图片信息
     */
    void insert(GoodsPicture goodsPicture);

    /**
     * 查询所有商品-图片信息
     * @return 所有商品-图片信息
     */
    List<GoodsPicture> selectAll();

    /**
     * 查询商品-图片数量
     * @return 商品-图片数量
     */
    int count();

    /**
     * 依据主键查询商品-图片信息
     * @param id 主键
     * @return 商品-图片信息
     */
    GoodsPicture selectById(Integer id);
    /**
     * 依据主键查询商品-图片信息
     * @param goodsId 商品主键
     * @return 商品-图片信息
     */
    List<GoodsPicture> selectByGoodsId(Integer goodsId);

    /**
     * 依据主键查询商品-图片信息
     * @param goodsId 商品主键
     * @return 商品-图片信息
     */
    List<GoodsPicture> selectByGoodsIds(Integer[] goodsId);

    /**
     * 依据主键修改商品-图片信息
     * @param goodsPicture 商品-图片实体
     */
    void updateById(GoodsPicture goodsPicture);

    /**
     * 依据主键删除商品-图片信息
     * @param ids 主键
     */
    void deleteByIds(Integer[] ids);
    /**
     * 依据商品主键删除商品-图片信息
     * @param goodsIds 商品主键
     */
    void deleteByGoodsIds(Integer[] goodsIds);

    /**
     * 依据图片id删除商品-图片信息
     * @param pictureId 图片id
     */
    void deleteByPictureIds(Integer[] pictureId);
}
