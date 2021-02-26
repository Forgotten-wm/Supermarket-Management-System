package com.wm.mapper;

import com.wm.pojo.Goods;
import com.wm.pojo.GoodsCount;
import com.wm.pojo.Supplier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @author wm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/app-*.xml")
public class GoodsCountMapperTest {

    @Autowired
    private GoodsCountMapper goodsCountMapper;

    @Test
    public void selectByGoodsId(){
        goodsCountMapper.selectByGoodsId(1);
    }
    @Test
    public void updateById(){
        GoodsCount goodsCount = goodsCountMapper.selectByGoodsId(1);
        goodsCount.setCount(15);
        goodsCountMapper.updateById(goodsCount);
    }

}
