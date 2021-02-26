package com.wm.controller;

import com.wm.pojo.User;
import com.wm.service.UserService;
import com.wm.until.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;

/**
 * @author wm
 */
@RequestMapping("/api/user/")
@Controller
public class UsersController {

    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping("login")
    public JsonData login(@NotNull User user){
        User login = userService.login(user);

        System.out.println("login = " + login);
        if (login == null){
            return new JsonData(500,"账号或密码错误");
        }
        System.out.println(new JsonData(login));
        return new JsonData(login);
    }


}
