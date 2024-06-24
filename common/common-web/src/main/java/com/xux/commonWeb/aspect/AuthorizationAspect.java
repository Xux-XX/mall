package com.xux.commonWeb.aspect;

import com.xux.commonWeb.annotation.PreAuthorization;
import com.xux.commonWeb.context.UserContext;
import com.xux.commonWeb.exception.AuthorizationException;
import com.xux.commonWeb.pojo.enums.Role;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/17 15:56
 */
@Aspect
@Component
@RequiredArgsConstructor
public class AuthorizationAspect {
    private final SpelExpressionParser parser;
    private final StandardEvaluationContext context;

    @Before("@annotation(preAuthorization)")
    public void preAuthorization(PreAuthorization preAuthorization) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String elExpression = preAuthorization.value();
        Boolean ok = parser.parseExpression(elExpression).getValue(context, Boolean.class);
        if (Boolean.FALSE.equals(ok)) throw new AuthorizationException("没有访问权限");
    }

    @Before("@annotation(com.xux.commonWeb.annotation.RequireAdmin)")
    public void requireAdmin(){
        Integer roleStatus = UserContext.get().getRoleStatus();
        if (!Role.isAdmin(roleStatus))throw new AuthorizationException("没有访问权限");
    }

    @Before("@annotation(com.xux.commonWeb.annotation.RequireLogin)")
    public void requireLogin(){
        Integer userId = UserContext.get().getUserId();
        if (userId == 0) throw new AuthorizationException("没有访问权限");
    }
}
