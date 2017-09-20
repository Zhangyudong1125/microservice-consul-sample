/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraud.redisoptest;

import com.google.common.collect.Sets;
import com.james.antifraud.TestBase;
import com.james.antifraudrule.dto.variablevo.LoginCityRec;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author militang
 * @version Id: AntiFraudActionAccumulateTest.java, v 0.1 17/9/19 下午2:20 militang Exp $$
 */
@Slf4j
public class AntiFraudActionAccumulateTest extends TestBase {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void redissettest() {
        Set<String> set = redisTemplate.opsForSet().members("okok");
        log.info("set={}", set);
        int windowTimedays = Long.valueOf(System.currentTimeMillis() / 1000 / (3600 * 24))
            .intValue();
        String citystr = "上海:1:" + windowTimedays;
        redisTemplate.opsForSet().add("okok", citystr);


        redisTemplate.expire("okok",10, TimeUnit.SECONDS);


        Set<LoginCityRec> set2 = redisTemplate.opsForSet().members("okok");

        log.info("set={}", set2);

    }

}