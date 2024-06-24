package com.xux.order.service;

import com.xux.order.pojo.entity.OrderProduct;

import java.util.List;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/23 23:11
 */

public interface OrderProductService {
    void addProductBatch(List<OrderProduct> list);
}
