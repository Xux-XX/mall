package com.xux.commonWeb.interceptor;

import com.xux.commonWeb.context.UserContext;
import com.xux.core.util.JWTUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/26 21:28
 */
public class FeignUserHeaderInterceptor implements RequestInterceptor {
    /**
     * openfeign调用时在请求头自动加上用户上下文信息
     */
    @Override
    public void apply(RequestTemplate template) {
        template.header(JWTUtil.USER_ID, UserContext.get().getUserId().toString());
        template.header(JWTUtil.ROLE_STATUS, UserContext.get().getRoleStatus().toString());
    }
}
