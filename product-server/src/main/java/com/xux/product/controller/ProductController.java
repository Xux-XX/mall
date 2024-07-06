package com.xux.product.controller;

import com.xux.commonWeb.annotation.PreAuthorization;
import com.xux.commonWeb.pojo.dto.BuyProductDto;
import com.xux.core.entity.Result;
import com.xux.product.pojo.entity.Product;
import com.xux.product.pojo.enums.ProductOrderBy;
import com.xux.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/23 21:28
 */
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Tag(name = "商品模块")
@Validated
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @Operation(summary = "新增商品")
    @PreAuthorization("@storeService.isOwner(#product.getStoreId())")
    public Result addProduct(@RequestBody Product product){
        productService.addProduct(product);
        return Result.ok();
    }

    @DeleteMapping("{productId}")
    @Operation(summary = "删除商品")
    @PreAuthorization("@productService.isOwner(#productId)")
    public Result removeProduct(@PathVariable Integer productId){
        productService.remove(productId);
        return Result.ok();
    }

    @GetMapping("{productId}")
    @Operation(summary = "通过商品id查询")
    public Result getById(@PathVariable("productId") Integer productId){
        Product product = productService.getProductById(productId);
        return Result.ok("操作成功", product);
    }

    @GetMapping("{productName}")
    @Operation(summary = "通过商品名字模糊查询")
    public Result searchProductLike(@PathVariable String productName,
                                    @RequestParam Integer pageNumber,
                                    @RequestParam @Max(value = 30, message = "最大页大小为30") Integer pageSize,
                                    @RequestParam(defaultValue = "DEFAULT") ProductOrderBy orderBy){
        List<Product> productList = productService.getByNameLike(productName, pageNumber, pageSize, orderBy);
        return Result.ok("查询成功", productList);
    }

    @GetMapping("{storeId}")
    @Operation(summary = "通过店铺id查询商品")
    public Result getByStoreId(@PathVariable Integer storeId,
                               @RequestParam Integer pageNumber,
                               @RequestParam @Max(value = 30, message = "最大页大小为30") Integer pageSize,
                               @RequestParam(defaultValue = "DEFAULT") ProductOrderBy orderBy){
        List<Product> productList = productService.getByStore(storeId, pageNumber, pageSize, orderBy);
        return Result.ok("查询成功", productList);
    }

    @GetMapping("{storeId}/outOfStock")
    @Operation(summary = "获取缺货的商品")
    @PreAuthorization("@storeService.isOwner(#storeId)")
    public Result getByStoreId(@PathVariable Integer storeId,
                               @RequestParam Integer pageNumber,
                               @RequestParam @Max(value = 30, message = "最大页大小为30") Integer pageSize){
        List<Product> productList = productService.getOutOfStock(storeId, pageNumber, pageSize);
        return Result.ok("查询成功", productList);
    }

    @PutMapping("stock/{increment}/{productId}")
    @Operation(summary = "修改商品库存")
    @PreAuthorization("@productService.isOwner(#productId)")
    public Result increaseStock(@PathVariable Integer increment,
                                @PathVariable Integer productId){
        productService.increaseStock(increment, productId);
        return Result.ok();
    }

    @PutMapping("sale")
    @Operation(summary = "批量增加商品销量", description = "用于购买商品时增加销量的接口，只用于内部调用，不暴露出去")
    public Result increaseSaleBatch(@RequestBody List<BuyProductDto> buyProductDtoList){
        productService.increaseSaleBatch(buyProductDtoList);
        return Result.ok();
    }

    @GetMapping("sale/{rankL}/{rankR}")
    public Result getBySaleRank(@PathVariable @Min(1) Integer rankL,
                                @PathVariable Integer rankR){
        if (rankR - rankL > 30)
            throw new ValidationException("查询范围不能大于30");
        List<Product> list = productService.getBySaleRank(rankL, rankR);
        return Result.ok("查询成功", list);
    }
}
