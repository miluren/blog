package com.z.blog.service.impl;

import com.z.blog.model.entity.Permission;
import com.z.blog.mapper.PermissionMapper;
import com.z.blog.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mr zhang
 * @since 2020-12-16
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Set<String> getPermissionByRoles(Set<String> roles) {
        return permissionMapper.getPermissionByRoles(roles);
    }

    @Override
    public Set<String> getAllPermission() {
        List<Permission> permissions = permissionMapper.selectList(null);
        Set<String> p = new HashSet<>();
        for (Permission permission: permissions) {
            p.add(permission.getPermissionCode());
        }
        return p;
    }
}
