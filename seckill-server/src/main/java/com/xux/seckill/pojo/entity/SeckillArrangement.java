package com.xux.seckill.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xux.commonWeb.pojo.entity.BaseEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @TableId(type = IdType.AUTO)
    private Integer seckillId;
    /**
     * 秒杀标题
     */
    @NotBlank(message = "标题不能为空")
    private String title;
    /**
     * 描述
     */
    @NotBlank(message = "描述不能为空")
    private String description;
    /**
     * 秒杀开始时间
     */
    @NotNull(message = "开始时间不能为空")
    private LocalDateTime startTime;
    /**
     * 秒杀结束时间
     */
    @NotNull(message = "结束时间不能为空")
    private LocalDateTime endTime;
}
