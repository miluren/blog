package com.z.blog.shiro;

import com.z.blog.shiro.filter.JwtFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;

/**
 * @author Mr zhang
 */
@Configuration
public class ShiroConfig {

    /**
     * 设置缓存
     * @return EhCacheManager
     */
    @Bean
    public EhCacheManager getEhCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return ehCacheManager;
    }

    /**
     * 让shiro的注解能够得到加载和执行
     * @return advisorAutoProxyCreator
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 注解的解析器
     * @param defaultWebSecurityManager 安全管理器
     * @return advisor
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(defaultWebSecurityManager);

        return advisor;
    }

    /**
     * 加密规则
     * @return HashedCredentialsMatcher
     */
    @Bean
    public HashedCredentialsMatcher getHashedCredentialsMatcher() {
        // 用来指定加密规则
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        // 加密方法
        matcher.setHashAlgorithmName("md5");
        // hash次数
        matcher.setHashIterations(3);

        return matcher;
    }

    /**
     * 把realm配置到Spring当中
     * @return IniRealm
     */
    @Bean
    public MyRealm getIniRealm(HashedCredentialsMatcher matcher) {
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(matcher);

        return myRealm;
    }

    /**
     * 配置 安全管理器到Spring中
     * @param myRealm realm
     * @return DefaultWebSecurityManager
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(MyRealm myRealm, EhCacheManager ehCacheManager) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        // SecurityManager要完成校验，需要Realm
        defaultWebSecurityManager.setRealm(myRealm);
        // 设置缓存管理器
        defaultWebSecurityManager.setCacheManager(ehCacheManager);

        return defaultWebSecurityManager;
    }


    /**
     * 配置shiro过滤器
     * @param defaultWebSecurityManager 安全管理器
     * @return  ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean  filterFactoryBean = new ShiroFilterFactoryBean();

        // 过滤器就是shiro进行权限校验的核心，进行认证授权是需要SecurityManager的
        filterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        HashMap<String, Filter> filter = new HashMap<>(16);
        filter.put("jwt", new JwtFilter());

        filterFactoryBean.setFilters(filter);

        // 设置拦截规则（拦截发布文章等接口）
        Map<String, String> filterMap = new HashMap<>(16);
        // anon 表示匿名用户（不拦截）
        // authc 认证用户 才能访问
        // user 使用RemeberMe的用户可访问
        // perms 对应权限可访问
        // roles 对应的角色可访问
        filterMap.put("/**", "jwt");
        filterMap.put("/", "anon");

        // 退出
        filterMap.put("/login/exit", "logout");
        // 所有路径都要拦截
//        filterMap.put("/**", "authc");

        filterFactoryBean.setFilterChainDefinitionMap(filterMap);
        // 设置为登录的时候跳转到 /login路径
        filterFactoryBean.setLoginUrl("/login");

        // 设置未授权访问的页面路径
        filterFactoryBean.setUnauthorizedUrl("/lesspermission.html");

        return filterFactoryBean;
    }

    /**
     * 将shiroBean 交给Spring容器回收
     * @return LifecycleBeanPostProcessor
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

}
