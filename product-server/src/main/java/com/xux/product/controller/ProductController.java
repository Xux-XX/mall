package com.xux.product.controller;

import com.xux.core.entity.Result;
import com.xux.product.pojo.entity.Product;
import com.xux.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/23 21:28
 */
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("{productId}")
    public Result getById(@PathVariable("productId") Integer productId){
        Product product = productService.getProductById(productId);
        return Result.ok("操作成功", product);
    }
}
