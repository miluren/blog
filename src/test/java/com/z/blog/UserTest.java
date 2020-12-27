package com.z.blog;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.z.blog.mapper.UserMapper;
import com.z.blog.model.entity.User;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 用户测试类
 * @author Mr zhang
 * @date 2020/12/18
 */
@SpringBootTest
public class UserTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void login() {
        String username = "zhang";
        String password = "123456";
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);

        User user = userMapper.selectOne(wrapper);
        Md5Hash hash = new Md5Hash(password, user.getSalt(), 3);
        System.out.println(hash.toString());
        System.out.println(user);
        System.out.println(hash.toString().equals(user.getPassword()));

    }

}
