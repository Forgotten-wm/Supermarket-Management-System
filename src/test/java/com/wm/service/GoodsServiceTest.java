package com.wm.service;

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
public class GoodsServiceTest {
    @Autowired
    private GoodsService goodsService;

    @Test
    public void paging(){
        System.out.println(goodsService.paging(1, 1));
    }

    @Test
    public void selectById(){
        System.out.println(goodsService.selectById(1));
    }

    @Test
    public void deleteByIds(){
        Integer[] ids = {1};
        goodsService.deleteByIds(ids);
    }
}
