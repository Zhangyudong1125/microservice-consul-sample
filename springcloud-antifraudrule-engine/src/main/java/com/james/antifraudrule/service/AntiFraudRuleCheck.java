/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraudrule.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.james.antifraudrule.antifraudrules.IpApplyRule;
import com.james.antifraudrule.antifraudrules.IpRegRule;
import com.james.antifraudrule.antifraudrules.abs.AbsAntiFraudRule;
import com.james.antifraudrule.dto.antifraudbizreqdto.AntiFraudObj;
import com.james.antifraudrule.dto.antifraudbizreqdto.Location;
import com.james.antifraudrule.dto.ruleresdto.RiskRuleResDto;
import lombok.extern.slf4j.Slf4j;
import org.easyrules.api.RulesEngine;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author militang
 * @version Id: AntiFraudRuleCheck.java, v 0.1 17/9/18 下午9:59 militang Exp $$
 */
@Service
@Slf4j
public class AntiFraudRuleCheck implements ApplicationContextAware {

    @Autowired
    private RulesEngine        rulesEngine;

    private static Set<String> ruleidset = Sets.newHashSet();
    static {
        ruleidset.add("G00101");
    }

    private ApplicationContext ctx;

    public List<RiskRuleResDto> antiFraudExcute(AntiFraudObj antiFraudObj) {

        List<RiskRuleResDto> lists = Lists.newArrayList();

        /*     AbsAntiFraudRule ipApplyRule = new IpRegRule<RiskRuleResDto>();
        ipApplyRule.setAntiFraudObj(antiFraudObj);
        
        Set<AbsAntiFraudRule> ruleset = Sets.newHashSet();
        ruleset.add(ipApplyRule);*/

        Set<AbsAntiFraudRule> ruleset = Sets.newHashSet();
        /* for (AbsAntiFraudRule antiFraudRule : ruleset) {
            rulesEngine.registerRule(antiFraudRule);
        }*/

        for (String ruleid : ruleidset) {
            AbsAntiFraudRule antiFraudRule = (AbsAntiFraudRule) ctx.getBean(ruleid);
            antiFraudRule.setAntiFraudObj(antiFraudObj);
            ruleset.add(antiFraudRule);
            rulesEngine.registerRule(antiFraudRule);
        }

        rulesEngine.fireRules();
        for (AbsAntiFraudRule antiFraudRule : ruleset) {
            if (antiFraudRule.isExecuted()) {
                RiskRuleResDto riskRuleResDto = (RiskRuleResDto) antiFraudRule.getResult();
                lists.add(riskRuleResDto);
                log.info("riskRuleResDto  :{}", riskRuleResDto);
            }
        }
        return lists;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }
}