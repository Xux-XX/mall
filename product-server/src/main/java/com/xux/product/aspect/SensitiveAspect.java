package com.xux.product.aspect;

import com.xux.product.pojo.entity.Comment;
import com.xux.product.service.SensitiveWordService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/17 14:01
 */

@Aspect
@Component
@RequiredArgsConstructor
public class SensitiveAspect {
    private final SensitiveWordService sensitiveService;

    @Pointcut("@annotation(com.xux.product.annotation.Sensitive)")
    public void sensitive(){}

    @AfterReturning(value = "sensitive()", returning = "commentList")
    public void filterSensitive(List<Comment> commentList){
        Iterator<Comment> iterator = commentList.iterator();
        while (iterator.hasNext()){
            Comment comment = iterator.next();
            if (sensitiveService.hasSensitive(comment.getContent()))iterator.remove();
        }

    }
}
