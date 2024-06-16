package com.xux.user.pojo.enums;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/15 13:00
 */
public enum LoginStatus {
    SUCCESS("登陆成功", ""),
    FAIL("用户名或密码错误", "");

    private final String message;
    private String data;

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public String getData() {
        return data;
    }

    LoginStatus(String message, String data) {
        this.message = message;
        this.data = data;
    }
}
