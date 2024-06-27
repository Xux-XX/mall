package com.xux.order.controller;

import com.xux.core.entity.Result;
import com.xux.order.pojo.vo.OrderVo;
import com.xux.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/26 21:53
 */
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Tag(name = "订单模块")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("seckill/{seckillMessageId}")
    @Operation(summary = "通过秒杀uid获取订单信息", description = "该接口用于秒杀场景下前端轮询订单是否创建成功")
    public Result checkSeckillOrder(@PathVariable Long seckillMessageId){
        OrderVo vo = orderService.getBySeckillMessageId(seckillMessageId);
        return Result.ok("查询成功", vo);
    }
}
