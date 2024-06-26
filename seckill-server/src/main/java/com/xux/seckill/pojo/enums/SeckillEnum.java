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
    SOLD_OUT("已经被抢光啦"),
    ERROR_ARG("参数异常");

    private final String message;
    private Long data;

    public Long getData() {
        return data;
    }

    public SeckillEnum setData(Long data) {
        this.data = data;
        return this;
    }

    SeckillEnum(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
