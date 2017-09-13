/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.redislock.lockimpl;

import javax.annotation.PostConstruct;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

/**
 * @author militang
 * @version Id: RedissonConnector.java, v 0.1 17/9/12 下午2:45 militang Exp $$
 */
@Component
public class RedissonConnector {
    RedissonClient redisson;

    @PostConstruct
    public void init() {
        redisson = Redisson.create();
    }

    public RedissonClient getClient() {
        return redisson;
    }
}