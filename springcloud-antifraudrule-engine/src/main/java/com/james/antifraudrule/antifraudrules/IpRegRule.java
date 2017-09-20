/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraudrule.antifraudrules;

import com.james.antifraudrule.antifraudrules.abs.AbsAntiFraudRule;
import com.james.antifraudrule.component.RedisWindows;
import com.james.antifraudrule.dto.ruleresdto.RiskRuleResDto;
import com.james.antifraudrule.dto.variablevo.IpRegInfo;
import com.james.antifraudrule.enums.AntiFraudTypeEnum;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

import com.google.common.base.Strings;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author militang
 * @version Id: IpRegRule.java, v 0.1 17/9/15 下午5:31 militang Exp $$
 */

@Rule(name = "ip,IP集中注册或申请")
@Slf4j
@Component("G00101")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class IpRegRule<T> extends AbsAntiFraudRule {

    @Autowired
    private RedisWindows redisWindows;

    private boolean      executed;

    private T            result;

    @Condition
    public boolean when() {
        String ip = super.getIp();
        if (Strings.isNullOrEmpty(ip)) {
            log.info("IpRegRules  can't get ip");
            return true;
        }

        int regedinof3 = redisWindows.hoursCnt(3, AntiFraudTypeEnum.REG_EVENT.name() + ":" + ip);
        if (regedinof3 >= 10) {
            return true;
        }

        int regedinof12 = redisWindows.hoursCnt(12, AntiFraudTypeEnum.REG_EVENT.name() + ":" + ip);
        if (regedinof12 >= 10) {
            return true;
        }

        int regedinof72 = redisWindows.hoursCnt(72, AntiFraudTypeEnum.REG_EVENT.name() + ":" + ip);
        if (regedinof72 >= 10) {
            return true;
        }

        return false;
    }

    @Action
    public void then() throws Exception {
        try {
            log.info("IpRegRules has been executed");
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