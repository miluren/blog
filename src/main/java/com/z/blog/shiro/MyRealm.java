package com.z.blog.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.z.blog.mapper.PermissionMapper;
import com.z.blog.mapper.RolesMapper;
import com.z.blog.mapper.UserMapper;
import com.z.blog.model.entity.User;
import com.z.blog.service.IPermissionService;
import com.z.blog.service.IRolesService;
import com.z.blog.service.IUserService;
import com.z.blog.service.impl.UserServiceImpl;
import com.z.blog.shiro.jwt.JwtToken;
import com.z.blog.utils.JwtUtil;
import lombok.NoArgsConstructor;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 继承AuthorizingRealm（实现了Realm接口的类）
 * @author Mr zhang
 */
@NoArgsConstructor
@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IRolesService iRolesService;
    @Autowired
    private IPermissionService iPermissionService;

    /**
     * 必须重写此方法，不然会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }


    /**
     * 返回Realm的自定义名称
     * @return 名称
     */
    @Override
    public String getName() {
        return "myRealm";
    }

    /**
     * 获取授权信息
     * @param principals  当事人（用户名），从 doGetAuthenticationInfo返回的第一个变量获取
     * @return 授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("————权限认证————");
        String username = JwtUtil.getUsername(principals.toString());
        String userId = JwtUtil.getUserId(principals.toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();


        QueryWrapper<User> userWrapper = new QueryWrapper<>();
        userWrapper.eq("username", username);
        // 获取当前用户的角色列表
        Set<String> roles = iRolesService.getRoles(userId);
        // 根据用户名查询当前用户的权限列表
        Set<String> permissionByRoles = iPermissionService.getPermissionByRoles(roles);
        // 获取所有的权限列表
        Set<String> allPermission = iPermissionService.getAllPermission();

        //设置该用户拥有的角色和权限
        info.setRoles(roles);
        info.setStringPermissions(permissionByRoles);

        return info;
    }

    /**
     * 获取认证信息
     * 给 doGetAuthorizationInfo 传递参数
     * @param authenticationToken 就是传递 subject.login(token)
     * @return 认证信息
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("————身份认证方法————");
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JwtUtil.getUsername(token);

        if (username == null || !JwtUtil.verify(token, username)) {
            throw new AuthenticationException("token认证失败！");
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        User user = iUserService.getOne(userQueryWrapper);
        String password = user.getPassword();
        if (password == null) {
            throw new AuthenticationException("该用户不存在！");
        }
        int ban = user.getBan();
        if (ban == 1) {
            throw new AuthenticationException("该用户已被封号！");
        }
        // 验证信息
        AuthenticationInfo info = new SimpleAuthenticationInfo(
                // 用户名
                token,
                // 从数据库查询出来的安全密码
                token,
                // 返回盐
                ByteSource.Util.bytes(user.getSalt()),
                // Realm 名字
                getName());

        return info;
    }
}
