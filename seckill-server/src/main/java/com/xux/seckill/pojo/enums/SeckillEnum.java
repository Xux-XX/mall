package com.xux.seckill.pojo.enums;

public enum SeckillEnum {
    SUCCESS("抢购成功"),
    ERROR("系统异常,请联系管理员"),
    NOT_START("活动还未开始"),
    ENDED("活动未开始或已经结束"),
    LIMIT("您已到达购买上上限"),
    SOLD_OUT("商品已经被抢光啦");

    private final String message;

    SeckillEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
