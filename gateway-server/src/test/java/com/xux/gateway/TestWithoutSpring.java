package com.xux.gateway;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xux.core.util.JWTUtil;
import org.junit.jupiter.api.Test;

import java.time.Instant;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/22 15:29
 */
public class TestWithoutSpring {
    @Test
    void test(){
        System.out.println(Instant.now().toEpochMilli());
    }
}
