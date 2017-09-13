/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.ruleengine.samplerules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

/**
 * @author militang
 * @version Id: AnnationRule.java, v 0.1 17/9/11 下午2:56 militang Exp $$
 */
@Rule(name = "my rule", description = "my rule description")
public class AnnationRule {

    //private BusinessData myBusinessData; //data to operate on
    //private BusinessData myBusinessData; //data to operate on

    @Condition
    public boolean when() {
        //my rule conditions
        return true;
    }

    @Action(order = 1)
    public void then() throws Exception {
        //my actions
    }


    // @Action(order = 2)
    @Action(order = 2)
    public void finallye() throws Exception {
        //my final actions
    }

}