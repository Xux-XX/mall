package com.xux.user.service;

import com.xux.user.pojo.entity.Address;

/**
 * @author xux
 * @since 0.0.1
 * @since 2024/6/24 下午1:58
 */
public interface AddressService {
    /**
     * 通过id获取
     * @param addressId id
     * @return Address
     */
    Address getAddressById(Integer addressId);

    /**
     * 新增地址
     * @param address 地址信息
     * @return 生成的id
     */
    Integer addAddress(Address address);

    /**
     * 删除地址
     * @param addressId 地址id
     */
    void removeAddress(Integer addressId);

    /**
     * 更新地址信息
     * @param address 地址信息
     */
    void updateAddress(Address address);

    /**
     * 检查调用者(从UserContext中获取)是否为该地址信息的创建者
     * 该方法用于Controller层鉴权
     * @param addressId 地址id
     * @return boolean
     */
    boolean isCreator(Integer addressId);
}
