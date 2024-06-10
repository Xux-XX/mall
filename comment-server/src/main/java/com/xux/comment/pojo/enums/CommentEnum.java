package com.xux.comment.pojo.enums;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/10 20:41
 */
public enum CommentEnum {
    SUCCESS("操作成功"),
    STORE_NOT_FOUND("店铺不存在"),
    PERMISSION_DENIED("权限不足");

    final String message;

    public String getMessage() {
        return message;
    }

    CommentEnum(String message) {
        this.message = message;
    }
}
