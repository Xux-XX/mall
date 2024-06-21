package com.xux.commonWeb.context;

import com.xux.commonWeb.pojo.entity.UserInfo;

/**
 * 使用ThreadLocal在一个线程内传递用户Id
 */
public class UserContext {
    private static final ThreadLocal<UserInfo> threadLocal = new ThreadLocal<>();

    public static UserInfo get(){
        return threadLocal.get();
    }

    public static void set(UserInfo userId){
        threadLocal.set(userId);
    }

    public static void remove(){
        threadLocal.remove();
    }
}
