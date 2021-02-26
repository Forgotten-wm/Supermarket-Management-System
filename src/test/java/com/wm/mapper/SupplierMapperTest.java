package com.wm.mapper;

import com.wm.pojo.Supplier;
import com.wm.pojo.User;
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
public class SupplierMapperTest {
    @Autowired
    private SupplierMapper supplierMapper;

    @Test
    public void insert(){
        supplierMapper.insert(new Supplier(1,"1","1"));
    }

    @Test
    public void selectAll(){
        System.out.println(supplierMapper.selectAll());
    }

    @Test
    public void count(){
        System.out.println(supplierMapper.count());
    }
    @Test
    public void selectById(){
        System.out.println(supplierMapper.selectById(1));
    }

    @Test
    public void updateById(){
        supplierMapper.updateById(new Supplier(1,"123","1234"));

    }

    @Test
    public void deleteByIds(){
        Integer[] ids ={1};
        supplierMapper.deleteByIds(ids);

    }


}
