/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraudrule.antifraudrules;

/**
 * @author militang
 * @version Id: DeviceExchangeRule.java, v 0.1 17/9/21 上午10:23 militang Exp $$
 */

import com.google.common.base.Strings;
import com.james.antifraudrule.antifraudrules.abs.AbsAntiFraudRule;
import com.james.antifraudrule.dto.ruleresdto.RiskRuleResDto;
import com.james.antifraudrule.dto.variablevo.ContentRec;
import com.james.antifraudrule.enums.AntiFraudTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Set;

@Rule(name = "异地更换设备")
@Slf4j
@Component("G00104")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DeviceExchangeRule<T> extends AbsAntiFraudRule {


    @Override
    public boolean isExecuted() {
        return false;
    }

    @Override
    public T getResult() {
        return (T)result;
    }

    @Condition
    public boolean when() {

        //申请时点登录IP所在城市非近30天TOP2常用登录地且登录设备非近30天TOP2常登录设备

        String devivcePrint = antiFraudObj.getLocation().getDeveiceFingerprint();
        String contractNo = antiFraudObj.getActor().getContractNo();
        String ipAddr = antiFraudObj.getLocation().getIp();

        String contentDeviceKey = AntiFraudTypeEnum.LOGIN_EVENT + ":" + contractNo + ":devicePrint:"
                                  + devivcePrint;
        String contentIpaddKey = AntiFraudTypeEnum.LOGIN_EVENT + ":" + contractNo + ":ip:" + ipAddr;

        Set<String> deviceSet = redisTemplate.opsForZSet().reverseRange(contentDeviceKey, 0, -1);
        Set<String> ipSet = redisTemplate.opsForZSet().range(contentIpaddKey, 0, -1);

        //String contentKey = "LOGN_EVENT:86000000000110:devicePrint:ABS87DJJR777D"  action + ":contractNo:"+markName + ":" + mark;*/;
        //String convalue = "fingerprint:ABS87DJJR777D:677637763:6";
        if (isTop2(devivcePrint, deviceSet)) {
            return false;
        }

        if (isTop2(ipAddr, ipSet)) {
            return false;
        }

        return true;
    }

    private boolean isTop2(String devivcePrint, Set<String> deviceSet) {
        int i = 0;
        for (String deviccontent : deviceSet) {
            if (i > 2) {
                return true;
            }
            ContentRec contentRec = new ContentRec(deviccontent);
            if (devivcePrint.equals(contentRec.getContent())) {
                return true;
            }
            i++;
        }
        return false;
    }

    @Action
    public void then() throws Exception {
        try {
            log.info("DeviceExchangeRule has been executed");
            RiskRuleResDto riskRuleResDto = new RiskRuleResDto();
            riskRuleResDto.setRuleid(getRuleid());
            riskRuleResDto.setRuledesc("触发 异地更换设备 规则");
            result = (T) riskRuleResDto; // assign your result here
            executed = true;
        } catch (Exception e) {
            throw e;
        }
    }
}