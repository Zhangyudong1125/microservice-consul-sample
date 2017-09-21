/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraudrule.component;

import com.james.antifraudrule.dto.variablevo.ContentRec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author militang
 * @version Id: DeviceRuleChkComponent.java, v 0.1 17/9/21 下午3:55 militang Exp $$
 */
@Component
public class DeviceRuleChkComponent {

    @Autowired
    private RedisTemplate redisTemplate;

    public boolean devicechkrule(String contentKey) {
        Set<String> set = redisTemplate.opsForZSet().range(contentKey, 0, -1);

        int windowTimehrss = Long.valueOf(System.currentTimeMillis() / 1000 / (3600)).intValue();

        int usr12hrs = 0;//使用设备近12小时登录用户数
        int usr168hrs = 0;//使用设备近7时登录用户数
        int windowTimeusr12hrs = windowTimehrss - 12;
        int windowTimeusr168hrs = windowTimehrss - 168;

        for (String devicelogin : set) {
            ContentRec loginRec = new ContentRec(devicelogin);
            if (loginRec.getTimeslong() >= windowTimeusr12hrs) {
                usr12hrs++;
            }
            if (loginRec.getTimeslong() >= windowTimeusr168hrs) {
                usr168hrs++;
            }
            if ((usr12hrs >= 2) || (usr168hrs >= 3)) {
                return true;
            }
        }
        return false;
    }
}