/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraud.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author militang
 * @version Id: RedisConfig.java, v 0.1 17/7/7 上午8:51 militang Exp $$
 */
@Configuration
public class RedisConfig {

    @Autowired
    private RedisTemplate redisTemplate;

    @Bean
    public RedisTemplate serRedisTemplate() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        //redisTemplate.setEnableTransactionSupport(true);
        return redisTemplate;

    }

    @Value("${spring.redis.host}")
    private String  redishost;

    @Value("${spring.redis.port}")
    private String  redishostport;

    @Value("${spring.redis.database}")
    private Integer database;

    @Bean
    public RedissonClient redissonClient() {
        String url = "http://"+redishost + ":" + redishostport;
        Config config = new Config();
        config.useSingleServer().setAddress(url);
        config.useSingleServer().setDatabase(database);
        return Redisson.create(config);
    }

}