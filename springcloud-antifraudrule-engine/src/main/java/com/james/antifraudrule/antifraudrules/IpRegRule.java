/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraudrule.antifraudrules;

import com.james.antifraudrule.antifraudrules.abs.AbsAntiFraudRule;
import com.james.antifraudrule.component.IpRuleChkComponent;
import com.james.antifraudrule.component.RedisWindows;
import com.james.antifraudrule.dto.ruleresdto.RiskRuleResDto;
import com.james.antifraudrule.dto.variablevo.ContentRec;
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

import java.util.Set;

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
    private IpRuleChkComponent ipRuleChkComponent;

    @Condition
    public boolean when() {
        String ip = super.getIp();
        if (Strings.isNullOrEmpty(ip)) {
            log.info("IpRegRule  can't get ip");
            return true;
        }
        String contentKey = AntiFraudTypeEnum.REG_EVENT.name() + ":ip:" + ip;
        //String contentKey = "LOGN_EVENT:86000000000110:devicePrint:ABS87DJJR777D"  action + ":contractNo:"+markName + ":" + mark;*/;
        //String convalue = "fingerprint:ABS87DJJR777D:677637763:6";
        if (ipRuleChkComponent.ipRuleCheck(contentKey)) {
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
        return (T) result;
    }

}