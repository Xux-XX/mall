package com.xux.order;

import com.xux.core.entity.Result;
import com.xux.order.service.OrderService;
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
public class OrderController {
    private final OrderService orderService;

    @GetMapping("seckill/{seckillMessageId}")
    public Result checkSeckillOrder(@PathVariable Long seckillMessageId){
        // TODO 待完成
        return Result.error();
    }
}
