package com.xux.feign;

import com.xux.feign.api.ProductFeignClient;
import com.xux.feign.config.FeignConfig;
import com.xux.feign.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/23 21:27
 */
@SpringBootTest(classes = FeignConfig.class)
public class FeignTest {
    @Autowired
    ProductFeignClient productFeignClient;
    @Test
    void test(){
        Product product = productFeignClient.getById(1, 1, 1);
        System.out.println(product);
    }
}
