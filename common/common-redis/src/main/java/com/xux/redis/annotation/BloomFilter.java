package com.xux.redis.annotation;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/25 18:15
 */
public @interface BloomFilter {
    /**
     * 布隆过滤器中对应的key
     */
    String value() default "";

    /**
     * 指明方法中第几个参数是主键
     */
    int argId() default 0;
}
