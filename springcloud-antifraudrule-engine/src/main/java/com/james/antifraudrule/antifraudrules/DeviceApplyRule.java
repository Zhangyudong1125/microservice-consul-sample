/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraudrule.antifraudrules;

/**
 * @author militang
 * @version Id: DeviceApplyRule.java, v 0.1 17/9/21 上午10:09 militang Exp $$
 */

import com.james.antifraudrule.antifraudrules.abs.AbsAntiFraudRule;
import com.james.antifraudrule.component.DeviceRuleChkComponent;
import com.james.antifraudrule.dto.ruleresdto.RiskRuleResDto;
import com.james.antifraudrule.dto.variablevo.ContentRec;
import com.james.antifraudrule.enums.AntiFraudTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Rule(name = "设备集中申请")
@Slf4j
@Component("G00103")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DeviceApplyRule<T> extends AbsAntiFraudRule {

    @Autowired
    private DeviceRuleChkComponent deviceRuleChkComponent;




    @Condition
    public boolean when() {

        String devivcePrint = antiFraudObj.getLocation().getDeveiceFingerprint();
        String contentKey = AntiFraudTypeEnum.AUTHAPPLY_EVENT + ":devicePrint:" + devivcePrint;

        if (deviceRuleChkComponent.devicechkrule(contentKey)) {
            return true;
        }
        return false;
    }

    @Action
    public void then() throws Exception {
        try {
            log.info("DeviceApplyRule has been executed");
            RiskRuleResDto riskRuleResDto = new RiskRuleResDto();
            riskRuleResDto.setRuleid(getRuleid());
            riskRuleResDto.setRuledesc("触发 设备集中申请超限 规则");
            result = (T) riskRuleResDto; // assign your result here
            executed = true;
        } catch (Exception e) {
            throw e;
        }
    }
}