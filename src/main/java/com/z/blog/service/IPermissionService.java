package com.z.blog.service;

import com.z.blog.model.entity.Permission;
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
public interface IPermissionService extends IService<Permission> {

    /**
     * 根据角色，获取权限列表
     * @param roles 角色
     * @return 权限列表
     */
    Set<String> getPermissionByRoles(Set<String> roles);

    /**
     * 获取权限列表
     * @return 权限列表
     */
    Set<String> getAllPermission();

}
