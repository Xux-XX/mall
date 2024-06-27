package com.xux.product.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xux.feign.dto.BuyProductDto;
import com.xux.product.mapper.ProductMapper;
import com.xux.product.pojo.dto.ProductUpdateDTO;
import com.xux.product.pojo.entity.Product;
import com.xux.product.pojo.enums.ProductOrderBy;
import com.xux.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/12 21:33
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
        implements ProductService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ProductMapper productMapper;
    private final String SALES_RANK_KEY = "product:sales";
    @Override
    public boolean exists(Integer productId, Integer storeId) {
        return this.lambdaQuery()
                .eq(Product::getProductId, productId)
                .eq(Product::getStoreId, storeId)
                .exists();
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
    public List<Product> getByStore(Integer storeId, Integer pageNumber, Integer pageSize, ProductOrderBy orderBy) {
        LambdaQueryChainWrapper<Product> query = this.lambdaQuery().eq(Product::getStoreId, storeId);
        orderBy.decorateWrapper(query);
        return query.page(new Page<>(pageNumber, pageSize)).getRecords();
    }

    @Override
    public List<Product> getOutOfStock(Integer pageNumber, Integer pageSize) {
        return this.lambdaQuery()
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
        List<Product> products = this.lambdaQuery()
                .in(Product::getProductId, buyProductDtoList.stream().map(BuyProductDto::getProductId).toList())
                .list();
        redisTemplate.opsForZSet().add(SALES_RANK_KEY, list2ZSetTuple(products));
    }

    @Override
    public List<Product> getBySaleRank(Integer rankL, Integer rankR) {
        // TODO 待完成
        return null;
    }

    /**
     * 将list按照销售量从大到小排序转为ZSet的tuple，用于批量插入
     * @param products 商品集合
     * @return Set<ZSetOperations.TypedTuple<Object>>
     */
    private Set<ZSetOperations.TypedTuple<Object>> list2ZSetTuple(List<Product> products){
        return products.stream()
                .map(product -> new ZSetOperations.TypedTuple<>() {
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
                })
                .collect(Collectors.toSet());
    }
}
