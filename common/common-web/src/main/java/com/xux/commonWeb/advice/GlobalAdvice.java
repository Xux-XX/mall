package com.xux.commonWeb.advice;

import com.xux.commonWeb.exception.AuthorizationException;
import com.xux.core.entity.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/17 17:24
 */
@ControllerAdvice
public class GlobalAdvice {
    @ExceptionHandler(AuthorizationException.class)
    public Result authorizationException(AuthorizationException e){
        return Result.fail(e.getMessage());
    }
}
