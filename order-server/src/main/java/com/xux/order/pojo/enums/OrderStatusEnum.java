package com.xux.order.pojo.enums;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/26 22:24
 */
public enum OrderStatusEnum {
    WAITING_PAYMENT(0),
    COMPLETED(1),
    CANCELLED(2),
    TIMEOUT(3);
    private final Integer status2int;

    OrderStatusEnum(Integer status2int) {
        this.status2int = status2int;
    }

    public Integer toInt() {
        return status2int;
    }
}
