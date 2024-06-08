package com.xux.seckill.feign;

import com.xux.common.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("order-server")
public interface OrderFeignClient {

    /**
     * 创建秒杀订单
     * @param seckillId 秒杀场次编号
     * @param productId 秒杀商品编号
     */
    @PostMapping("seckill_order/{seckillId}/{productId}")
    public Result createSeckillOrder(@PathVariable("seckillId") Integer seckillId,
                                     @PathVariable("productId") Integer productId,
                                     @RequestHeader("user-jwt") String jwt);
}
