package com.xux.order.controller;

import com.xux.commonWeb.annotation.PreAuthorization;
import com.xux.commonWeb.annotation.RequireLogin;
import com.xux.core.entity.Result;
import com.xux.order.pojo.vo.OrderVo;
import com.xux.order.service.OrderService;
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
 * @since 2024/6/26 21:53
 */
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Tag(name = "订单模块")
@Validated
public class OrderController {
    private final OrderService orderService;

    @GetMapping("seckill/{seckillMessageId}")
    @Operation(summary = "通过秒杀uid获取订单", description = "该接口用于秒杀场景下前端轮询订单是否创建成功")
    @RequireLogin
    public Result checkSeckillOrder(@PathVariable Long seckillMessageId){
        OrderVo vo = orderService.getBySeckillMessageId(seckillMessageId);
        return Result.ok("查询成功", vo);
    }

    @GetMapping("{orderId}")
    @Operation(summary = "通过订单id获取订单")
    @RequireLogin
    public Result getOrderById(@PathVariable Integer orderId){
        OrderVo order = orderService.getByOrderId(orderId);
        return Result.ok("查询成功", order);
    }

    @GetMapping("{pageNumber}/{pageSize}")
    @Operation(summary = "分页查询订单信息", description = "返回结果按时间从进到远")
    @RequireLogin
    public Result getOrder(@PathVariable Integer pageNumber,
                           @PathVariable @Max(value = 30, message = "最大页大小为30") Integer pageSize){
        List<OrderVo> orderList = orderService.getOrderByTime(pageNumber, pageSize);
        return Result.ok("查询成功", orderList);
    }

    @PostMapping("user/{orderId}")
    @Operation(summary = "取消未支付订单")
    @PreAuthorization("@orderService.isOrderUser(#orderId)")
    public Result cancelNotPayOrder(@PathVariable Integer orderId){
        boolean success = orderService.cancelNotPayOrder(orderId);
        if (success) return Result.ok("取消订单成功");
        return Result.fail("取消失败");
    }
}
