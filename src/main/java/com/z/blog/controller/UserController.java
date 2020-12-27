package com.z.blog.controller;


import com.z.blog.model.entity.User;
import com.z.blog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Mr zhang
 * @since 2020-12-16
 */
@RestController
@RequestMapping("/blog/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/test")
    public String test() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);

        return "ok";
    }
}
