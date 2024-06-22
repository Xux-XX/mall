package com.xux.seckill.pojo.constant;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/20 23:07
 */
public class RedisConstant {
    private static final String SECKILL = "seckill";
    private static final String PRODUCT = "product";
    private static final String STOCK = "stock";
    private static final String LIMIT = "limit";
    private static final String USER = "user";
    public static final String START_TIME = "startTime";
    public static final String END_TIME = "endTIme";

    public static String getSeckillKey(Integer seckillId){
        return SECKILL + ":" + seckillId;
    }
    public static String getProductKey(Integer seckillId, Integer productId){
        return getSeckillKey(seckillId)+ ":" + PRODUCT + ":" + productId;
    }
    public static String getUserKey(Integer seckillId,Integer productId, Integer userId){
        return getProductKey(seckillId, productId) + ":" + USER + ":" + userId;
    }
    public static String getUserLockKey(String userKey){
        return userKey + ":lock";
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
    public static String getStockLockKey(String stockKey){
        return stockKey + ":lock";
    }
}
