package com.xux.product.service.impl;

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
    public boolean exists(Integer productId) {
        return false;
    }

    @Override
    public boolean exists(Integer productId, Integer storeId) {
        return false;
    }

    @Override
    public void addProduct(Product product) {

    }

    @Override
    public void removeById(Integer productId) {

    }

    @Override
    public List<Product> getByNameLike(String productName, Integer pageNumber, Integer pageSize, ProductOrderBy orderBy) {
        return null;
    }

    @Override
    public Product getById(Integer productId) {
        return null;
    }

    @Override
    public List<Product> getByCategory(Integer categoryId, Integer pageNumber, Integer pageSize, ProductOrderBy orderBy) {
        return null;
    }

    @Override
    public List<Product> getByStore(Integer storeId, Integer pageNumber, Integer pageSize, ProductOrderBy orderBy) {
        return null;
    }

    @Override
    public List<Product> getOutOfStock(Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public void increaseStock(Integer increment, Integer productId) {

    }

    @Override
    public void update(ProductUpdateDTO productUpdateDTO) {

    }
}
