package com.xux.commonWeb.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/9 20:55
 */
public class RequestUtil {
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    public static String getIP(){
        HttpServletRequest httpServletRequest = getHttpServletRequest();
        return httpServletRequest.getRemoteAddr();
    }

    public static String getURI(){
        return getHttpServletRequest().getRequestURI();
    }
}
