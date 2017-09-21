/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraudrule.antifraudrules;

/**
 * @author militang
 * @version Id: HighRiskAreaMorOpRule.java, v 0.1 17/9/21 下午5:00 militang Exp $$
 */

import com.james.antifraudrule.antifraudrules.abs.AbsAntiFraudRule;
import com.james.antifraudrule.dto.ruleresdto.RiskRuleResDto;
import lombok.extern.slf4j.Slf4j;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.joda.time.DateTime;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Rule(name = "高危地凌晨操作")
@Slf4j
@Component("G00113")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HighRiskAreaMorOpRule<T> extends AbsAntiFraudRule {


    @Override
    @Condition
    public boolean when() {
        //申请者GPS所在城市在盗卡高危地且操作时间在凌晨00:00-05:00
        String gps = super.antiFraudObj.getLocation().getGpsDesc();
        //// TODO: 17/9/21  gps 高危区域

        DateTime now = DateTime.now();
        int hour = now.getHourOfDay();
        if ((hour >= 0) && (hour <= 5)) {
            return true;
        }
        return false;
    }

    @Override
    @Action
    public void then() throws Exception {
        try {
            log.info("HighRiskAreaMorOpRule has been executed");
            RiskRuleResDto riskRuleResDto = new RiskRuleResDto();
            riskRuleResDto.setRuleid(getRuleid());
            riskRuleResDto.setRuledesc("触发 高危地凌晨操作 规则");
            result = (T) riskRuleResDto; // assign your result here
            executed = true;
        } catch (Exception e) {
            // executed flag will remain false if an exception occurs
            throw e;
        }
    }
}