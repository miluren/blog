package com.z.blog.common;

import java.util.UUID;

/**
 * 常量
 * @author Mr zhang
 * @date 2020/12/17
 */
public class Constant {

    public static final String JWT_ID = UUID.randomUUID().toString();

    /**
     * 加密密文
     */
    public static final String JWT_SECRET = "secret";

    /**
     * JWT过期时间
     * millisecond
     */
    public static final int JWT_TTL = 60*60*1000;
}
