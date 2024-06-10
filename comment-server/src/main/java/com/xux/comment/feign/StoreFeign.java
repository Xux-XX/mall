package com.xux.comment.feign;

import com.xux.common.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/10 20:21
 */
@FeignClient("store-server")
public interface StoreFeign {
    @GetMapping("exist/{storeId}")
    Result exist(@PathVariable("storeId") Integer storeId);
}
