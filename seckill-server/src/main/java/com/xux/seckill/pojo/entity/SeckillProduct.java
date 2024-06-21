package com.xux.seckill.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xux.commonWeb.pojo.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/19 22:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("tb_seckill_product")
public class SeckillProduct extends BaseEntity {
    /**
     * 关联商品id
     */
    private Integer seckillId;
    /**
     * 关联商品id
     */
    private Integer productId;
    /**
     * 秒杀价格
     */
    private Double seckillPrice;
    /**
     * 库存
     */
    private Integer stock;
    /**
     * 每人购买上限
     */
    @TableField("`limit`")
    private Integer limit;
    /**
     * 描述
     */
    private String description;
}
