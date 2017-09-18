/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraud.antifraudrules.abs;

import com.james.antifraud.dto.antifraudbizreqdto.AntiFraudObj;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author militang
 * @version Id: AbsAntiFraudRule.java, v 0.1 17/9/15 下午5:22 militang Exp $$
 */
@Data
@Component
public abstract class AbsAntiFraudRule<T> {

    @Autowired
    protected RedisTemplate  redisTemplate;

    /**
     * 风控探针的参数传入
     */
    protected AntiFraudObj antiFraudObj;

    public Object          variable;

    protected abstract String getRuleid();

    public abstract boolean isExecuted();

    public abstract T getResult();

    protected String getIp() {
        return antiFraudObj.getLocation().getIp();
    }

}