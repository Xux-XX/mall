package com.xux.comment.pojo.enums;

/**
 * @author xux
 * @version 0.1
 * @date 2024/6/9 15:16
 */
public enum SensitiveEnum {
    SUCCESS("操作成功"),
    PERMISSION_DENIED("权限不足"),
    DUPLICATE("该敏感词已存在"),
    NOT_FOUND("该敏感词不存在");

    final String message;

    SensitiveEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
