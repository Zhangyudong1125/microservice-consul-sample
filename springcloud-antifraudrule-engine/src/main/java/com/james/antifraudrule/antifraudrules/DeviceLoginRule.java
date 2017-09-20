/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraudrule.antifraudrules;

import com.google.common.base.Strings;
import com.james.antifraudrule.antifraudrules.abs.AbsAntiFraudRule;
import com.james.antifraudrule.dto.ruleresdto.RiskRuleResDto;
import com.james.antifraudrule.dto.variablevo.LoginRec;
import com.james.antifraudrule.enums.AntiFraudTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Set;

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
public class DeviceLoginRule<T> extends AbsAntiFraudRule {

    private boolean executed;

    private T       result;

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
        String contentKey = AntiFraudTypeEnum.LOGIN_EVENT + ":devicePrint:" + devivcePrint;

        Set<String> set = redisTemplate.opsForZSet().range(contentKey, 0, -1);

        int windowTimehrss = Long.valueOf(System.currentTimeMillis() / 1000 / (3600)).intValue();

        int usr12hrs = 0;//使用设备近12小时登录用户数
        int usr168hrs = 0;//使用设备近7时登录用户数
        int windowTimeusr12hrs = windowTimehrss - 12;
        int windowTimeusr168hrs = windowTimehrss - 168;

        for (String devicelogin : set) {
            LoginRec loginRec = new LoginRec(devicelogin);
            if (loginRec.getTimeslong() >= windowTimeusr12hrs) {
                usr12hrs++;
            }
            if (loginRec.getTimeslong() >= windowTimeusr168hrs) {
                usr168hrs++;
            }
            if ((usr12hrs >= 2) || (usr168hrs >= 3)) {
                return true;
            }
        }

        //申请时使用设备近12小时登录用户数≥2 申请时使用设备近7天登录用户数≥3

        return false;
    }

    @Action
    public void then() throws Exception {
        try {
            log.info("IpRegRule has been executed");
            RiskRuleResDto riskRuleResDto = new RiskRuleResDto();
            riskRuleResDto.setRuleid(getRuleid());
            riskRuleResDto.setRuledesc("触发 申请时使用设备近12小时登录用户数 规则");
            result = (T) riskRuleResDto; // assign your result here
            executed = true;
        } catch (Exception e) {
            // executed flag will remain false if an exception occurs
            throw e;
        }
    }
}