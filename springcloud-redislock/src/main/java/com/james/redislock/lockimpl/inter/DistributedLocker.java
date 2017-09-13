/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.redislock.lockimpl.inter;

import com.james.redislock.exception.UnableToAquireLockException;

/**
 * @author militang
 * @version Id: DistributedLocker.java, v 0.1 17/9/12 下午2:43 militang Exp $$
 */
public interface DistributedLocker {
    /**
     * 获取锁
     * @param resourceName  锁的名称
     * @param worker 获取锁后的处理类
     * @param <T>
     * @return 处理完具体的业务逻辑要返回的数据
     * @throws UnableToAquireLockException
     * @throws Exception
     */
    <T> T lock(String resourceName, AquiredLockWorker<T> worker) throws UnableToAquireLockException,
                                                                 Exception;

    <T> T lock(String resourceName, AquiredLockWorker<T> worker,
               int lockTime) throws UnableToAquireLockException, Exception;

}