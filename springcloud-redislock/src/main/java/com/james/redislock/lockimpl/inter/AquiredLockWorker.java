/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.redislock.lockimpl.inter;

/**
 * @author militang
 * @version Id: AquiredLockWorker.java, v 0.1 17/9/12 下午2:42 militang Exp $$
 */
public interface AquiredLockWorker<T> {
    T invokeAfterLockAquire() throws Exception;
}