package com.z.blog.shiro.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * token
 * @author Mr zhang
 * @date 2020/12/17
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
