package com.xux.rabbitmq.util;

import java.time.Instant;
import java.util.Random;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/23 18:03
 */
public class MessageUtil {
    private static final Random random = new Random();

    public static Long randomMessageId(){
        long timestamp = Instant.now().toEpochMilli();
        long random = MessageUtil.random.nextInt(1024);
        return timestamp << 10 | random;
    }
}
