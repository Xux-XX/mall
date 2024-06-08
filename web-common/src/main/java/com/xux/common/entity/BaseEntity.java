package com.xux.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;

import java.time.LocalDateTime;

public class BaseEntity {
    /* 创建用户编号 */
    @TableField(fill = FieldFill.INSERT)
    private Integer createUser;

    /* 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /* 更新用户编号 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer updateUser;

    /* 更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /* 逻辑删除 */
    @TableLogic
    private Boolean isDeleted;
}
