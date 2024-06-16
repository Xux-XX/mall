package com.xux.user.service;

import com.xux.user.pojo.enums.LoginStatus;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/14 21:21
 */
public interface UserService {
    /**
     * 登录并获取jwt
     * @param username 用户名
     * @param password 密码
     * @return SUCCESS: 登陆成功,返回jwt
     *         <p>FAIL: 登陆失败
     */
    LoginStatus login(String username, String password);

    /**
     * 退出登录
     */
    void logout();
}
