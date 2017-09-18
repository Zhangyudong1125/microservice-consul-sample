/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.test.redislocktest;

import com.james.test.TestBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author militang
 * @version Id: RedisLockTest.java, v 0.1 17/9/18 上午11:34 militang Exp $$
 */
@Slf4j
public class RedisLockTest extends TestBase {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    RedissonClient        redissonClient;

    @Test
    public  void redisionlock() {
        String serverId = "local";
        Long counter = redisTemplate.opsForValue().increment("COUNTER", 1);
        RLock lock = redissonClient.getLock("TEST");
        try {
            lock.lock();
            log.info("Request Thread - " + counter + "[" + serverId + "] locked and begun...");
            Thread.sleep(5000); // 5 sec
            log.info("Request Thread - " + counter + "[" + serverId + "] ended successfully...");
        } catch (Exception ex) {
            log.error("Error occurred");
        } finally {
            lock.unlock();
            log.info("Request Thread - " + counter + "[" + serverId + "] unlocked...");
        }
        log.info("lock-" + counter + "[" + serverId + "]");
        //return "lock-" + counter + "[" + serverId + "]";
    }
}
