package com.wm.service;

import com.wm.pojo.User;

/**
 * @author wm
 */

public interface UserService {
    /**
     * 登录业务
     * @param user 用户信息
     * @return 非空 登录成功
     */
    User login(User user);
}
