package com.xux.seckill.util;

public class SeckillUtil {
    public static String getProductKey(Integer seckillId, Integer productId){
        return "seckill:" + seckillId + ":" + productId;
    }

    public static String getUserKey(Integer userId, Integer seckillId, Integer productId){
        return getProductKey(seckillId,productId) + ":user:" + userId;
    }
}
