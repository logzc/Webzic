package com.logzc.webzic.exception;

/**
 * This is a Util Exception.
 * Created by lishuang on 2016/7/21.
 */
public class AccessDenyException extends RuntimeException {

    public AccessDenyException(Throwable cause) {
        super(cause);
    }

    public AccessDenyException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessDenyException(String message) {
        super(message);
    }
}
