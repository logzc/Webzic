package com.logzc.webzic.exception;

/**
 * This is a Util Exception.
 * Created by lishuang on 2016/7/21.
 */
public class ZicException extends RuntimeException {

    public ZicException(Throwable cause) {
        super(cause);
    }

    public ZicException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZicException(String message) {
        super(message);
    }
}
