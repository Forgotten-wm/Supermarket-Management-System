package com.wm.service;

import com.github.pagehelper.PageInfo;
import com.wm.pojo.Goods;
import com.wm.vo.GoodsVo;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

/**
 * @author wm
 */


public interface GoodsService {

    /**
     * 分页查询商品信息
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 商品信息
     */
    PageInfo<Goods> paging(@NotNull Integer pageNum, @NotNull Integer pageSize);

    /**
     * 依据主键查询商品信息
     *
     * @param id 主键
     * @return 商品信息
     */
    Goods selectById(Integer id);
    /**
     * 依据主键查询全部商品信息
     *
     * @return 商品信息
     */
    List<Goods> selectAll();


    /**
     * 根据主键批量删除商品
     *
     * @param ids 主键数组
     */
    void deleteByIds(Integer[] ids);

    /**
     * 修改商品信息
     *
     * @param goods 商品信息
     */
    void updateById(Goods goods);

    /**
     * 进货业务
     * @param goodsVo 进货信息
     * @throws IOException 图片io异常
     */
    void  purchase(GoodsVo goodsVo) throws IOException;
}
