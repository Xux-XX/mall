package com.xux.product.pojo.enums;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/10 20:41
 */
public enum CommentEnum {
    /** 新增或删除评论时的枚举类 */
    SUCCESS("操作成功"),
    PRODUCT_NOT_FOUND("商品不存在"),
    PERMISSION_DENIED("权限不足");

    final String message;

    public String getMessage() {
        return message;
    }

    CommentEnum(String message) {
        this.message = message;
    }
}
