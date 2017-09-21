/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraudrule.antifraudrules;

import com.google.common.base.Strings;
import com.james.antifraudrule.antifraudrules.abs.AbsAntiFraudRule;
import com.james.antifraudrule.component.IpRuleChkComponent;
import com.james.antifraudrule.dto.ruleresdto.RiskRuleResDto;
import com.james.antifraudrule.enums.AntiFraudTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author militang
 * @version Id: LostCardRule.java, v 0.1 17/9/21 下午4:53 militang Exp $$
 */

@Rule(name = "盗卡地区IP集中")
@Slf4j
@Component("G00112")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LostCardRule<T> extends AbsAntiFraudRule {

    @Autowired
    private IpRuleChkComponent ipRuleChkComponent;


    @Override
    @Condition
    public boolean when() {
        String ip = super.getIp();
        if (Strings.isNullOrEmpty(ip)) {
            log.info("IpRegRule  can't get ip");
            return true;
        }
        String city = super.antiFraudObj.getLocation().getCity();

        //// TODO: 17/9/21 获取高危城市
        String contentKey = AntiFraudTypeEnum.AUTHAPPLY_EVENT.name() + ":ip:" + ip;
        if (ipRuleChkComponent.ipRuleCheck(contentKey)) {
            return true;
        }

        return false;
    }

    @Override
    @Action
    public void then() throws Exception {
        try {
            log.info("LostCardRule has been executed");
            RiskRuleResDto riskRuleResDto = new RiskRuleResDto();
            riskRuleResDto.setRuleid(getRuleid());
            riskRuleResDto.setRuledesc("触发 盗卡地区IP集中 规则");
            result = (T) riskRuleResDto; // assign your result here
            executed = true;
        } catch (Exception e) {
            // executed flag will remain false if an exception occurs
            throw e;
        }
    }
}