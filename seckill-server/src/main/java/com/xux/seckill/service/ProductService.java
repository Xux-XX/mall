package com.xux.seckill.service;

import com.xux.seckill.pojo.entity.SeckillProduct;

import java.util.List;

public interface ProductService {
    List<SeckillProduct> getProductsBySeckillId(Integer seckillId);
}
