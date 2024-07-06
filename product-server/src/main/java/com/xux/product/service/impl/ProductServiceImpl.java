package com.xux.product.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xux.commonWeb.pojo.dto.BuyProductDto;
import com.xux.product.mapper.ProductMapper;
import com.xux.product.pojo.dto.ProductUpdateDTO;
import com.xux.product.pojo.entity.Product;
import com.xux.product.pojo.enums.ProductOrderBy;
import com.xux.product.service.ProductService;
import com.xux.product.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/12 21:33
 */
@Service("productService")
@RequiredArgsConstructor
@CacheConfig(cacheNames = "cache:product")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
        implements ProductService {
    @Autowired
    @Lazy
    private ProductServiceImpl thisService;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ProductMapper productMapper;
    private final StoreService storeService;
    private final String SALES_RANK_KEY = "product:sales";
    @Override
    public boolean exists(Integer productId, Integer storeId) {
        return this.lambdaQuery()
                .eq(Product::getProductId, productId)
                .eq(Product::getStoreId, storeId)
                .exists();
    }

    @Override
    @Cacheable(key = "#productId")
    public boolean isOwner(Integer productId) {
        Integer storeId = this.lambdaQuery()
                .eq(Product::getProductId, productId)
                .select(Product::getStoreId)
                .one()
                .getStoreId();
        return storeService.isOwner(storeId);
    }

    @Override
    public void addProduct(Product product) {
        product.setSales(0);
        this.save(product);
    }

    @Override
    public void remove(Integer productId) {
        this.removeById(productId);
    }

    @Override
    public List<Product> getByNameLike(String productName, Integer pageNumber, Integer pageSize, ProductOrderBy orderBy) {
        LambdaQueryChainWrapper<Product> query = this.lambdaQuery()
                .like(Product::getName, productName);
        orderBy.decorateWrapper(query);
        return query.page(new Page<>(pageNumber, pageSize)).getRecords();
    }

    @Override
    public Product getProductById(Integer productId) {
        return this.getById(productId);
    }

    @Override
    public List<Product> getProductByIds(List<Integer> ids) {
        return this.lambdaQuery()
                .in(Product::getProductId, ids)
                .list();
    }

    @Override
    public List<Product> getByStore(Integer storeId, Integer pageNumber, Integer pageSize, ProductOrderBy orderBy) {
        LambdaQueryChainWrapper<Product> query = this.lambdaQuery().eq(Product::getStoreId, storeId);
        orderBy.decorateWrapper(query);
        return query.page(new Page<>(pageNumber, pageSize)).getRecords();
    }

    @Override
    public List<Product> getOutOfStock(Integer storeId, Integer pageNumber, Integer pageSize) {
        return this.lambdaQuery()
                .eq(Product::getStoreId, storeId)
                .eq(Product::getStock, 0)
                .page(new Page<>(pageNumber, pageSize))
                .getRecords();
    }

    @Override
    public void increaseStock(Integer increment, Integer productId) {
        this.lambdaUpdate()
                .eq(Product::getProductId, productId)
                .setSql("set stock = stock + ", increment)
                .update();
    }

    @Override
    public void update(ProductUpdateDTO productUpdateDTO) {
        // TODO 待实现
    }

    /**
     * 更新数据库和redis中的zset排行榜
     * @param buyProductDtoList 包含商品id和购买的数量number
     */
    @Override
    @Transactional
    public void increaseSaleBatch(List<BuyProductDto> buyProductDtoList) {
        productMapper.increaseSalesBatch(buyProductDtoList);
        List<Integer> productIds = buyProductDtoList
                .stream()
                .map(BuyProductDto::getProductId)
                .toList();
        Set<ZSetOperations.TypedTuple<Object>> zSetTuple = this.lambdaQuery()
                .in(Product::getProductId, productIds)
                .list()
                .stream()
                .map(this::createTypedTuple)
                .collect(Collectors.toSet());
        redisTemplate.opsForZSet().add(SALES_RANK_KEY, zSetTuple);
    }

    @Override
    public List<Product> getBySaleRank(Integer rankL, Integer rankR) {
        List<Integer> productIds = redisTemplate
                .opsForZSet()
                .range(SALES_RANK_KEY, rankL, rankR)
                .stream()
                .map(Integer.class::cast)
                .toList();
        return thisService.getProductByIds(productIds);
    }

    /**
     * 创建一个TypedTuple对象，用于批量更新zset中的销售量
     * @param product 商品信息
     * @return ZSetOperations.TypedTuple<Object>
     */
    private ZSetOperations.TypedTuple<Object> createTypedTuple(Product product) {
        return new ZSetOperations.TypedTuple<>() {
            @Override
            public int compareTo(ZSetOperations.TypedTuple<Object> o) {
                return Double.compare(o.getScore(), this.getScore());
            }
            @Override
            public Object getValue() {
                return product.getProductId();
            }
            @Override
            public Double getScore() {
                return Double.valueOf(product.getSales());
            }
        };
    }
}
