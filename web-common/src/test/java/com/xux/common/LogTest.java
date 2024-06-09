package com.xux.common;

import com.xux.common.annotation.Log;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/9 19:05
 */
@SpringBootTest
@Component
public class LogTest {
    @Autowired
    TempApplication application;
    @Test
    void logTest(){
        application.temp(1);
    }

}
