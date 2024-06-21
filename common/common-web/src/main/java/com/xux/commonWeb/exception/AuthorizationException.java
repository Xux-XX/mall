package com.xux.commonWeb.exception;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/17 17:07
 */
public class AuthorizationException extends RuntimeException{
    public AuthorizationException(String message) {
        super(message);
    }
}
