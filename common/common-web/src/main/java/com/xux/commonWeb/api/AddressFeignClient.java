package com.xux.commonWeb.api;

import com.xux.commonWeb.pojo.entity.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/23 20:21
 */
@FeignClient(name = "user-server")
public interface AddressFeignClient {
    @GetMapping("/address/{addressId}")
    Address getById(@PathVariable("addressId") Integer addressId);

    @GetMapping("/address/exists/{addressId}")
    Boolean exists(@PathVariable("addressId") Integer addressId);
}
