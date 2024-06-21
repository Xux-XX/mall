package com.xux.seckill.service;

import com.xux.seckill.pojo.enums.SeckillEnum;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/19 21:10
 */
public interface SeckillService {
    public SeckillEnum doSeckill(Integer seckillId, Integer productId, Integer number) throws InterruptedException;
}
