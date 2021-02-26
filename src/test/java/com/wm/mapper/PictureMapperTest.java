package com.wm.mapper;

import com.wm.pojo.Picture;
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
public class PictureMapperTest {

    @Autowired
    private PictureMapper pictureMapper;

    @Test
    public void insert(){
        pictureMapper.insert(new Picture(1,"1","123","123"));
    }

    @Test
    public void selectAll(){
        System.out.println(pictureMapper.selectAll());
    }

    @Test
    public void count(){
        System.out.println(pictureMapper.count());
    }
    @Test
    public void selectById(){
        System.out.println(pictureMapper.selectById(1));
    }

    @Test
    public void updateById(){
        pictureMapper.updateById(new Picture(1,"123","123","1234"));

    }

    @Test
    public void deleteByIds(){
        Integer[] ids ={1};
        pictureMapper.deleteByIds(ids);

    }




}
