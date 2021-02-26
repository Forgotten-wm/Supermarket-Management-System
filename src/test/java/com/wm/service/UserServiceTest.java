package com.wm.service;

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
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void login(){
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123");
        System.out.println(userService.login(user));
    }


}
