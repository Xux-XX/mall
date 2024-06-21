package com.xux.seckill.service;

import com.xux.seckill.pojo.entity.SeckillProduct;

import java.util.List;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/19 22:21
 */

public interface ProductService {
    List<SeckillProduct> getBySeckill(Integer seckillId);

    SeckillProduct getProduct(Integer seckillId, Integer productId);
}
