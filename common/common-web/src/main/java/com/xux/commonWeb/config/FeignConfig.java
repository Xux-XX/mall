package com.xux.commonWeb.config;

import com.xux.commonWeb.interceptor.FeignUserHeaderInterceptor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/23 20:54
 */
@EnableFeignClients("com.xux.commonWeb.api")
public class FeignConfig {
    @Bean
    public ResultDecoder resultDecoder(){
        return new ResultDecoder();
    }

    @Bean
    public FeignUserHeaderInterceptor userHeaderInterceptor(){
        return new FeignUserHeaderInterceptor();
    }
}
