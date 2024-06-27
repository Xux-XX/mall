package com.xux.order.service;

import com.xux.order.pojo.entity.OrderProduct;

import java.util.List;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/23 23:11
 */

public interface OrderProductService {
    /**
     * 批量新增订单关联商品
     * @param list 商品列表
     */
    void addProductBatch(List<OrderProduct> list);

    /**
     * 新增订单关联商品
     * @param orderProduct 商品
     */
    void addProduct(OrderProduct orderProduct);

    /**
     * 通过订单号获取订单中的商品
     * @param orderId 订单id
     * @return List<OrderProduct> 订单中的商品
     */
    List<OrderProduct> getByOrderId(Integer orderId);
}
