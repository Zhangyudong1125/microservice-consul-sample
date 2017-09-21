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
 * @version Id: IpRuleChkComponent.java, v 0.1 17/9/21 下午3:51 militang Exp $$
 */
@Component
public class IpRuleChkComponent {

    @Autowired
    RedisTemplate redisTemplate;

    public boolean ipRuleCheck(String contentKey) {
        Set<String> ipApplySet = redisTemplate.opsForZSet().range(contentKey, 0, -1);

        //滑窗单位为小时
        int windowTimeHours = Long.valueOf(System.currentTimeMillis() / 1000 / 3600).intValue();
        int applyinof3 = 0;
        int applyinof12 = 0;
        int applyinof72 = 0;
        for (String contentstr : ipApplySet) {
            ContentRec loginRec = new ContentRec(contentstr);
            if (windowTimeHours >= loginRec.getTimeslong() - 3) {
                applyinof3 = applyinof3 + loginRec.getCnt();
                if (applyinof3 >= 10) {
                    return true;
                }
            }
            if (windowTimeHours >= loginRec.getTimeslong() - 12) {
                applyinof12 = applyinof12 + loginRec.getCnt();
                if (applyinof12 >= 20) {
                    return true;
                }
            }
            if (windowTimeHours >= loginRec.getTimeslong() - 72) {
                applyinof72 = applyinof72 + loginRec.getCnt();
                if (applyinof72 >= 30) {
                    return true;
                }
            }

        }
        return false;
    }
}