package com.xux.seckill.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xux.commonWeb.pojo.entity.BaseEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
     * 关联场次id
     */
    @NotNull(message = "关联抢购场次不能为空")
    private Integer seckillId;
    /**
     * 关联商品id
     */
    @NotNull(message = "关联商品id不能为空")
    private Integer productId;
    /**
     * 秒杀价格
     */
    @NotNull(message = "价格不能为空")
    private Double seckillPrice;
    /**
     * 库存
     */
    @NotNull(message = "库存不能为空")
    private Integer stock;
    /**
     * 每人购买上限
     */
    @NotNull(message = "购买上限不能为空")
    @TableField("`limit`")
    private Integer limit;
    /**
     * 描述
     */
    @NotBlank(message = "描述不能为空")
    private String description;
}
