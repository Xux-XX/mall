package com.xux.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xux.seckill.mapper.ProductMapper;
import com.xux.seckill.pojo.entity.SeckillProduct;
import com.xux.seckill.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/19 22:21
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, SeckillProduct> implements ProductService {
    @Override
    public List<SeckillProduct> getBySeckill(Integer seckillId) {
        return this.lambdaQuery()
                .eq(SeckillProduct::getSeckillId, seckillId)
                .list();
    }

    @Override
    public SeckillProduct getProduct(Integer seckillId, Integer productId) {
        return this.lambdaQuery()
                .eq(SeckillProduct::getSeckillId, seckillId)
                .eq(SeckillProduct::getProductId, productId)
                .one();
    }
}
