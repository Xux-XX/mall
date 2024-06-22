package com.xux.seckill.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xux.commonWeb.pojo.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/19 21:09
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("tb_seckill_arrangement")
public class SeckillArrangement extends BaseEntity {
    /**
     * 秒杀场次id
     */
    @TableId
    private Integer seckillId;
    /**
     * 秒杀标题
     */
    private String title;
    /**
     * 描述
     */
    private String description;
    /**
     * 秒杀开始时间
     */
    private LocalDateTime startTime;
    /**
     * 秒杀结束时间
     */
    private LocalDateTime endTime;
}
