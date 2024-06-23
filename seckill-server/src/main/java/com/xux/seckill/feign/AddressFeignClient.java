package com.xux.seckill.feign;

import com.xux.core.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/23 17:38
 */
@FeignClient
public interface AddressFeignClient {
    @GetMapping("/address/exists/{addressId}")
    Result exists(@PathVariable("addressId") Integer addressId);
}
