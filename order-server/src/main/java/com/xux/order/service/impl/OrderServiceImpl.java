package com.xux.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xux.commonWeb.api.AddressFeignClient;
import com.xux.commonWeb.api.ProductFeignClient;
import com.xux.commonWeb.context.UserContext;
import com.xux.commonWeb.pojo.entity.Address;
import com.xux.commonWeb.pojo.entity.Product;
import com.xux.commonWeb.util.BeanUtil;
import com.xux.order.mapper.OrderMapper;
import com.xux.order.pojo.dto.CreateOrderDto;
import com.xux.order.pojo.dto.ProductDto;
import com.xux.order.pojo.entity.Order;
import com.xux.order.pojo.entity.OrderProduct;
import com.xux.order.pojo.enums.OrderStatusEnum;
import com.xux.order.pojo.vo.OrderVo;
import com.xux.order.service.OrderProductService;
import com.xux.order.service.OrderService;
import com.xux.rabbitmq.entity.OrderMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/23 20:37
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
        implements OrderService {
    private final AddressFeignClient addressFeignClient;
    private final ProductFeignClient productFeignClient;
    private final OrderProductService orderProductService;
    private final TransactionTemplate transactionTemplate;
    @Value("${mall.order.expire}")
    private Integer expire;
    @Override
    public void createOrderByMessage(OrderMessage message) {
        Address address = addressFeignClient.getById(message.getAddressId());
        Order order = new Order();
        order.setAddress(address);
        order.setSeckillMessageId(message.getMessageId());
        order.setPayPrice(message.getTotalPrice());
        order.setExpireAt(LocalDateTime.now().plusMinutes(expire));
        Product product = productFeignClient.getById(message.getProductId());
        OrderProduct orderProduct = BeanUtil.copyProperties(product, OrderProduct.class);
        // 订单新增和订单商品新增放在一个事务中
        transactionTemplate.executeWithoutResult(status -> {
            this.save(order);
            orderProduct.setOrderId(order.getOrderId());
            orderProductService.addProduct(orderProduct);
        });
    }

    @Override
    public Integer createByProductList(CreateOrderDto createOrderDto) {
        Map<Integer, Integer> map = createOrderDto.getProductList()
                .stream()
                .collect(Collectors.toMap(ProductDto::getProductId, ProductDto::getNumber));
        // 远程调用获取地点详细信息和商品详细信息
        Address address = addressFeignClient.getById(createOrderDto.getAddressId());
        List<OrderProduct> productList = productFeignClient.getByIdBatch(map.keySet().stream().toList())
                .stream()
                .map(product -> BeanUtil.copyProperties(product, OrderProduct.class))
                .toList();
        // 保存订单
        double price = 0;
        for (OrderProduct orderProduct : productList) {
            orderProduct.setNumber(map.get(orderProduct.getOrderId()));
            price += orderProduct.getNumber() * orderProduct.getPrice();
        }
        Order order = new Order();
        order.setAddress(address);
        order.setNote(createOrderDto.getNote());
        order.setPayPrice(price);
        order.setUserId(UserContext.get().getUserId());
        order.setExpireAt(LocalDateTime.now().plusMinutes(expire));
        // 新增订单和新增商品放在一个事务中
        transactionTemplate.executeWithoutResult(status -> {
            this.save(order);
            productList.forEach(orderProduct -> orderProduct.setOrderId(order.getOrderId()));
            orderProductService.addProductBatch(productList);
        });
        return order.getOrderId();
    }

    @Override
    public OrderVo getBySeckillMessageId(Long seckillMessageId) {
        Order order = this.lambdaQuery()
                .eq(Order::getSeckillMessageId, seckillMessageId)
                .eq(Order::getUserId, UserContext.get().getUserId())
                .eq(Order::getStatus, OrderStatusEnum.WAITING_PAYMENT.toInt())
                .one();
        if (order == null) return null;
        // TODO 待完成
//        orderProductService.getByOrderId(order.getOrderId())
//                .stream()
    }

}
