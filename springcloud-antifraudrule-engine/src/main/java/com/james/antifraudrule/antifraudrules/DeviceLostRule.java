/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraudrule.antifraudrules;

import com.james.antifraudrule.antifraudrules.abs.AbsAntiFraudRule;
import com.james.antifraudrule.dto.ruleresdto.RiskRuleResDto;
import lombok.extern.slf4j.Slf4j;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author militang
 * @version Id: DeviceLostRule.java, v 0.1 17/9/21 下午5:13 militang Exp $$
 */
@Rule(name = "设备丢失")
@Slf4j
@Component("G00114")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DeviceLostRule<T> extends AbsAntiFraudRule {

    @Override
    @Condition
    public boolean when() {

        //同一设备关联账号数≥2且同一设备关联的手机号归属地≥2

        return false;
    }

    @Override
    @Action
    public void then() throws Exception {
        try {
            log.info("DeviceLostRule has been executed");
            RiskRuleResDto riskRuleResDto = new RiskRuleResDto();
            riskRuleResDto.setRuleid(getRuleid());
            riskRuleResDto.setRuledesc("触发 设备丢失 规则");
            result = (T) riskRuleResDto; // assign your result here
            executed = true;
        } catch (Exception e) {
            // executed flag will remain false if an exception occurs
            throw e;
        }
    }
}