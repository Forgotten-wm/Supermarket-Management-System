package com.wm.service.impl;

import com.wm.mapper.UserMapper;
import com.wm.pojo.User;
import com.wm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wm
 */
@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User login(User user) {
        if (user == null){
            System.out.println("error:用户信息为空");
            return null;
        }

        if (user.getUsername() == null){
            System.out.println("error:用户名为空");
            return null;
        }

        if (user.getPassword() == null){
            System.out.println("error:用户密码为空");
            return null;
        }

        User newUser = userMapper.selectByUsername(user.getUsername());

        if (!user.getPassword().equals(newUser.getPassword())){
            System.out.println("error:密码错误");
            return null;
        }
        return newUser;
    }
}
