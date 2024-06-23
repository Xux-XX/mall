package com.xux.feign.entity;

import lombok.Data;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/23 21:33
 */
@Data
public class Product {
    private Integer productId;
    private Integer storeId;
    private String description;
    private String name;
    private Integer stock;
    private Double price;
    private String picture;
    private Integer sales;
    private Integer status;
}
