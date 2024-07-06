package com.xux.commonWeb.interceptor;

import com.xux.commonWeb.context.UserContext;
import com.xux.commonWeb.exception.AuthorizationException;
import com.xux.commonWeb.pojo.entity.UserInfo;
import com.xux.core.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/17 16:57
 */
@Slf4j
public class UserContextInterceptor implements WebRequestInterceptor {

    @Override
    public void preHandle(WebRequest request) throws Exception {
        String userId = request.getHeader(JWTUtil.USER_ID);
        String roleStatus = request.getHeader(JWTUtil.ROLE_STATUS);
        if (userId == null || roleStatus == null) {
            log.warn("用户信息获取异常,{}", request.getDescription(false));
            throw new AuthorizationException("用户信息获取异常");
        }
        UserContext.set(new UserInfo(Integer.valueOf(userId), Integer.valueOf(roleStatus)));
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {

    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {
        UserContext.remove();
    }
}
