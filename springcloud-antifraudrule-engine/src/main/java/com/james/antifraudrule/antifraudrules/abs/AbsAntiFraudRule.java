/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraudrule.antifraudrules.abs;

import com.james.antifraudrule.dto.antifraudbizreqdto.AntiFraudObj;
import org.easyrules.annotation.Action;
import org.easyrules.api.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author militang
 * @version Id: AbsAntiFraudRule.java, v 0.1 17/9/15 下午5:22 militang Exp $$
 */
@Data
@Component
public abstract class AbsAntiFraudRule<T> {

    protected boolean       executed;

    protected T             result;

    @Autowired
    protected RedisTemplate redisTemplate;

    /**
     * 风控探针的参数传入
     */
    protected AntiFraudObj  antiFraudObj;

    public Object           variable;

    protected abstract String getRuleid();

    public abstract boolean isExecuted();

    public abstract T getResult();

    protected String getIp() {
        return antiFraudObj.getLocation().getIp();
    }

    public abstract boolean when();

    @Action
    public abstract void then() throws Exception;

}