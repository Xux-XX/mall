package com.xux.product.pojo.enums;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xux.product.pojo.entity.Product;

import java.sql.Wrapper;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/17 20:50
 */
public enum ProductOrderBy {
    SALES_DEC,
    SALES_INC,
    STOCK_DEC,
    STOCK_INC,
    PRICE_DEC,
    PRICE_INC,
    DEFAULT;

    /**
     * 根据排序规则修饰wrapper
     */
    public LambdaQueryChainWrapper<Product> decorateWrapper(LambdaQueryChainWrapper<Product> wrapper){
        switch (this){
            case SALES_DEC -> wrapper.orderByDesc(Product::getSales);
            case SALES_INC -> wrapper.orderByAsc(Product::getSales);
            case STOCK_DEC -> wrapper.orderByDesc(Product::getStock);
            case STOCK_INC -> wrapper.orderByAsc(Product::getStock);
            case PRICE_DEC -> wrapper.orderByDesc(Product::getPrice);
            case PRICE_INC -> wrapper.orderByAsc(Product::getPrice);
        }
        return wrapper;
    }
}
