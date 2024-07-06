package com.xux.product.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xux.commonWeb.pojo.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/16 22:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("tb_store")
public class Store extends BaseEntity {
    /**
     * 主键id
     */
    @TableId
    private Integer storeId;
    /**
     * 店铺名称
     */
    private String name;
    /**
     * 店铺描述
     */
    private String description;
    /**
     * 店铺拥有者
     */
    private Integer owner;
    /**
     * 店铺状态
     */
    private Integer status;
}
