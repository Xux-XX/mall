package com.xux.seckill.schedule;

import com.xux.seckill.pojo.constant.RedisConstant;
import com.xux.seckill.pojo.entity.SeckillArrangement;
import com.xux.seckill.pojo.entity.SeckillProduct;
import com.xux.seckill.service.ArrangementService;
import com.xux.seckill.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/19 22:23
 */
@Component
@RequiredArgsConstructor
public class SeckillSchedule {
    private final ArrangementService arrangementService;
    private final ProductService productService;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ZoneId zoneId = ZoneId.of("GMT+8");

    /**
     * 每天晚上2点,把第二天3点内的秒杀信息载入redis中
     */
//    @Async
    @Scheduled(cron = "* * 2 * * ?")
    public void load2Redis(){
        LocalDateTime start = LocalDateTime.now();
        List<SeckillArrangement> arrangements = arrangementService.getByStarTime(start);
        for (SeckillArrangement arrangement : arrangements) {
            Instant expireAt = arrangement.getEndTime().plusMinutes(5).atZone(zoneId).toInstant();
            String seckillKey = RedisConstant.getSeckillKey(arrangement.getSeckillId());
            // 若存在则跳过
            // 不存在则创建一个map存入redis
            if (Boolean.TRUE.equals(redisTemplate.hasKey(seckillKey))) continue;
            Map<String, Object> map = Map.of(
                    RedisConstant.START_TIME, arrangement.getStartTime().atZone(zoneId).toInstant().toEpochMilli(),
                    RedisConstant.END_TIME, arrangement.getEndTime().atZone(zoneId).toInstant().toEpochMilli()
            );
            redisTemplate.opsForHash().putAll(seckillKey, map);
            redisTemplate.expireAt(seckillKey, expireAt);
            // 获取库存和购买上限并将其载入redis
            List<SeckillProduct> products = productService.getBySeckill(arrangement.getSeckillId());
            for (SeckillProduct product : products) {
                String productKey = RedisConstant.getProductKey(product.getSeckillId(), product.getProductId());
                if (Boolean.TRUE.equals(redisTemplate.hasKey(productKey))) continue;
                String limitKey = RedisConstant.getLimitKey(productKey);
                String stockKey = RedisConstant.getStockKey(productKey);
                map = Map.of(
                        limitKey, product.getLimit(),
                        stockKey, product.getStock()
                );
                redisTemplate.opsForValue().multiSet(map);
                redisTemplate.expireAt(limitKey, expireAt);
                redisTemplate.expireAt(stockKey, expireAt);
            }
        }
    }
}
