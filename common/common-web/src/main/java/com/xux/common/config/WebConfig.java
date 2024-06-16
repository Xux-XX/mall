package com.xux.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/10 13:47
 */

@MapperScan("com.xux.common.mapper")
@ComponentScan({"com.xux.common.service.impl", "com.xux.common.aspect"})
public class WebConfig {

}
