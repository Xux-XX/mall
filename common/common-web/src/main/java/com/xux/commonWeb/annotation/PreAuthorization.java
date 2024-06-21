package com.xux.commonWeb.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/17 15:57
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PreAuthorization {
    // 使用el表达式动态授权
    String value() default "";
}
