package com.xux.commonWeb.aspect;

import com.xux.commonWeb.annotation.PreAuthorization;
import com.xux.commonWeb.context.UserContext;
import com.xux.commonWeb.exception.AuthorizationException;
import com.xux.commonWeb.pojo.enums.Role;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.ApplicationContext;
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
    private final ApplicationContext applicationContext;

    @Before("@annotation(preAuthorization)")
    public void preAuthorization(PreAuthorization preAuthorization) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String elExpression = preAuthorization.value();
        String[] split = elExpression.split("\\.");
        assert split.length == 2;
        String beanName = split[0];
        String methodName = split[1];
        Object bean = applicationContext.getBean(beanName);
        Method method = bean.getClass().getMethod(methodName);
        if (!((boolean) method.invoke(bean))) throw new AuthorizationException("没有访问权限");
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
