/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraud.ruleenginetest;

import com.james.antifraud.TestBase;
import com.james.antifraudrule.dto.antifraudbizreqdto.AntiFraudObj;
import com.james.antifraudrule.dto.antifraudbizreqdto.Location;
import com.james.antifraudrule.dto.ruleresdto.RiskRuleResDto;
import com.james.antifraudrule.component.AntiFraudRuleCheck;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author militang
 * @version Id: AntiFraudRuleCheckTest.java, v 0.1 17/9/18 下午10:25 militang Exp $$
 */
@Slf4j
public class AntiFraudRuleCheckTest extends TestBase {

    @Autowired
    private AntiFraudRuleCheck antiFraudRuleCheck;

    @Test
    public void antifraudcheck() {
        AntiFraudObj antiFraudObj = new AntiFraudObj();
        Location location = new Location();
        location.setIp("129.128.117.12");
        antiFraudObj.setLocation(location);
        List<RiskRuleResDto> list = antiFraudRuleCheck.antiFraudExcute(antiFraudObj);
        log.info("result list is={}", list);

    }
}