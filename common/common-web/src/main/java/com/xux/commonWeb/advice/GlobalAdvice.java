package com.xux.commonWeb.advice;

import com.xux.commonWeb.exception.AuthorizationException;
import com.xux.core.entity.Result;
import jakarta.validation.ConstraintViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/17 17:24
 */

@RestControllerAdvice
public class GlobalAdvice {
    @ExceptionHandler({AuthorizationException.class, IllegalArgumentException.class, ConstraintViolationException.class})
    public Result authorizationException(Exception e){
        return Result.fail(e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public Result otherException(){
        return Result.error();
    }
}
