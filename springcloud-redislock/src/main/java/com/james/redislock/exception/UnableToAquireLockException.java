/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.redislock.exception;

/**
 * @author militang
 * @version Id: UnableToAquireLockException.java, v 0.1 17/9/12 下午2:44 militang Exp $$
 */
public class UnableToAquireLockException extends RuntimeException {
    public UnableToAquireLockException() {
    }

    public UnableToAquireLockException(String message) {
        super(message);
    }

    public UnableToAquireLockException(String message, Throwable cause) {
        super(message, cause);
    }
}