package com.xux.rabbitmq.aspect;

import com.xux.rabbitmq.entity.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/25 15:10
 */
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class IdempotentAspect {
    private final RedisTemplate<String, Object> redisTemplate;
    // 过期时间，单位小时
    private final long EXPIRE_TIME = 24;

    /**
     * 使用Redis + 分布式ID解决消息队列消费幂等性
     */
    @Around("@annotation(com.xux.rabbitmq.annotation.Idempotent)")
    public Object before(ProceedingJoinPoint joinPoint) throws Throwable {
        // 要求入参唯一且类型为Message的子类
        Object[] args = joinPoint.getArgs();
        if (args.length != 1) throw new RuntimeException("方法入参不唯一");
        if (!(args[0] instanceof Message))
            throw new RuntimeException("入参类型错误,应为" + Message.class + ",实际为" + args[0].getClass());
        Long messageId = ((Message) args[0]).getMessageId();
        String key = getMessageIdKey(messageId);
        Boolean success = redisTemplate.opsForValue().setIfAbsent(key, 0, EXPIRE_TIME, TimeUnit.HOURS);
        if (Boolean.FALSE.equals(success)) {
            log.info("重复消息{}", args[0]);
            return null;
        }
        try {
            return joinPoint.proceed();
        }catch (Throwable e){
            redisTemplate.delete(key);
            throw e;
        }
    }

    private String getMessageIdKey(Long messageId){
        return "rabbitmq:idempotent:" + messageId.toString();
    }
}
