/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.ruleengine.springrules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

import lombok.extern.slf4j.Slf4j;

/**
 * @author militang
 * @version Id: DummyRule.java, v 0.1 17/9/11 下午3:39 militang Exp $$
 */
@Rule(name = "dummy rule")
@Slf4j
public class DummyRule {
    @Condition
    public boolean when() {
        return true;
    }

    @Action
    public void then() {
        log.info("Hey, I'm managed by Spring");
        //System.out.println("Hey, I'm managed by Spring");
    }
}