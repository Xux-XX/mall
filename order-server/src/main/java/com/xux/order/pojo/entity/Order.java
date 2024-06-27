package com.xux.order.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xux.commonWeb.pojo.entity.Address;
import com.xux.commonWeb.pojo.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/23 15:16
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("tb_order")
public class Order extends BaseEntity {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer orderId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 商铺id
     */
    private Integer storeId;
    /**
     * 订单过期时间点
     */
    private LocalDateTime expireAt;
    /**
     * 需要支付的价格
     */
    private Double payPrice;
    /**
     * 订单备注
     */
    private String note;
    /**
     * 秒杀业务中产生的uid
     */
    private Long seckillMessageId;
    /**
     * 订单状态:
     *  0: 未支付
     *  1: 已支付
     *  2: 已取消
     *  3: 订单超时
     */
    private Integer status;
    /**
     * 收货人姓名
     */
    private String consigneeName;
    /**
     * 收货人电话
     */
    private String consigneePhone;
    /**
     * 收货地址(省)
     */
    private String consigneeProvince;
    /**
     * 收货地址(市)
     */
    private String consigneeCity;
    /**
     * 收货地址(区)
     */
    private String consigneeRegion;
    /**
     * 详细地址
     */
    private String consigneeDetailAddress;

    /**
     * 设置地址相关属性
     */
    public void setAddress(Address address){
        BeanUtils.copyProperties(address, this);
    }
}
