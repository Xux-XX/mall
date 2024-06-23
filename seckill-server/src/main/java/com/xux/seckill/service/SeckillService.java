package com.xux.seckill.service;

import com.xux.seckill.pojo.enums.SeckillEnum;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/19 21:10
 */
public interface SeckillService {
    /**
     * 秒杀商品
     * @param seckillId 秒杀场次id
     * @param productId 秒杀商品id
     * @param number 秒杀数目
     * @param addressId 地址id(用于创建订单)
     * @return SeckillEnum
     */
    SeckillEnum doSeckill(Integer seckillId, Integer productId, Integer number, Integer addressId);
}
