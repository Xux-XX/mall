package com.xux.core.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

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

    public static final String ROLE_STATUS = "roleStatus";

    private Instant getExpire(){
        return LocalDateTime.now().plusHours(expire).atZone(zoneId).toInstant();
    }
    public JWTUtil(String sign, int expire, String zoneId) {
        this.expire = expire;
        this.sign = sign;
        this.verifier = JWT.require(Algorithm.HMAC256(sign)).build();
        this.zoneId = ZoneId.of(zoneId);
    }

    public DecodedJWT parse(String jwt){
        return verifier.verify(jwt);
    }

    public String createJWT(Integer userId, Integer roleStatus){
        Algorithm algorithm = Algorithm.HMAC256(sign);
        return JWT.create()
                .withClaim(USER_ID, userId)
                .withClaim(ROLE_STATUS, roleStatus)
                .withExpiresAt(getExpire())
                .sign(algorithm);
    }

}
