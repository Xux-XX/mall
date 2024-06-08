package com.xux.seckill.service;

import com.xux.seckill.pojo.enums.SeckillEnum;

public interface SeckillService {
    /**
     * 秒杀商品
     * @param seckillId 秒杀场次编号
     * @param productId 秒杀商品编号
     * @return 处理结果状态(秒杀成功,秒杀失败,还未开始,已经结束)
     */
    public SeckillEnum doSeckill(Integer seckillId, Integer productId);
}
