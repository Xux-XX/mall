package com.xux.seckill.controller;

import com.xux.commonWeb.annotation.RequireLogin;
import com.xux.core.entity.Result;
import com.xux.seckill.pojo.enums.SeckillEnum;
import com.xux.seckill.service.ArrangementService;
import com.xux.seckill.service.ProductService;
import com.xux.seckill.service.SeckillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/22 14:10
 */

@RestController
@RequestMapping("/seckill")
@Tag(name = "秒杀模块")
@RequiredArgsConstructor
public class SeckillController {
    private final SeckillService seckillService;

    @PostMapping("{seckillId}/{productId}")
    @Operation(summary = "秒杀商品")
    public Result doSeckill(@PathVariable Integer seckillId,
                            @PathVariable Integer productId,
                            @RequestParam("number") Integer number,
                            @RequestParam("addressId") Integer addressId){
        SeckillEnum status = seckillService.doSeckill(seckillId, productId, number, addressId);
        if (status == SeckillEnum.SUCCESS) return Result.ok(status.getMessage());
        return Result.fail(status.getMessage());
    }

}
