package com.xux.core.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/14 21:27
 */
public class JWTUtil {
    /** 避免每次解码都创建一个verifier */
    private final JWTVerifier verifier;

    /** 设置时区*/
    private final ZoneId zoneId;

    /** 签名 */
    private final String sign;

    /** 过期时间 单位小时 */
    private final int expire;

    /** jwt中携带的字段 */
    public static final String USER_ID = "userId";

    private Instant getExpire(){
        return LocalDateTime.now().plusHours(expire).atZone(zoneId).toInstant();
    }
    public JWTUtil(@Value("${jwt.sign}")String sign,
                   @Value("${jwt.zone-id}") String zoneId,
                   @Value("${jwt.expire}") int expire) {
        this.expire = expire;
        this.sign = sign;
        this.verifier = JWT.require(Algorithm.HMAC256(sign)).build();
        this.zoneId = ZoneId.of(zoneId);
    }

    public Integer parse(String jwt){
        return verifier.verify(jwt).getClaim(USER_ID).asInt();
    }

    public String createJWT(Integer userId){
        Algorithm algorithm = Algorithm.HMAC256(sign);
        return JWT.create()
                .withClaim(USER_ID, userId)
                .withExpiresAt(getExpire())
                .sign(algorithm);
    }

}
