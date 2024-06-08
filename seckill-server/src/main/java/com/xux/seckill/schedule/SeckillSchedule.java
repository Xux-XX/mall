package com.xux.seckill.schedule;

import com.xux.seckill.pojo.entity.SeckillArrangement;
import com.xux.seckill.pojo.entity.SeckillProduct;
import com.xux.seckill.service.ArrangementService;
import com.xux.seckill.service.ProductService;
import com.xux.seckill.util.SeckillUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SeckillSchedule {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ArrangementService arrangementService;
    private final ProductService productService;
    /**
     * 每天晚上一点执行，将接下来三天的秒杀商品载入缓存
     */
    @Scheduled(cron = "0 0 1 * * ？")
    public void loadProduct2Redis(){
        LocalDateTime fromTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now().plusDays(3);
        List<SeckillArrangement> arrangements = arrangementService.getArrangementList(fromTime, endTime);
        for (SeckillArrangement arrangement : arrangements) {
            List<SeckillProduct> products = productService.getProductsBySeckillId(arrangement.getSeckillId());
            for (SeckillProduct product : products) {
                String key = SeckillUtil.getProductKey(arrangement.getSeckillId(), product.getProductId());
                redisTemplate.opsForHash().putIfAbsent(key, "total", product.getTotal());
                redisTemplate.opsForHash().putIfAbsent(key, "limit", product.getLimit());
                redisTemplate.opsForHash().putIfAbsent(key, "start", arrangement.getStartTime());
                redisTemplate.expireAt(key, arrangement.getEndTime().toInstant(ZoneOffset.UTC));
            }
        }
    }

}
