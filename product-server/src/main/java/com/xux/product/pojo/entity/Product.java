package com.xux.product.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xux.commonWeb.pojo.entity.BaseEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/12 21:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("tb_product")
public class Product extends BaseEntity {
    /**
     *主键
     */
    @TableId(type = IdType.AUTO)
    private Integer productId;
    /**
     * 所属店铺id
     */
    private Integer storeId;
    /**
     * 商品描述
     */
    @NotBlank
    private String description;
    /**
     * 商品名称
     */
    @NotBlank
    private String name;
    /**
     * 库存
     */
    private Integer stock;
    /**
     * 价格
     */
    private Double price;
    /**
     * 图片URL
     */
    @NotBlank
    private String picture;
    /**
     * 销量
     */
    private Integer sales;
    /**
     * 状态:
     *  1. 是否发布
     *  2. 是否为推荐商品
     *  3. 是否为新商品
     */
    private Integer status;

}
