package com.xux.seckill;

import com.xux.common.context.UserContext;
import com.xux.common.entity.UserInfo;
import com.xux.seckill.schedule.SeckillSchedule;
import com.xux.seckill.service.ArrangementService;
import com.xux.seckill.service.SeckillService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.LocalDateTime;
import java.util.Map;

@SpringBootTest
public class SeckillTest {
    @Autowired
    SeckillSchedule seckillSchedule;
    @Autowired
    ArrangementService arrangementService;
    @Autowired
    SeckillService seckillService;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    @Test
    public void test(){
        Map<Object, Object> entries = redisTemplate.opsForHash().entries("?");
        System.out.println(entries);
    }

    @Test
    public void addSeckillGoods(){
        seckillSchedule.loadProduct2Redis();
    }

    @Test
    public void seckillTest(){
        UserInfo userInfo = new UserInfo("", 1);
        UserContext.set(userInfo);
        System.out.println(seckillService.doSeckill(6, 383));
    }
}
