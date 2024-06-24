package com.xux.feign.api;

import com.xux.feign.dto.BuyProductDto;
import com.xux.feign.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/23 20:22
 */
@FeignClient("product-server")
public interface ProductFeignClient {
    @GetMapping("/product/{productId}")
    Product getById(@PathVariable("productId") Integer productId,
                    @RequestHeader("userId")Integer userId,
                    @RequestHeader("roleStatus") Integer roleStatus);

    @GetMapping("/product")
    List<Product> getByIdBatch(@RequestParam("ids") List<Integer> productId);

    @PostMapping("/product/buy")
    void HasBuyProduct(@RequestBody List<BuyProductDto> buyProductDtoList);
}
