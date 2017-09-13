/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.ruleengine.rules;

import org.easyrules.core.BasicRule;

/**
 * @author militang
 * @version Id: MyRule.java, v 0.1 17/9/11 下午2:54 militang Exp $$
 */
public class MyRule extends BasicRule {

    //private BusinessData myBusinessData; //data to operate on

    @Override
    public boolean evaluate() {
        //my rule conditions
        return true;
    }

    @Override
    public void execute() throws Exception {
        //my actions
    }

}