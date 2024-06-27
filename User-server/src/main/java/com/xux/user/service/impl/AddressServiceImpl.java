package com.xux.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xux.commonWeb.context.UserContext;
import com.xux.commonWeb.pojo.entity.BaseEntity;
import com.xux.user.mapper.AddressMapper;
import com.xux.user.pojo.entity.Address;
import com.xux.user.service.AddressService;

/**
 * @author xux
 * @since 0.0.1
 * @since 2024/6/24 下午4:15
 */
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address>
        implements AddressService {

    @Override
    public Address getAddressById(Integer addressId) {
        return this.getById(addressId);
    }

    @Override
    public Integer addAddress(Address address) {
        address.setAddressId(null);
        this.save(address);
        return address.getAddressId();
    }

    @Override
    public void removeAddress(Integer addressId) {
        this.removeById(addressId);
    }

    @Override
    public void updateAddress(Address address) {
        this.lambdaUpdate()
                .eq(Address::getAddressId, address.getAddressId())
                .update(address);
    }

    @Override
    public boolean isCreator(Integer addressId) {
        return this.lambdaQuery()
                .eq(Address::getAddressId, addressId)
                .eq(BaseEntity::getCreateUser, UserContext.get().getUserId())
                .exists();

    }
}
