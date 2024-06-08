package com.xux.seckill.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_seckill_product")
public class SeckillProduct {
    /* 秒杀场次编号 */
    private Integer seckillId;

    /* 商品编号 */
    private Integer productId;

    /* 总库存 */
    private Integer total;

    /* 每人购买上限 */
    @TableField("`limit`")
    private Integer limit;

    /* 秒杀价格 */
    private Double price;
}
