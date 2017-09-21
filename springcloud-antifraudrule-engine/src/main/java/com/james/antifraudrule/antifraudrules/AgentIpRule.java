/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraudrule.antifraudrules;

/**
 * @author militang
 * @version Id: AgentIpRule.java, v 0.1 17/9/21 下午4:36 militang Exp $$
 */

import com.james.antifraudrule.antifraudrules.abs.AbsAntiFraudRule;
import com.james.antifraudrule.dto.ruleresdto.RiskRuleResDto;
import lombok.extern.slf4j.Slf4j;
import org.easyrules.annotation.Rule;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Rule(name = "申请时点IP为代理IP")
@Slf4j
@Component("G00109")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AgentIpRule<T> extends AbsAntiFraudRule {

    @Override
    public boolean isExecuted() {
        return false;
    }

    @Override
    public T getResult() {
        return (T) result;
    }

    @Override
    public boolean when() {

        //// TODO: 17/9/21 代理ip 查询对接 
        return false;
    }

    @Override
    public void then() throws Exception {
        try {
            log.info("AgentIpRule has been executed");
            RiskRuleResDto riskRuleResDto = new RiskRuleResDto();
            riskRuleResDto.setRuleid(getRuleid());
            riskRuleResDto.setRuledesc("触发 申请时点IP为代理IP 规则");
            result = (T) riskRuleResDto; // assign your result here
            executed = true;
        } catch (Exception e) {
            throw e;
        }
    }
}