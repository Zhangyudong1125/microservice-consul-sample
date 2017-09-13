/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.redislock;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author militang
 * @version Id: ConfigServerApp.java, v 0.1 17/9/4 上午10:31 militang Exp $$
 */
@SpringBootApplication
//@EnableDiscoveryClient
public class RedisLockServerApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder(RedisLockServerApp.class).web(true).run(args);
    }
}
