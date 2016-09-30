package com.logzc.webzic.exception;

/**
 * This is a Util Exception.
 * Created by lishuang on 2016/7/21.
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(String message) {
        super(message);
    }
}
