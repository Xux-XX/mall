package com.xux.seckill.controller;

import com.xux.commonWeb.annotation.RequireAdmin;
import com.xux.commonWeb.annotation.RequireLogin;
import com.xux.core.entity.Result;
import com.xux.seckill.pojo.entity.SeckillArrangement;
import com.xux.seckill.pojo.entity.SeckillProduct;
import com.xux.seckill.pojo.enums.SeckillEnum;
import com.xux.seckill.service.ArrangementService;
import com.xux.seckill.service.ProductService;
import com.xux.seckill.service.SeckillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/22 14:10
 */

@RestController
@RequestMapping("/seckill")
@Tag(name = "秒杀模块")
@RequiredArgsConstructor
@Validated
public class SeckillController {
    private final SeckillService seckillService;
    private final ArrangementService arrangementService;
    private final ProductService productService;

    @PostMapping("{seckillId}/{productId}")
    @Operation(summary = "秒杀商品")
    @RequireLogin
    public Result doSeckill(@PathVariable Integer seckillId,
                            @PathVariable Integer productId,
                            @RequestParam("number") @Max(value = 10, message = "一次最大抢购数量为10") Integer number,
                            @RequestParam("addressId") Integer addressId){
        SeckillEnum status = seckillService.doSeckill(seckillId, productId, number, addressId);
        if (status == SeckillEnum.SUCCESS) return Result.ok(status.getMessage(), status.getData());
        return Result.fail(status.getMessage());
    }

    @PostMapping("arrangement")
    @Operation(summary = "新增秒杀场次")
    @RequireAdmin
    public Result addArrangement(@RequestBody SeckillArrangement seckillArrangement){
        Integer arrangementId = arrangementService.addArrangement(seckillArrangement);
        return Result.ok("新增成功", arrangementId);
    }

    @PostMapping("products")
    @Operation(summary = "增加抢购场次对应的商品")
    @RequireAdmin
    public Result addSeckillProducts(@RequestBody List<SeckillProduct> seckillProducts){
        productService.addProductBatch(seckillProducts);
        return Result.ok();
    }

    @DeleteMapping("{seckillId}")
    @Operation(summary = "删除对应秒杀场次")
    @RequireAdmin
    public Result removeArrangement(@PathVariable Integer seckillId){
        arrangementService.removeArrangement(seckillId);
        return Result.ok();
    }

    @GetMapping
    @Operation(summary = "查询秒杀场次")
    public Result getByStartTime(@RequestParam Integer pageNumber,
                                 @RequestParam Integer pageSize){
        List<SeckillArrangement> list = arrangementService.getOrderByBeginTime(pageNumber, pageSize);
        return Result.ok("查询成功", list);
    }

    @GetMapping("{seckillId}")
    @Operation(summary = "查询秒杀场次中的商品")
    public Result getProductBySeckillId(@PathVariable Integer seckillId,
                             @RequestParam Integer pageNumber,
                             @RequestParam Integer pageSize){
        List<SeckillProduct> list = productService.getPageBySeckill(seckillId, pageNumber, pageSize);
        return Result.ok("查询成功", list);
    }
}
