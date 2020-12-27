package com.z.blog.service;

import com.z.blog.model.entity.Roles;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mr zhang
 * @since 2020-12-16
 */
public interface IRolesService extends IService<Roles> {

    /**
     * 获取角色
     * @param userId 用户编号
     * @return 角色列表
     */
    Set<String> getRoles(String userId);

}
