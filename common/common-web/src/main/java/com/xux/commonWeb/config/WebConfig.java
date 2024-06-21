package com.xux.commonWeb.config;

import com.xux.commonWeb.interceptor.UserContextInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/10 13:47
 */
@ComponentScan({"com.xux.commonWeb.service.impl", "com.xux.commonWeb.aspect"})
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addWebRequestInterceptor(new UserContextInterceptor());
    }
}
