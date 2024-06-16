package com.xux.common.context;

/**
 * 使用ThreadLocal在一个线程内传递用户Id
 */
public class UserContext {
    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static Integer get(){
//        return new UserInfo("", 1);
        return threadLocal.get();
    }

    public static void set(Integer userId){
        threadLocal.set(userId);
    }

    public static void remove(){
        threadLocal.remove();
    }
}
