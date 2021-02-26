package com.wm.mapper;

import com.wm.pojo.*;
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
public class GoodsPictureMapperTest {
    @Autowired
    private GoodsPictureMapper goodsPictureMapper;

    @Test
    public void insert(){

        goodsPictureMapper.insert(new GoodsPicture(1,new Goods(5,"4","1",10,"1",new Date(),new Date(),new Supplier(2,"1","1"),"1"),new Picture(2,"1","1","1")));
    }


    @Test
    public void selectAll(){
        System.out.println(goodsPictureMapper.selectAll());
    }

    @Test
    public void count(){
        System.out.println(goodsPictureMapper.count());
    }

    @Test
    public void selectById(){
        System.out.println(goodsPictureMapper.selectById(1));
    }
   @Test
    public void selectByGoodsId(){
        System.out.println(goodsPictureMapper.selectByGoodsId(1));
    }

    @Test
    public void updateById(){
        goodsPictureMapper.updateById(new GoodsPicture(4,new Goods(4,"4","1",10,"1",new Date(),new Date(),new Supplier(2,"1","1"),"1"),new Picture(2,"1","1","1")));


    }

    @Test
    public void deleteByIds(){
        Integer[] ids = new Integer[1];
        ids[0]=1;
        goodsPictureMapper.deleteByIds(ids);

    }


    @Test
    public void deleteByGoodsIds(){
        Integer[] ids ={1};
        goodsPictureMapper.deleteByGoodsIds(ids);

    }
    @Test
    public void selectByGoodsIds(){
        Integer[] ids ={1};
        System.out.println(goodsPictureMapper.selectByGoodsIds(ids));

    }


}
