/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraudrule.antifraudrules;

import com.james.antifraudrule.antifraudrules.abs.AbsAntiFraudRule;
import lombok.extern.slf4j.Slf4j;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author militang
 * @version Id: EmergeContactDuplicateRule.java, v 0.1 17/9/21 下午1:59 militang Exp $$
 */

@Rule(name = "紧急联系人重合")
@Slf4j
@Component("G00107")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EmergeContactDuplicateRule<T> extends AbsAntiFraudRule {
    @Override
    protected String getRuleid() {
        return "G00107";
    }

    @Override
    public boolean isExecuted() {
        return false;
    }

    @Override
    public Object getResult() {
        return (T)result;
    }

    @Override
    @Condition
    public boolean when() {
        return false;
    }

    @Override
    @Action
    public void then() throws Exception {

    }
}