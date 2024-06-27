package com.xux.order.pojo.vo;

import com.xux.order.pojo.entity.OrderProduct;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/26 22:01
 */
@Data
public class OrderVo {
    private Integer orderId;
    private Integer userId;
    private Integer storeId;
    private LocalDateTime expireAt;
    private Double payPrice;
    private String note;
    private Integer status;
    private String consigneeName;
    private String consigneePhone;
    private String consigneeProvince;
    private String consigneeCity;
    private String consigneeRegion;
    private String consigneeDetailAddress;
    private List<OrderProduct> productList;

}
