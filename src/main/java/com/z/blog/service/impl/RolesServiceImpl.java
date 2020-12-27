package com.z.blog.service.impl;

import com.z.blog.model.entity.Roles;
import com.z.blog.mapper.RolesMapper;
import com.z.blog.service.IRolesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class RolesServiceImpl extends ServiceImpl<RolesMapper, Roles> implements IRolesService {

    @Autowired
    private RolesMapper rolesMapper;

    @Override
    public Set<String> getRoles(String userId) {
        return rolesMapper.getRoles(userId);
    }
}
