package com.wm.mapper;

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
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert(){
        userMapper.insert(new User(1,"123","123"));
    }

    @Test
    public void selectAll(){
        System.out.println(userMapper.selectAll());
    }

    @Test
    public void count(){
        System.out.println(userMapper.count());
    }
   @Test
    public void selectById(){
        System.out.println(userMapper.selectById(2));
    }
 @Test
    public void selectByUsername(){
        System.out.println(userMapper.selectByUsername("admin"));
    }

    @Test
    public void updateById(){
        userMapper.updateById(new User(2,"123","1234"));

    }

    @Test
    public void deleteByIds(){
        Integer[] ids ={1};
        userMapper.deleteByIds(ids);

    }


}
