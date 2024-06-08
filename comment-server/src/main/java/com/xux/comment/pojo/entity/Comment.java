package com.xux.comment.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xux.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("tb_comment")
public class Comment extends BaseEntity {
    /* 评论编号 */
    @TableId(type = IdType.AUTO)
    private Integer commentId;

    /* 评论内容 */
    private String content;

    /* 评论用户编号 */
    private Integer userId;

    /* 评论店铺编号 */
    private Integer storeId;

    /* 评分 */
    private Integer rating;
}
