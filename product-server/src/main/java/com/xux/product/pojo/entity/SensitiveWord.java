package com.xux.product.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xux.commonWeb.pojo.entity.BaseEntity;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "添加词不能为空")
    private String word;

}

