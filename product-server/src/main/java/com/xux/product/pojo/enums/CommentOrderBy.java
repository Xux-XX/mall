package com.xux.product.pojo.enums;

/**
 * 获取评论时的排序规则
 * @author xux
 * @version 0.1
 * @since 2024/6/16 21:04
 */
public enum CommentOrderBy {
    /** 按评分从高到低排序 */
    RATING,
    /** 按点赞数从多到少排序 */
    LIKE_COUNT,
    /** 按创建时间从新到旧排序 */
    CREATE_TIME,
    /** 按默认排序(数据库中顺序) */
    DEFAULT

}
