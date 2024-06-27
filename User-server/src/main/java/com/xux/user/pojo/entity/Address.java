package com.xux.user.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xux.commonWeb.pojo.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 收货地址信息
 * @author xux
 * @since 0.0.1
 * @since 2024/6/24 下午1:53
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("tb_address")
public class Address extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Integer addressId;
    /**
     * 收货人姓名
     */
    private String consigneeName;
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
    private String consigneeDetail;
}
