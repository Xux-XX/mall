package com.xux.commonWeb.aspect;

import com.alibaba.fastjson2.JSON;
import com.xux.commonWeb.annotation.Log;
import com.xux.commonWeb.context.UserContext;
import com.xux.commonWeb.pojo.entity.LogEntity;
import com.xux.commonWeb.service.LogService;
import com.xux.commonWeb.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/9 18:56
 */
@Aspect
@Component
@Slf4j
public class LogAspect {
    @Autowired
    private LogService logService;
    private static final ThreadLocal<Long> beginTime = new ThreadLocal<>();

    @Around("@annotation(controllerLog)")
    public Object logAround(ProceedingJoinPoint joinPoint, Log controllerLog) throws Throwable {
        beginTime.set(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
        Object result = joinPoint.proceed();
        Long cost = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli() - beginTime.get();
        beginTime.remove();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String params = JSON.toJSONString(method.getParameters());
        String logResult = JSON.toJSONString(result);
        String requestURI = RequestUtil.getURI();
        String requestIP = RequestUtil.getIP();
        LogEntity info = LogEntity.builder()
                .costTime(cost)
                .methodName(method.toString())
                .logType("INFO")
                .param(params)
                .description(controllerLog.description())
                .userId(UserContext.get().getUserId())
                .result(logResult)
                .requestIp(requestIP)
                .requestURI(requestURI)
                .exceptionDetail(null)
                .build();
        logService.saveAsync(info);
        return result;
    }

    @AfterThrowing(value = "@annotation(controllerLog)", throwing = "e")
    public void doAfterThrow(JoinPoint joinPoint, Log controllerLog, Throwable e){
        Long cost = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli() - beginTime.get();
        beginTime.remove();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String params = JSON.toJSONString(method.getParameters());
        String requestURI = RequestUtil.getURI();
        String requestIP = RequestUtil.getIP();
        LogEntity info = LogEntity.builder()
                .costTime(cost)
                .methodName(method.toString())
                .logType("ERROR")
                .param(params)
                .description(controllerLog.description())
                .userId(UserContext.get().getUserId())
                .result(null)
                .requestIp(requestIP)
                .requestURI(requestURI)
                .exceptionDetail(Arrays.toString(e.getStackTrace()))
                .build();
        logService.saveAsync(info);
    }
}
