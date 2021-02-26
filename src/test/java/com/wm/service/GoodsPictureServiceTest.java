package com.wm.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.stream.XMLOutputFactory;

/**
 * @author wm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/app-*.xml")
public class GoodsPictureServiceTest {
    @Autowired
    private GoodsPictureService goodsPictureService;

    @Test
    public void selectByGoodsId(){
        System.out.println(goodsPictureService.selectByGoodsId(1));
    }
}
