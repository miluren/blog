package com.z.blog;

import com.z.blog.model.entity.User;
import com.z.blog.mapper.UserMapper;
import com.z.blog.service.IPermissionService;
import com.z.blog.service.IUserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.*;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IPermissionService iPermissionService;

    @Autowired
    private IUserService iUserService;

    @Test
    void contextLoads() {
        // 查询全部user
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    void pawd() {
        System.out.println("--注册--");
        User user = new User();
        user.setUsername("zhang");
        user.setPassword("123456");

        // (0-9000)之间的 盐
        int salt = new Random().nextInt(9000);
        System.out.println("salt " + salt);

        // 加密 密码
        Md5Hash hash = new Md5Hash(user.getPassword());
        System.out.println("--> "+ hash);

        // 加盐加密 密码
        Md5Hash hash1 = new Md5Hash(user.getPassword(), salt+"");
        System.out.println("salt--> "+ hash1);

        // 加盐加密 多次hash
        Md5Hash hash2 = new Md5Hash(user.getPassword(), salt+"", 3);
        System.out.println("salt3--> "+ hash2.toHex());
    }

    @Test
    void getpermission() {
        Set<String> roles = new HashSet<>();
        roles.add("1");
        roles.add("2");
        Set<String> permissionByRoles = iPermissionService.getPermissionByRoles(roles);
        System.out.println(permissionByRoles);
    }


    public static void main(String[] args) {
        File file = new File("D:\\img\\blog\\0.jpg");
        file.delete();
    }

}
