package com.xux.comment.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xux.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("tb_sensitive_words")
public class SensitiveWord extends BaseEntity {
    /* 敏感词编号 */
    @TableId(type = IdType.AUTO)
    private Integer wordId;

    /* 敏感词 */
    private String word;

}

