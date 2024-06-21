package com.xux.seckill.pojo.enums;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/20 20:40
 */
public enum SeckillEnum {
    SUCCESS("抢购成功"),
    OUT_OF_TIME("未处于秒杀时间段"),
    LIMIT("超过购买上限"),
    RETRY("系统繁忙,请重试"),
    SOLD_OUT("已经被抢光啦");

    private final String message;

    public String getMessage() {
        return message;
    }

    SeckillEnum(String message) {
        this.message = message;
    }
}
