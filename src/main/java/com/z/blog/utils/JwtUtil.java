package com.z.blog.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.z.blog.common.Constant;
import org.apache.shiro.SecurityUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt工具类
 * @author Mr zhang
 * @date 2020/12/17
 */
public class JwtUtil {

    /**
     * 过期时间 24 小时
     */
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;
    /**
     * 密钥
      */
    private static final String SECRET = "SHIRO+JWT";


    /**
     *   秘钥
     */
    public static SecretKey generalKey() {
        String stringKey = Constant.JWT_SECRET;

        // 本地解码
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        // 根据给定的字节数组使用AES加密算法构造一个密钥

        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * 生成 token
     *
     * @param username 用户名
     * @param id 用户编号
     * @return 加密的token
     */
    public static String createToken(String username, String id) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            // 附带username信息
            return JWT.create()
                    .withClaim("username", username)
                    .withClaim("userid", id)
                    //到期时间
                    .withExpiresAt(date)
                    //创建一个新的JWT，并使用给定的算法进行标记
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 校验 token 是否正确
     *
     * @param token    密钥
     * @param username 用户名
     * @return 是否正确
     */
    public static boolean verify(String token, String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            //在token中附带了username信息
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            //验证 token
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息，无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获得token中的信息，无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取token中的信息
     * @return Map
     */
    public static Map<String, Object> getTokenInfo() {
        //获取jwt中的载体
        String token = SecurityUtils.getSubject().getPrincipal().toString();
        String[] split = token.split("\\.");
        byte[] decode = java.util.Base64.getDecoder().decode(split[1]);
        String s;
        s = new String(decode, StandardCharsets.UTF_8);
        JsonObject jsonObject = new JsonParser().parse(s).getAsJsonObject();
        String userId = jsonObject.get("userid").getAsString();
        String username = jsonObject.get("username").getAsString();
        Map<String, Object> info = new HashMap<>(16);
        info.put("userId", userId);
        info.put("username", username);
        return info;
    }

}
