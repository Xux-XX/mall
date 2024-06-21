package com.xux.seckill.pojo.constant;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/20 23:07
 */
public class RedisConstant {
    public static final String SECKILL_KEY = "seckill";
    public static final String STOCK = "stock";
    public static final String START_TIME = "startTime";
    public static final String END_TIME = "endTIme";
    public static final String LIMIT = "limit";

    public static String getSeckillKey(Integer seckillId){
        return SECKILL_KEY + ":" + seckillId;
    }
    public static String getProductKey(Integer seckillId, Integer productId){
        return getSeckillKey(seckillId)+ ":" + productId;
    }
    public static String getUserKey(Integer seckillId, Integer userId){
        return getSeckillKey(seckillId) + ":" + userId;
    }
    public static String getLimitKey(Integer seckillId, Integer productId){
        return getProductKey(seckillId, productId) + ":" + LIMIT;
    }
    public static String getLimitKey(String productKey){
        return productKey + ":" + LIMIT;
    }
    public static String getStockKey(String productKey){
        return productKey + ":" + STOCK;
    }
}
