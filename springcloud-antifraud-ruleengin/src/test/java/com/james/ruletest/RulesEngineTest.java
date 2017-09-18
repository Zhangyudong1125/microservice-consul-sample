/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.ruletest;

import com.james.TestBase;
import com.james.antifraud.antifraudrules.IpApplyRule;
import com.james.antifraud.antifraudrules.abs.AbsAntiFraudRule;
import com.james.antifraud.dto.antifraudbizreqdto.AntiFraudObj;
import com.james.antifraud.dto.antifraudbizreqdto.Location;
import com.james.antifraud.dto.ruleresdto.RiskRuleResDto;
import lombok.extern.slf4j.Slf4j;
import org.easyrules.api.RulesEngine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

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
        Location  location= new Location();
        location.setIp("129.128.117.12");
        AbsAntiFraudRule  ipApplyRule = new IpApplyRule<RiskRuleResDto>();
        ipApplyRule.setAntiFraudObj(antiFraudObj);

        rulesEngine.registerRule(ipApplyRule);
        rulesEngine.fireRules();

        Set rulesset = rulesEngine.getRules();

        for (Object object : rulesset) {
            AbsAntiFraudRule absAntiFraudRule = (AbsAntiFraudRule) object;
            if (absAntiFraudRule.isExecuted()) {
                log.info("absAntiFraudRule  :{}", absAntiFraudRule.getResult());
            }

        }

    }

}