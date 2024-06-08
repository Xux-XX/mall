package com.xux.common.context;

import com.xux.common.entity.UserInfo;

/**
 * 使用ThreadLocal在一个线程内传递用户Id
 */
public class UserContext {
    private static final ThreadLocal<UserInfo> threadLocal = new ThreadLocal<>();

    public static UserInfo get(){
        return threadLocal.get();
    }

    public static void set(UserInfo user){
        threadLocal.set(user);
    }

    public static void remove(){
        threadLocal.remove();
    }
}
