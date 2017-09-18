/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.ruletest;

import com.james.TestBase;
import org.easyrules.api.RulesEngine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author militang
 * @version Id: RulesEngineTest.java, v 0.1 17/9/11 下午4:38 militang Exp $$
 */
public class RulesEngineTest2 extends TestBase {
    @Autowired
    private RulesEngine rullesEngine;

    @Autowired
    private RulesEngine rulesEngine;

    @Test
    public void runtest() {
        rullesEngine.fireRules();
        rulesEngine.fireRules();

    }


}