package com.xux.seckill.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("tb_seckill_arrangement")
public class SeckillArrangement {
    /* 秒杀场次编号 */
    @TableId
    private Integer seckillId;
    /* 开始时间 */
    private LocalDateTime startTime;
    /* 结束时间 */
    private LocalDateTime endTime;

}
