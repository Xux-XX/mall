package com.xux.common.config;

import com.xux.common.aspect.LogAspect;
import com.xux.common.service.impl.LogServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/10 13:47
 */
@Configuration
@MapperScan("com.xux.common.mapper")
@ComponentScan({"com.xux.common.service.impl", "com.xux.common.aspect"})
public class ServiceConfig {
}
