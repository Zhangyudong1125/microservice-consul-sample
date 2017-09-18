/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraudrule.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author militang
 * @version Id: RedisWindows.java, v 0.1 17/9/18 下午9:24 militang Exp $$
 */
@Component
@Slf4j
public class RedisWindows {

    @Autowired
    private RedisTemplate redisTemplate;

    public int timett() {
        long nowlon = System.currentTimeMillis();
        //滑窗单位为小时
        int windowTimeHours = Long.valueOf(nowlon / 1000 / 3600).intValue();
        return windowTimeHours;
        //log.info();
    }

    public int hoursCnt(int recentHours, String bizKey) {
        long nowlon = System.currentTimeMillis();
        //滑窗单位为小时
        int windowTimeHours = Long.valueOf(nowlon / 1000 / 3600).intValue();
        int cnt = 0;
        for (int i = 0; i < recentHours; i++) {
            String key = bizKey + ":" + windowTimeHours;
            Object objectcnt = redisTemplate.opsForValue().get(key);
            if (null != objectcnt) {
                cnt = cnt + Integer.valueOf(String.valueOf(objectcnt)).intValue();
            }
        }
        return cnt;
    }

    public static void main(String args[]) {
        for (int i = 0; i < 120; i++) {
            int hours = new RedisWindows().timett();
            log.info("hours is {}", hours);
        }
    }
}