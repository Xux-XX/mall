package com.xux.order.pojo.enums;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/23 17:49
 */
public enum CreateOrderEnum {
    SUCCESS("创建订单成功"),
    ERROR_ARG("非法参数");
    final String message;

    CreateOrderEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
