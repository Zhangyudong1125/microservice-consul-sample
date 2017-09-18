/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.ruleengine.antifraudrules;

import com.google.common.base.Strings;
import com.james.ruleengine.antifraudrules.abs.AbsAntiFraudRules;
import com.james.ruleengine.antifraudrules.variablevo.IpRegInfo;
import lombok.extern.slf4j.Slf4j;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

/**
 * @author militang
 * @version Id: IpRegRule.java, v 0.1 17/9/15 下午5:31 militang Exp $$
 */

@Rule(name = "my rule")
@Slf4j
public class IpRegRule<T> extends AbsAntiFraudRules {

    private boolean executed;

    private T       result;

    @Condition
    public boolean when() {
        String ip = super.getParams().get("ip");
        if (Strings.isNullOrEmpty(ip)) {
            log.info("IpRegRule  can't get ip");
            return false;
        }
        IpRegInfo ipRegInfo = (IpRegInfo) super.variable;
        ipRegInfo.getIpreginfos().get(new Integer(3));
        ipRegInfo.getIpreginfos().get(new Integer(12));
        ipRegInfo.getIpreginfos().get(new Integer(72));

        return true;
    }

    @Action
    public void then() throws Exception {
        try {
            System.out.println("my rule has been executed");
            result = null; // assign your result here
            executed = true;
        } catch (Exception e) {
            // executed flag will remain false if an exception occurs
            throw e;
        }
    }

    public boolean isExecuted() {
        return executed;
    }

    public T getResult() {
        return result;
    }

}