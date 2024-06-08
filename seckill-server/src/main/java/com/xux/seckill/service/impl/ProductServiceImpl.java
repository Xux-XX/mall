package com.xux.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xux.seckill.mapper.ProductMapper;
import com.xux.seckill.pojo.entity.SeckillProduct;
import com.xux.seckill.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, SeckillProduct>
        implements ProductService {
    @Override
    public List<SeckillProduct> getProductsBySeckillId(Integer seckillId) {
        return this.lambdaQuery()
                .eq(SeckillProduct::getSeckillId, seckillId)
                .list();
    }
}
