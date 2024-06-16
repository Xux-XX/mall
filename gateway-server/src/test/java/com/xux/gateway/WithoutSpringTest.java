package com.xux.gateway;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/15 15:31
 */

public class WithoutSpringTest {
    @Test
    public void test() throws InterruptedException {
        String jwt = JWT.create()
                .withClaim("userId", 1)
                .withExpiresAt(LocalDateTime.now().plusSeconds(1).atZone(ZoneId.of("GMT+8")).toInstant())
                .sign(Algorithm.HMAC256("xux_XX"));
        Thread.sleep(2000);
        System.out.println(JWT.require(Algorithm.HMAC256("xux_XX")).build().verify(jwt).getExpiresAt());
    }
}
