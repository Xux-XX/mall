package com.xux.gateway;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xux.core.util.JWTUtil;
import org.junit.jupiter.api.Test;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/22 15:29
 */
public class TestWithoutSpring {
    @Test
    void test(){
        JWTUtil util = new JWTUtil("xux_XX", 24, "GMT+8");
        String jwt = util.createJWT(1, 0);
        jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3MTkxMzA4NDQsInVzZXJJZCI6IjExIiwicm9sZVN0YXR1cyI6IjEifQ.9OGBn8tqR6kVucCkMV1qBemx7X9wi3iyRwI2a1bOO_c";
        System.out.println(util.parse(jwt).getClaim("userId"));
    }
}
