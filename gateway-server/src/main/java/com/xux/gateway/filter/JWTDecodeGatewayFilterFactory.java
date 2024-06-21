package com.xux.gateway.filter;

import com.alibaba.fastjson2.JSON;
import com.auth0.jwt.exceptions.TokenExpiredException;
//import com.xux.core.entity.Result;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xux.core.entity.Result;
import com.xux.core.util.JWTUtil;
import com.xux.redis.util.LoginRedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.core.io.buffer.DefaultDataBuffer;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.alibaba.fastjson2.JSONWriter.Feature.WriteMapNullValue;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/15 14:54
 */
@Component
@RequiredArgsConstructor
public class JWTDecodeGatewayFilterFactory implements GatewayFilterFactory<Object> {
    private final JWTUtil jwtUtil;
    private final LoginRedisUtil redisUtil;
    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            // 未登录将userId设置为0
            String userId = "0";
            String userRole = "0";
            List<String> authorization = exchange.getRequest().getHeaders().get("Authorization");
            if (authorization != null){
                // 携带了jwt则进行认证
                String jwt = authorization.get(0);
                try {
                    // 解析失败或者不存在redis表示token已经过期
                    DecodedJWT parse = jwtUtil.parse(jwt);
                    userId = parse.getClaim(JWTUtil.USER_ID).asString();
                    userRole = parse.getClaim(JWTUtil.ROLE_STATUS).asString();
                    if (redisUtil.hasKey(userId.toString())) throw new TokenExpiredException("", null);
                }catch (TokenExpiredException e){
                    // jwt过期
                    return exchange
                            .getResponse()
                            .writeWith(toDataBuffer(Result.unAuthorized("身份校验失败,请重新登录")));
                }
            }
            // 将解析出来的数据写入请求头传递到下游
            ServerHttpRequest mutateRequest = exchange.getRequest()
                    .mutate()
                    .header(JWTUtil.USER_ID, userId)
                    .header(JWTUtil.ROLE_STATUS, userRole)
                    .build();
            ServerWebExchange mutateExchange = exchange.mutate().request(mutateRequest).build();
            return chain.filter(mutateExchange);
        };
    }

    private Flux<DefaultDataBuffer> toDataBuffer(Object o){
        return Flux.just(JSON.toJSONString(o, WriteMapNullValue))
                .map(res -> res.getBytes(StandardCharsets.UTF_8))
                .map(DefaultDataBufferFactory.sharedInstance::wrap);
    }
}
