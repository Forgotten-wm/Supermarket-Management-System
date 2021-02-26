package com.wm.mapper;

import com.wm.pojo.Goods;
import com.wm.pojo.Picture;
import com.wm.pojo.User;

import java.util.List;

/**
 * @author wm
 */

public interface GoodsMapper {


    /**
     * 添加商品信息
     *
     * @param goods 商品信息
     */
    void insert(Goods goods);

    /**
     * 查询所有商品信息
     *
     * @return 所有商品信息
     */
    List<Goods> selectAll();

    /**
     * 查询商品数量
     *
     * @return 商品数量
     */
    int count();

    /**
     * 依据主键查询商品信息
     *
     * @param id 主键
     * @return 商品信息
     */
    Goods selectById(Integer id);

    /**
     * 依据主键修改商品信息
     *
     * @param goods 商品实体
     */
    void updateById(Goods goods);

    /**
     * 遗迹主键删除商品信息
     *
     * @param ids 主键
     */
    void deleteByIds(Integer[] ids);
}
