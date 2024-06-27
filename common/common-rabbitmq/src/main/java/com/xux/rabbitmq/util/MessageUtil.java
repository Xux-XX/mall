package com.xux.rabbitmq.util;

import java.time.Instant;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/23 18:03
 */
public class MessageUtil {
    private static final AtomicInteger id = new AtomicInteger(0);

    public static Long snowflakeId(){
        long timestamp = Instant.now().toEpochMilli();
        int sequence = id.getAndIncrement() & ((1 << 20) - 1);
        return timestamp << 20 | sequence;
    }
}
