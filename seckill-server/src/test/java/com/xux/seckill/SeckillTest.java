package com.xux.seckill;

import com.xux.seckill.pojo.constant.RedisConstant;
import com.xux.seckill.pojo.entity.SeckillArrangement;
import com.xux.seckill.service.ArrangementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.LocalDateTime;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/20 23:55
 */
@SpringBootTest
public class SeckillTest {
    @Autowired
    private ArrangementService arrangementService;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    @Test
    public void test(){

    }
}
