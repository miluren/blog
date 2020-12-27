package com.z.blog.service;

import com.z.blog.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mr zhang
 * @since 2020-12-16
 */
public interface IUserService extends IService<User> {

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 是否登录成功
     */
    User login(String username, String password);

}
