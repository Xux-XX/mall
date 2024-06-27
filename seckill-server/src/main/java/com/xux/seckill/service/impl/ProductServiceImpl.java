package com.xux.seckill.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xux.seckill.mapper.ProductMapper;
import com.xux.seckill.pojo.entity.SeckillProduct;
import com.xux.seckill.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/19 22:21
 */
@Service
@CacheConfig(cacheNames = "seckill:product")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, SeckillProduct>
        implements ProductService {
    /**
     * 自己注入自己保证事务不会失效
     */
    @Lazy
    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Override
    @Cacheable(key = "#seckillId")
    public List<SeckillProduct> getBySeckill(Integer seckillId) {
        return this.lambdaQuery()
                .eq(SeckillProduct::getSeckillId, seckillId)
                .list();
    }

    @Override
    public List<SeckillProduct> getPageBySeckill(Integer seckillId, Integer pageNumber, Integer pageSize) {
        return this.lambdaQuery()
                .eq(SeckillProduct::getSeckillId, seckillId)
                .page(new Page<>(pageNumber, pageSize))
                .getRecords();
    }

    @Override
    @Cacheable(key = "#seckillId + ':' + #productId")
    public SeckillProduct getProduct(Integer seckillId, Integer productId) {
        return this.lambdaQuery()
                .eq(SeckillProduct::getSeckillId, seckillId)
                .eq(SeckillProduct::getProductId, productId)
                .one();
    }

    @Override
    public void addProduct(SeckillProduct seckillProduct) {
        this.save(seckillProduct);
    }

    @Override
    public void addProductBatch(List<SeckillProduct> seckillProductList) {
        productServiceImpl.saveBatch(seckillProductList);
    }

    @Override
    @CacheEvict(key = "#seckillId + ':' + #productId")
    public void removeProduct(Integer seckillId, Integer productId) {
        this.lambdaUpdate()
                .eq(SeckillProduct::getSeckillId, seckillId)
                .eq(SeckillProduct::getProductId, productId)
                .remove();
    }

    @Override
    @CacheEvict(key = "#seckillProduct.seckillId + ':' + #seckillProduct.productId")
    public void updateProduct(SeckillProduct seckillProduct) {
        this.lambdaUpdate()
                .eq(SeckillProduct::getSeckillId, seckillProduct.getSeckillPrice())
                .eq(SeckillProduct::getProductId, seckillProduct.getProductId())
                .update(seckillProduct);
    }
}
