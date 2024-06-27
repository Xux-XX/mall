package com.xux.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xux.order.mapper.OrderProductMapper;
import com.xux.order.pojo.entity.OrderProduct;
import com.xux.order.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/23 23:13
 */
@Service
public class OrderProductServiceImpl extends ServiceImpl<OrderProductMapper, OrderProduct>
        implements OrderProductService {
    @Lazy
    @Autowired
    private OrderProductServiceImpl OrderProductServiceImpl;

    @Override
    public void addProductBatch(List<OrderProduct> list) {
        OrderProductServiceImpl.saveBatch(list);
    }

    @Override
    public void addProduct(OrderProduct orderProduct) {
        this.save(orderProduct);
    }

    @Override
    public List<OrderProduct> getByOrderId(Integer orderId) {
        return this.lambdaQuery()
                .eq(OrderProduct::getOrderId, orderId)
                .list();
    }
}
