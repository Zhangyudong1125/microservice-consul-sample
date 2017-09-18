/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraud.ruletest;

import java.util.Set;

import com.google.common.collect.Sets;
import com.james.antifraud.TestBase;
import com.james.antifraudrule.antifraudrules.IpApplyRule;
import com.james.antifraudrule.antifraudrules.abs.AbsAntiFraudRule;
import com.james.antifraudrule.dto.antifraudbizreqdto.AntiFraudObj;
import com.james.antifraudrule.dto.antifraudbizreqdto.Location;
import com.james.antifraudrule.dto.ruleresdto.RiskRuleResDto;
import org.easyrules.api.Rule;
import org.easyrules.api.RulesEngine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

/**
 * @author militang
 * @version Id: RulesEngineTest.java, v 0.1 17/9/11 下午4:38 militang Exp $$
 */
@Slf4j
public class RulesEngineTest extends TestBase {

    @Autowired
    private RulesEngine rulesEngine;

    @Test
    public void runtest() {

        AntiFraudObj antiFraudObj = new AntiFraudObj();
        Location location = new Location();
        location.setIp("129.128.117.12");
        antiFraudObj.setLocation(location);
        AbsAntiFraudRule ipApplyRule = new IpApplyRule<RiskRuleResDto>();
        ipApplyRule.setAntiFraudObj(antiFraudObj);

        Set<AbsAntiFraudRule> ruleset = Sets.newHashSet();
        ruleset.add(ipApplyRule);

        for (AbsAntiFraudRule antiFraudRule : ruleset) {
            rulesEngine.registerRule(antiFraudRule);
        }
        rulesEngine.fireRules();
        for (AbsAntiFraudRule antiFraudRule : ruleset) {
            if (antiFraudRule.isExecuted()) {
                RiskRuleResDto riskRuleResDto = (RiskRuleResDto) antiFraudRule.getResult();
                log.info("riskRuleResDto  :{}", riskRuleResDto);
            }
        }

    }

}