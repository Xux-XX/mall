package com.xux.commonWeb.config;

import com.xux.commonWeb.interceptor.UserContextInterceptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/10 13:47
 */
@ComponentScan({"com.xux.commonWeb.service.impl", "com.xux.commonWeb.aspect", "com.xux.commonWeb.advice"})
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addWebRequestInterceptor(new UserContextInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/doc.html",
                        "/v3/api-docs/**",
                        "/swagger-resources/**",
                        "/webjars/**"
                );
    }

    @Bean
    public SpelExpressionParser parser(){
        return new SpelExpressionParser();
    }
    @Bean
    public StandardEvaluationContext context(ApplicationContext applicationContext){
        return new StandardEvaluationContext(applicationContext);
    }
}
