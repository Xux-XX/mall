package com.xux.user.controller;

import com.xux.commonWeb.annotation.PreAuthorization;
import com.xux.core.entity.Result;
import com.xux.user.pojo.entity.Address;
import com.xux.user.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xux
 * @since 0.0.1
 * @since 2024/6/24 下午4:24
 */
@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping("/address/{addressId}")
    @PreAuthorization()
    public Result getAddress(@PathVariable("addressId") Integer addressId){
        Address address = addressService.getAddressById(addressId);
        return Result.ok("查询成功", address);
    }
}
