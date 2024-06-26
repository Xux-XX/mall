package com.xux.product.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xux.commonWeb.pojo.entity.BaseEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("tb_comment")
public class Comment extends BaseEntity {
    /** 评论编号 */
    @TableId(type = IdType.AUTO)
    private Integer commentId;

    /** 父评论id */
    private Integer parentId;

    /** 评论内容 */
    @NotBlank(message = "评论不能为空")
    private String content;

    /** 评论店铺编号 */
    @NotNull(message = "关联店铺不能为空")
    private Integer storeId;

    /**商品id */
    @NotNull(message = "关联商品不能为空")
    private Integer productId;

    /** 评论点赞数 */
    private Integer likeCount;

    /** 评分 */
    @NotNull(message = "评分不能为空")
    @Min(value = 1, message = "评分不能小于1")
    @Max(value = 5, message = "评分不能大于5")
    private Integer rating;
}
