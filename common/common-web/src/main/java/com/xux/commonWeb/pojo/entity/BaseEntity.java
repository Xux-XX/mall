package com.xux.commonWeb.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class BaseEntity {
    /** 创建用户编号 */
    @TableField(fill = FieldFill.INSERT)
    @JsonIgnore
    private Integer createUser;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    @JsonIgnore
    private LocalDateTime createTime;

    /** 更新用户编号 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonIgnore
    private Integer updateUser;

    /** 更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonIgnore
    private LocalDateTime updateTime;

    /** 逻辑删除 */
    @TableLogic
    @JsonIgnore
    private Boolean isDeleted;
}
