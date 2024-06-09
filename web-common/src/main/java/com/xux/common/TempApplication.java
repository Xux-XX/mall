package com.xux.common;

import com.xux.common.annotation.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/9 19:11
 */
@SpringBootApplication
public class TempApplication {
    @Log
    public Integer temp(Integer id){
        return id;
    }
}
