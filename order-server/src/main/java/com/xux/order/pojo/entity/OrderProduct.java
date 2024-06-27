package com.xux.order.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xux.commonWeb.pojo.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/23 22:16
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("tb_product_in_order")
public class OrderProduct extends BaseEntity {
    @JsonIgnore
    private Integer orderId;
    private Integer productId;
    private String name;
    private String picture;
    private Integer number;
    private Double price;
    private Double totalPrice;
}
