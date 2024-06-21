package com.xux.commonWeb.pojo.enums;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/17 16:54
 */
public enum Role {
    ADMIN(1);

    private final Integer code;

    Role(Integer code) {
        this.code = code;
    }

    public static boolean isAdmin(Integer roleStatus){
        return (roleStatus & ADMIN.code) == 0;
    }
}
