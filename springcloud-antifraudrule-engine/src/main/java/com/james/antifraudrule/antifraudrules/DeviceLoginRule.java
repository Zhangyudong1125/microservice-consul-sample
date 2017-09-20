/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraudrule.antifraudrules;

import com.google.common.base.Strings;
import com.james.antifraudrule.antifraudrules.abs.AbsAntiFraudRule;
import com.james.antifraudrule.dto.ruleresdto.RiskRuleResDto;
import com.james.antifraudrule.enums.AntiFraudTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author militang
 * @version Id: DeviceLoginRule.java, v 0.1 17/9/19 下午10:31 militang Exp $$
 */

/*申请时使用设备近12小时登录用户数≥2
        申请时使用设备近7天登录用户数≥3*/

@Rule(name = "申请时使用设备近12小时登录用户数")
@Slf4j
@Component("G00102")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DeviceLoginRule extends AbsAntiFraudRule {
    @Override
    protected String getRuleid() {
        return "G00102";
    }

    @Override
    public boolean isExecuted() {
        return false;
    }

    @Override
    public Object getResult() {
        return null;
    }

    @Condition
    public boolean when() {
        String devivcePrint = antiFraudObj.getLocation().getDeveiceFingerprint();


        //申请时使用设备近12小时登录用户数≥2 申请时使用设备近7天登录用户数≥3




        return false;
    }

    @Action
    public void then() throws Exception {
        /* try {
            log.info("IpRegRule has been executed");
            RiskRuleResDto riskRuleResDto = new RiskRuleResDto();
            riskRuleResDto.setRuleid(getRuleid());
            riskRuleResDto.setRuledesc("触发 IP集中注册或申请次数限制 规则");
            result = (T) riskRuleResDto; // assign your result here
            executed = true;
        } catch (Exception e) {
            // executed flag will remain false if an exception occurs
            throw e;
        }*/
    }
}