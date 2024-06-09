package com.xux.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/9 18:56
 */
@Aspect
@Component
public class LogAspect {

    @Pointcut("@annotation(com.xux.common.annotation.Log)")
    public void logPointCut(){}

    @Around("logPointCut()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String name = method.getName();

        return joinPoint.proceed();
    }
}
