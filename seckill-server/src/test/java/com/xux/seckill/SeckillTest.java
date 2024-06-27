package com.xux.seckill;

import com.xux.commonWeb.context.UserContext;
import com.xux.commonWeb.pojo.entity.UserInfo;
import com.xux.seckill.schedule.SeckillSchedule;
import com.xux.seckill.service.ArrangementService;
import com.xux.seckill.service.SeckillService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/20 23:55
 */
@SpringBootTest
public class SeckillTest {
    @Autowired
    SeckillSchedule seckillSchedule;
    @Autowired
    SeckillService seckillService;
    @Test
    public void test(){
        seckillSchedule.load2Redis();
    }

    @Test
    public void doSeckill(){
        Integer seckillId = 10;
        Integer productId = 1;
        Integer number = 1;
        UserContext.set(new UserInfo(1, 0));
//        System.out.println(seckillService.doSeckill(seckillId, productId, number));
    }
}
