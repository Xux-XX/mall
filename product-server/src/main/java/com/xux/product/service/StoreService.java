package com.xux.product.service;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/16 22:07
 */
public interface StoreService {
    /**
     * 判断当前用户是否为店铺拥有者
     * @param storeId 店铺id
     * @return boolean
     */
    boolean isOwner(Integer storeId);
}
