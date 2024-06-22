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
     * @return SeckillEnum
     */
    public SeckillEnum doSeckill(Integer seckillId, Integer productId, Integer number);
}
