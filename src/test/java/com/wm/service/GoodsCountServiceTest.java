package com.wm.service;

import com.wm.pojo.GoodsCount;
import com.wm.vo.GoodsCountVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author wm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/app-*.xml")
public class GoodsCountServiceTest {
    @Autowired
    private GoodsCountService goodsCountService;

    @Test
    public void selectByGoodsId(){
        System.out.println(goodsCountService.selectByGoodsId(1));
    }


    @Test
    public void updateById(){
        Integer[] ids = {1,2};
        Integer[] counts = {1,1};
        GoodsCountVo goodsCountVo = new GoodsCountVo();
        goodsCountVo.setIds(ids);
        goodsCountVo.setCounts(counts);
        goodsCountService.updateById(goodsCountVo);
    }

}
