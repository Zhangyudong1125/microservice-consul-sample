/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraud.antifraudrules;

import com.james.antifraud.dto.ruleresdto.RiskRuleResDto;
import com.james.antifraud.dto.variablevo.IpApplyInfo;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

import com.google.common.base.Strings;
import com.james.antifraud.antifraudrules.abs.AbsAntiFraudRule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author militang
 * @version Id: IpRegRule.java, v 0.1 17/9/15 下午5:31 militang Exp $$
 */

@Rule(name = "ip,IP集中注册或申请次数限制")
@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class IpApplyRule<T> extends AbsAntiFraudRule {

    private boolean executed;

    private T       result;

    @Condition
    public boolean when() {
        String ip = super.getIp();
        if (Strings.isNullOrEmpty(ip)) {
            log.info("IpRegRule  can't get ip");
            return true;
        }

        Integer h3 = 2;//(Integer) redisTemplate.opsForValue().get(ip);

        Integer h12 = 8;

        Integer h72 = 19;

        if (h3 >= 10) {
            return true;
        }
        if (h12 >= 20) {
            return true;
        }
        if (h72 >= 30) {
            return true;
        }
        //IpApplyInfo ipApplyInfo = (IpApplyInfo) super.variable;

        /* IpApplyInfo.AppliedInfo appliedInfo3 = ipApplyInfo.getIpreginfos().get(new Integer(3));
        if (appliedInfo3.getCnt() >= 10) {
            return true;
        }
        IpApplyInfo.AppliedInfo appliedInfo12 = ipApplyInfo.getIpreginfos().get(new Integer(12));
        if (appliedInfo12.getCnt() >= 20) {
            return true;
        }
        
        IpApplyInfo.AppliedInfo appliedInfo72 = ipApplyInfo.getIpreginfos().get(new Integer(72));
        if (appliedInfo72.getCnt() >= 30) {
            return true;
        }*/
        return false;
    }

    @Action
    public void then() throws Exception {
        try {
            log.info("IpRegRule has been executed");
            RiskRuleResDto riskRuleResDto = new RiskRuleResDto();
            riskRuleResDto.setRuleid(getRuleid());
            riskRuleResDto.setRuledesc("触发 IP集中注册或申请次数限制 规则");
            result = (T) riskRuleResDto; // assign your result here
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
        return "G00101";
    }
}