package com.xux.commonWeb.util;

import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/23 22:45
 */
public class BeanUtil {
    @SneakyThrows
    public static <T> T copyProperties(Object source, Class<T> clazz) {
        T instance = clazz.getDeclaredConstructor().newInstance();
        BeanUtils.copyProperties(source, instance);
        return instance;
    }
}
