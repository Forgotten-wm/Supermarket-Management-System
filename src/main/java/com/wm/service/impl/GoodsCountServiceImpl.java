package com.wm.service.impl;

import com.wm.mapper.GoodsCountMapper;
import com.wm.pojo.Goods;
import com.wm.pojo.GoodsCount;
import com.wm.service.GoodsCountService;
import com.wm.vo.GoodsCountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wm
 */

@Service
public class GoodsCountServiceImpl implements GoodsCountService {

    private GoodsCountMapper goodsCountMapper;

    @Autowired
    public GoodsCountServiceImpl(GoodsCountMapper goodsCountMapper) {
        this.goodsCountMapper = goodsCountMapper;
    }

    @Override
    public GoodsCount selectByGoodsId(Integer goodsId) {
        if (goodsId == null || goodsId <= 0) {
            throw new RuntimeException("参数异常");
        }
        GoodsCount goodsCount = goodsCountMapper.selectByGoodsId(goodsId);
        if (goodsCount == null) {
            throw new RuntimeException("结果异常");
        }
        return goodsCount;
    }

    @Transactional(rollbackForClassName = "Exception")
    @Override
    public void updateById(GoodsCountVo goodsCountVo) {
        Integer[] ids, counts;
        GoodsCount goodsCount;

        if (goodsCountVo == null || (ids = goodsCountVo.getIds()) == null || ids.length <= 0 || (counts = goodsCountVo.getCounts()) == null || counts.length <= 0) {
            throw new RuntimeException("参数异常");
        }

        for (int i = 0, j = ids.length; i < j; i++) {
            goodsCount = goodsCountMapper.selectById(ids[i]);
            goodsCount.setCount(goodsCount.getCount() - counts[i]);
            goodsCountMapper.updateById(goodsCount);

        }

    }
}