package com.wm.mapper;

import com.wm.pojo.Goods;
import com.wm.pojo.Supplier;
import com.wm.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.metadata.ValidateUnwrappedValue;
import java.util.Date;

/**
 * @author wm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/app-*.xml")
public class GoodsMapperTest {

    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    public void insert(){
        goodsMapper.insert(new Goods(1,"4","1",10,"1",new Date(),new Date(),new Supplier(2,"1","1"),"1"));
    }
    @Test
    public void selectAll(){
        System.out.println(goodsMapper.selectAll());
    }

    @Test
    public void count(){
        System.out.println(goodsMapper.count());
    }


    @Test
    public void selectById(){
        System.out.println(goodsMapper.selectById(4));
    }


    @Test
    public void updateById(){
        goodsMapper.updateById(new Goods(2,"41","1",10,"1",new Date(),new Date(),new Supplier(2,"1","1"),"1"));

    }

    @Test
    public void deleteByIds(){
        Integer[] ids ={1};
        goodsMapper.deleteByIds(ids);

    }

}
