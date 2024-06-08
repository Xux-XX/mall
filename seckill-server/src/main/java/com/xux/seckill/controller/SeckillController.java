package com.xux.seckill.controller;

import com.xux.seckill.pojo.enums.SeckillEnum;
import com.xux.seckill.service.SeckillService;
import com.xux.common.context.UserContext;
import com.xux.common.entity.Result;
import com.xux.common.entity.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("seckill")
@RequiredArgsConstructor
public class SeckillController {
    private final SeckillService seckillService;

    @PostMapping("{seckillId}/{productId}")
    public Result doSeckill(@PathVariable("seckillId") Integer seckillId,
                            @PathVariable("productId") Integer productId){
        SeckillEnum res = seckillService.doSeckill(seckillId, productId);
        if (res != SeckillEnum.SUCCESS) return Result.fail(res.getMessage());
        return Result.ok(res.getMessage());
    }

    @PostMapping("/test/{seckillId}/{productId}/{userId}")
    public Result testSeckill(@PathVariable("seckillId") Integer seckillId,
                              @PathVariable("productId") Integer productId,
                              @PathVariable("userId") Integer userId){
        UserContext.set(new UserInfo("", userId));
        SeckillEnum res = seckillService.doSeckill(seckillId, productId);
        if (res != SeckillEnum.SUCCESS) return Result.fail(res.getMessage());
        return Result.ok(res.getMessage());
    }
}
