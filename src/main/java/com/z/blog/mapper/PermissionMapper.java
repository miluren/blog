package com.z.blog.mapper;

import com.z.blog.model.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Mr zhang
 * @since 2020-12-16
 */
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据角色，获取权限列表
     * @param roles 角色
     * @return 权限列表
     */
    Set<String> getPermissionByRoles(Set<String> roles);

}
