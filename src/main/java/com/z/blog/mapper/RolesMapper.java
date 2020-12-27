package com.z.blog.mapper;

import com.z.blog.model.entity.Roles;
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
public interface RolesMapper extends BaseMapper<Roles> {

    /**
     * 获取角色
     * @param userId 用户编号
     * @return 角色列表
     */
    Set<String> getRoles(String userId);
}
