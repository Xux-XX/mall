package com.xux.seckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/19 21:08
 */
@SpringBootApplication
@EnableAsync
public class SeckillApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeckillApplication.class, args);
    }
}
