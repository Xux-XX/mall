package com.xux.product.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xux.product.mapper.ProductMapper;
import com.xux.product.pojo.dto.ProductUpdateDTO;
import com.xux.product.pojo.entity.Product;
import com.xux.product.pojo.enums.ProductOrderBy;
import com.xux.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/12 21:33
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
        implements ProductService {

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

    }
}
