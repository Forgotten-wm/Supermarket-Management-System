package com.wm.mapper;

import com.wm.pojo.User;

import java.util.List;

/**
 * @author wm
 */

public interface UserMapper {

    /**
     * 添加用户信息
     * @param user 用户信息
     */
    void insert(User user);

    /**
     * 查询所有用户信息
     * @return 所有用户信息
     */
    List<User> selectAll();

    /**
     * 查询用户数量
     * @return 用户数量
     */
    int count();

    /**
     * 依据主键查询用户信息
     * @param id 主键
     * @return 用户信息
     */
    User selectById(Integer id);
    /**
     * 依据主键查询用户信息
     * @param username 用户名
     * @return 用户信息
     */
    User selectByUsername(String username);

    /**
     * 依据主键修改用户信息
     * @param user 用户实体
     */
    void updateById(User user);

    /**
     * 遗迹主键删除用户信息
     * @param ids 主键
     */
    void deleteByIds(Integer[] ids);
}
