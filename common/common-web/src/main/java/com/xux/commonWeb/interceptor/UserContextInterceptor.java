package com.xux.commonWeb.interceptor;

import com.xux.commonWeb.context.UserContext;
import com.xux.commonWeb.exception.AuthorizationException;
import com.xux.commonWeb.pojo.entity.UserInfo;
import com.xux.core.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/17 16:57
 */

public class UserContextInterceptor implements WebRequestInterceptor {

    @Override
    public void preHandle(WebRequest request) throws Exception {
        String userId = request.getHeader(JWTUtil.USER_ID);
        String roleStatus = request.getHeader(JWTUtil.ROLE_STATUS);
        if (userId == null || roleStatus == null) throw new AuthorizationException("用户信息获取异常");
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
