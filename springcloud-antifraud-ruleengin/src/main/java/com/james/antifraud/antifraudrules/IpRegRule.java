/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraud.antifraudrules;

import com.james.antifraud.antifraudrules.abs.AbsAntiFraudRule;
import com.james.antifraud.dto.variablevo.IpRegInfo;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

import com.google.common.base.Strings;

import lombok.extern.slf4j.Slf4j;

/**
 * @author militang
 * @version Id: IpRegRule.java, v 0.1 17/9/15 下午5:31 militang Exp $$
 */

@Rule(name = "ip,IP集中注册或申请")
@Slf4j
public class IpRegRule<T> extends AbsAntiFraudRule {

    private boolean executed;

    private T       result;

    @Condition
    public boolean when() {
        String ip = super.getIp();
        if (Strings.isNullOrEmpty(ip)) {
            log.info("IpRegRule  can't get ip");
            return true;
        }
        IpRegInfo ipRegInfo = (IpRegInfo) super.variable;
        IpRegInfo.Regedinof regedinof3 = ipRegInfo.getIpreginfos().get(new Integer(3));
        if (regedinof3.getCnt() >= 10) {
            return true;
        }
        IpRegInfo.Regedinof regedinof12 = ipRegInfo.getIpreginfos().get(new Integer(12));

        if (regedinof12.getCnt() >= 10) {
            return true;
        }
        IpRegInfo.Regedinof regedinof72 = ipRegInfo.getIpreginfos().get(new Integer(72));

        if (regedinof72.getCnt() >= 10) {
            return true;
        }
        return false;
    }

    @Action
    public void then() throws Exception {
        try {
            log.info("IpRegRule has been executed");
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

    @Override
    protected String getRuleid() {
        return null;
    }
}