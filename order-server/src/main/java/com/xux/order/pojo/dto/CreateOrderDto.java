package com.xux.order.pojo.dto;

import lombok.Data;

import java.util.List;

/**
 * 用于接收前端传来的参数
 * @author xux
 * @version 0.1
 * @since 2024/6/23 20:24
 */
@Data
public class CreateOrderDto {
    private Integer addressId;
    List<ProductDto> productList;
    private String note;
}
