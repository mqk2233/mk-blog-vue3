package com.mk.config;

import com.mk.blog.utils.RedisUtils;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author MK
 * @date 2022/3/2
 */
@Component
public class TokenUtil {

    @Resource
    private RedisUtils redisUtils;
    /**
     * token有效期
     */
    private final static Long EXPIRATION_TIME = 24 * 60 * 60 * 1000L;
    /**
     * 签名key
     */
    private final static String SIGN_KEY = "mk2233";

    /**
     * 使用jwt根据用户名生成token
     *
     * @param username 用户名
     * @return {@link String }
     * @author MK
     * @date 2022/3/2 21:55
     */
    public String createToken(String username) {
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SIGN_KEY)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        redisUtils.set(username, token, EXPIRATION_TIME, TimeUnit.MILLISECONDS);
        return token;
    }

    /**
     * 根据token获取用户信息
     *
     * @param token token
     * @return {@link String }
     * @author MK
     * @date 2022/3/2 22:01
     */
    public String getUserInfo(String token) {
        return Jwts.parser()
                .setSigningKey(SIGN_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * 通过用户名移除token
     *
     * @param username 用户名
     * @author MK
     * @date 2022/3/3 22:13
     */
    public void removeToken(String username) {
        redisUtils.remove(username);
    }
}
